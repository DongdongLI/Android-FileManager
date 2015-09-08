package com.example.filemanager;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FileItemAdapter extends ArrayAdapter<FileItem>{
	
	List<FileItem> mData;
	Context mContext;
	int mResource;
	
	public FileItemAdapter(Context context, int resource,List<FileItem> objects) {
		super(context, resource, objects);
		this.mContext=context;
		this.mData=objects;
		this.mResource=resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate( mResource, parent,false);
		}
		
		FileItem item=mData.get(position);
		TextView pathTextView=(TextView)convertView.findViewById(R.id.pathTex);
		if(item.getFileName()!=null)
			pathTextView.setText(item.getFileName());
		else
			pathTextView.setText(item.getPath());
		return convertView;
	}
	
	
}
