/*
 * This file is generated by jOOQ.
 */
package com.databasir.web.persist.tables;


import com.databasir.web.persist.Databasir;
import com.databasir.web.persist.Keys;
import com.databasir.web.persist.tables.records.SchemaMetaHistoryRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.JSON;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SchemaMetaHistory extends TableImpl<SchemaMetaHistoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>databasir.schema_meta_history</code>
     */
    public static final SchemaMetaHistory SCHEMA_META_HISTORY = new SchemaMetaHistory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SchemaMetaHistoryRecord> getRecordType() {
        return SchemaMetaHistoryRecord.class;
    }

    /**
     * The column <code>databasir.schema_meta_history.id</code>.
     */
    public final TableField<SchemaMetaHistoryRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>databasir.schema_meta_history.schema_source_id</code>.
     */
    public final TableField<SchemaMetaHistoryRecord, Integer> SCHEMA_SOURCE_ID = createField(DSL.name("schema_source_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>databasir.schema_meta_history.schema_meta_id</code>.
     */
    public final TableField<SchemaMetaHistoryRecord, Integer> SCHEMA_META_ID = createField(DSL.name("schema_meta_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>databasir.schema_meta_history.schema_meta_object</code>.
     */
    public final TableField<SchemaMetaHistoryRecord, JSON> SCHEMA_META_OBJECT = createField(DSL.name("schema_meta_object"), SQLDataType.JSON, this, "");

    /**
     * The column <code>databasir.schema_meta_history.version</code>.
     */
    public final TableField<SchemaMetaHistoryRecord, Long> VERSION = createField(DSL.name("version"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>databasir.schema_meta_history.create_at</code>.
     */
    public final TableField<SchemaMetaHistoryRecord, LocalDateTime> CREATE_AT = createField(DSL.name("create_at"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.LOCALDATETIME)), this, "");

    private SchemaMetaHistory(Name alias, Table<SchemaMetaHistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private SchemaMetaHistory(Name alias, Table<SchemaMetaHistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>databasir.schema_meta_history</code> table
     * reference
     */
    public SchemaMetaHistory(String alias) {
        this(DSL.name(alias), SCHEMA_META_HISTORY);
    }

    /**
     * Create an aliased <code>databasir.schema_meta_history</code> table
     * reference
     */
    public SchemaMetaHistory(Name alias) {
        this(alias, SCHEMA_META_HISTORY);
    }

    /**
     * Create a <code>databasir.schema_meta_history</code> table reference
     */
    public SchemaMetaHistory() {
        this(DSL.name("schema_meta_history"), null);
    }

    public <O extends Record> SchemaMetaHistory(Table<O> child, ForeignKey<O, SchemaMetaHistoryRecord> key) {
        super(child, key, SCHEMA_META_HISTORY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Databasir.DATABASIR;
    }

    @Override
    public Identity<SchemaMetaHistoryRecord, Integer> getIdentity() {
        return (Identity<SchemaMetaHistoryRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<SchemaMetaHistoryRecord> getPrimaryKey() {
        return Keys.KEY_SCHEMA_META_HISTORY_PRIMARY;
    }

    @Override
    public List<UniqueKey<SchemaMetaHistoryRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_SCHEMA_META_HISTORY_UK_CONNECTION_ID_VERSION);
    }

    @Override
    public SchemaMetaHistory as(String alias) {
        return new SchemaMetaHistory(DSL.name(alias), this);
    }

    @Override
    public SchemaMetaHistory as(Name alias) {
        return new SchemaMetaHistory(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SchemaMetaHistory rename(String name) {
        return new SchemaMetaHistory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SchemaMetaHistory rename(Name name) {
        return new SchemaMetaHistory(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, Integer, JSON, Long, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
