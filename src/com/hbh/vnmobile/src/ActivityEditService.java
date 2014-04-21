package com.hbh.vnmobile.src;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Service;
import com.hbh.vnmobile.src.obj.Services;

public class ActivityEditService extends BaseActivity implements
		OnClickListener, OnTouchListener, OnDateSelectedListener {

	int id;

	TextView tv_created_date, tv_modified, tv_create_by;
	EditText edt_phone_number, edt_quantity;
	Button btn_update, btn_add;
	Spinner spinner_service;

	Services myservices;
	String type_change, current_service = "---";

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_edit_service);

		//
		Bundle b = getIntent().getExtras();
		type_change = b.getString(Constants.TXT_TYPE_CHANGE);

		id = b.getInt(Constants.TXT_ID);

		if (type_change.equals(Constants.TXT_TYPE_EDIT)) {

		}
		//
		tv_created_date = (TextView) findViewById(R.id.txt_created_date);
		tv_create_by = (TextView) findViewById(R.id.txt_created_by);
		tv_modified = (TextView) findViewById(R.id.txt_modified);
		edt_phone_number = (EditText) findViewById(R.id.number);
		edt_quantity = (EditText) findViewById(R.id.quantity);

		spinner_service = (Spinner) findViewById(R.id.spinner_service);
		//
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_update.setOnClickListener(this);

		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);

		//
		loadDada();
		//
		initServiceSpinner();
	}

	protected void loadDada() {
		String str = readFile(Constants.TXT_SERVICE_FILE_NAME);
		myservices = gson.fromJson(str, Services.class);

		if (myservices != null) {
			if (type_change.equals(Constants.TXT_TYPE_EDIT)) {
				btn_add.setVisibility(View.GONE);
				for (int i = 0; i < myservices.services.size(); i++)
					if (myservices.services.get(i).id == id) {
						Service s = myservices.services.get(i);
						tv_created_date.setText(s.datetime);
						tv_modified.setText(s.modified);
						tv_create_by.setText(s.created_by);
						edt_phone_number.setText(s.number);
						edt_quantity.setText(s.quantity);
						current_service = s.service;
					}
			} else {
				btn_update.setVisibility(View.GONE);
				String datetime = new SimpleDateFormat("dd/MM/yyyy hh:mm")
				.format(new Date());
				tv_create_by.setText("admin");
				tv_created_date.setText(datetime);
			}

		}
	}

	protected void saveDada() {
		String str = gson.toJson(myservices);
		writeFile(str, Constants.TXT_SERVICE_FILE_NAME);
		Intent intent = new Intent(ActivityEditService.this,
				ActivityServices.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

	ArrayList<String> services = new ArrayList<String>();

	protected void initServiceSpinner() {
		services.add("Starter Kit");
		services.add("CRBT");
		services.add("Voicelmaill");
		services.add("MCA");
		services.add("Callblocking");
		services.add("Airtime");
		services.add("SMS");

		for (int i = 0; i < services.size(); i++)
			if (services.get(i).equals(this.current_service))
				services.remove(i);
		services.add(0, this.current_service);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, services);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_service.setAdapter(adapter);

	}

	protected void update() {
		for (int i = 0; i < myservices.services.size(); i++)
			if (myservices.services.get(i).id == id) {
				Service s = myservices.services.get(i);
				s.quantity = edt_quantity.getText().toString();
				s.service = services.get(spinner_service
						.getLastVisiblePosition());
				s.number = edt_phone_number.getText().toString();
			}

		saveDada();
	}

	protected void add() {

		if (myservices != null) {
			String datetime = new SimpleDateFormat("dd/MM/yyyy hh:mm")
					.format(new Date());
			Service s = new Service(myservices.services.size() + 1,
					services.get(spinner_service.getLastVisiblePosition()),
					datetime, edt_quantity.getText().toString(),
					edt_phone_number.getText().toString(), tv_modified
							.getText().toString(), tv_create_by.getText()
							.toString());
			myservices.services.add(s);
			// Appointment a = new Appointment(
			// appointments.appointments.size() + 1, edt_contact.getText()
			// .toString(), edt_address.getText().toString(),
			// types.get(spinner_type.getLastVisiblePosition()), tv_date
			// .getText().toString(),
			// tv_time.getText().toString(), statuss.get(spinner_status
			// .getLastVisiblePosition()), edt_description
			// .getText().toString());
			// appointments.appointments.add(a);

			Toast.makeText(getBaseContext(), "Added Service", 3000).show();
			saveDada();
		}
	}

	final int DATE_SELECT = 100;
	final int TIME_SELECT = 101;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_update:
			update();
			break;
		case R.id.btn_add:
			add();
			break;
		default:
			break;
		}
	}

	@Override
	public void onDateSelectedListener(int id, Date date) {
		// TODO Auto-generated method stub
		switch (id) {
		case DATE_SELECT:
			// String newstring = new SimpleDateFormat("dd/MM/yyyy")
			// .format(new Date());
			// tv_date.setText(newstring);

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onTimeSelectedListener(int id, int hour, int minute) {
		// TODO Auto-generated method stub
		switch (id) {
		case TIME_SELECT:
			// tv_time.setText(String.valueOf(hour) + ":" +
			// String.valueOf(minute));

			break;

		default:
			break;
		}
	}
}
