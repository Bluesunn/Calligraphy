package com.itmk.web.sys_menu.entity;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MakeMenuTree {
    public static List<SysMenu> makeTree(List<SysMenu> menuList, Long pid){
        //存放组装的树数据
        List<SysMenu> list = new ArrayList<>();
        //组装树
        Optional.ofNullable(menuList).orElse(new ArrayList<>())//判断是否为空，为空则返回list，不为空则进行调用
                .stream()//调用为流
                .filter(item -> item != null && item.getParentId().equals(pid))//过滤
                .forEach(item ->{
                    SysMenu menu = new SysMenu();
                    BeanUtils.copyProperties(item,menu);
                    menu.setLabel(item.getTitle());
                    menu.setValue(item.getMenuId());
                    //查找下级：递归调用；自己调用自己
                    List<SysMenu> children = makeTree(menuList, item.getMenuId());
                    menu.setChildren(children);
                    list.add(menu);
                });
        return list;
    }
}
