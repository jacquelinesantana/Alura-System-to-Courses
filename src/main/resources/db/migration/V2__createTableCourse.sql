CREATE TABLE Course(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    
    name varchar(50) NOT NULL,
    code varchar(10) NOT NULL,
    description varchar(1000),
    instructorEmail varchar(50) NOT NULL,
    statusCourse ENUM('ACTIVE', 'INACTIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE', 
    inactiveDate datetime, 
    PRIMARY KEY (id), 
    FOREIGN KEY (instructorEmail) REFERENCES User(email),
    CONSTRAINT UC_Code UNIQUE (code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;