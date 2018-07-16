package com.epam.garmash.web.servlets;

import com.epam.garmash.beans.User;
import com.epam.garmash.dto.OrderDto;
import com.epam.garmash.dto.ShoppingCart;
import com.epam.garmash.service.OrderService;
import com.epam.garmash.web.AppContextConstants;
import com.epam.garmash.web.Paths;
import com.epam.garmash.web.ViewConstants;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(AppContextConstants.ORDER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user;
        String path;
        if ((user = (User) req.getSession().getAttribute(ViewConstants.SIGNED_IN_USER)) == null) {
            path = Paths.CART + "?modal=open";
        } else {
            orderService.addOrder(createOrderDto(req, user));
            path = "cart?order=success";
            ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("cart");
            req.getSession().removeAttribute("totalPrice");
            shoppingCart.clear();
        }
        resp.sendRedirect(path);
    }

    private OrderDto createOrderDto(HttpServletRequest req, User user) {
        OrderDto orderDto = new OrderDto();
        orderDto.setShoppingCart((ShoppingCart) req.getSession().getAttribute("cart"));
        orderDto.setUserId(user.getEmail());
        orderDto.setAddress(req.getParameter("address"));
        return orderDto;
    }

}
