package com.android.lxf.frame;


import android.app.Application;

import com.android.lxf.toolsutil.UtilsInitializer;

/**
 * 作者：lxf on 16/3/2 15:01
 * 邮箱：1173074500@qq.com
 * 说明：
 */
public class LxfApp extends Application {
	@Override
	public void onCreate() {
		UtilsInitializer.init(this);
		super.onCreate();
	}
}
