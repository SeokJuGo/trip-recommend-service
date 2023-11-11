<script setup>
import { ref,onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";
const board = ref([]);
const route = useRoute();
const { id } = route.params;
const b= async () => {
  await axios
    .get(`http://localhost:8080/enjoytrip/board/detail/${id}`)
    .then(({ data }) => {
      board.value = data;
      console.log("1. board data >> ", board.value);
      //console.log("----------------------------->", response);
    })
    .catch((e) => {
      console.log("1. board catch >> ", e);
    });
};

const deleleShare = () => {
  
  axios.delete(`http://localhost:8080/enjoytrip/board/delete`, {
    data: {
      id: id,
    }
  })
  .then(response => console.log(response))
  .catch(error => console.log(error));
}

onMounted(() => {
  b();
});
</script>

<template>
     <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글보기</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary px-5">{{ board.title }}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">{{ board.userId }}</span> <br />
                  <span class="text-secondary fw-light"> {{ board.createdDate }} </span>
                </p>
              </div>
            </div>
            <div class="col-md-4 align-self-center text-end">댓글 : 17</div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              {{ board.content }}
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                글목록
              </button>
              <c:if test="${userinfo.userId eq article.userId}">
              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
                글수정
              </button>
              <button type="button" @click="deleleShare" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
                글삭제
              </button>
              </c:if>
            </div>
          </div>
        </div>
      </div>

</template>

<style scoped>

</style>