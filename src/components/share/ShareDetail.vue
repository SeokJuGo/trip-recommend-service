<script setup>

</script>

<template>
<div class="container-fluid bg-danger-subtle">
	<div class="container-md py-5 text-white-emphasis vh-100">
		<!-- Title -->
		<h1 class="border-bottom border-2 border-secondary">여행지 정보공유</h1>
		<div
			class="border border-2 border-dark-subtle rounded bg-white shadow p-4">
			<form id="form-board-mvmodify" action="" class="d-flex flex-column">
				<input type="hidden" name="action" value="mvmodify"> <input
					type="hidden" name="boardId" value="  ">
				<div class="mb-3">
					<label for="subject" class="form-label">제목</label> <input
						type="text" name="subject" id="subject" class="form-control"
						placeholder="제목" value="  " readonly>
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용</label>
					<textarea name="content" id="content" class="form-control"
						style="height: 40vh;" placeholder="내용">  </textarea>
				</div>
				<div class="d-grid gap-2">
					<button id="btn-board-modify" class="btn btn-dark">수정하기</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- <script>
	document.querySelector("#btn-board-modify").addEventListener("click",
			function() {
				let form = document.querySelector("#form-board-mvmodify");
				form.setAttribute("action", "${root}/board");
				form.submit();
			});
</script> -->

</template>

<style scoped>

</style>