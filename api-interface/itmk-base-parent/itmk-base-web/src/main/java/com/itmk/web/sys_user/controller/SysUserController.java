package com.itmk.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.SysUserRole.entity.SysUserRole;
import com.itmk.web.SysUserRole.service.SysUserRoleService;
import com.itmk.web.sys_role.entity.SysRole;
import com.itmk.web.sys_user.entity.SysUser;
import com.itmk.web.sys_user.entity.SysUserPage;
import com.itmk.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sysUser")
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    //新增save
    @PostMapping
    public ResultVo add(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        sysUserService.saveUser(sysUser);
        return ResultUtils.success("新增成功!");
    }
    //编辑update
    @PutMapping
    public ResultVo edit(@RequestBody SysUser sysUser) {
        sysUser.setUpdateTime(new Date());
        sysUserService.editUser(sysUser);
        return ResultUtils.success("编辑成功!");
    }
    //删除
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId) {
       sysUserService.deleteUser(userId);
        return  ResultUtils.success("删除成功！");
    }
    //列表
    @GetMapping("/getList")
    public ResultVo getlist(SysUserPage parm) {
        //构造分页对象
        IPage<SysUser> Page=new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getNickName())){//判断是否为空
            query.lambda().like(SysUser::getNickName,parm.getNickName());//封装模糊查询
        }
        if(StringUtils.isNotEmpty(parm.getPhone())){
            query.lambda().like(SysUser::getPhone,parm.getPhone());
        }
        query.lambda().orderByDesc(SysUser::getCreateTime);//最新录入排在最前面
        //查询列表
        IPage<SysUser> list = sysUserService.page(Page, query);
        return ResultUtils.success("查询成功！",list);
    }
    //根据id查询用户角色
    @GetMapping("/getRoleList")
    public ResultVo getRoleList(Long userId) {
        QueryWrapper<SysUserRole> query = new QueryWrapper<>();
        query.lambda().eq(SysUserRole::getUserId,userId);
        List<SysUserRole> list = sysUserRoleService.list(query);
        List<Long> roleList =new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())//如果list为空，新创建一个ArrayList代替
                .forEach(item ->{
                /*item -> {...} 这里使用的是 Java 8 引入的 Lambda 表达式的形式。
                item 是在遍历过程中的当前元素，-> 后面的部分是针对这个元素要执行的具体操作
                （在这里是获取 roleId 并添加到另一个列表中）这种写法简洁地定义了一个匿名函数来处理每个元素。
                */
                    roleList.add(item.getRoleId());
                });
        return ResultUtils.success("查询成功!",roleList);
    }
    //重置密码
    @PostMapping("/resetPassword")
    public ResultVo resetPassword(@RequestBody SysUser sysUser){
        UpdateWrapper<SysUser> query = new UpdateWrapper<>();
        query.lambda().eq(SysUser::getUserId,sysUser.getUserId())
                .set(SysUser::getPassword,"666666");
        if(sysUserService.update(query)){
            return ResultUtils.success("密码重置成功!");
        }
        return ResultUtils.error("密码重置失败!");
    }
}
