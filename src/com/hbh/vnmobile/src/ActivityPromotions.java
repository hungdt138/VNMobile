package com.hbh.vnmobile.src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hbh.vnmobile.src.adapter.PromotionAdapter;
import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Promotions;

public class ActivityPromotions extends BaseActivity implements OnClickListener {

	PromotionAdapter adapter;
	ListView listview;
	Promotions products;
	int pId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_promotions);
		//
		listview = (ListView) findViewById(R.id.listview);
		loadData();
	}

	protected void loadData() {
		String str = readFile(Constants.TXT_PROMOTION_FILE_NAME);
		products = gson.fromJson(str, Promotions.class);
		if (products != null) {
			adapter = new PromotionAdapter(getBaseContext(), 0, products.promotions);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
			//
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

						Intent intent = new Intent(ActivityPromotions.this,
								ActivityPromotionDetail.class);
						Bundle b = new Bundle();
						b.putString(Constants.TXT_NAME, products.promotions.get(arg2).name);
						b.putString(Constants.TXT_DESCRIPTION, products.promotions.get(arg2).content);
						startActivity(intent.putExtras(b));
				}
			});
		}
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
