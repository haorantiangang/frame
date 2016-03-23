/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.toolsutil;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * author lxf(1173074500@qq.com) on 15/7/21
 * 下午4:43.
 * 现在主要用于toast显示，以后可以加上自定义的dialog
 */
public class TipsUtil {
    private static Toast sToast;
    private static Context sContext;
    public static  void initTost(Context context){  //程序启动初始化
        sContext = context;
    }
    public static void toast(String msg) {
        toast(sContext, msg, Toast.LENGTH_SHORT);
    }
    public static void toastLong(String msg) {
        toast(sContext, msg, Toast.LENGTH_LONG);
    }
    public static void toast(String msg, int time) {
        toast(sContext, msg, time);
    }

    public static void toast(int msgResId) {
        toast(sContext, sContext.getString(msgResId), Toast.LENGTH_LONG);
    }

    public static void toast(Context context, int msgResId, int time) {
        toast(sContext, sContext.getString(msgResId), time);
    }

    public static void toast(final Context context, final String msg, final int time) {
       UIUtils.runOnUIThread (new Runnable() {
            @Override
            public void run() {
                if (sToast != null) {
                    sToast.cancel();
                }
                sToast = Toast.makeText(context, msg, time);
                sToast.setGravity(Gravity.CENTER, 0, 0);
                sToast.show();
            }
        });
    }
}
