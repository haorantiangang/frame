package com.android.lxf.test;

import android.content.Context;

import com.android.lxf.frame.FrameWorkApp;
import com.android.lxf.toolsutil.LogUtils;

/**
 * Created by lxf on 16/2/17.
 */
public class AppApplacation extends FrameWorkApp {
	@Override
	public void onCreate() {
		super.onCreate();
		LogUtils.d("AppApplacation==");
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		LogUtils.d("attachBaseContext==");
	}
}
