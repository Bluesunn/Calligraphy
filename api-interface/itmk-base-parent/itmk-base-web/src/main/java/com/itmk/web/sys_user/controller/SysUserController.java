package com.itmk.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.sys_role.entity.SysRole;
import com.itmk.web.sys_user.entity.SelectItme;
import com.itmk.web.sys_user.entity.SysUser;
import com.itmk.web.sys_user.entity.SysUserPage;
import com.itmk.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sysUser")
@RestController
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    //新增
    @PostMapping
    public ResultVo add(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        if(sysUserService.save(sysUser)) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败！");
    }
    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysUser sysUser) {
        sysUser.setUpdateTime(new Date());
        if(sysUserService.updateById(sysUser)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败！");
    }
    //删除
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId) {
        if(sysUserService.removeById(userId)) {
            return  ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败");
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

}
