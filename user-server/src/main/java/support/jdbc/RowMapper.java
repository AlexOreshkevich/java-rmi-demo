package support.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper<T> {

    T rowMap(ResultSet rs) throws SQLException, IllegalAccessException;
}
