package com.android.lxf.test.bean;

import java.io.Serializable;

/**
 * 作者：lxf on 16/3/2 15:37
 * 邮箱：1173074500@qq.com
 * 说明：
 */
public class BaseBean<T> implements Serializable {
	public   T data;
	public   int code;
	public  String msg;
}
