package com.itmk.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回值类型
 * @param <T>
 */
@Data //自动生成getter和setter
@AllArgsConstructor //构造函数
public class ResultVo<T> {
    private String msg;
    private int code;
    private T data;
}