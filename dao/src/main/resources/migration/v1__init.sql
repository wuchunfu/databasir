CREATE TABLE `schema_source`
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    title         VARCHAR(255) NOT NULL,
    description   TEXT         NOT NULL,
    database_type VARCHAR(255) NOT NULL DEFAULT 'UNKNOWN',
    schema_name   VARCHAR(255) NOT NULL,
    deleted       BOOLEAN      NOT NULL DEFAULT FALSE,
    create_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `schema_source_tag`
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    schema_source_id INT       NOT NULL,
    tag_id           INT       NOT NULL,
    create_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UNIQUE uk_database_tag (schema_source_id, tag_id)
);

CREATE TABLE schema_source_meta_rule
(
    id                             INT PRIMARY KEY AUTO_INCREMENT,
    schema_source_id               INT       NOT NULL,
    ignore_table_name_regex_array  JSON      NOT NULL,
    ignore_column_name_regex_array JSON      NOT NULL,
    update_at                      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_at                      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UNIQUE uk_schema_source_id (schema_source_id)
);

CREATE TABLE connection
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    schema_source_id INT       NOT NULL,
    url              TEXT      NOT NULL,
    username         TEXT      NOT NULL,
    password         TEXT      NOT NULL,
    update_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UNIQUE uk_schema_source_id (schema_source_id)
);

CREATE TABLE connection_property
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    connection_id INT       NOT NULL,
    `key`         TEXT      NOT NULL,
    `value`       TEXT      NOT NULL,
    create_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_connection_id (connection_id)
);

CREATE TABLE schema_meta
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    schema_source_id INT       NOT NULL,
    name             TEXT      NOT NULL,
    product_name     TEXT      NOT NULL,
    product_version  TEXT      NOT NULL,
    version          BIGINT    NOT NULL DEFAULT 1,
    update_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_schema_source_id UNIQUE (schema_source_id)
);

CREATE TABLE schema_meta_history
(
    id                 INT PRIMARY KEY AUTO_INCREMENT,
    schema_source_id   INT       NOT NULL,
    schema_meta_id     INT       NOT NULL,
    schema_meta_object JSON               DEFAULT NULL,
    version            BIGINT    NOT NULL,
    create_at          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_connection_id_version UNIQUE (schema_meta_id, version),
    INDEX idx_schema_source_id (schema_source_id)
);

CREATE TABLE table_meta
(

    id             INT PRIMARY KEY AUTO_INCREMENT,
    schema_meta_id INT       NOT NULL,
    name           TEXT      NOT NULL,
    type           TEXT      NOT NULL,
    comment        TEXT      NOT NULL,
    create_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_schema_meta_id (schema_meta_id)
);

CREATE TABLE table_column_meta
(

    id                INT PRIMARY KEY AUTO_INCREMENT,
    table_meta_id     INT          NOT NULL,
    schema_meta_id    INT          NOT NULL,
    name              TEXT         NOT NULL,
    type              VARCHAR(255) NOT NULL,
    comment           TEXT         NOT NULL,
    default_value     TEXT                  DEFAULT NULL,
    size              INT          NOT NULL,
    decimal_digits    INT          NOT NULL,
    is_nullable       BOOLEAN      NOT NULL,
    is_auto_increment BOOLEAN      NOT NULL,
    create_at         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_table_meta_id (table_meta_id),
    INDEX idx_schema_meta_id (schema_meta_id)
);

CREATE TABLE table_index_meta
(

    id                INT PRIMARY KEY AUTO_INCREMENT,
    table_meta_id     INT       NOT NULL,
    schema_meta_id    INT       NOT NULL,
    name              TEXT      NOT NULL,
    is_primary        BOOLEAN   NOT NULL,
    is_unique         BOOLEAN   NOT NULL,
    column_name_array JSON      NOT NULL,
    create_at         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_table_meta_id (table_meta_id),
    INDEX idx_schema_meta_id (schema_meta_id)
);

CREATE TABLE table_trigger_meta
(

    id                INT PRIMARY KEY AUTO_INCREMENT,
    table_meta_id     INT          NOT NULL,
    schema_meta_id    INT          NOT NULL,
    timing            VARCHAR(64)  NOT NULL,
    manipulation      VARCHAR(128) NOT NULL,
    statement         TEXT         NOT NULL,
    trigger_create_at VARCHAR(255) NOT NULL,
    create_at         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_table_meta_id (table_meta_id),
    INDEX idx_schema_meta_id (schema_meta_id)
);

CREATE TABLE user
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    email     TEXT         NOT NULL,
    password  TEXT         NOT NULL,
    nickname  VARCHAR(255) NOT NULL,
    avatar    VARCHAR(512)          DEFAULT NULL,
    enabled   BOOLEAN      NOT NULL DEFAULT FALSE,
    deleted   BOOLEAN      NOT NULL DEFAULT FALSE,
    update_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
);

CREATE TABLE user_role
(

    id        INT PRIMARY KEY AUTO_INCREMENT,
    user_id   INT       NOT NULL,
    role_id   INT       NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE role
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(255) NOT NULL,
    is_basic  BOOLEAN      NOT NULL DEFAULT FALSE,
    deleted   BOOLEAN      NOT NULL DEFAULT FALSE,
    update_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UNIQUE uk_name (name)
);

CREATE TABLE role_permission
(

    id            INT PRIMARY KEY AUTO_INCREMENT,
    role_id       INT       NOT NULL,
    permission_id INT       NOT NULL,
    create_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE permission
(

    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    expression VARCHAR(512) NOT NULL,
    path       VARCHAR(512) NOT NULL,
    update_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    create_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);