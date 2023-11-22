<script setup>
import { ref, onMounted } from "vue";
import BoardAPI from "@/api/board.js";

// 게시글 데이터 요청
const params = ref({
    pageNum: undefined,
    pageSize: undefined,
    searchType: undefined,
    searchQuery: undefined,
    boardTypeId: 3,
});

const boards = ref([]);
const pageInfo = ref({});
const fetchBoards = async () => {
    await BoardAPI.fetchBoards(params.value)
        .then((response) => {
            boards.value = response.boards;
            pageInfo.value = response;
        })
        .catch((error) => {
            console.error("[HotplaceList.vue] fetchBoards() Error >> ", error);
        });
};

onMounted(() => {
    fetchBoards();
});
</script>

<template>
    <div class="container-md py-4">
        <!-- Title -->
        <div class="d-flex justify-content-center pb-3">
            <h1 class="border-bottom border-2 border-white text-white">최고의 여행지</h1>
        </div>

        <!-- Search -->
        <div class="d-flex justify-content-center pb-3">
            <div class="input-group mb-3 w-75">
                <select class="form-select border border-secondary" id="inputGroupSelect01">
                    <option disabled>선택</option>
                    <option value="1" selected>제목</option>
                    <option value="2">내용</option>
                    <option value="3">작성자</option>
                </select>
                <input
                    type="text"
                    class="form-control border border-secondary"
                    placeholder="떠나고 싶은 곳을 찾아보세요!"
                />
                <button
                    class="btn btn-light border border-secondary"
                    type="button"
                    id="button-addon2"
                >
                    검색
                </button>
            </div>
        </div>

        <!-- Grid -->
        <div class="d-flex justify-content-center">
            <div class="row">
                <!-- Column 1 -->
                <div class="col-md-6 col-lg-4">
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/강릉.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">강릉</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/양양.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">양양</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/태안.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">태안</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                </div>
                <!-- Column 2 -->
                <div class="col-md-6 col-lg-4">
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/양양.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">양양</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/태안.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">태안</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/강릉.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">강릉</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                </div>
                <!-- Column 2 -->
                <div class="col-md-6 col-lg-4">
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/강릉.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">강릉</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/양양.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">양양</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                    <!-- Card -->
                    <div class="card text-bg-dark shadow mb-3">
                        <img src="@/assets/img/태안.jpg" class="card-img" alt="..." />
                        <div class="card-img-overlay">
                            <h5 class="card-title">태안</h5>
                            <p class="card-text">
                                This is a wider card with supporting text below as a natural lead-in
                                to additional content. This content is a little bit longer.
                            </p>
                            <p class="card-text">
                                <small>Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
button {
    min-width: 100px;
}

select {
    max-width: 110px;
}
</style>
