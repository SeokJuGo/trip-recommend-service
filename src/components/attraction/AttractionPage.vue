<template>
  <!-- Title -->
  <div class="d-flex justify-content-center mb-3">
    <h3 class="fw-bold text-center border-bottom border-2 border-secondary">
      관광지 정보
    </h3>
  </div>
  <!-- Grid Row -->
  <div class="row justify-content-end mb-3">
    
    <div class="col-md-4 mb-0 mb-md-0 float-right">
      <VSelect
        id="sido"
        :selectOption="sidoList"
        @onKeySelect="onChangeSido"
        class="form-select"
        aria-label="Default select example" />
    </div>
    <div class="col-md-4 mb-0 mb-md-0 float-right">
      <VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
    </div>
  </div>
  <div class="row justify-content-center mb-3">
    <div class="col-md-11 mb-0 mb-md-0">
      <input
        type="text"
        class="form-control"
        id="title"
        placeholder="관광지 검색 키워드를 입력하세요."
        v-model="searchAtt.title" />
    </div>
    <div class="col-md-1 mb-0 mb-md-0">
      <button
        class="w-100 btn btn-light border form-control"
        id="btn-search-attraction"
        @click="searchAttracton(searchAtt.title)">
        검색
      </button>
    </div>
  </div>

  <div class="row h-100">
    <VKakaoMap
      :attractions="attractionList"
      :selectAttraction="selectAttraction"
      @response="categoryChange" />
  </div>
</template>
<script setup>
import { ref, onMounted, provide } from "vue";
import { listAttraction } from "@/api/attraction";
import { listSido, listGugun } from "@/api/map";

import VKakaoMap from "./VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";

const sidoList = ref([]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const attractionList = ref([]);
const selectAttraction = ref({});
const searchAtt = ref({
  sidoCode: 0,
  gugunCode: 0,
  contentTypeId: 0,
  title: "",
});

provide("searchAtt", searchAtt);
onMounted(() => {
  getSidoList();
});

const getSidoList = () => {
  listSido(
    ({ data }) => {
      let options = [];
      options.push({ text: "시도선택", value: "" });
      data.forEach((sido) => {
        options.push({ text: sido.sidoName, value: sido.sidoCode });
      });
      sidoList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};

const onChangeSido = (val) => {
  searchAtt.value.sidoCode = val;
  console.log(searchAtt);
  listGugun(
    { sidoCode: val },
    ({ data }) => {
      let options = [];
      options.push({ text: "구군선택", value: "" });
      data.forEach((gugun) => {
        options.push({ text: gugun.gugunName, value: gugun.gugunCode });
      });
      gugunList.value = options;
    },
    (err) => {
      console.log(err);
    }
  );
};
const onChangeGugun = (val) => {
  searchAtt.value.gugunCode = val;
  getAttractions();
};

const categoryChange = (category) => {
  console.log(category);
  searchAtt.value.contentTypeId = category;
  getAttractions();
};

const searchAttracton = (val) => {
  getAttractions();
};
const getAttractions = () => {
  attractionList.value = [];
  console.log(searchAtt.value)
  listAttraction(
    searchAtt.value,
    ({ data }) => {
      attractionList.value = data;
    },
    (err) => {
      console.log(err);
    }
  );
  console.log(attractionList.value);
};

const viewAttraction = (attraction) => {
  selectAttraction.value = attraction;
};
</script>
<style scoped>

.acard {
  margin: 0 auto;
}
img {
  width: 50px;
}

@media (min-width: 992px) {
  .navbar,
  .navbar-collapse {
    flex-direction: column;
  }
  .navbar-expand-lg .navbar-nav {
    flex-direction: column;
  }
  .navbar {
    width: 28%;
    height: 100vh;
    align-items: flex-start;
  }
  .navbar-brand {
    margin-left: 0.5em;
    padding-bottom: 0;
    border-bottom: 4px solid #464646;
  }
  form input {
    margin-bottom: 0.7em;
  }
}
</style>
