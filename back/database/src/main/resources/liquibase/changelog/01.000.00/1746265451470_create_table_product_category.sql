--liquibase formatted sql
--changeset Maxim Dorofeev:1746265451470_create_table_product_category.sql

CREATE TABLE product_category
(
    id               UUID PRIMARY KEY NOT NULL,
    product_id       UUID             NOT NULL REFERENCES product (id),
    category_id      UUID             NOT NULL REFERENCES category (id),
    create_date_time TIMESTAMPTZ      NOT NULL,
    update_date_time TIMESTAMPTZ      NOT NULL,
    version          BIGINT           NOT NULL DEFAULT 0,
    UNIQUE (product_id, category_id)
);

CREATE INDEX product_category_category_id_idx ON product_category (category_id);

-- ROLLBACK DROP TABLE product_category;

COMMENT ON TABLE product_category IS 'Таблица с продуктами';

COMMENT ON COLUMN product_category.id IS 'Идентификатор записи';
COMMENT ON COLUMN product_category.product_id IS 'Идентификатор продукта';
COMMENT ON COLUMN product_category.category_id IS 'Идентификатор категории';
COMMENT ON COLUMN product_category.create_date_time IS 'Дата и время создания записи';
COMMENT ON COLUMN product_category.update_date_time IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN product_category.version IS 'Номер версии объекта';