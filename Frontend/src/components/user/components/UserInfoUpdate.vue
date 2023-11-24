<template>
  
	<div class="container w-30 mb-5 p-5 ">

		<div class="d-flex justify-content-center fs-2 p-3">내 정보 수정</div>
		<h3>내 정보</h3>
		<form id="form-join" method="POST" action="">
			<input type="hidden" name="action" value="join" />
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="id"
					placeholder="Enter email" name="id" :value="user.username"
					readonly /> <label for="id">ID <font-awesome-icon :icon="['fas', 'check']" size="lg" style="color: #2bab3a;" v-if="success.username" /></label>
			</div>
			<div class="form-floating m-3">
				<input type="password" class="form-control" id="password"
					placeholder="Enter password" name="password" v-model="user.password" @blur="vaildPassword"/> <label for="pwd">Password  <font-awesome-icon :icon="['fas', 'check']" size="lg" style="color: #2bab3a;" v-if="success.password" /></label>
					
			</div>

			<span :class="styleType.password">{{ notice.password}}</span>
			<div class="form-floating m-3">
				<input type="password" class="form-control" id="password2"
					placeholder="Enter password" name="password2" v-model="password2" @blur="comparePassword" /> <label for="pwd2">Password <font-awesome-icon :icon="['fas', 'check']" size="lg" style="color: #2bab3a;" v-if="success.passwordCheck" /></label>
			</div>

			<span :class="styleType.passwordCheck">{{ notice.passwordCheck}}</span>
            <div class="form-floating m-3">
				<input type="text" class="form-control" id="name"
					placeholder="Enter name" name="name" v-model="user.nickname" @blur="vaildNickname"/> <label for="name">Name <font-awesome-icon :icon="['fas', 'check']" size="lg" style="color: #2bab3a;" v-if="success.nickname" /></label>
			</div>
			<span :class="styleType.nickname">{{ notice.nickname}}</span>
            <div class="form-floating m-3">
				<input type="text" class="form-control" id="email"
					placeholder="Enter email" name="email" v-model="user.email" @blur="vaildEmail"/> <label for="email">Email <font-awesome-icon :icon="['fas', 'check']" size="lg" style="color: #2bab3a;" v-if="success.email" /></label>
			</div>
			<span :class="styleType.email">{{ notice.email}}</span>
			<h3>프로필 </h3>
			<ProfileInputFormVue @on-change-files="onChangeFiles" />
			<div class="form-floating m-3 d-flex justify-content-end">
				<button type="button" class="btn btn-lg btn-outline-danger w-10 " @click="update">수정하기</button>
			</div>
		</form>
  </div>


</template>
<script setup>
import {onMounted,ref,reactive} from 'vue';
import {findById, modify} from "@/api/user";
import { userStore} from "@/stores/userPiniaStore";
import { useRouter, useRoute } from "vue-router";
import ProfileInputFormVue from '../common/ProfileInputForm.vue';
import ProfileAPI from "@/api/profile.js";

const store = userStore();
const username = ref(store.userInfo.username);	
const userid = ref(store.userInfo.id);

const user = ref("");
  onMounted(() => {
    findById(
    username.value,
    ({ data }) => {
      user.value=data.userInfo;
    },
    (err) => {
      console.log(err);
    }
  );
  console.log
});



const router = useRouter();

//아이디 체크
const password2=ref("");
const notice = reactive({password:"",passwordCheck:"",nickname:"",email:"",})
const styleType = reactive({
password:"error-message",
asswordCheck:"error-message",
nickname:"error-message",
email:"error-message",})
const success = reactive({username:true,password:false,passwordCheck:false,nickname:true,email:true})
var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;

const vaildPassword=()=>{
	if(user.value.password.length<8 || user.value.password.length>20 ){
		notice.password="비밀번호는 8자 이상 20자 미만이어야 합니다.";;
		styleType.password="error-message";
		success.password=false;
	}else{
		notice.password="";
		success.password=true;
		styleType.password="success-message";
	}
	
}
const comparePassword=()=>{
	if(user.value.password!=password2.value){
		notice.passwordCheck="비밀번호를 확인해주세요";
		styleType.passwordCheck="error-message";
		success.passwordCheck=false;
	}else{
		notice.passwordCheck="";
		styleType.passwordCheck="success-message";
		success.passwordCheck=true;
	}
	
}
const vaildNickname=()=>{
	if(user.value.nickname.length<2 || user.value.nickname.length>15 ){
		notice.nickname="이름은 2자 이상 15자 미만이어야 합니다.";;
		styleType.nickname="error-message";
		success.nickname=false;
	}else{
		notice.nickname="";
		success.nickname=true;
		styleType.nickname="success-message";
	}
	
}
const emailCheck=(email_address)=>{     
	var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	if(!email_regex.test(email_address)){ 
		return false; 
	}else{
		return true;
	}
}
const vaildEmail=()=>{
	if(!emailCheck(user.value.email)){
		notice.email="이메일 형식을 확인 ex) user@gmail.com";;
		styleType.email="error-message";
		success.email=false;
	}else{
		notice.email="";
		success.email=true;
		styleType.email="success-message";
	}
	
}
const isJoinPossible = (obj) => {
  for (const key in obj) {
    if (!obj[key]) {
      return false;
    }
  }
  return true;
};

const files = ref([]);
const onChangeFiles = (updatedFiles) => {
    files.value = updatedFiles;
    console.log("[files.vue] onChangeFiles() >> ", files);
};

//수정
const update=async() =>{
	const areAllSuccessTrue = () => isJoinPossible(success);
	const result = areAllSuccessTrue();
	if(!result) return;
    console.log(user.value);
	await modify(
        user.value,
        ({ data }) => {
			router.push({ name: "user-info" }); 
        },
        (error) => {
		  console.log("no");
        },
      );
	  if (files.value.length != 0) {
        await ProfileAPI.uploadFiles(userid.value, files.value)
            .then((response) => {})
            .catch((error) => {
                console.log(" uploadFiles(), Error >> ", error);
            });
    }
}
const preview=()=>{
	router.go(-1);
}
</script>
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