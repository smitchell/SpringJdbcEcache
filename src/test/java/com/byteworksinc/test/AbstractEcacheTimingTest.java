package com.byteworksinc.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.byteworksinc.model.Country;
import com.byteworksinc.service.CountryService;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;

/**
 * Created by smitchell on 2/16/14.
 */
abstract class AbstractEcacheTimingTest {

    private final Map<Long, Country> countryMap = new HashMap<Long, Country>();
    private final NumberFormat nf = NumberFormat.getInstance();

    /**
     * A large array of Sector Ids randomly picked from the set of roughly 250 sectors in the sectorMap
     */
    private final List<Long> randomIds = new ArrayList<Long>();

    private int keysSize = 100000;


    @Resource
    private CountryService countryService;

    void initMap() {
        assertNotNull(countryService);
        for (final Country sector :  countryService.listAll()) {
            countryMap.put(sector.getId(), sector);
        }
        assertFalse(countryMap.isEmpty());
        final int end = countryMap.size() - 1;
        Random random = new Random();
        final List<Long> keys = new ArrayList<Long>(countryMap.keySet());
        for (int idx = 1; idx <= keysSize; ++idx){
            addRandomInteger(0, end, random, keys);
        }

    }

    void reportTime(final String title, final long startTime, final long endTime) {
        final float elaspedSeconds = (endTime - startTime)/ 1000F;
        System.out.println(title.concat(" completed in ").concat(nf.format(elaspedSeconds)).concat(" seconds."));
    }

    void addRandomInteger(final int aStart, final int aEnd, final Random aRandom, List<Long> keys){
        //get the range, casting to long to avoid overflow problems
        final long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        final long fraction = (long)(range * aRandom.nextDouble());
        final int randomNumber =  (int)(fraction + aStart);
        final long key = keys.get(randomNumber);
        randomIds.add(key);
    }

    CountryService getCountryService() {
        return countryService;
    }

    List<Long> getRandomIds() {
        return randomIds;
    }

    int getKeysSize() {
        return keysSize;
    }

    Map<Long, Country> getCountryMap() {
        return countryMap;
    }
}
