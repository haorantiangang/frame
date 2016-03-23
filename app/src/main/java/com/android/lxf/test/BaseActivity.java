package com.android.lxf.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.android.lxf.message.IEvent;
import com.android.lxf.message.SDMessage;

public class BaseActivity extends ActionBarActivity implements IEvent,View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onSDEvent(SDMessage mcmessage) {

	}

	@Override
	public void onClick(View v) {

	}
}
