<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import PlanAPI from "@/api/plan.js";
import BoardAPI from "@/api/board.js";
import { listAttraction } from "@/api/attraction.js";

const route = useRoute();
const { id } = route.params;

const board = ref({});
const plan = ref([]);
const totalDistance = ref();
// Fetch Board
const fetchPlan = async () => {
    await PlanAPI.fetchBoard(id)
        .then((response) => {
            plan.value = response;
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] fetchPlan() Error >> ", error);
        });
};
const fetchBoard = async () => {
    await BoardAPI.fetchBoard(id)
        .then((response) => {
            board.value = response;
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] fetchBoard() Error >> ", error);
        });
    console.log(board.value);
};

const router = useRouter();
const moveToPlanList = () => router.push(`/plan/list`);
const moveToPlanWrite = () => router.push(`/attraction`);
const moveToPlanUpdate = () => router.push(`/plan/update/${id}`);

const deletePlan = async () => {
    await PlanAPI.deleteBoard(id)
        .then((response) => {})
        .catch((error) => {
            console.log("[PlanDetail.vue] deleteBoard() Error >> ", error);
        });

    await BoardAPI.deleteBoard(id)
        .then((response) => {
            moveToPlanList();
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] deleteBoard() Error >> ", error);
        });
};

onMounted(async () => {
    await fetchPlan();
    await fetchBoard();
    await calDistance();
});

var lat,
    lng,
    preLat,
    preLng = 0;
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

let count = 0;
const calDistance = () => {
    console.log("calDistance 시작");

    plan.value.forEach((plan) => {
        count++;
        console.log(plan);
        if (count !== 1) {
            lat = plan.attraction.latitude;
            lng = plan.attraction.longitude;
            var dis = distance(lat, lng, preLat, preLng);
            dis = Math.round(dis * 100) / 100;
            totalDistance.value = dis;
            plan.distance = dis;
            plan.walk = getWalk(dis * 1000);
            plan.bicycle = getBicycle(dis * 1000);
            plan.car = getCar(dis * 1000);
        }

        preLat = plan.attraction.latitude;
        preLng = plan.attraction.longitude;
    });
    console.log(plan.value);
};

function getWalk(distance) {
    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
    var walkkTime = (distance / 67) | 0;
    var walkMin = "";

    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
    walkMin = walkkTime;
    return walkMin;
}

function getBicycle(distance) {
    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
    var bicycleTime = (distance / 227) | 0;
    var bicycleMin = "";

    bicycleMin = bicycleTime;
    return bicycleMin;
}

function getCar(distance) {
    // 자동차의 평균 시속을 기준으로 자동차의 분속을 계산합니다 (가정: 자동차 시속 40km/h)
    var carSpeed = 40; // 자동차의 평균 시속 (단위: km/h)
    var carTime = (distance / ((carSpeed * 1000) / 60)) | 0;
    var carMin = "";

    carMin = carTime;
    return carMin;
}
</script>

<template>
    <div class="container-md py-5">
        <h1 class="border-bottom border-2 border-secondary">내 계획</h1>

        <div class="row d-flex justify-content-center rounded-0 shadow bg-white pt-4 px-4 mb-3">
            <h2 class="m-0">계획 이름 : {{ board?.title }}</h2>
            <!-- Title -->
            <div class="text-black-50 fst-italic ms-auto">
                <small class="d-block text-end">작성자:&nbsp;{{ board?.author?.username }}</small>
                <small class="d-block text-end">작성일:&nbsp;{{ board?.createdDate }}</small>
                <small class="d-block text-end">수정일:&nbsp;{{ board?.updatedDate }}</small>
            </div>
            <div class="d-flex justify-content-center border-bottom border-1 border-black-50">
                <div class="d-flex align-items-center fst-italic me-auto">
                    <div>
                        <a-divider />
                        <div>
                            <div class="row justify-content-end mb-3">
                                <a-timeline
                                    mode="alternate"
                                    v-for="attraction in plan"
                                    :key="attraction.attractionId"
                                >
                                    <a-timeline-item color="green">
                                        <div class="move" v-if="attraction.bicycle">
                                            {{ attraction.distance }}KM
                                            <i class="bi bi-person-fill"></i> 도보
                                            {{ attraction.walk }}분
                                            <i class="bi bi-bicycle" style="color: red"></i> 자전거
                                            {{ attraction.bicycle }}분
                                            <i class="bi bi-car-front-fill" style="color: blue"></i>
                                            자동차 {{ attraction.car }}분
                                        </div>
                                        <h3>{{ attraction.attraction.title }}</h3>
                                        <p>{{ attraction.attraction.address }}</p>
                                        <p>{{ attraction.attraction.overview }}</p>
                                    </a-timeline-item>

                                    <a-timeline-item
                                        ><img :src="attraction.attraction.firstImage"
                                    /></a-timeline-item>

                                    <a-timeline-item color="red"
                                        >{{ attraction.detail }}
                                        <div></div>
                                        <div></div
                                    ></a-timeline-item>
                                </a-timeline>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Content -->
            <!-- Buttons -->
            <div class="d-flex justify-content-center pb-3">
                <button @click="moveToPlanList" class="me-1 btn btn-outline-dark">목록으로</button>
                <button @click="moveToPlanWrite" class="ms-1 me-auto btn btn-dark">새글작성</button>
                <button @click="deletePlan" class="me-1 ms-auto btn btn-danger">삭제하기</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
img {
    width: 70%;
}
.btn {
    min-width: 100px;
}

.move:hover {
    box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.3); /* 호버 시 그림자 증가 */
}
</style>
