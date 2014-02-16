package com.byteworksinc.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by smitchell on 12/28/13.
 */
public interface GenericDao< T, ID extends Serializable> {

    /**
     * Returns an object by its primary key.
     *
     * @param id
     *            - The primary key to retrieve by.
     * @return - An instance of the match type
     */
    T findByPrimaryKey(ID id);

    List<T> listAll();

    void save(T instance);

    void delete(T instance);
}
