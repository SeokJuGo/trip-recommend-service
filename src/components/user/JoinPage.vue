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
                        <div
                            class="d-flex flex-column justify-content-center text-center text-shadow rounded-4 shadow-4"
                            :style="randomImageStyle"
                        >
                            <!-- <img :src="randomImageSrc" class="w-100 rounded-4 shadow-4" alt="" /> -->
                            <h4 class="mb-4 fst-italic">
                                {{ randomQuote?.text }}
                            </h4>
                            <p class="small mb-0">- {{ randomQuote?.author }} -</p>
                        </div>
                    </div>

                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <div
                            class="card cascading-left"
                            style="background: hsla(0, 0%, 100%, 0.55); backdrop-filter: blur(30px)"
                        >
                            <div class="card-body p-5 shadow-5 text-center">
                                <h2 class="fw-bold mb-5">Sign up now</h2>
                                <form>
                                    <!-- 2 column grid layout with text inputs for the first and last names -->
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <input
                                                    type="text"
                                                    id="form3Example1"
                                                    class="form-control"
                                                />
                                                <label class="form-label" for="form3Example1"
                                                    >First name</label
                                                >
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <input
                                                    type="text"
                                                    id="form3Example2"
                                                    class="form-control"
                                                />
                                                <label class="form-label" for="form3Example2"
                                                    >Last name</label
                                                >
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Email input -->
                                    <div class="form-outline mb-4">
                                        <input
                                            type="email"
                                            id="form3Example3"
                                            class="form-control"
                                        />
                                        <label class="form-label" for="form3Example3"
                                            >Email address</label
                                        >
                                    </div>

                                    <!-- Password input -->
                                    <div class="form-outline mb-4">
                                        <input
                                            type="password"
                                            id="form3Example4"
                                            class="form-control"
                                        />
                                        <label class="form-label" for="form3Example4"
                                            >Password</label
                                        >
                                    </div>

                                    <!-- Checkbox -->
                                    <div class="form-check d-flex justify-content-center mb-4">
                                        <input
                                            class="form-check-input me-2"
                                            type="checkbox"
                                            value=""
                                            id="form2Example33"
                                            checked
                                        />
                                        <label class="form-check-label" for="form2Example33">
                                            Subscribe to our newsletter
                                        </label>
                                    </div>

                                    <!-- Submit button -->
                                    <button type="submit" class="btn btn-primary btn-block mb-4">
                                        Sign up
                                    </button>

                                    <!-- Register buttons -->
                                    <div class="text-center">
                                        <p>or sign up with:</p>
                                        <button
                                            type="button"
                                            class="btn btn-link btn-floating mx-1"
                                        >
                                            <i class="fab fa-facebook-f"></i>
                                        </button>

                                        <button
                                            type="button"
                                            class="btn btn-link btn-floating mx-1"
                                        >
                                            <i class="fab fa-google"></i>
                                        </button>

                                        <button
                                            type="button"
                                            class="btn btn-link btn-floating mx-1"
                                        >
                                            <i class="fab fa-twitter"></i>
                                        </button>

                                        <button
                                            type="button"
                                            class="btn btn-link btn-floating mx-1"
                                        >
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
