package com.android.lxf.test;

import android.os.Bundle;
import android.view.View;

import com.android.lxf.R;
import com.android.lxf.message.MessageKey;
import com.android.lxf.message.MessageManager;
import com.android.lxf.message.SDMessage;

public class SubMessageActivity extends BaseActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_s);
	}

	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.main:
				SDMessage sdMessage = new SDMessage(MessageKey.MessageMainTH, "你好主线程");
				MessageManager.getInstance().postM(sdMessage);
				break;
			case R.id.sub:
				new Thread(new Runnable() {
					@Override
					public void run() {
						SDMessage sdMessage = new SDMessage(MessageKey.MessageSubTH, "你好子线程");
						MessageManager.getInstance().postM(sdMessage);
					}
				}).start();
				break;
		}
	}
}
