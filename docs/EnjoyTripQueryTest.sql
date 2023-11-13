CREATE SCHEMA IF NOT EXISTS ENJOYTRIP;

-- DROP TABLES FOR INITIALIZE
DROP TABLE IF EXISTS BOARD;
DROP TABLE IF EXISTS BOARD_TYPE;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS ROLE;


/* CREATE TABLES AND INSERT SAMPLE DATA RELATED TO USER */


-- CREATE TABLE ROLE
CREATE TABLE IF NOT EXISTS ROLE (
    id 				INT AUTO_INCREMENT PRIMARY KEY,
    name 			VARCHAR(50) NOT NULL UNIQUE,
    description		VARCHAR(255),
	created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO ROLE (id, name, description) VALUE (1, "ADMIN", "관리자");
INSERT INTO ROLE (id, name, description) VALUE (2, "USER", "사용자");
INSERT INTO ROLE (id, name, description) VALUE (3, "GUEST", "비회원");

-- CREATE TABLE USER
CREATE TABLE IF NOT EXISTS USER (
	id				INT AUTO_INCREMENT PRIMARY KEY,
    username 		VARCHAR(50) 	NOT NULL UNIQUE,
    password 		VARCHAR(255)	NOT NULL,
    nickname		VARCHAR(100) 	NOT NULL,
    email 			VARCHAR(100) 	NOT NULL UNIQUE,
    created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    role_id			INT DEFAULT 2,
    CONSTRAINT		FK_USER_ROLE
		FOREIGN KEY (role_id) REFERENCES ROLE (id)
);
INSERT INTO USER (username, password, nickname, email, role_id) VALUES
("admin", "1234", "NickName_Admin", "admin@ssafy.com", 1),
("ssafy", "1234", "NickName_SSAFY", "ssafy@ssafy.com", 1),
("test1", "1234", "NickName_Test1", "test1@ssafy.com", 2),
("test2", "1234", "NickName_Test2", "test2@ssafy.com", 2),
("test3", "1234", "NickName_Test3", "test3@ssafy.com", 3),
("user1", "1234", "NickName_User1", "user1@ssafy.com", 2),
("user2", "1234", "NickName_User2", "user2@ssafy.com", 2),
("user3", "1234", "NickName_User3", "user3@ssafy.com", 3);


/* CREATE TABLES AND INSERT SAMPLE DATA RELATED TO BOARD */


-- CREATE TABLE BOARD TYPE
CREATE TABLE IF NOT EXISTS BOARD_TYPE (
    id 				INT AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(255) NOT NULL UNIQUE,
	description		VARCHAR(255),
	created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO BOARD_TYPE (id, name, description) VALUES (1, 'BOARD', '일반 게시판');
INSERT INTO BOARD_TYPE (id, name, description) VALUES (2, 'PLAN', '여행계획 게시판');
INSERT INTO BOARD_TYPE (id, name, description) VALUES (3, 'HOTPLACE', '핫플레이스 게시판');

-- CREATE TABLE BOARD
CREATE TABLE IF NOT EXISTS BOARD (
    id 				INT AUTO_INCREMENT PRIMARY KEY,
    title 			VARCHAR(255) 	NOT NULL,
    content			TEXT 			NOT NULL,
    created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id			INT,
    board_type_id	INT,
    CONSTRAINT FK_BOARD_USER
		FOREIGN KEY (user_id)
        REFERENCES USER (id),
	CONSTRAINT FK_BOARD_BOARD_TYPE
		FOREIGN KEY (board_type_id)
        REFERENCES BOARD_TYPE (id)
);
INSERT INTO BOARD (title, content, user_id, board_type_id) VALUES
("Board Title Sample Data 001", "Board Content Sample Data 001", 1, 1),
("Board Title Sample Data 002", "Board Content Sample Data 002", 2, 2),
("Board Title Sample Data 003", "Board Content Sample Data 003", 3, 3),
("Board Title Sample Data 004", "Board Content Sample Data 004", 4, 1),
("Board Title Sample Data 005", "Board Content Sample Data 005", 6, 2),
("Board Title Sample Data 006", "Board Content Sample Data 006", 7, 3),
("Board Title Sample Data 007", "Board Content Sample Data 007", 1, 1),
("Board Title Sample Data 008", "Board Content Sample Data 008", 2, 2),
("Board Title Sample Data 009", "Board Content Sample Data 009", 3, 3),
("Board Title Sample Data 010", "Board Content Sample Data 010", 4, 1),
("Board Title Sample Data 011", "Board Content Sample Data 011", 6, 2),
("Board Title Sample Data 012", "Board Content Sample Data 012", 7, 3);
