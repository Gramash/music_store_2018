package com.epam.garmash.dao;

import com.epam.garmash.beans.Brand;

import java.util.List;

/**
 * Interface that provides contract to access and manipulate
 * data related to brands from the database
 */
public interface BrandDao {
    /**
     * Retrieves a list of brands from a database
     *
     * @return a list of brands and corresponding id's from database
     */
    List<Brand> getBrands();

}
