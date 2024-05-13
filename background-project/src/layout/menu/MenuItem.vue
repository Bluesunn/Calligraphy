<template>
    <template v-for="menu in menuList" :key="menu.path">
        <!-- 有下级菜单 -->
        <el-sub-menu v-if="menu.children && menu.children.length > 0" :index="menu.path">
        <template #title>
            <!-- 动态组件的方式生成图标 -->
            <el-icon>
                <component :is="menu.meta.icon"></component>
            </el-icon>
            
            <span>{{ menu.meta.title }}</span>
       </template>

       <!-- 生成下级 -->
       <MenuItem :menuList="menu.children"></MenuItem>
       
      </el-sub-menu>

      <!-- 没有下级 -->
      <el-menu-item style="color: #f4f4f5" v-else :index="menu.path">
        <el-icon>
            <component :is="menu.meta.icon"></component>
        </el-icon>
      <template #title>{{menu.meta.title}}</template>
    </el-menu-item>

    </template>

</template>

<script setup lang="ts">
    //接受父组件传递的数据
    //这里的menuList是形参
    defineProps(['menuList'])

</script>

<style scoped>

</style>