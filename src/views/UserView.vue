<script setup>
import { ref, onMounted, onUnmounted, watch } from "vue";
import Carousel from "@/components/common/Carousel.vue";

const srcs = ref([
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
    `https://source.unsplash.com/random/1920x1080/?travel,beach,sig=${Math.random()}`,
]);

const src = `https://source.unsplash.com/random/2160x1920/?travel,beach,sig=${Math.random()}`;
const backgroundStyle = ref({
    // opacity: 0.5,
    // filter: "blur(5px)",
    // filter: "grayscale(100%)",
    backgroundImage: `url(${src})`,
    // backgroundImage: `url(${srcs.value[0]})`,
    // backgroundImage: `url('${srcs.value.join("'), url('")}')`,
    backgroundColor: "rgba(255, 255, 255, 0.2)",
    backgroundRepeat: "repeat-y",
    backgroundAttachment: "fixed", // 이미지를 스크롤에 고정
    transition: "background-image 0.5s ease", // 애니메이션 효과 추가
});

const backgroundFilter = ref({
    // backdropFilter: "blur(5px)",
});

let imageIndex = ref(0);
const handleScroll = () => {
    const scrollPosition = window.scrollY;
    const viewportHeight = window.innerHeight;
    const newIndex = Math.floor(scrollPosition / (viewportHeight / 5)) % srcs.value.length;
    imageIndex.value = newIndex;
};

// imageIndex가 변경될 때마다 이미지를 로드하고 배경을 업데이트
watch(imageIndex, (newIndex) => {
    const tempImage = new Image();
    tempImage.onload = () => {
        backgroundStyle.value.backgroundImage = `url(${srcs.value[newIndex]})`;
    };
    tempImage.src = srcs.value[newIndex];
});

onMounted(() => {
    window.addEventListener("scroll", handleScroll);
});

onUnmounted(() => {
    window.removeEventListener("scroll", handleScroll);
});
</script>

<template>
    <!-- <div class="container-fluid bg-white p-0">
        <router-view></router-view>
    </div> -->
    <div class="container-fluid bg-white p-0" :style="backgroundStyle">
        <div :style="backgroundFilter">
            <router-view></router-view>
        </div>
    </div>
</template>

<style scoped></style>
