package com.hbh.vnmobile.src.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbh.vnmobile.src.R;

public class ItemView extends LinearLayout {
	public TextView name, date;

	public ItemView(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) this.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.item_view, this, true);
		this.name = (TextView) findViewById(R.id.name);
		this.date = (TextView) findViewById(R.id.date);
	}

}
