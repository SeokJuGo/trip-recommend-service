<template>
  <div class="mx-auto mt-3 col-lg-4 col-md-6 my-sm-auto ms-sm-auto me-sm-0">
    <div class="nav-wrapper position-relative end-0">

      <a-menu v-model:selectedKeys="current" mode="horizontal">


        <router-link class="nav-link" :to="{ name: 'user-myplan' }">
          <a-menu-item class="scale" key="mail">
            <template #icon>
              <mail-outlined />
            </template>
            계획
          </a-menu-item>
        </router-link>

        <router-link class="nav-link" :to="{ name: 'user-board' }">
          <a-menu-item class="scale" key="app">
            <template #icon>
              <appstore-outlined />
            </template>
            게시판
          </a-menu-item>
        </router-link>
        <router-link class="nav-link" :to="{ name: 'user-info' }">
          <a-menu-item class="scale" key="board">
            <template #icon>
              <appstore-outlined />
            </template>
            내 정보
          </a-menu-item>
        </router-link>
      </a-menu>


    </div>
  </div>
</template>
<script setup>
import { defineComponent, ref } from 'vue';
import { MailOutlined, AppstoreOutlined, SettingOutlined } from '@ant-design/icons-vue';


const current = ref(['mail']);

</script>

<style>
.scale {
  transform: scale(1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transition: all 0.3s ease-in-out; /* 부드러운 모션을 위해 추가*/
}
.scale:hover {
  transform: scale(1.2);
  -webkit-transform: scale(1.2);
  -moz-transform: scale(1.2);
  -ms-transform: scale(1.2);
  -o-transform: scale(1.2);
}
.scale img {
  width: 200px;
  height: 200px;
  display:inline-block;
  background: crimson;
  
}

</style>