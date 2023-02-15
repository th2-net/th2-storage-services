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

package com.exactpro.th2.storageservices.controller;

import com.datastax.oss.driver.api.core.metadata.schema.KeyspaceMetadata;
import com.exactpro.th2.storageservices.model.BookResponse;
import com.exactpro.th2.storageservices.model.CustomNotFoundResponse;
import com.exactpro.th2.storageservices.model.KeyspaceResponse;
import com.exactpro.th2.storageservices.service.StorageService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@Produces(MediaType.APPLICATION_JSON)
public class StorageController {

    private static final Logger logger = LoggerFactory.getLogger(StorageController.class);

    @Inject
    private StorageService storageService;

    @Get("/{keyspace}/books/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BookResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CustomNotFoundResponse.class))
                            })
            })
    @Operation(summary = "Returns information about book from cradle")
    public BookResponse getBook(String keyspace, String id) {
        return storageService.getBook(keyspace, id);
    }

    @Get("/keyspaces/{keyspace}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = KeyspaceResponse.class))
                            }),
                    @ApiResponse(
                            responseCode = "404",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CustomNotFoundResponse.class))
                            })
            })
    @Operation(summary = "Returns information about keyspace from cradle")
    public KeyspaceResponse getKeyspace(String keyspace) {
        return storageService.getKeyspace(keyspace);
    }
}
