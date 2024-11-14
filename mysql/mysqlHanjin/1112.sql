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
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


select *
from running_board;

select *
from running_board_image;
-- ALTER USER 'ssafy'@'localhost' IDENTIFIED BY 'ssafy';
-- CREATE USER 'ssafy'@'localhost' IDENTIFIED BY 'ssafy';