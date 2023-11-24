<script setup>
import { ref, watch, onMounted, inject, provide } from "vue";
import { userStore } from "@/stores/userPiniaStore";
import { useRouter } from "vue-router";
import ShareAPI from "@/api/board.js";
import PlanAPI from "../../api/plan.js";
const router = useRouter();
var map;
const positions = ref([]);
const markers = ref([]);
const traceAttraction = ref({});
const props = defineProps({ attractions: Array, selectAttraction: Object });
const choiceList = ref([]);

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
      obj.attractionId = attraction.contentId;

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
    level: 2,
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
    var content =
        '<div class="overlay_info">' +
        `    <a href="${position.image}" target="_blank"><strong>${position.title}</strong></a>` +
        '    <div class="desc">' +
        `  <img style="width: 70px;height:40px" src="${position.image}" alt="">` +
        `  <span class="address">${position.address}</span>` +
        "  </div>" +
        "  </div>",
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

const detail = ref("");
let i = 0;
// Attraction List 아이템 중 하나를 선택했을 때 뜨는 창들
const trace = (attraction) => {
  if (!choiceList.value.includes(attraction)) {
    attraction.detail = detail.value;
    traceAttraction.value = attraction;
    choiceList.value.push(attraction);
    open.value = false;
    detail.value = "";
  } else {
    // 이미 존재하는 경우에 대한 처리
    alert(`같은 여행지는 넣을 수 없어요!`);
  }
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

// Plan Modal

const showModalPlan = (attraction) => {
  open.value = true;
  attractionSample.value = attraction;
};
const handleCancel = () => {
  open.value = false;
  
};
// Draw Line
var drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
var moveLine; // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
var clickLine; // 마우스로 클릭한 좌표로 그려질 선 객체입니다
var distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다
var dots = {}; // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.
var count = 0;
const init = () => {
  drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
  moveLine; // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
  clickLine; // 마우스로 클릭한 좌표로 그려질 선 객체입니다
  distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다
  dots = {}; // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.
  count = 0;
};
const draw = () => {
  init();
  console.log(choiceList.value.length);
  choiceList.value.forEach((position) => {
    console.log(position);
    count++;

    // 클릭한 위치입니다
    var clickPosition = position.latlng;

    // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
    if (!drawingFlag) {
      // 상태를 true로, 선이 그리고있는 상태로 변경합니다
      drawingFlag = true;

      // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
      deleteClickLine();

      // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
      deleteDistnce();

      // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
      deleteCircleDot();

      // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
      clickLine = new kakao.maps.Polyline({
        map: map, // 선을 표시할 지도입니다
        path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: "#db4040", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });

      // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
      moveLine = new kakao.maps.Polyline({
        strokeWeight: 3, // 선의 두께입니다
        strokeColor: "#db4040", // 선의 색깔입니다
        strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });

      // 클릭한 지점에 대한 정보를 지도에 표시합니다
      displayCircleDot(clickPosition, 0);
    } else {
      // 선이 그려지고 있는 상태이면

      // 그려지고 있는 선의 좌표 배열을 얻어옵니다
      var path = clickLine.getPath();

      // 좌표 배열에 클릭한 위치를 추가합니다
      path.push(clickPosition);

      // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
      clickLine.setPath(path);

      var distance = Math.round(clickLine.getLength());
      displayCircleDot(clickPosition, distance);
    }

    if (choiceList.value.length != count) {
      // 지도 마우스무브 이벤트가 발생했는데 선을 그리고있는 상태이면

      // 마우스 커서의 현재 위치를 얻어옵니다
      var mousePosition = position.latlng;

      // 마우스 클릭으로 그려진 선의 좌표 배열을 얻어옵니다
      var path = clickLine.getPath();

      // 마우스 클릭으로 그려진 마지막 좌표와 마우스 커서 위치의 좌표로 선을 표시합니다
      var movepath = [path[path.length - 1], mousePosition];
      moveLine.setPath(movepath);
      moveLine.setMap(map);

      // 거리정보를 지도에 표시합니다
      showDistance(content, mousePosition);
    } else {
      // 마우스무브로 그려진 선은 지도에서 제거합니다
      moveLine.setMap(null);
      moveLine = null;

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

        // 그려진 선의 거리정보를 지도에 표시합니다
        showDistance(content, path[path.length - 1]);
      } else {
        // 선을 구성하는 좌표의 개수가 1개 이하이면
        // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
        deleteClickLine();
        deleteCircleDot();
        deleteDistnce();
      }

      // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
      drawingFlag = false;
    }
  });
};

// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
function deleteClickLine() {
  if (clickLine) {
    clickLine.setMap(null);
    clickLine = null;
  }
}

// 마우스 드래그로 그려지고 있는 선의 총거리 정보를 표시하거
// 마우스 오른쪽 클릭으로 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 생성하고 지도에 표시하는 함수입니다
function showDistance(content, position) {
  if (distanceOverlay) {
    // 커스텀오버레이가 생성된 상태이면

    // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
    distanceOverlay.setPosition(position);
    distanceOverlay.setContent(content);
  } else {
    // 커스텀 오버레이가 생성되지 않은 상태이면

    // 커스텀 오버레이를 생성하고 지도에 표시합니다
    distanceOverlay = new kakao.maps.CustomOverlay({
      map: map, // 커스텀오버레이를 표시할 지도입니다
      content: content, // 커스텀오버레이에 표시할 내용입니다
      position: position, // 커스텀오버레이를 표시할 위치입니다.
      xAnchor: 0,
      yAnchor: 0,
      zIndex: 3,
    });
  }
}

// 그려지고 있는 선의 총거리 정보와
// 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
function deleteDistnce() {
  if (distanceOverlay) {
    distanceOverlay.setMap(null);
    distanceOverlay = null;
  }
}

// 선이 그려지고 있는 상태일 때 지도를 클릭하면 호출하여
// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 표출하는 함수입니다
function displayCircleDot(position, distance) {
  // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
  var circleOverlay = new kakao.maps.CustomOverlay({
    content: '<span class="dot"></span>',
    position: position,
    zIndex: 1,
  });

  // 지도에 표시합니다
  circleOverlay.setMap(map);

  if (distance > 0) {
    // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
    var distanceOverlay = new kakao.maps.CustomOverlay({
      content:
        '<div class="dotOverlay">거리 <span class="number">' +
        distance +
        "</span>m</div>",
      position: position,
      yAnchor: 1,
      zIndex: 2,
    });

    // 지도에 표시합니다
    distanceOverlay.setMap(map);
  }

  // 배열에 추가합니다
  dots.push({ circle: circleOverlay, distance: distanceOverlay });
}

// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 지도에서 모두 제거하는 함수입니다
function deleteCircleDot() {
  var i;

  for (i = 0; i < dots.length; i++) {
    if (dots[i].circle) {
      dots[i].circle.setMap(null);
    }

    if (dots[i].distance) {
      dots[i].distance.setMap(null);
    }
  }

  dots = [];
}

// 마우스 우클릭 하여 선 그리기가 종료됐을 때 호출하여
// 그려진 선의 총거리 정보와 거리에 대한 도보, 자전거 시간을 계산하여
// HTML Content를 만들어 리턴하는 함수입니다
function getTimeHTML(distance) {
  // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
  var walkkTime = (distance / 67) | 0;
  var walkHour = "",
    walkMin = "";

  // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
  if (walkkTime > 60) {
    walkHour =
      '<span class="number">' + Math.floor(walkkTime / 60) + "</span>시간 ";
  }
  walkMin = '<span class="number">' + (walkkTime % 60) + "</span>분";

  // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
  var bicycleTime = (distance / 227) | 0;
  var bicycleHour = "",
    bicycleMin = "";

  // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
  if (bicycleTime > 60) {
    bicycleHour =
      '<span class="number">' + Math.floor(bicycleTime / 60) + "</span>시간 ";
  }
  bicycleMin = '<span class="number">' + (bicycleTime % 60) + "</span>분";

  // 자동차의 평균 시속을 기준으로 자동차의 분속을 계산합니다 (가정: 자동차 시속 40km/h)
  var carSpeed = 40; // 자동차의 평균 시속 (단위: km/h)
  var carTime = (distance / ((carSpeed * 1000) / 60)) | 0;
  var carHour = "",
    carMin = "";

  // 계산한 자동차 시간이 60분 보다 크면 시간으로 표출합니다
  if (carTime > 60) {
    carHour =
      '<span class="number">' + Math.floor(carTime / 60) + "</span>시간 ";
  }
  carMin = '<span class="number">' + (carTime % 60) + "</span>분";

  // 거리와 도보 시간, 자전거 시간, 자동차 시간을 가지고 HTML Content를 만들어 리턴합니다
  var content = '<ul class="dotOverlay distanceInfo">';
  content += "    <li>";
  content +=
    '        <span class="label">총거리</span><span class="number">' +
    distance +
    "</span>m";
  content += "    </li>";
  content += "    <li>";
  content += '        <span class="label">도보</span>' + walkHour + walkMin;
  content += "    </li>";
  content += "    <li>";
  content +=
    '        <span class="label">자전거</span>' + bicycleHour + bicycleMin;
  content += "    </li>";
  content += "    <li>";
  content += '        <span class="label">자동차</span>' + carHour + carMin;
  content += "    </li>";
  content += "</ul>";

  return content;
}

