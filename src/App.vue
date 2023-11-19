<script setup>
import HeaderNav from "./components/common/HeaderNav.vue";
import FooterNav from "./components/common/FooterNav.vue";
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
    <HeaderNav />

    <router-view></router-view>

    <footer-nav></footer-nav>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
* {
    font-family: "Jua", sans-serif;
}
</style>
