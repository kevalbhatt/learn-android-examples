package com.example.talking.android;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	Button speak;
	EditText text;
	TextToSpeech speaker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speak=(Button) findViewById(R.id.button1);
        text=(EditText) findViewById(R.id.editText1);
        
        speak.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				speaker=new TextToSpeech(getApplicationContext(), new OnInitListener() {
					
					public void onInit(int arg0) {
						// TODO Auto-generated method stub
						speaker.setLanguage(Locale.ENGLISH);
						speaker.speak(text.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
					}
				});
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
