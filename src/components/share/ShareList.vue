<script setup>
import { ref, onMounted } from "vue";
import ShareAPI from "@/api/board.js";

import ShareListItem from "./item/ShareListItem.vue";
import PageNavigation from "../common/PageNavigation.vue";

// 게시글 데이터 요청
const params = ref({
    pageNum: undefined,
    pageSize: undefined,
    searchType: "title",
    searchQuery: undefined,
    boardTypeId: 4,
});

const boards = ref([]);
const pageInfo = ref({});
const fetchBoards = async () => {
    await ShareAPI.fetchBoards(params.value)
        .then((response) => {
            boards.value = response.boards;
            pageInfo.value = response;
        })
        .catch((error) => {
            console.log("[ShareList.vue] fetchBoards() Error >> ", error);
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
    <!-- border border-2 border-dark -->
    <div class="container-md py-4">
        <!-- Title -->
        <div class="d-flex justify-content-center pb-3">
            <h1 class="border-bottom border-2 border-white text-white text-shadow">
                여행지 정보공유
            </h1>
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

        <!-- Content -->
        <div class="border border-2 border-dark-subtle rounded shadow bg-white p-4">
            <table class="table border table-hover text-center">
                <thead>
                    <tr>
                        <th class="text-center" style="width: 7.5%">#</th>
                        <th class="text-start" style="width: 55%">제목</th>
                        <th class="text-center" style="width: 12.5%">작성자</th>
                        <th class="text-center" style="width: 7.5%">조회수</th>
                        <th class="text-center" style="width: 17.5%">작성일</th>
                    </tr>
                </thead>
                <ShareListItem v-for="board in boards" :board="board" :key="board.id" />
            </table>

            <!-- Button -->
            <div class="d-flex justify-content-center">
                <div class="col-md-3 text-start"></div>

                <!-- Pagination -->
                <div class="col-md-6 text-center">
                    <PageNavigation
                        :page-num="pageInfo.pageNum"
                        :total-pages="pageInfo.totalPages"
                        @on-change-page-num="onChangePageNum"
                    ></PageNavigation>
                </div>

                <div class="col-md-3 text-end">
                    <button class="btn btn-dark" @click="$router.push(`/share/write`)">
                        글쓰기
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
button {
    min-width: 100px;
}

select {
    max-width: 110px;
}

.text-shadow {
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
}
</style>
