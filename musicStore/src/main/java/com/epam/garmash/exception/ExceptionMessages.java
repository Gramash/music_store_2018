package com.epam.garmash.exception;

public final class ExceptionMessages {

    public static final String CANT_GET_BRANDS = "can't get brands";
    public static final String CANT_GET_PRODUCT_TYPES = "can't get product types";
    public static final String CANT_PERFORM_TRANSACTION = "can't perform transaction";
    public static final String CANT_PERFORM_NON_TRANSACTIONAL_OPERATION = "can't perorm non-transactional operation";

    public ExceptionMessages() {
        throw new AssertionError();
    }

    public static final String CANT_CREATE_USER = "can't create user";
    public static final String CANT_FIND_USER = "can't find user by login";

    public static final String CANT_GET_PRODUCTS = "can't get products";
    public static final String CANT_GET_PRODUCT_COUNT = "can't get products count";
}
