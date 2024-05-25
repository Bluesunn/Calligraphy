package com.itmk.web.sys_user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.sys_user.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    //增加
    void saveUser(SysUser user);
    //编辑
    void editUser(SysUser user);
    //删除
    void deleteUser(Long userId);
}
