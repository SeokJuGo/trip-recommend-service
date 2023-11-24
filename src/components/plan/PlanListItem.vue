<script setup>
import { ref, onMounted } from "vue";
const props = defineProps({
    board: Object,
});

const src = ref(`https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`);
</script>

<template>
    <!-- Card -->
    <div class="col-lg-12">
        <router-link :to="{ name: 'plan-view', params: { id: board.id } }">
            <div class="card border border-1 border-dark rounded-0 shadow mb-3">
                <div class="row">
                    <div class="col-md-4">
                        <!-- <img src="@/assets/img/강릉.jpg" class="img-fluid rounded-0" alt="..." /> -->
                        <img :src="src" class="img-fluid rounded-0" alt="..." />
                    </div>
                    <div class="col-md-8">
                        <div class="card-body d-flex flex-column h-100">
                            <!-- <h2 class="card-title">{{ board?.title }}</h2>
                            <p class="card-text">
                                {{ board?.content }}
                            </p>
                            <p class="card-text mt-auto">
                                <small class="text-body-secondary">Last updated 3 mins ago</small>
                            </p> -->
                            <div class="d-flex justify-content-between">
                                <h2 class="card-title">{{ board?.title }}</h2>
                                <p class="card-text text-end">
                                    <small class="d-block text-body-secondary"
                                        >Created {{ board?.createdDate }}</small
                                    >
                                    <small class="d-block"
                                        >Posted By {{ board?.author.nickname }}</small
                                    >
                                    <small class="d-block">View {{ board?.hit }}</small>
                                </p>
                            </div>
                            <div class="card-text mt-auto shadow-3-strong p-3">
                                {{ board?.content }}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- <div class="card text-bg-dark shadow rounded-0 mb-3">
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
            </div> -->
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
