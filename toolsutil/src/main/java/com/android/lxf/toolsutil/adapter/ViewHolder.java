/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.toolsutil.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * author lxf on 15/12/5
 *        上午11:35.
 */
public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        initHolder(context);
    }
    public ViewHolder(Context context, View convertView, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = convertView;
        initHolder(context);
    }

    private void initHolder(Context context) {
        mConvertView.setTag(this);   // setTag
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            //创建ViewHolder对象 ,并做View缓存
            return new ViewHolder(context, parent, layoutId, position);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.setPostion(position);
        return holder;
    }

    public View getConvertView() {
        return mConvertView;
    }
    public void setPostion(int postion) {
        this.mPosition = postion;
    }
    public int getPosition() {
        return mPosition;
    }
    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView获得字符串
     *
     * @param viewId
     * @return
     */
    public String getText(int viewId) {
        TextView view = getView(viewId);

        return view.getText().toString();
    }

    /**
     * 为ImageView设置图片
     * setImageResource
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     * setImageBitmap
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

}
