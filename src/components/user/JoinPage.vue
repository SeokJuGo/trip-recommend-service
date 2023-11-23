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

// 명언 관련 스크립트
const quotes = ref([
    { text: "여행은 살아보는 거야, 공부하는 게 아니야.", author: "미르치아 엘리아데스" },
    { text: "세상을 보는 것은 책을 읽는 것보다 더 나은 교육이다.", author: "제임스 버먼" },
    { text: "여행은 당신을 변화시킨다. 당신은 항상 어떤식으로든지.", author: "안토니 로렌" },
    { text: "여행은 사랑하는 사람들과 함께하는 것이다.", author: "비언드 케르쉬너" },
    { text: "당신이 길을 가다 보면, 길이 당신을 찾아온다.", author: "작자 미상" },
    { text: "여행은 당신을 발견하는 것이다.", author: "청년이탈리아" },
    { text: "세상 어디든 당신이 가는 곳, 그곳이 여행지다.", author: "도로시 길먼" },
    { text: "여행은 살아보는 거야, 공부하는 게 아니야.", author: "미르치아 엘리아데스" },
    { text: "세상을 보는 것은 책을 읽는 것보다 더 나은 교육이다.", author: "제임스 버먼" },
    { text: "여행은 당신을 변화시킨다. 당신은 항상 어떤 식으로든지.", author: "안토니 로렌" },
    { text: "여행은 사랑하는 사람들과 함께하는 것이다.", author: "비언드 케르쉬너" },
    { text: "당신이 길을 가다 보면, 길이 당신을 찾아온다.", author: "작자 미상" },
    { text: "여행은 마음을 열고 눈을 뜨게 만든다.", author: "잭 케랙" },
    { text: "세계를 보는 것은 자기 자신을 보는 것이다.", author: "헤리 에이미슨" },
    { text: "여행은 삶을 더 크게 만든다.", author: "알렉산더 수퍼트" },
    { text: "당신이 가는 곳마다, 당신은 그곳의 일부가 된다.", author: "앤서니 부르덴" },
    { text: "여행은 언제나 새로운 시작이다.", author: "안소니 보덴" },
    { text: "세계는 책을 읽는 사람에게는 작은 마을이다.", author: "알렉산더 솔레제니친" },
    { text: "여행은 당신이 모르는 것을 알게 하는 가장 좋은 방법이다.", author: "마크 트웨인" },
    { text: "모험이 없는 여행은 새로운 경험이 아니다.", author: "알 프랭크" },
]);

const randomIndex = Math.floor(Math.random() * quotes.value.length);
const randomQuote = quotes.value[randomIndex];

const randomImageSrc = `https://source.unsplash.com/random/1080x1600/?landmark`;
const randomImageStyle = {
    minHeight: "90vh",
    backgroundImage: "url(" + randomImageSrc + ")",
    backgroundSize: "cover",
};
</script>

