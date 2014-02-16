package com.byteworksinc.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: smitchell
 * Date: 11/24/13
 * Time: 9:01 PM
 *
 */
public class SqlLoader {
    private static Logger logger = Logger.getLogger(SqlLoader.class);
    private static final String basePath = "/sql/";
    private static final String ls = System.getProperty("line.separator");

    public static String load(final String path) {
        String sql = null;
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = SqlLoader.class.getResourceAsStream(basePath + path);
            if (is == null) {
                throw new FileNotFoundException(basePath + path);
            } else {
                final StringBuilder  stringBuilder = new StringBuilder();
                String line = null;
                reader = new BufferedReader( new InputStreamReader(is));
                while( ( line = reader.readLine() ) != null ) {
                    stringBuilder.append( line );
                    stringBuilder.append( ls );
                }
                sql = stringBuilder.toString();
            }
        } catch (IOException e) {
            logger.error("Error reading " + path, e);
        }  finally {
            if (reader != null) {
                try {
                    reader.close();
                    is = null;
                } catch (IOException e) {
                    logger.error("Error reading " + path, e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("Error reading " + path, e);
                }
            }
        }
        return sql;

    }
}
