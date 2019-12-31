--------------------------------------------------

-- MYSQL Script for BookStore backend

--------------------------------------------------

--  create database
CREATE DATABASE IF NOT EXISTS bookstore;

-- create user for this bookstore specifically
CREATE USER 'bsadmin'@'localhost' IDENTIFIED BY 'bookstoreadmin';

-- grant privileges to this user
GRANT ALL PRIVILEGES ON bookstore.* TO 'bsadmin'@'localhost';



