package com.hbh.vnmobile.src;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Kpi;
import com.hbh.vnmobile.src.obj.Kpis;
import com.hbh.vnmobile.src.obj.Store;
import com.hbh.vnmobile.src.obj.Stores;

public class ActivityEditKPI extends BaseActivity implements OnClickListener {

	Kpis kpis;
	String type;
	int pId;

	TextView txt_outlet, txt_create_date, txt_modified, txt_created_by,
			txt_location;
	ImageView img_store;
	EditText edt_p1, edt_p2, edt_p3, edt_p4, edt_p5;
	Button btn_update, btn_add;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	int id;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_edit_kpi);
		//
		Bundle b = getIntent().getExtras();
		pId = b.getInt(Constants.TXT_PID);
		id = b.getInt(Constants.TXT_ID);
		type = b.getString(Constants.TXT_TYPE_CHANGE);
		//
		txt_outlet = (TextView) findViewById(R.id.txt_name);
		txt_create_date = (TextView) findViewById(R.id.txt_created_date);
		txt_created_by = (TextView) findViewById(R.id.txt_created_by);
		txt_location = (TextView) findViewById(R.id.txt_location);
		txt_modified = (TextView) findViewById(R.id.txt_modified);

		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_update.setOnClickListener(this);

		edt_p1 = (EditText) findViewById(R.id.edt_p1);
		edt_p2 = (EditText) findViewById(R.id.edt_p2);
		edt_p3 = (EditText) findViewById(R.id.edt_p3);
		edt_p4 = (EditText) findViewById(R.id.edt_p4);
		edt_p5 = (EditText) findViewById(R.id.edt_p5);

		//
		loadData(id);
	}

	protected void loadData(int id) {
		String str = readFile(Constants.TXT_KPI_FILE_NAME);
		kpis = gson.fromJson(str, Kpis.class);
		if (kpis != null) {

			//
			if (type.equals(Constants.TXT_TYPE_EDIT)) {
				btn_add.setVisibility(View.GONE);
				for (int i = 0; i < kpis.kpis.size(); i++)
					if (kpis.kpis.get(i).id == id) {
						Kpi s = kpis.kpis.get(i);
						txt_outlet.setText(s.name);
						txt_create_date.setText(s.create_date);
						txt_created_by.setText(s.created_by);
						txt_location.setText(s.location);
						txt_modified.setText(s.modified_date);
						
						edt_p1.setText(s.card10k);
						edt_p2.setText(s.card20k);
						edt_p3.setText(s.card50k);
						edt_p4.setText(s.card100k);
						edt_p5.setText(s.card3gusb);

					}
			} else {
				btn_update.setVisibility(View.GONE);

				String st = readFile(Constants.TXT_STORE_FILE_NAME);
				Stores stores = gson.fromJson(st, Stores.class);

				for (int i = 0; i < stores.stores.size(); i++)
					if (stores.stores.get(i).id == pId) {

						String newstring = new SimpleDateFormat("dd/MM/yyyy")
								.format(new Date());
						System.out.println(newstring); // 2011-01-18

						Store s = stores.stores.get(i);
						txt_outlet.setText(s.name);
						txt_create_date.setText(newstring);
						txt_created_by.setText("admin");
						txt_location.setText(String.valueOf(s.latitude) + "/"
								+ String.valueOf(s.longitude));
						txt_modified.setText(newstring);

					}
			}

		}
	}

	protected void saveData() {
		if (kpis != null) {
			String s = gson.toJson(kpis);
			writeFile(s, Constants.TXT_KPI_FILE_NAME);
			Intent intent = new Intent(ActivityEditKPI.this,
					ActivityKPI.class);
			Bundle b = new Bundle();
			b.putInt(Constants.TXT_PID, pId);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent.putExtras(b));

		}
	}

	protected void update() {
		if (kpis != null) {

			for (int i = 0; i < kpis.kpis.size(); i++)
				if (kpis.kpis.get(i).id == id) {
					kpis.kpis.get(i).card10k = edt_p1.getText().toString();
					kpis.kpis.get(i).card20k = edt_p2.getText().toString();
					kpis.kpis.get(i).card50k = edt_p3.getText().toString();
					kpis.kpis.get(i).card100k = edt_p4.getText().toString();
					kpis.kpis.get(i).card3gusb = edt_p5.getText().toString();
				}
			saveData();
			Toast.makeText(getBaseContext(), "Updated", 3000).show();
		}
	}

	protected void add() {
		if (kpis != null) {
			Kpi s = new Kpi(kpis.kpis.size() + 1, txt_outlet.getText()
					.toString(), txt_create_date.getText().toString(),
					txt_modified.getText().toString(), txt_created_by.getText()
							.toString(), txt_location.getText().toString(),
					edt_p1.getText().toString(), edt_p2.getText().toString(),
					edt_p3.getText().toString(), edt_p4.getText().toString(),
					edt_p5.getText().toString());
			kpis.kpis.add(s);
			saveData();
			Toast.makeText(getBaseContext(), "Added", 3000).show();
//			finish();

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_add:
			add();
			break;
		case R.id.btn_update:
			update();
			break;

		default:
			break;
		}
	}

}
