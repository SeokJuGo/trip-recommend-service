# EnjoyTrip*Final*서울*15반*조시훈\_고석주

## 목차

-   [Overview](#enjoytrip---epickorea)
-   [Environment](#environment)
-   [ERDiagram](#erdiagram)
-   [REST API](#rest-api)
    -   [BOARD_TYPE](#board_type)
    -   [BOARD](#board)
    -   [PLAN](#plan)
    -   [FILEINFO](#fileinfo)
-   [기본기능](#기본기능)
    -   [메인페이지](#메인페이지)
    -   [지역별여행지](#지역별여행지)
    -   [나의여행계획](#나의여행계획)
    -   [핫플자랑하기](#핫플자랑하기)
    -   [여행지정보공유](#여행지정보공유)
-   [추가기능](#추가기능)
    -   [비밀번호찾기](#비밀번호찾기)
    -   [카카오 지도 API](#카카오-지도-api외부api)
    -   [랜덤이미지 API](#랜덤이미지-api외부api)

# EnjoyTrip - `EpicKorea`

> 공공데이터 포탈 및 카카오 API 등 외부 API를 활용한 여행정보제공 웹서비스

## Environment

-   Windows10 / Eclipse(STS) / VSCode
-   Java / MySQL / Tomcat
-   Spring Framework (Spring Boot) / MyBatis Framework
-   HTML / CSS / JavaScript / Vue.js / BootStrap
-   Kakao Map API, 공공데이터 포탈

## Getting started

Clone 후 Vue 폴더에서 다음 명령어 실행

```
$ npm install
```

# ERDiagram

![Alt text](images/ER_Diagram.png)

[Top](#)

# REST API

기본적인 API외의 핵심적인 기능만 기재

[Top](#)

## BOARD_TYPE

-   `BOARD_TYPE` 테이블을 별도로 생성하여 게시판 종류별로 관리
-   추후의 확장성을 고려하여 CRUD 시스템 구성

![Alt text](images/board_type.png)

[Top](#)

## BOARD

-   `BOARD_TYPE` 테이블을 참조하여 하나의 `BOARD` 테이블로 `나의여행계획`, `핫플자랑하기`, 여행정보공유` 게시판을 한번에 관리
-   `@RequestParam` QueryString 형태로 검색조건을 입력받아 게시판별 검색기능 구현
-   `pageNum`, `pageSize`를 통해 페이징 기능 구현

![Alt text](images/api_board.png)

[Top](#)

## PLAN

-   `BOARD`를 참조하여 여행계획 데이터를 관리하고, 세부 관광지 정보를 순서대로 관리
-   즉, `PLAN`테이블은 `BOARD:ATTRACTION = N:M` 관계를 위한 중간 참조테이블

![Alt text](images/api_plan.png)

[Top](#)

## FILEINFO

-   첨부파일 관리 테이블을 별도로 생성
-   첨부파일 다운로드 & 썸네일 이미지 다운로드로 동시에 사용가능

![Alt text](images/api_attach.png)

[Top](#)

# 기본기능

## 메인페이지

-   관광지 정보 조회수 기준 내림차순 정렬하여 가장 인기있는 관광지를 메인에 표시
-   서브쿼리(Subquery, Nested Query)를 사용하여 성능개선

```SQL
SELECT
    A.content_id contentId, content_type_id contentTypeId,
    title title, addr1 address, zipcode zipCode, tel, readcount readCount,
    first_image firstImage, first_image2 secondImage,
    sido_code sidoCode, gugun_code gugunCode,
    latitude, longitude, mlevel, overview
FROM attraction_info A
JOIN (
    SELECT content_id
    FROM attraction_info
    ORDER BY readcount DESC
    LIMIT 12
) AS top_attractions ON A.content_id = top_attractions.content_id
JOIN attraction_description B ON A.content_id = B.content_id
ORDER BY A.readcount DESC;
```

![Alt text](images/index.png)

[Top](#)

## 지역별여행지

-   시도, 구군별 필터링 기능 제공
-   관광지 타입별 필터링 기능 제공

![Alt text](images/plan_write.png)

[Top](#)

## 나의여행계획

![Alt text](images/plan.png)
![Alt text](images/plan_write.png)
![Alt text](images/plan_detail.png)

[Top](#)

## 핫플자랑하기

-   조회수를 기준으로 내림차순 정렬하여 인기가 많은 관광지 순으로 나열
-   썸네일 이미지를 등록해야 게시글이 작성되도록 구현
-   댓글 등록 기능 제공

![Alt text](images/hotplace.png)
![Alt text](images/hotplace_detail.png)

[Top](#)

## 여행지정보공유

-   다중 첨부파일 업로드 기능 제공
-   댓글 등록 기능 제공

![Alt text](images/share.png)
![Alt text](images/share_write.png)
![Alt text](images/share_detail.png)

[Top](#)

# 추가기능

## 비밀번호찾기

-   비밀번호 찾기 기능 구현

![Alt text](images/forget_password.png)

[Top](#)

## 카카오 지도 API(외부API)

-   거리계산 기능 제공

```javascript
// 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
var path = clickLine.getPath();

// 선을 구성하는 좌표의 개수가 2개 이상이면
if (path.length > 1) {
// 마지막 클릭 지점에 대한 거리 정보 커스텀 오버레이를 지웁니다
if (dots[dots.length - 1].distance) {
    dots[dots.length - 1].distance.setMap(null);
    dots[dots.length - 1].distance = null;
}

var distance = Math.round(clickLine.getLength()), // 선의 총 거리를 계산합니다
    content = getTimeHTML(distance); // 커스텀오버레이에 추가될 내용입니다
```

```javascript
// 거리 구하기
function distance(lat1, lon1, lat2, lon2) {
    const R = 6371; // 지구 반지름 (단위: km)
    const dLat = deg2rad(lat2 - lat1);
    const dLon = deg2rad(lon2 - lon1);
    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = R * c; // 두 지점 간의 거리 (단위: km)
    console.log(distance);
    return distance;
}

function deg2rad(deg) {
    return deg * (Math.PI / 180);
}
```

[Top](#)

## 랜덤이미지 API(외부API)

-   `Unsplash`의 API를 이용
-   이미지 사이즈, 검색 키워드를 이용 여행과 관련된 이미지 추출하여 배경이미지 등으로 사용
-   `Pinia`를 이용해 별도의 Store 공간에 저장하여 사용

```javascript
import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useImageStore = defineStore('image', () => {

    const srcs1920x720 = ref([
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        ...
    ]);

    const srcs1920x1080 = ref([
        `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
        ...
    ]);

    function getCarouselImage() {
        const randomIndex = Math.floor(Math.random() * srcs1920x720.value.length);
        return srcs1920x720.value[randomIndex];
    };

    function getBackgroundImage() {
        const randomIndex = Math.floor(Math.random() * srcs1920x1080.value.length);
        return srcs1920x1080.value[randomIndex];
    };

    return { srcs1920x720, srcs1920x1080, getCarouselImage, getBackgroundImage };
});
```

[Top](#)
