import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useImageStore = defineStore('image', () => {
    // const count = ref(0)
    // const name = ref('Eduardo')
    // const doubleCount = computed(() => count.value * 2)
    // function increment() {
    //   count.value++
    // }
    // return { count, name, doubleCount, increment }

    const srcs1920x720 = ref([
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
        `https://source.unsplash.com/random/1920x720/?travel,beach,sig=${Math.random()}`,
    ]);

    const srcs1920x1080 = ref([
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
    
    function getCarouselImage() {
        const randomIndex = Math.floor(Math.random() * srcs1920x720.value.length);
        return srcs1920x720.value[randomIndex];
    };

    function getBackgroundImage() {
        const randomIndex = Math.floor(Math.random() * srcs1920x1080.value.length);
        return srcs1920x1080.value[randomIndex];
    };

    return { srcs1920x720, srcs1920x1080, getCarouselImage, getBackgroundImage };
});