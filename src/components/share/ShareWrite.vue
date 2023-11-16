<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import ShareAPI from "@/api/board.js";

const data = ref({
    title: "",
    content: "",
    username: "ssafy",
    boardType: "BOARD",
});

const router = useRouter();
const insertShare = async () => {
    await ShareAPI.insertBoard(data.value)
        .then((response) => {
            router.push(`/share/view/${response}`);
        })
        .catch((error) => {
            console.log("[ShareWrite.vue] insertShare(), Error >> ", error);
        });
};
</script>

<template>
    <div class="container-fluid bg-danger-subtle">
        <div class="container-md py-5 text-white-emphasis vh-100">
            <!-- Title -->
            <h1 class="border-bottom border-2 border-secondary">여행지 정보공유</h1>
            <div class="border border-2 border-dark-subtle rounded bg-white shadow p-4">
                <form
                    @submit.prevent
                    id="form-board-write"
                    action="write"
                    class="d-flex flex-column"
                >
                    <input type="hidden" name="action" value="write" />
                    <div class="mb-3">
                        <label for="subject" class="form-label">제목</label>
                        <input
                            type="text"
                            name="subject"
                            id="subject"
                            class="form-control"
                            placeholder="제목"
                            v-model="data.title"
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
                            v-model="data.content"
                        >
                        </textarea>
                    </div>
                    <div class="d-grid gap-2">
                        <button @click="insertShare" id="btn-board-register" class="btn btn-dark">
                            등록하기
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
