--liquibase formatted sql
--changeset Maxim Dorofeev:1748682057805_insert_tshirt_to_category_table.sql

INSERT INTO category
VALUES ('ac953672-f82e-46a8-a7b7-ca6152d355ef', 't_shirt', 'Футболки', true, 1, current_timestamp, current_timestamp);