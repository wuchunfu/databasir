/*
 * This file is generated by jOOQ.
 */
package com.databasir.web.persist.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SchemaSource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer       id;
    private String        title;
    private String        description;
    private String        databaseType;
    private String        schemaName;
    private Boolean       deleted;
    private LocalDateTime createAt;

    public SchemaSource() {}

    public SchemaSource(SchemaSource value) {
        this.id = value.id;
        this.title = value.title;
        this.description = value.description;
        this.databaseType = value.databaseType;
        this.schemaName = value.schemaName;
        this.deleted = value.deleted;
        this.createAt = value.createAt;
    }

    public SchemaSource(
        Integer       id,
        String        title,
        String        description,
        String        databaseType,
        String        schemaName,
        Boolean       deleted,
        LocalDateTime createAt
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.databaseType = databaseType;
        this.schemaName = schemaName;
        this.deleted = deleted;
        this.createAt = createAt;
    }

    /**
     * Getter for <code>databasir.schema_source.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>databasir.schema_source.id</code>.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for <code>databasir.schema_source.title</code>.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>databasir.schema_source.title</code>.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for <code>databasir.schema_source.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>databasir.schema_source.description</code>.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for <code>databasir.schema_source.database_type</code>.
     */
    public String getDatabaseType() {
        return this.databaseType;
    }

    /**
     * Setter for <code>databasir.schema_source.database_type</code>.
     */
    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    /**
     * Getter for <code>databasir.schema_source.schema_name</code>.
     */
    public String getSchemaName() {
        return this.schemaName;
    }

    /**
     * Setter for <code>databasir.schema_source.schema_name</code>.
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * Getter for <code>databasir.schema_source.deleted</code>.
     */
    public Boolean getDeleted() {
        return this.deleted;
    }

    /**
     * Setter for <code>databasir.schema_source.deleted</code>.
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Getter for <code>databasir.schema_source.create_at</code>.
     */
    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    /**
     * Setter for <code>databasir.schema_source.create_at</code>.
     */
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SchemaSource (");

        sb.append(id);
        sb.append(", ").append(title);
        sb.append(", ").append(description);
        sb.append(", ").append(databaseType);
        sb.append(", ").append(schemaName);
        sb.append(", ").append(deleted);
        sb.append(", ").append(createAt);

        sb.append(")");
        return sb.toString();
    }
}
