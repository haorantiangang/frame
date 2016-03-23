/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.http;


import com.android.lxf.http.com.lxf.http.it.IHttpApi;
import com.android.lxf.http.com.lxf.http.it.IResponseListener;

import java.io.File;

/**
 * author lxf on 15/11/24
 *        下午3:54.
 *        工厂方法，根据需求自加，自取
 */
public class HttpStrategy {


    private static HttpStrategy mHttpUtils;
    private  static IHttpApi mIHttpApi;
    private HttpStrategy (){

    }
    public static HttpStrategy  getInstance(){
        if (mHttpUtils ==  null)
        {
            synchronized (HttpUtils.class)
            {
                mHttpUtils = new HttpStrategy();
            }
        }
        return mHttpUtils;
    }
    //从网络获取数据 (get 和 post)
    public   <T> ResponseDataI<T>  getData(ResquestDataI data,Class<T> t,IResponseListener listener){
          if (mIHttpApi == null)
          {
              mIHttpApi =  new AsyncHttpImpl();
          }

        return  mIHttpApi.getData(data,t,listener);
    }

    //上传文件

    public void postFile(ResquestDataI data,File file,IResponseListener listener){

        mIHttpApi.postFile(data,file,listener);
    };

    //下载文件
    public void downLoadFile(ResquestDataI data,IResponseListener listener){
        mIHttpApi.getFile(data,listener);
    };

    //设置网络的种类，比如 volley ,okhttp等
    public void setHttpType(IHttpApi httpType){
        mIHttpApi = httpType;
    }
}
