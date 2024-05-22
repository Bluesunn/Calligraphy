import http from "@/http";
import { RoleListParm, SysRole } from "./RoleModel";

//新增角色
export const addApi = (parm: SysRole) => {
  return http.post("/api/role", parm);
};

//角色列表
export const getListApi = (parm: RoleListParm) => {
  return http.get("/api/role/getList", parm);
};
