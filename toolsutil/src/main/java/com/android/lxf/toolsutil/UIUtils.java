package com.android.lxf.toolsutil;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by lxf(1173074500@qq.com) on 2015/8/18.
 * introduction: UI 层面的工具类
 */
public class UIUtils {

	/**
	 * 为方便调用
	 *
	 * @return
	 */
	public static Context getContext() {
		return AppUtils.getContext();
	}

	public static void runOnUIThread(Runnable run) {
		if (isRunInMainThread()) {
			run.run();
		} else {
			getHandler().post(run);
		}
	}
	/**
	 * 获取屏幕宽度
	 * @return 屏幕宽度
	 */
	public static int getWith() {
		return getContext().getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * @return 屏幕高度
	 */
	public static int getHeight() {
		return getContext().getResources().getDisplayMetrics().heightPixels;
	}

	protected static Handler getHandler() {
		return AppUtils.getMainThreadHandler();
	}


	/**
	 * dip转换px
	 *
	 * @param dip
	 * @return
	 */
	public static int dip2px(float dip) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	/**
	 * pxz转换dip
	 */
	public static int px2dip(int px) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}


	/**
	 * 延时在主线程执行runnable
	 */
	public static boolean postDelayed(Runnable runnable, long delayMillis) {
		return getHandler().postDelayed(runnable, delayMillis);
	}

	/**
	 * 在主线程执行runnable
	 */
	public static boolean post(Runnable runnable) {
		return getHandler().post(runnable);
	}

	/**
	 * 从主线程looper里面移除runnable
	 */
	public static void removeCallbacks(Runnable runnable) {
		getHandler().removeCallbacks(runnable);
	}

	public static View inflate(int resId) {
		return LayoutInflater.from(getContext()).inflate(resId, null);
	}

	/**
	 * 方便调用
	 */
	public static Resources getResources() {
		return AppUtils.getResources();
	}

	/**
	 * 同时为多个view设置消失
	 * @param view
	 */
	public static void setGone(View... view) {
		setVisState(View.GONE,view);
	}

	/**
	 * 为多个view设置隐藏，内部已经防止空指针
	 * @param view
	 */
	public static void setInvisible(View... view) {
		setVisState(View.INVISIBLE,view);
	}
	protected static void setVisState(int visState,View...views){
		if (views != null && views.length > 0) {
			for (View v : views) {
				if (v == null) continue;
				v.setVisibility(visState);
			}
		}
	}
	/**
	 * 为多个view设置显示，内部已经防止空指针
	 * @param view
	 */
	public static void setVisible(View... view) {
		setVisState(View.VISIBLE,view);
	}

	/**
	 * 获取文字
	 */
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/**
	 * 获取文字数组
	 */
	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	/**
	 * 获取dimen
	 */
	public static int getDimens(int resId) {
		return getResources().getDimensionPixelSize(resId);
	}

	/**
	 * 获取drawable
	 */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/**
	 * 获取颜色
	 */
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/**
	 * 获取颜色选择器
	 */
	public static ColorStateList getColorStateList(int resId) {
		return getResources().getColorStateList(resId);
	}

	public static boolean isRunInMainThread() {
		return android.os.Process.myTid() == AppUtils.getMainThreadId();
	}
	/**
	 * 将listView设置为与items同高
	 * 注意调用的时机
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
}
