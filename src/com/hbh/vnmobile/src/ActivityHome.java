package com.hbh.vnmobile.src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class ActivityHome extends BaseActivity implements OnClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_homepage);
		//
		findViewById(R.id.btn_map).setOnClickListener(this);
		findViewById(R.id.btn_product).setOnClickListener(this);
		findViewById(R.id.btn_promotion).setOnClickListener(this);
		findViewById(R.id.btn_order).setOnClickListener(this);
		findViewById(R.id.btn_service).setOnClickListener(this);
		findViewById(R.id.btn_setting).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_map:
			startActivity(new Intent(ActivityHome.this, ActivityMap.class));
			break;
		case R.id.btn_product:
			startActivity(new Intent(ActivityHome.this, ActivityProducts.class));
			break;
		case R.id.btn_promotion:
			startActivity(new Intent(ActivityHome.this, ActivityPromotions.class));
			break;
		case R.id.btn_service:
			startActivity(new Intent(ActivityHome.this, ActivityServices.class));
			break;
		case R.id.btn_order:
			startActivity(new Intent(ActivityHome.this, ActivityOrders.class));
			break;

		default:
			break;
		}
		
	}
}
