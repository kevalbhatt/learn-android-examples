package com.example.kbplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FileExplorer extends ListActivity {
	private String path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_explorere);
		// Use the current directory as title
		path = "/";
		if (getIntent().hasExtra("path")) {
			path = getIntent().getStringExtra("path");
		}
		setTitle(path);

		// Read all files sorted into the values-array
		List values = new ArrayList();
		File dir = new File(path);
		if (!dir.canRead()) {
			setTitle(getTitle() + " (inaccessible)");
		}
		
		String[] list = dir.list();
		
		if (list != null) {
			for (String file : list) {
				if (!file.startsWith(".")) {
					values.add(file);
				}
			}
		}
		
		Collections.sort(values);

		// Put the data into the list
		ArrayAdapter adapter = new ArrayAdapter
(this,android.R.layout.simple_expandable_list_item_2, android.R.id.text1, values);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String filename = (String) getListAdapter().getItem(position);
	
		if (path.endsWith(File.separator)) {
			
			filename = path + filename;
		} else {
			filename = path + File.separator + filename;
		}
		if (new File(filename).isDirectory()) {
			Intent intent = new Intent(this, FileExplorer.class);
			intent.putExtra("path", filename);
			startActivity(intent);
		} else {
			Toast.makeText(this, filename + " is not a directory", Toast.LENGTH_LONG).show();
			Intent i = new Intent(FileExplorer.this, VideoManager.class);
			i.putExtra("videoUrl", filename);
			i.putExtra("methodName", "filemanager");
			startActivity(i);
			this.finish();
		}
	}

}
