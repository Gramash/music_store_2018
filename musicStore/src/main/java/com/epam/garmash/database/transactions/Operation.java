package com.epam.garmash.database.transactions;

/**
 * Represents an operation to be performed.
 * This is a functional interface
 * whose functional method is {@link #action()}.
 *
 * @param <T> type of the result to be returned by invocation of operation method
 */
public interface Operation<T> {

    /**
     * Performs an action.
     *
     * @return result of the performed action.
     */
    T action();

}

