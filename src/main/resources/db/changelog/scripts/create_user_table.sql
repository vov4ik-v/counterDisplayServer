CREATE TABLE IF not exists user(
    id BIGINT PRIMARY KEY  NOT NULL  AUTO_INCREMENT,
    city VARCHAR(255) default null,
    number VARCHAR(255) default null,
    region VARCHAR(255) default null,
    street VARCHAR(255) default null,
    avatar_id INT default null,
    created_date DATETIME(6) default null,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(3000) default null,
    phone_number VARCHAR(255) default null,
    username VARCHAR(255) not null unique,
    first_name VARCHAR(255) default null,
    last_name VARCHAR(255) default null
)