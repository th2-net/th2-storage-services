package com.exactpro.th2.storageservices.dao;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.DaoTable;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface CassandraDataMapper {

    @DaoFactory
    BookOperator cradleBookOperator(@DaoKeyspace String keyspace, @DaoTable String booksTable);

}