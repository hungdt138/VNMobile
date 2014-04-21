package com.hbh.vnmobile.src;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.hbh.vnmobile.src.adapter.OrderAdapter;
import com.hbh.vnmobile.src.adapter.ServiceAdapter;
import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Orders;

public class ActivityOrders extends BaseActivity implements OnClickListener {

	OrderAdapter adapterOby, adapterOd;
	ListView lvOrder, lvOrderBy;
	Orders orderbys, ordereds;
	Button btn_ordered, btn_orderby;

	// int pId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_order);
		//
		// pId = getIntent().getExtras().getInt(Constants.TXT_ID);
		//
		findViewById(R.id.btn_add).setOnClickListener(this);
		(btn_orderby = (Button) findViewById(R.id.btn_orderby))
				.setOnClickListener(this);
		(btn_ordered = (Button) findViewById(R.id.btn_ordered))
				.setOnClickListener(this);

		lvOrder = (ListView) findViewById(R.id.listview);
		lvOrderBy = (ListView) findViewById(R.id.listview2);
		loadData();
	}

	protected void loadData() {
		String str1 = readFile(Constants.TXT_ORDERBY_FILE_NAME);
		orderbys = gson.fromJson(str1, Orders.class);
		System.out.println("STER1="+str1);

		String str2 = readFile(Constants.TXT_ORDERD_FILE_NAME);
		ordereds = gson.fromJson(str2, Orders.class);

		if (orderbys != null) {
			adapterOby = new OrderAdapter(getBaseContext(), 0, orderbys.orders);
			lvOrderBy.setAdapter(adapterOby);
			adapterOby.notifyDataSetChanged();

			lvOrderBy.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					Intent intent = new Intent(ActivityOrders.this,
							ActivityEditOrder.class);
					Bundle b = new Bundle();
					b.putInt(Constants.TXT_ORDER__TYPE, Constants.TYPE_ORDER_BY);
					b.putInt(Constants.TXT_ID, orderbys.orders.get(arg2).id);
					b.putString(Constants.TXT_TYPE_CHANGE,
							Constants.TXT_TYPE_EDIT);
					startActivity(intent.putExtras(b));
				}
			});
		}

		if (ordereds != null) {
			adapterOd = new OrderAdapter(getBaseContext(), 0, ordereds.orders);
			lvOrder.setAdapter(adapterOd);
			adapterOd.notifyDataSetChanged();

			lvOrder.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					Intent intent = new Intent(ActivityOrders.this,
							ActivityEditOrder.class);
					Bundle b = new Bundle();
					b.putInt(Constants.TXT_ORDER__TYPE, Constants.TYPE_ORDERED);
					b.putInt(Constants.TXT_ID, ordereds.orders.get(arg2).id);
					b.putString(Constants.TXT_TYPE_CHANGE,
							Constants.TXT_TYPE_EDIT);
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
			Intent intent = new Intent(ActivityOrders.this,
					ActivityEditOrder.class);
			Bundle b = new Bundle();
			// b.putInt(Constants.TXT_PID, pId);
			if (onOrdered)
				b.putInt(Constants.TXT_ORDER__TYPE, Constants.TYPE_ORDERED);
			else
				b.putInt(Constants.TXT_ORDER__TYPE, Constants.TYPE_ORDER_BY);
			b.putInt(Constants.TXT_ID, 0);
			b.putString(Constants.TXT_TYPE_CHANGE, Constants.TXT_TYPE_ADD);

			startActivity(intent.putExtras(b));
			break;
		case R.id.btn_ordered:
			if (!onOrdered) {
				onOrdered = true;
				btn_orderby
						.setBackgroundResource(R.drawable.btn_order_by_deactive);
				btn_ordered
						.setBackgroundResource(R.drawable.btn_ordered_active);
				lvOrder.setVisibility(View.VISIBLE);
				lvOrderBy.setVisibility(View.INVISIBLE);

			}
			break;

		case R.id.btn_orderby:
			if (onOrdered) {
				onOrdered = false;
				btn_orderby
						.setBackgroundResource(R.drawable.btn_order_by_active);
				btn_ordered
						.setBackgroundResource(R.drawable.btn_ordered_deactive);
				lvOrder.setVisibility(View.INVISIBLE);
				lvOrderBy.setVisibility(View.VISIBLE);
			}

			break;

		default:
			break;
		}
	}

	boolean onOrdered = true;
}
