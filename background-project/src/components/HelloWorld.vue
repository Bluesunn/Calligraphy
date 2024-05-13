<template>
  <el-button type="primary" size="default" @click="addBtn()">fgfg</el-button>
  
  <div>
    {{ count }}
  </div>
</template>

<script setup lang="ts">
import { useTestStore } from '@/store/test';
import { computed } from 'vue';
const store = useTestStore();
const count =computed(() => {
  return store.getCount;
});

const addBtn = () =>{
  // store.setCount(++store.count);


  // store.$patch({
  //   count:++store.count
  // })
//通过action来操作最稳妥


  // store.$patch((state)=>{
  //   state.count= ++store.count
  // })
}
</script>

<style scoped>

</style>