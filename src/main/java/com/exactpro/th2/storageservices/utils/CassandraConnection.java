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
