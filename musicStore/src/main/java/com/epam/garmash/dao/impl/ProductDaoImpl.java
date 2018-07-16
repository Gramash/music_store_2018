package com.epam.garmash.dao.impl;

import com.epam.garmash.beans.Product;
import com.epam.garmash.dao.ProductDao;
import com.epam.garmash.database.ProductsQueryCreator;
import com.epam.garmash.database.connections.ConnectionManager;
import com.epam.garmash.dto.ProductDto;
import com.epam.garmash.exception.DaoException;
import com.epam.garmash.exception.ExceptionMessages;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger LOG = Logger.getLogger(ProductDaoImpl.class);
    private static final String ID_FIELD = "id";
    private static final String PRODUCT_NAME_FIELD = "name";
    private static final String PRODUCT_TYPE_FIELD = "type";
    private static final String PRODUCT_BRAND_FIELD = "brand";
    private static final String PRODUCT_PRICE_FIELD = "price";
    private static final String PRODUCT_DESCRIPTION_FIELD = "description";
    private static final String PRODUCT_IMAGE_FIELD = "image_src";

    private ConnectionManager connectionManager;
    private ProductsQueryCreator productsQueryCreator = new ProductsQueryCreator();

    public ProductDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Product> getProducts(ProductDto productDto) {
        Connection connection = connectionManager.getConnection();
        LOG.debug("Connection " + connection);
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Product> result;
        try {
            String query = productsQueryCreator.createProductQuery(productDto);
            preparedStatement = connection.prepareStatement(query);
            LOG.debug("Query " + query);
            int k = 1;
            LOG.debug("Token list= " + productsQueryCreator.getTokens());
            for (String str : productsQueryCreator.getTokens()) {
                LOG.debug("Token " + str);
                preparedStatement.setObject(k++, str);
            }
            LOG.debug("Prepared statement " + preparedStatement.toString());
            rs = preparedStatement.executeQuery();
            LOG.debug("result set = " + rs.toString());
            result = new ArrayList<>();
            while (rs.next()) {
                result.add(createProduct(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessages.CANT_GET_PRODUCTS, e);
        } finally {
            connectionManager.close(rs);
            connectionManager.close(preparedStatement);
        }
        return result;
    }

    @Override
    public int getProductCount(ProductDto productDto) {
        Connection connection = connectionManager.getConnection();
        LOG.debug("Connection " + connection);
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String query = productsQueryCreator.createProductCountQuery(productDto);
            preparedStatement = connection.prepareStatement(query);
            LOG.debug("Query " + query);
            int k = 1;
            LOG.debug("Token list= " + productsQueryCreator.getTokens());
            for (String str : productsQueryCreator.getTokens()) {
                LOG.debug("Token " + str);
                preparedStatement.setObject(k++, str);
            }
            LOG.debug("Prepared statement " + preparedStatement.toString());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(ExceptionMessages.CANT_GET_PRODUCT_COUNT, e);
        } finally {
            connectionManager.close(rs);
            connectionManager.close(preparedStatement);
        }
        return result;
    }

    private Product createProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getInt(ID_FIELD),
                rs.getString(PRODUCT_NAME_FIELD),
                rs.getString(PRODUCT_BRAND_FIELD),
                rs.getString(PRODUCT_TYPE_FIELD),
                rs.getDouble(PRODUCT_PRICE_FIELD),
                rs.getString(PRODUCT_DESCRIPTION_FIELD),
                rs.getString(PRODUCT_IMAGE_FIELD));
    }

}
