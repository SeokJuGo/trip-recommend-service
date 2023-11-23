<script setup>

import paju from "@/assets/img/파주프로방스.png"
import song from "@/assets/img/송도한옥마을.jpg"
import eul from "@/assets/img/을왕리.jpg"
</script>

<template>
    <div class="d-flex justify-content-center mb-3">
			<h1
				class="display-4 fw-bold text-center border-bottom border-2 border-secondary">
				나의 여행 계획</h1>
		</div>
		<!-- Grid Row -->
		<div
			class="row align-items-center border rounded shadow mb-4 bg-info-subtle">
			<div class="col-lg-5 p-3 p-lg-3">
				<img class="rounded shadow w-100"
					:src="paju" alt="" />
			</div>
			<div class="col-lg-7 p-3 p-lg-3 pt-0">
				<h1 class="display-4 fw-bold">파주 여행 계획</h1>
				<ul class="lead">
					<li>DMZ 탐험: 비무장지대(DMZ)를 방문하여 한국전쟁의 역사를 알아보세요.</li>
					<li>헤이리 예술마을 방문: 독특한 미술관과 작업실, 카페를 만날 수 있는 예술마을입니다.</li>
					<li>임진각 평화공원: 평화의 종에 경의를 표하고 자유의 다리를 감상하세요.</li>
					<li>프로방스 마을: 다채로운 건물이 있는 그림 같은 프랑스풍 마을을 산책해보세요.</li>
					<li>오두산 통일전망대: 탁 트인 전망을 감상하고 남북관계에 대해 알아보세요.</li>
				</ul>
				<div class="text-end">
					<button type="button" class="btn btn-lg btn-secondary">상세보기</button>
					<button type="button" class="btn btn-lg btn-primary">계획수정</button>
					<button type="button" class="btn btn-lg btn-danger">계획삭제</button>
				</div>
			</div>
		</div>
		<!-- Grid Row -->
		<div
			class="row align-items-center border rounded shadow mb-4 bg-danger-subtle">
			<div class="col-lg-5 p-3 p-lg-3">
				<img class="rounded shadow w-100"
					:src="song" alt="" />
			</div>
			<div class="col-lg-7 p-3 p-lg-3 pt-0">
				<h1 class="display-4 fw-bold">송도 여행 계획</h1>
				<ul class="lead">
					<li>센트럴파크: 인공호수 옆에서 휴식을 취하거나, 자전거를 타거나, 여유롭게 산책을 즐겨보세요.</li>
					<li>송도 스카이워크: 바닥이 유리로 된 다리에서 도시의 멋진 전망을 감상해보세요.</li>
					<li>트라이볼(Tri-Bowl): 미술 전시와 공연을 즐길 수 있는 미래형 복합문화공간을 방문해 보세요.</li>
					<li>커낼워크(Canal Walk): 해안가 쇼핑 및 식사 지구를 탐험해보세요.</li>
					<li>송도 한옥마을: 현대적인 환경 속에서 한국의 전통 건축물을 만나보세요.</li>
				</ul>
				<div class="text-end">
					<button type="button" class="btn btn-lg btn-secondary">상세보기</button>
					<button type="button" class="btn btn-lg btn-primary">계획수정</button>
					<button type="button" class="btn btn-lg btn-danger">계획삭제</button>
				</div>
			</div>
		</div>
		<!-- Grid Row -->
		<div
			class="row align-items-center border rounded shadow bg-primary-subtle">
			<div class="col-lg-5 p-3 p-lg-3">
				<img class="rounded shadow w-100" :src="eul"
					alt="" />
			</div>
			<div class="col-lg-7 p-3 p-lg-3 pt-0">
				<h1 class="display-4 fw-bold">을왕리 여행 계획</h1>
				<ul class="lead">
					<li>을왕리 해수욕장: 일광욕과 수상스포츠, 해변 식사를 즐길 수 있습니다.</li>
					<li>을왕리 스카이워크: 바다 위를 걸으며 숨막히는 해안 풍경을 감상하세요.</li>
					<li>을왕리오름: 이 화산 오름에 올라 해안의 아름다운 풍경을 감상하세요.</li>
					<li>을왕리 어촌마을: 전통 어촌마을을 체험하고 신선한 해산물을 맛보세요.</li>
					<li>을왕리 수상스포츠: 패러세일링, 윈드서핑, 제트스키를 타고 아드레날린을 분출해보세요.</li>
				</ul>
				<div class="text-end">
					<button type="button" class="btn btn-lg btn-secondary">상세보기</button>
					<button type="button" class="btn btn-lg btn-primary">계획수정</button>
					<button type="button" class="btn btn-lg btn-danger">계획삭제</button>
				</div>
			</div>
		</div>
</template>

<style scoped>

</style>