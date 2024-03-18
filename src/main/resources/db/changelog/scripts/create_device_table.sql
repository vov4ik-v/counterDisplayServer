CREATE TABLE if not exists device
(
    number_of_device BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantora_name    VARCHAR(255) default null,
    counter_type    INT  default null,
    address        VARCHAR(255)  default null,
    frequency      BIGINT  default null,
    password       VARCHAR(255)  default null,
    price          INT  default null,
    user_id        BIGINT  default null,
    created_date    VARCHAR(255) NOT NULL,
    FOREIGN KEY fk_user_id (user_id) REFERENCES user(id),
    INDEX idx_user_id (user_id)
);
