package com.itmk.web.sys_user.entity;

import lombok.Data;

@Data
public class SysUserPage {
    //电话
    private String phone;
    //姓名
    private String nickName;
    //第几页
    private Long currentPage;
    //条数
    private Long pageSize;
}
