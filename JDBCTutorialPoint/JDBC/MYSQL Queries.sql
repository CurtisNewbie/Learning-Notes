/* create database */
CREATE DATABASE hiber;

/* create new userand assign privileges */
GRANT ALL PRIVILEGES ON hiber.* TO 'hiber'@'localhose' IDENTIFIED BY 'hiberPW';

/* create tables */
CREATE TABLE customer
(
firstName VARCHAR(30),
lastName VARCHAR(30),
custID INT(10) UNSIGNED AUTO_INCREMENT,
PRIMARY KEY (custID)
);

CREATE TABLE image
(
img_no INT(5) UNSIGNED AUTO_INCREMENT,
img_data MEDIUMBLOB NOT NULL,
PRIMARY KEY (img_no)
);
