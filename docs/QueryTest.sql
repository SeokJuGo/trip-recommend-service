USE ENJOYTRIP;
SHOW TABLES;

DESC ATTRACTION_INFO;
DESC ATTRACTION_DETAIL;
DESC ATTRACTION_DESCRIPTION;

DESC USER;
DESC BOARD;

SELECT * FROM USER;
SELECT * FROM BOARD;

SELECT * FROM SIDO;
SELECT * FROM GUGUN;
SELECT * FROM ATTRACTION_INFO;
SELECT * FROM ATTRACTION_DETAIL;
SELECT * FROM ATTRACTION_DESCRIPTION;

-- AttractionMapper SQL Query Test
SELECT A.content_id contentId, content_type_id contentTypeId,
	title title, addr1 address, zipcode zipCode, tel,
	first_image firstImage, first_image2 secondImage, readcount readCount,
    sido_code sidoCode, gugun_code gugunCode,
	latitude, longitude, mlevel, overview
FROM ATTRACTION_INFO A JOIN ATTRACTION_DESCRIPTION B
ON A.CONTENT_ID = B.CONTENT_ID
WHERE title LIKE CONCAT('%', "test", '%');

SELECT A.content_id contentId, content_type_id contentTypeId,
	title title, addr1 address, zipcode zipCode, tel,
	first_image firstImage, first_image2 secondImage, readcount readCount,
    sido_code sidoCode, gugun_code gugunCode,
	latitude, longitude, mlevel, overview
FROM ATTRACTION_INFO A JOIN ATTRACTION_DESCRIPTION B
ON A.CONTENT_ID = B.CONTENT_ID
WHERE 1 = 1;

-- BoardMapper SQL Query Test
SELECT A.ID userId, A.NAME userName, A.EMAIL userEmail,
	B.ID id, B.TITLE title, B.CONTENT content,
	B.CREATED_DATE createdDate, B.UPDATED_DATE updatedDate
FROM USER A JOIN BOARD B
ON A.ID = B.USER_ID
WHERE A.ID = "SSAFY";