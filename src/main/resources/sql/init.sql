DROP DATABASE IF EXISTS warehouse;
CREATE DATABASE warehouse;

\c warehouse;

CREATE TABLE t_products
(
    id SERIAL NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT,
    product_price DECIMAL(10, 2) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE t_roles
(
    id SERIAL NOT NULL,
    role_name VARCHAR(255) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE t_users
(
    id SERIAL NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    user_password VARCHAR(256),
    role_id INTEGER REFERENCES t_roles(id),
	PRIMARY KEY(id)
);