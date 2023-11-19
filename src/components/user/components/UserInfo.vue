<template>
  
	<div class="container w-30 mb-5 p-5 ">

		<div class="d-flex justify-content-center fs-2 p-3">내 정보</div>
		<form id="form-join" method="POST" action="">
			<input type="hidden" name="action" value="join" />
			<div class="form-floating m-3 ">
				<input type="text" class="form-control " id="id"
					placeholder="Enter email" name="id" :value="userDetail.username" readonly/> 
			</div>
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="name"
					placeholder="Enter name" name="name" :value="userDetail.nickname" readonly /> 
			</div>
			<div class="form-floating m-3">
				<input type="text" class="form-control col-lg-3" id="email"
					placeholder="Enter email" name="email" :value="userDetail.email" readonly/> 
			</div>
			<div class="form-floating m-3 d-flex justify-content-end">
				<button type="button" class="btn btn-lg btn-outline-dark w-10 " @click="modify">내 정보 수정</button>
			</div>
		</form>
  </div>


</template>
<script setup>
import {onMounted,ref,reactive} from 'vue';
import {findById} from "@/api/user";
import { userStore} from "@/stores/userPiniaStore"
import { useRouter, useRoute } from "vue-router";
const store = userStore();
const username = ref(store.userInfo.username);
const userDetail = ref("");

const router = useRouter();
const modify = ()=>router.push({ name: "user-update" }); 

  onMounted(() => {
    findById(
    username.value,
    ({ data }) => {
      userDetail.value=data.userInfo;
    },
    (err) => {
      console.log(err);
    }

    
  );
});
</script>
<style scoped>
.info {
  text-align:right;
}
</style>
