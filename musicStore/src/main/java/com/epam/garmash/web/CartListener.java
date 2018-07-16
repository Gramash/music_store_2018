package com.epam.garmash.web;

import com.epam.garmash.dto.ShoppingCart;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class CartListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("cart", new ShoppingCart());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
