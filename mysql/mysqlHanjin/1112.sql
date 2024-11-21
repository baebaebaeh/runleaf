DROP database IF EXISTS runleaf;
CREATE DATABASE runleaf DEFAULT CHARACTER SET utf8mb4;
use runleaf;
-- 1. Member 테이블 생성
CREATE TABLE member (
    member_id int AUTO_INCREMENT PRIMARY KEY,
    username varchar(30) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    phone varchar(30) NOT NULL,
    role varchar(20) NOT NULL
);

-- 2. MemberFile 테이블 생성
CREATE TABLE member_file (
    member_file_id int AUTO_INCREMENT PRIMARY KEY,
    file_path varchar(100) NOT NULL,
    org_name varchar(30) NULL,
    system_name varchar(50) NULL,
    member_id INT
);

-- 3. 외래 키 제약 조건 추가
ALTER TABLE member_file
ADD CONSTRAINT fk_member_file_member FOREIGN KEY (member_id) REFERENCES member(member_id);



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
CREATE TABLE Comment (
    comment_id INT NOT NULL AUTO_INCREMENT,
    member_id INT NOT NULL,
    parent_id INT NULL,
    running_board_id INT NOT NULL,
    content VARCHAR(255) NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifided_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    writer VARCHAR(20) NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (member_id) REFERENCES Member(member_id),
    FOREIGN KEY (running_board_id) REFERENCES running_board(running_board_id),
    FOREIGN KEY (parent_id) REFERENCES Comment(comment_id)
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