package com.android.lxf.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.lxf.R;
import com.android.lxf.message.MessageKey;
import com.android.lxf.message.MessageManager;
import com.android.lxf.message.SDMessage;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MessageActivity extends BaseActivity {

	@InjectView(R.id.go)
	Button go;
	@InjectView(R.id.show)
	TextView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		ButterKnife.inject(this);
		MessageManager.getInstance().register(this, MessageKey.MessageMainTH);
		MessageManager.getInstance().register(this, MessageKey.MessageSubTH);
		go.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.go:
			 startActivity(new Intent(MessageActivity.this,SubMessageActivity.class));
			break;
			case R.id.show:

			break;
		}
		super.onClick(v);
	}

	@Override
	public void onSDEvent(SDMessage mcmessage) {
		if (mcmessage.notificationID == MessageKey.MessageMainTH )
		{
			show.setText(mcmessage.extObj.toString());
		}
		if (mcmessage.notificationID == MessageKey.MessageSubTH )
		{
			show.setText(mcmessage.extObj.toString());
		}
		super.onSDEvent(mcmessage);
	}
}
