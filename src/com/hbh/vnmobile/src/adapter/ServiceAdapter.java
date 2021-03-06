package com.hbh.vnmobile.src.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hbh.vnmobile.src.obj.Service;
import com.hbh.vnmobile.src.views.ItemView;

public class ServiceAdapter extends ArrayAdapter<Service> {

	ArrayList<Service> myArray;
	int res;
	Context context;
	int tvr;

	public ServiceAdapter(Context context, int textViewResourceId,
			ArrayList<Service> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub

		this.context = context;
		res = textViewResourceId;
		myArray = objects;
		this.tvr = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// // TODO Auto-generated method stub

		return displayItemType(convertView, position);

	}

	private View displayItemType(View convertView, final int position) {

		final Service product = myArray.get(position);

		if (convertView == null)
			convertView = new ItemView(context);

		if (product != null) {

			TextView title = ((ItemView) convertView).name;
			title.setText(product.service);

			
			TextView date = ((ItemView) convertView).date;
			date.setText(product.datetime);

			if (position % 2 == 0)
				convertView.setBackgroundColor(Color.parseColor("#FCDDCF"));
			else
				convertView.setBackgroundColor(Color.parseColor("#FDEFE9"));
		}
		return convertView;
	}
}
