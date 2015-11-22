package com.example.login_signup;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	LoginDataBaseAdapter loginDataBaseAdapter;
	Button login;
	Button registerr;
	EditText enterpassword, username;
	TextView forgetpass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		login = (Button) findViewById(R.id.login_btn);
		registerr = (Button) findViewById(R.id.register_btn);
		username = (EditText) findViewById(R.id.username_edt);
		enterpassword = (EditText) findViewById(R.id.password_edt);
		forgetpass = (TextView) findViewById(R.id.textView3);

		loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
		loginDataBaseAdapter.open();

		registerr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, Registration.class);
				startActivity(i);
			}
		});

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Username = username.getText().toString();
				String Password = enterpassword.getText().toString();

				Boolean storedPassword = loginDataBaseAdapter.getSinlgeEntry(Username, Password);

				if (storedPassword) {
					Toast.makeText(MainActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
					Intent ii = new Intent(MainActivity.this, Home.class);
					startActivity(ii);
				} else {
					Toast.makeText(MainActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
				}
			}
		});

		forgetpass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				final Dialog dialog = new Dialog(MainActivity.this);
				dialog.getWindow();
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.forget_search);
				dialog.show();

				final EditText security = (EditText) dialog.findViewById(R.id.securityhint_edt);
				final TextView getpass = (TextView) dialog.findViewById(R.id.textView3);

				Button ok = (Button) dialog.findViewById(R.id.getpassword_btn);
				Button cancel = (Button) dialog.findViewById(R.id.cancel_btn);

				ok.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {

						String userName = security.getText().toString();
						if (userName.equals("")) {
							Toast.makeText(getApplicationContext(), "Please enter your securityhint",
									Toast.LENGTH_SHORT).show();
						} else {
							String storedPassword = loginDataBaseAdapter.getAllTags(userName);
							if (storedPassword == null) {
								Toast.makeText(getApplicationContext(), "Please enter correct securityhint",
										Toast.LENGTH_SHORT).show();
							} else {
								Log.d("GET PASSWORD", storedPassword);
								getpass.setText(storedPassword);
							}
						}
					}
				});
				cancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Close The Database
		loginDataBaseAdapter.close();
	}

}
