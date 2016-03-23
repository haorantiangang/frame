package com.android.lxf.http.com.lxf.http.it;

/**
 * author lxf on 15/11/24
 * 下午4:23.
 * 用于监听网返回回调
 */
public interface IResponseListener<T> {

    void success(T data);//成功

    void failure(T data);//失败

}
