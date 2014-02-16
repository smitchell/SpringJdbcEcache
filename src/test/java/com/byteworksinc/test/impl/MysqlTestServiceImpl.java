package com.byteworksinc.test.impl;

import com.byteworksinc.dao.impl.SqlLoader;
import com.byteworksinc.test.MysqlTestService;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by smitchell on 2/16/14.
 */
@Controller
@Transactional(readOnly = false)
public class MysqlTestServiceImpl implements MysqlTestService {

    private final String sqlPrefix = "mysql/";
    private final String schemaDDL = SqlLoader.load(sqlPrefix + "SchemaDDL.sql");
    private final String bootstrapSQL = SqlLoader.load(sqlPrefix + "Bootstrap.sql");


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    /**
     * Initializes a test Mysql environment.
     * You must run mysql> CREATE DATABASE springjdbccache;
     *
     * Also, be sure to verify the JDBC url in local.properties
     */
    @Override
    public void initializeDatabase() {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        jdbcTemplate.update(schemaDDL, params);
        jdbcTemplate.update("TRUNCATE TABLE COUNTRY",params);
        jdbcTemplate.update(bootstrapSQL, params);
    }
}
