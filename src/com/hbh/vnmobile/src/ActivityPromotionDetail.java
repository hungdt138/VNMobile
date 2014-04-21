package com.hbh.vnmobile.src;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hbh.vnmobile.src.constants.Constants;

public class ActivityPromotionDetail extends BaseActivity implements
		OnClickListener {

	TextView title, content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_product_detail);
		//
		title = (TextView) findViewById(R.id.title);
		content = (TextView) findViewById(R.id.content);
		//
		String t = getIntent().getExtras().getString(Constants.TXT_NAME);
		title.setText(t);
		
		String c = getIntent().getExtras().getString(Constants.TXT_DESCRIPTION);
		content.setText(c);

	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		default:
			break;
		}
	}

}

