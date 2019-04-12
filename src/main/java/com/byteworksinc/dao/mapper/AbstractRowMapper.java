package com.byteworksinc.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * Created by smitchell on 12/29/13.
 */
public class AbstractRowMapper {
    private Logger logger = Logger.getLogger(this.getClass());

    protected Long getNonZeroLong(final Long value) {
        if (value != null && value.equals(0L)) {
            return null;
        }
        return value;
    }

    protected Integer getNonZeroInteger(final Integer value) {
        if (value != null && value.equals(0)) {
            return null;
        }
        return value;
    }

    protected boolean hasColumn(final ResultSet rs, final String column) {
        try
        {
            rs.findColumn(column);
            return true;
        } catch (SQLException sqlex)
        {
            logger.trace("column doesn't exist: " + column);
        }
        return false;
    }
}