const emit = defineEmits(["response"]);
const responseCategoryType = (event) => {
  if (searchAtt.value.sidoCode != 0 || searchAtt.value.title != "") {
    console.log(event.target.value);
    emit("response", event.target.value);
  } else {
    alert("먼저 검색명 입력 또는 시도를 클릭해주세요!");
    return;
  }
};
const searchAtt = inject("searchAtt");

const store = userStore();
const userinfo = ref(store.userInfo);

const boardData = ref({
  title: "",
  content: "",
  boardTypeId: 2, // PLAN
  userId: userinfo.value.id, // USER
});

const title = ref("");
const planSave = async () => {
  console.log(choiceList.value);
  if (choiceList.value.length < 2) {
    alert("두 가지 이상 선택!");
    return;
  }
  const boardId = ref(0);
  await ShareAPI.insertBoard(boardData.value)
    .then((response) => {
      boardId.value = response;
    })
    .catch((error) => {
      console.log("[ShareWrite.vue] insertBoard(), Error >> ", error);
    });

  choiceList.value.forEach(async (data) => {
    console.log(data);
    console.log;
    await PlanAPI.insertBoard([
      {
        attractionId: data.attractionId,
        boardId: boardId.value,
        detail: data.detail,
        distance: 0,
      },
    ])
      .then((response) => {
        router.push({ name: 'plan-view', params: { id: boardId.value } });

      })
      .catch((error) => {
        console.log("[PlanWrite.vue] uploadFiles(), Error >> ", error);
      });
  });
};
</script>

