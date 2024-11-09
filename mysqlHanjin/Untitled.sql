DROP database IF EXISTS runleaf;
CREATE DATABASE runleaf DEFAULT CHARACTER SET utf8mb4;
use runleaf;


create table board (
	id int AUTO_INCREMENT primary key,
    member_id int,
    running_data_id int,
    content varchar(300),
    created_date timestamp default current_timestamp,
    modifided_date timestamp default current_timestamp, 
    main_image_path varchar(300),
	start_longitude double default 0,
    start_latitude double default 0
);

-- boardcontroller에서 등록할때 사용하는 insert문
insert into board (
	member_id, 
    running_data_id, 
    content, 
    main_image_path,
    start_longitude,
    start_latitude
)
VALUES (
	1,
    1,
    '내용입니다',
    '메인이미지 경로입니다',
    1,
    1
),
(
	1,
    1,
    '내용입니다',
    '메인이미지 경로입니다',
    2,
    2
),
(
	1,
    1,
    '내용입니다',
    '메인이미지 경로입니다',
    3,
    3
);

select *
from board;

select id, member_id, running_data_id, content, created_date,
modifided_date, main_image_path, start_latitude, start_longitude,
ST_Distance_Sphere(
POINT(start_latitude, start_longitude),
POINT(2, 2)
) AS distance
from board
order by distance asc
limit 0, 5;

select id, member_id, running_data_id, content, created_date,
	modifided_date, main_image_path, start_latitude, start_longitude
from board
order by created_date asc
limit 0, 5;


	
ALTER USER 'ssafy'@'localhost' IDENTIFIED BY 'ssafy';
CREATE USER 'ssafy'@'localhost' IDENTIFIED BY 'ssafy';