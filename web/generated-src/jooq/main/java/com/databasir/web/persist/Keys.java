/*
 * This file is generated by jOOQ.
 */
package com.databasir.web.persist;


import com.databasir.web.persist.tables.Connection;
import com.databasir.web.persist.tables.ConnectionProperty;
import com.databasir.web.persist.tables.SchemaMeta;
import com.databasir.web.persist.tables.SchemaMetaHistory;
import com.databasir.web.persist.tables.SchemaSource;
import com.databasir.web.persist.tables.SchemaSourceMetaRule;
import com.databasir.web.persist.tables.SchemaSourceTag;
import com.databasir.web.persist.tables.TableColumnMeta;
import com.databasir.web.persist.tables.TableIndexMeta;
import com.databasir.web.persist.tables.TableMeta;
import com.databasir.web.persist.tables.TableTriggerMeta;
import com.databasir.web.persist.tables.Tag;
import com.databasir.web.persist.tables.records.ConnectionPropertyRecord;
import com.databasir.web.persist.tables.records.ConnectionRecord;
import com.databasir.web.persist.tables.records.SchemaMetaHistoryRecord;
import com.databasir.web.persist.tables.records.SchemaMetaRecord;
import com.databasir.web.persist.tables.records.SchemaSourceMetaRuleRecord;
import com.databasir.web.persist.tables.records.SchemaSourceRecord;
import com.databasir.web.persist.tables.records.SchemaSourceTagRecord;
import com.databasir.web.persist.tables.records.TableColumnMetaRecord;
import com.databasir.web.persist.tables.records.TableIndexMetaRecord;
import com.databasir.web.persist.tables.records.TableMetaRecord;
import com.databasir.web.persist.tables.records.TableTriggerMetaRecord;
import com.databasir.web.persist.tables.records.TagRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * databasir.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ConnectionRecord> KEY_CONNECTION_PRIMARY = Internal.createUniqueKey(Connection.CONNECTION, DSL.name("KEY_connection_PRIMARY"), new TableField[] { Connection.CONNECTION.ID }, true);
    public static final UniqueKey<ConnectionRecord> KEY_CONNECTION_UK_SCHEMA_SOURCE_ID = Internal.createUniqueKey(Connection.CONNECTION, DSL.name("KEY_connection_uk_schema_source_id"), new TableField[] { Connection.CONNECTION.SCHEMA_SOURCE_ID }, true);
    public static final UniqueKey<ConnectionPropertyRecord> KEY_CONNECTION_PROPERTY_PRIMARY = Internal.createUniqueKey(ConnectionProperty.CONNECTION_PROPERTY, DSL.name("KEY_connection_property_PRIMARY"), new TableField[] { ConnectionProperty.CONNECTION_PROPERTY.ID }, true);
    public static final UniqueKey<SchemaMetaRecord> KEY_SCHEMA_META_PRIMARY = Internal.createUniqueKey(SchemaMeta.SCHEMA_META, DSL.name("KEY_schema_meta_PRIMARY"), new TableField[] { SchemaMeta.SCHEMA_META.ID }, true);
    public static final UniqueKey<SchemaMetaRecord> KEY_SCHEMA_META_UK_SCHEMA_SOURCE_ID = Internal.createUniqueKey(SchemaMeta.SCHEMA_META, DSL.name("KEY_schema_meta_uk_schema_source_id"), new TableField[] { SchemaMeta.SCHEMA_META.SCHEMA_SOURCE_ID }, true);
    public static final UniqueKey<SchemaMetaHistoryRecord> KEY_SCHEMA_META_HISTORY_PRIMARY = Internal.createUniqueKey(SchemaMetaHistory.SCHEMA_META_HISTORY, DSL.name("KEY_schema_meta_history_PRIMARY"), new TableField[] { SchemaMetaHistory.SCHEMA_META_HISTORY.ID }, true);
    public static final UniqueKey<SchemaMetaHistoryRecord> KEY_SCHEMA_META_HISTORY_UK_CONNECTION_ID_VERSION = Internal.createUniqueKey(SchemaMetaHistory.SCHEMA_META_HISTORY, DSL.name("KEY_schema_meta_history_uk_connection_id_version"), new TableField[] { SchemaMetaHistory.SCHEMA_META_HISTORY.SCHEMA_META_ID, SchemaMetaHistory.SCHEMA_META_HISTORY.VERSION }, true);
    public static final UniqueKey<SchemaSourceRecord> KEY_SCHEMA_SOURCE_PRIMARY = Internal.createUniqueKey(SchemaSource.SCHEMA_SOURCE, DSL.name("KEY_schema_source_PRIMARY"), new TableField[] { SchemaSource.SCHEMA_SOURCE.ID }, true);
    public static final UniqueKey<SchemaSourceMetaRuleRecord> KEY_SCHEMA_SOURCE_META_RULE_PRIMARY = Internal.createUniqueKey(SchemaSourceMetaRule.SCHEMA_SOURCE_META_RULE, DSL.name("KEY_schema_source_meta_rule_PRIMARY"), new TableField[] { SchemaSourceMetaRule.SCHEMA_SOURCE_META_RULE.ID }, true);
    public static final UniqueKey<SchemaSourceMetaRuleRecord> KEY_SCHEMA_SOURCE_META_RULE_UK_SCHEMA_SOURCE_ID = Internal.createUniqueKey(SchemaSourceMetaRule.SCHEMA_SOURCE_META_RULE, DSL.name("KEY_schema_source_meta_rule_uk_schema_source_id"), new TableField[] { SchemaSourceMetaRule.SCHEMA_SOURCE_META_RULE.SCHEMA_SOURCE_ID }, true);
    public static final UniqueKey<SchemaSourceTagRecord> KEY_SCHEMA_SOURCE_TAG_PRIMARY = Internal.createUniqueKey(SchemaSourceTag.SCHEMA_SOURCE_TAG, DSL.name("KEY_schema_source_tag_PRIMARY"), new TableField[] { SchemaSourceTag.SCHEMA_SOURCE_TAG.ID }, true);
    public static final UniqueKey<SchemaSourceTagRecord> KEY_SCHEMA_SOURCE_TAG_UK_DATABASE_TAG = Internal.createUniqueKey(SchemaSourceTag.SCHEMA_SOURCE_TAG, DSL.name("KEY_schema_source_tag_uk_database_tag"), new TableField[] { SchemaSourceTag.SCHEMA_SOURCE_TAG.SCHEMA_SOURCE_ID, SchemaSourceTag.SCHEMA_SOURCE_TAG.TAG_ID }, true);
    public static final UniqueKey<TableColumnMetaRecord> KEY_TABLE_COLUMN_META_PRIMARY = Internal.createUniqueKey(TableColumnMeta.TABLE_COLUMN_META, DSL.name("KEY_table_column_meta_PRIMARY"), new TableField[] { TableColumnMeta.TABLE_COLUMN_META.ID }, true);
    public static final UniqueKey<TableIndexMetaRecord> KEY_TABLE_INDEX_META_PRIMARY = Internal.createUniqueKey(TableIndexMeta.TABLE_INDEX_META, DSL.name("KEY_table_index_meta_PRIMARY"), new TableField[] { TableIndexMeta.TABLE_INDEX_META.ID }, true);
    public static final UniqueKey<TableMetaRecord> KEY_TABLE_META_PRIMARY = Internal.createUniqueKey(TableMeta.TABLE_META, DSL.name("KEY_table_meta_PRIMARY"), new TableField[] { TableMeta.TABLE_META.ID }, true);
    public static final UniqueKey<TableTriggerMetaRecord> KEY_TABLE_TRIGGER_META_PRIMARY = Internal.createUniqueKey(TableTriggerMeta.TABLE_TRIGGER_META, DSL.name("KEY_table_trigger_meta_PRIMARY"), new TableField[] { TableTriggerMeta.TABLE_TRIGGER_META.ID }, true);
    public static final UniqueKey<TagRecord> KEY_TAG_PRIMARY = Internal.createUniqueKey(Tag.TAG, DSL.name("KEY_tag_PRIMARY"), new TableField[] { Tag.TAG.ID }, true);
    public static final UniqueKey<TagRecord> KEY_TAG_UK_NAME = Internal.createUniqueKey(Tag.TAG, DSL.name("KEY_tag_uk_name"), new TableField[] { Tag.TAG.NAME }, true);
}
