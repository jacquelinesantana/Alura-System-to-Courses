CREATE TABLE register (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_email VARCHAR(255) NOT NULL,
    course_code VARCHAR(255) NOT NULL,
    enrollment_date DATE NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user(email),
    FOREIGN KEY (course_code) REFERENCES course(code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;