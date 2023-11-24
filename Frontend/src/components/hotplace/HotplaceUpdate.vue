<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import BoardAPI from "@/api/board.js";
import FileInfoAPI from "@/api/fileinfo.js";
import FileInputForm from "@/components/common/FileInputForm.vue";

const route = useRoute();
const { id } = route.params;

const data = ref({
    id: id,
    hit: 0,
    title: "",
    content: "",
    boardTypeId: 3, // Hotplace
    userId: 1, // admin
});

// Fetch Board
const fetchBoard = async () => {
    await BoardAPI.fetchBoard(id)
        .then((response) => {
            data.value = response;
            data.value.userId = response.author.id;
            data.value.boardTypeId = response.boardType.id;
            console.log(data.value);
        })
        .catch((error) => {
            console.log("[HotplaceUpdate.vue] fetchBoard() Error >> ", error);
        });
};

// Fetch Files
const files = ref([]);
const existsFiles = ref([]);
const fetchFiles = async () => {
    await FileInfoAPI.fetchFiles(id)
        .then((response) => {
            existsFiles.value = response;
        })
        .catch((error) => {
            console.log("[HotplaceUpdate.vue] fetchFiles() Error >> ", error);
        });
};

const onChangeFiles = (updatedFiles) => {
    files.value = updatedFiles;
    console.log("[HotplaceUpdate.vue] onChangeFiles() >> ", files);
};

const router = useRouter();
const updateBoard = async () => {
    if (files.value.length == 0) {
        alert("이미지를 등록해야 합니다!!!");
        return false;
    }

    await BoardAPI.updateBoard(data.value.id, data.value)
        .then((response) => {})
        .catch((error) => {
            console.log("[HotplaceUpdate.vue] updateBoard(), Error >> ", error);
        });

    if (files.value.length != 0) {
        for (const file of existsFiles.value) {
            await FileInfoAPI.deleteFile(file.id)
                .then((response) => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                });
        }

        await FileInfoAPI.uploadFiles(data.value.id, files.value)
            .then((response) => {})
            .catch((error) => {
                console.log("[HotplaceUpdate.vue] uploadFiles(), Error >> ", error);
            });
    }
    router.push(`/hotplace/view/${id}`);
};

onMounted(() => {
    fetchBoard();
    fetchFiles();
});
</script>

<template>
    <div class="container-md py-5">
        <!-- Title -->
        <h1 class="border-bottom border-2 border-white text-white text-shadow">핫플레이스</h1>

        <div class="border border-2 border-dark-subtle rounded-0 shadow bg-white pt-4 px-4">
            <h2 class="fst-italic border-bottom border-2 border-secondary">핫플레이스 자랑하기</h2>
            <form @submit.prevent="onSubmit" action="">
                <div class="mb-3">
                    <label for="title" class="form-label">제목</label>
                    <input
                        type="text"
                        class="form-control rounded-0"
                        id="title"
                        placeholder="제목"
                        v-model="data.title"
                    />
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">내용</label>
                    <textarea
                        class="form-control rounded-0"
                        id="content"
                        placeholder="내용"
                        v-model="data.content"
                        rows="20"
                    ></textarea>
                </div>
                <div class="mb-3">
                    <FileInputForm @on-change-files="onChangeFiles" />
                </div>
                <div class="row d-flex justify-content-center">
                    <div class="col-md-6 mb-3 text-center text-md-end">
                        <button
                            class="btn btn-outline-dark"
                            @click="$router.push('/hotplace/list')"
                        >
                            목록으로
                        </button>
                    </div>
                    <div class="col-md-6 mb-3 text-center text-md-start">
                        <button class="btn btn-dark" @click="updateBoard">수정하기</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</template>

<style scoped>
.btn {
    min-width: 110px;
}

.text-shadow {
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
}
</style>
