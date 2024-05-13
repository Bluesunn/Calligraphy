<template>
   <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    :collapse="isCollapse"
    @open="handleOpen"
    @close="handleClose"
  >
    <!-- 前面的menuList是子组件MenuItem里定义的形参，后面是父组件中定义的对象数组 -->
    <MenuItem :menuList="menuList"></MenuItem>
  </el-menu>
</template>

<script setup lang="ts">

    import { ref,reactive} from 'vue'
    import MenuItem from '@/layout/menu/MenuItem.vue';
    
    let menuList =reactive([
        {
                
        }
    ])

    const isCollapse = ref(false)
    const handleOpen = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
    }
    const handleClose = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
    }
</script>

    <style scoped>
    .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
    }
</style>