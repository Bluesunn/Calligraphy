<template>
    <el-container class="mycontainer">
      <el-aside width="200px" class="leftmenu">
        <MenuBar></MenuBar>
      </el-aside>
      <el-container>
        <el-header class="header">
          <Header></Header>
        </el-header>
        <el-main class="main">Main</el-main>
      </el-container>
    </el-container>
</template>

<script setup lang="ts">
  import Header from '@/layout/header/Header.vue';
  import MenuBar from '@/layout/menu/MenuBar.vue';
</script>

<style scoped>

  .mycontainer{
      height: 100%;
      .leftmenu{
        background-color: rebeccapurple;
    }
      .header{
        background-color: sandybrown;
    }
      .main{
        background-color: blue;

    }
  }


</style>