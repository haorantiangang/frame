/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.http;

import com.android.lxf.http.com.lxf.http.it.IHttpApi;
import com.android.lxf.http.com.lxf.http.it.IResponseListener;


/**
 * author lxf on 15/11/24
 *        下午3:55.
 *        //工具类，给外部调用
 */
public class HttpUtils {
    private static HttpUtils mHttpUtils;

    private HttpUtils (){
        HttpStrategy.getInstance().setHttpType(new OkHttpImpl());
    }

    public static HttpUtils  getInstance(){
        if (mHttpUtils ==  null)
        {
           synchronized (HttpUtils.class)
           {
               mHttpUtils = new HttpUtils();
           }
        }
        return mHttpUtils;
    }

    //从网络获取数据
    public   <T> ResponseDataI<T>  getData(ResquestDataI data,Class<T> t,IResponseListener listener){
      return  HttpStrategy.getInstance().getData(data,t,listener);
    }

    //设置使用的网络库
    public  void setHttpP(IHttpApi iHttpApi){
        HttpStrategy.getInstance().setHttpType(iHttpApi);
    }

}
