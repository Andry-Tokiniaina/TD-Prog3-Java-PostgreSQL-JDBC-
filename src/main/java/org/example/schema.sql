CREATE TABLE Product(
    id int PRIMARY KEY,
    name varchar,
    price numeric,
    creation_datetime timestamp
);
CREATE TABLE Product_category (
    id int,
    name varchar,
    product_id int,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES Product(id)
);