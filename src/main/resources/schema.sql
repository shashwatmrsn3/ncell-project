CREATE TABLE numbers (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         phone_number BIGINT,
                         UNIQUE (phone_number)
);

CREATE TABLE otp (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     otp INT,
                     phone_number BIGINT,
                     expiration_timestamp TIMESTAMP,
                     is_used BOOLEAN,
                     FOREIGN KEY (phone_number) REFERENCES numbers(phone_number)
);
