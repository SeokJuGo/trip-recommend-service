<script setup>

</script>

<template>
<!-- Main Content -->
<div class="container-fluid bg-warning-subtle">
  <div class="container-md pt-5 pb-1">
    <!-- Title -->
    <div class="d-flex justify-content-center mb-3">
      <h1 class="display-4 fw-bold text-center border-bottom border-2 border-secondary">
        관광지 정보 조회
      </h1>
    </div>
    <!-- Grid Row -->
    <div class="row justify-content-center mb-3">
      <div class="col-md-4 mb-2 mb-md-0">
        <select id="sido-select" class="form-select" aria-label="Default select example">
          <option selected>시도선택</option>
          <!--axios로 불러와서 for문 돌려야됨 <c:forEach var="sido" items="${sidoList}">
            <option value="${sido.sidoCode}">${sido.sidoName}</option>
          </c:forEach> -->
        </select>
      </div>
      <div class="col-md-4 mb-2 mb-md-0">
        <select id="gugun-select" class="form-select" aria-label="Default select example">
          <option selected>구군선택</option>
          <!-- axios로 불러와서 for문 돌려야됨 <c:forEach var="gugun" items="${gugunList}">
            <option value="${gugun.gugunCode}">${gugun.gugunName}</option>
          </c:forEach> -->
        </select>
      </div>
      <div class="col-md-4 mb-0 mb-md-0">
        <select id="contentType" class="form-select" aria-label="Default select example">
          <option selected>관광정보선택</option>
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="15">축제공연행사</option>
          <option value="25">여행코스</option>
          <option value="28">레포츠</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">음식점</option>
        </select>
      </div>
    </div>
    <div class="row justify-content-center mb-3">
      <div class="col-md-12 mb-0 mb-md-0">
        <input
          type="text"
          class="form-control"
          id="title"
          placeholder="관광지 검색 키워드를 입력하세요."
        />
      </div>
    </div>
    <div class="row justify-content-center mb-3">
      <div class="col-md-12 mb-0 mb-md-0">
        <button class="w-100 btn btn-light border form-control" id="btn-search-attraction">
          검색
        </button>
      </div>
    </div>
    <div id="map" style="width: 100%; height: 600px"></div>
  </div>

  <div class="container-md pt-1 pb-5">
    <table class="table table-bordered table-striped table-hover text-center">
      <thead>
        <tr>
          <th class="text-center" style="width: 7.5%">#</th>
          <th class="text-start" style="width: 32.5%">관광지</th>
          <th class="text-center" style="width: 25%">주소1</th>
          <th class="text-center" style="width: 25%">주소2</th>
          <th class="text-center" style="width: 10%">전화번호</th>
        </tr>
      </thead>
      <tbody>
        <!-- <c:forEach var="attraction" items="${attractions}">
          <tr>
            <td class="text-center">${attraction.contentId}</td>
            <td class="text-start">${attraction.title}</td>
            <td class="text-center">${attraction.addr1}</td>
            <td class="text-center">${attraction.addr2}</td>
            <td class="text-center">${attraction.tel}</td>
          </tr>
        </c:forEach> -->
      </tbody>
    </table>

    <div class="mx-auto" aria-label="Page navigation example">
      <!-- <div class="row">${navigation.navigator}</div> -->
    </div>
  </div>
</div>

<!-- JavaScripts -->
<!--
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e32a76c8b5f1bc4d07d3957e5f24edb"
></script>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e32a76c8b5f1bc4d07d3957e5f24edb&libraries=services,clusterer,drawing"
></script> 
// Custom JavaScripts
<script type="text/javascript">
  if ("${title}") document.getElementById("title").value = "${title}";
  if (${sidoCode}) document.getElementById("sido-select").value = ${sidoCode};
  if (${gugunCode}) document.getElementById("gugun-select").value = ${gugunCode};
  if (${contentTypeId}) document.getElementById("contentType").value = ${contentTypeId};

  document.addEventListener("DOMContentLoaded", () => {
      // Page has finished loading
      pageLoaded = true;

    //시도 선택 시
      document.getElementById("sido-select").addEventListener("change", () => {
          let contentTypeId = document.getElementById("contentType").value;
          let gugunCode = document.getElementById("gugun-select").value;
          let sidoCode = document.getElementById("sido-select").value;
          let title = document.getElementById("title").value;

          let baseURL = "${root}/attraction?action=list";
          let queryString = "";

          if (title) queryString += "&title=" + title;
          if (sidoCode) queryString += "&sidoCode=" + sidoCode;
          if (gugunCode) queryString += "&gugunCode=" + gugunCode;
          if (contentTypeId) queryString += "&contentTypeId=" + contentTypeId;

          window.location.href = baseURL + queryString;
      });
      //구군 선택 시
      document.getElementById("gugun-select").addEventListener("change", () => {
          let contentTypeId = document.getElementById("contentType").value;
          let gugunCode = document.getElementById("gugun-select").value;
          let sidoCode = document.getElementById("sido-select").value;
          let title = document.getElementById("title").value;

          let baseURL = "${root}/attraction?action=list";
          let queryString = "";

          if (title) queryString += "&title=" + title;
          if (sidoCode) queryString += "&sidoCode=" + sidoCode;
          if (gugunCode) queryString += "&gugunCode=" + gugunCode;
          if (contentTypeId) queryString += "&contentTypeId=" + contentTypeId;

          window.location.href = baseURL + queryString;
      });
      //관광지 타입 선택 시
      document.getElementById("contentType").addEventListener("change", () => {
          let contentTypeId = document.getElementById("contentType").value;
          let gugunCode = document.getElementById("gugun-select").value;
          let sidoCode = document.getElementById("sido-select").value;
          let title = document.getElementById("title").value;

          let baseURL = "${root}/attraction?action=list";
          let queryString = "";

          if (title) queryString += "&title=" + title;
          if (sidoCode) queryString += "&sidoCode=" + sidoCode;
          if (gugunCode) queryString += "&gugunCode=" + gugunCode;
          if (contentTypeId) queryString += "&contentTypeId=" + contentTypeId;

          window.location.href = baseURL + queryString;
      });

      document.getElementById("btn-search-attraction").addEventListener("click", () => {
          let contentTypeId = document.getElementById("contentType").value;
          let gugunCode = document.getElementById("gugun-select").value;
          let sidoCode = document.getElementById("sido-select").value;
          let title = document.getElementById("title").value;

          let baseURL = "${root}/attraction?action=list";
          let queryString = "";

          if (title) queryString += "&title=" + title;
          if (sidoCode) queryString += "&sidoCode=" + sidoCode;
          if (gugunCode) queryString += "&gugunCode=" + gugunCode;
          if (contentTypeId) queryString += "&contentTypeId=" + contentTypeId;

          window.location.href = baseURL + queryString;
      });

      window.onload = function() {
          displayMarker("${attractions}");
      }
  });
</script>
<script type="text/javascript" src="./assets/js/maps.js"></script>
-->

</template>

<style scoped>

</style>