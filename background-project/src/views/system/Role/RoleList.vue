<template>
  <el-main>
    <!-- ：model是绑定数据对象用的 -->
    <el-form :model="searchParm" :inline="true" size="default">
      <el-form-item>
        <el-input
          placeholder="请输入关键字"
          v-model="searchParm.roleName"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="Search" @click="searchBtn">搜索</el-button>
        <el-button icon="Close" type="danger" plain @click="resetBtn"
          >重置</el-button
        >
        <el-button icon="Plus" type="primary" @click="addBtn">新增</el-button>
      </el-form-item>
    </el-form>
    <SysDialog
      :title="dialog.title"
      :width="dialog.width"
      :height="dialog.height"
      :visible="dialog.visible"
      @onClose="onClose"
      @onConfirm="commit"
    >
      <template v-slot:content>
        <el-form
          :model="addModel"
          ref="addRef"
          :rules="rules"
          label-width="80px"
          :inline="false"
          size="default"
        >
          <!-- prop用于绑定数据对象中的具体属性 -->
          <el-form-item prop="roleName" label="角色名称">
            <el-input v-model="addModel.roleName"></el-input>
          </el-form-item>

          <el-form-item prop="remark" label="备注">
            <el-input v-model="addModel.remark"></el-input>
          </el-form-item>
        </el-form>
      </template>
    </SysDialog>
  </el-main>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { FormInstance } from "element-plus";
//引入弹窗组件和弹窗钩子
import useDialog from "@/hooks/useDialog";
import SysDialog from "@/components/SysDialog.vue";

//表单ref属性（类似于id）
const addRef = ref<FormInstance>();

//弹框属性
const { dialog, onClose, onShow } = useDialog();

//表单绑定的对象
const searchParm = reactive({
  currentPage: 1,
  pageSize: 10,
  roleName: "",
});

//新增按钮
const addBtn = () => {
  //改变弹窗信息
  dialog.title = "新增";
  dialog.height = 180;
  //显示弹窗
  onShow();
};

//新增表单对象
const addModel = reactive({
  roleId: "",
  roleName: "",
  remark: "",
});

//表单验证规则
const rules = reactive({
  roleName: [
    {
      required: true,
      message: "请输入角色名称",
      trigger: "change",
    },
  ],
});

//表单提交
const commit = () => {
  addRef.value?.validate((valid) => {
    if (valid) {
      console.log("表单验证通过");
    }
  });
};
const searchBtn = () => {};
const resetBtn = () => {};
</script>

<style scoped></style>
