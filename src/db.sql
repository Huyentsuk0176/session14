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
delimiter $$
create procedure update_student(
    in in_id int,
    in in_name varchar(100),
    in in_age int
)
begin
update students
set name = in_name,
    age = in_age
where id = in_id;
end $$
delimiter ;


CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

-- ===== TABLE =====
DROP TABLE IF EXISTS students;
CREATE TABLE students (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          age INT NOT NULL CHECK (age >= 0)
);

-- ===== ADD STUDENT =====
DROP PROCEDURE IF EXISTS add_students;
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

-- ===== UPDATE STUDENT =====
DROP PROCEDURE IF EXISTS update_student;
DELIMITER //

CREATE PROCEDURE update_student(
    IN in_id INT,
    IN in_name VARCHAR(100),
    IN in_age INT
)
BEGIN
UPDATE students
SET name = in_name,
    age = in_age
WHERE id = in_id;
END //

DELIMITER ;

-- ===== DELETE STUDENTS BY AGE =====
DROP PROCEDURE IF EXISTS delete_students_by_age;
DELIMITER //

CREATE PROCEDURE delete_students_by_age(
    IN in_age INT
)
BEGIN
DELETE FROM students
WHERE age < in_age;
END //

DELIMITER ;

-- ===== INSERT DATA TEST =====
INSERT INTO students(name, age)
VALUES
    ('nguyen van a', 19),
    ('tran thi b', 20),
    ('le van c', 22),
    ('pham thi huyen', 18);

SELECT * FROM students;

-- ===== DELETE TEST =====
SET SQL_SAFE_UPDATES = 0;
CALL delete_students_by_age(20);

SELECT * FROM students;
