CREATE DATABASE bankingapp;

use bankingapp;

CREATE TABLE user(
    user_id BIGINT(12) PRIMARY KEY,
    name_with_initials VARCHAR(40),
    address VARCHAR(100));

CREATE TABLE account(
    account_no INT(10) AUTO_INCREMENT PRIMARY KEY,
    account_type CHAR,
    balance DOUBLE(9,2),
    user_id BIGINT(12),
    FOREIGN KEY (user_id) REFERENCES user(user_id));

CREATE TABLE authenticate(
    username VARCHAR(15) PRIMARY KEY,
    password VARCHAR(20),
    user_id BIGINT(12),
    FOREIGN KEY (user_id) REFERENCES user(user_id));

INSERT INTO user
    VALUES (000000000001, 'admin', 'admin', '2025-05-06');

INSERT INTO account
    VALUES(10000000, 'S', 0, 000000000001);