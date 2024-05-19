<template>
  <div>
    <el-button type="primary" size="defult" @click="addBtn">新增</el-button>
  </div>
  <SysDialog
    :visible="dialog.visible"
    :title="dialog.title"
    :height="dialog.height"
    :width="dialog.width"
    @onClose="onClose"
    @onConfirm="onConfirm"
  >
    <template v-slot:content> <div>张三</div> </template>
  </SysDialog>
</template>

<script setup lang="ts">
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";

const { dialog, onClose, onConfirm, onShow } = useDialog(); //解构赋值

//调用封装好的弹框组件
const addBtn = () => {
  onShow();
};
</script>

<style scoped></style>
