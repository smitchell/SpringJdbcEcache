package com.byteworksinc.test;

import com.byteworksinc.dao.CountryDao;
import com.byteworksinc.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by smitchell on 2/16/14.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-h2-test.xml" })
public class EcacheH2TimingTest extends AbstractEcacheTimingTest {

    @Resource
    private CountryDao nonCacheCountryDao;

    @PostConstruct
    public void postConstruct() {
        assertNotNull(getCountryService());
        assertNotNull(nonCacheCountryDao);
        initMap();
    }

    @Test
    public void testFetchFromMap() {
        final long startTime = System.currentTimeMillis();
        Country country;
        assertEquals(getKeysSize(), getRandomIds().size());
        for (final Long id : getRandomIds()) {
            country = getCountryMap().get(id);
            assertNotNull(country);
        }
        final long endTime = System.currentTimeMillis();
        reportTime("Fetch from HashMap", startTime, endTime);
    }

    @Test
    public void testFetchFromDao() {
        final long startTime = System.currentTimeMillis();
        Country country;
        assertEquals(getKeysSize(), getRandomIds().size());
        for (final Long id : getRandomIds()) {
            country = getCountryService().findByPrimaryKey(id);
            assertNotNull(country);
        }
        final long endTime = System.currentTimeMillis();
        reportTime("Fetch from Ecache", startTime, endTime);
    }

    @Test
    public void testFetchFromNonCachedDao() {
        final long startTime = System.currentTimeMillis();
        Country country;
        assertEquals(getKeysSize(), getRandomIds().size());
        for (final Long id : getRandomIds()) {
            country = nonCacheCountryDao.findByPrimaryKey(id);
            assertNotNull(country);
        }
        final long endTime = System.currentTimeMillis();
        reportTime("Fetch without cache", startTime, endTime);
    }

}
