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
public class TableTriggerMeta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer       id;
    private Integer       tableMetaId;
    private Integer       schemaMetaId;
    private String        timing;
    private String        manipulation;
    private String        statement;
    private String        triggerCreateAt;
    private LocalDateTime createAt;

    public TableTriggerMeta() {}

    public TableTriggerMeta(TableTriggerMeta value) {
        this.id = value.id;
        this.tableMetaId = value.tableMetaId;
        this.schemaMetaId = value.schemaMetaId;
        this.timing = value.timing;
        this.manipulation = value.manipulation;
        this.statement = value.statement;
        this.triggerCreateAt = value.triggerCreateAt;
        this.createAt = value.createAt;
    }

    public TableTriggerMeta(
        Integer       id,
        Integer       tableMetaId,
        Integer       schemaMetaId,
        String        timing,
        String        manipulation,
        String        statement,
        String        triggerCreateAt,
        LocalDateTime createAt
    ) {
        this.id = id;
        this.tableMetaId = tableMetaId;
        this.schemaMetaId = schemaMetaId;
        this.timing = timing;
        this.manipulation = manipulation;
        this.statement = statement;
        this.triggerCreateAt = triggerCreateAt;
        this.createAt = createAt;
    }

    /**
     * Getter for <code>databasir.table_trigger_meta.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>databasir.table_trigger_meta.id</code>.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for <code>databasir.table_trigger_meta.table_meta_id</code>.
     */
    public Integer getTableMetaId() {
        return this.tableMetaId;
    }

    /**
     * Setter for <code>databasir.table_trigger_meta.table_meta_id</code>.
     */
    public void setTableMetaId(Integer tableMetaId) {
        this.tableMetaId = tableMetaId;
    }

    /**
     * Getter for <code>databasir.table_trigger_meta.schema_meta_id</code>.
     */
    public Integer getSchemaMetaId() {
        return this.schemaMetaId;
    }

    /**
     * Setter for <code>databasir.table_trigger_meta.schema_meta_id</code>.
     */
    public void setSchemaMetaId(Integer schemaMetaId) {
        this.schemaMetaId = schemaMetaId;
    }

    /**
     * Getter for <code>databasir.table_trigger_meta.timing</code>.
     */
    public String getTiming() {
        return this.timing;
    }

    /**
     * Setter for <code>databasir.table_trigger_meta.timing</code>.
     */
    public void setTiming(String timing) {
        this.timing = timing;
    }

    /**
     * Getter for <code>databasir.table_trigger_meta.manipulation</code>.
     */
    public String getManipulation() {
        return this.manipulation;
    }

    /**
     * Setter for <code>databasir.table_trigger_meta.manipulation</code>.
     */
    public void setManipulation(String manipulation) {
        this.manipulation = manipulation;
    }

    /**
     * Getter for <code>databasir.table_trigger_meta.statement</code>.
     */
    public String getStatement() {
        return this.statement;
    }

    /**
     * Setter for <code>databasir.table_trigger_meta.statement</code>.
     */
    public void setStatement(String statement) {
        this.statement = statement;
    }

    /**
     * Getter for <code>databasir.table_trigger_meta.trigger_create_at</code>.
     */
    public String getTriggerCreateAt() {
        return this.triggerCreateAt;
    }

    /**
     * Setter for <code>databasir.table_trigger_meta.trigger_create_at</code>.
     */
    public void setTriggerCreateAt(String triggerCreateAt) {
        this.triggerCreateAt = triggerCreateAt;
    }

    /**
     * Getter for <code>databasir.table_trigger_meta.create_at</code>.
     */
    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    /**
     * Setter for <code>databasir.table_trigger_meta.create_at</code>.
     */
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TableTriggerMeta (");

        sb.append(id);
        sb.append(", ").append(tableMetaId);
        sb.append(", ").append(schemaMetaId);
        sb.append(", ").append(timing);
        sb.append(", ").append(manipulation);
        sb.append(", ").append(statement);
        sb.append(", ").append(triggerCreateAt);
        sb.append(", ").append(createAt);

        sb.append(")");
        return sb.toString();
    }
}
