<script setup>
import { onMounted, reactive, ref, watch } from "vue";
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
const styleType = reactive({
  username: "error-message",
  password: "error-message",
});
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
function goToJoin() {
  router.push({ name: "join" });
}
function goToFind() {
  router.push({ name: "user-find" });
}

// 명언 관련 스크립트
const quotes = ref([
  {
    text: "여행은 살아보는 거야, 공부하는 게 아니야.",
    author: "미르치아 엘리아데스",
  },
  {
    text: "세상을 보는 것은 책을 읽는 것보다 더 나은 교육이다.",
    author: "제임스 버먼",
  },
  {
    text: "여행은 당신을 변화시킨다. 당신은 항상 어떤식으로든지.",
    author: "안토니 로렌",
  },
  {
    text: "여행은 사랑하는 사람들과 함께하는 것이다.",
    author: "비언드 케르쉬너",
  },
  { text: "당신이 길을 가다 보면, 길이 당신을 찾아온다.", author: "작자 미상" },
  { text: "여행은 당신을 발견하는 것이다.", author: "청년이탈리아" },
  {
    text: "세상 어디든 당신이 가는 곳, 그곳이 여행지다.",
    author: "도로시 길먼",
  },
  {
    text: "여행은 살아보는 거야, 공부하는 게 아니야.",
    author: "미르치아 엘리아데스",
  },
  {
    text: "세상을 보는 것은 책을 읽는 것보다 더 나은 교육이다.",
    author: "제임스 버먼",
  },
  {
    text: "여행은 당신을 변화시킨다. 당신은 항상 어떤 식으로든지.",
    author: "안토니 로렌",
  },
  {
    text: "여행은 사랑하는 사람들과 함께하는 것이다.",
    author: "비언드 케르쉬너",
  },
  { text: "당신이 길을 가다 보면, 길이 당신을 찾아온다.", author: "작자 미상" },
  { text: "여행은 마음을 열고 눈을 뜨게 만든다.", author: "잭 케랙" },
  {
    text: "세계를 보는 것은 자기 자신을 보는 것이다.",
    author: "헤리 에이미슨",
  },
  { text: "여행은 삶을 더 크게 만든다.", author: "알렉산더 수퍼트" },
  {
    text: "당신이 가는 곳마다, 당신은 그곳의 일부가 된다.",
    author: "앤서니 부르덴",
  },
  { text: "여행은 언제나 새로운 시작이다.", author: "안소니 보덴" },
  {
    text: "세계는 책을 읽는 사람에게는 작은 마을이다.",
    author: "알렉산더 솔레제니친",
  },
  {
    text: "여행은 당신이 모르는 것을 알게 하는 가장 좋은 방법이다.",
    author: "마크 트웨인",
  },
  { text: "모험이 없는 여행은 새로운 경험이 아니다.", author: "알 프랭크" },
]);

const randomIndex = Math.floor(Math.random() * quotes.value.length);
const randomQuote = quotes.value[randomIndex];

const randomImageSrc = `https://source.unsplash.com/random/1080x1920/?landmark`;
const randomImageStyle = {
  backgroundImage: "url(" + randomImageSrc + ")",
  backgroundSize: "cover",
};
</script>

<template>
  <section class="h-100 gradient-form">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-xl-10">
          <div class="card rounded-3 text-black">
            <div class="row g-0">
              <div class="col-lg-6">
                <div class="card-body p-md-5 mx-md-4">
                  <div class="text-center">
                    <img
                      src="@/assets/img/favicon.png"
                      style="width: 270px"
                      alt="logo" />
                    <h4 class="mt-3 mb-5 pb-1">당신 곁의 여행 가이드</h4>
                  </div>

                  <form>
                    <p>Please login to your account</p>

                    <div class="form-outline mb-4">
                      <input
                        type="email"
                        id="form2Example11"
                        class="form-control"
                        placeholder="Enter id"
                        v-model="user.username"
                        @blur="changeNoticeId" />

                      <span :class="styleType.username">{{
                        notice.username
                      }}</span>
                    </div>

                    <div class="form-outline mb-4">
                      <input
                        type="password"
                        id="form2Example22"
                        class="form-control"
                        placeholder="Enter password"
                        v-model="user.password"
                        @blur="changeNoticePassword" />
                      <span :class="styleType.password">{{
                        notice.password
                      }}</span>
                    </div>

                    <div class="text-center pt-1 mb-5 pb-1">
                      <button
                        class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"
                        type="button"
                        @click="signIn">
                        Log in
                      </button>
                      <button class="text-muted btn btn-outline-danger" type="button" @click="goToFind()"
                        >Forgot password?</button
                      >
                    </div>

                    <div
                      class="d-flex align-items-center justify-content-center pb-4">
                      <p class="mb-0 me-2">Don't have an account?</p>
                      <button
                        type="button"
                        class="btn btn-outline-success"
                        @click="goToJoin">
                        Create new
                      </button>
                    </div>
                  </form>
                </div>
              </div>
              <div
                class="col-lg-6 d-flex align-items-center gradient-custom-2"
                :style="randomImageStyle">
                <div class="text-white px-3 py-4 p-md-5 mx-md-4 text-shadow">
                  <h4 class="mb-4 fst-italic">
                    {{ randomQuote?.text }}
                  </h4>
                  <p class="small mb-0">- {{ randomQuote?.author }} -</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
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

.text-shadow {
  /* color: #fff; 텍스트 색상 */
  /* font-size: 24px; 텍스트 크기 */
  /* letter-spacing: 2px; 글자 간격 */

  /* 테두리 효과 */
  text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000,
    1px 1px 0 #000;
}
</style>
