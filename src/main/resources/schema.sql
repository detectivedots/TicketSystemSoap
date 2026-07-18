CREATE TABLE events(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(256),
    seats_left INT
);

CREATE TABLE tickets(
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    owner_name VARCHAR(256),
    booking_time TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events(id)
);