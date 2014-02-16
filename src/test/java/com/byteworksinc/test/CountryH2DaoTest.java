package com.byteworksinc.test;

import com.byteworksinc.dao.CountryDao;
import com.byteworksinc.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: stevecmitchell
 * Date: Aug 27, 2010
 * Time: 7:11:29 PM
 *
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-h2-test.xml" })
public class CountryH2DaoTest implements DaoTest {
    public static final String TEST_CODE = "XX";
    public static final String TEST_NAME = "TEST";
	@Resource
    private CountryDao countryDao;


    /**
	 * Gets instance by id.
	 */
    @Test
    @Rollback(true)
    @Override
	public void testFindByPrimaryKey() {
        final Country country = generateCountry();
        countryDao.save(country);
        final Country match = countryDao.findByPrimaryKey(country.getId());
        assertNotNull(match);
        assertEquals(country.getId(), match.getId());
        assertEquals(country.getCountryCode(), match.getCountryCode());
        assertEquals(country.getName(), match.getName());
     }

    @Test
    @Rollback(true)
    @Override
	public void testInsert() {
        final Country country = generateCountry();
        countryDao.save(country);
        assertNotNull(country.getId());
        assertNotNull(countryDao.findByPrimaryKey(country.getId()));
     }

    @Test
    @Rollback(true)
    @Override
    public void testUpdate() {
        final Country country = generateCountry();
        countryDao.save(country);
        country.setCountryCode("ZZ");
        country.setName("TEST name");
        countryDao.save(country);
        final Country match = countryDao.findByPrimaryKey(country.getId());
        assertNotNull(match);
        assertEquals(country.getId(), match.getId());
        assertEquals(country.getCountryCode(), match.getCountryCode());
        assertEquals(country.getName(), match.getName());
    }


	/**
	 * Delete a country.
	 */
    @Test
    @Rollback(true)
    @Override
	public void testDelete() {
        final Country country = generateCountry();
        countryDao.save(country);
        assertNotNull(countryDao.findByPrimaryKey(country.getId()));
        countryDao.delete(country);
        assertNull(countryDao.findByPrimaryKey(country.getId()));
     }

	/**
	 * Lists countries.
	 */
    @Test
    @Rollback(true)
	public void testListAll() {
        Country country = generateCountry();
        countryDao.save(country);
        final List<Country> results = countryDao.listAll();
        assertNotNull(results);
        assertTrue(!results.isEmpty());
     }

    /**
	 * Finds country by COUNTRY_CODE.
	 */
    @Test
    @Rollback(true)
	public void testFindByCode() {
        Country country = generateCountry();
        countryDao.save(country);
        assertNotNull(countryDao.findByCode(TEST_CODE));
     }

    private Country generateCountry() {
        final Country country = new Country();
        country.setName(TEST_NAME);
        country.setCountryCode(TEST_CODE);
        return country;
    }

}
