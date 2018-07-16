package com.epam.garmash.web.servlets;

import com.epam.garmash.beans.Product;
import com.epam.garmash.dto.ShoppingCart;
import com.epam.garmash.exception.servlets.CartException;
import com.epam.garmash.utils.parser.ProductParser;
import com.epam.garmash.utils.parser.RequestParser;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(CartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart cart = getCart(req);
        LOG.debug("You are in cart servlet. ProdID= " + req.getParameter("productId") + " price= " +
                req.getParameter("price"));

        LOG.debug("product to string " + req.getParameter("product"));
        //get values of fields for product
        Map<String, String> productFieldValue = ProductParser.parseFieldsValues(req.getParameter("product"));
        LOG.debug("Product fields and Values " + productFieldValue);
        //create product bean
        Product productToAdd = createProductBean(productFieldValue);
        //put product to catMap
        cart.add(productToAdd);
        req.getSession().setAttribute("totalPrice", cart.getTotalPrice());
        LOG.debug(cart.getCart());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart cart = getCart(req);
        LOG.debug("cart " + cart.getCart());
        req.setAttribute("cart", cart.getCart());
        req.getRequestDispatcher("/WEB-INF/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ShoppingCart cart = getCart(req);
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String request = br.readLine();
        LOG.debug("request " + request);
        Map<String, String> reqParameters = RequestParser.getQueryParameters(request);
        LOG.debug("productID " + reqParameters.get("productId") + " product count " + reqParameters.get("count"));

        String id = reqParameters.get("productId");
        String count = reqParameters.get("count");
        int idInt;
        int countInt;

        try {
            idInt = Integer.valueOf(id);
            countInt = Integer.valueOf(count);
        } catch (NumberFormatException | NullPointerException e) {
            throw new CartException("Please specify correct parameters for the product");
        }
        req.getSession().setAttribute("totalPrice", cart.getTotalPrice());
        cart.patchById(idInt, countInt);
        LOG.debug(cart.getCart());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ShoppingCart cart = getCart(req);
        int id;
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String request = br.readLine();
        Map<String, String> reqParameters = RequestParser.getQueryParameters(request);

        LOG.debug("#doDelete product ID: " + reqParameters.get("productId"));
        try {
            id = Integer.valueOf(reqParameters.get(("productId")));
        } catch (NullPointerException | NumberFormatException e) {
            throw new CartException("Please input correct product id");
        }
        if (cart.findById(id).isPresent()) {
            cart.remove(cart.findById(id).get());
            req.getSession().setAttribute("totalPrice", cart.getTotalPrice());
        }
    }

    private Product createProductBean(Map<String, String> fieldValueMap) {
        return new Product(Integer.valueOf(fieldValueMap.get("id")),
                fieldValueMap.get("name"),
                fieldValueMap.get("brand"),
                fieldValueMap.get("type"),
                Double.valueOf(fieldValueMap.get("price")),
                fieldValueMap.get("description"),
                fieldValueMap.get("image"));
    }

    private ShoppingCart getCart(HttpServletRequest req) {
        return (ShoppingCart) req.getSession().getAttribute("cart");
    }

}
