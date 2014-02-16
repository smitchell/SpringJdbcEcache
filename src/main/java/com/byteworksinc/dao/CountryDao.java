/**
 * 
 */
package com.byteworksinc.dao;

import com.byteworksinc.model.Country;

/**
 * Created by IntelliJ IDEA.
 * User: stevecmitchell
 * Date: Aug 27, 2010
 * Time: 6:47:21 PM
 *
 */
public interface CountryDao extends GenericDao<Country, Long> {

    /**
	 * Finds sector by COUNTRY_CODE.
	 * @param code - The COUNTRY_CODE to use for a case insensitive search.
	 * @return - The list of countries with a given COUNTRY_CODE.
	 */
    Country findByCode(String code);
	
    /**
	 * Finds IS_COUNTRY by SECTOR_NAME.
	 * @param name - The COUNTRY_NAME to use for a case insensitive search.
	 * @return - The list of countries with a given SECTOR_NAME.
	 */
    Country findByName(String name);

}
