// KAKAO API
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



///////////////////////////////////////////////////////////////////////////////////////////////////
let positions = [];
function displayMarker(attractions) {
	const dataArray = attractions.match(/AttractionInfoDto \[.*?\]/g);	
	if (!dataArray) return;
	
	const attractionInfoObjects = [];
	for (const item of dataArray) {
	    const match = item.match(/title=([^,]+),.*latitude=([^,]+), longitude=([^,]+),/);
	    if (match) {
	        const [, title, mapy, mapx] = match;
	        attractionInfoObjects.push({
	            title: title,
	            mapy: parseFloat(mapy),
	            mapx: parseFloat(mapx),
	        });
	    }
	}
	
	positions = attractionInfoObjects.map((attract) => ({
	  title: attract.title,
	  latlng: new kakao.maps.LatLng(attract.mapy, attract.mapx),
	}));
	
	createMarker();
	const centerX = parseFloat(attractionInfoObjects[0].mapx);
	const centerY = parseFloat(attractionInfoObjects[0].mapy);
	map.setCenter(new kakao.maps.LatLng(centerY, centerX));
}




//const displayMarker = (json) => {
//    const attracts = json.response.body.items.item;
//    positions = attracts.map((attract) => ({
//        title: attract.title,
//        latlng: new kakao.maps.LatLng(attract.mapy, attract.mapx),
//    }));
//
//    createMarker();
//    const centerX = parseFloat(attracts[0].mapx);
//    const centerY = parseFloat(attracts[0].mapy);
//    map.setCenter(new kakao.maps.LatLng(centerY, centerX));
//}

let markers = [];
const createMarker = () => {
    let imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    let imageSize = new kakao.maps.Size(24, 35);
    // let imageSize = new kakao.maps.Size(40, 40);

    const contentTypeValue = document.getElementById("contentType").value;
    if (contentTypeValue == 12) imageSrc = "./assets/img/markers/landmark.svg";
    else if (contentTypeValue == 14) imageSrc = "./assets/img/markers/culture.svg";
    else if (contentTypeValue == 15) imageSrc = "./assets/img/markers/festival.svg";
    else if (contentTypeValue == 25) imageSrc = "./assets/img/markers/trip-course.svg";
    else if (contentTypeValue == 28) imageSrc = "./assets/img/markers/leisure-sports.svg";
    else if (contentTypeValue == 32) imageSrc = "./assets/img/markers/hotel.svg";
    else if (contentTypeValue == 38) imageSrc = "./assets/img/markers/shopping.svg";
    else if (contentTypeValue == 39) imageSrc = "./assets/img/markers/restaurant.svg";
    markers.map(marker => marker.setMap(null));

    markers = [];
    positions.forEach((position) => {
        const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        const marker = new kakao.maps.Marker({
            map: map,
            image: markerImage,
            title: position.title,
            position: position.latlng,
        });

        markers.push(marker);
    });
};
