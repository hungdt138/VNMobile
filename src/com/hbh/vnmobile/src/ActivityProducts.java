package com.hbh.vnmobile.src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.hbh.vnmobile.src.adapter.ProductAdapter;
import com.hbh.vnmobile.src.adapter.SurveyAdapter;
import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Products;
import com.hbh.vnmobile.src.obj.Surveys;

public class ActivityProducts extends BaseActivity implements OnClickListener {

	ProductAdapter adapter;
	ListView listview;
	Products products;
	int pId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_products);
		//
		listview = (ListView) findViewById(R.id.listview);
		loadData();
	}

	protected void loadData() {
		String str = readFile(Constants.TXT_PRODUCT_FILE_NAME);
		products = gson.fromJson(str, Products.class);
		if (products != null) {
			adapter = new ProductAdapter(getBaseContext(), 0, products.products);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
			//
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

						Intent intent = new Intent(ActivityProducts.this,
								ActivityProductDetail.class);
						Bundle b = new Bundle();
						b.putString(Constants.TXT_NAME, products.products.get(arg2).name);
						b.putString(Constants.TXT_DESCRIPTION, products.products.get(arg2).content);
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
