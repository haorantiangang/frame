package com.android.lxf.test.http;

import com.android.lxf.http.HttpModle;
import com.android.lxf.http.HttpUtils;
import com.android.lxf.http.ResquestDataI;
import com.android.lxf.http.com.lxf.http.it.IResponseListener;
import com.android.lxf.test.bean.HttpGetBean;

import java.util.HashMap;

/**
 * 作者：lxf on 16/3/3 14:09
 * 邮箱：1173074500@qq.com
 * 说明：
 */
public class HttpRoot {

	//测试get方法
	public static void getMan(int type,String name,IResponseListener listener){
		HashMap hashMap = new HashMap();
		hashMap.put(Constant.type,type);
		hashMap.put(Constant.name,name);
		ResquestDataI resquestDataI = ResquestDataI.HttpBuilder.newBuilder()
				.url(Constant.man)
				//.url("http://www.baidu.com")
				.addParams(hashMap)
				.build();
		resquestDataI.setRequestMethod(HttpModle.GET);
//		HttpUtils.getInstance().setHttpP(new AsyncHttpImpl());
		HttpUtils.getInstance().getData(resquestDataI, HttpGetBean.class, listener);

	}
	// 测试post方法
	public static void postMan(String name,String home,int age,IResponseListener listener){
		HashMap hashMap = new HashMap();
		hashMap.put(Constant.name,name);
		hashMap.put(Constant.age,age);
		hashMap.put(Constant.home,home);
		ResquestDataI resquestDataI = ResquestDataI.HttpBuilder.newBuilder()
				.url(Constant.man)
				.addParams(hashMap)
				.build();
		resquestDataI.setRequestMethod(HttpModle.POST);
//		HttpUtils.getInstance().setHttpP(new AsyncHttpImpl());
		HttpUtils.getInstance().getData(resquestDataI, HttpGetBean.class, listener);

	}

}
