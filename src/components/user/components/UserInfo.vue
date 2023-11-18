<template>
<profile-info-card
				title="Profile Information"
				description="즐거운 여행"
				:info="{
				  fullName: userDetail.nickname,
				  mobile: '(44) 123 1234 123',
				  email: userDetail.email,
				  location: 'USA',
				}"
			  />

</template>
<script setup>
import ProfileInfoCard from "../items/UserInfoCard.vue";
import {onMounted,ref,reactive} from 'vue';
import {findById} from "@/api/user";
import { userStore} from "@/stores/userPiniaStore"
const store = userStore();
const username = ref(store.userInfo.username);
const userDetail = ref("");


console.log(userDetail);
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
<style></style>
