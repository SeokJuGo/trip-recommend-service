<script setup>
import { ref,inject } from "vue";
import { useRouter } from "vue-router";
import PlanAPI from "@/api/board.js";
import FileInfoAPI from "@/api/fileinfo.js";
import FileInputForm from "@/components/common/FileInputForm.vue";

const choiceList = inject("choiceList")
console.log(choiceList)
const data = ref({
    title: "",
    content: "",
    boardTypeId: 2, // SHARE
    userId: 1, // admin
});

const files = ref([]);
const onChangeFiles = (updatedFiles) => {
    files.value = updatedFiles;
    console.log("[PlanWrite.vue] onChangeFiles() >> ", files);
};

const router = useRouter();
const insertBoard = async () => {
    const id = ref(0);
    await PlanAPI.insertBoard(data.value)
        .then((response) => {
            id.value = response;
        })
        .catch((error) => {
            console.log("[PlanWrite.vue] insertBoard(), Error >> ", error);
        });

    if (files.value.length != 0) {
        await FileInfoAPI.uploadFiles(id.value, files.value)
            .then((response) => {})
            .catch((error) => {
                console.log("[PlanWrite.vue] uploadFiles(), Error >> ", error);
            });
    }
    router.push(`/plan/view/${id.value}`);
};
</script>

<template>
    <div class="container-md py-5">
        <!-- Title -->
        <h1 class="border-bottom border-2 border-secondary">여행 계획</h1>

        <div class="row border border-2 border-dark-subtle rounded-0 shadow bg-white pt-4 px-4">
            <h2 class="fst-italic border-bottom border-2 border-secondary">내 여행지 등록하기</h2>
            <form @submit.prevent="onSubmit" action="">
                
                        <button class="btn btn-dark" @click="insertBoard">등록하기</button>
                    
                
            </form>
        </div>
    </div>
</template>

<style scoped>
.btn {
    min-width: 110px;
}
</style>
