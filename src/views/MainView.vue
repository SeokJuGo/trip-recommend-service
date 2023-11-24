<script setup>
import { ref, onMounted, onUnmounted, watch } from "vue";
import { useImageStore } from "@/stores/imageStore";
import Carousel from "@/components/common/Carousel.vue";

const imageStore = useImageStore();
const srcs1920x720 = imageStore.srcs1920x720;
const getBackgroundImage = imageStore.getBackgroundImage;

const backgroundStyle = ref({
    backgroundImage: `url(${getBackgroundImage()})`,
    backgroundColor: "rgba(255, 255, 255, 0.2)",
    backgroundRepeat: "repeat-y",
    backgroundAttachment: "fixed", // 이미지를 스크롤에 고정
    transition: "background-image 0.5s ease", // 애니메이션 효과 추가
});

const backgroundFilter = ref({
    backdropFilter: "blur(5px)",
});

let newIndex = ref(0);
const handleScroll = () => {
    const scrollPosition = window.scrollY;
    const viewportHeight = window.innerHeight;
    newIndex.value = Math.floor((scrollPosition / (viewportHeight / 1)) % srcs1920x720.length);
};

// scrollIndex가 변경될 때마다 이미지를 로드하고 배경을 업데이트
watch(newIndex, (newVal, oldVal) => {
    const checkImage = new Image();
    checkImage.onload = () => {
        backgroundStyle.value.backgroundImage = `url(${srcs1920x720[newVal]})`;
    };
    checkImage.src = srcs1920x720[newVal];
});

onMounted(() => {
    window.addEventListener("scroll", handleScroll);
});

onUnmounted(() => {
    window.removeEventListener("scroll", handleScroll);
});
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
