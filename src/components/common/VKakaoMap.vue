<script setup>
import { ref, watch, onMounted } from "vue";
var map;
const positions = ref([]);
const markers = ref([]);
const traceAttraction = ref({});
const props = defineProps({ attractions: Array, selectAttraction: Object });
const choiceList = ref([]);
const endPlan = false;
watch(
  () => traceAttraction.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = traceAttraction.value.latlng;

    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);
  },
  { deep: true }
);

// watch(
//   () => choiceList.value,
//   () => {

//   },
//   { deep: true }
// );
onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

watch(
  () => props.attractions.value,
  () => {
    positions.value = [];
    props.attractions.forEach((attraction) => {
      let obj = {};
      obj.latlng = new kakao.maps.LatLng(
        attraction.latitude,
        attraction.longitude
      );
      obj.title = attraction.title;
      obj.address = attraction.address;
      obj.image = attraction.firstImage;
      obj.overview = attraction.overview;

      positions.value.push(obj);
    });
    loadMarkers();
  },
  { deep: true }
);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);
};

const loadMarkers = () => {
  deleteMarkers();

  markers.value = [];
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      content: "",
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true,
      // image: markerImage, // 마커의 이미지
    });
    var content = '<div class="overlay_info">'+
            `    <a href="${position.image}" target="_blank"><strong>${position.title}</strong></a>`+
            '    <div class="desc">'+
              `  <img style="width: 70px;height:40px" src="${position.image}" alt="">`+
              `  <span class="address">${position.address}</span>`+
              '  </div>'+
              '  </div>',
      iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
      content: content,
      removable: iwRemoveable,
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, "click", function () {
      // 마커 위에 인포윈도우를 표시합니다
      infowindow.open(map, marker);
    });

    markers.value.push(marker);
  });

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
};

// Attraction List 아이템 중 하나를 선택했을 때 뜨는 창들
const trace = (attraction) => {
  traceAttraction.value = attraction;
  choiceList.value.push(attraction);
  open.value = false;
};

function removeChoice(target) {
  choiceList.value = choiceList.value.filter((t) => t !== target);
}

// 모달 창 로직
const open = ref(false);
const attractionSample = ref();
const showModal = (attraction) => {
  open.value = true;
  attractionSample.value = attraction;
};
const handleCancel = () => {
  open.value = false;
};
</script>

<template>
  <div class="map_wrap">
    <div
      id="map"
      style="width: 100%; position: relative; overflow: hidden"></div>

    <div id="menu_wrap" class="bg_white">
      <div class="option">
      </div>
      <hr />
      <ul
        id="placesList"
        v-for="attraction in positions"
        :key="attraction.contentId">
        <a-card hoverable style="width: 200px" @click="showModal(attraction)">
          <template #cover>
            <img alt="" :src="attraction.image" />
          </template>
          <template #actions>
            <setting-outlined key="setting" />
            <edit-outlined key="edit" />
            <ellipsis-outlined key="ellipsis" />
          </template>
          <a-card-meta
            :title="attraction.title"
            :description="attraction.address">
          </a-card-meta>
        </a-card>
      </ul>
  <div>

      <a-modal
        style="top: 100px"
        width="50%"
        v-model:open="open">
        <template #footer>
        <a-button key="back" @click="handleCancel">취소</a-button>
        <a-button key="submit" type="primary" :loading="loading" @click="trace(attractionSample)">내 계획 추가</a-button>
      </template>
        <div class="row">
          <div class="col-md-6">
            <h4>{{ attractionSample.title }}</h4>
            <img :src="attractionSample.image" style="width:90%" />
            <p>{{ attractionSample.address }}</p>
          </div>
          <div class="col-md-6 mt-5">
            <p>{{ attractionSample.overview }}</p>
          </div>
        </div>
      </a-modal>
    </div>
    </div>
    <div id="menu_detail" class="bg_white" v-if="choiceList[0]">
      <div
        id="placeDetail"
        v-for="choice in choiceList"
        :key="choice.contentId">
        <a-card hoverable style="width: 200px" @click="removeChoice(choice)">
          <template #cover>
            <img alt="" :src="choice.image" />
          </template>
          <template #actions>
            <setting-outlined key="setting" />
            <edit-outlined key="edit" />
            <ellipsis-outlined key="ellipsis" />
          </template>
          <a-card-meta
            :title="choice.title"
            :description="choice.address">
          </a-card-meta>
        </a-card>
      </div>
    </div>
  </div>
</template>

<style>
#map,
#menu_wrap,
#menu_detail {
  width: 100%;
  height: 100%;
}
.placesList {
  margin: 0 auto;
}
.wrap,
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 100%;
}

#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 210px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}

#menu_detail {
  position: absolute;
  top: 0;
  right: 0; /* 변경된 부분: left에서 right로 */
  bottom: 0;
  width: 210px;
  margin: 10px 10px 30px 0; /* 변경된 부분: 오른쪽 여백을 0으로 설정 */
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}

.map_detail {
  position: relative;
  width: calc(
    100% - 400px
  ); /* 변경된 부분: 메뉴 너비를 고려한 지도의 너비 조정 */
  height: 500px;
}

#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
.dot {overflow:hidden;float:left;width:12px;height:12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png');}    
.dotOverlay {position:relative;bottom:10px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;font-size:12px;padding:5px;background:#fff;}
.dotOverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}    
.number {font-weight:bold;color:#ee6152;}
.dotOverlay:after {content:'';position:absolute;margin-left:-6px;left:50%;bottom:-8px;width:11px;height:8px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png')}
.distanceInfo {position:relative;top:5px;left:5px;list-style:none;margin:0;}
.distanceInfo .label {display:inline-block;width:50px;}
.distanceInfo:after {content:none;}
    .overlay_info {border-radius: 6px; margin-bottom: 12px; float:left;position: relative; border: 1px solid #ccc; border-bottom: 2px solid #ddd;background-color:#fff;}
    .overlay_info:nth-of-type(n) {border:0; box-shadow: 0px 1px 2px #888;}
    .overlay_info a {display: block; background: #d95050; background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center; text-decoration: none; color: #fff; padding:12px 36px 12px 14px; font-size: 14px; border-radius: 6px 6px 0 0}
    .overlay_info a strong {background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/place_icon.png) no-repeat; padding-left: 27px;}
    .overlay_info .desc {padding:14px;position: relative; min-width: 190px; height: 56px}
    .overlay_info img {vertical-align: top;}
    .overlay_info .address {font-size: 12px; color: #333; position: absolute; left: 80px; right: 14px; top: 24px; white-space: normal}
    .overlay_info:after {content:'';position: absolute; margin-left: -11px; left: 50%; bottom: -12px; width: 22px; height: 12px; background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png) no-repeat 0 bottom;}
    
</style>
