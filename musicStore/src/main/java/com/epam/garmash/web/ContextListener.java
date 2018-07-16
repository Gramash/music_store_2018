package com.epam.garmash.web;

import com.epam.garmash.dao.DaoFactory;
import com.epam.garmash.database.connections.ConnectionManager;
import com.epam.garmash.database.transactions.TransactionManager;
import com.epam.garmash.service.OrderService;
import com.epam.garmash.service.captcha.Captcha;
import com.epam.garmash.service.captcha.CaptchaService;
import com.epam.garmash.service.captcha.handlers.HandlerFactory;
import com.epam.garmash.service.product.ProductService;
import com.epam.garmash.service.product.brand.BrandService;
import com.epam.garmash.service.product.type.ProductTypeService;
import com.epam.garmash.service.user.UserService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {

    @Resource(name = "jdbc/musicshop")
    private DataSource ds;

    private static final Logger LOG = Logger.getLogger(ContextListener.class);
    private static final String CAPTCHA_HANDLER_CONTEXT_PARAM_NAME = "captcha handler";
    private static final String LOG_CAPTCHA_HANDLER = "Application /musicShop started with captcha handler - ";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String captchaInitParamValue = sce.getServletContext().getInitParameter(CAPTCHA_HANDLER_CONTEXT_PARAM_NAME);
        LOG.info(LOG_CAPTCHA_HANDLER + captchaInitParamValue);

        ConnectionManager connectionManager = new ConnectionManager();
        TransactionManager transactionManager = new TransactionManager(connectionManager, ds);
        DaoFactory daoFactory = new DaoFactory(connectionManager);
        UserService userService = new UserService(daoFactory,
                transactionManager);
        Map<String, Captcha> captchaMap = Collections.synchronizedMap(new HashMap<>());

        HandlerFactory handlerFactory = new HandlerFactory(captchaMap);
        CaptchaService captchaService = new CaptchaService(handlerFactory.createHandler(captchaInitParamValue));
        BrandService brandService = new BrandService(daoFactory, transactionManager);
        ProductTypeService productTypeService = new ProductTypeService(transactionManager, daoFactory);
        ProductService productService = new ProductService(transactionManager, daoFactory);
        OrderService orderService = new OrderService(transactionManager, daoFactory);

        sce.getServletContext().setAttribute(AppContextConstants.USER_SERVICE, userService);
        sce.getServletContext().setAttribute(AppContextConstants.BRAND_SERVICE, brandService);
        sce.getServletContext().setAttribute(AppContextConstants.CAPTCHA_SERVICE, captchaService);
        sce.getServletContext().setAttribute(AppContextConstants.PRODUCT_TYPE_SERVICE, productTypeService);
        sce.getServletContext().setAttribute(AppContextConstants.PRODUCT_SERVICE, productService);
        sce.getServletContext().setAttribute(AppContextConstants.ORDER_SERVICE, orderService);

        captchaService.startCaptchaCleaner(captchaMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
