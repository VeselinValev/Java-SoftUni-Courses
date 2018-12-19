package orm.persistence;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import orm.strategies.schema_init_strategies.SchemaInitializationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class EntityManager<E> implements DbContext<E> {
    private Connection connection;
    private String dataSource;
    private SchemaInitializationStrategy strategy;

    EntityManager(Connection connection,
                  String dataSource,
                  SchemaInitializationStrategy strategy)
            throws SQLException {
        this.connection = connection;
        this.dataSource = dataSource;
        this.strategy = strategy;
        this.strategy.execute();
    }

    public void doDelete(Class<E> table, String where) throws Exception {
        String tableName = table.getAnnotation(Entity.class).name();
        if(!this.ifTableExists(tableName)){
            throw new Exception("Table doesn't exist.");
        }
        String query = "DELETE FROM `" + tableName + "` WHERE " + where;
        this.connection.prepareStatement(query).execute();
    }


    @Override
    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        if (value == null || (int)value <= 0){
            return this.doInsert(entity, primary);
        }

        return this.doUpdate(entity, primary);
    }

    @Override
    public Iterable<E> find(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String tableName = this.getTableName(table);
        StringBuilder query = new StringBuilder().append("SELECT * FROM ").append(tableName);

        return fillCollection(query.toString(), table);
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String tableName = this.getTableName(table);
        StringBuilder query = new StringBuilder().append("SELECT * FROM ").append(tableName).append(" WHERE ") .append(where);

        return fillCollection(query.toString(), table);
    }

    @Override
    public E findFirst(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        String tableName = this.getTableName(table);
        StringBuilder query = new StringBuilder().append("SELECT * FROM ").append(tableName).append(" LIMIT 1;");

        return this.findEntity(query.toString(), table);
    }

    @Override
    public E findFirst(Class<E> table, String where) throws InvocationTargetException, SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException {
        String tableName = this.getTableName(table);
        StringBuilder query = new StringBuilder().append("SELECT * FROM ").append(tableName)
                .append(" WHERE ") .append(where).append(" LIMIT 1;");

        return this.findEntity(query.toString(), table);
    }

    private E findEntity(String query, Class<E> table) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        Constructor<E> constructor = table.getDeclaredConstructor();
        constructor.setAccessible(true);
        E entity = constructor.newInstance();

        if (rs.first()){
            this.fillEntity(rs, entity);
        }

        return entity;
    }

    private Iterable<E> fillCollection(String query, Class<E> table) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        Constructor<E> constructor = table.getDeclaredConstructor();
        constructor.setAccessible(true);

        List<E> entities = new ArrayList<>();
        while (rs.next()){
            E entity = constructor.newInstance();
            this.fillEntity(rs, entity);
            entities.add(entity);
        }
        return entities;
    }

    private void fillEntity(ResultSet rs, Object instance)
            throws SQLException, IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field: fields){
            field.setAccessible(true);
            String fieldName = field.getAnnotation(Column.class).name();
            if (field.getType() == int.class || field.getType() == Integer.class) {
                field.set(instance, rs.getInt(fieldName));
            } else if (field.getType() == long.class || field.getType() == Long.class) {
                field.set(instance, rs.getLong(fieldName));
            } else if (field.getType() == double.class || field.getType() == Double.class) {
                field.set(instance, rs.getDouble(fieldName));
            } else if (field.getType() == String.class) {
                field.set(instance, rs.getString(fieldName));
            } else if (field.getType() == Date.class) {
                field.set(instance, rs.getDate(fieldName));
            }
        }
    }

    private Field getId(Class entity){
        return Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst().orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }

    private boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        if (!ifTableExists(tableName)){
            this.doCreate(entity.getClass());
        }else{
            this.doAlter(entity.getClass());
        }

        Field[] columns = entity.getClass().getDeclaredFields();
        Map<String, String> columnsAndValues = extractColumnNamesAndValues(entity, columns);

        StringBuilder query = new StringBuilder().append("INSERT INTO `").append(tableName).append("`(")
                .append(columnsAndValues.keySet().toString().replaceAll("[\\[\\]]", "")).append(")")
                .append(" VALUES(").append(columnsAndValues.values().toString().replaceAll("[\\[\\]]", "")).append(");");

        return connection.prepareStatement(query.toString()).execute();
    }

    private boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        Field[] columns = entity.getClass().getDeclaredFields();
        Map<String, String> columnsAndValues = extractColumnNamesAndValues(entity, columns);

        StringBuilder query = new StringBuilder().append("UPDATE `").append(tableName).append("` SET ");

        for (Map.Entry<String, String> keyValuePair: columnsAndValues.entrySet()){
            query.append(keyValuePair.getKey()).append(" = ").append(keyValuePair.getValue()).append(", ");
        }

        query.delete(query.length() - 2, query.length()).append(" WHERE ").append("id = ").append(primary.get(entity)).append(";");

        return connection.prepareStatement(query.toString()).execute();
    }

    private <E> void doAlter(Class<E> entity) throws SQLException {
        String tableName = this.getTableName(entity);
        StringBuilder addColumnQuery = new StringBuilder().append("ALTER TABLE `").append(tableName).append("` ");
        Field[] entityFields = entity.getDeclaredFields();
        for (Field currentField: entityFields){
            currentField.setAccessible(true);
            String currentColumnName = currentField.getAnnotation(Column.class).name();
            String getColumnNamesFromDatabaseQuery = "SHOW COLUMNS FROM `" + tableName + "` LIKE '" + currentColumnName + "' ";
            ResultSet rs = connection.prepareStatement(getColumnNamesFromDatabaseQuery).executeQuery();
            if (!rs.first()){
                addColumnQuery.append(" ADD COLUMN ").append(currentColumnName).append(" ")
                        .append(getDbType(currentField.getType().getSimpleName())).append(",");
            }
        }
        this.connection.prepareStatement(addColumnQuery.substring(0, addColumnQuery.length() - 1)).execute();
    }

    private  <E> void doCreate(Class<E> entity) throws SQLException {
        String tableName = this.getTableName(entity);
        StringBuilder createTableQuery = new StringBuilder().append("CREATE TABLE `").append(tableName).append("` ").append("(");
        Field[] entityFields = entity.getDeclaredFields();
        for (Field currentField: entityFields){
            currentField.setAccessible(true);
            String dataType = this.getDbType(currentField.getType().getSimpleName());
            String columnName = "`" + currentField.getAnnotation(Column.class).name() + "`";
            createTableQuery.append(columnName).append(" ").append(dataType);
            if (currentField.isAnnotationPresent(Id.class)){
                createTableQuery.append(" UNSIGNED PRIMARY KEY AUTO_INCREMENT");
            }
            createTableQuery.append(",");
        }
        createTableQuery.deleteCharAt(createTableQuery.length() - 1).append(");");


        this.connection.prepareStatement(createTableQuery.toString()).execute();
    }

    private String getTableName(Class entity){
        String tableName = "";
        if (entity.isAnnotationPresent(Entity.class)){
            Entity annotation = (Entity) entity.getAnnotation(Entity.class);
            tableName = annotation.name();
        }
        if (tableName.equals("")){
            tableName = entity.getSimpleName();
        }
        return tableName;
    }

    private Map<String, String> extractColumnNamesAndValues(E entity, Field[] columns) throws IllegalAccessException {
        Map<String, String> columnsAndValues = new LinkedHashMap<>();
        for (Field column : columns) {
            if (column.isAnnotationPresent(Column.class) && !column.isAnnotationPresent(Id.class)) {
                column.setAccessible(true);
                Column annotation = column.getAnnotation(Column.class);
                String columnName = "`" + annotation.name() + "`";
                String value = "";
                if (column.get(entity) instanceof Date) {
                    value = "'" + new SimpleDateFormat("yyyy-MM-dd").format(column.get(entity)) + "'";
                } else {
                    value = "'" + column.get(entity) + "'";
                }
                columnsAndValues.put(columnName, value);
            }
        }
        return columnsAndValues;
    }

    private boolean ifTableExists(String tableName) throws SQLException {
        String ifTableExistsQuery = "SHOW TABLES LIKE '" + tableName + "'";
        PreparedStatement stmt = connection.prepareStatement(ifTableExistsQuery);
        ResultSet rs = stmt.executeQuery();
        if (rs.first()){
            return true;
        }
        return false;
    }

    private String getDbType(String dataType){
        String result = "";
        switch (dataType) {
            case "int":
            case "Integer":
                result = "INT";
                break;
            case "double":
            case "Double":
                result = "DOUBLE(10,2)";
                break;
            case "long":
            case "Long":
                result = "BIGINT";
                break;
            case "String":
                result = "VARCHAR(50)";
                break;
            case "Date":
                result = "DATETIME";
                break;
        }

        return result;
    }
}