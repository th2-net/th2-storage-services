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

package com.exactpro.th2.storageservices.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name="book")
public class BookResponse {

    private final String id;
    @Schema(required = false)
    private final String fullName;
    @Schema(required = false)
    private final String desc;
    private final Instant created;
    private final String schemaVersion;

    public BookResponse(String id, String fullName, String desc, Instant created, String schemaVersion) {
        this.id = id;
        this.fullName = fullName;
        this.desc = desc;
        this.created = created;
        this.schemaVersion = schemaVersion;
    }

    public BookResponse(BookEntity bookEntity) {
        this.id = bookEntity.getName();
        this.fullName = bookEntity.getFullName();
        this.desc = bookEntity.getDesc();
        this.created = bookEntity.getCreated();
        this.schemaVersion = bookEntity.getSchemaVersion();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDesc() {
        return desc;
    }

    public Instant getCreated() {
        return created;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }
}
