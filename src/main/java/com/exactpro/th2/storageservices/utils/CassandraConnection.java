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

package com.exactpro.th2.storageservices.utils;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.time.Duration;

@Singleton
public class CassandraConnection {

    private static final Logger logger = LoggerFactory.getLogger(CassandraConnection.class);

    private final CqlSession session;
    private final Duration timeout;

    public CassandraConnection (CassandraConfig cassandraConfig) {
        logger.info("Initializing Cassandra Connection");
        CqlSession cqlSession = null;
        try {
           cqlSession = new CqlSessionBuilder()
                    .withLocalDatacenter(cassandraConfig.getDataCenter())
                    .addContactPoint(new InetSocketAddress(cassandraConfig.getHost(), cassandraConfig.getPort()))
                    .withAuthCredentials(cassandraConfig.getUsername(), cassandraConfig.getPassword())
                    .build();

            logger.info("Cassandra Connection Initialized");
        } catch (Exception e) {
            logger.error("Could not initialize cassandra connection properly");
        } finally {
            this.session = cqlSession;
            this.timeout = Duration.ofMillis(cassandraConfig.getTimeout());
        }


    }

    public CqlSession getSession() {
        return session;
    }

    public Duration getTimeout() {
        return timeout;
    }

    @PreDestroy
    private void preDestroy () {
        logger.info("Closing Cassandra Connection");
        session.close();
    }
}
