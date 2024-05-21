package com.itmk.status;

/**
 * 后端返回这些状态码给前端，前端接受到数据后，根据状态码在页面显示相应状态
 */
public class StatusCode {
    //返回成功
    public static final int SUCCESS_CODE = 200;
    //错误状态码
    public static final int ERROR_CODE = 500;
    //无权限
    public static final int NO_LOGIN = 600;
    public static final int NO_AUTH = 700;
}