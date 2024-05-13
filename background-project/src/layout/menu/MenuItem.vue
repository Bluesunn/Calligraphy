<template>
    <template v-for="menu in menuList" :key="menu.path">

    </template>

</template>

<script setup lang="ts">
    //图标引入
    import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
    } from '@element-plus/icons-vue'
    //接受父组件传递的数据
    //这里的menuList是形参
    defineProps(['menuList'])

</script>

<style scoped>

</style>