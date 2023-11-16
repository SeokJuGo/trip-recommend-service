<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import ShareAPI from "@/api/board.js";

const route = useRoute();
const { id } = route.params;

const board = ref({});
const fetchBoard = async () => {
    await ShareAPI.fetchBoard(id)
        .then((response) => {
            console.log("[ShareDetail.vue] fetchBoard() >> ", response);
            board.value = response;
        })
        .catch((error) => {
            console.log("[ShareDetail.vue] fetchBoard() Error >> ", error);
        });
};

const router = useRouter();
const moveToShareList = () => router.push(`/share/list`);
const moveToShareUpdate = () => router.push(`/share/update/${id}`);

const deleteShare = async () => {
    await ShareAPI.deleteBoard(id)
        .then(() => moveToShareList())
        .catch((error) => console.log("[ShareDetail.vue] deleteShare() Error >> ", error));
};

onMounted(() => {
    fetchBoard();
});

// const board = ref([]);

// const route = useRoute();
// const { id } = route.params;

// //글 delete function
// const deleleShare = () => {

//   axios.delete(`http://localhost:8080/enjoytrip/board/delete`, {
//     data: {
//       id: id,
//     }
//   })
//   .then(response => console.log(response))
//   .catch(error => console.log(error));
// }

// //update 라우터
// const router = useRouter()
// const goUpdate = function() {
// 	router.push({name:'share-update'})
// }

// //list 라우터
// const goList = function() {
// 	router.push({name:'share-list'})
// }
</script>

<template>
    <div class="container-fluid bg-primary-subtle min-vh-100">
        <!-- Component Head -->
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-md-9">
                <h1 class="border-bottom border-2 border-secondary">여행지 정보공유</h1>
            </div>
        </div>
        <!-- Component Body -->
        <div class="row justify-content-center px-3 pb-3">
            <div class="col-md-9 bg-white shadow p-3">
                <!-- Title -->
                <div class="d-flex justify-content-center">
                    <div class="me-auto">
                        <h1 class="fw-bold fst-italic">{{ board?.title }}</h1>
                    </div>
                    <div class="ms-auto fst-italic text-black-50">
                        <div class="text-end">작성자:{{ board?.author?.username }}</div>
                        <div class="text-end">작성일:{{ board?.createdDate }}</div>
                        <div class="text-end">수정일:{{ board?.updatedDate }}</div>
                    </div>
                </div>
                <!-- Content -->
                <div class="border border-1 border-black-50 p-2">
                    {{ board?.content }}
                </div>
                <!-- Buttons -->
                <div class="d-flex justify-content-center mt-2">
                    <div @click="moveToShareList" class="btn btn-dark me-auto">목록</div>
                    <div @click="moveToShareUpdate" class="btn btn-primary ms-auto">수정</div>
                    <div @click="deleteShare" class="btn btn-danger ms-2">삭제</div>
                </div>
            </div>
        </div>
        <!-- Comment -->
        <div class="row justify-content-center px-3 pb-4">
            <div class="col-md-9 bg-white shadow p-3">
                <h3 class="fw-bold fst-normal">댓글(0)</h3>
                <!-- Comment Content -->
                <div class="border border-1 border-black-50 shadow px-3 py-2">
                    <div class="d-flex justify-content-between text-black-50">
                        <div class="fst-normal">
                            <span>작성자&nbsp;</span>
                            <small>2023-11-16 10:24:25</small>
                        </div>
                        <div class="fst-italic">
                            <small>수정</small>
                            <small>&nbsp;/&nbsp;</small>
                            <small>삭제</small>
                        </div>
                    </div>
                    <div>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit reiciendis
                        nulla, dicta sunt doloribus nobis culpa accusantium provident laboriosam.
                        Dolorum aperiam libero quas aut, qui facere commodi ipsa. Fuga,
                        consequuntur. Debitis corporis consequatur neque reprehenderit delectus
                        culpa ducimus nemo, ad quaerat quisquam omnis eius, suscipit rem repellendus
                        quo ab provident ratione non, commodi dolores ut incidunt aliquid quam
                        aperiam! Unde? Atque deleniti cupiditate consectetur, fugiat neque suscipit
                        in placeat omnis molestias vel animi quas non autem commodi quidem dolor
                        eligendi error. Quam nulla dolorum possimus impedit. Velit corporis eveniet
                        autem. Omnis et aut culpa. Ducimus perspiciatis soluta vel molestias, minus
                        eum quos aut voluptatum architecto aliquid inventore neque eligendi
                        voluptatem assumenda ullam reprehenderit facere est totam temporibus dolorum
                        iusto odio! Omnis vel porro molestiae praesentium laboriosam consequatur
                        nemo unde eum voluptas animi, veritatis impedit, tempore culpa minus. Facere
                        vero, ipsa fugiat, natus beatae, iste eaque explicabo exercitationem sint
                        itaque consequuntur!
                    </div>
                </div>
                <!-- Comment Form -->
                <form class="pt-3" method="post">
                    <textarea class="form-control" rows="5"></textarea>
                </form>
                <!-- Buttons -->
                <div class="d-flex justify-content-end mt-2">
                    <div class="btn btn-dark">등록</div>
                </div>
            </div>
        </div>
    </div>
    <!-- <div class="row justify-content-center">
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
                    <button
                        @click="goList"
                        type="button"
                        id="btn-list"
                        class="btn btn-outline-primary mb-3"
                    >
                        글목록
                    </button>
                    <button
                        @click="goUpdate"
                        type="button"
                        id="btn-mv-modify"
                        class="btn btn-outline-success mb-3 ms-1"
                    >
                        글수정
                    </button>
                    <button
                        type="button"
                        @click="deleleShare"
                        id="btn-delete"
                        class="btn btn-outline-danger mb-3 ms-1"
                    >
                        글삭제
                    </button>
                </div>
            </div>
        </div>
    </div> -->
</template>

<style scoped>
.btn {
    min-width: 100px;
}
</style>
