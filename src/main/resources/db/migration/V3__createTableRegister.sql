CREATE TABLE register (
    email_user VARCHAR(255) NOT NULL,
    code_course VARCHAR(255) NOT NULL,
    PRIMARY KEY (email_user, code_course),
    CONSTRAINT fk_user
        FOREIGN KEY (email_user) REFERENCES user(email)
        ON DELETE CASCADE,
    CONSTRAINT fk_course
        FOREIGN KEY (code_course) REFERENCES course(code)
        ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;