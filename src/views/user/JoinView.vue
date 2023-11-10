<script setup>

</script>

<template>
<section>
	<div class="container w-25 mt-5 mb-5 p-5 border border-3 border-success rounded-4">
		<div class="d-flex justify-content-center fs-2 p-3">회원가입</div>
		<form id="form-join" method="POST" action="">
			<input type="hidden" name="action" value="join" />
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="id"
					placeholder="Enter email" name="id" /> <label for="id">ID</label>
			</div>
			<div class="form-floating m-3">
				<input type="password" class="form-control" id="password"
					placeholder="Enter password" name="password" /> <label for="pwd">Password</label>
			</div>
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="name"
					placeholder="Enter name" name="name" /> <label for="name">Name</label>
			</div>
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="email"
					placeholder="Enter email" name="email" /> <label for="email">Email</label>
			</div>
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="age"
					placeholder="Enter password" name="age" /> <label for="age">Age</label>
			</div>
			<div class="form-floating m-3 d-flex justify-content-center">
				<button type="button" id="btn-join"
					class="btn btn-outline-success m-3">회원가입</button>
				<button type="button" class="btn btn-outline-success m-3"
					id="btn-go-to-index">이전으로</button>
			</div>
		</form>
	</div>
</section>
<!-- <script>
	document.querySelector("#btn-join").addEventListener("click", function() {
		if (!document.querySelector("#name").value) {
			alert("이름을 입력해주세요.");
			return;
		} else if (!document.querySelector("#id").value) {
			alert("아이디를 입력해주세요.");
			return;
		} else if (!document.querySelector("#password").value) {
			alert("비밀번호를 입력해주세요.");
			return;
		} else if (!document.querySelector("#email").value) {
			alert("이메일을 입력해주세요.");
			return;
		} else if (!document.querySelector("#age").value) {
			alert("나이를 입력해주세요.");
			return;
		} else {
			let form = document.querySelector("#form-join");
			form.setAttribute("action", "${root}/user");
			form.submit();
		}
	});

	document.querySelector("#btn-go-to-index").addEventListener("click",
			function() {
				window.location.href = "${root}/index.jsp";
			});
</script> -->

</template>

<style scoped>

</style>