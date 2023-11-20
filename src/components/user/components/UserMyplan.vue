<template>
<div class="mt-3 row">
				<div class="col-12"> 
					
				 	 <div class="mb-5 ps-3">
						<h2 class="mb-1">내 여행 계획</h2>
						<h6 class="mb-1">Projects</h6>
					<p class="text-sm">Architects design houses</p>
					</div>
				  
				</div>
				</div>
			  <div class="mt-3 row">
				
					
					<my-plan-card
					title="Minimalist"
					:image="img1"
					label="Project #3"
					description="Different people have different taste, and various types
						  of music."
					:action="{
					  color: 'success',
					  label: 'View Project',
					}"
				  />
				  <my-plan-card
					title="Minimalist"
					:image="img1"
					label="Project #3"
					description="Different people have different taste, and various types
						  of music."
					:action="{
					  color: 'success',
					  label: 'View Project',
					}"
				  />
				  <my-plan-card
					title="Minimalist"
					:image="img1"
					label="Project #3"
					description="Different people have different taste, and various types
						  of music."
					:action="{
					  color: 'success',
					  label: 'View Project',
					}"
				  />
				  <my-plan-card
					title="Minimalist"
					:image="img1"
					label="Project #3"
					description="Different people have different taste, and various types
						  of music."
					:action="{
					  color: 'success',
					  label: 'View Project',
					}"
				  />
				  </div>

</template>
<script setup>

      import img1 from "@/assets/img/강릉.jpg"
	  import MyPlanCard from "../items/MyPlanCard.vue";
</script>