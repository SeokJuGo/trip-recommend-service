<script setup>
import { computed } from "vue";

const props = defineProps({
    pageNum: Number,
    totalPages: Number,
});

const navigationSize = parseInt(import.meta.env.VITE_ARTICLE_NAVIGATION_SIZE);

const pages = computed(() => {
    let sPage = props.pageNum - parseInt(navigationSize / 2);
    let ePage = props.pageNum + parseInt(navigationSize / 2);

    if (sPage < 1) {
        sPage = 1;
        ePage = navigationSize;
    }

    if (ePage > props.totalPages) {
        ePage = props.totalPages;
        sPage = props.totalPages - navigationSize + 1;
        sPage = Math.max(sPage, 1);
    }

    const pages = [];
    for (let i = sPage; i <= ePage; i++) pages.push(i);
    return pages;
});
</script>

<template>
    <div>
        <ul class="pagination justify-content-center m-0">
            <li class="page-item">
                <a class="page-link" @click="$emit('onChangePageNum', 1)">최신</a>
            </li>
            <li class="page-item">
                <a class="page-link" @click="$emit('onChangePageNum', Math.max(pageNum - 1, 1))"
                    >이전</a
                >
            </li>

            <div v-for="page in pages" :key="page">
                <li :class="page === pageNum ? 'page-item active' : 'page-item'">
                    <a class="page-link" @click="$emit('onChangePageNum', page)">{{ page }}</a>
                </li>
            </div>

            <li class="page-item">
                <a
                    class="page-link"
                    @click="$emit('onChangePageNum', Math.min(pageNum + 1, totalPages))"
                    >다음</a
                >
            </li>
            <li class="page-item">
                <a class="page-link" @click="$emit('onChangePageNum', totalPages)">마지막</a>
            </li>
        </ul>
    </div>
</template>

<style scoped>
a {
    cursor: pointer;
}

div > li > a,
.pagination > li > a {
    background-color: white;
    color: #000000;
    border: solid 1px #000000;
}

div > li > a:focus,
div > li > a:hover,
div > li > span:focus,
div > li > span:hover,
.pagination > li > a:focus,
.pagination > li > a:hover,
.pagination > li > span:focus,
.pagination > li > span:hover {
    color: #5a5a5a;
    background-color: #eee;
    border-color: #ddd;
}

.pagination > div > .active > a {
    color: white;
    background-color: #000000 !important;
    border: solid 1px #000000 !important;
    /* border: solid 1px #ddd !important; */
}

.pagination > div > .active > a:hover {
    background-color: #000000 !important;
    border: solid 1px #ddd;
}
</style>
