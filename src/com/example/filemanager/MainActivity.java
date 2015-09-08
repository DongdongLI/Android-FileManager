package com.example.filemanager;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private Button rootBtn;
	private Button extBtn;
	private ArrayList<FileItem> files;
	private FileItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        rootBtn=(Button)findViewById(R.id.rootBtn);
        extBtn=(Button)findViewById(R.id.exitBtn);
        
        fill("/");
        
        
        rootBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// back to root
				fill("/");
			}
		});
        
        extBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
    }

	public ArrayList<FileItem> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<FileItem> files) {
		this.files = files;
	}
	
	public void fill(String dir){
		File start=new File(dir);
		ArrayList<FileItem> items = new ArrayList<FileItem>();
		File[] files=start.listFiles();
		
		for(File f:files){
			FileItem temp=new FileItem();
			if(f.isDirectory()){
				temp.setIsDir(true);
				temp.setPath(f.getAbsolutePath());
			}else{
				temp.setIsDir(false);
				temp.setPath(f.getAbsolutePath());
				temp.setFileName(f.getName());
			}
			items.add(temp);
		}
		
				adapter=new FileItemAdapter(getApplicationContext(),R.layout.listitem, items);
				setListAdapter(adapter);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		FileItem clicked=adapter.getItem(position);
		Log.d("demo1","next path: "+clicked.getPath() );
		Toast.makeText(getApplicationContext(), "next path: "+clicked.getPath(), Toast.LENGTH_SHORT).show();
		//new GetFileAsync().execute(getFiles().get(position).getPath());
		fill(clicked.getPath());
	}
	
	
}
	

