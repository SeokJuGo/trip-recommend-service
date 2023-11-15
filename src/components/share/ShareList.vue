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
            console.error("[ShareList.vue] fetchBoards() >> ", error);
        });
};

// 리스트
// const params = ref({
//     pageNum: 1,
//     pageSize: 10,
//     keyword: "",
//     searchType: "",
// });

// const pageNum = ref(1);
// const totalPages = ref(0);

// const boardlist = async () => {
//     await api
//         .get(`http://localhost:8080/enjoytrip/board`, { params: params.value })
//         .then((response) => {
//             console.log(response);
//             pageNum.value = response.data.pageNum;
//             totalPages.value = response.data.totalPages;
//             boards.value = response.data.boards;
//             console.log(response.data);
//         })
//         .catch((e) => {
//             console.log("1. boards catch >> ", e);
//         });
// };

// const getBoardList = async () => {
//     console.log("서버에서 글목록 얻어오자!!!", params.value);
//     // API 호출
//     await api
//         .get(`http://localhost:8080/enjoytrip/board`, { params: params.value })
//         .then((response) => {
//             console.log(response);
//             pageNum.value = response.data.pageNum;
//             totalPages.value = response.data.totalPages;
//             boards.value = response.data.boards;
//             console.log(response.data);
//         })
//         .catch((e) => {
//             console.log("2. boards catch >> ", e);
//         });
// };

// const onPageChange = (val) => {
//     console.log(val + "번 페이지로 이동 준비 끝!!!");
//     pageNum.value = val;
//     params.value.pageNum = val;
//     getBoardList();
// };

//글 작성 라우터
// const router = useRouter();

// const goWrite = function () {
//     router.push({ name: "share-write" });
// };

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

                    <ShareListItem v-for="board in boards" :key="board.id" :board="board">
                    </ShareListItem>
                </table>

                <!-- Button -->
                <div class="d-flex justify-content-center">
                    <div class="col-md-3 text-start"></div>
                    <div class="col-md-6 text-center">
                        <!-- <PageNavigation
                            :page-num="params.pageNum"
                            :total-pages="params.totalPages"
                            @pageChange="onPageChange"
                        ></PageNavigation> -->
                        <PageNavigation
                            :page-num="pageInfo.pageNum"
                            :total-pages="pageInfo.totalPages"
                            @on-change-page-num="onChangePageNum"
                        ></PageNavigation>
                        {{ boards.totalPages }}
                    </div>

                    <div class="col-md-3 text-end">
                        <button class="btn btn-dark" @click="$router.push(`/share/write`)">
                            등록하기
                        </button>
                    </div>
                </div>

                <!-- Pagination -->
                <!-- <PageNavigation
                        :current-page="pageNum"
                        :total-page="totalPages"
                        @pageChange="onPageChange"
                    ></PageNavigation> -->

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

<style scoped></style>
