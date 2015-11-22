package com.example.kbplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class VideoStoredInSDCard extends Activity {
	private Cursor videoCursor;
	private int videoColumnIndex;
	ListView videolist;
	int count;
	String thumbPath;

	String[] thumbColumns = { MediaStore.Video.Thumbnails.DATA, MediaStore.Video.Thumbnails.VIDEO_ID };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_list);
		initialization();
	}

	private void initialization() {
		System.gc();
		String[] videoProjection = { MediaStore.Video.Media._ID, MediaStore.Video.Media.DATA,
				MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.SIZE };
		videoCursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, videoProjection, null, null, null);
		count = videoCursor.getCount();
		videolist = (ListView) findViewById(R.id.PhoneVideoList);

		videolist.setAdapter(new VideoListAdapter(this.getApplicationContext()));
		videolist.setOnItemClickListener(videogridlistener);
	}

	private OnItemClickListener videogridlistener = new OnItemClickListener() {
		public void onItemClick(AdapterView parent, View v, int position, long id) {
			System.gc();
			videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
			videoCursor.moveToPosition(position);
			String filename = videoCursor.getString(videoColumnIndex);
			System.out.println("---------++-----------------------");
			System.out.println(filename);
			System.out.println("-------------++-------------------");
			Log.i("FileName: ", filename);
			Intent i = new Intent(VideoStoredInSDCard.this,VideoManager.class);
			i.putExtra("videoUrl", filename);
			i.putExtra("methodName", "listview");
			VideoStoredInSDCard.this.startActivity(i);
			// Intent intent = new Intent(VideoActivity.this, ViewVideo.class);
			// intent.putExtra("videofilename", filename);
			// startActivity(intent);
		}
	};

	public class VideoListAdapter extends BaseAdapter {
		private Context vContext;
		int layoutResourceId;

		public VideoListAdapter(Context c) {
			vContext = c;
		}

		/*public int getCount() {
			return videoCursor.getCount();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}*/
		public int getCount() {
			// TODO Auto-generated method stub
			return videoCursor.getCount();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@SuppressLint("ViewHolder")
		public View getView(int position, View convertView, ViewGroup parent) {
			View listItemRow = null;
			listItemRow = LayoutInflater.from(vContext).inflate(R.layout.list_item, parent, false);

			TextView txtTitle = (TextView) listItemRow.findViewById(R.id.txtTitle);
			TextView txtSize = (TextView) listItemRow.findViewById(R.id.txtSize);
			ImageView thumbImage = (ImageView) listItemRow.findViewById(R.id.imgIcon);

			videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
			videoCursor.moveToPosition(position);
			txtTitle.setText(videoCursor.getString(videoColumnIndex));

			videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
			videoCursor.moveToPosition(position);
			txtSize.setText(" Size(KB):" + videoCursor.getString(videoColumnIndex));

			int videoId = videoCursor.getInt(videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
			@SuppressWarnings("deprecation")
			Cursor videoThumbnailCursor = managedQuery(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, thumbColumns,
					MediaStore.Video.Thumbnails.VIDEO_ID + "=" + videoId, null, null);

			if (videoThumbnailCursor.moveToFirst()) {
				thumbPath = videoThumbnailCursor
						.getString(videoThumbnailCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
				Log.i("ThumbPath: ", thumbPath);

			}
			System.out.println("------------++--------------------");
			System.out.println(Uri.parse(thumbPath));
			System.out.println("-------------++-------------------");
			
			thumbImage.setImageURI(Uri.parse(thumbPath));

			return listItemRow;

		}

	}

}
