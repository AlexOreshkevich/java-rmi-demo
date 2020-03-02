package support.jdbc;

import lombok.Setter;
import model.Entity;
import reflect.ClassUtils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RowMapperImpl<T extends Entity> implements RowMapper<T> {

    @Setter
    private T obj;

    @Override
    public T rowMap(final ResultSet rs) throws IllegalAccessException, SQLException {
        List<Field> fields = ClassUtils.getFields(obj.getClass());
        int i = 1;
        for (final Field field : fields) {
            field.set(obj, rs.getObject(i++));
        }
        return obj;
    }
}
