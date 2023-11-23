<script setup>
import { ref, onMounted } from "vue";
import BoardAPI from "@/api/board.js";
import PlanListItem from "@/components/plan/PlanListItem.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";

// 게시글 데이터 요청
const params = ref({
    pageNum: undefined,
    pageSize: 5,
    searchType: "title",
    searchQuery: undefined,
    boardTypeId: 2,
});

const boards = ref([]);
const pageInfo = ref({});
const fetchBoards = async () => {
    await BoardAPI.fetchBoards(params.value)
        .then((response) => {
            boards.value = response.boards;
            pageInfo.value = response;
        })
        .catch((error) => {
            console.error("[HotplaceList.vue] fetchBoards() Error >> ", error);
        });
};

// PageNavigation 이동
const onChangePageNum = (pageNum) => {
    console.log("[ShareList.vue] onChangePageNum(), pageNum = ", pageNum);
    params.value.pageNum = pageNum;
    fetchBoards();
};

onMounted(() => {
    fetchBoards();
});
</script>

<template>
    <div class="container-md py-4">
        <!-- Title -->
        <div class="d-flex justify-content-center pb-3">
            <h1 class="border-bottom border-2 border-white text-white text-shadow">나의여행계획</h1>
        </div>

        <!-- Search -->
        <div class="d-flex justify-content-center pb-3">
            <div class="input-group mb-3 w-75">
                <select
                    class="form-select border border-secondary"
                    id="inputGroupSelect01"
                    v-model="params.searchType"
                >
                    <option disabled>선택</option>
                    <option value="title" selected>제목</option>
                    <option value="content">내용</option>
                    <option value="nickname">작성자</option>
                </select>
                <input
                    type="text"
                    class="form-control border border-secondary"
                    placeholder="떠나고 싶은 곳을 찾아보세요!"
                    v-model="params.searchQuery"
                />
                <button
                    class="btn btn-light border border-secondary"
                    type="button"
                    id="button-addon2"
                    @click="fetchBoards"
                >
                    검색
                </button>
            </div>
        </div>

        <!-- Grid -->
        <div class="d-flex justify-content-center">
            <div class="row">
                <PlanListItem v-for="board in boards" :key="board.id" :board="board" />
            </div>
        </div>

        <!-- Pagination -->
        <div class="d-flex justify-content-center border border-1 bg-white shadow py-2">
            <PageNavigation
                :page-num="pageInfo.pageNum"
                :total-pages="pageInfo.totalPages"
                @on-change-page-num="onChangePageNum"
            ></PageNavigation>
        </div>
    </div>
</template>

<style scoped>
select {
    max-width: 110px;
}

.text-shadow {
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
}
</style>
