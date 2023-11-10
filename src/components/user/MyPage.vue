<script setup>

</script>

<template>

<section class="container-fluid" style="height: 100vh">
	<div class="container p-5"></div>
	<div
		class="container w-25 mt-5 p-3 border border-3 border-success rounded-4">
		<div class="d-flex justify-content-center fs-2 p-3">회원정보수정</div>
		<form id="form-join" method="POST" action="">
			<input type="hidden" name="action" value="update" />
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="id"
					placeholder="Enter email" name="id" value=""
					readonly /> <label for="id">ID</label>
			</div>
			<div class="form-floating m-3">
				<input type="password" class="form-control" id="password"
					placeholder="Enter password" name="password"
					value="" /> <label for="pwd">Password</label>
			</div>
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="name"
					placeholder="Enter name" name="name" value="" />
				<label for="name">Name</label>
			</div>
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="email"
					placeholder="Enter email" name="email"
					value="" /> <label for="email">Email</label>
			</div>
			<div class="form-floating m-3">
				<input type="text" class="form-control" id="age"
					placeholder="Enter password" name="age" value="" />
				<label for="age">Age</label>
			</div>
			<div class="form-floating m-3 d-flex justify-content-center">
				<button type="button" id="btn-update"
					class="btn btn-outline-success m-3">회원정보수정</button>
				<button type="button" class="btn btn-outline-success m-3"
					id="btn-go-to-delete">회원탈퇴</button>
			</div>
		</form>
	</div>
</section>
<!-- <script>
	document.querySelector("#btn-update").addEventListener("click", function() {
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

	document.querySelector("#btn-go-to-delete").addEventListener("click",
			function() {
				window.location.href = "${root}/user?action=remove";
			});

	// 바꾼 input에 대해서는 색깔을 바꾼다
	/*
	 */
	var inputElements = document.querySelectorAll(".form-control");
	inputElements.forEach(function(inputElement) {
		inputElement.addEventListener("change", function() {
			// 현재 변경된 input 요소의 배경색만 변경합니다.
			inputElement.style.backgroundColor = "#f2f2f2";
		});
	});
</script> -->

</template>

<style scoped>

</style>