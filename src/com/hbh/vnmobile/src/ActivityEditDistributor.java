package com.hbh.vnmobile.src;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Store;
import com.hbh.vnmobile.src.obj.Stores;

public class ActivityEditDistributor extends BaseActivity implements
		OnClickListener {

	int id;
	EditText edt_name, edt_contact, edt_mobile, edt_address, edt_stk,
			edt_description;
	String name, type = "---", contact, mobile, address, province = "---", stk,
			status = "---", description, district = "---";
	Button btn_update, btn_delete, btn_add, btn_update_location;
	Spinner spinner_province, spinner_district, spinner_type, spinner_status;

	Stores stores;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_edit_or_add_distributor);

		//
		Bundle b = getIntent().getExtras();
		String type_change = b.getString(Constants.TXT_TYPE_CHANGE);

		if (type_change.equals(Constants.TXT_TYPE_EDIT)) {

			id = b.getInt(Constants.TXT_ID);
			name = b.getString(Constants.TXT_NAME);
			type = b.getString(Constants.TXT_TYPE);
			contact = b.getString(Constants.TXT_CONTACT);
			mobile = b.getString(Constants.TXT_MOBILE);
			address = b.getString(Constants.TXT_ADDRESS);
			this.province = b.getString(Constants.TXT_PROVINCE);
			stk = b.getString(Constants.TXT_STK);
			status = b.getString(Constants.TXT_STATUS);
			description = b.getString(Constants.TXT_DESCRIPTION);
			district = b.getString(Constants.TXT_DISTRICT);
		}
		//

		spinner_province = (Spinner) findViewById(R.id.spinner_province);

		spinner_district = (Spinner) findViewById(R.id.spinner_district);

		spinner_type = (Spinner) findViewById(R.id.spinner_type);

		spinner_status = (Spinner) findViewById(R.id.spinner_status);

		edt_name = (EditText) findViewById(R.id.edt_title);

		edt_contact = (EditText) findViewById(R.id.edt_contact);

		edt_mobile = (EditText) findViewById(R.id.edt_mobile);

		edt_address = (EditText) findViewById(R.id.edt_address);

		edt_stk = (EditText) findViewById(R.id.edt_stk);

		edt_description = (EditText) findViewById(R.id.edt_desciption);
		//
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_update.setOnClickListener(this);

		btn_update_location = (Button) findViewById(R.id.btn_update_location);
		btn_update_location.setOnClickListener(this);

		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_delete.setOnClickListener(this);

		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);

		//
		if (type_change.equals(Constants.TXT_TYPE_EDIT)) {
			edt_stk.setText(stk);
			edt_name.setText(name);
			edt_contact.setText(contact);
			edt_mobile.setText(mobile);
			edt_address.setText(address);
			edt_description.setText(description);
			btn_add.setVisibility(View.GONE);
		} else {
			btn_update.setVisibility(View.GONE);
			btn_update_location.setVisibility(View.GONE);
			btn_delete.setVisibility(View.GONE);
		}
		//
		loadDada();
		//
		initDistrictSpinner();
		initProvinceSpinner();
		initStatusSpinner();
		initTypeSpinner();
	}

	protected void loadDada() {
		String str = readFile(Constants.TXT_STORE_FILE_NAME);
		stores = gson.fromJson(str, Stores.class);
	}

	protected void saveDada() {
		String str = gson.toJson(stores);
		writeFile(str, Constants.TXT_STORE_FILE_NAME);
		Intent intent = new Intent(ActivityEditDistributor.this,
				ActivityMap.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

	ArrayList<String> provinces = new ArrayList<String>();

	protected void initProvinceSpinner() {

		provinces.add("Hanoi");
		provinces.add("Ho Chi Minh");
		provinces.add("Da Nang");

		for (int i = 0; i < provinces.size(); i++)
			if (provinces.get(i).equals(this.provinces))
				provinces.remove(i);
		provinces.add(0, this.province);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, provinces);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_province.setAdapter(adapter);

	}

	ArrayList<String> statuss = new ArrayList<String>();

	protected void initStatusSpinner() {
		statuss.add("Open");
		statuss.add("Closed");
		statuss.add("Change");
		statuss.add("New");

		for (int i = 0; i < statuss.size(); i++)
			if (statuss.get(i).equals(this.status))
				statuss.remove(i);
		statuss.add(0, this.status);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, statuss);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_status.setAdapter(adapter);

	}

	ArrayList<String> districts = new ArrayList<String>();

	protected void initDistrictSpinner() {
		districts.add("Dong Da");
		districts.add("Hoan Kiem");
		districts.add("Hoang Mai");
		districts.add("Long Bien");

		for (int i = 0; i < districts.size(); i++)
			if (districts.get(i).equals(this.district))
				districts.remove(i);
		districts.add(0, this.district);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, districts);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_district.setAdapter(adapter);

	}

	ArrayList<String> types = new ArrayList<String>();

	protected void initTypeSpinner() {
		types.add("distributor");
		types.add("seller");

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
		for (int i = 0; i < stores.stores.size(); i++)
			if (stores.stores.get(i).id == id) {
				String str_title = edt_name.getText().toString().trim();
				stores.stores.get(i).name = str_title;

				String str_type = types.get(spinner_type
						.getLastVisiblePosition());
				stores.stores.get(i).type = str_type;

				String str_province = provinces.get(spinner_province
						.getLastVisiblePosition());
				stores.stores.get(i).province = str_province;

				String str_district = districts.get(spinner_district
						.getLastVisiblePosition());
				stores.stores.get(i).district = str_district;

				String str_status = types.get(spinner_status
						.getLastVisiblePosition());
				stores.stores.get(i).status = str_status;

				String str_add = edt_address.getText().toString().trim();
				stores.stores.get(i).address = str_add;

				String str_contact = edt_contact.getText().toString().trim();
				stores.stores.get(i).contact = str_contact;

				String str_mobile = edt_mobile.getText().toString().trim();
				stores.stores.get(i).mobile = str_mobile;

				String str_stk = edt_stk.getText().toString().trim();
				stores.stores.get(i).stk = str_stk;

				String str_description = edt_description.getText().toString()
						.trim();
				stores.stores.get(i).description = str_description;

			}
		Toast.makeText(getBaseContext(), "Updated", 3000).show();
		saveDada();
	}

	
	protected void updateLoc() {
		for (int i = 0; i < stores.stores.size(); i++)
			if (stores.stores.get(i).id == id) {
				String str_title = edt_name.getText().toString().trim();
				stores.stores.get(i).name = str_title;

				String str_type = types.get(spinner_type
						.getLastVisiblePosition());
				stores.stores.get(i).type = str_type;

				String str_province = provinces.get(spinner_province
						.getLastVisiblePosition());
				stores.stores.get(i).province = str_province;

				String str_district = districts.get(spinner_district
						.getLastVisiblePosition());
				stores.stores.get(i).district = str_district;

				String str_status = types.get(spinner_status
						.getLastVisiblePosition());
				stores.stores.get(i).status = str_status;

				String str_add = edt_address.getText().toString().trim();
				stores.stores.get(i).address = str_add;

				String str_contact = edt_contact.getText().toString().trim();
				stores.stores.get(i).contact = str_contact;

				String str_mobile = edt_mobile.getText().toString().trim();
				stores.stores.get(i).mobile = str_mobile;

				String str_stk = edt_stk.getText().toString().trim();
				stores.stores.get(i).stk = str_stk;

				String str_description = edt_description.getText().toString()
						.trim();
				stores.stores.get(i).description = str_description;
				
				stores.stores.get(i).latitude = ActivityMap.currentLat;
				stores.stores.get(i).longitude = ActivityMap.currentLong;

			}
		Toast.makeText(getBaseContext(), "Updated", 3000).show();
		saveDada();
	}

	protected void add() {
		// for (int i = 0; i < stores.stores.size(); i++)
		// if (stores.stores.get(i).id == id) {
		String str_title = edt_name.getText().toString().trim();

		String str_type = types.get(spinner_type.getLastVisiblePosition());

		String str_province = provinces.get(spinner_province
				.getLastVisiblePosition());

		String str_district = districts.get(spinner_district
				.getLastVisiblePosition());

		String str_status = types.get(spinner_status.getLastVisiblePosition());

		String str_add = edt_address.getText().toString().trim();

		String str_contact = edt_contact.getText().toString().trim();

		String str_mobile = edt_mobile.getText().toString().trim();

		String str_stk = edt_stk.getText().toString().trim();

		String str_description = edt_description.getText().toString().trim();

		Store store = new Store(stores.stores.size() + 1, str_title, str_add,
				str_type, str_province, str_district, str_contact, str_mobile,
				str_stk, str_status, str_description, ActivityMap.currentLat,
				ActivityMap.currentLong);
		stores.stores.add(store);
		// }
		Toast.makeText(getBaseContext(), "Added Distributor", 3000).show();

		saveDada();
	}

	protected void delete() {
		for (int i = 0; i < stores.stores.size(); i++)
			if (stores.stores.get(i).id == id) {
				stores.stores.remove(i);
				saveDada();
				
				break;
			}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_update:
			update();
			break;
		case R.id.btn_update_location:
			updateLoc();
			break;

		case R.id.btn_delete:
			delete();
			break;
		case R.id.btn_add:
			add();
			break;
		default:
			break;
		}
	}
}
