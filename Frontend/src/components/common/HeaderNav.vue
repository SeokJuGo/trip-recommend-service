<script setup>
import { onMounted } from "vue";
import logo from "@/assets/img/logo.png"
import { userStore} from "@/stores/userPiniaStore"
import { useRouter, useRoute } from "vue-router";
import { toast } from "vue3-toastify";
const router = useRouter();
const store = userStore();

async function logout() {
  console.log("로그아웃 -> " + store.userInfo.username);
  await store.userLogout(store.userInfo.username);
  toast.success("로그아웃 완료", {
    
    autoClose: 2000,
  });
  router.push({ name: "main" }); // 메인 페이지로 이동
}


</script>

<template>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-md navbar-light bg-white shadow fixed-top">
        <div class="container-md">
            <a class="navbar-brand" href="/">
                <img
                    class="d-inline-block align-text-center"
                    :src="logo"
                    width="192"
                    height="54"
                    alt="Logo"
                />
                <!-- <strong>&nbsp;EpicKorea.com</strong> -->
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
                        <router-link class="nav-link" :to="{ name: 'plan' }"
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
                    <li class="nav-item" >
                        <router-link class="nav-link" :to="{ name: 'user' }" v-show="!store.isLogin">로그인</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{ name: 'join' }" v-show="!store.isLogin">회원가입</router-link>
                    </li>
                </ul>

                <ul class="navbar-nav ms-auto" id="logout">
                    <li class="nav-item">
                        <a class="nav-link" @click="logout()" v-show="store.isLogin">로그아웃</a>
                    </li>
                    <li class="nav-item" v-show="store.isLogin">
                        <router-link class="nav-link" :to="{ name: 'mypage' }"
                            >마이페이지</router-link
                        >
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<style scoped></style>
