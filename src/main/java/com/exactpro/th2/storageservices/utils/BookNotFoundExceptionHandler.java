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