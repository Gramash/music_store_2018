package com.epam.garmash.service;

import com.epam.garmash.dao.DaoFactory;
import com.epam.garmash.dao.OrderDao;
import com.epam.garmash.database.transactions.TransactionManager;
import com.epam.garmash.dto.OrderDto;

public class OrderService {

    private static final int TRANSACTION_READ_COMMITTED = 2;

    private TransactionManager transactionManager;
    private OrderDao orderDao;

    public OrderService(TransactionManager transactionManager, DaoFactory daoFactory) {
        this.transactionManager = transactionManager;
        this.orderDao = daoFactory.createOrderDao();
    }

    public void addOrder(OrderDto orderDto) {
        transactionManager.transactional(() -> {
            orderDto.setOrderId(orderDao.addOrder(orderDto));
            orderDto.getShoppingCart()
                    .getCart()
                    .forEach((product, integer) ->
                            orderDao.addOrderProduct(product, orderDto.getOrderId()));
            return null;
        }, TRANSACTION_READ_COMMITTED);
    }

}
