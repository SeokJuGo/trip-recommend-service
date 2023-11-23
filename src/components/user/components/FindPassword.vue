<script setup>
import emailjs from "emailjs-com";
import { ref } from "vue";
import { modify ,findByEmail} from "@/api/user";

const form = ref(null);
const userInfo = ref("");
const inputFieldReset = ref({
  name: userInfo.value.username,
  email: "",
  message: "",
});
const email = ref();
const sendMail = () => {
  find();
  emailjs
    .sendForm(
      "service_2lurd1r",
      "template_butrqam",
      form.value,
      "d8Cm9voiRtuCxJZ8t"
    )
    .then(
      () => {
        alert("임시 비밀번호가 발급되었습니다.!");
        inputFieldReset.value = " ";
      },
      (error) => {
        alert("Message not sent", error);
      }
    );
    update();
};

function rand(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}
const newPassword = ref(rand(10000000, 1000000000));
inputFieldReset.value.message = newPassword.value;
const newUser = ref({
  email: userInfo.value.email,
  nickname: userInfo.value.nickname,
  password: newPassword.value,
  username: userInfo.value.username,
});
const find = ()=>{
findByEmail(
  inputFieldReset.value.email,
    ({ data }) => {
      userInfo.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
}
const update=()=>{
  modify(
    newUser.value,
    ({ data }) => {
      attractionList.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
}


</script>

<template>
  <section>
    <div class="form-container">
      <p>유저 비밀번호 찾기</p>
      <form class="form" ref="form" @submit.prevent="sendMail">
        <div class="form-group">
          <input name="from_name" placeholder="name" type="hidden" required />
        </div>
        <div class="form-group">
          <input
            name="email"
            placeholder="email"
            type="email"
            :value="inputFieldReset.email"
            required />
        </div>
        <div class="form-group">
          <input
            placeholder="subject"
            type="hidden"
            required />
        </div>
        <div class="form-group">
          <input
            placeholder="telephone"
            type="text"
            required />
        </div>
        <div class="form-group">
          <textarea
            class="text-area"
            placeholder="message"
            type="text"
            style="display: none"
            required></textarea>
        </div>
        <div class="form-group">
          <input class="submit" type="submit" name="send" />
        </div>
      </form>
    </div>
  </section>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Comfortaa&display=swap");

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html,
body {
  font-size: 12px;
  overflow: hidden;
  font-family: "Comfortaa", cursive;
  color: white;
}

p {
  font-size: 30px;
  padding: 10px;
  font-weight: bold;
}

.form-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 50px auto;
  border-radius: 10px;
  padding: 10px;
  width: 500px;
}

.form-group {
  padding: 15px;
}

.form-group input {
  padding: 10px 10px 10px 10px;
  border: none;
  border-radius: 10px;
  width: 400px;
  outline: none;
  font-weight: 500;
}

.text-area {
  height: 150px;
  width: 400px;
  border: none;
  border-radius: 10px;
  padding: 10px;
  outline: none;
  font-family: "Comfortaa", cursive;
  font-weight: 500;
}
</style>
