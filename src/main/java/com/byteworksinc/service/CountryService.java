package com.byteworksinc.service;

import com.byteworksinc.model.Country;
import java.util.List;

/**
 * Created by smitchell on 2/16/14.
 */
public interface CountryService {

    /**
     * Gets instance by id.
     * @param id - The id of the instance.
     * @return The instance matching the id.
     */
    Country findByPrimaryKey(Long id);

    /**
     * Saves a country.
     * @param instance - The country to save.
     */
    void save(Country instance);

    /**
     * Delete a country.
     * @param country - The country to delete.
     */
    void delete(Country country);

    /**
     * Lists sectors.
     * @return - The list of sectors.
     */
    List<Country> listAll();
    
}
