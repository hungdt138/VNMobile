package com.hbh.vnmobile.src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hbh.vnmobile.src.adapter.KpiAdapter;
import com.hbh.vnmobile.src.adapter.SurveyAdapter;
import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Kpis;

public class ActivityKPI extends BaseActivity implements OnClickListener {

	KpiAdapter adapter;
	ListView listview;
	Kpis kpis;
	int pId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_survey);
		//
		pId = getIntent().getExtras().getInt(Constants.TXT_ID);
		//
		findViewById(R.id.btn_add).setOnClickListener(this);
		listview = (ListView) findViewById(R.id.listview);
		loadData();
	}

	protected void loadData() {
		String str = readFile(Constants.TXT_KPI_FILE_NAME);
		kpis = gson.fromJson(str, Kpis.class);
		if (kpis != null) {
			adapter = new KpiAdapter(getBaseContext(), 0, kpis.kpis);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
			//
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

						Intent intent = new Intent(ActivityKPI.this,
								ActivityEditKPI.class);
						Bundle b = new Bundle();
						b.putInt(Constants.TXT_PID, pId);
						b.putInt(Constants.TXT_ID, kpis.kpis.get(arg2).id);
						b.putString(Constants.TXT_TYPE_CHANGE, Constants.TXT_TYPE_EDIT);
//						b.putInt(Constants.TXT_ID, stores.stores.get(arg2).id);
//						b.putString(Constants.TXT_NAME,
//								stores.stores.get(arg2).name);
//						b.putString(Constants.TXT_TYPE,
//								stores.stores.get(arg2).type);
//						b.putString(Constants.TXT_PROVINCE,
//								stores.stores.get(arg2).province);
//						b.putString(Constants.TXT_DISTRICT,
//								stores.stores.get(arg2).district);
//						b.putString(Constants.TXT_ADDRESS,
//								stores.stores.get(arg2).address);
//						b.putString(Constants.TXT_STK,
//								stores.stores.get(arg2).stk);
//						b.putString(Constants.TXT_MOBILE,
//								stores.stores.get(arg2).mobile);
//						b.putString(Constants.TXT_CONTACT,
//								stores.stores.get(arg2).contact);
//						b.putString(Constants.TXT_STATUS,
//								stores.stores.get(arg2).status);
//						b.putString(Constants.TXT_DESCRIPTION,
//								stores.stores.get(arg2).description);
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
			Intent intent = new Intent(ActivityKPI.this,
					ActivityEditKPI.class);
			Bundle b = new Bundle();
			b.putInt(Constants.TXT_PID, pId);
			b.putInt(Constants.TXT_ID, 0);
			b.putString(Constants.TXT_TYPE_CHANGE, Constants.TXT_TYPE_ADD);

			startActivity(intent.putExtras(b));
			break;

		default:
			break;
		}
	}

}
