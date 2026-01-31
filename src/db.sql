CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

CREATE TABLE students (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100),
                          age INT
);

DELIMITER //

CREATE PROCEDURE add_students(
    IN in_name VARCHAR(100),
    IN in_age INT
)
BEGIN
INSERT INTO students(name, age)
VALUES (in_name, in_age);
END //

DELIMITER ;
