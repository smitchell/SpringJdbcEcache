package com.byteworksinc.test.impl;

import com.byteworksinc.dao.CountryDao;
import com.byteworksinc.dao.impl.SqlLoader;
import com.byteworksinc.dao.mapper.CountryColumn;
import com.byteworksinc.dao.mapper.CountryRowMapper;
import com.byteworksinc.model.Country;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: stevecmitchell
 * Date: Aug 27, 2010
 * Time: 6:54:13 PM
 *
 */
public class NonCacheCountryDaoImpl implements CountryDao {

    private final CountryRowMapper countryRowMapper = new CountryRowMapper();
    private final String sqlPrefix = "country/";
    private final String insertSQL = SqlLoader.load(sqlPrefix + "insert.sql");
    private final String updateSQL = SqlLoader.load(sqlPrefix + "update.sql");
    private final String deleteSQL = SqlLoader.load(sqlPrefix + "delete.sql");
    private final String selectSQL = SqlLoader.load(sqlPrefix + "select.sql");
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Gets instance by id.
     * @param id - The id of the instance.
     * @return The instance matching the id.
     */
    @Override
    public Country findByPrimaryKey(final Long id) {
        final MapSqlParameterSource params = new MapSqlParameterSource("value", id);
        final String sql = selectSQL.concat(" where ").concat(CountryColumn.COUNTRY_ID.name()).concat(" = :value");
        final List<Country> results = jdbcTemplate.query(sql, params, countryRowMapper);
        if (results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    /**
     * Finds an fundcompanyfiling type by FILING_TYPE_CODE.
     * @param code - The FILING_TYPE_CODE to match.
     * @return - The Country matching the specified FILING_TYPE_CODE.
     */
    @Override
    public Country findByCode(final String code) {
        final MapSqlParameterSource params = new MapSqlParameterSource("value", code);
        final String sql = selectSQL.concat(" where ").concat(CountryColumn.COUNTRY_CODE.name()).concat(" = :value");
        final List<Country> results = jdbcTemplate.query(sql, params, countryRowMapper);
        if (results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

    /**
     * Saves a fundcompanyfiling type.
     * @param instance - The fundcompanyfiling type to save.
     */
    @Override
    public void save(Country instance) {
        final Date now = new Date();
        instance.setLastUpdateDate(now);
        if (instance.getId() ==  null) {
            instance.setCreateDate(now);
            insert(instance);
        } else {
            update(instance);
        }
    }

    public void insert(final Country instance) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = generateMapSqlParameterSource(instance);
        jdbcTemplate.update(insertSQL, params, keyHolder);
        instance.setId(keyHolder.getKey().longValue());
    }

    public void update(final Country instance) {
        MapSqlParameterSource params = generateMapSqlParameterSource(instance);
        instance.setLastUpdateDate(new Date());
        jdbcTemplate.update(updateSQL, params);
    }

    private MapSqlParameterSource generateMapSqlParameterSource(Country instance) {
        return new MapSqlParameterSource("id", instance.getId())
                .addValue("name", instance.getName())
                .addValue("countryCode", instance.getCountryCode())
                .addValue("createDate", instance.getCreateDate())
                .addValue("lastUpdateDate", instance.getLastUpdateDate());
    }

    /**
     * Delete a Country type.
     * @param instance - The Country type to delete.
     */
    @Override
    public void delete(Country instance) {
        final MapSqlParameterSource params = new MapSqlParameterSource("value", instance.getId());
        jdbcTemplate.update(deleteSQL, params);
    }

    /**
     * Lists Country types.
     * @return - The list of Country types.
     */
    @Override
    public List<Country> listAll() {
        return jdbcTemplate.query(selectSQL.concat(" ORDER BY COUNTRY_NAME"), countryRowMapper);
    }

	/**
	 * Finds Country by name.
	 * @param name - The name to use for a case insensitive search.
	 * @return - The list of countries with a given name.
	 */
    @Override
	public Country findByName(String name) {
        final MapSqlParameterSource params = new MapSqlParameterSource("value", name);
        final String sql = selectSQL.concat(" where ").concat(CountryColumn.COUNTRY_NAME.name()).concat(" = :value");
        final List<Country> results = jdbcTemplate.query(sql, params, countryRowMapper);
        if (results.size() > 0) {
            return results.get(0);
        }
        return null;
    }

}
