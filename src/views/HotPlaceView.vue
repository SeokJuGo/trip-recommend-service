<script setup>
import Carousel from "@/components/common/Carousel.vue";

const src = `https://source.unsplash.com/random/2160x1920/?travel,beach,sig=${Math.random()}`;
const backgroundStyle = {
    // opacity: 0.5,
    // filter: "blur(5px)",
    // filter: "grayscale(100%)",
    backgroundImage: `url(${src})`,
    backgroundColor: "rgba(255, 255, 255, 0.2)",
};

const backgroundFilter = {
    backdropFilter: "blur(5px)",
};
</script>

<template>
    <div class="container-fluid bg-white p-0" :style="backgroundStyle">
        <div :style="backgroundFilter">
            <Carousel />
            <router-view></router-view>
        </div>
    </div>
</template>

<style scoped></style>
