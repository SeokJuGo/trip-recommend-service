<script setup>
import { ref, onMounted } from "vue";
import { useImageStore } from "@/stores/imageStore";
import { useQuoteStore } from "@/stores/quoteStore";

const imageStore = useImageStore();
// const srcs1920x720 = imageStore.srcs1920x720;
const getCarouselImage = imageStore.getCarouselImage;

const quoteStore = useQuoteStore();
// const quotes = quoteStore.quotes;
const getRandomQuote = quoteStore.getRandomQuote;

const quotes = ref([]);
onMounted(() => {
    for (let i = 0; i < 5; i++) {
        quotes.value.push(getRandomQuote());
    }
})

</script>

<template>
    <div id="carouselExampleCaptions" class="carousel slide">
        <div class="carousel-indicators">
            <button
                type="button"
                data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to="0"
                class="active"
                aria-current="true"
                aria-label="Slide 1"
            ></button>
            <button
                type="button"
                data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to="1"
                aria-label="Slide 2"
            ></button>
            <button
                type="button"
                data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to="2"
                aria-label="Slide 3"
            ></button>
            <button
                type="button"
                data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to="3"
                aria-label="Slide 4"
            ></button>
            <button
                type="button"
                data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to="4"
                aria-label="Slide 5"
            ></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img :src="getCarouselImage()" class="d-block w-100" alt="..." />
                <div class="mask d-flex justify-content-center align-items-center h-100">
                    <h1 class="fst-italic text-white text-shadow mb-0">{{ quotes[0]?.text }}</h1>
                </div>
                <div class="carousel-caption d-none d-md-block">
                    <h5 class="text-shadow">{{ quotes[0]?.text }}</h5>
                    <p class="text-shadow">- {{ quotes[0]?.author }} -</p>
                </div>
            </div>
            <div class="carousel-item">
                <img :src="getCarouselImage()" class="d-block w-100" alt="..." />
                <div class="mask d-flex justify-content-center align-items-center h-100">
                    <h1 class="fst-italic text-white text-shadow mb-0">{{ quotes[1]?.text }}</h1>
                </div>
                <div class="carousel-caption d-none d-md-block">
                    <h5 class="text-shadow">{{ quotes[1]?.text }}</h5>
                    <p class="text-shadow">- {{ quotes[1]?.author }} -</p>
                </div>
            </div>
            <div class="carousel-item">
                <img :src="getCarouselImage()" class="d-block w-100" alt="..." />
                <div class="mask d-flex justify-content-center align-items-center h-100">
                    <h1 class="fst-italic text-white text-shadow mb-0">{{ quotes[2]?.text }}</h1>
                </div>
                <div class="carousel-caption d-none d-md-block">
                    <h5 class="text-shadow">{{ quotes[2]?.text }}</h5>
                    <p class="text-shadow">- {{ quotes[2]?.author }} -</p>
                </div>
            </div>
            <div class="carousel-item">
                <img :src="getCarouselImage()" class="d-block w-100" alt="..." />
                <div class="mask d-flex justify-content-center align-items-center h-100">
                    <h1 class="fst-italic text-white text-shadow mb-0">{{ quotes[3]?.text }}</h1>
                </div>
                <div class="carousel-caption d-none d-md-block">
                    <h5 class="text-shadow">{{ quotes[3]?.text }}</h5>
                    <p class="text-shadow">- {{ quotes[3]?.author }} -</p>
                </div>
            </div>
            <div class="carousel-item">
                <img :src="getCarouselImage()" class="d-block w-100" alt="..." />
                <div class="mask d-flex justify-content-center align-items-center h-100">
                    <h1 class="fst-italic text-white text-shadow mb-0">{{ quotes[4]?.text }}</h1>
                </div>
                <div class="carousel-caption d-none d-md-block">
                    <h5 class="text-shadow">{{ quotes[4]?.text }}</h5>
                    <p class="text-shadow">- {{ quotes[4]?.author }} -</p>
                </div>
            </div>
        </div>
        <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="prev"
        >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleCaptions"
            data-bs-slide="next"
        >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</template>

<style scoped>

.text-shadow {
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
}

</style>
