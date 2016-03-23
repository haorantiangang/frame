package com.android.lxf.toolsutil;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by lxf(1173074500@qq.com) on 2015/12/25.
 * introduction:UsefulResourceRefHolder+Initializer
 * 用于存储应用的Context及各类资源的引用，供各个Utils类使用。
 * 需要在Application对象的onCreate方法中调用init方法，才能确保整个utils可以使用
 * 以后如果自定义功能多了可能需要配置文件，然后我们一起搞一个android Struts啊啊啊啊啊啊啊啊啊
 */
public class UtilsInitializer {

	/**
	 * Context对象
	 */
	static Context mContext;
	static int mMainThreadId;
	static Handler mMainThreadHandler;
	static Resources mResources;
	static Looper mMainLooper;


	/**
	 * 用于加载Util的全局静态资源，在onCreate中调用
	 * 保证在主线程中调用
	 * <p/>
	 * @param context
	 * @param isDebuggable
	 * @see UtilsInitializer#init(Context, boolean, Class)
	 */


	/**
	 * 用于加载Util的全局静态资源，在onCreate中调用
	 * 保证在主线程中调用
	 * <p/>
	 * @param context
	 */
	public static void init(Context context) {
		mContext = context;
		mResources = context.getResources();
		mMainThreadId = android.os.Process.myTid();
		mMainThreadHandler = new Handler();
		mMainLooper = context.getMainLooper();
		TipsUtil.initTost(context);
		DataStoreUtil.init(context);
		ImageLoaderUtils.init(context);
	}

	/**
	 * 释放引用
	 */
	public static void release() {
		mContext = null;
		mResources = null;
		mMainThreadHandler = null;
		mMainLooper = null;
	}


}
