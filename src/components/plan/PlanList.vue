<script setup>
import { ref, onMounted } from "vue";
import BoardAPI from "@/api/board.js";
import HotplaceListItem from "./PlanListItem.vue";

// 게시글 데이터 요청
const params = ref({
    pageNum: undefined,
    pageSize: undefined,
    searchType: undefined,
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

        <!-- Search -->
        <div class="d-flex justify-content-center pb-3">
            <div class="input-group mb-3 w-75">
                <select class="form-select border border-secondary" id="inputGroupSelect01">
                    <option disabled>선택</option>
                    <option value="1" selected>제목</option>
                    <option value="2">내용</option>
                    <option value="3">작성자</option>
                </select>
                <input
                    type="text"
                    class="form-control border border-secondary"
                    placeholder="떠나고 싶은 곳을 찾아보세요!"
                />
                <button
                    class="btn btn-light border border-secondary"
                    type="button"
                    id="button-addon2"
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
