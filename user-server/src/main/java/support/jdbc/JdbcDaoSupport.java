package support.jdbc;

import model.Entity;
import reflect.ClassUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcDaoSupport {

    private static final ConnectionPool pool = new ConnectionPool();

    private Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    public Long getId(String sql) {
        Long id = null;

        try (Connection con = getConnection(); Statement statement = con.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public <T> T selectOne(String sql, RowMapper<T> rowMapper, Long param) {
        T obj = null;

        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setObject(1, param);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    obj = rowMapper.rowMap(rs);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public <T> List<T> selectList(String sql, RowMapper<T> rowMap, T param) {
        List<T> objs = new ArrayList<>();

        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            List<Field> fields = ClassUtils.getFields(param.getClass());
            for (int i = 0; i < fields.size(); i++) {
                statement.setObject(i + 1, fields.get(i));
            }
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    objs.add(rowMap.rowMap(rs));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objs;
    }

    @Deprecated
    public <T> void update(String sql, T... param) {
        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            List<Field> fields = ClassUtils.getFields(param.getClass());
            for (int i = 0; i < fields.size(); i++) {
                statement.setObject(i + 1, fields.get(i));
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends Entity> void create(String sql, T param) {
        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            List<Field> fields = ClassUtils.getFields(param.getClass());
            // TODO insert ID for entity
            for (int i = 0, j = 1; i < fields.size(); i++, j++) {
                Field field = fields.get(i);
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                statement.setObject(j, field.get(param));
                field.setAccessible(isAccessible);
            }
            statement.execute();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void delete(String sql, Long param) {
        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setLong(1, Long.class.cast(param));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
