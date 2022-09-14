package com.exactpro.th2;

import com.exactpro.th2.storageservices.service.BookService;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import org.spockframework.mock.MockImplementation;

@MicronautTest
class BookServiceTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }
}
