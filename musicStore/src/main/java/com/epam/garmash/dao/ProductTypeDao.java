package com.epam.garmash.dao;

import com.epam.garmash.beans.ProductType;

import java.util.List;

/**
 * The interface provides contract to access data in
 * `types` table in a database
 */
public interface ProductTypeDao {

    /**
     * Returns a list of all product types {@link ProductType} from the database
     *
     * @return list of product type objects
     */
    List<ProductType> getTypes();

}
