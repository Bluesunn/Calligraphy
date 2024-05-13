<template>
    <el-container class="mycontainer">
      <el-aside width="200px" class="leftmenu">左侧菜单</el-aside>
      <el-container>
        <el-header class="header">Header</el-header>
        <el-main class="main">Main</el-main>
      </el-container>
    </el-container>
</template>

<script setup lang="ts">

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