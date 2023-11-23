<script setup>
defineProps({
    attraction: Object,
});

const truncateText = (text, maxLength) => {
    if (text.length > maxLength) {
        return text.slice(0, maxLength) + "...";
    }
    return text;
};
</script>

<template>
    <div class="col-md">
        <div class="card">
            <img :src="attraction?.firstImage" class="card-img-top" alt="..." />
            <div class="card-body">
                <h5 class="card-title">{{ attraction?.title }}</h5>
                <small class="card-text">
                    ({{ attraction?.zipCode }}) {{ attraction?.address }} Tel. {{ attraction?.tel }}
                </small>
                <p class="card-text">
                    {{ truncateText(attraction?.overview, 255) }}
                    <!-- {{ attraction?.overview }} -->
                </p>
            </div>
        </div>
    </div>
</template>

<style scoped></style>
