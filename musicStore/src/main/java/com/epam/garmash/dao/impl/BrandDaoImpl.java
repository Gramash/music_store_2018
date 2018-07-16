package com.epam.garmash.dao.impl;

import com.epam.garmash.beans.Brand;
import com.epam.garmash.dao.BrandDao;
import com.epam.garmash.database.connections.ConnectionManager;
import com.epam.garmash.exception.DaoException;
import com.epam.garmash.exception.ExceptionMessages;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandDaoImpl implements BrandDao {

    private static final String GET_BRANDS = "SELECT * FROM `product_brand`";

    private ConnectionManager connectionManager;

    public BrandDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Brand> getBrands() {
        Connection connection = connectionManager.getConnection();
        ResultSet rs = null;
        Statement statement = null;
        List<Brand> brandList;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_BRANDS);
            brandList = new ArrayList<>();
            while (rs.next()) {
                brandList.add(new Brand(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessages.CANT_GET_BRANDS, e);
        } finally {
            connectionManager.close(rs);
            connectionManager.close(statement);
        }
        return brandList;
    }

}
