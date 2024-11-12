DROP database IF EXISTS runleaf;
CREATE DATABASE runleaf DEFAULT CHARACTER SET utf8mb4;
use runleaf;


create table board (
	board_id int AUTO_INCREMENT primary key,
    member_id int,
    running_data_id int,
    content varchar(300),
    created_date timestamp default current_timestamp,
    modifided_date timestamp default current_timestamp, 
    main_image_path varchar(300),
    writer varchar(300)
);

create table running_data (
	running_data_id int AUTO_INCREMENT PRIMARY KEY,
    member_id int,
    difficulty int,
    running_time varchar(300),
    start_latitude varchar(300),
    start_longitude varchar(300),
    created_time timestamp default current_timestamp, 
    evaluation varchar(300),
    one_line_comment varchar(300)
);

insert into running_data (
    member_id ,
    difficulty ,
    running_time,
    start_latitude,
    start_longitude ,
    one_line_comment,
    evaluation
    
) values (
	1,
    5,
    '1분',
    3, 
    3,
    '좋습니다',
    '최고'
),(
	2,
    5,
    '3분',
    3, 
    3,
    '싫습니다',
    '최악'
);

-- boardcontroller에서 등록할때 사용하는 insert문
insert into board (
	member_id, 
    running_data_id, 
    content, 
    main_image_path,
    writer
)
VALUES (
	1,
    1,
    '내용입니다',
    '메인이미지 경로입니다',
    '배한진'
),
(
	2,
    2,
    '내용입니다',
    '메인이미지 경로입니다',
    '이신욱'
),
(
	3,
    3,
    '내용입니다',
    '메인이미지 경로입니다',
    '유승호'
);

select *
from board;

select *
from running_data;

select board_id, member_id, running_data_id, content, created_date,
modifided_date, main_image_path, now_latitude, now_longitude,
ST_Distance_Sphere(
POINT(now_latitude, now_longitude),
POINT(2, 2)
) AS distance
from board
order by distance asc
limit 0, 5;

select board_id, member_id, running_data_id, content, created_date,
	modifided_date, main_image_path, now_latitude, now_longitude
from board
order by created_date asc
limit 0, 5;

SELECT 	b.board_id AS board_id, 
		b.member_id AS board_member_id,
		b.running_data_id AS running_data_id,
		b.content, 
		b.created_date,
		b.modifided_date, 
		b.main_image_path, 
		b.now_latitude, 
		b.now_longitude,
		r.difficulty,
		r.running_time,
		r.evaluation,	
		r.one_line_comment AS one_line_comment,
	ST_Distance_Sphere(
		POINT(b.now_longitude, b.now_latitude),
		POINT(r.start_longitude, r.start_latitude)
		) AS distance
FROM board b
JOIN running_data r on b.running_data_id = r.running_data_id
order by distance asc
limit 0, 5;
	
-- ALTER USER 'ssafy'@'localhost' IDENTIFIED BY 'ssafy';
-- CREATE USER 'ssafy'@'localhost' IDENTIFIED BY 'ssafy';