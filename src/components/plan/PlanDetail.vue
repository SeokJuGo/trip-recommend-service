<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import PlanAPI from "@/api/plan.js";
import BoardAPI from "@/api/board.js";


const route = useRoute();
const { id } = route.params;

// Fetch Board
const plan = ref([]);
const fetchPlan = async () => {
    await PlanAPI.fetchBoard(id)
        .then((response) => {
            plan.value = response;
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] fetchPlan() Error >> ", error);
        });

        console.log(plan.value);
};
const board = ref({});
const fetchBoard = async () => {
    await BoardAPI.fetchBoard(id)
        .then((response) => {
            plan.value = response;
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] fetchBoard() Error >> ", error);
        });
        console.log(board.value);
};

const router = useRouter();
const moveToPlanList = () => router.push(`/plan/list`);
const moveToPlanWrite = () => router.push(`/plan/write`);
const moveToPlanUpdate = () => router.push(`/plan/update/${id}`);

const deletePlan = async () => {
    await PlanAPI.deleteBoard(id)
        .then((response) => {
            moveToPlanList();
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] deleteBoard() Error >> ", error);
        });
};

onMounted(() => {
    fetchPlan();
    fetchBoard();
});

</script>

<template>
    <div class="container-md py-5">
        <h1 class="border-bottom border-2 border-secondary">내 계획</h1>
        <div class="row d-flex justify-content-center rounded-0 shadow bg-white pt-4 px-4 mb-3">
            <!-- Title -->
            <div class="d-flex justify-content-center border-bottom border-1 border-black-50">
                <div class="d-flex align-items-center fst-italic me-auto">
                    <h2 class="m-0">{{ board?.title }}</h2>
                </div>
                <div class="text-black-50 fst-italic ms-auto">
                    <small class="d-block text-end"
                        >작성자:&nbsp;{{ board?.author?.username }}</small
                    >
                    <small class="d-block text-end">작성일:&nbsp;{{ board?.createdDate }}</small>
                    <small class="d-block text-end">수정일:&nbsp;{{ board?.updatedDate }}</small>
                </div>
            </div>
            <!-- Content -->
            <!-- Buttons -->
            <div class="d-flex justify-content-center pb-3"
            >
                <button @click="moveToPlanList" class="me-1 btn btn-outline-dark">목록으로</button>
                <button @click="moveToPlanWrite" class="ms-1 me-auto btn btn-dark">
                    새글작성
                </button>
                <button @click="deletePlan" class="me-1 ms-auto btn btn-danger">삭제하기</button>
                <button @click="moveToPlanUpdate" class="ms-1 btn btn-primary">수정하기</button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.btn {
    min-width: 100px;
}
</style>
