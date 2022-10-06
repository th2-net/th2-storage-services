package com.exactpro.th2.storageservices.model;

import com.exactpro.th2.storageservices.utils.BookEndpointException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "notFoundResponse", title = "notFoundResponse", description = "Error response class, with proprietary error codes: " + BookEndpointException.BOOK_NOT_FOUND + ", " + BookEndpointException.KEYSPACE_NOT_FOUND)
public class CustomNotFoundResponse {
    private final String errorCode;
    private final String message;

    public CustomNotFoundResponse(String errorCode, String message) {
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
