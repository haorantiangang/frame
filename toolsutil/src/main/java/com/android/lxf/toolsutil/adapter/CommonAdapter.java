/**
 * Copyright (c) 2013-2014 YunZhongXiaoNiao Tech
 */
package com.android.lxf.toolsutil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * author lxf on 15/12/5
 *        上午10:50.
 */
public  abstract class CommonAdapter<T>  extends BaseAdapter {

    protected Context mContext;//上线文
    protected List<T> mDatas;//数据集合
    protected LayoutInflater mInflater;
    protected int mItemLayoutId;
    protected void setGone(View... view) {//设置隐藏
        if (view != null && view.length > 0) {
            for (View v : view) {
                if (v == null) continue;
                v.setVisibility(View.GONE);
            }
        }
    }

    protected void setInvisible(View... view) {//设置不可见
        if (view != null && view.length > 0) {
            for (View v : view) {
                if (v == null) continue;
                v.setVisibility(View.INVISIBLE);
            }
        }
    }

    protected void setVisible(View... view) {//设置可见
        if (view != null && view.length > 0) {
            for (View v : view) {
                if (v == null) continue;
                v.setVisibility(View.VISIBLE);
            }
        }
    }
    //初始化
    public CommonAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
    }
    //刷新数据
    public void refreshData(List<T> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDatas != null && mDatas.size() > 0) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (mDatas != null && mDatas.size() > 0) {
            return mDatas.get(position);
        }
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //从ViewHolder中获取控件view，若为空则创建
        final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        try {
            convert(viewHolder, getItem(position),position);
        } catch (NullPointerException e) {

            e.printStackTrace();
        }
        return viewHolder.getConvertView();
    }
    public abstract void convert(ViewHolder holder, T item,int position);
    public ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }
}
