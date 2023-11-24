<script setup>
import { ref, onMounted } from "vue";
import FileInfoAPI from "@/api/fileinfo.js";
const props = defineProps({
    board: Object,
    // thumnail: String,
});

const src = ref({});
const fileId = ref(0);
const fetchImage = async () => {
    await FileInfoAPI.fetchFiles(props.board.id)
        .then((response) => {
            fileId.value = response[0].id;
        })
        .catch((error) => {
            // console.error("[HotplaceDetail.vue] fetchFiles() Error >> ", error);
        });
    if (fileId.value !== 0) {
        await FileInfoAPI.downloadImage(fileId.value)
            .then((response) => {
                src.value = window.URL.createObjectURL(response.data);
            })
            .catch((error) => {
                console.error("[HotplaceDetail.vue] downloadImage() Error >> ", error);
            });
    } else {
        src.value = `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`;
    }
};

onMounted(async () => {
    await fetchImage();
});
</script>

<template>
    <!-- Card -->
    <div class="col-lg-6">
        <router-link :to="{ name: 'hotplace-view', params: { id: board.id } }">
            <div class="card text-bg-dark shadow rounded-0 mb-3">
                <!-- style="width: 636px; height: 477px" -->
                <img v-if="src" :src="src" class="card-img rounded-0" alt="..." />
                <img
                    v-else
                    src="@/assets/img/강릉.jpg"
                    class="card-img rounded-0"
                    alt="thumbnail"
                />
                <div class="card-img-overlay d-flex flex-column">
                    <div class="d-flex justify-content-between">
                        <h5 class="card-title text-shadow">{{ board?.title }}</h5>
                        <p class="text-shadow card-text text-end">
                            <small class="d-block">Created {{ board?.createdDate }}</small>
                            <small class="d-block">Posted By {{ board?.author.nickname }}</small>
                            <small class="d-block">View {{ board?.hit }}</small>
                        </p>
                    </div>
                    <p class="text-shadow card-text mt-auto">
                        {{ board?.content }}
                    </p>
                </div>
            </div>
        </router-link>
    </div>
</template>

<style scoped>
a {
    color: black;
    text-decoration: none;
}

.text-shadow {
    /* color: #fff; 텍스트 색상 */
    /* font-size: 24px; 텍스트 크기 */
    /* letter-spacing: 2px; 글자 간격 */

    /* 테두리 효과 */
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
}
</style>
