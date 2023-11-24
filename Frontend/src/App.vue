<script setup>
import HeaderNav from "./components/common/HeaderNav.vue";
import FooterNav from "./components/common/FooterNav.vue";
import { RouterView } from "vue-router";
import { userStore } from "@/stores/userPiniaStore";

const store = userStore();
let token = sessionStorage.getItem("access-token");
store.getUserInfo(token);
</script>

<template>
    <HeaderNav />

    <router-view></router-view>

    <FooterNav />
</template>

<style>
@import url("https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.0.0/mdb.min.css");
@import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css");
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css");
@import url("https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
* {
    font-family: "Jua", sans-serif;
}
</style>
