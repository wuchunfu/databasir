/*
 * This file is generated by jOOQ.
 */
package com.databasir.dao.tables;


import com.databasir.dao.Databasir;
import com.databasir.dao.Keys;
import com.databasir.dao.tables.records.PermissionRecord;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class Permission extends TableImpl<PermissionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>databasir.permission</code>
     */
    public static final Permission PERMISSION = new Permission();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PermissionRecord> getRecordType() {
        return PermissionRecord.class;
    }

    /**
     * The column <code>databasir.permission.id</code>.
     */
    public final TableField<PermissionRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>databasir.permission.name</code>.
     */
    public final TableField<PermissionRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>databasir.permission.expression</code>.
     */
    public final TableField<PermissionRecord, String> EXPRESSION = createField(DSL.name("expression"), SQLDataType.VARCHAR(512).nullable(false), this, "");

    /**
     * The column <code>databasir.permission.path</code>.
     */
    public final TableField<PermissionRecord, String> PATH = createField(DSL.name("path"), SQLDataType.VARCHAR(512).nullable(false), this, "");

    /**
     * The column <code>databasir.permission.update_at</code>.
     */
    public final TableField<PermissionRecord, LocalDateTime> UPDATE_AT = createField(DSL.name("update_at"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>databasir.permission.create_at</code>.
     */
    public final TableField<PermissionRecord, LocalDateTime> CREATE_AT = createField(DSL.name("create_at"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field("CURRENT_TIMESTAMP", SQLDataType.LOCALDATETIME)), this, "");

    private Permission(Name alias, Table<PermissionRecord> aliased) {
        this(alias, aliased, null);
    }

    private Permission(Name alias, Table<PermissionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>databasir.permission</code> table reference
     */
    public Permission(String alias) {
        this(DSL.name(alias), PERMISSION);
    }

    /**
     * Create an aliased <code>databasir.permission</code> table reference
     */
    public Permission(Name alias) {
        this(alias, PERMISSION);
    }

    /**
     * Create a <code>databasir.permission</code> table reference
     */
    public Permission() {
        this(DSL.name("permission"), null);
    }

    public <O extends Record> Permission(Table<O> child, ForeignKey<O, PermissionRecord> key) {
        super(child, key, PERMISSION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Databasir.DATABASIR;
    }

    @Override
    public Identity<PermissionRecord, Integer> getIdentity() {
        return (Identity<PermissionRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<PermissionRecord> getPrimaryKey() {
        return Keys.KEY_PERMISSION_PRIMARY;
    }

    @Override
    public Permission as(String alias) {
        return new Permission(DSL.name(alias), this);
    }

    @Override
    public Permission as(Name alias) {
        return new Permission(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Permission rename(String name) {
        return new Permission(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Permission rename(Name name) {
        return new Permission(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
