package com.hbh.vnmobile.src;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends BaseActivity implements OnClickListener {

	EditText username, password;
	CheckBox checkbox;
	Button login, forgot;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	String USERNAME_DEMO = "admin";
	String PASSWORD_DEMO = "admin";

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_login);
		//
		checkbox = (CheckBox) findViewById(R.id.remember);
		//
		username = (EditText) findViewById(R.id.edt_username);
		password = (EditText) findViewById(R.id.edt_password);
		//
		findViewById(R.id.login).setOnClickListener(this);
		findViewById(R.id.forgot).setOnClickListener(this);
		
		//
		restoreUIState();
//		2B:E7:47:A4:82:07:97:FD:4E:46:DA:BA:77:94:AB:CB:F1:FE:DB:DA
	}

	@Override
	public void onClick(View v) {
		
	}

	protected void login() {
		String u = username.getText().toString().trim();
		String p = password.getText().toString().trim();

		if (u.equals("") || p.equals(""))
			Toast.makeText(getBaseContext(), "Bạn cần nhập đủ thông tin",
					Toast.LENGTH_LONG).show();
		else {

			if (!u.equals(USERNAME_DEMO) || !p.equals(PASSWORD_DEMO))
				Toast.makeText(getBaseContext(), "Sai mật khẩu",
						Toast.LENGTH_LONG).show();
			else
				startActivity(new Intent(ActivityLogin.this, ActivityHome.class));
		}
	}
	
	
	private final String ACCOUNT = "ACCOUNT";
	private final String PASSWORD = "PASSWORD";
	private final String SAVE_PASSWORD = "SAVE_PASSWORD";
	
	public void saveUIState() {

		SharedPreferences saveUI = getPreferences(Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = saveUI.edit();

		editor.putString(ACCOUNT, username.getText().toString());
		editor.putString(PASSWORD, password.getText().toString());
		editor.putBoolean(SAVE_PASSWORD, checkbox.isChecked());
		editor.commit();
	}

	public void restoreUIState() {
		SharedPreferences savedUI = getPreferences(Activity.MODE_PRIVATE);
		boolean bool = savedUI.getBoolean(SAVE_PASSWORD, true);
		checkbox.setChecked(bool);
		if (bool) {
			String usr = savedUI.getString(ACCOUNT, "");
			username.setText(usr);
			String pwd = savedUI.getString(PASSWORD, "");
			password.setText(pwd);
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		saveUIState();
	}

}
