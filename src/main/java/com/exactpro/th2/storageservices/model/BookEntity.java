package com.exactpro.th2.storageservices.model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;

import java.time.Instant;

@Entity
@CqlName(BookEntity.TABLE_NAME)
@PropertyStrategy(mutable = false)
public class BookEntity {

    public static final String TABLE_NAME = "books";

    public static final String FIELD_NAME = "name";
    public static final String FIELD_FULLNAME = "fullname";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_CREATED = "created";
    public static final String FIELD_SCHEMA_VERSION = "schema_version";

    @PartitionKey(0)
    @CqlName(FIELD_NAME)
    private final String name;

    @CqlName(FIELD_FULLNAME)
    private final String fullName;

    @CqlName(FIELD_DESCRIPTION)
    private final String desc;

    @CqlName(FIELD_CREATED)
    private final Instant created;

    @CqlName(FIELD_SCHEMA_VERSION)
    private final String schemaVersion;

    public BookEntity(String name, String fullName, String desc, Instant created, String schemaVersion) {
        this.name = name;
        this.fullName = fullName;
        this.desc = desc;
        this.created = created;
        this.schemaVersion = schemaVersion;
    }

    public String getName() {
        return name;
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
