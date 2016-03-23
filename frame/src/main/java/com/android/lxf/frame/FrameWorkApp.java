package com.android.lxf.frame;

import android.app.Application;

import com.android.lxf.toolsutil.LogUtils;
import com.android.lxf.toolsutil.UtilsInitializer;

/**
 * 作者：lxf on 16/3/22 15:30
 * 邮箱：1173074500@qq.com
 * 说明：
 */
public class FrameWorkApp  extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		UtilsInitializer.init(this);
		initCash();//监控整个应用的崩溃信息
	}

	private void initCash(){
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
			@Override
			public void uncaughtException(Thread thread, Throwable ex) {
				for (int i = 0 ;i<ex.getStackTrace().length;i++)
				{
					LogUtils.d("崩溃信息：文件－－"+ex.getStackTrace()[i].getFileName()+"－-行号－－"+
							ex.getStackTrace()[i].getLineNumber()+"－－函数名－－"+ex.getStackTrace()[i].getMethodName());
				}

			}
		});
	}
}
