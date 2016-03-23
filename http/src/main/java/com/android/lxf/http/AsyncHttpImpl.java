/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.http;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.lxf.http.com.lxf.http.it.IHttpApi;
import com.android.lxf.http.com.lxf.http.it.IResponseListener;
import com.android.lxf.toolsutil.LogUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * author lxf on 15/11/24
 * 下午4:21.
 * AsyncHttp 的实现
 */
public class AsyncHttpImpl<T> implements IHttpApi<T> {
	private static AsyncHttpClient client;
//	private static AsyncHttpImpl mAsyncHttp;
	private T obj;

	public AsyncHttpImpl() {
		client=new AsyncHttpClient();
	}

	//    public static AsyncHttpImpl  getInstance(){
//        if (mAsyncHttp ==  null)
//        {
//            synchronized (HttpUtils.class)
//            {
//                mAsyncHttp = new AsyncHttpImpl();
//                client=new AsyncHttpClient();
//            }
//        }
//        return mAsyncHttp;
//    }
	@Override
	public ResponseDataI<T> getData(ResquestDataI data, Class<T> t, IResponseListener listener) {
		return getDataFromNet(data, t, listener);
	}


	@Override
	public void getFile(ResquestDataI data, IResponseListener listener) {

	}

	@Override
	public void postFile(ResquestDataI data, File file, IResponseListener listener) {
		//用post方法可以上传文件

	}

	//从网络获取数据
	private ResponseDataI<T> getDataFromNet(ResquestDataI data, final Class<T> t, final IResponseListener listener) {
		final ResponseDataI responseDataI = new ResponseDataI();


		switch (data.getRequestMethod()) {
			case GET:
				RequestParams paramsget = getParams(data.getRequestParamsMap());
				if (paramsget !=null)
				LogUtils.d(data.getUrl()+"?"+paramsget.toString());
				else
				{
					LogUtils.d(data.getUrl());
				}
				client.get(data.getUrl(), paramsget, new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
						String data = new String(responseBody);
						Log.d("===", data);
						obj = JSON.parseObject(data, t);
						responseDataI.data = obj;
						listener.success(obj);
					}

					@Override
					public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
						String data = new String(responseBody);
						responseDataI.respMsg = data;
						listener.success(data);
					}
				});
				break;
			case POST:
				RequestParams paramspost = getParams(data.getRequestParamsMap());

				client.post(data.getUrl(), paramspost, new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
						String data = new String(responseBody);
						Log.d("===", data);
						obj = JSON.parseObject(data, t);
						responseDataI.data = obj;
						listener.success(obj);
					}

					@Override
					public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
						String data = new String(responseBody);
						responseDataI.respMsg = data;
						listener.success(data);
					}
				});
				break;
		}
		return responseDataI;
	}

	//{"code":0,"msg":"","data":{}}
	private RequestParams getParams(HashMap hashMap) {
		RequestParams params = null ;
		if (hashMap != null) {
			params= new RequestParams();
			Iterator iter = hashMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				params.put(entry.getKey().toString(), entry.getValue());
			}
			LogUtils.d("AsyncHttpClient 上传参数"+params.toString());
		}

		return params;
	}
}
