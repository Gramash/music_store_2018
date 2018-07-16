package com.epam.garmash.utils.validator;

import com.epam.garmash.dto.ProductDto;

import java.util.Objects;

public class ProductDtoValidator {

    private static final double LOWER_PRICE_BOUND_DEFAULT = 0.0;
    private static final double UPPER_PRICE_BOUND_DEFAULT = 100000.0;
    private static final String DEFAULT_PRODUCTS_PER_PAGE = "10";


    public void validate(ProductDto dto) {
        validatePrice(dto);
        validatePaginationParameters(dto);
    }

    private void validatePrice(ProductDto dto) {
        double lowerPrice = validatePriceBounds(dto.getLowerPriceBound(), LOWER_PRICE_BOUND_DEFAULT);
        double upperPrice = validatePriceBounds(dto.getUpperPriceBound(), UPPER_PRICE_BOUND_DEFAULT);

        if (lowerPrice < 0 || lowerPrice > upperPrice) {
            lowerPrice = LOWER_PRICE_BOUND_DEFAULT;
        }
        if (upperPrice < 0 || upperPrice <= lowerPrice) {
            upperPrice = UPPER_PRICE_BOUND_DEFAULT;
        }

        dto.setLowerPriceBound(String.valueOf(lowerPrice));
        dto.setUpperPriceBound(String.valueOf(upperPrice));
    }

    private void validatePaginationParameters(ProductDto dto) {
        if (dto.getProductsPerPage() == null || Objects.equals(dto.getProductsPerPage(), "0")) {
            dto.setProductsPerPage(DEFAULT_PRODUCTS_PER_PAGE);
        }
        if (dto.getCurrentPage() == null) {
            dto.setCurrentPage("1");
        }
    }

    private double validatePriceBounds(String priceBound, double defaultPrice) {
        try {
            return Double.parseDouble(priceBound);
        } catch (NullPointerException | NumberFormatException e) {
            return defaultPrice;
        }
    }

}
