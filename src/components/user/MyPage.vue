<template>
	
	<div class="container-fluid bg-warning-subtle">
	  <div class="container-md pt-5 pb-1">
		  
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
			  <div
				class="mx-auto mt-3 col-lg-4 col-md-6 my-sm-auto ms-sm-auto me-sm-0"
			  >
				<div class="nav-wrapper position-relative end-0">
				  <ul
					class="p-1 bg-transparent nav nav-pills nav-fill"
					role="tablist"
				  >
					<li class="nav-item">
						
						<h4><router-link class="nav-link" :to="{ name: 'user-myplan' }" >계획</router-link></h4>
					</li>
					<li class="nav-item">
						
						<h4><router-link class="nav-link" :to="{ name: 'user-board' }" >게시글</router-link></h4>
					</li>
					<li class="nav-item">
						<h4><router-link class="nav-link" :to="{ name: 'user-info' }" >정보</router-link></h4>
					</li>
				  </ul>
				</div>
			  </div>
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

import {ref} from 'vue';
import {findById} from "@/api/user";
import { userStore} from "@/stores/userPiniaStore"

const store = userStore();
const userInfo = ref(store.userInfo);
console.log(userInfo.value);
	  </script>
	  <style scoped>
	.profile{
		width: 100px;
		height: 100px; 
		border-radius: 70%;
		overflow: hidden;
		}
	</style>
	