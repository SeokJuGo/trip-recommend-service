<script setup>
import { ref } from "vue";
import api from "axios";
import { onMounted } from "vue";
const boards = ref([]);
const boardlist = async () => {
  await api
    .get(`http://localhost:8080/enjoytrip/board`, {
      params: {
        pgno: "1",
        key: "",
        word: "",
        spp: 5,
      },
    })
    .then(({ data }) => {
      boards.value = data;
      console.log("1. boards data >> ", boards.value);
      //console.log("----------------------------->", response);
    })
    .catch((e) => {
      console.log("1. boards catch >> ", e);
    });
};

onMounted(() => {
  boardlist();
});
</script>

<template>
<div class="container-fluid bg-danger-subtle">
	<div class="container-md py-5 text-white-emphasis">
		<!-- Title -->
		<h1 class="border-bottom border-2 border-secondary">여행지 정보공유</h1>
		<div
			class="border border-2 border-dark-subtle rounded bg-white shadow p-4">
			<!-- <div class="col-md-9 ms-auto mb-3 w-50">
					<form class="d-flex" id="form-search" action="">
						<input type="hidden" name="action" value="list"> <input
							type="hidden" name="pgno" value="1"> <select name="key"
							id="key" class="form-select form-select-sm ms-5 me-1 w-25"
							aria-label="검색조건">
							<option value="" selected="">검색조건</option>
							<option value="article_no">글번호</option>
							<option value="subject">제목</option>
							<option value="user_id">작성자</option>
						</select>
						<div class="input-group input-group-sm w-50">
							<input type="text" name="word" id="word" class="form-control"
								placeholder="검색어...">
							<button id="btn-search" class="btn btn-dark" type="button">검색</button>
						</div>
					</form>
				</div> -->
			<table
				class="table table-bordered table-striped table-hover text-center">
				<thead>
					<tr>
						<th class="text-center" style="width: 7.5%">#</th>
						<th class="text-start" style="width: 65%">제목</th>
						<th class="text-center" style="width: 7.5%">작성자</th>
						<th class="text-center" style="width: 7.5%">조회수</th>
						<th class="text-center" style="width: 12.5%">작성일</th>
					</tr>
				</thead>
				<tbody>
						<tr class="text-center" v-for = "(board,index) in boards" :key="board.articleNo">
							<th scope="row">{{ board.userName }}</th> <!--id-->
							<td class="text-start"><a
								href=""
								class="article-title link-dark" data-no=""
								style="text-decoration: none">  </a>{{ board.title }}</td>
							<td>{{ board.userId }}</td> <!--id-->
							<td>{{ board.userEmail }}</td> <!--hit-->
							<td>{{ board.createdDate }}</td> <!--createddate-->
						</tr>
					</tbody>
			</table>

			<div class="d-flex justify-content-center">
				<div class="col-md-3 text-start"></div>

				<nav class="mx-auto" aria-label="Page navigation example">
					<!-- <div class="row">${navigation.navigator}</div> -->
				</nav>
				<div class="col-md-3 text-end">
					<button id="btn-board-register" class="btn btn-dark">등록하기</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- <script>
	document.querySelector("#btn-board-register").addEventListener("click",
			function() {
				location.href = "${root}/board?action=mvwrite";
			});
</script> -->

</template>

<style scoped>

</style>