package com.epam.garmash.dao;

import com.epam.garmash.beans.Product;
import com.epam.garmash.dto.ProductDto;

import java.util.List;

/**
 * Interface that provides contract for classes
 * to access database and extract records related to
 * `product` table. Some methods use product dao and
 * sql builder {@link com.epam.garmash.database.SqlBuilder} to
 * create query.
 */
public interface ProductDao {

    /**
     * Returns selected list of products {@link Product} obtained from
     * database. Method obtains product dto instance to create a query
     * based on the content of the former.
     *
     * @param productDto an instance of data transfer object reflecting
     *                   client's preferences in sorting and filtering of the product list.
     * @return list of products sorted and filtered according to product dto content.
     */
    List<Product> getProducts(ProductDto productDto);

    /**
     * Obtains product dto to form an sql query and
     * returns total amount of products that meet the
     * query requirements.
     *
     * @param productDto an instance of data transfer object reflecting
     *                   client's preferences in sorting and filtering of the product list.
     * @return total amount of products meeting the requirements specified in product dao.
     */
    int getProductCount(ProductDto productDto);

}
