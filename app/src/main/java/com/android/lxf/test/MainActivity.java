package com.android.lxf.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.lxf.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {


	@InjectView(R.id.message)
	Button message;
	@InjectView(R.id.http)
	Button http;
	@InjectView(R.id.db)
	Button db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.inject(this);
		int m = 10/0;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.message:
        startActivity(new Intent(MainActivity.this,MessageActivity.class));
		break;
		case R.id.http:
			startActivity(new Intent(MainActivity.this,HttpActivity.class));

		break;
		case R.id.db:

		break;
		}
		super.onClick(v);
	}
}
