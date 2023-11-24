// KAKAO MAP API
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
///////////////////////////////////////////////////////////////////////////////
// 공공데이터 API
const serviceKey =
  "PxdsnRGoDtzCABQXD3VELgrYeUlROjLiZxl7MGykEkhdsQyR5nOUJ1JgWLlWhc88OPbIidC1pxPptgsu7tqQqQ%3D%3D";

///////////////////////////////////////////////////////////////////////////////
// 시도선택 정보 불러오기
let dataApiUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  serviceKey +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
fetch(dataApiUrl, { method: "GET" })
  .then((response) => response.json())
  .then((data) => initSido(data));

const initSido = (data) => {
  let select = document.getElementById("sido-select");
  let areas = data.response.body.items.item;

  areas.forEach((area) => {
    let option = document.createElement("option");
    option.setAttribute("value", area.code);
    option.appendChild(document.createTextNode(area.name));
    select.appendChild(option);
  });
};

///////////////////////////////////////////////////////////////////////////////
// 구군선택 정보 불러오기
document.getElementById("sido-select").addEventListener("change", () => {
  if (!isNaN(document.getElementById("sido-select").value)) {
    let baseUrl = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${serviceKey}`;
    let queryString = `&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json`;

    let areaCode = document.getElementById("sido-select").value;
    if (parseInt(areaCode)) queryString += `&areaCode=${areaCode}`;

    fetch(baseUrl + queryString, { method: "GET" })
      .then((response) => response.json())
      .then((data) => initGugoon(data));
  }
});

const initGugoon = (data) => {
  let select = document.getElementById("gugoon-select");
  let areas = data.response.body.items.item;

  while (select.firstChild) select.removeChild(select.firstChild);
  let initOption = document.createElement("option");
  initOption.setAttribute("selected", true);
  initOption.appendChild(document.createTextNode("구군선택"));
  select.appendChild(initOption);

  areas.forEach((area) => {
    let option = document.createElement("option");
    option.setAttribute("value", area.code);
    option.appendChild(document.createTextNode(area.name));
    select.appendChild(option);
  });
};

///////////////////////////////////////////////////////////////////////////////
// 시도, 구군 값을 통해 관광 정보 불러오기
document.getElementById("sido-select").addEventListener("change", () => {
  if (!isNaN(document.getElementById("sido-select").value)) {
    let baseUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}`;
    let queryString = `&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

    let sidoCode = document.getElementById("sido-select").value;
    let gugoonCode = document.getElementById("gugoon-select").value;
    let contentTypeId = document.getElementById("contentType").value;
    if (parseInt(sidoCode)) queryString += `&areaCode=${sidoCode}`;
    if (parseInt(gugoonCode)) queryString += `&sigunguCode=${gugoonCode}`;
    if (parseInt(contentTypeId)) queryString += `&contentTypeId=${contentTypeId}`;

    fetch(baseUrl + queryString, { method: "GET" })
      .then((response) => response.json())
      .then((data) => displayMarker(data));
  }
});
document.getElementById("gugoon-select").addEventListener("change", () => {
  if (!isNaN(document.getElementById("gugoon-select").value)) {
    let baseUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}`;
    let queryString = `&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

    let sidoCode = document.getElementById("sido-select").value;
    let gugoonCode = document.getElementById("gugoon-select").value;
    let contentTypeId = document.getElementById("contentType").value;
    if (parseInt(sidoCode)) queryString += `&areaCode=${sidoCode}`;
    if (parseInt(gugoonCode)) queryString += `&sigunguCode=${gugoonCode}`;
    if (parseInt(contentTypeId)) queryString += `&contentTypeId=${contentTypeId}`;

    fetch(baseUrl + queryString, { method: "GET" })
      .then((response) => response.json())
      .then((data) => displayMarker(data));
  }
});

document.getElementById("contentType").addEventListener("change", () => {
  if (!isNaN(document.getElementById("contentType").value)) {
    let baseUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}`;
    let queryString = `&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

    let sidoCode = document.getElementById("sido-select").value;
    let gugoonCode = document.getElementById("gugoon-select").value;
    let contentTypeId = document.getElementById("contentType").value;
    if (parseInt(sidoCode)) queryString += `&areaCode=${sidoCode}`;
    if (parseInt(gugoonCode)) queryString += `&sigunguCode=${gugoonCode}`;
    if (parseInt(contentTypeId)) queryString += `&contentTypeId=${contentTypeId}`;

    fetch(baseUrl + queryString, { method: "GET" })
      .then((response) => response.json())
      .then((data) => displayMarker(data));
  }
});

let positions;
const displayMarker = (data) => {
  let attracts = data.response.body.items.item;

  positions = [];
  attracts.forEach((attract) => {
    positions.push({
      title: attract.title,
      latlng: new kakao.maps.LatLng(attract.mapy, attract.mapx),
    });
  });
  createMarker();

  centerX = parseFloat(attracts[0].mapx);
  centerY = parseFloat(attracts[0].mapy);
  map.setCenter(new kakao.maps.LatLng(centerY, centerX));
};

const createMarker = () => {
  // 마커 이미지의 이미지 주소입니다
  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

  for (var i = 0; i < positions.length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35);

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: positions[i].latlng, // 마커를 표시할 위치
      title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });
  }
};
