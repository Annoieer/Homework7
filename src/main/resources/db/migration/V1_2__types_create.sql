create table if not exists product_types (
id bigserial primary key,
name varchar(255) unique
);