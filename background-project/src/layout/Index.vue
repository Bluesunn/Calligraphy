<template>
    <el-container class="mycontainer">
      <el-aside width="auto" class="leftmenu">
        <MenuBar></MenuBar>
      </el-aside>
      <el-container>
        <el-header class="header">
          <Header></Header>
        </el-header>
        <el-main class="main">
          <router-view/>

        </el-main>
      </el-container>
    </el-container>
</template>

<script setup lang="ts">
  import Header from '@/layout/header/Header.vue';
  import MenuBar from '@/layout/menu/MenuBar.vue';
</script>

<style lang="scss">

  .mycontainer{
      height: 100%;
      .leftmenu{
        background-color: #304156;
    }
      .header{
        background-color: #009688;
    }
      .main{
        background-color: darkgoldenrod;

    }
  }


</style>