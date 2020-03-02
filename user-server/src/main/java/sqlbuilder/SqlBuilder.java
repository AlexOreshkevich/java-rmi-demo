package sqlbuilder;

import model.Entity;
import reflect.ClassUtils;

import java.lang.reflect.Field;
import java.util.List;

public class SqlBuilder {

    private static final String SQL_INSERT = "INSERT INTO %s.%s (%s) VALUES (%s)";
    private static final String SQL_DELETE = "DELETE FROM %s.%s WHERE id = ?";
    private static final String SQL_NEXT_ID = "SELECT nextval('%s.%s_id_seq'::regclass)";
    private static final String SQL_SELECT_BY_ID = "SELECT %s FROM %s.%s WHERE id = ?)";
    private static final String SQL_SELECT = "SELECT %s FROM %s.%s)";

    private final String schema = "rmi";

    public <T extends Entity> String getInsertSQL(final T obj) {
        List<Field> fields = ClassUtils.getFields(obj.getClass());
        String tableName = obj.getClass().getCanonicalName();

        StringBuilder values = new StringBuilder();
        StringBuilder fieldsInLine = new StringBuilder();
        for (final Field field : fields) {
            fieldsInLine.append(String.format("%s, ", field.getName()));
            values.append("?, ");
        }
        removeLastComma(values);
        removeLastComma(fieldsInLine);

        return String.format(SQL_INSERT, schema, tableName, fieldsInLine, values);
    }

    public String getNextIdSQL(final Class<? extends Entity> clazz) {
        return String.format(SQL_NEXT_ID, schema, clazz.getCanonicalName());
    }

    public String getSelectByIdSQL(final Class<? extends Entity> clazz) {
        List<Field> fields = ClassUtils.getFields(clazz);
        StringBuilder fieldsInLine = new StringBuilder();
        for (final Field field : fields) {
            fieldsInLine.append(field.getName());
            fieldsInLine.append(", ");
        }
        return String.format(SQL_SELECT_BY_ID, fieldsInLine, schema, clazz.getCanonicalName());
    }

    public String getSelectSQL(final Class<? extends Entity> clazz) {
        List<Field> fields = ClassUtils.getFields(clazz);
        StringBuilder fieldsInLine = new StringBuilder();
        for (final Field field : fields) {
            fieldsInLine.append(field.getName());
            fieldsInLine.append(", ");
        }
        return String.format(SQL_SELECT, fieldsInLine, schema, clazz.getCanonicalName());
    }

    public String getDeleteSQL(final Class<? extends Entity> clazz) {
        return String.format(SQL_DELETE, schema, clazz.getCanonicalName());
    }

    private void removeLastComma(final StringBuilder stringBuilder) {
        if (stringBuilder.lastIndexOf(",") > 0) {
            stringBuilder.delete(stringBuilder.lastIndexOf(","), stringBuilder.length());
        }
    }
}
