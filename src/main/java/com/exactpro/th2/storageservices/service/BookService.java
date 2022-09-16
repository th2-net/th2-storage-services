/*
 * Copyright 2020-2022 Exactpro (Exactpro Systems Limited)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exactpro.th2.storageservices.service;

import com.datastax.oss.driver.api.core.cql.BoundStatementBuilder;
import com.exactpro.th2.storageservices.dao.BookOperator;
import com.exactpro.th2.storageservices.dao.CassandraDataMapper;
import com.exactpro.th2.storageservices.dao.CassandraDataMapperBuilder;
import com.exactpro.th2.storageservices.model.BookEntity;
import com.exactpro.th2.storageservices.model.BookResponse;
import com.exactpro.th2.storageservices.utils.BookEndpointException;
import com.exactpro.th2.storageservices.utils.CassandraConnection;
import io.micronaut.context.annotation.Bean;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Bean
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final ConcurrentHashMap<String, BookOperator> operators;
    private final CassandraDataMapper cassandraDataMapper;
    private final Function<BoundStatementBuilder, BoundStatementBuilder> readAttrs;

    @Inject
    public BookService (CassandraConnection cassandraConnection) {
        this.operators = new ConcurrentHashMap<>();
        this.cassandraDataMapper = new CassandraDataMapperBuilder(cassandraConnection.getSession()).build();
        this.readAttrs = builder -> builder.setTimeout(cassandraConnection.getTimeout());
    }

    public BookResponse getBook (String keyspace, String id) {
        try {
            var operator = operators.computeIfAbsent(keyspace, (key) -> cassandraDataMapper.cradleBookOperator(keyspace, BookEntity.TABLE_NAME));


            BookEntity entity = operator.get(id, readAttrs);

            if (entity == null) {
                throw new BookEndpointException(BookEndpointException.BOOK_NOT_FOUND, "Could not find book with `id` " + id);
            }

            return new BookResponse(entity);
        } catch (Exception e) {
            throw new BookEndpointException(BookEndpointException.KEYSPACE_NOT_FOUND, e.getMessage());
        }
    }
}
