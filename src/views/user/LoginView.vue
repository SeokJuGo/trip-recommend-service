<script setup>

</script>

<template>
<div class="container-fluid pt-5" style="height: 75vh">
	<section class="container" style="height: 300px">
		<div class="container p-5"></div>
		<div
			class="container w-25 mt-5 p-3 border border-3 border-success rounded-4">
			<form id="form-login" method="POST" action="">
				<input type="hidden" name="action" value="login" />
				<div class="form-floating m-3">
					<input type="text" class="form-control" id="id"
						placeholder="Enter id" name="id" /> <label for="id">ID</label>
				</div>
				<div class="form-floating m-3">
					<input type="password" class="form-control" id="password"
						placeholder="Enter password" name="password" /> <label for="pwd">Password</label>
				</div>
				<div class="form-floating m-3 d-flex justify-content-center">
					<button type="button" class="btn btn-outline-success m-3"
						id="btn-login">로그인</button>
					<button type="button" class="btn btn-outline-success m-3"
						id="btn-go-to-index">이전으로</button>
				</div>
			</form>
		</div>
	</section>
</div>

<!-- <script>
	document.querySelector("#btn-login").addEventListener("click", function() {
		if (!document.querySelector("#id").value) {
			alert("아이디를 입력해주세요.");
			return;
		} else if (!document.querySelector("#password").value) {
			alert("비밀번호를 입력해주세요.");
			return;
		} else {
			let form = document.querySelector("#form-login");
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