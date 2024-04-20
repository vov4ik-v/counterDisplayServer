CREATE TABLE if not exists device
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial_number BIGINT NOT NULL UNIQUE,
    cantora_name VARCHAR(255) default null,
    is_calibrated BOOL NOT NULL,
    counter_type INT default null,
    city VARCHAR(255) default null,
    number VARCHAR(255) default null,
    region VARCHAR(255) default null,
    street VARCHAR(255) default null,
    user_id BIGINT default null,
    FOREIGN KEY fk_user_id (user_id) REFERENCES user (id),
    INDEX idx_user_id (user_id)
);
