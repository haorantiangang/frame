/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.http.com.lxf.http.it;


import com.android.lxf.http.ResponseDataI;
import com.android.lxf.http.ResquestDataI;

import java.io.File;

/**
 * author lxf on 15/11/24
 *        下午3:43.
 */
public interface IHttpApi<T> {

     ResponseDataI<T> getData(ResquestDataI data, Class<T> clazz, IResponseListener listener);//获取数据
    void getFile(ResquestDataI data, IResponseListener listener);//获取文件
    void postFile(ResquestDataI data, File file, IResponseListener listener);//上传文件

}
