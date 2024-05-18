<template>
  <div>
    <el-button type="primary" size="defult" @click="addBtn">新增</el-button>
  </div>
  <SysDialog
    :visible="dialog.visible"
    :title="dialog.title"
    :height="dialog.height"
    :width="dialog.width"
    @on-close="onClose"
    @on-confirm="onConfirm"
  >
    <template v-slot:content> <div>张三</div> </template>
  </SysDialog>
</template>

<script setup lang="ts">
import SysDialog from "@/components/SysDialog.vue";
import useDialog from "@/hooks/useDialog";
import { reactive } from "vue";

const { dialog, onClose, onConfirm, onShow } = useDialog();

//调用封装好的弹框组件
const addBtn = () => {
  onShow();
};
</script>

<style scoped></style>
