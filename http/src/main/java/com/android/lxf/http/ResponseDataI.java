/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.http;

/**
 * author lxf on 15/11/24
 *        下午3:54.
 *        返回的数据
 */
public class ResponseDataI<T>{
    public int respCode;
    public String respMsg;
    public T data = null;
}
