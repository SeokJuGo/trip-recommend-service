<template>
	
			
	<div class="container-fluid bg-warning-subtle">
		<div class="container-md pt-5 pb-1">
			<div class="col-4">
		</div>
			<div class="card card-body mx-3 mx-md-4 mt-n6">
		 

			  <div class="row gx-4">
			  <div class="col-auto">
				<div class="avatar avatar-xl position-relative">
				  <img
					src="@/assets/img/강릉.jpg"
					alt="profile_image"
					class="profile shadow-sm w-100 border-radius-lg"
	
				  />
				</div>
			  </div>
			  <div class="col-auto my-auto">
				<div class="h-100">
				  <h5 class="mb-1">{{ userInfo.nickname }}</h5>
				  <p class="mb-0 font-weight-normal text-sm">{{ userInfo.email }}</p>
				</div>
			  </div>
			  
				  
			<user-nav></user-nav>
					
			</div>
			
			<div class="row">
				<div class="nav-wrapper position-relative end-0">
				  <ul
					class="p-1 bg-transparent nav nav-pills nav-fill"
					role="tablist"
				  >
					<li class="nav-item">
						
					  
					</li>	
					<li class="nav-item">
					</li>
					<li class="nav-item">
					  
					</li>
				  </ul>
				</div>
					<router-view/>
			</div>
		  </div>
		  
	</div>
</div>
	  </template>
	  
	  <script setup>
	  import { RouterLink, RouterView } from "vue-router";
	  import UserNav from "./UserNav.vue";
		import {findById} from "@/api/user";
		import { userStore} from "@/stores/userPiniaStore"
		import { defineComponent, ref, watch } from 'vue';
  		import { MailOutlined, QqOutlined, AppstoreOutlined, SettingOutlined } from '@ant-design/icons-vue';
		const store = userStore();
		const userInfo = ref(store.userInfo);
		console.log(userInfo.value);
		
  
    
      const selectedKeys = ref(['1']);
      const openKeys = ref(['sub1']);
      const handleClick = e => {
        console.log('click', e);
      };
      const titleClick = e => {
        console.log('titleClick', e);
      };
      watch(openKeys, val => {
        console.log('openKeys', val);
      });
     
	  </script>
	  <style scoped>
	.profile{
		width: 100px;
		height: 100px; 
		border-radius: 70%;
		overflow: hidden;
		}
	</style>
	