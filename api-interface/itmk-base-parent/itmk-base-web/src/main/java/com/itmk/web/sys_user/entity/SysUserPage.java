package com.itmk.web.sys_user.entity;

import lombok.Data;

@Data
public class SysUserPage {
    private String phone;
    private String nickName;
    private Long currentPage;
    private Long pageSize;
}
