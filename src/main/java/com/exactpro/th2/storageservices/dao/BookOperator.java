package com.exactpro.th2.storageservices.dao;

import com.datastax.oss.driver.api.core.cql.BoundStatementBuilder;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.exactpro.th2.storageservices.model.BookEntity;

import java.util.function.Function;

@Dao
public interface BookOperator {

    @Select
    BookEntity get (String id, Function<BoundStatementBuilder, BoundStatementBuilder> attributes);
}
