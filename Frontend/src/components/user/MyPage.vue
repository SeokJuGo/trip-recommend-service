<template>
	
			

    <div class="container-fluid bg-success-subtle">
        <div class="container-md">
			

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
	