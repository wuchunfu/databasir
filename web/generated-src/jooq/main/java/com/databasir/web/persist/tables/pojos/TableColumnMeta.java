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
public class TableColumnMeta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer       id;
    private Integer       tableMetaId;
    private Integer       schemaMetaId;
    private String        name;
    private String        type;
    private String        comment;
    private String        defaultValue;
    private Integer       size;
    private Integer       decimalDigits;
    private Boolean       isNullable;
    private Boolean       isAutoIncrement;
    private LocalDateTime createAt;

    public TableColumnMeta() {}

    public TableColumnMeta(TableColumnMeta value) {
        this.id = value.id;
        this.tableMetaId = value.tableMetaId;
        this.schemaMetaId = value.schemaMetaId;
        this.name = value.name;
        this.type = value.type;
        this.comment = value.comment;
        this.defaultValue = value.defaultValue;
        this.size = value.size;
        this.decimalDigits = value.decimalDigits;
        this.isNullable = value.isNullable;
        this.isAutoIncrement = value.isAutoIncrement;
        this.createAt = value.createAt;
    }

    public TableColumnMeta(
        Integer       id,
        Integer       tableMetaId,
        Integer       schemaMetaId,
        String        name,
        String        type,
        String        comment,
        String        defaultValue,
        Integer       size,
        Integer       decimalDigits,
        Boolean       isNullable,
        Boolean       isAutoIncrement,
        LocalDateTime createAt
    ) {
        this.id = id;
        this.tableMetaId = tableMetaId;
        this.schemaMetaId = schemaMetaId;
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.defaultValue = defaultValue;
        this.size = size;
        this.decimalDigits = decimalDigits;
        this.isNullable = isNullable;
        this.isAutoIncrement = isAutoIncrement;
        this.createAt = createAt;
    }

    /**
     * Getter for <code>databasir.table_column_meta.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter for <code>databasir.table_column_meta.id</code>.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for <code>databasir.table_column_meta.table_meta_id</code>.
     */
    public Integer getTableMetaId() {
        return this.tableMetaId;
    }

    /**
     * Setter for <code>databasir.table_column_meta.table_meta_id</code>.
     */
    public void setTableMetaId(Integer tableMetaId) {
        this.tableMetaId = tableMetaId;
    }

    /**
     * Getter for <code>databasir.table_column_meta.schema_meta_id</code>.
     */
    public Integer getSchemaMetaId() {
        return this.schemaMetaId;
    }

    /**
     * Setter for <code>databasir.table_column_meta.schema_meta_id</code>.
     */
    public void setSchemaMetaId(Integer schemaMetaId) {
        this.schemaMetaId = schemaMetaId;
    }

    /**
     * Getter for <code>databasir.table_column_meta.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>databasir.table_column_meta.name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>databasir.table_column_meta.type</code>.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter for <code>databasir.table_column_meta.type</code>.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for <code>databasir.table_column_meta.comment</code>.
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Setter for <code>databasir.table_column_meta.comment</code>.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter for <code>databasir.table_column_meta.default_value</code>.
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Setter for <code>databasir.table_column_meta.default_value</code>.
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Getter for <code>databasir.table_column_meta.size</code>.
     */
    public Integer getSize() {
        return this.size;
    }

    /**
     * Setter for <code>databasir.table_column_meta.size</code>.
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Getter for <code>databasir.table_column_meta.decimal_digits</code>.
     */
    public Integer getDecimalDigits() {
        return this.decimalDigits;
    }

    /**
     * Setter for <code>databasir.table_column_meta.decimal_digits</code>.
     */
    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * Getter for <code>databasir.table_column_meta.is_nullable</code>.
     */
    public Boolean getIsNullable() {
        return this.isNullable;
    }

    /**
     * Setter for <code>databasir.table_column_meta.is_nullable</code>.
     */
    public void setIsNullable(Boolean isNullable) {
        this.isNullable = isNullable;
    }

    /**
     * Getter for <code>databasir.table_column_meta.is_auto_increment</code>.
     */
    public Boolean getIsAutoIncrement() {
        return this.isAutoIncrement;
    }

    /**
     * Setter for <code>databasir.table_column_meta.is_auto_increment</code>.
     */
    public void setIsAutoIncrement(Boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }

    /**
     * Getter for <code>databasir.table_column_meta.create_at</code>.
     */
    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    /**
     * Setter for <code>databasir.table_column_meta.create_at</code>.
     */
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TableColumnMeta (");

        sb.append(id);
        sb.append(", ").append(tableMetaId);
        sb.append(", ").append(schemaMetaId);
        sb.append(", ").append(name);
        sb.append(", ").append(type);
        sb.append(", ").append(comment);
        sb.append(", ").append(defaultValue);
        sb.append(", ").append(size);
        sb.append(", ").append(decimalDigits);
        sb.append(", ").append(isNullable);
        sb.append(", ").append(isAutoIncrement);
        sb.append(", ").append(createAt);

        sb.append(")");
        return sb.toString();
    }
}
