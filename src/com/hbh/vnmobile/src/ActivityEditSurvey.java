package com.hbh.vnmobile.src;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hbh.vnmobile.src.adapter.SurveyAdapter;
import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Store;
import com.hbh.vnmobile.src.obj.Stores;
import com.hbh.vnmobile.src.obj.Survey;
import com.hbh.vnmobile.src.obj.Surveys;

public class ActivityEditSurvey extends BaseActivity implements OnClickListener {

	SurveyAdapter adapter;
	Surveys surveys;
	String type;
	int pId;

	TextView txt_outlet, txt_create_date, txt_modified, txt_created_by,
			txt_location;
	ImageView img_store;
	EditText edt_comment;
	CheckBox cb_visibility, cb_competitor, cb_posm, cb_branch, cb_friendly;
	Button btn_update, btn_add;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	int id;

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_edit_survey);
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

		cb_branch = (CheckBox) findViewById(R.id.cb_branch);
		cb_visibility = (CheckBox) findViewById(R.id.cb_visibility);
		cb_competitor = (CheckBox) findViewById(R.id.cb_competitor);
		cb_friendly = (CheckBox) findViewById(R.id.cb_friendly);
		cb_posm = (CheckBox) findViewById(R.id.cb_posm);
		
		img_store = (ImageView) findViewById(R.id.img_store);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_update.setOnClickListener(this);
		findViewById(R.id.btn_capture).setOnClickListener(this);

		edt_comment = (EditText) findViewById(R.id.edt_comment);

		//
		loadData(id);
	}

	protected void loadData(int id) {
		String str = readFile(Constants.TXT_SURVEY_FILE_NAME);
		surveys = gson.fromJson(str, Surveys.class);
		if (surveys != null) {

			//
			if (type.equals(Constants.TXT_TYPE_EDIT)) {
				btn_add.setVisibility(View.GONE);
				for (int i = 0; i < surveys.surveys.size(); i++)
					if (surveys.surveys.get(i).id == id) {
						Survey s = surveys.surveys.get(i);
						txt_outlet.setText(s.name);
						txt_create_date.setText(s.create_date);
						txt_created_by.setText(s.created_by);
						txt_location.setText(s.location);
						txt_modified.setText(s.modified_date);

						cb_visibility.setChecked(s.visibility);
						cb_branch.setChecked(s.branch);
						cb_competitor.setChecked(s.sale_any_item_competitor);
						cb_friendly.setChecked(s.friendly);
						cb_posm.setChecked(s.has_posm);

						edt_comment.setText(s.comment);
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
		if (surveys != null) {
			String s = gson.toJson(surveys);
			writeFile(s, Constants.TXT_SURVEY_FILE_NAME);
		}
	}

	protected void update() {
		if (surveys != null) {
			boolean vi = cb_visibility.isChecked();
			boolean co = cb_competitor.isChecked();
			boolean po = cb_posm.isChecked();
			boolean br = cb_branch.isChecked();
			boolean fr = cb_friendly.isChecked();

			String cm = edt_comment.getText().toString();

			for (int i = 0; i < surveys.surveys.size(); i++)
				if (surveys.surveys.get(i).id == id) {
					surveys.surveys.get(i).comment = cm;
					surveys.surveys.get(i).branch = br;
					surveys.surveys.get(i).has_posm = po;
					surveys.surveys.get(i).visibility = vi;
					surveys.surveys.get(i).sale_any_item_competitor = co;
					surveys.surveys.get(i).friendly = fr;
				}
			saveData();
			Toast.makeText(getBaseContext(), "Updated", 3000).show();
		}
	}

	protected void add() {
		if (surveys != null) {
			Survey s = new Survey(surveys.surveys.size() + 1, txt_outlet
					.getText().toString(),
					txt_create_date.getText().toString(), txt_modified
							.getText().toString(), txt_created_by.getText()
							.toString(), txt_location.getText().toString(),
					edt_comment.getText().toString(),
					cb_visibility.isChecked(), cb_competitor.isChecked(),
					cb_posm.isChecked(), cb_branch.isChecked(),
					cb_friendly.isChecked());
			surveys.surveys.add(s);
			saveData();
			Toast.makeText(getBaseContext(), "Added", 3000).show();
			Intent intent = new Intent(ActivityEditSurvey.this,
					ActivitySurvey.class);
			Bundle b = new Bundle();
			b.putInt(Constants.TXT_PID, pId);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent.putExtras(b));
			finish();

		}
	}
	
	private static int RESULT_TAKE_NEW = 1337;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_capture:
			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, RESULT_TAKE_NEW);
			break;
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

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && null != data) {
			if (requestCode == RESULT_TAKE_NEW) {

				Bundle b = data.getExtras();
				Bitmap tmp = (Bitmap) b.get("data");

				if (tmp != null) {
					int w = tmp.getWidth(), h = tmp.getHeight();
					// Log.e("size", w+" "+h);
					if (w > 500) {
						h = h * 500 / w;
						w = 500;
					}
					if (h > 500) {
						w = w * 500 / h;
						h = 500;
					}
					img_store.setBackgroundDrawable(new BitmapDrawable(tmp));
				
				}
			}
		} else
			super.onActivityResult(requestCode, resultCode, data);
	}

}
