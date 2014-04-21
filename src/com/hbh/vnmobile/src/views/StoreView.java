package com.hbh.vnmobile.src.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbh.vnmobile.src.R;

public class StoreView extends LinearLayout {
	public TextView name;

	public StoreView(Context context) {
		super(context);
		LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.store_view, this, true);
		this.name = (TextView) findViewById(R.id.name);
	}

}
