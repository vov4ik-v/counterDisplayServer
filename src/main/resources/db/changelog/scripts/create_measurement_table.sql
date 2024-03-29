CREATE TABLE IF not exists measurement(
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    created_date VARCHAR(255) default null,
    display_count BIGINT default null,
    device_id BIGINT NOT NULL,
    FOREIGN KEY fk_device_id (device_id) REFERENCES device(number_of_device),
    INDEX idx_device_id (device_id)

)