package com.example.filemanager;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listView;
	private Button rootBtn;
	private Button extBtn;
	private ArrayList<FileItem> files;
	private ProgressDialog progressDialog;
	private FileItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView=(ListView)findViewById(R.id.listView1);
        rootBtn=(Button)findViewById(R.id.rootBtn);
        extBtn=(Button)findViewById(R.id.exitBtn);
        //files=new ArrayList<FileItem>();
        
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        
        
        /// test
        new GetFileAsync().execute("/");
        /// 
        
        
        
        
        
        
        rootBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
			}
		});
        
        extBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
    }
	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}
	public ArrayList<FileItem> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<FileItem> files) {
		this.files = files;
	}
	
	
	
	class GetFileAsync extends AsyncTask<String, Void, ArrayList<FileItem>>{
		
		
		@Override
		protected ArrayList<FileItem> doInBackground(String... params) {
			
			String path=params[0];
			File start=new File(path);
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
				}
				items.add(temp);
			}
			
			return items;
		}

		@Override
		protected void onPostExecute(ArrayList<FileItem> result) {
			getProgressDialog().cancel();
			setFiles(result);
			adapter=new FileItemAdapter(getApplicationContext(),R.layout.listitem, files);
	        listView.setAdapter(adapter);
	        adapter.setNotifyOnChange(true);
		}

		
	}
}
