package com.byteworksinc.test;

import com.byteworksinc.dao.CountryDao;

import com.byteworksinc.model.Country;
import com.byteworksinc.service.CountryService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by smitchell on 2/16/14.
 */
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-mysql-test.xml" })
public class EcacheMySqlTimingTest extends AbstractEcacheTimingTest {

    @Resource
    private MysqlTestService mysqlTestService;

    @Resource
    private CountryDao nonCacheCountryDao;

    @PostConstruct
    public void postConstruct() {
        assertNotNull(getCountryService());
        assertNotNull(nonCacheCountryDao);
        assertNotNull(mysqlTestService);
        mysqlTestService.initializeDatabase();
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
    public void testFetchFromCachedDao() {
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
