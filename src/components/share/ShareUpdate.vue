<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import ShareAPI from "@/api/board.js";

const route = useRoute();
const { id } = route.params;

const board = ref({});
const fetchBoard = async () => {
    await ShareAPI.fetchBoard(id)
        .then((response) => {
            console.log("[ShareUpdate.vue] fetchBoard() >> ", response);
            board.value = response;
            board.value.boardType = "BOARD";
            board.value.username = response.author.username;
        })
        .catch((error) => {
            console.log("[ShareUpdate.vue] fetchBoard() Error >> ", error);
        });
};

const router = useRouter();
const updateBoard = async () => {
    await ShareAPI.updateBoard(id, board.value)
        .then((response) => {
            router.replace(`/share/view/${id}`);
        })
        .catch((error) => {
            console.log("[ShareUpdate.vue] updateBoard() Error >> ", error);
        });
};

// import axios from "axios";
// const b = async () => {
//     await axios
//         .get(`http://localhost:8080/enjoytrip/board/detail/${id}`)
//         .then(({ data }) => {
//             board.value = data;
//             console.log("1. board data >> ", board.value);
//             //console.log("----------------------------->", response);
//         })
//         .catch((e) => {
//             console.log("1. board catch >> ", e);
//         });
// };

// const deleleShare = () => {
//     axios
//         .delete(`http://localhost:8080/enjoytrip/board/delete`, {
//             data: {
//                 id: id,
//             },
//         })
//         .then((response) => console.log(response))
//         .catch((error) => console.log(error));
// };

// const update = async () => {
//     await axios
//         .put(`http://localhost:8080/enjoytrip/board/update`, board.value)
//         .then((response) => console.log(response));
// };

onMounted(() => {
    fetchBoard();
});
</script>

<template>
    <div class="container-fluid bg-danger-subtle">
        <div class="container-md py-5 text-white-emphasis vh-100">
            <!-- Title -->
            <h1 class="border-bottom border-2 border-secondary">여행지 정보공유 수정</h1>
            <div class="border border-2 border-dark-subtle rounded bg-white shadow p-4">
                <form id="form-board-modify" action="" class="d-flex flex-column">
                    <input type="hidden" name="action" value="modify" />
                    <input type="hidden" name="boardId" :value="board.id" />
                    <div class="mb-3">
                        <label for="title" class="form-label">제목</label>
                        <input
                            type="text"
                            name="title"
                            id="title"
                            class="form-control"
                            placeholder="제목"
                            v-model="board.title"
                        />
                    </div>
                    <div class="mb-3">
                        <label for="content" class="form-label">내용</label>
                        <textarea
                            name="content"
                            id="content"
                            class="form-control"
                            style="height: 40vh"
                            placeholder="내용"
                            v-model="board.content"
                            >{{ board.content }}</textarea
                        >
                    </div>
                </form>
                <div class="d-grid gap-2">
                    <button id="btn-board-modify" class="btn btn-dark" @click="updateBoard">
                        수정하기
                    </button>
                    <button id="btn-board-delete" class="btn btn-dark" @click="deleleShare">
                        삭제하기
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
