<template>
  <!-- Main Content -->
  <div class="container-fluid">
    <div class="container-xxl pt-5 pb-3 vh-100 d-flex flex-column">
      <!-- Title -->
      <div class="d-flex justify-content-center mb-3">
        <h3 class="fw-bold text-center border-bottom border-2 border-secondary">
          관광지 정보
        </h3>
      </div>
      <!-- Grid Row -->
      <div class="row justify-content-center mb-3">
        <div class="col-md-4 mb-2 mb-md-0">
          <VSelect
            :selectOption="sidoList"
            @onKeySelect="onChangeSido"
            class="form-select"
            aria-label="Default select example" />
        </div>
        <div class="col-md-4 mb-2 mb-md-0">
          <VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
        </div>
        <div class="col-md-4 mb-0 mb-md-0">
          <select
            @click="getAttractions"
            v-model="searchAtt.contentTypeId"
            id="contentType"
            class="form-select"
            aria-label="Default select example">
            <option selected>관광정보선택</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
          </select>
        </div>
      </div>
      <div class="row justify-content-center mb-3">
        <div class="col-md-12 mb-0 mb-md-0">
          <input
            type="text"
            class="form-control"
            id="title"
            placeholder="관광지 검색 키워드를 입력하세요." 
            v-model="searchAtt.title"/>
            <p>{{ searchAtt.title }}</p>
        </div>
      </div>
      <div class="row justify-content-center mb-3">
        <div class="col-md-12 mb-0 mb-md-0">
          <button
            class="w-100 btn btn-light border form-control"
            id="btn-search-attraction"
            @click="getAttractions">
            검색
          </button>
        </div>
      </div>

      <div class="row h-100">
        <VKakaoMap
          :attractions="attractionList"
          :selectAttraction="selectAttraction" />
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from "vue";
import { listAttraction } from "@/api/attraction";
import { listSido, listGugun } from "@/api/map";

import VKakaoMap from "@/components/common/VKakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";

const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;

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
const param = ref({
  serviceKey: VITE_OPEN_API_SERVICE_KEY,
  pageNo: 1,
  numOfRows: 20,
  zscode: 0,
});

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

const getAttractions = () => {
  attractionList.value = [];
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
