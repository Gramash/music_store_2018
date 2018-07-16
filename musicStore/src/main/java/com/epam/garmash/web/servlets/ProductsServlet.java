package com.epam.garmash.web.servlets;

import com.epam.garmash.dto.ProductDto;
import com.epam.garmash.service.product.ProductService;
import com.epam.garmash.service.product.brand.BrandService;
import com.epam.garmash.service.product.type.ProductTypeService;
import com.epam.garmash.utils.validator.ProductDtoValidator;
import com.epam.garmash.web.AppContextConstants;
import com.epam.garmash.web.Paths;
import com.epam.garmash.web.ViewConstants;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ProductsServlet.class);

    private BrandService brandService;
    private ProductTypeService productTypeService;
    private ProductService productService;
    private ProductDtoValidator productDtoValidator;

    @Override
    public void init(ServletConfig config) {
        brandService = (BrandService) config.getServletContext().getAttribute(AppContextConstants.BRAND_SERVICE);
        productTypeService = (ProductTypeService) config.getServletContext().getAttribute(AppContextConstants.PRODUCT_TYPE_SERVICE);
        productService = (ProductService) config.getServletContext().getAttribute(AppContextConstants.PRODUCT_SERVICE);
        productDtoValidator = new ProductDtoValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDto productDto = ProductDto.createProductDto(req);
        productDtoValidator.validate(productDto);

        LOG.debug("Number of Pages " + getNumberOfRequiredPages(productDto));
        String urlPrefix = extractProtocolAndPort(req.getRequestURL().toString());
        LOG.debug("URL " + productDto.createURL(urlPrefix));

        req.setAttribute(ViewConstants.PRODUCT_TYPE_LIST, productTypeService.getAllProductTypes());
        req.setAttribute(ViewConstants.PRODUCTS, productService.getProducts(productDto));
        req.setAttribute(ViewConstants.PRODUCT_BRAND_LIST, brandService.getAllBrands());
        req.setAttribute(ViewConstants.REQUEST_URL, productDto.createURL(urlPrefix));
        req.setAttribute(ViewConstants.NUMBER_OF_PAGES, getNumberOfRequiredPages(productDto));
        req.setAttribute(ViewConstants.CURRENT_PAGE, productDto.getCurrentPage());
        req.getRequestDispatcher(Paths.PRODUCTS_JSP).forward(req, resp);

    }

    private int getNumberOfRequiredPages(ProductDto productDto) {
        int productsPerPage = Integer.valueOf(productDto.getProductsPerPage());
        int result = (productService.getProductsCount(productDto) + productsPerPage - 1) /
                productsPerPage;
        LOG.debug("ProductListSize " + result);
        return result;
    }

    private String extractProtocolAndPort(String requestURL) {
        Pattern pattern = Pattern.compile(".*/");
        Matcher matcher = pattern.matcher(requestURL);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

}
