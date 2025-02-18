CREATE TABLE IF NOT EXISTS reservations (
    id INT NOT NULL AUTO_INCREMENT,
    guest_name VARCHAR(255),
    date_from DATE,
    date_to DATE,
    is_deleted BOOLEAN DEFAULT 0,
    PRIMARY KEY (id)
);
