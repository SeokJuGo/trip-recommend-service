CREATE SCHEMA IF NOT EXISTS ENJOYTRIP;

-- DROP TABLES FOR INITIALIZE
DROP TABLE IF EXISTS BOARD;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS USER_ROLE;

/* CREATE TABLES ABOUT USER AND INSERT SAMPLE DATA INTO USER RELATED TABLES */

-- CREATE TABLE USER
CREATE TABLE IF NOT EXISTS USER (
	id				INT AUTO_INCREMENT PRIMARY KEY,
    username 		VARCHAR(50) 	NOT NULL UNIQUE,
    password 		VARCHAR(255)	NOT NULL,
    nickname		VARCHAR(100) 	NOT NULL,
    email 			VARCHAR(100) 	NOT NULL UNIQUE,
    created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO USER (username, password, nickname, email) VALUES
("admin", "1234", "NickName_Admin", "admin@ssafy.com"),
("ssafy", "1234", "NickName_SSAFY", "ssafy@ssafy.com"),
("test1", "1234", "NickName_Test1", "test1@ssafy.com"),
("test2", "1234", "NickName_Test2", "test2@ssafy.com"),
("test3", "1234", "NickName_Test3", "test3@ssafy.com"),
("user1", "1234", "NickName_User1", "user1@ssafy.com"),
("user2", "1234", "NickName_User2", "user2@ssafy.com"),
("user3", "1234", "NickName_User3", "user3@ssafy.com");

-- CREATE TABLE ROLE
CREATE TABLE IF NOT EXISTS ROLE (
    id 				INT AUTO_INCREMENT PRIMARY KEY,
    name 			VARCHAR(50) NOT NULL UNIQUE,
    description		VARCHAR(255),
	created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO ROLE (id, name) VALUE (1, "관리자");
INSERT INTO ROLE (id, name) VALUE (2, "사용자");
INSERT INTO ROLE (id, name) VALUE (3, "비회원");

CREATE TABLE IF NOT EXISTS USER_ROLE (
	id		INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    role_id INT,
	created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USER (id),
    FOREIGN KEY (role_id) REFERENCES ROLE (id)
);
INSERT INTO USER_ROLE (user_id, role_id) VALUE (1, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUE (2, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUE (3, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUE (4, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUE (5, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUE (6, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUE (7, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUE (8, 3);

/* CREATE TABLES ABOUT BOARD AND INSERT SAMPLE DATA INTO BOARD RELATED TABLES */

-- CREATE TABLE CATEGORY
CREATE TABLE IF NOT EXISTS CATEGORY (
    id 				INT AUTO_INCREMENT PRIMARY KEY,
    name			VARCHAR(255) NOT NULL UNIQUE,
	created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO CATEGORY (id, name) VALUES (1, 'BOARD');
INSERT INTO CATEGORY (id, name) VALUES (2, 'PLAN');
INSERT INTO CATEGORY (id, name) VALUES (3, 'HOTPLACE');

-- CREATE TABLE BOARD
CREATE TABLE IF NOT EXISTS BOARD (
    id 				INT 			AUTO_INCREMENT PRIMARY KEY,
    title 			VARCHAR(255) 	NOT NULL,
    content			TEXT 			NOT NULL,
    created_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date 	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id			INT,
    category_id		INT,
    CONSTRAINT FK_board_user
		FOREIGN KEY (user_id)
        REFERENCES USER (id),
	CONSTRAINT FK_board_category
		FOREIGN KEY (category_id)
        REFERENCES CATEGORY (id)
);
INSERT INTO BOARD (title, content, user_id, category_id) VALUES
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
