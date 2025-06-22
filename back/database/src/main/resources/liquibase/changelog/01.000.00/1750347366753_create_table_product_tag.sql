--liquibase formatted sql
--changeset Maxim Dorofeev:1750347366753_create_table_product_tag.sql


CREATE TABLE product_tag
(
    id               UUID PRIMARY KEY NOT NULL,
    product_id       UUID             NOT NULL REFERENCES product (id),
    tag_id           UUID             NOT NULL REFERENCES tag (id),
    create_date_time TIMESTAMPTZ      NOT NULL,
    update_date_time TIMESTAMPTZ      NOT NULL,
    version          BIGINT           NOT NULL DEFAULT 0,
    UNIQUE (product_id, tag_id)
);

CREATE INDEX product_tag_tag_id_idx ON product_tag (tag_id);

-- ROLLBACK DROP TABLE product_tag;

COMMENT ON TABLE product_tag IS 'Таблица связей между продуктами и категориям';

COMMENT ON COLUMN product_tag.id IS 'Идентификатор записи';
COMMENT ON COLUMN product_tag.product_id IS 'Идентификатор продукта';
COMMENT ON COLUMN product_tag.tag_id IS 'Идентификатор тега';
COMMENT ON COLUMN product_tag.create_date_time IS 'Дата и время создания записи';
COMMENT ON COLUMN product_tag.update_date_time IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN product_tag.version IS 'Номер версии объекта';