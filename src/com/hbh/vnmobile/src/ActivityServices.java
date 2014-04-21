package com.hbh.vnmobile.src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hbh.vnmobile.src.adapter.ServiceAdapter;
import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Services;

public class ActivityServices extends BaseActivity implements OnClickListener {

	ServiceAdapter adapter;
	ListView listview;
	Services services;
//	int pId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_service);
		//
//		pId = getIntent().getExtras().getInt(Constants.TXT_ID);
		//
		findViewById(R.id.btn_add).setOnClickListener(this);
		listview = (ListView) findViewById(R.id.listview);
		loadData();
	}

	protected void loadData() {
		String str = readFile(Constants.TXT_SERVICE_FILE_NAME);
		services = gson.fromJson(str, Services.class);
		if (services != null) {
			adapter = new ServiceAdapter(getBaseContext(), 0, services.services);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
			//
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

						Intent intent = new Intent(ActivityServices.this,
								ActivityEditService.class);
						Bundle b = new Bundle();
//						b.putInt(Constants.TXT_PID, p);
						b.putInt(Constants.TXT_ID, services.services.get(arg2).id);
						b.putString(Constants.TXT_TYPE_CHANGE, Constants.TXT_TYPE_EDIT);
						startActivity(intent.putExtras(b));
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_add:
			Intent intent = new Intent(ActivityServices.this,
					ActivityEditService.class);
			Bundle b = new Bundle();
//			b.putInt(Constants.TXT_PID, pId);
			b.putInt(Constants.TXT_ID, 0);
			b.putString(Constants.TXT_TYPE_CHANGE, Constants.TXT_TYPE_ADD);

			startActivity(intent.putExtras(b));
			break;

		default:
			break;
		}
	}

}
