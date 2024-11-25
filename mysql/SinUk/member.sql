drop table member;
drop table member_file;

-- 1. Member 테이블 생성
CREATE TABLE member (
    member_id int AUTO_INCREMENT PRIMARY KEY,
    username varchar(30) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    phone varchar(20) NOT NULL,
    total_dist DOUBLE DEFAULT 0,          
    total_running_second DOUBLE DEFAULT 0,
    role varchar(20) NOT NULL
);



-- 2. MemberFile 테이블 생성
CREATE TABLE member_file (
    member_file_id int AUTO_INCREMENT PRIMARY KEY,
    file_path varchar(100) NOT NULL,
    org_name varchar(50) NULL,
    system_name varchar(100) NULL,
    member_id INT
);

-- 3. Follow 테이블 생성
CREATE TABLE follow (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    follower_id INT NOT NULL,
    following_id INT NOT NULL,
    UNIQUE KEY unique_follow (follower_id, following_id)
);

-- 4. 외래 키 제약 조건 추가
ALTER TABLE member_file
ADD CONSTRAINT fk_member_file_member FOREIGN KEY (member_id) REFERENCES member(member_id);