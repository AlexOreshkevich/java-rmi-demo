package dao.jdbc;

import dao.UserDao;
import model.User;
import sqlbuilder.SqlBuilder;
import support.jdbc.JdbcDaoSupport;
import support.jdbc.RowMapper;
import support.jdbc.RowMapperImpl;

import java.util.List;

public class UserJdbcDao extends JdbcDaoSupport implements UserDao {

    private RowMapper<User> rowMap = new RowMapperImpl<>();
    private SqlBuilder builder = new SqlBuilder();

    @Override
    public List<User> findAll() {
        return selectList(builder.getSelectSQL(User.class), rowMap, null);
    }

    @Override
    public void create(final User obj) {
        create(builder.getInsertSQL(obj), obj);
    }

    @Override
    public User read(final Long id) {
        return selectOne("", rowMap, id);
    }

    @Override
    public void update(final User obj) {
    }

    @Override
    public void delete(final Long id) {
        delete(builder.getDeleteSQL(User.class), id);
    }
}
