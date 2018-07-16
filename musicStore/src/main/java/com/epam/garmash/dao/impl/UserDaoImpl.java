package com.epam.garmash.dao.impl;

import com.epam.garmash.beans.User;
import com.epam.garmash.dao.UserDao;
import com.epam.garmash.database.connections.ConnectionManager;
import com.epam.garmash.exception.DaoException;
import com.epam.garmash.exception.ExceptionMessages;
import com.epam.garmash.web.ViewConstants;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ? ";
    private static final String CREATE_USER = "INSERT INTO users VALUES (?,?,?,?,?)";
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private ConnectionManager connectionManager;

    public UserDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Connection connection = connectionManager.getConnection();
        LOG.debug("#findUserByEmail " + connection);
        Optional<User> optionalUser = Optional.empty();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = connection.prepareStatement(GET_USER_BY_EMAIL);
            prstm.setString(1, email);
            rs = prstm.executeQuery();
            if (rs.next()) {
                optionalUser = Optional.of(new User(rs.getString(ViewConstants.NAME),
                        rs.getString(ViewConstants.LASE_NAME),
                        rs.getString(ViewConstants.EMAIL),
                        rs.getString(ViewConstants.PASSWORD),
                        rs.getString(ViewConstants.AVATAR), false));
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessages.CANT_FIND_USER, e);
        } finally {
            connectionManager.close(rs);
            connectionManager.close(prstm);
        }
        return optionalUser;
    }

    @Override
    public void createUser(User user) {
        Connection connection = connectionManager.getConnection();
        LOG.debug("#createUser " + connection);
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(CREATE_USER);
            prstm.setString(1, user.getEmail());
            prstm.setString(2, user.getName());
            prstm.setString(3, user.getLastName());
            prstm.setString(4, user.getPass());
            prstm.setString(5, user.getAvatar());
            prstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(ExceptionMessages.CANT_CREATE_USER, e);
        } finally {
            connectionManager.close(prstm);
        }
    }

}