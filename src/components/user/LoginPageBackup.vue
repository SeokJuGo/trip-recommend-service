<script setup>
import { onMounted, reactive, ref } from "vue";
import { userStore } from "@/stores/userPiniaStore";

import { useRouter } from "vue-router";
import { toast } from "vue3-toastify";
const store = userStore();
const user = reactive({
    username: "",
    password: "",
});
const router = useRouter();
const notice = reactive({ username: "", password: "" });
const styleType = reactive({ username: "error-message", password: "error-message" });
function changeNoticeId() {
    if (user.username.length < 4) {
        notice.username = "아이디는 4글자보다 커야합니다.";
        styleType.username = "error-message";
    } else if (user.username.length > 10) {
        notice.username = "아이디는 10자이하여야 합니다.";
        styleType.username = "error-message";
    } else {
        notice.username = "";
    }
}
function changeNoticePassword() {
    if (user.password.length < 8) {
        notice.password = "비밀번호는는 8글자보다 커야합니다.";
    } else if (user.password.length > 200) {
        notice.password = "비밀번호는 200자이하여야 합니다.";
    } else {
        // 성공 시

        notice.password = "";
    }
}
// 쿠키 아이디 저장
async function storeIDByCookie(username) {
    const cookies = document.cookie;
    const cookieArray = cookies.split("; ");
    document.cookie = `username=${username}`;
}
// 로그인 함수
async function signIn() {
    console.log(user);
    await store.userConfirm(user);
    let token = sessionStorage.getItem("access-token");
    console.log("1. confirm() token >> " + token);
    if (store.isLogin) {
        await store.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", store.userInfo);
        if (store.userInfo.rolename == "ADMIN") {
            toast.success(store.userInfo.username + "관리자 님 환영합니다!", {
                autoClose: 1000,
            });
        } else {
            toast.success(store.userInfo.name + "님 환영합니다!", {
                autoClose: 1000,
            });
        }
        console.log();
        await storeIDByCookie(user.username);
        router.push({ name: "main" });
    } else {
        error.message = "아이디 또는 비밀번호가 잘못되었습니다.";
    }
}
function preview() {
    router.go(-1);
}
</script>

<template>
    <div class="container-fluid pt-5" style="height: 75vh">
        <section class="container" style="height: 300px">
            <div class="container p-5"></div>

            <div class="container w-25 mt-5 p-3 border border-3 border-success rounded-4">
                <form @submit.prevent id="form-login" method="POST" action="">
                    <input type="hidden" name="action" value="login" />
                    <div class="form-floating m-3">
                        <input
                            type="text"
                            class="form-control"
                            id="id"
                            placeholder="Enter id"
                            name="id"
                            v-model="user.username"
                            @blur="changeNoticeId"
                        />
                        <label for="id">ID</label>
                    </div>
                    <span :class="styleType.username">{{ notice.username }}</span>
                    <div class="form-floating m-3">
                        <input
                            type="password"
                            class="form-control"
                            id="password"
                            placeholder="Enter password"
                            name="password"
                            v-model="user.password"
                            @blur="changeNoticePassword"
                        />
                        <label for="pwd">Password</label>
                    </div>
                    <span :class="styleType.password">{{ notice.password }}</span>
                    <div class="form-floating m-3 d-flex justify-content-center">
                        <button
                            type="button"
                            class="btn btn-outline-success m-3"
                            id="btn-login"
                            @click="signIn"
                        >
                            로그인
                        </button>
                        <button
                            type="button"
                            class="btn btn-outline-success m-3"
                            id="btn-go-to-index"
                            @click="preview"
                        >
                            이전으로
                        </button>
                    </div>
                </form>
            </div>
        </section>
    </div>
</template>

<style scoped>
.success-message {
    color: rgba(57, 158, 240, 0.829);
    font-size: 14px;
}
.error-message {
    color: rgba(231, 78, 78, 0.829);
    font-size: 14px;
}
</style>
