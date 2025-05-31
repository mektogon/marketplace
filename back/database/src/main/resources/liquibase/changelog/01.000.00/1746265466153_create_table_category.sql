--liquibase formatted sql
--changeset Maxim Dorofeev:1746265466153_create_table_category.sql

CREATE TABLE category
(
    id               UUID PRIMARY KEY    NOT NULL,
    code             VARCHAR(128) UNIQUE NOT NULL,
    display_name     VARCHAR(128)        NOT NULL,
    is_visible       BOOLEAN             NOT NULL DEFAULT false,
    priority         SMALLINT            NOT NULL,
    create_date_time TIMESTAMPTZ         NOT NULL,
    update_date_time TIMESTAMPTZ         NOT NULL,
    version          BIGINT              NOT NULL DEFAULT 0
);

CREATE INDEX category_is_visible_idx ON category (is_visible);

-- ROLLBACK DROP TABLE category;

COMMENT ON TABLE category IS 'Таблица с категориями';

COMMENT ON COLUMN category.id IS 'Идентификатор записи';
COMMENT ON COLUMN category.code IS 'Код категории';
COMMENT ON COLUMN category.display_name IS 'Наименование категории';
COMMENT ON COLUMN category.priority IS 'Приоритет категории';
COMMENT ON COLUMN category.is_visible IS 'Отображение категории';
COMMENT ON COLUMN category.create_date_time IS 'Дата и время создания записи';
COMMENT ON COLUMN category.update_date_time IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN category.version IS 'Номер версии объекта';