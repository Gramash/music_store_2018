package com.epam.garmash.dao.impl;

import com.epam.garmash.beans.ProductType;
import com.epam.garmash.dao.ProductTypeDao;
import com.epam.garmash.database.connections.ConnectionManager;
import com.epam.garmash.exception.DaoException;
import com.epam.garmash.exception.ExceptionMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDaoImpl implements ProductTypeDao {

    private ConnectionManager connectionManager;

    public ProductTypeDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static final String GET_PRODUCT_TYPES = "SELECT * FROM `product_type`";

    @Override
    public List<ProductType> getTypes() {
        Connection connection = connectionManager.getConnection();
        ResultSet rs = null;
        Statement statement = null;
        List<ProductType> productTypeList;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_PRODUCT_TYPES);
            productTypeList = new ArrayList<>();
            while (rs.next()) {
                productTypeList.add(new ProductType(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessages.CANT_GET_PRODUCT_TYPES, e);
        } finally {
            connectionManager.close(rs);
            connectionManager.close(statement);
        }
        return productTypeList;
    }

}