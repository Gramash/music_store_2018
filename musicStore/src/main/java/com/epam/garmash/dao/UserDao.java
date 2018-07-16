package com.epam.garmash.dao;

import com.epam.garmash.beans.User;

import java.util.Optional;

/**
 * Provides contract to access, represent, and manipulate data
 * related to user taken from a storage
 */
public interface UserDao {

    /**
     * Returns an instance of optional {@link Optional} containing
     * the representation of a user record taken from the storage
     * by the specified email in the form of user entity {@link User}
     * or empty optional instance if no users in the database
     * correspond to desired email.
     *
     * @param email desired email by which user is searched
     * @return an instance of optional class containing user object
     * or an empty optional if no users were found
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Creates a record of a user in a storage
     * according to a specified parameter's values
     *
     * @param user a bean object containing necessary information to create a record
     *             in a storage;
     */
    void createUser(User user);

}