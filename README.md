# 管理界面

左侧共分为

- 主页：显示统计数据
- 系统管理
  - 用户管理
  - 角色管理
- 班级管理：管理上课费用，时间，地点，上课老师
- 公告管理：可以设置临时停课

# 路由部分

在router文件夹下面的index.ts设置好路由对象及其子路由对象，启动项目时Vue Route根据文件里路由对象设定的URL来进行渲染，首先渲染layout文件夹里的Index.vue组件，把layout界面的骨架部分渲染在App.vue组件里。然后重定向到dashboard文件夹，继续渲染里面的组件在Index.vue里（嵌套路由，因为只设置了两个路由出口，一个在App.vue，一个在Index.vue)。

渲染完整个界面后，在element-plus提供的menu组件里开启route属性，它就会以当前点击的菜单的index属性作为path去路由文件中进行寻找并跳转
