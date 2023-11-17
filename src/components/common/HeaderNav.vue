<script setup>
import logo from "@/assets/img/logo.png";
import { userStore} from "@/stores/userPiniaStore";
import { logout } from "@/api/user";

const store = userStore();

let token = sessionStorage.getItem("access-token");
store.getUserInfo(token);
const username = store.userInfo;
console.log(username);

</script>

<template>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-md navbar-light bg-light shadow fixed-top">
        <div class="container-md">
            <a class="navbar-brand" href="/">
                <img
                    class="d-inline-block align-text-center"
                    :src="logo"
                    width="90"
                    height="50"
                    alt="Logo"
                />
                <strong>&nbsp;EnjoyTrip.com</strong>
            </a>
            <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#mynavbar"
            >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mynavbar">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'attraction' }"
                            >지역별여행지
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'myplan' }"
                            >나의여행계획
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'hotplace' }"
                            >핫플자랑하기
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'share' }"
                            >여행지정보공유
                        </router-link>
                    </li>
                </ul>

                <ul class="navbar-nav ms-auto" id="login">
                    <i class="mt-auto mb-auto bi bi-person-circle"></i>
                    <li class="nav-item" >
                        <router-link class="nav-link" :to="{ name: 'user' }" v-show="!store.isLogin">로그인</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'join' }" v-show="!store.isLogin">회원가입</router-link>
                    </li>
                </ul>

                <ul class="navbar-nav ms-auto" id="logout">
                    <i class="mt-auto mb-auto bi bi-person-circle"></i>
                    <li class="nav-item">
                        <a class="nav-link"  v-show="store.isLogin">로그아웃</a>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'mypage' }"
                        v-show="store.isLogin">마이페이지</router-link
                        >
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<style scoped></style>
