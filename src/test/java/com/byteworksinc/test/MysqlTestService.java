package com.byteworksinc.test;

/**
 * Created by smitchell on 2/16/14.
 */
public interface MysqlTestService {

    /**
     * Initializes a test Mysql environment.
     * You must run mysql> CREATE DATABASE springjdbccache;
     *
     * Also, be sure to verify the JDBC url in local.properties
     */
    void initializeDatabase();
}
