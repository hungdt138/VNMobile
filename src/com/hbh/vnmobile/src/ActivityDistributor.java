package com.hbh.vnmobile.src;

import com.hbh.vnmobile.src.constants.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityDistributor extends BaseActivity implements
		OnClickListener {

	int id;
	TextView txt_name, txt_type, txt_contact, txt_mobile, txt_address, txt_stk,
			txt_status;
	String name, type, contact, mobile, address, province, stk, status,
			description, district;
	Button btn_edit, btn_survey, btn_update_stock, btn_kpi, btn_appointement;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_distributor);
		Bundle b = getIntent().getExtras();
		id = b.getInt(Constants.TXT_ID);
		name = b.getString(Constants.TXT_NAME);
		type = b.getString(Constants.TXT_TYPE);
		contact = b.getString(Constants.TXT_CONTACT);
		mobile = b.getString(Constants.TXT_MOBILE);
		address = b.getString(Constants.TXT_ADDRESS);
		province = b.getString(Constants.TXT_PROVINCE);
		stk = b.getString(Constants.TXT_STK);
		status = b.getString(Constants.TXT_STATUS);
		description = b.getString(Constants.TXT_DESCRIPTION);
		district = b.getString(Constants.TXT_DISTRICT);

		//
		txt_name = (TextView) findViewById(R.id.txt_name);
		txt_name.setText(name);

		txt_type = (TextView) findViewById(R.id.txt_type);
		txt_type.setText(type);

		txt_contact = (TextView) findViewById(R.id.txt_contact);
		txt_contact.setText(contact);

		txt_mobile = (TextView) findViewById(R.id.txt_mobile);
		txt_mobile.setText(mobile);

		txt_address = (TextView) findViewById(R.id.txt_address);
		txt_address.setText(address);

		txt_stk = (TextView) findViewById(R.id.txt_stk);
		txt_stk.setText(stk);

		txt_status = (TextView) findViewById(R.id.txt_status);
		txt_status.setText(status);
		//
		btn_edit = (Button) findViewById(R.id.btn_edit);
		btn_edit.setOnClickListener(this);

		btn_survey = (Button) findViewById(R.id.btn_survey);
		btn_survey.setOnClickListener(this);

		btn_update_stock = (Button) findViewById(R.id.btn_update_stock);
		btn_update_stock.setOnClickListener(this);

		btn_kpi = (Button) findViewById(R.id.btn_kpi);
		btn_kpi.setOnClickListener(this);

		btn_appointement = (Button) findViewById(R.id.btn_appoinment);
		btn_appointement.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_edit:
			editDistributorInfo();
			break;
		case R.id.btn_survey:
			gotoSurvey();
			break;
		case R.id.btn_update_stock:

			break;

		case R.id.btn_kpi:
			gotoKpi();
			break;
		case R.id.btn_appoinment:
			gotoAppointment();
			break;

		default:
			break;
		}

	}

	protected void gotoAppointment() {
		Intent i = new Intent(ActivityDistributor.this, ActivityAppointment.class);
		Bundle b = new Bundle();
		b.putInt(Constants.TXT_ID, id);
		startActivity(i.putExtras(b));
	}
	
	protected void gotoSurvey() {
		Intent i = new Intent(ActivityDistributor.this, ActivitySurvey.class);
		Bundle b = new Bundle();
		b.putInt(Constants.TXT_ID, id);
		startActivity(i.putExtras(b));
	}

	protected void gotoKpi() {
		Intent i = new Intent(ActivityDistributor.this, ActivityKPI.class);
		Bundle b = new Bundle();
		b.putInt(Constants.TXT_ID, id);
		startActivity(i.putExtras(b));
	}

	protected void editDistributorInfo() {
		Intent inten = new Intent(ActivityDistributor.this,
				ActivityEditDistributor.class);

		Bundle b = new Bundle();
		b.putString(Constants.TXT_TYPE_CHANGE, Constants.TXT_TYPE_EDIT);
		b.putInt(Constants.TXT_ID, id);
		b.putString(Constants.TXT_NAME, name);
		b.putString(Constants.TXT_TYPE, type);
		b.putString(Constants.TXT_PROVINCE, province);
		b.putString(Constants.TXT_DISTRICT, district);
		b.putString(Constants.TXT_ADDRESS, address);
		b.putString(Constants.TXT_STK, stk);
		b.putString(Constants.TXT_MOBILE, mobile);
		b.putString(Constants.TXT_CONTACT, contact);
		b.putString(Constants.TXT_STATUS, status);
		b.putString(Constants.TXT_DESCRIPTION, description);
		startActivity(inten.putExtras(b));
	}

}
