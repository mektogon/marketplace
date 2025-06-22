--liquibase formatted sql
--changeset Maxim Dorofeev:1750347548072_change_comment_table_product_category.sql

COMMENT ON TABLE product_category IS 'Таблица связей продуктов и категорий';