package com.epam.garmash.database.connections;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private ThreadLocal<Connection> connectionsMap = new ThreadLocal<>();

    /**
     * Method is a wrapper of a ThreadLocal {@link java.lang.ThreadLocal#set(Object) set}
     * method that adds a connection to the map
     *
     * @param connection
     */
    public void setConnection(Connection connection) {
        connectionsMap.set(connection);
    }

    /**
     * Returns the local-thread connection that was previously set
     * in the map @see #connectionsMap.
     * If no connections had been set before this method invoked for the first time
     * returns the result of default implementation of {@link ThreadLocal#initialValue() initialValue}.
     *
     * @return local-thread connection instance or null if none are present in the map
     */
    public Connection getConnection() {
        return connectionsMap.get();
    }

    public void removeConnection() {
        connectionsMap.remove();
    }

    /**
     * Tries to perform a rollback on a connection instance.
     *
     * @param connection
     */
    public void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method is intended to close resources of classes that implement {@linkplain AutoCloseable}
     * interface (connections, result sets, etc.),
     * and to process exceptions if any.
     *
     * @param autoCloseable
     */
    public void close(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
