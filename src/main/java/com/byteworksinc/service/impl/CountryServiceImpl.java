package com.byteworksinc.service.impl;

import com.byteworksinc.dao.CountryDao;
import com.byteworksinc.model.Country;
import com.byteworksinc.service.CountryService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by smitchell on 2/16/14.
 */
@Controller
@Transactional(readOnly = false)
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryDao countryDao;

    @Transactional(readOnly = true)
    @Override
    public Country findByPrimaryKey(final Long id) {
        return countryDao.findByPrimaryKey(id);
    }

    @Override
    public void save(Country country) {
        countryDao.save(country);
    }

    @Override
    public void delete(Country country) {
        countryDao.delete(country);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Country> listAll() {
        return countryDao.listAll();
    }
}
