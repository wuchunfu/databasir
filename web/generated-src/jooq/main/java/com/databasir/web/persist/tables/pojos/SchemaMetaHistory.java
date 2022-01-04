/*
 * This file is generated by jOOQ.
 */
package com.databasir.web.persist.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import org.jooq.JSON;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SchemaMetaHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer       id;
    private Integer       schemaSourceId;
    private Integer       schemaMetaId;
    private JSON          schemaMetaObject;
    private Long          version;
    private LocalDateTime createAt;

    public SchemaMetaHistory() {}

    public SchemaMetaHistory(SchemaMetaHistory value) {
        this.id = value.id;
        this.schemaSourceId = value.schemaSourceId;
        this.schemaMetaId = value.schemaMetaId;
        this.schemaMetaObject = value.schemaMetaObject;
        this.version = value.version;
        this.createAt = value.createAt;
    }

    public SchemaMetaHistory(
        Integer       id,
        Integer       schemaSourceId,
        Integer       schemaMetaId,
        JSON          schemaMetaObject,
        Long          version,
        LocalDateTime createAt
    ) {
        this.id = id;
        this.schemaSourceId = schemaSourceId;
        this.schemaMetaId = schemaMetaId;
        this.schemaMetaObject = schemaMetaObject;
        this.version = version;
        this.createAt = createAt;
    }

    /**
     * Getter for <code>databasir.schema_meta_history.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>databasir.schema_meta_history.id</code>.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for <code>databasir.schema_meta_history.schema_source_id</code>.
     */
    public Integer getSchemaSourceId() {
        return this.schemaSourceId;
    }

    /**
     * Setter for <code>databasir.schema_meta_history.schema_source_id</code>.
     */
    public void setSchemaSourceId(Integer schemaSourceId) {
        this.schemaSourceId = schemaSourceId;
    }

    /**
     * Getter for <code>databasir.schema_meta_history.schema_meta_id</code>.
     */
    public Integer getSchemaMetaId() {
        return this.schemaMetaId;
    }

    /**
     * Setter for <code>databasir.schema_meta_history.schema_meta_id</code>.
     */
    public void setSchemaMetaId(Integer schemaMetaId) {
        this.schemaMetaId = schemaMetaId;
    }

    /**
     * Getter for <code>databasir.schema_meta_history.schema_meta_object</code>.
     */
    public JSON getSchemaMetaObject() {
        return this.schemaMetaObject;
    }

    /**
     * Setter for <code>databasir.schema_meta_history.schema_meta_object</code>.
     */
    public void setSchemaMetaObject(JSON schemaMetaObject) {
        this.schemaMetaObject = schemaMetaObject;
    }

    /**
     * Getter for <code>databasir.schema_meta_history.version</code>.
     */
    public Long getVersion() {
        return this.version;
    }

    /**
     * Setter for <code>databasir.schema_meta_history.version</code>.
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Getter for <code>databasir.schema_meta_history.create_at</code>.
     */
    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    /**
     * Setter for <code>databasir.schema_meta_history.create_at</code>.
     */
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SchemaMetaHistory (");

        sb.append(id);
        sb.append(", ").append(schemaSourceId);
        sb.append(", ").append(schemaMetaId);
        sb.append(", ").append(schemaMetaObject);
        sb.append(", ").append(version);
        sb.append(", ").append(createAt);

        sb.append(")");
        return sb.toString();
    }
}
