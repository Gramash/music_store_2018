package com.epam.garmash.service.user;

import com.epam.garmash.beans.User;
import com.epam.garmash.dao.DaoFactory;
import com.epam.garmash.dao.UserDao;
import com.epam.garmash.database.transactions.TransactionManager;
import com.epam.garmash.dto.UserDto;
import com.epam.garmash.exception.DaoException;
import com.epam.garmash.exception.ServiceException;

import java.util.Optional;

public class UserService {

    private UserDao userDao;
    private TransactionManager transactionManager;
    private static final int TRANSACTION_NONE = 0;
    private static final int TRANSACTION_READ_COMMITTED = 2;
    private static final int TRANSACTION_REPEATABLE_READ = 4;
    private static final int TRANSACTION_SERIALIZABLE = 8;

    public UserService(DaoFactory daoFactory, TransactionManager transactionManager) {
        this.userDao = daoFactory.createUserDao();
        this.transactionManager = transactionManager;
    }

    @SuppressWarnings("unchecked")
    public Optional<User> getUserByEmail(UserDto userDto) {
        return (Optional<User>) transactionManager.nonTransactional(() -> {
            try {
                return userDao.findUserByEmail(userDto.getEmail());
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage(), e);
            }

        });
    }

    public boolean addIfUnique(User user) {
        return (boolean) transactionManager.transactional(() -> {
            try {
                if (!userDao.findUserByEmail(user.getEmail()).isPresent()) {
                    userDao.createUser(user);
                    return true;
                } else {
                    return false;
                }
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }, TRANSACTION_READ_COMMITTED);
    }

}