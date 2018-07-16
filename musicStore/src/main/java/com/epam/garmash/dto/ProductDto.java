package com.epam.garmash.dto;

import com.epam.garmash.web.ViewConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDto {

    private static final String SERVLET = "products?";
    private static final String BRAND = "brand=";
    private static final String TYPE = "type=";
    private static final String LOWER_PRICE = "lowerPrice=";
    private static final String UPPER_PRICE = "upperPrice=";
    private static final String PRODUCTS_COUNT = "productsCount=";
    private static final String PRODUCT_NAME = "productName=";
    private static final String SORT_BY = "sortBy=";
    private static final String AMPERSAND = "&";

    private String name;
    private List<String> brands;
    private List<String> types;
    private String lowerPriceBound;
    private String upperPriceBound;
    private String productsPerPage;
    private String currentPage;
    private String sortBy;
    private String sortOrder;

    public static ProductDto createProductDto(HttpServletRequest req) {
        return new ProductDto(req.getParameter(ViewConstants.PRODUCT_NAME),
                getParametersByName(req, ViewConstants.BRAND),
                getParametersByName(req, ViewConstants.TYPE),
                req.getParameter(ViewConstants.LOWER_PRICE),
                req.getParameter(ViewConstants.UPPER_PRICE),
                req.getParameter(ViewConstants.PRODUCTS_COUNT),
                req.getParameter(ViewConstants.CURRENT_PAGE),
                req.getParameter(ViewConstants.SORT_BY));
    }

    public ProductDto(String name, List<String> brands, List<String> types, String lowerPriceBound,
                      String upperPriceBound, String productsPerPage, String currentPage, String sortBy) {
        this.name = name;
        this.brands = new ArrayList<>(brands);
        this.types = new ArrayList<>(types);
        this.lowerPriceBound = lowerPriceBound;
        this.upperPriceBound = upperPriceBound;
        this.productsPerPage = productsPerPage;
        this.currentPage = currentPage;
        if (sortBy != null) {
            this.sortBy = sortBy.split(" ")[0];
            this.sortOrder = sortBy.split(" ")[1];
        }
    }

    public String createURL(String urlPrefix) {
        StringBuilder url = new StringBuilder();
        url.append(urlPrefix)
                .append(SERVLET);
        for (String brand : brands) {
            url.append(BRAND).append(brand).append(AMPERSAND);
        }
        for (String type : types) {
            url.append(TYPE).append(type.replaceAll(" ", "+")).append(AMPERSAND);
        }
        url.append(LOWER_PRICE).append(lowerPriceBound).append(AMPERSAND)
                .append(UPPER_PRICE).append(upperPriceBound).append(AMPERSAND)
                .append(PRODUCTS_COUNT).append(productsPerPage).append(AMPERSAND);
        if (name != null) {
            url.append(PRODUCT_NAME).append(name).append(AMPERSAND);
        }
        if (sortBy != null && sortOrder != null) {
            url.append(SORT_BY).append(sortBy).append("+").append(sortOrder).append(AMPERSAND);
        }
        return url.toString();
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getProductsPerPage() {
        return productsPerPage;
    }

    public void setProductsPerPage(String productsPerPage) {
        this.productsPerPage = productsPerPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBrands() {
        return new ArrayList<>(brands);
    }

    public List<String> getTypes() {
        return new ArrayList<>(types);
    }

    public String getLowerPriceBound() {
        return lowerPriceBound;
    }

    public void setLowerPriceBound(String lowerPriceBound) {
        this.lowerPriceBound = lowerPriceBound;
    }

    public String getUpperPriceBound() {
        return upperPriceBound;
    }

    public void setUpperPriceBound(String upperPriceBound) {
        this.upperPriceBound = upperPriceBound;
    }

    private static List<String> getParametersByName(HttpServletRequest req, String name) {
        List<String> result = new ArrayList<>();
        if (req.getParameterValues(name) != null) {
            result.addAll(Arrays.asList(req.getParameterValues(name)));
        }
        return result;
    }
}
