/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.http;

import java.util.HashMap;

/**
 * author lxf on 15/11/24
 *        下午3:54.
 *        请求的数据
 */
public class ResquestDataI {

    //进行数据请前的封装
    private String url;
    private HashMap<String, Object> requestParamsMap;
    private HashMap<String, String> headerParams;
    public HttpModle getRequestMethod() {
        return requestMethod;
    }

    private HttpModle requestMethod = HttpModle.GET ;
    public ResquestDataI(){}
    public ResquestDataI(String url, HashMap<String, Object> requestParamsMap, HashMap<String, String> headerParams){
        this.url = url;
        this.requestParamsMap = requestParamsMap;
        this.headerParams = headerParams;
    }
    //设置url
    public void setUrl(String url) {
        this.url = url;
    }
    //设置请求参数
    public void setRequestParamsMap(HashMap<String, Object> requestParamsMap) {
        this.requestParamsMap = requestParamsMap;
    }
    //设置请求头
    public void setHeaderParams(HashMap<String, String> headerParams) {
        this.headerParams = headerParams;
    }
    // 获得url
    public String getUrl() {
        return url;
    }

    public void setRequestMethod(HttpModle requestMethod) {
        this.requestMethod = requestMethod;
    }

    public HashMap<String, Object> getRequestParamsMap() {
        return requestParamsMap;
    }

    public HashMap<String, String> getHeaderParams() {
        return headerParams;
    }

    public  static class HttpBuilder{
        ResquestDataI resquestData;
        private HttpBuilder(){

        }
        private HttpBuilder(ResquestDataI req) {
            this.resquestData = req;
        }
        public static  HttpBuilder newBuilder(){
            return new HttpBuilder(new ResquestDataI());
        }
        public HttpBuilder url(String url) {
            resquestData.url = url;
            return this;
        }
        public HttpBuilder addMethod(HttpModle type) {
            resquestData.requestMethod = type;
            return this;
        }
        public HttpBuilder header(String key,String value) {
            if(resquestData.headerParams==null){
                resquestData.headerParams=new HashMap<String, String>();
            }
            resquestData.headerParams.put(key,value);
            return this;
        }
        public HttpBuilder params(String key,Object value) {
            if(resquestData.requestParamsMap==null){
                resquestData.requestParamsMap=new HashMap<String, Object>();
            }
            resquestData.requestParamsMap.put(key,value);
            return this;
        }

        public HttpBuilder addParams(HashMap<String, Object> requestParamsMap) {
            if(resquestData.requestParamsMap==null){
                resquestData.requestParamsMap=new HashMap<String, Object>();
            }
            resquestData.requestParamsMap.putAll(requestParamsMap);
            return this;
        }
        public HttpBuilder addHeaders(HashMap<String, String> requestHeader) {
            if(resquestData.headerParams==null){
                resquestData.headerParams=new HashMap<String, String>();
            }
            resquestData.headerParams.putAll(requestHeader);
            return this;
        }
        public ResquestDataI build(){
            return resquestData;
        }
    }

}
