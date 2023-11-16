<script setup>
import { ref, onMounted } from "vue";
import ShareAPI from "@/api/board.js";
import { useRoute, useRouter } from "vue-router";

import ShareListItem from "./item/ShareListItem.vue";
import PageNavigation from "../common/PageNavigation.vue";

// 게시글 데이터 요청
const params = ref({
    pageNum: undefined,
    pageSize: undefined,
    keyword: undefined,
    searchType: undefined,
    boardType: "BOARD",
});

const boards = ref([]);
const pageInfo = ref({});
const fetchBoards = async () => {
    await ShareAPI.fetchBoards(params.value)
        .then((response) => {
            boards.value = response.boards;
            pageInfo.value = response;
            console.log("[ShareList.vue] fetchBoards() >> ", response);
            console.log("[ShareList.vue] fetchBoards() >> pageInfo = ", pageInfo.value);
        })
        .catch((error) => {
            console.error("[ShareList.vue] fetchBoards() Error >> ", error);
        });
};

//글 작성 라우터
// const router = useRouter();

// const goWrite = function () {
//     router.push({ name: "share-write" });
// };

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
    <div class="container-fluid bg-danger-subtle">
        <div class="container-md py-5 text-white-emphasis">
            <!-- Title -->
            <h1 class="border-bottom border-2 border-secondary">여행지 정보공유</h1>
            <div class="border border-2 border-dark-subtle rounded bg-white shadow p-4 h-100">
                <table class="table table-bordered table-striped table-hover text-center">
                    <thead>
                        <tr>
                            <th class="text-center" style="width: 7.5%">#</th>
                            <th class="text-start" style="width: 65%">제목</th>
                            <th class="text-center" style="width: 7.5%">작성자</th>
                            <th class="text-center" style="width: 7.5%">조회수</th>
                            <th class="text-center" style="width: 12.5%">작성일</th>
                        </tr>
                    </thead>

                    <ShareListItem v-for="board in boards" :key="board.id" :board="board" />
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

                <!-- Search Form -->
                <!-- <div class="col-md-9 ms-auto mb-3 w-50">
                    <form class="d-flex" id="form-search" action="">
                        <input type="hidden" name="action" value="list" />
                        <input type="hidden" name="pgno" value="1" />
                        <select
                            name="key"
                            id="key"
                            class="form-select form-select-sm ms-5 me-1 w-25"
                            aria-label="검색조건"
                        >
                            <option value="" selected="">검색조건</option>
                            <option value="article_no">글번호</option>
                            <option value="subject">제목</option>
                            <option value="user_id">작성자</option>
                        </select>
                        <div class="input-group input-group-sm w-50">
                            <input
                                type="text"
                                name="word"
                                id="word"
                                class="form-control"
                                placeholder="검색어..."
                            />
                            <button id="btn-search" class="btn btn-dark" type="button">검색</button>
                        </div>
                    </form>
                </div> -->
            </div>
        </div>
    </div>
</template>

<style scoped>
.btn {
    min-width: 100px;
}
</style>
