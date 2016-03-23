package com.android.lxf.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.lxf.R;
import com.android.lxf.http.com.lxf.http.it.IResponseListener;
import com.android.lxf.test.bean.HttpGetBean;
import com.android.lxf.test.http.HttpRoot;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HttpActivity extends BaseActivity {

	@InjectView(R.id.get)
	Button get;
	@InjectView(R.id.post)
	Button post;
	@InjectView(R.id.up)
	Button up;
	@InjectView(R.id.down)
	Button down;
	@InjectView(R.id.show)
	TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http);
		ButterKnife.inject(this);
		get.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				HttpRoot.getMan(1, "刘晓峰", new IResponseListener() {
					@Override
					public void success(Object data) {
						HttpGetBean beans = (HttpGetBean)data;
						String s =null;
						for (int i = 0;i<beans.data.size();i++)
						{
							s= s+beans.data.get(i).getHome();
							s=s+"---";
							s= s+beans.data.get(i).getAge();
							s=s+"====";
						}
						show.setText(s.toString());
					}

					@Override
					public void failure(Object data) {
						show.setText(data.toString());

					}
				});
			}
		});
		post.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				HttpRoot.postMan("张无忌", "hadkfkkf", 23, new IResponseListener() {
					@Override
					public void success(Object data) {
						show.setText(data.toString());
					}

					@Override
					public void failure(Object data) {
						show.setText(data.toString());
					}
				});
			}
		});
	}

}
