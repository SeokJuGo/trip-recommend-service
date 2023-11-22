<script setup>
import { onMounted } from "vue";
const props = defineProps({
    board: Object,
});
</script>

<template>
    <!-- Card -->
    <div class="col-lg-6">
        <router-link :to="{ name: 'hotplace-view', params: { id: board.id } }">
            <div class="card text-bg-dark shadow rounded-0 mb-3">
                <img src="@/assets/img/강릉.jpg" class="card-img rounded-0" alt="thumnail" />
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
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Obcaecati nam magni
                        non at vel harum. Hic, ut qui esse dolore voluptatibus odio provident labore
                        est quis minus nostrum, fugiat dignissimos!
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
