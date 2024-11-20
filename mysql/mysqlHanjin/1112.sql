DROP database IF EXISTS runleaf;
CREATE DATABASE runleaf DEFAULT CHARACTER SET utf8mb4;
use runleaf;
create table running_board (
    running_board_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT NOT NULL,
    difficulty INT NOT NULL,
    start_running_ts TIMESTAMP,
    end_running_ts TIMESTAMP,
    start_latitude DOUBLE,
    start_longitude DOUBLE,
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    title VARCHAR(255),
    content VARCHAR(255),
    main_image_path VARCHAR(255),
    writer VARCHAR(50),
    on_board TINYINT(1) DEFAULT 0
);
create table running_board_image (
	running_board_image_id int AUTO_INCREMENT PRIMARY KEY,
    running_board_id int,
    ori_name varchar(300),
    system_name varchar(300),
    path varchar(300),
    FOREIGN KEY(running_board_id) REFERENCES running_board(running_board_id)
);
create table running_coordinate (
	running_coordinate_id int AUTO_INCREMENT PRIMARY KEY,
    running_board_id int,
    latitude DOUBLE,
    longitude DOUBLE,
    created_Ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(running_board_id) REFERENCES running_board(running_board_id)
);
CREATE TABLE `Member` (
	`id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`username`	varchar(30)	NOT NULL,
	`password`	varchar(100)	NOT NULL,
	`nickname`	varchar(50)	default 'default',
	`email`	varchar(50)	NOT NULL,
	`phone`	varchar(30)	NOT NULL,
    `role` varchar(20) NOT NULL
);
CREATE TABLE `member_file` (
	`id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`file_path`	varchar(30)	NOT NULL,
	`org_name`	varchar(100)	NOT NULL,
	`system_name`	varchar(255)	NOT NULL,
	`member_id`	varchar(50)	NOT NULL
);







select *
from running_board;
select *
from Member;
select *
from running_board_image;
select *
from running_coordinate;
-- ALTER USER 'ssafy'@'localhost' IDENTIFIED BY 'ssafy';
-- CREATE USER 'ssafy'@'localhost' IDENTIFIED BY 'ssafy';