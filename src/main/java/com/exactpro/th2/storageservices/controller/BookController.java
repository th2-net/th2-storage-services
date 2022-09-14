package com.exactpro.th2.storageservices.controller;

import com.exactpro.th2.storageservices.model.BookResponse;
import com.exactpro.th2.storageservices.service.BookService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Inject
    private BookService bookService;

    @Get("/{keyspace}/{id}")
    @Operation(summary = "Returns information from cradle")
//    @ApiResponse(content = @Content(mediaType = "text/plain", schema = @Schema(type="book")))
//    @ApiResponse(responseCode = "404", description = "Could not find keyspace/Could not find book with id")
    public BookResponse getBook (String keyspace, String id) {
        return bookService.getBook(keyspace, id);
    }
}
