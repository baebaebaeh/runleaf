-- 1. Member 테이블 생성
CREATE TABLE member (
    member_id int AUTO_INCREMENT PRIMARY KEY,
    username varchar(30) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    phone varchar(30) NOT NULL,
    role varchar(20) NOT NULL
);
drop table member_file;


-- 2. MemberFile 테이블 생성
CREATE TABLE member_file (
    member_file_id int AUTO_INCREMENT PRIMARY KEY,
    file_path varchar(100) NOT NULL,
    org_name varchar(50) NULL,
    system_name varchar(100) NULL,
    member_id INT
);

-- 3. 외래 키 제약 조건 추가
ALTER TABLE member_file
ADD CONSTRAINT fk_member_file_member FOREIGN KEY (member_id) REFERENCES member(member_id);