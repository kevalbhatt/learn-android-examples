package com.example.pixar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	ImageView ronaldo;
	RadioGroup resize;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ronaldo = (ImageView) findViewById(R.id.imageView1);
		resize = (RadioGroup) findViewById(R.id.radioGroup1);
		resize.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub

			}

		});
		resize.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				switch (arg1) {
				case R.id.radio0:
					ronaldo.setLayoutParams(new RelativeLayout.LayoutParams(100, 100));
					break;
				case R.id.radio1:
					ronaldo.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
					break;
				case R.id.radio2:
					ronaldo.setLayoutParams(new RelativeLayout.LayoutParams(300, 300));
					break;

				default:
					break;
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
