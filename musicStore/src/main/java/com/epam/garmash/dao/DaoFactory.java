package com.epam.garmash.dao;

import com.epam.garmash.dao.impl.BrandDaoImpl;
import com.epam.garmash.dao.impl.OrderDaoImpl;
import com.epam.garmash.dao.impl.ProductDaoImpl;
import com.epam.garmash.dao.impl.ProductTypeDaoImpl;
import com.epam.garmash.dao.impl.UserDaoImpl;
import com.epam.garmash.database.connections.ConnectionManager;

public class DaoFactory {

    private ConnectionManager connectionManager;

    public DaoFactory(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connectionManager);
    }

    public ProductTypeDao createProductTypeDao() {
        return new ProductTypeDaoImpl(connectionManager);
    }

    public BrandDao createBrandDao() {
        return new BrandDaoImpl(connectionManager);
    }

    public ProductDao createProductDao() {
        return new ProductDaoImpl(connectionManager);
    }

    public OrderDao createOrderDao() {
        return new OrderDaoImpl(connectionManager);
    }


}
