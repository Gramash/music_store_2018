package com.epam.garmash.dao;

import com.epam.garmash.beans.Product;
import com.epam.garmash.dto.OrderDto;

public interface OrderDao {

    int addOrder(OrderDto orderDto);

    void addOrderProduct(Product product, int orderId);

}
