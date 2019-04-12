package com.byteworksinc.dao.mapper;

import com.byteworksinc.model.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created with IntelliJ IDEA.
 * User: smitchell
 * Date: 11/26/13
 * Time: 8:40 PM
 *
 */
public class CountryRowMapper extends AbstractRowMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Country country = new Country();
        country.setId(getNonZeroLong(rs.getLong(CountryColumn.COUNTRY_ID.name())));
        country.setCountryCode(rs.getString(CountryColumn.COUNTRY_CODE.name()));
        country.setName(rs.getString(CountryColumn.COUNTRY_NAME.name()));
        country.setCreateDate(rs.getDate(CountryColumn.CREATE_DATE.name()));
        country.setLastUpdateDate(rs.getDate(CountryColumn.UPDATE_DATE.name()));
        return country;
    }

}
