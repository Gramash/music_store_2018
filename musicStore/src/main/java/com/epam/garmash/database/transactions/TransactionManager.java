package com.epam.garmash.database.transactions;

import com.epam.garmash.database.connections.ConnectionManager;
import com.epam.garmash.exception.ExceptionMessages;
import com.epam.garmash.exception.TransactionException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager<T> {

    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    private ConnectionManager connectionManager;
    private DataSource dataSource;

    public TransactionManager(ConnectionManager connectionManager, DataSource dataSource) {
        this.connectionManager = connectionManager;
        this.dataSource = dataSource;
    }

    /**
     * Template method that prepares connection for transactional operation, performs
     * transaction via functional interface {@link Operation}, and processes connection afterwards.
     * The method obtains connection, places it in connection manager {@link ConnectionManager},
     * disables autocommit for obtained connection, and sets isolation level for transaction.
     * Actual action is passed through parametrized functional interface implementation.
     * Upon successful completion of the action the result is committed.
     * In case of exception the method rolls changes back.
     * In both cases connection is being closed and removed from ConnectionManager in 'finally' block.
     *
     * @param operation    functional interface to implement concrete action within transaction
     * @param isolationLvl integer value representing transaction isolation
     * @return the result of an action that was passed as a parameter in the form of Operation functional interface
     */
    public T transactional(Operation<T> operation, int isolationLvl) {
        Connection connection = null;
        T result;
        try {
            connectionManager.setConnection(dataSource.getConnection());
            LOG.debug("Connection obtained in transaction manager " + connectionManager.getConnection());
            connection = connectionManager.getConnection();
            connection.setTransactionIsolation(isolationLvl);
            connection.setAutoCommit(false);
            result = operation.action();
            connection.commit();
        } catch (Exception e) {
            connectionManager.rollback(connection);
            throw new TransactionException(ExceptionMessages.CANT_PERFORM_TRANSACTION, e);
        } finally {
            connectionManager.close(connection);
            connectionManager.removeConnection();
        }
        return result;
    }

    /**
     * Template method that obtains connection via {@linkplain DataSource},
     * places it in connection manager {@linkplain ConnectionManager},
     * executes operation passed as Operation functional interface,
     * closes connection, and removes it form ConnectionManager afterwards.
     * Handles @{@linkplain SQLException} upon
     * its appearance.
     *
     * @param t functional interface with {@link Operation#action()} method
     * @return the result of an action that was passed as a parameter in the form of Operation<T> functional interface
     */
    public T nonTransactional(Operation<T> t) {
        Connection connection = null;
        T result;
        try {
            connectionManager.setConnection(dataSource.getConnection());
            connection = connectionManager.getConnection();
            result = t.action();
        } catch (Exception e) {
            throw new TransactionException(ExceptionMessages.CANT_PERFORM_NON_TRANSACTIONAL_OPERATION, e);
        } finally {
            connectionManager.close(connection);
            connectionManager.removeConnection();
        }
        return result;
    }


}
