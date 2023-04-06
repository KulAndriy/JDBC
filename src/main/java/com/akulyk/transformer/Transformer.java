package com.akulyk.transformer;


import java.sql.ResultSet;
import java.sql.*;

public class Transformer<T> {
    private final Class<T> clazz;

    public Transformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Object fromResultSetToEntity(ResultSet rs)
            throws SQLException {
        //create new object
        Object entity = null;

        /**
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         */

        return entity;
    }
}
