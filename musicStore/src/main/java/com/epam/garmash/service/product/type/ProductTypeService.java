package com.epam.garmash.service.product.type;

import com.epam.garmash.beans.ProductType;
import com.epam.garmash.dao.DaoFactory;
import com.epam.garmash.dao.ProductTypeDao;
import com.epam.garmash.database.transactions.TransactionManager;
import com.epam.garmash.exception.ExceptionMessages;
import com.epam.garmash.exception.ServiceException;
import com.epam.garmash.exception.TransactionException;

import java.util.List;

public class ProductTypeService {

    private TransactionManager<List<ProductType>> transactionManager;
    private ProductTypeDao productTypeDao;

    public ProductTypeService(TransactionManager<List<ProductType>> transactionManager, DaoFactory daoFactory) {
        this.transactionManager = transactionManager;
        this.productTypeDao = daoFactory.createProductTypeDao();
    }

    public List<ProductType> getAllProductTypes() {
        try {
            return transactionManager.nonTransactional(() -> productTypeDao.getTypes());
        } catch (TransactionException e) {
            throw new ServiceException(ExceptionMessages.CANT_GET_PRODUCT_TYPES, e);
        }
    }

}
