DROP TABLE `Member`;

CREATE TABLE `Member` (
	`id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`username`	varchar(30)	NOT NULL,
	`password`	varchar(100)	NOT NULL,
	`nickname`	varchar(50)	NOT NULL,
	`email`	varchar(50)	NOT NULL,
	`phone`	varchar(30)	NOT NULL,
	`member_file`	varchar(50)	NULL
);


CREATE TABLE `member_file` (
	`id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`file_path`	varchar(30)	NOT NULL,
	`org_name`	varchar(100)	NOT NULL,
	`system_name`	varchar(50)	NOT NULL,
	`member_id`	varchar(50)	NOT NULL
);