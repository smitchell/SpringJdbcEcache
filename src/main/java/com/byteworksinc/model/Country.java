package com.byteworksinc.model;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: stevecmitchell
 * Date: Aug 27, 2010
 * Time: 6:28:17 PM
 * 
 */
public class Country extends Model {
    private static final long serialVersionUID = -2495805368466646494L;
    private String name;
    private String countryCode;
    private Date createDate;
    private Date lastUpdateDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
