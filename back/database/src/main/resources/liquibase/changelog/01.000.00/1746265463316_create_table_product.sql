--liquibase formatted sql
--changeset Maxim Dorofeev:1746265463316_create_table_product.sql

CREATE TABLE product
(
    id               UUID PRIMARY KEY NOT NULL,
    display_name     VARCHAR(256)     NOT NULL,
    description      VARCHAR(3072)    NULL,
    is_visible       BOOLEAN          NOT NULL DEFAULT false,
    create_date_time TIMESTAMPTZ      NOT NULL,
    update_date_time TIMESTAMPTZ      NOT NULL,
    version          BIGINT           NOT NULL DEFAULT 0
);

CREATE INDEX product_is_visible_idx ON product (is_visible);

-- ROLLBACK DROP TABLE product;

COMMENT ON TABLE product IS 'Таблица с продуктами';

COMMENT ON COLUMN product.id IS 'Идентификатор записи';
COMMENT ON COLUMN product.display_name IS 'Наименование продукта';
COMMENT ON COLUMN product.description IS 'Описание продукта';
COMMENT ON COLUMN product.is_visible IS 'Отображение продукта';
COMMENT ON COLUMN product.create_date_time IS 'Дата и время создания записи';
COMMENT ON COLUMN product.update_date_time IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN product.version IS 'Номер версии объекта';