<script setup>
import HeaderNav from "./components/common/HeaderNav.vue";
import FooterNav from "./components/common/FooterNav.vue";
import { RouterLink, RouterView } from "vue-router";
import { userStore } from "@/stores/userPiniaStore";
const store = userStore();
let token = sessionStorage.getItem("access-token");
store.getUserInfo(token);
</script>

<template>
    <HeaderNav />

    <router-view></router-view>

    <footer-nav></footer-nav>
</template>

<style>
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css");
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
* {
    font-family: "Jua", sans-serif;
}
</style>
