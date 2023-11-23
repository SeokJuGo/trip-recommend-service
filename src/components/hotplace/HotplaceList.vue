<script setup>
import { ref, onMounted, watch } from "vue";
import BoardAPI from "@/api/board.js";
import FileInfoAPI from "@/api/fileinfo.js";
import HotplaceListItem from "@/components/hotplace/HotplaceListItem.vue";
import PageNavigation from "@/components/common/PageNavigation.vue";

// 게시글 데이터 요청
const params = ref({
    sortBy: "hit",
    orderBy: "desc",
    pageNum: undefined,
    pageSize: 10,
    searchType: "title",
    searchQuery: undefined,
    boardTypeId: 3,
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
            <h1 class="border-bottom border-2 border-white text-white text-shadow">
                최고의 여행지
            </h1>
        </div>
        <div class="text-center mb-3">
            <button class="fs-3 btn btn-dark" @click="$router.push(`/hotplace/write`)">
                자랑하러가기
            </button>
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
                <HotplaceListItem v-for="board in boards" :key="board.id" :board="board" />
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
button {
    min-width: 100px;
}

select {
    max-width: 110px;
}

.text-shadow {
    /* color: #fff; 텍스트 색상 */
    /* font-size: 24px; 텍스트 크기 */
    /* letter-spacing: 2px; 글자 간격 */

    /* 테두리 효과 */
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
}
</style>
