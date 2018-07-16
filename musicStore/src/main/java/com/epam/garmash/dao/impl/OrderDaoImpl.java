package com.epam.garmash.dao.impl;

import com.epam.garmash.beans.Product;
import com.epam.garmash.dao.OrderDao;
import com.epam.garmash.database.connections.ConnectionManager;
import com.epam.garmash.dto.OrderDto;
import com.epam.garmash.exception.DaoException;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {

    private static final String INSERT_ORDER = "insert into `order` values (?, DEFAULT, ?, DEFAULT, DEFAULT)";
    private static final String ADD_ORDER_HAS_PRODUCT = "insert into `order_has_product` values (?, ?, ?, ?)";
    public static final String SELECT_LAST_INSERT_ID = "Select LAST_INSERT_ID()";

    private ConnectionManager connectionManager;

    public OrderDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public int addOrder(OrderDto orderDto) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(INSERT_ORDER);
            prstm.setString(1, orderDto.getUserId());
            prstm.setString(3, orderDto.getAddress());
            prstm.executeUpdate();
            return getLastInsertedID();
        } catch (SQLException e) {
            throw new DaoException("can't add order", e);
        } finally {
            connectionManager.close(prstm);
        }
    }

    @Override
    public void addOrderProduct(Product product, int orderId) {
        Connection connection = connectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(ADD_ORDER_HAS_PRODUCT);
            prstm.setInt(1, orderId);
            prstm.setInt(2, product.getId());
            prstm.setInt(3, product.getId());
            prstm.setDouble(4, product.getPrice());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("cant add order_product", e);
        } finally {
            connectionManager.close(prstm);
        }

    }

    private int getLastInsertedID() {
        Connection connection = connectionManager.getConnection();
        Statement stm = null;
        try {
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(SELECT_LAST_INSERT_ID);
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DaoException("cant get last insert ID", e);
        } finally {
            connectionManager.close(stm);
        }
    }
}
