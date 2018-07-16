package com.epam.garmash.database;

import com.epam.garmash.dto.ProductDto;

import java.util.List;

public class ProductsQueryCreator {

    private static final String TABLE_NAME = "all_products";
    private static final String BRAND = "brand";
    private static final String TYPE = "type";
    private static final String PRICE = "price";
    private static final String NAME = "name";

    private SqlBuilder sqlBuilder = new SqlBuilder();
    private List<String> tokens = sqlBuilder.getParameters();

    public List<String> getTokens() {
        return tokens;
    }

    public String createProductQuery(ProductDto productDto) {
        sqlBuilder.selectFrom(TABLE_NAME);
        if (!productDto.getBrands().isEmpty()) {
            sqlBuilder.whereOrAnd(BRAND)
                    .in(productDto.getBrands());
        }
        if (!productDto.getTypes().isEmpty()) {
            sqlBuilder.whereOrAnd(TYPE)
                    .in(productDto.getTypes());
        }
        sqlBuilder.whereOrAnd(PRICE).moreOrEquals(productDto.getLowerPriceBound())
                .whereOrAnd(PRICE).lessOrEquals(productDto.getUpperPriceBound());
        if (productDto.getName() != null) {
            sqlBuilder.whereOrAnd(NAME).like(productDto.getName());
        }
        if (productDto.getSortBy() != null) {
            sqlBuilder.orderBy(productDto.getSortBy(), productDto.getSortOrder());
        }
        sqlBuilder.limitFromTo(getLimitFrom(productDto), Integer.valueOf(productDto.getProductsPerPage()));

        return sqlBuilder.build();
    }

    public String createProductCountQuery(ProductDto productDto) {
        sqlBuilder.selectCountFrom(TABLE_NAME);
        if (!productDto.getBrands().isEmpty()) {
            sqlBuilder.whereOrAnd(BRAND)
                    .in(productDto.getBrands());
        }
        if (!productDto.getTypes().isEmpty()) {
            sqlBuilder.whereOrAnd(TYPE)
                    .in(productDto.getTypes());
        }
        sqlBuilder.whereOrAnd(PRICE).moreOrEquals(productDto.getLowerPriceBound())
                .whereOrAnd(PRICE).lessOrEquals(productDto.getUpperPriceBound());
        if (productDto.getName() != null) {
            sqlBuilder.whereOrAnd(NAME).like(productDto.getName());
        }
        return sqlBuilder.build();
    }

    private int getLimitFrom(ProductDto dto) {
        return (Integer.valueOf(dto.getCurrentPage()) - 1) * Integer.valueOf(dto.getProductsPerPage());
    }

}
