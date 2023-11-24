<script setup>
import axios from "axios";
import { ref, onMounted } from "vue";
import { useQuoteStore } from "@/stores/quoteStore";
import MainItem from "@/components/main/MainItem.vue";

const attractions = ref([]);
const fetchAttractions = async () => {
    await axios
        .get("/", {
            baseURL: import.meta.env.VITE_BACKEND_BASE_URL,
            headers: {
                "Content-Type": "application/json;charset=utf-8",
            },
        })
        .then((response) => {
            console.log("[MainPage.vue] fetchAttractions() >> ", response.data);
            attractions.value = response.data;
        })
        .catch((error) => {
            console.log("[MainPage.vue] fetchAttractions() Error >> ", error);
        });
};

const quoteStore = useQuoteStore();
// const quotes = quoteStore.quotes;
const getRandomQuote = quoteStore.getRandomQuote;

const quotes = ref([]);
onMounted(() => {
    fetchAttractions();
    for (let i = 0; i < 5; i++) {
        quotes.value.push(getRandomQuote());
    }
});
</script>

<template>
    <div class="container-md py-4">
        <div class="border border-2 text-white text-shadow bg-primary-subtle shadow p-4 mb-3">
            <h1>여행가고 싶을 땐?</h1>
            <h3 class="fst-italic bg-success-subtle shadow shadow p-3">
                "여행의 장점은 새로운 문화를 체험하며 다양한 사람들과 소통할 수 있는 기회를
                제공한다는 것입니다. 또한 여행은 일상의 소소한 스트레스를 떨쳐내고 새로운 환경에서의
                경험을 통해 자기 성장을 이끌어내는데 도움이 됩니다. 심신의 안정과 피로 회복을
                촉진하여 마음의 평화를 찾을 수 있어, 여행은 지친 마음을 힐링할 수 있는 특별한 경험이
                될 거예요. 다음은 사람들에게 많은 관심과 사랑을 받은 관광지입니다. 어디로
                떠나볼까요?"
            </h3>
        </div>

        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <MainItem
                v-for="attraction in attractions"
                :attraction="attraction"
                :key="attraction.contentId"
            />
        </div>
    </div>
</template>

<style scoped>
.text-shadow {
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
}
</style>
