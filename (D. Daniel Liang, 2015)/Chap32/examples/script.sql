CREATE DATABASE javabook;
USE javabook;

-- Create table Course
CREATE TABLE Course(
    courseId CHAR(5),
    subjectId CHAR(4) NOT NULL, 
    courseNumber INTEGER, 
    title VARCHAR(50) NOT NULL,
    numOfCredits INTEGER,
    PRIMARY KEY (courseId)
);

-- Create table Student
CREATE TABLE Student(
    studentId CHAR(5),
    firstName VARCHAR(25),
    lastName VARCHAR(25),
    birthDate DATE,
    street VARCHAR(25),
    phone CHAR(11),
    zipCode CHAR(5),
    deptId CHAR(4),
    PRIMARY KEY (studentId)
);

-- Create table Enrollment
CREATE TABLE Enrollment(
    studentId CHAR(5),
    courseId CHAR(5),
    dataRegistered DATE,
    grade CHAR(1),
    PRIMARY KEY (studentId, courseId),
    FOREIGN KEY (studentId) REFERENCES Student (studentId),
    FOREIGN KEY (courseId) REFERENCES Course (courseId)
);

CREATE USER 'demouser'@'localhost' IDENTIFIED BY 'demopw';
GRANT ALL PRIVILEGES ON javabook.* TO 'demouser'@'localhost';