package com.itmk.web.sys_user.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.SysUserRole.entity.SysUserRole;
import com.itmk.web.SysUserRole.service.SysUserRoleService;
import com.itmk.web.sys_user.entity.SysUser;
import com.itmk.web.sys_user.mapper.SysUserMapper;
import com.itmk.web.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Override
    @Transactional//确保方法或类中的数据库操作在一个事务中执行
    //增加
    public void saveUser(SysUser sysUser) {
        //将用户的信息存入数据库
        int i=this.baseMapper.insert(sysUser);//i大于1时，表明数据存储成功
        if(i>0){
            //将前端逗号分隔的字符串转为数组，将多个子字符串集成一个数组存储
            String []split = sysUser.getRoleId().split(",");//roleid定义为string类型
            if(split.length>0){
                List<SysUserRole> roles = new ArrayList<>();
                for(int j=0;j<split.length;j++){//用户可能存在多个角色，因此需要使用循环输入
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(sysUser.getUserId());
                    sysUserRole.setRoleId(Long.parseLong(split[j]));//转化为长整型值
                    roles.add(sysUserRole);
                }
                //保存用户到角色表
                sysUserRoleService.saveBatch(roles);
            }
        }
    }
    //编辑
    @Override
    @Transactional
    public void editUser(SysUser sysUser) {
        //编辑用户信息进入数据库
        int i=this.baseMapper.updateById(sysUser);//i大于1时，表明数据存储成功
        if(i>0){
            //将前端逗号分隔的字符串转为数组，将多个子字符串集成一个数组存储
            String []split = sysUser.getRoleId().split(",");//roleid定义为string类型
            //删除用户原来的角色
            QueryWrapper<SysUserRole> query = new QueryWrapper<>();//创建一个用于构建查询条件的包装器对象，针对 SysUserRole 实体类
            query.lambda().eq(SysUserRole::getUserId,sysUser.getUserId());
            sysUserRoleService.remove(query);
            //重新插入
            if(split.length>0){
                List<SysUserRole> roles = new ArrayList<>();
                for(int j=0;j<split.length;j++){//用户可能存在多个角色，因此需要使用循环输入
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(sysUser.getUserId());
                    sysUserRole.setRoleId(Long.parseLong(split[j]));//转化为长整型值
                    roles.add(sysUserRole);
                }
                //保存用户到角色表
                sysUserRoleService.saveBatch(roles);
            }
        }
    }
    //删除
    @Override
    @Transactional
    public void deleteUser(Long userId) {
        int i=this.baseMapper.deleteById(userId);
        if(i>0){
            QueryWrapper<SysUserRole> query = new QueryWrapper<>();
            query.lambda().eq(SysUserRole::getUserId,userId);//过滤和精确定位
            sysUserRoleService.remove(query);//删除满足指定用户 ID 条件的相关角色记录
            //通常情况下，直接删除一个单独的 userid 值不太符合常规的数据库操作逻辑
        }
    }
}
