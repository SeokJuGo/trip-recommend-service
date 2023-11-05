CREATE SCHEMA IF NOT EXISTS ENJOYTRIP;

-- DROP TABLES FOR INITIALIZE
DROP TABLE IF EXISTS BOARD;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS ROLE;

-- CREATE TABLE ROLE
CREATE TABLE IF NOT EXISTS ROLE (
    num 		INT 		NOT NULL PRIMARY KEY,
    auth_name 	VARCHAR(10) NOT NULL
);
INSERT INTO ROLE VALUES (1, "관리자");
INSERT INTO ROLE VALUES (2, "사용자");

-- CREATE TABLE USER
CREATE TABLE IF NOT EXISTS USER (
    id 				VARCHAR(16) NOT NULL PRIMARY KEY,
    name 			VARCHAR(40) NOT NULL,
    password 		VARCHAR(40) NOT NULL,
    email 			VARCHAR(40) NOT NULL,
    created_date 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role			INT NOT NULL DEFAULT(2),
    CONSTRAINT fk_role_to_user
		FOREIGN KEY (role)
        REFERENCES ROLE (num)
);
INSERT INTO USER (id, name, password, email, role) VALUES ("admin", "admin", 1234, "admin@ssafy.com", 1);
INSERT INTO USER (id, name, password, email, role) VALUES ("ssafy", "ssafy", 1234, "ssafy@ssafy.com", 1);
INSERT INTO USER (id, name, password, email, role) VALUES ("test1", "Test1", 1234, "test1@ssafy.com", 1);
INSERT INTO USER (id, name, password, email, role) VALUES ("test2", "Test2", 1234, "test2@ssafy.com", 1);
INSERT INTO USER (id, name, password, email, role) VALUES ("test3", "Test3", 1234, "test3@ssafy.com", 1);
INSERT INTO USER (id, name, password, email, role) VALUES ("user1", "User1", 1234, "user1@ssafy.com", 2);
INSERT INTO USER (id, name, password, email, role) VALUES ("user2", "User2", 1234, "user2@ssafy.com", 2);
INSERT INTO USER (id, name, password, email, role) VALUES ("user3", "User3", 1234, "user3@ssafy.com", 2);

-- CREATE TABLE BOARD
CREATE TABLE IF NOT EXISTS BOARD (
    id     	INT 		NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title 	VARCHAR(100) 	NULL DEFAULT NULL,
    content VARCHAR(2000) 	NULL DEFAULT NULL,
    user_id VARCHAR(16)		NULL DEFAULT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_board_to_user
        FOREIGN KEY (user_id)
        REFERENCES USER (id)
);
INSERT INTO BOARD (title, content, user_id)
VALUES ("자유 게시판 테스트 데이터 제목 001", "자유 게시판 테스트 데이터 내용 001", "admin"),
("자유 게시판 테스트 데이터 제목 002", "자유 게시판 테스트 데이터 내용 002", "ssafy"),
("자유 게시판 테스트 데이터 제목 003", "자유 게시판 테스트 데이터 내용 003", "test1"),
("자유 게시판 테스트 데이터 제목 004", "자유 게시판 테스트 데이터 내용 004", "user1"),
("자유 게시판 테스트 데이터 제목 005", "자유 게시판 테스트 데이터 내용 005", "user2");
