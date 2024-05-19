<template>
  <div class="logo">
    <img :src="MenuLogo" />
    <span class="logo-title">{{ title }}</span>
  </div>
</template>

<script setup lang="ts">
import MenuLogo from "@/assets/MenuLogo.png";
import { ref } from "vue";

const title = ref("后台管理系统");
</script>

<style lang="scss" scoped>
.logo {
  display: flex;
  width: 100%;
  height: 60px;
  background-color: #2b2f3a;
  text-align: center;
  cursor: pointer;
  align-items: center;
  img {
    width: 50px;
    height: 50px;
    margin-right: 5px;
    margin-left: 5px;
  }
  .logo-title {
    color: #fff;
    font-weight: 800;
    line-height: 60px;
    font-size: 22px;
    font-family: FangSong;
  }
}
</style>
