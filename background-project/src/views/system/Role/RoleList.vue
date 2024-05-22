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
    <!-- 表格数据 -->
    <el-table :height="tableHeight" :data="tableList" border stripe>
      <el-table-column prop="roleName" label="角色名称"></el-table-column>
      <el-table-column prop="remark" label="角色备注"></el-table-column>
      <el-table-column label="操作" width="220" align="center">
        <template #default="scope">
          <el-button
            type="primary"
            icon="Edit"
            size="default"
            @click="editBtn(scope.row)"
            >编辑</el-button
          >
          <el-button
            type="danger"
            icon="Delete"
            size="default"
            @click="deleteBtn(scope.row.roleId)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page.sync="searchParm.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]"
      :page-size="searchParm.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="searchParm.total"
      background
    >
    </el-pagination>

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
import { onMounted, reactive, ref } from "vue";
import { ElMessage, FormInstance } from "element-plus";
//引入弹窗组件和弹窗钩子
import useDialog from "@/hooks/useDialog";
import SysDialog from "@/components/SysDialog.vue";
import { addApi, getListApi } from "@/api/role";
import { SysRole } from "@/api/role/RoleModel";
import { nextTick } from "vue";

//表单ref属性（类似于id）
const addRef = ref<FormInstance>();

//弹框属性
const { dialog, onClose, onShow } = useDialog();

//表单绑定的对象
const searchParm = reactive({
  currentPage: 1,
  pageSize: 10,
  roleName: "",
  total: 0,
});

//新增表单对象
const addModel = reactive({
  roleId: "",
  roleName: "",
  remark: "",
});

//新增按钮
const addBtn = () => {
  //改变弹窗信息
  dialog.title = "新增";
  dialog.height = 180;
  //显示弹窗
  onShow();
};

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
  addRef.value?.validate(async (valid) => {
    if (valid) {
      console.log("表单验证通过");
      //提交请求
      let res = await addApi(addModel);
      if (res && res.code == 200) {
        ElMessage.success(res.msg);
        onClose();
      }
    }
  });
};

//表格高度
const tableHeight = ref(0);
//表格数据
const tableList = ref([]);

//查询列表
const getList = async () => {
  let res = await getListApi(searchParm);
  if (res && res.code == 200) {
    //设置表格数据
    console.log(res);
    tableList.value = res.data.records;
    //设置分页总条数
    searchParm.total = res.data.total;
  }
};

//编辑按钮
const editBtn = (row: SysRole) => {
  console.log(row);
};

//删除按钮
const deleteBtn = (roleId: string) => {
  console.log(roleId);
};

//页容量改变时触发
const sizeChange = (size: number) => {
  searchParm.pageSize = size;
  getList();
};
//页数改变时触发
const currentChange = (page: number) => {
  searchParm.currentPage = page;
  getList();
};

//搜索
const searchBtn = () => {
  getList();
};
const resetBtn = () => {
  searchParm.roleName = "";
  searchParm.currentPage = 1;
  getList();
};
onMounted(() => {
  nextTick(() => {
    tableHeight.value = window.innerHeight - 230;
  });
  getList();
});
</script>

<style scoped></style>
