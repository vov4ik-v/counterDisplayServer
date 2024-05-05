INSERT INTO user (username, email, password, phone_number, image_url, street, city, number, region, first_name, last_name, created_date)
VALUES
    ('user1', 'user1@example.com', '$2a$10$NOtTKqjYpHODvTIqARjTQ.nJTh1mss/zpr47/qlu76o3CrCjZSO7.', '+123456789', 'http://example.com/image1.jpg', 'Street1', 'City1', 123, 'Region1', 'John', 'Doe', NOW()),
    ('user2', 'user2@example.com', '$2a$10$NOtTKqjYpHODvTIqARjTQ.nJTh1mss/zpr47/qlu76o3CrCjZSO7.', '+987654321', 'http://example.com/image2.jpg', 'Street2', 'City2', 456, 'Region2', 'Jane', 'Smith', NOW()),
    ('user3', 'user3@example.com', '$2a$10$NOtTKqjYpHODvTIqARjTQ.nJTh1mss/zpr47/qlu76o3CrCjZSO7.', '+111222333', 'http://example.com/image3.jpg', 'Street3', 'City3', 789, 'Region3', 'Alice', 'Johnson', NOW()),
    ('user4', 'user4@example.com', '$2a$10$NOtTKqjYpHODvTIqARjTQ.nJTh1mss/zpr47/qlu76o3CrCjZSO7.', '+444555666', 'http://example.com/image4.jpg', 'Street4', 'City4', 321, 'Region4', 'Bob', 'Brown', NOW());
