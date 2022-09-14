package com.exactpro.th2.storageservices.utils;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {HttpStatusException.class, ExceptionHandler.class})
public class BookNotFoundExceptionHandler implements ExceptionHandler<BookNotFoundException, HttpResponse> {

    @Override
    public HttpResponse handle(HttpRequest request, BookNotFoundException exception) {
        return HttpResponse.notFound().body(new CustomNotFoundBody("PROPRIETARY_ERROR_CODE", exception.getMessage()));
    }

    private static class CustomNotFoundBody {
        private final String errorCode;
        private final String message;

        public CustomNotFoundBody(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String getErrorCode() {
            return errorCode;
        }
    }
}