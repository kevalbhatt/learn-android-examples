package com.example.chatting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class MainActivity extends Activity {
EditText me,you;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        me=(EditText) findViewById(R.id.editText1 );
        you=(EditText) findViewById(R.id.editText2);
        
        // setOnKeyListener dosnt work on softkeyboard so we use TextChangedListener
     /*  me.setOnKeyListener(new OnKeyListener() {
			
    	   
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				you.setText("");
				if (me.getText().toString().equalsIgnoreCase("hi")) {
					you.setText("hello");
				}
				return false;
			}
		});*/
       
        me.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (me.getText().toString().equalsIgnoreCase("hi")) {
					you.setText("hello");
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
