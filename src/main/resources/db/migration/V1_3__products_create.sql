create table if not exists products (
id bigserial primary key,
account varchar(255) unique,
balance bigserial,
product_type_id bigserial,
user_id bigserial,
foreign key (product_type_id) references product_types (id),
foreign key (user_id) references users (id)
);