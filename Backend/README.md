# EnjoyTrip_Framework_서울_15반 조시훈   고석주

---

## :one: 필수

### 1. 메인화면

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/5ed0be72-e402-4224-a43b-e8646211edea)

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/887e9d77-4d41-4297-bb67-1c795fd7429d)
![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/8b8f2f75-95e0-45a3-9f00-84e15e6d6438)

- ReadCount 내림차순 순으로 메인화면 출력

### 2. 회원관리 로그인

![](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/123c8fe9-44ea-4223-b45b-bc59ed821d43)

- **1) 회원전체목록**

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/2596ae36-f139-40d9-9a3d-88bf70fec527)

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/d1818b54-752a-46e6-a5e8-9f2f01e70075)

- **회원등록**

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/f0d526c9-a090-4728-8824-e40ab885951b)

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/dc34c576-9613-47de-b8f8-f08a51bb60a6)

- **회원 수정**

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/934503b6-d2eb-44f9-89e5-e3e6bed30f5d)

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/fda8adae-cd7d-466c-b4cf-f09345ae2c85)

- **회원 삭제**

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/c15e7f37-2527-4012-9bf7-f9270ebd9525)

![image-20231105144518130](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20231105144518130.png)



- 회원가입

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/24e76f2d-1f0c-4330-98e7-32e889534bfa)

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/cc886ef6-830d-4a90-af03-a0da173f0d29)



- **로그인**

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/8e6e0b72-5489-445f-b153-98ff7f27c296)

![image](https://github.com/SeokJuGo/SeokJuGo/assets/116260619/61c3bd1f-4191-488e-8ea2-c69d007bab9e)

### 3. 관광지 정보 조회

- **지역별 관광지 정보 조회**
- **관광지, 숙박, 음식점 조회**
- **문화시설, 공연, 여행코스, 쇼핑 조회**
- QueryString으로 매개변수 처리
- 동적 쿼리를 이용해 Database 일괄 처리
- 특정 관광지 정보 필요 시 PathVariable 처리

![image](docs/images/swagger-ui_attraction_api.png)

### 4. 게시판 API

- **게시판 목록 조회**
- **게시글 상세 조회**
- **게시글 작성**
- **게시글 수정**
- **게시글 삭제**
- JSON RequestBody로 매개변수 처리
- 동적 쿼리를 이용해 검색기능 일괄 처리
- 특정 게시글 정보 필요 시 PathVariable 처리

![image](docs/images/swagger-ui_board_api.png)