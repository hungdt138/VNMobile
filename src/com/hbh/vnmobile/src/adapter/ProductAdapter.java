package com.hbh.vnmobile.src.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hbh.vnmobile.src.obj.Product;
import com.hbh.vnmobile.src.views.StoreView;

public class ProductAdapter extends ArrayAdapter<Product> {

	ArrayList<Product> myArray;
	int res;
	Context context;
	int tvr;

	public ProductAdapter(Context context, int textViewResourceId,
			ArrayList<Product> objects) {
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

		final Product product = myArray.get(position);

		if (convertView == null)
			convertView = new StoreView(context);

		if (product != null) {

			TextView title = ((StoreView) convertView).name;
			title.setText(product.name);

			if (position % 2 == 0)
				title.setBackgroundColor(Color.parseColor("#FCDDCF"));
			else
				title.setBackgroundColor(Color.parseColor("#FDEFE9"));
		}
		return convertView;
	}
}
