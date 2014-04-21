package com.hbh.vnmobile.src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hbh.vnmobile.src.adapter.AppointmentAdapter;
import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Appointments;

public class ActivityAppointment extends BaseActivity implements OnClickListener {

	AppointmentAdapter adapter;
	ListView listview;
	Appointments appointments;
	int pId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_appointment);
		//
		pId = getIntent().getExtras().getInt(Constants.TXT_ID);
		//
		findViewById(R.id.btn_add).setOnClickListener(this);
		listview = (ListView) findViewById(R.id.listview);
		loadData();
	}

	protected void loadData() {
		String str = readFile(Constants.TXT_APPOINTMENT_FILE_NAME);
		appointments = gson.fromJson(str, Appointments.class);
		if (appointments != null) {
			adapter = new AppointmentAdapter(getBaseContext(), 0, appointments.appointments);
			listview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
			//
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

						Intent intent = new Intent(ActivityAppointment.this,
								ActivityEditAppointment.class);
						Bundle b = new Bundle();
						b.putInt(Constants.TXT_PID, pId);
						b.putInt(Constants.TXT_ID, appointments.appointments.get(arg2).id);
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
			Intent intent = new Intent(ActivityAppointment.this,
					ActivityEditAppointment.class);
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
