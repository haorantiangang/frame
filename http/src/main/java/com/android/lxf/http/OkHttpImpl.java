/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.http;


import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.android.lxf.http.com.lxf.http.it.IHttpApi;
import com.android.lxf.http.com.lxf.http.it.IResponseListener;
import com.android.lxf.toolsutil.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author lxf on 15/11/25
 * 下午5:10.
 */
public class OkHttpImpl<T> implements IHttpApi {

	public static final MediaType MEDIA_TYPE_MARKDOWN
			= MediaType.parse("text/x-markdown; charset=utf-8");


	private OkHttpClient mOkHttpclient;
	private T obj;
	public OkHttpImpl() {
		mOkHttpclient = new OkHttpClient();
	}

	@Override
	public ResponseDataI getData(ResquestDataI data, Class clazz, IResponseListener listener) {
		return getDataFromNet(data, clazz, listener);
	}

	@Override
	public void getFile(ResquestDataI data, IResponseListener listener) {



	}

	@Override
	public void postFile(ResquestDataI data, File file, IResponseListener listener) {
		Request request = new Request.Builder()
				.url(data.getUrl())
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
				.build();
		getDataFromNetLast(request,null,null,listener);
	}

	//从网络获取数据
	private ResponseDataI<T> getDataFromNet(ResquestDataI data, final Class<T> t, final IResponseListener listener) {
		final ResponseDataI responseDataI = new ResponseDataI();
		switch (data.getRequestMethod()) {
			case GET:
				Request requestGet = setGetRequest(data);
				getDataFromNetLast(requestGet,t,responseDataI, listener);
				break;
			case POST:
				Request requestPost = setPostRequest(data);
				getDataFromNetLast(requestPost, t,responseDataI,listener);

				break;
		}
		return responseDataI;
	}


	private void getDataFromNetLast(Request request, final Class<T> t,final ResponseDataI responseDataI,final IResponseListener listener) {

		LogUtils.d("url:" + request.url());
		//new call
		Call call = mOkHttpclient.newCall(request);
		//请求加入调度
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, final IOException e) {
				new Handler(Looper.getMainLooper()).post(new Runnable() {
					@Override
					public void run() {
						try {
							listener.failure(e);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}

			@Override
			public void onResponse(Call call, final Response response) throws IOException {

				new Handler(Looper.getMainLooper()).post(new Runnable() {
					@Override
					public void run() {
						try {
							obj = JSON.parseObject(response.body().string(), t);
							responseDataI.data = obj;
							listener.success(obj);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}

	private Request setGetRequest(ResquestDataI data) {
		Request.Builder builer = new Request.Builder();
		String url = data.getUrl();
		HashMap paramsMap = data.getRequestParamsMap();
		//添加url
		if (paramsMap != null) {
			Iterator iter = paramsMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				url += url.contains("?") ? "&" : "?";
				url += entry.getKey() + "=" + entry.getValue() ;
			}
			url = url.substring(0, url.length() - 1);
		}
		LogUtils.d("geturl:" + url);
		builer.url(url); //添加url
		//添加头部信息
		HashMap HeaderparamsMap = data.getHeaderParams();
		if (HeaderparamsMap != null) {
			Iterator iter = HeaderparamsMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				builer.addHeader(entry.getKey().toString(), entry.getValue().toString());
			}
		}


		return builer.build();
	}

	//设置post请求参数
	private Request setPostRequest(ResquestDataI data) {
		Request.Builder builder = new Request.Builder();
		String url = data.getUrl();
        String paramLog = "";
		FormBody.Builder bodyBuilder = new FormBody.Builder();
		HashMap paramsMap = data.getRequestParamsMap();
		//添加params
		if (paramsMap != null) {
			Iterator iter = paramsMap.entrySet().iterator();

			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				bodyBuilder.add(entry.getKey().toString(), entry.getValue().toString());
				paramLog = paramLog+entry.getKey().toString()+":"+entry.getValue().toString()+"&";
			}
		}

		FormBody body = bodyBuilder.build();
//添加头部
		HashMap HeaderparamsMap = data.getHeaderParams();
		if (HeaderparamsMap != null) {
			Iterator iter = HeaderparamsMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				builder.addHeader(entry.getKey().toString(), entry.getValue().toString());
			}
		}
		LogUtils.d("posturl:" + url);
		LogUtils.d("posturlbody:" + paramLog);
		builder.url(url).post(body);
		return builder.build();
	}
}


