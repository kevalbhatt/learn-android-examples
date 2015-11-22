package com.example.kbplayer;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoManager extends Activity implements SurfaceHolder.Callback, OnPreparedListener {
	VideoView view;
	Button play, browse,gallery;
	EditText videoPath;

	MediaPlayer mediaPlayer;
	SurfaceHolder vidHolder;
	SurfaceView vidSurface;
	String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = (VideoView) findViewById(R.id.videoView1);
		play = (Button) findViewById(R.id.button1);
		browse = (Button) findViewById(R.id.button2);
		gallery = (Button) findViewById(R.id.button3);
		videoPath = (EditText) findViewById(R.id.text1);
		
		browse.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(VideoManager.this, FileExplorer.class);
				startActivity(i);

			}
		});
		gallery.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(VideoManager.this, VideoStoredInSDCard.class);
				startActivity(i);

			}
		});
	
		Intent intent = getIntent();
		String value = intent.getStringExtra("videoUrl");
		
		if(value != null){
			
		}

		
		
		
		
		
		String methodName = intent.getStringExtra("methodName");
		if(methodName != null){
			if (methodName.equals("filemanager") || methodName.equals("listview")){
				fromListView(value);
			} else {
				simpleGetAbsolutePath();
			}
		}else{
			simpleGetAbsolutePath();
		}
		
		// MediaController()

		// surface();
	}

	public void simpleUrl() {
		

		play.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				view.setVideoPath("/mnt/sdcard/Download/Home/Home.mp4");
				view.start();

			}
		});
	}

	public void simpleGetAbsolutePath() {
	
		

		String c = Environment.getExternalStorageDirectory().getAbsolutePath();
		videoPath.setText(c + "/Download/Home/Home.mp4");
		play.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String t = videoPath.getText().toString();
				System.out.println(t);
				view.setVideoPath(t);
				view.start();

			}
		});
		
	}

	public void fromListView(String url) {

		//VideoView vidView = (VideoView) findViewById(R.id.videoView1);
		
		MediaController vidControl = new MediaController(this);
		vidControl.setAnchorView(view);
		view.setMediaController(vidControl);
		if (url != null) {
			//videoPath.setText(url);
			view.setVideoPath(url);
		} else {
			view.setVideoPath("/sdcard/Download/Home/Home.mp4");
		}
		view.start();

	}

	public void MediaController() {
		VideoView vidView = (VideoView) findViewById(R.id.videoView1);
		MediaController vidControl = new MediaController(this);
		vidControl.setAnchorView(vidView);
		vidView.setMediaController(vidControl);

		Uri vidUri = Uri.parse(vidAddress);// vidAddress =
											// "http://youtube..............."
		vidView.setVideoURI(vidUri);
		vidView.start();
	}

	public void surface() {

		vidSurface = (SurfaceView) findViewById(R.id.surfView);
		vidHolder = vidSurface.getHolder();
		vidHolder.addCallback(this);

	}

	public static void printNamesToLogCat(Context context) {
		Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
		String[] projection = { MediaStore.Video.VideoColumns.DATA };
		Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
		int vidsCount = 0;
		if (c != null) {
			vidsCount = c.getCount();
			while (c.moveToNext()) {
				Log.d("VIDEO", c.getString(0));
			}
			c.close();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mediaPlayer.start();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		// prepare for playback
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDisplay(vidHolder);
			mediaPlayer.setDataSource(vidAddress);
			mediaPlayer.prepare();
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}
}
