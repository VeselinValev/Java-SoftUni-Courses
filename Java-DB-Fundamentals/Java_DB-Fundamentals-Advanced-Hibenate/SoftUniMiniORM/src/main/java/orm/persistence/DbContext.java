package orm.persistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbContext<E> {
    boolean persist(E entity) throws IllegalAccessException, SQLException;

    Iterable<E> find(Class<E> table) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    Iterable<E> find(Class<E> table, String where) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    E findFirst(Class<E> table) throws SQLException, InvocationTargetException, InstantiationException, NoSuchMethodException, IllegalAccessException;

    E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException;
}
