<script setup>
import { onMounted, reactive, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { toast } from "vue3-toastify";
import { idCheck, join } from "@/api/user";

const router = useRouter();

//사용자
const user = reactive({
    username: "",
    password: "",
    nickname: "",
    email: "",
});
//아이디 체크
const password2 = ref("");
const notice = reactive({ username: "", password: "", passwordCheck: "", nickname: "", email: "" });
const styleType = reactive({
    username: "error-message",
    password: "error-message",
    passwordCheck: "error-message",
    nickname: "error-message",
    email: "error-message",
});
const success = reactive({
    username: false,
    password: false,
    passwordCheck: false,
    nickname: false,
    email: false,
});
var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
const vaildUserName = () => {
    if (user.username.length < 4 || user.username.length > 15) {
        notice.username = "아이디는 4자 이상 15자 미만이어야 합니다.";
        styleType.username = "error-message";
        success.username = false;
    } else if (regExp.test(user.username)) {
        notice.username = "아이디는 영어 또는 숫자만 사용 가능합니다.";
        styleType.username = "error-message";
        success.username = false;
    } else {
        notice.username = "";
        styleType.username = "success-message";
        success.username = false;
    }
};
const vaildPassword = () => {
    if (user.password.length < 8 || user.password.length > 20) {
        notice.password = "비밀번호는 8자 이상 20자 미만이어야 합니다.";
        styleType.password = "error-message";
        success.password = false;
    } else {
        notice.password = "";
        success.password = true;
        styleType.password = "success-message";
    }
};
const comparePassword = () => {
    if (user.password != password2.value) {
        notice.passwordCheck = "비밀번호를 확인해주세요";
        styleType.passwordCheck = "error-message";
        success.passwordCheck = false;
    } else {
        notice.passwordCheck = "";
        styleType.passwordCheck = "success-message";
        success.passwordCheck = true;
    }
};
const vaildNickname = () => {
    if (user.nickname.length < 2 || user.nickname.length > 10) {
        notice.nickname = "이름은 2자 이상 10자 미만이어야 합니다.";
        styleType.nickname = "error-message";
        success.nickname = false;
    } else {
        notice.nickname = "";
        success.nickname = true;
        styleType.nickname = "success-message";
    }
};
const emailCheck = (email_address) => {
    var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
    if (!email_regex.test(email_address)) {
        return false;
    } else {
        return true;
    }
};
const vaildEmail = () => {
    if (!emailCheck(user.email)) {
        notice.email = "이메일 형식을 확인 ex) user@gmail.com";
        styleType.email = "error-message";
        success.email = false;
    } else {
        notice.email = "";
        success.email = true;
        styleType.email = "success-message";
    }
};
const isJoinPossible = (obj) => {
    for (const key in obj) {
        if (!obj[key]) {
            return false;
        }
    }
    return true;
};

const duplicatedCheck = () => {
    if (notice.username != "") return;
    idCheck(
        user.username,
        ({ data }) => {
            console.log(data);
            if (data == 0) {
                notice.username = "사용가능한 아이디입니다.";
                styleType.username = "success-message";
                success.username = true;
            } else {
                notice.username = "중복된 아이디입니다.";
                styleType.username = "error-meesage";
            }
        },
        (error) => {
            console.log(error);
        }
    );
};

//회원가입
const signUp = () => {
    const areAllSuccessTrue = () => isJoinPossible(success);
    const result = areAllSuccessTrue();
    if (!result) return;
    join(
        user,
        ({ data }) => {
            router.push({ name: "login" });
        },
        (error) => {
            console.log("no");
        }
    );
};
const preview = () => {
    router.go(-1);
};
</script>

<template>
    <section>
        <div class="container w-25 mt-5 mb-5 p-5 border border-3 border-success rounded-4">
            <div class="d-flex justify-content-center fs-2 p-3">회원가입</div>
            <form @submit.prevent id="form-join" method="POST" action="">
                <input type="hidden" name="action" value="join" />
                <div class="row">
                    <div class="col-md-6 form-floating m-3">
                        <input
                            type="text"
                            class="form-control"
                            id="id"
                            placeholder="Enter email"
                            name="id"
                            v-model="user.username"
                            @blur="vaildUserName"
                        />
                        <label for="id"
                            >ID
                            <font-awesome-icon
                                :icon="['fas', 'check']"
                                size="lg"
                                style="color: #2bab3a"
                                v-if="success.username"
                        /></label>
                    </div>
                    <div class="col-md-3 form-floating m-3">
                        <button class="btn btn-dark btn text-white" @click="duplicatedCheck">
                            중복<br />확인
                        </button>
                    </div>
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
                        @blur="vaildPassword"
                    />
                    <label for="pwd"
                        >Password
                        <font-awesome-icon
                            :icon="['fas', 'check']"
                            size="lg"
                            style="color: #2bab3a"
                            v-if="success.password"
                    /></label>
                </div>

                <span :class="styleType.password">{{ notice.password }}</span>
                <div class="form-floating m-3">
                    <input
                        type="password"
                        class="form-control"
                        id="password2"
                        placeholder="Enter password"
                        name="password2"
                        v-model="password2"
                        @blur="comparePassword"
                    />
                    <label for="pwd2"
                        >Password
                        <font-awesome-icon
                            :icon="['fas', 'check']"
                            size="lg"
                            style="color: #2bab3a"
                            v-if="success.passwordCheck"
                    /></label>
                </div>

                <span :class="styleType.passwordCheck">{{ notice.passwordCheck }}</span>
                <div class="form-floating m-3">
                    <input
                        type="text"
                        class="form-control"
                        id="name"
                        placeholder="Enter name"
                        name="name"
                        v-model="user.nickname"
                        @blur="vaildNickname"
                    />
                    <label for="name"
                        >Name
                        <font-awesome-icon
                            :icon="['fas', 'check']"
                            size="lg"
                            style="color: #2bab3a"
                            v-if="success.nickname"
                    /></label>
                </div>
                <span :class="styleType.nickname">{{ notice.nickname }}</span>
                <div class="form-floating m-3">
                    <input
                        type="text"
                        class="form-control"
                        id="email"
                        placeholder="Enter email"
                        name="email"
                        v-model="user.email"
                        @blur="vaildEmail"
                    />
                    <label for="email"
                        >Email
                        <font-awesome-icon
                            :icon="['fas', 'check']"
                            size="lg"
                            style="color: #2bab3a"
                            v-if="success.email"
                    /></label>
                </div>
                <span :class="styleType.email">{{ notice.email }}</span>
                <div class="form-floating m-3 d-flex justify-content-center">
                    <button
                        type="button"
                        id="btn-join"
                        class="btn btn-outline-success m-3"
                        @click="signUp"
                    >
                        회원가입
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
