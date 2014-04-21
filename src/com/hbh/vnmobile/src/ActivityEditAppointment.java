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
import com.hbh.vnmobile.src.obj.Appointment;
import com.hbh.vnmobile.src.obj.Appointments;

public class ActivityEditAppointment extends BaseActivity implements
		OnClickListener, OnTouchListener, OnDateSelectedListener {

	int id, pId;

	TextView tv_date, tv_time;
	EditText edt_contact, edt_address, edt_description;
	String contact_name, type = "---", address, status = "---", description;
	Button btn_update, btn_add;
	Spinner spinner_type, spinner_status;

	Appointments appointments;
	String type_change;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_edit_appointment);

		//
		Bundle b = getIntent().getExtras();
		type_change = b.getString(Constants.TXT_TYPE_CHANGE);

		id = b.getInt(Constants.TXT_ID);
		pId = b.getInt(Constants.TXT_PID);

		if (type_change.equals(Constants.TXT_TYPE_EDIT)) {

		}
		//

		spinner_type = (Spinner) findViewById(R.id.spinner_type);
		spinner_status = (Spinner) findViewById(R.id.spinner_status);
		edt_contact = (EditText) findViewById(R.id.edt_contact);
		edt_address = (EditText) findViewById(R.id.edt_address);
		tv_date = (TextView) findViewById(R.id.tv_date);
		tv_date.setOnClickListener(this);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_time.setOnClickListener(this);

		edt_description = (EditText) findViewById(R.id.edt_description);
		//
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_update.setOnClickListener(this);

		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);

		//
		loadDada();
		//
		initStatusSpinner();
		initTypeSpinner();
	}

	protected void loadDada() {
		String str = readFile(Constants.TXT_APPOINTMENT_FILE_NAME);
		appointments = gson.fromJson(str, Appointments.class);

		if (appointments != null) {
			if (type_change.equals(Constants.TXT_TYPE_EDIT)) {
				btn_add.setVisibility(View.GONE);
				for (int i = 0; i < appointments.appointments.size(); i++)
					if (appointments.appointments.get(i).id == id) {
						Appointment a = appointments.appointments.get(i);
						edt_contact.setText(a.contact_name);
						edt_address.setText(a.address);
						edt_description.setText(a.description);
						tv_date.setText(a.date);
						tv_time.setText(a.time);
						type = a.type;
						status = a.status;
					}
			} else {
				btn_update.setVisibility(View.GONE);
			}

		}
	}

	protected void saveDada() {
		String str = gson.toJson(appointments);
		writeFile(str, Constants.TXT_APPOINTMENT_FILE_NAME);
		Intent intent = new Intent(ActivityEditAppointment.this,
				ActivityAppointment.class);
		Bundle b = new Bundle();
		b.putInt(Constants.TXT_PID, pId);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent.putExtras(b));
		finish();
	}

	ArrayList<String> provinces = new ArrayList<String>();

	ArrayList<String> statuss = new ArrayList<String>();

	protected void initStatusSpinner() {
		statuss.add("Open");
		statuss.add("Closed");
		statuss.add("Expired");

		for (int i = 0; i < statuss.size(); i++)
			if (statuss.get(i).equals(this.status))
				statuss.remove(i);
		statuss.add(0, this.status);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, statuss);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_status.setAdapter(adapter);

	}

	ArrayList<String> types = new ArrayList<String>();

	protected void initTypeSpinner() {
		types.add("meeting");
		types.add("review");

		for (int i = 0; i < types.size(); i++)
			if (types.get(i).equals(this.type))
				types.remove(i);
		types.add(0, this.type);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, types);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_type.setAdapter(adapter);

	}

	protected void update() {
		for (int i = 0; i < appointments.appointments.size(); i++)
			if (appointments.appointments.get(i).id == id) {
				Appointment a = appointments.appointments.get(i);
				a.contact_name = edt_contact.getText().toString();
				a.address = edt_address.getText().toString();
				a.date = tv_date.getText().toString();
				a.time = tv_time.getText().toString();
				a.status = statuss.get(spinner_status.getLastVisiblePosition());
				a.type = types.get(spinner_type.getLastVisiblePosition());
				a.description = edt_description.getText().toString();
			}

		saveDada();
	}

	protected void add() {

		if (appointments != null) {

			Appointment a = new Appointment(
					appointments.appointments.size() + 1, edt_contact.getText()
							.toString(), edt_address.getText().toString(),
					types.get(spinner_type.getLastVisiblePosition()), tv_date
							.getText().toString(),
					tv_time.getText().toString(), statuss.get(spinner_status
							.getLastVisiblePosition()), edt_description
							.getText().toString());
			appointments.appointments.add(a);

			Toast.makeText(getBaseContext(), "Added Appointment", 3000).show();
			saveDada();
		}
	}

	protected void delete() {
		for (int i = 0; i < appointments.appointments.size(); i++)
			if (appointments.appointments.get(i).id == id) {
				appointments.appointments.remove(i);
				saveDada();
				Intent intent = new Intent(ActivityEditAppointment.this,
						ActivityMap.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
				break;
			}
	}

	final int DATE_SELECT = 100;
	final int TIME_SELECT = 101;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_time:
			showSelectOneTime("Select Time", "Select", TIME_SELECT, this);
			break;
		case R.id.tv_date:
			showSelectOneDate("Select Date", "Select", DATE_SELECT, this);
			break;
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
			String newstring = new SimpleDateFormat("dd/MM/yyyy")
					.format(new Date());
			tv_date.setText(newstring);

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
			tv_time.setText(String.valueOf(hour) + ":" + String.valueOf(minute));

			break;

		default:
			break;
		}
	}
}