<template>
  <div class="map_wrap">
    <div
      id="map"
      style="width: 100%; position: relative; overflow: hidden"></div>
    <div class="category">
      <button type="button" value="12" @click="responseCategoryType">
        <div style="color:black"><i class="bi bi-bus-front-fill"></i></div> 관광
      </button>
      <button type="button" value="14" @click="responseCategoryType">
        <div style="color:blue" class="icon"><i class="bi bi-building"></i></div> 문화
      </button>
      <button type="button" value="15" @click="responseCategoryType">
        <div style="color:blanchedalmond" class="icon"> <i class="bi bi-back"></i></div> 행사
      </button>
      <button type="button" value="25" @click="responseCategoryType">
        <div style="color:black"><i class="bi bi-airplane"></i></div> 여행
      </button>
      <button type="button" value="28" @click="responseCategoryType">
        <div style="color:red"><i class="bi bi-bicycle"></i></div> 레포츠
      </button>
      <button type="button" value="32" @click="responseCategoryType">
        <div style="color:purple" class="icon"><i class="bi bi-house-door-fill"></i></div> 숙박
      </button>
      <button type="button" value="38" @click="responseCategoryType">
        <div style="color:red" class="icon"><i class="bi bi-cart"></i></div> 쇼핑
      </button>
      <button type="button" value="39" @click="responseCategoryType">
        <div style="color:orange" class="icon"> <i class="bi bi-cup-straw"></i></div> 음식
      </button>
    </div>

    <form class="distance catogory" @submit.prevent></form>

    <div id="menu_wrap" class="bg_white">
      <div class="option"></div>

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
        <a-modal style="top: 100px" width="50%" v-model:open="open">
          <template #footer>
            <a-button key="back" @click="handleCancel">취소</a-button>
            <a-button
              key="submit"
              type="primary"
              :loading="loading"
              @click="trace(attractionSample)"
              >내 계획 추가</a-button
            >
          </template>
          <div class="row">
            <div class="col-md-6">
              <h4>{{ attractionSample.title }}</h4>
              <img :src="attractionSample.image" style="width: 90%" />
              <p>{{ attractionSample.address }}</p>
              <p>메모</p>
              <input class="memo" type="text" v-model="detail" />
            </div>
            <div class="col-md-6 mt-5">
              <p>{{ attractionSample.overview }}</p>
            </div>
          </div>
        </a-modal>
      </div>
    </div>

    <div id="menu_detail" class="bg_white" v-if="choiceList[0]">
      <div class="justify-content-center m-3">
        <button type="button" class="btn btn-outline-danger m-3" @click="draw">
          거리확인
        </button>
        <button
          type="button"
          class="btn btn-outline-success m-3"
          @click="planSave">
          저장하기
        </button>
        <h3>My Plan Title</h3><input class="memo" v-model="boardData.title">
      </div>

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

          <a-card-meta :title="choice.title" :description="choice.address">
          </a-card-meta>
        </a-card>
      </div>
    </div>
    <div></div>
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
  right: 0;
  /* 변경된 부분: left에서 right로 */
  bottom: 0;
  width: 210px;
  margin: 10px 10px 30px 0;
  /* 변경된 부분: 오른쪽 여백을 0으로 설정 */
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}

.map_detail {
  position: relative;
  width: calc(100% - 400px);
  /* 변경된 부분: 메뉴 너비를 고려한 지도의 너비 조정 */
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

.dot {
  overflow: hidden;
  float: left;
  width: 12px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png");
}

.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
}

.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.number {
  font-weight: bold;
  color: #ee6152;
}

.dotOverlay:after {
  content: "";
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png");
}

.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  margin: 0;
}

.distanceInfo .label {
  display: inline-block;
  width: 50px;
}

.distanceInfo:after {
  content: none;
}

.overlay_info {
  border-radius: 6px;
  margin-bottom: 12px;
  float: left;
  position: relative;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  background-color: #fff;
}

.overlay_info:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.overlay_info a {
  display: block;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
  text-decoration: none;
  color: #fff;
  padding: 12px 36px 12px 14px;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
}

.overlay_info a strong {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/place_icon.png)
    no-repeat;
  padding-left: 27px;
}

.overlay_info .desc {
  padding: 14px;
  position: relative;
  min-width: 190px;
  height: 56px;
}

.overlay_info img {
  vertical-align: top;
}

.overlay_info .address {
  font-size: 12px;
  color: #333;
  position: absolute;
  left: 80px;
  right: 14px;
  top: 24px;
  white-space: normal;
}

.overlay_info:after {
  content: "";
  position: absolute;
  margin-left: -11px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png)
    no-repeat 0 bottom;
}

.category {
  position: absolute;
  overflow: hidden;
  top: 0%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;

  z-index: 10;
  font-size: 14px;
  text-align: center;
}
.distance {
  position: absolute;
  overflow: hidden;
  bottom: 100%;
  left: 90%;
  transform: translate(-50%, -50%);
  width: 100%;

  z-index: 5;
  font-size: 14px;
  text-align: center;
}
.category button {
  background-color: #fff;
  border: none;
  cursor: pointer;
  padding: 10px; /* 내부 여백 크게 조절 */
  margin: 5px;
  border-radius: 15px; /* 버튼 모서리 둥글게 조절 */
  transition: all 0.3s ease; /* 부드러운 변화를 위한 트랜지션 */
}
.category button:hover {
  transform: scale(1.1); /* 호버 시 크기 확대 */
  box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.3); /* 호버 시 그림자 증가 */
}
.memo {
  border-radius: 15px; /* 버튼 모서리 둥글게 조절 */
}
</style>
