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
			  <h5 class="mb-1">Richard Davis</h5>
			  <p class="mb-0 font-weight-normal text-sm">CEO / Co-Founder</p>
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
					
					<h4>계획</h4>
				</li>
				<li class="nav-item">
					
					<h4>게시글</h4>
				</li>
				<li class="nav-item">
				  <h4>내 정보</h4>
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
					
				  <h1><font-awesome-icon :icon="['fas', 'location-dot']" bounce size="2xl" style="color: #2a69d5;" /></h1>
				</li>	
				<li class="nav-item">
				</li>
				<li class="nav-item">
				  
				</li>
			  </ul>
			</div>
			<div class="mt-3 row">
			<div class="col-12"> 
				
			  <div class="mb-5 ps-3">
				<h2 class="mb-1">내 여행 계획</h2>
				<p class="text-sm">Architects design houses</p>
				
			  </div>
			  
			</div>
			</div>
		  <div class="mt-3 row">
			
			<div class="col-12 position-relative">
				
			  <profile-info-card
				title="Profile Information"
				description="Hi, I’m Alec Thompson, Decisions: If you can’t decide, the answer is no. If two equally difficult paths, choose the one more painful in the short term (pain avoidance is creating an illusion of equality)."
				:info="{
				  fullName: 'Alec M. Thompson',
				  mobile: '(44) 123 1234 123',
				  email: 'alecthompson@mail.com',
				  location: 'USA',
				}"
			  />
			  <hr class="vertical dark" />
			</div>
			<div class="mt-4 col-12 col-xl-4 mt-xl-0">
			  <div class="card card-plain h-100">
				<div class="p-3 pb-0 card-header">
				  <h6 class="mb-0">Conversations</h6>
				</div>
				<div class="p-3 card-body">
				  <ul class="list-group">
					
				  </ul>
				</div>
			  </div>
			</div>
		  </div>
		</div>
	  </div>
	  </div>
</div>
  </template>
  
  <script setup>
  import ProfileInfoCard from "./components/ProfileInfoCard.vue";
  

  
	

  </script>
  <style scoped>
.profile{
	width: 100px;
    height: 100px; 
    border-radius: 70%;
    overflow: hidden;
	}
</style>