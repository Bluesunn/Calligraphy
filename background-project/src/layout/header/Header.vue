<template>
  <div class="header-container">
    <BreadCrumb />
  </div>
</template>

<script setup lang="ts">
import BreadCrumb from "./BreadCrumb.vue";
</script>

<style lang="scss" scoped>
.header-container {
  display: flex;
  align-items: center;
  height: 100%;
}
</style>
