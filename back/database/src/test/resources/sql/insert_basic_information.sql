INSERT INTO category
VALUES ('8da503df-3308-4e52-9535-ab9c3dd8f2a4', 'category-1', 'Категория-1 isVisible = true', true, 1,
        current_timestamp,
        current_timestamp),
       ('8c26f51a-015b-419b-ae18-b0e31659012a', 'category-2', 'Категория-2 isVisible = false', false, 1,
        current_timestamp, current_timestamp),
       ('a557ada1-6b9b-4aea-8edd-7c355f0e0444', 'category-3', 'Категория-3 isVisible = true', true, 1,
        current_timestamp,
        current_timestamp);

INSERT INTO product
VALUES ('0d085d2b-5689-4ddf-af39-d356855c69f3', 'Футболка-1', 'isVisible = true и category-1', true, current_timestamp,
        current_timestamp),
       ('6308c8e0-183c-4c50-af8d-159e1025839b', 'Футболка-2', 'isVisible = false и category-1', false, current_timestamp,
        current_timestamp),
       ('f92ff989-145c-42d6-95d2-541fd418a8c2', 'Футболка-3', 'isVisible = true и category-3', true, current_timestamp,
        current_timestamp),
       ('5c97fd07-2807-4fb9-ba2c-4a2dc952b7b6', 'Футболка-4', 'isVisible = true и без категории', true,
        current_timestamp,
        current_timestamp);

INSERT INTO product_category
VALUES ('7b84237d-47e0-45ba-b7e6-5a21798b7dd3', '0d085d2b-5689-4ddf-af39-d356855c69f3',
        '8da503df-3308-4e52-9535-ab9c3dd8f2a4', current_timestamp, current_timestamp), -- Футболка-1 + category-1
       ('e31f5d42-224d-422d-9d1b-2b2817721e59', '6308c8e0-183c-4c50-af8d-159e1025839b',
        '8da503df-3308-4e52-9535-ab9c3dd8f2a4', current_timestamp, current_timestamp), -- Футболка-2 + category-1
       ('6cdd36bb-e9de-408c-a438-11f31bb4ac9b', 'f92ff989-145c-42d6-95d2-541fd418a8c2',
        'a557ada1-6b9b-4aea-8edd-7c355f0e0444', current_timestamp, current_timestamp); -- Футболка-3 + category-3

INSERT INTO tag
VALUES ('8c3fa7b2-f0bf-43ac-acdb-5ea44044e919', 'tag-1', 'tag-1', true, current_timestamp, current_timestamp),
       ('86d00335-86a0-4b03-a952-f5e4b05db3cc', 'tag-2', 'tag-2', false, current_timestamp, current_timestamp),
       ('aec32727-4077-47e7-92dd-359efcda7abf', 'tag-3', 'tag-3', true, current_timestamp, current_timestamp);

INSERT INTO product_tag
VALUES ('d161e6e8-c8ff-41df-8f5f-65eebf565708', '0d085d2b-5689-4ddf-af39-d356855c69f3',
        '8c3fa7b2-f0bf-43ac-acdb-5ea44044e919', current_timestamp, current_timestamp), -- Футболка-1 + tag-1
       ('1432fcfb-3276-4709-bdf4-7980500d4a41', '0d085d2b-5689-4ddf-af39-d356855c69f3',
        '86d00335-86a0-4b03-a952-f5e4b05db3cc', current_timestamp, current_timestamp); -- Футболка-1 + tag-2

