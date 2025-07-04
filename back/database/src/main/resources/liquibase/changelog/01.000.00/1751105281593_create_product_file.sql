--liquibase formatted sql
--changeset Maxim Dorofeev:1751105281593_create_product_file.sql

CREATE TABLE product_file
(
    id               UUID PRIMARY KEY NOT NULL,
    product_id       UUID             NOT NULL REFERENCES product (id),
    file_hash        VARCHAR(128)     NOT NULL,
    url              VARCHAR(256)     NOT NULL,
    is_thumbnail     BOOLEAN          NULL,
    create_date_time TIMESTAMPTZ      NOT NULL,
    update_date_time TIMESTAMPTZ      NOT NULL,
    version          BIGINT           NOT NULL DEFAULT 0,
    UNIQUE (product_id, file_hash)
);

CREATE INDEX product_file_is_thumbnail_idx ON product_file (is_thumbnail);
CREATE INDEX product_file_file_hash_idx ON product_file (file_hash);

-- ROLLBACK DROP TABLE product_file;

COMMENT ON TABLE product_file IS 'Таблица связей между продуктами и файлами';

COMMENT ON COLUMN product_file.id IS 'Идентификатор записи';
COMMENT ON COLUMN product_file.product_id IS 'Идентификатор продукта';
COMMENT ON COLUMN product_file.file_hash IS 'Хеш от файла';
COMMENT ON COLUMN product_file.url IS 'Относительный путь расположения до файла, включая наименования и расширение';
COMMENT ON COLUMN product_file.is_thumbnail IS 'Миниатюра для файлов типа изображений';
COMMENT ON COLUMN product_file.create_date_time IS 'Дата и время создания записи';
COMMENT ON COLUMN product_file.update_date_time IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN product_file.version IS 'Номер версии объекта';