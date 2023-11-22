<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import PlanAPI from "@/api/board.js";
import CommentAPI from "@/api/comment.js";
import FileInfoAPI from "@/api/fileinfo.js";

import Comment from "@/components/common/Comment.vue";

const route = useRoute();
const { id } = route.params;

// Fetch Board
const board = ref({});
const fetchBoard = async () => {
    await PlanAPI.fetchBoard(id)
        .then((response) => {
            board.value = response;
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] fetchBoard() Error >> ", error);
        });
};

// Fetch Files
const files = ref([]);
const fetchFiles = async () => {
    await FileInfoAPI.fetchFiles(id)
        .then((response) => {
            files.value = response;
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] fetchFiles() Error >> ", error);
        });
};

// Comment Data
const data = ref({
    boardId: id,
    content: "",
    userId: 1,
});

// Fetch Comments
const comments = ref([]);
const fetchComments = async () => {
    await CommentAPI.fetchComments(id)
        .then((response) => {
            console.log("[PlanDetail.vue] fetchComments() >> ", response);
            comments.value = response;
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] fetchComments() Error >> ", error);
        });
};

// Insert Comment
const insertComment = async () => {
    await CommentAPI.insertComment(data.value)
        .then((response) => {})
        .catch((error) => {
            console.log(error);
        });
};

const router = useRouter();
const moveToPlanList = () => router.push(`/plan/list`);
const moveToPlanWrite = () => router.push(`/plan/write`);
const moveToPlanUpdate = () => router.push(`/plan/update/${id}`);

const deletePlan = async () => {
    if (files.value.length) {
        for (const file of files.value) {
            await FileInfoAPI.deleteFile(file.id)
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }

    await PlanAPI.deleteBoard(id)
        .then((response) => {
            moveToPlanList();
        })
        .catch((error) => {
            console.log("[PlanDetail.vue] deleteBoard() Error >> ", error);
        });
};

const formatFileSize = (sizeInBytes) => {
    // 변환 공식: 1MB = 1024KB, 1KB = 1024Bytes
    let i = 0;
    let fileSize = sizeInBytes;
    const sizes = ["Bytes", "KB", "MB", "GB"];
    while (fileSize > 1024 && i < sizes.length - 1) {
        fileSize /= 1024;
        i++;
    }

    return fileSize.toFixed(2) + " " + sizes[i];
};

onMounted(() => {
    fetchBoard();
    fetchFiles();
    fetchComments();
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
            <div class="border-bottom border-1 border-black-50 py-3" style="min-height: 40vh">
                {{ board?.content }}
            </div>
            <!-- FileList -->
            <div v-if="files.length" class="border border-1 border-secondary-subtle my-3">
                <div
                    class="d-flex justify-content-center px-2"
                    v-for="(file, index) in files"
                    :key="index"
                >
                    <div class="me-auto">
                        {{ file.filename }}
                    </div>
                    <div class="ms-auto">
                        {{ formatFileSize(file.filesize) }}
                    </div>
                </div>
            </div>
            <!-- Buttons -->
            <div
                :class="{ 'mt-3': !files || files.length === 0 }"
                class="d-flex justify-content-center pb-3"
            >
                <button @click="moveToPlanList" class="me-1 btn btn-outline-dark">목록으로</button>
                <button @click="moveToPlanWrite" class="ms-1 me-auto btn btn-dark">
                    새글작성
                </button>
                <button @click="deletePlan" class="me-1 ms-auto btn btn-danger">삭제하기</button>
                <button @click="moveToPlanUpdate" class="ms-1 btn btn-primary">수정하기</button>
            </div>
        </div>

        <!-- Comment -->
        <div class="row d-flex justify-content-center rounded-0 shadow bg-white pt-4 px-4">
            <h3 class="border-bottom border-1 border-dark-50">댓글(0)</h3>
            <!-- Comment Content -->
            <Comment v-for="comment in comments" :comment="comment" :key="comment.id" />
            <form class="py-4" action="">
                <textarea
                    class="form-control rounded-0"
                    id="content"
                    placeholder="댓글"
                    v-model="data.content"
                    rows="3"
                ></textarea>
                <!-- Buttons -->
                <div class="d-flex justify-content-end pt-2">
                    <button class="btn btn-dark" @click="insertComment">등록</button>
                </div>
            </form>
        </div>
    </div>
</template>

<style scoped>
.btn {
    min-width: 100px;
}
</style>
