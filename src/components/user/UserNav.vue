<template>
  <div class="mx-auto mt-3 col-lg-4 col-md-6 my-sm-auto ms-sm-auto me-sm-0">
    <div class="nav-wrapper position-relative end-0">

      <a-menu v-model:selectedKeys="current" mode="horizontal">


        <router-link class="nav-link" :to="{ name: 'user-myplan' }">
          <a-menu-item key="mail">
            <template #icon>
              <mail-outlined />
            </template>
            계획
          </a-menu-item>
        </router-link>

        <router-link class="nav-link" :to="{ name: 'user-board' }">
          <a-menu-item key="app">
            <template #icon>
              <appstore-outlined />
            </template>
            게시판
          </a-menu-item>
        </router-link>
        <router-link class="nav-link" :to="{ name: 'user-info' }">
          <a-menu-item key="board">
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