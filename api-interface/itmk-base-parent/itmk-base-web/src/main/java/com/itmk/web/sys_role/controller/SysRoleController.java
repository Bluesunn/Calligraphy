package com.itmk.web.sys_role.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.sys_role.entity.RoleParm;
import com.itmk.web.sys_role.entity.SysRole;
import com.itmk.web.sys_role.service.SysRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/api/role")
@RestController
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    //新增
    @PostMapping
    public ResultVo add(@RequestBody SysRole sysRole ){
        sysRole.setCreateTime(new Date());
        if(sysRoleService.save(sysRole)){
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败！");
    }
    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysRole sysRole ){
        sysRole.setUpdateTime(new Date());
        if(sysRoleService.updateById(sysRole)){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败！");
    }
    //删除
    @DeleteMapping("/{roleId}")
    public ResultVo delete(@PathVariable("roleId") Long roleId ){
        if(sysRoleService.removeById(roleId)){
            return ResultUtils.success("删除成功！");
        }
        return ResultUtils.error("删除失败！");
    }
    //列表
    @GetMapping("/getList")
    public ResultVo getList(RoleParm parm){
        //构造分页对象
        IPage<SysRole> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getRoleName())){//判断是否为空
            query.lambda().like(SysRole::getRoleName,parm.getRoleName());//封装模糊查询
        }
        query.lambda().orderByDesc(SysRole::getUpdateTime);//用于将新增的数据显示在列表的最上层
        IPage<SysRole> list = sysRoleService.page(page, query);//调用方法实现
        return ResultUtils.success("查询成功",list);
    }
}
