--liquibase formatted sql
--changeset Maxim Dorofeev:1749889831106_create_tag_table.sql

CREATE TABLE tag
(
    id               UUID PRIMARY KEY    NOT NULL,
    code             VARCHAR(128) UNIQUE NOT NULL,
    display_name     VARCHAR(128)        NOT NULL,
    is_visible       BOOLEAN             NOT NULL DEFAULT false,
    create_date_time TIMESTAMPTZ         NOT NULL,
    update_date_time TIMESTAMPTZ         NOT NULL,
    version          BIGINT              NOT NULL DEFAULT 0
);

-- ROLLBACK DROP TABLE tag;

COMMENT ON TABLE tag IS 'Таблица с тегами';

COMMENT ON COLUMN tag.id IS 'Идентификатор записи';
COMMENT ON COLUMN tag.code IS 'Код тега';
COMMENT ON COLUMN tag.display_name IS 'Наименование тега';
COMMENT ON COLUMN tag.is_visible IS 'Отображение продукта';
COMMENT ON COLUMN tag.create_date_time IS 'Дата и время создания записи';
COMMENT ON COLUMN tag.update_date_time IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN tag.version IS 'Номер версии объекта';