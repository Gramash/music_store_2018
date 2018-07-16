package com.epam.garmash.service.product;

import com.epam.garmash.beans.Product;
import com.epam.garmash.dao.DaoFactory;
import com.epam.garmash.dao.ProductDao;
import com.epam.garmash.database.transactions.TransactionManager;
import com.epam.garmash.dto.ProductDto;
import com.epam.garmash.exception.ExceptionMessages;
import com.epam.garmash.exception.ServiceException;
import com.epam.garmash.exception.TransactionException;

import java.util.List;

public class ProductService {

    private TransactionManager transactionManager;
    private ProductDao productDao;

    public ProductService(TransactionManager transactionManager, DaoFactory daoFactory) {
        this.transactionManager = transactionManager;
        this.productDao = daoFactory.createProductDao();
    }

    public List<Product> getProducts(ProductDto productDto) {
        try {
            return (List<Product>) transactionManager.nonTransactional(() -> productDao.getProducts(productDto));
        } catch (TransactionException e) {
            throw new ServiceException(ExceptionMessages.CANT_GET_PRODUCTS, e);
        }
    }

    public int getProductsCount(ProductDto productDto) {
        try {
            return (int) transactionManager.nonTransactional(() -> productDao.getProductCount(productDto));
        } catch (TransactionException e) {
            throw new ServiceException(ExceptionMessages.CANT_GET_PRODUCT_COUNT, e);
        }
    }

}
