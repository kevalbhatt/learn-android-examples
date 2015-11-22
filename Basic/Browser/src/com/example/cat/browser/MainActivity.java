package com.example.cat.browser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
Button go;
WebView page;
EditText url;
private ProgressBar progress;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=(Button) findViewById(R.id.button1);
        page=(WebView) findViewById(R.id.webView1);
        url=(EditText) findViewById(R.id.editText1);
        progress = (ProgressBar) findViewById(R.id.progressBar);
		progress.setMax(100);
        go.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				page.setWebChromeClient(new MyWebViewClient());
				page.setWebViewClient(new WebViewClient());
				page.getSettings().setJavaScriptEnabled(true);
				page.loadUrl("http://"+ url.getText().toString());
				MainActivity.this.progress.setProgress(0);
			}
		});
    }

    private class MyWebViewClient extends WebChromeClient {	
		@Override
		public void onProgressChanged(WebView view, int newProgress) {			
			MainActivity.this.setValue(newProgress);
			super.onProgressChanged(view, newProgress);
		}
	}
    public void setValue(int progress) {
		this.progress.setProgress(progress);		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
