<script setup>
import emailjs from 'emailjs-com';
import { modify ,findByEmail} from "@/api/user";
import {ref} from 'vue';

    const form = ref(null);
    const inputFieldReset = ref(null);

    const sendMail = () => {
      find()
      emailjs
    .sendForm(
      "service_2lurd1r",
      "template_butrqam",
      form.value,
      "d8Cm9voiRtuCxJZ8t"
    )
        .then(() => {
          alert("비밀번호를 보냈습니다!")
          inputFieldReset.value = " ";
        }, (error) => {
          alert('Message not sent', error);
        }); 
        userInfo.value.password=message.value;
        update()
      }
      function rand(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}
const message = ref(rand(10000000, 1000000000));
const email = ref();
const userInfo = ref({"email":"",
                      "id":"",
                      "nickanme":"",
                    "rolename":"",
                    "username":"",
                    "password":""});
const find = ()=>{
findByEmail(
   email.value,
    ({ data }) => {
      userInfo.value = data;
      console.log(userInfo.value)
    },
    (err) => {
      console.log(err);
    }
  );
}

const update=()=>{
  
  modify(
    userInfo.value,
    ({ data }) => {
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
  <p>비밀번호 찾기</p>
  <form class="form" ref="form" @submit.prevent="sendMail" >
  <div class="form-group">
  <input name="from_name" placeholder="name" type="hidden" value="석주" required>
  </div>
  <div class="form-group">
  <input name="email" placeholder="email" type="email" v-model="email" required>
  </div>
  <div class="form-group">
  <input name="subject" placeholder="subject" type="hidden" value="dd" required>
  </div>
  <div class="form-group">
  <textarea style="display:none" name="message" placeholder="message" type="hidden" :value="message" required></textarea>
  </div>
  <div class="form-group">
  <input class="submit" type="submit" name="send" />
  </div>
  </form>
  </div>
  </section>
  </template> 
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Comfortaa&display=swap');

*{
 margin: 0;
 padding: 0;
 box-sizing: border-box;
}

html, body {
 font-size: 12px;
 overflow: hidden;
 font-family: 'Comfortaa', cursive;
 color: white;
}

p{
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

.form-group{
 padding: 15px;
}

.form-group input{
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
 font-weight: 500;
}

.form-group .submit{
 border: none;
 border-radius:5px;
 height: 40px;
 width: 70px ;
 cursor: pointer;
 font-weight: 500;
}
</style>