<template>
    <div class="container-md py-5">
        <!-- Section: Design Block -->
        <section class="text-center text-lg-start">
            <!-- Jumbotron -->
            <div class="container py-4">
                <div class="row g-0 align-items-center">
                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <div class="d-flex flex-column justify-content-center text-center text-shadow rounded-4 shadow-4"
                            :style="randomImageStyle">
                            <!-- <img :src="randomImageSrc" class="w-100 rounded-4 shadow-4" alt="" /> -->
                            <h4 class="mb-4 fst-italic">
                                {{ randomQuote?.text }}
                            </h4>
                            <p class="small mb-0">- {{ randomQuote?.author }} -</p>
                        </div>
                    </div>

                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <div class="card cascading-left"
                            style="background: hsla(0, 0%, 100%, 0.55); backdrop-filter: blur(30px)">
                            <div class="card-body p-5 shadow-5 text-center">
                                <h2 class="fw-bold mb-5">Sign up now</h2>
                                <form @submit.prevent>
                                    <!-- 2 column grid layout with text inputs for the first and last names -->
                                    <div class="row">
                                        <div class="col-md-10 mb-4">
                                            <div class="form-floating">
                                                <input type="text" id="form3Example1" class="form-control"
                                                    placeholder="Enter Your ID" v-model="user.username"
                                                    @blur="vaildUserName" />
                                                <label for="id">ID
                                                    <font-awesome-icon :icon="['fas', 'check']" size="lg"
                                                        style="color: #2bab3a" v-if="success.username" /></label>
                                            </div>
                                        </div>
                                        <div class="col-md-1 mb-4">
                                            <div class="form-floating">
                                                <button class="btn btn-dark btn text-white" @click="duplicatedCheck">
                                                    Check
                                                </button>

                                            </div>
                                        </div>

                                        <span :class="styleType.username">{{ notice.username }}</span>
                                    </div>



                                    <!-- Password input -->
                                    <div class="form-outline mb-4">
                                        <input type="password" id="form3Example3" class="form-control"
                                            placeholder="Enter password" v-model="user.password" @blur="vaildPassword" />

                                    </div>
                                    <span :class="styleType.password">{{ notice.password }}</span>
                                    <div class="form-outline mb-4">
                                        <input type="password" id="form4Example4" class="form-control" v-model="password2"
                                            placeholder="Check password" @blur="comparePassword" />
                                    </div>

                                    <span :class="styleType.passwordCheck">{{ notice.passwordCheck }}</span>
                                    <!-- Name input -->
                                    <div class="form-outline mb-4">
                                        <input type="name" id="form5Example5" class="form-control" v-model="user.nickname"
                                            placeholder="Enter name" @blur="vaildNickname" />
                                    </div>

                                    <span :class="styleType.nickname">{{ notice.nickname }}</span>
                                    <!-- Email input -->
                                    <div class="form-outline mb-4">
                                        <input type="email" id="form6Example6" class="form-control" v-model="user.email"
                                            placeholder="Enter Email" @blur="vaildEmail" />

                                    </div>
                                    <span :class="styleType.email">{{ notice.email }}</span>
                                    <br>

                                    <!-- Submit button -->
                                    <button type="submit" class="btn btn-primary btn-block mb-4" @click="signUp">
                                        Sign up
                                    </button>

                                    <!-- Register buttons -->
                                    <div class="text-center">
                                        <p>or sign up with:</p>
                                        <button type="button" class="btn btn-link btn-floating mx-1">
                                            <i class="fab fa-facebook-f"></i>
                                        </button>

                                        <button type="button" class="btn btn-link btn-floating mx-1">
                                            <i class="fab fa-google"></i>
                                        </button>

                                        <button type="button" class="btn btn-link btn-floating mx-1">
                                            <i class="fab fa-twitter"></i>
                                        </button>

                                        <button type="button" class="btn btn-link btn-floating mx-1">
                                            <i class="fab fa-github"></i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- <div class="col-lg-6 mb-5 mb-lg-0">
                                                                                                        <img
                                                                                                            src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg"
                                                                                                            class="w-100 rounded-4 shadow-4"
                                                                                                            alt=""
                                                                                                        />
                                                                                                    </div> -->
                </div>
            </div>
            <!-- Jumbotron -->
        </section>
        <!-- Section: Design Block -->
        <!-- <section class="container" style="height: 300px">
                                                                                		<div class="container p-5"></div>
		
                                                                                		<div
                                                                                			class="container w-25 mt-5 p-3 border border-3 border-success rounded-4">
                                                                                			<form @submit.prevent id="form-login" method="POST" action="">
                                                                                				<input type="hidden" name="action" value="login" />
                                                                                				<div class="form-floating m-3">
                                                                                					<input type="text" class="form-control" id="id"
                                                                                						placeholder="Enter id" name="id" v-model="user.username" @blur="changeNoticeId"/> <label for="id">ID</label>
                                                                                				</div>
                                                                                				<span :class="styleType.username">{{ notice.username }}</span>
                                                                                				<div class="form-floating m-3">
                                                                                					<input type="password" class="form-control" id="password"
                                                                                						placeholder="Enter password" name="password" v-model="user.password"  @blur="changeNoticePassword" /> <label for="pwd">Password</label>
                                                                                				</div>
                                                                                				<span :class="styleType.password">{{ notice.password }}</span>
                                                                                				<div class="form-floating m-3 d-flex justify-content-center">
                                                                                					<button type="button" class="btn btn-outline-success m-3"
                                                                                						id="btn-login" @click="signIn" >로그인</button>
                                                                                					<button type="button" class="btn btn-outline-success m-3"
                                                                                						id="btn-go-to-index" @click="preview">이전으로</button>
                                                                                				</div>
                                                                                			</form>
                                                                                		</div>
                                                                                	</section> -->
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

.cascading-left {
    margin-left: -50px;
}

.cascading-right {
    margin-right: -50px;
}

@media (max-width: 991.98px) {
    .cascading-left {
        margin-left: 0;
    }

    .cascading-right {
        margin-right: 0;
    }
}

.text-shadow {
    /* color: #fff; 텍스트 색상 */
    /* font-size: 24px; 텍스트 크기 */
    /* letter-spacing: 2px; 글자 간격 */

    /* 테두리 효과 */
    text-shadow: -1px -1px 0 #fff, 1px -1px 0 #fff, -1px 1px 0 #fff, 1px 1px 0 #fff;
}
</style>
