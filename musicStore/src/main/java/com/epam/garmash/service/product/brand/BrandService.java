package com.epam.garmash.service.product.brand;

import com.epam.garmash.beans.Brand;
import com.epam.garmash.dao.BrandDao;
import com.epam.garmash.dao.DaoFactory;
import com.epam.garmash.database.transactions.TransactionManager;
import com.epam.garmash.exception.ExceptionMessages;
import com.epam.garmash.exception.ServiceException;
import com.epam.garmash.exception.TransactionException;

import java.util.List;

public class BrandService {

    private BrandDao brandDao;
    private TransactionManager<List<Brand>> transactionManager;

    public BrandService(DaoFactory daoFactory, TransactionManager<List<Brand>> transactionManager) {
        this.brandDao = daoFactory.createBrandDao();
        this.transactionManager = transactionManager;
    }

    public List<Brand> getAllBrands() {
        try {
            return transactionManager.nonTransactional(() -> brandDao.getBrands());
        } catch (TransactionException e) {
            throw new ServiceException(ExceptionMessages.CANT_GET_BRANDS, e);
        }
    }

}
