CREATE TABLE Course(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    
    name varchar(50) NOT NULL,
    code varchar(10) NOT NULL,
    description varchar(1000),
    instructorEmail varchar(50) NOT NULL,
    statusCourse ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE', 
    inactiveDate datetime, 
    PRIMARY KEY (id), 
    FOREIGN KEY (instructorEmail) REFERENCES User(email),
    CONSTRAINT UC_Code UNIQUE (code)