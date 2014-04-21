package com.hbh.vnmobile.src;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Kpi;
import com.hbh.vnmobile.src.obj.Order;
import com.hbh.vnmobile.src.obj.Orders;
import com.hbh.vnmobile.src.obj.Store;
import com.hbh.vnmobile.src.obj.Stores;

public class ActivityEditOrder extends BaseActivity implements OnClickListener {

	Orders orders;
	String type;
	int pId;
	int order_type = 0;

	TextView txt_outlet, txt_create_date, txt_modified, txt_created_by;
	ImageView img_store;
	EditText edt_p1, edt_p2, edt_p3, edt_p4, edt_p5, edt_description;
	Button btn_update, btn_add;
	Spinner spinner_status;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	int id;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_edit_order);
		//
		Bundle b = getIntent().getExtras();
		pId = b.getInt(Constants.TXT_PID);
		id = b.getInt(Constants.TXT_ID);
		type = b.getString(Constants.TXT_TYPE_CHANGE);
		order_type = b.getInt(Constants.TXT_ORDER__TYPE);
		//
		System.out.println("TYPE =" + order_type);

		spinner_status = (Spinner) findViewById(R.id.spinner_status);

		txt_outlet = (TextView) findViewById(R.id.txt_name);
		txt_create_date = (TextView) findViewById(R.id.txt_created_date);
		txt_created_by = (TextView) findViewById(R.id.txt_created_by);
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
		edt_description = (EditText) findViewById(R.id.description);

		//
		loadData(id);
		initStatusSpinner();
	}

	protected void loadData(int id) {
		String str = "";
		if (order_type == Constants.TYPE_ORDER_BY)
			str = readFile(Constants.TXT_ORDERBY_FILE_NAME);
		else
			str = readFile(Constants.TXT_ORDERD_FILE_NAME);
		orders = gson.fromJson(str, Orders.class);
		if (orders != null) {

			// orders.orders
			if (type.equals(Constants.TXT_TYPE_EDIT)) {
				btn_add.setVisibility(View.GONE);
				for (int i = 0; i < orders.orders.size(); i++)
					if (orders.orders.get(i).id == id) {
						Order s = orders.orders.get(i);
						txt_outlet.setText(s.outlet);
						txt_create_date.setText(s.create_date);
						txt_created_by.setText(s.created_by);
						txt_modified.setText(s.modified);

						edt_p1.setText(s.card10k);
						edt_p2.setText(s.card20k);
						edt_p3.setText(s.card50k);
						edt_p4.setText(s.card100k);
						edt_p5.setText(s.card3gusb);

						current_status = s.status;

					}
			} else {
				btn_update.setVisibility(View.GONE);

				String st = readFile(Constants.TXT_STORE_FILE_NAME);
				Stores stores = gson.fromJson(st, Stores.class);

				// for (int i = 0; i < stores.stores.size(); i++)
				// if (stores.stores.get(i).id == pId) {

				String newstring = new SimpleDateFormat("dd/MM/yyyy")
						.format(new Date());
				System.out.println(newstring); // 2011-01-18

				// Store s = stores.stores.get(i);
				txt_outlet.setText("My Store");
				txt_create_date.setText(newstring);
				txt_created_by.setText("admin");
				txt_modified.setText(newstring);

				// }
			}

		}
	}

	protected void saveData() {
		if (orders != null) {
			String s = gson.toJson(orders);

			if (order_type == Constants.TYPE_ORDER_BY)
				writeFile(s, Constants.TXT_ORDERBY_FILE_NAME);
			else
				writeFile(s, Constants.TXT_ORDERD_FILE_NAME);

			Intent intent = new Intent(ActivityEditOrder.this,
					ActivityOrders.class);
			Bundle b = new Bundle();
			b.putInt(Constants.TXT_PID, pId);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent.putExtras(b));

		}
	}

	ArrayList<String> statuss = new ArrayList<String>();
	String current_status = "---";

	protected void initStatusSpinner() {
		statuss.add("Pending");
		statuss.add("Denied");
		statuss.add("Approved");

		for (int i = 0; i < statuss.size(); i++)
			if (statuss.get(i).equals(this.current_status))
				statuss.remove(i);
		statuss.add(0, this.current_status);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, statuss);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_status.setAdapter(adapter);

	}

	protected void update() {
		if (orders != null) {

			for (int i = 0; i < orders.orders.size(); i++)
				if (orders.orders.get(i).id == id) {
					orders.orders.get(i).card10k = edt_p1.getText().toString();
					orders.orders.get(i).card20k = edt_p2.getText().toString();
					orders.orders.get(i).card50k = edt_p3.getText().toString();
					orders.orders.get(i).card100k = edt_p4.getText().toString();
					orders.orders.get(i).card3gusb = edt_p5.getText()
							.toString();
					orders.orders.get(i).status = statuss.get(spinner_status
							.getLastVisiblePosition());
					orders.orders.get(i).description = edt_description
							.getText().toString();
				}
			saveData();
			Toast.makeText(getBaseContext(), "Updated", 3000).show();
		}
	}

	protected void add() {
		if (orders != null) {
			Order s = new Order(orders.orders.size() + 1, txt_outlet.getText()
					.toString(), txt_create_date.getText().toString(),
					txt_modified.getText().toString(), txt_created_by.getText()
							.toString(), edt_p1.getText().toString(), edt_p2
							.getText().toString(), edt_p3.getText().toString(),
					edt_p4.getText().toString(), edt_p5.getText().toString(),
					edt_description.getText().toString(),
					statuss.get(spinner_status.getLastVisiblePosition()));
			orders.orders.add(s);
			saveData();
			Toast.makeText(getBaseContext(), "Added", 3000).show();
			// finish();

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
