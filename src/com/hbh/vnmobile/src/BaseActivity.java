package com.hbh.vnmobile.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;

public abstract class BaseActivity extends Activity {
	
	public Gson gson = new Gson();
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);

		initView();
	}

	public abstract void initView();

	public void writeFile(String str, String filename) {
		try {
			FileOutputStream outputStream = openFileOutput(filename,
					Context.MODE_PRIVATE);
			outputStream.write(str.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String readFile(String filename) {
		File file = new File(getBaseContext().getFilesDir(), filename);
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(file));
			String strLine = "";

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				strLine += sCurrentLine;
			}

			return strLine;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	
	public void showSelectOneDate(String title, String btnTitle, final int id,
			final OnDateSelectedListener listener) {
		final Dialog mDialog = new Dialog(this,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		mDialog.getWindow().getAttributes().windowAnimations = R.style.Animations_SmileWindow;
		mDialog.setContentView(R.layout.dialog_select_date);
		mDialog.show();

		TextView tvtitle = (TextView) mDialog.findViewById(R.id.title);
		tvtitle.setText(title);

		final DatePicker picker = (DatePicker) mDialog
				.findViewById(R.id.datepicker);

		ImageButton close = (ImageButton) mDialog.findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDialog.dismiss();
			}
		});

		Button select = (Button) mDialog.findViewById(R.id.set);
		select.setText(btnTitle);
		select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Date date = new java.util.Date(picker.getYear(), picker
						.getMonth(), picker.getDayOfMonth());
				listener.onDateSelectedListener(id, date);
				mDialog.dismiss();
			}
		});
	}

	
	public void showSelectOneTime(String title, String btnTitle, final int id,
			final OnDateSelectedListener listener) {
		final Dialog mDialog = new Dialog(this,
				android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
		mDialog.getWindow().getAttributes().windowAnimations = R.style.Animations_SmileWindow;
		mDialog.setContentView(R.layout.dialog_select_time);
		mDialog.show();

		TextView tvtitle = (TextView) mDialog.findViewById(R.id.title);
		tvtitle.setText(title);

		final TimePicker picker = (TimePicker) mDialog
				.findViewById(R.id.timepicker);

		ImageButton close = (ImageButton) mDialog.findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDialog.dismiss();
			}
		});

		Button select = (Button) mDialog.findViewById(R.id.set);
		select.setText(btnTitle);
		select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int hour = picker.getCurrentHour();
				int minute = picker.getCurrentMinute();
				listener.onTimeSelectedListener(id, hour, minute);
				mDialog.dismiss();
			}
		});
	}

}
