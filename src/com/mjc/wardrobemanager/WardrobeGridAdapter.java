package com.mjc.wardrobemanager;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WardrobeGridAdapter extends BaseAdapter{

	private ArrayList<WardrobeItem> items;
	private Context mContext; 
	
	
	public WardrobeGridAdapter(Context c){
		mContext = c;
		items = new ArrayList<WardrobeItem>();
	}
	
	public void add(WardrobeItem i){
		items.add(i);
		notifyDataSetChanged();
		
	}
	
	@Override
	public int getCount() {		
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			
		
		
		if (convertView!=null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.gridlistelement,null);
			((ImageView)convertView.findViewById(R.id.imageView1)).setImageDrawable(items.get(position).getPic());
			((TextView)convertView.findViewById(R.id.quantityLabel)).setText(items.get(position).getDescription());
			return convertView;
			
		}
		else{
			RelativeLayout r = (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.gridlistelement, null);
			ImageView v = (ImageView)r.findViewById(R.id.imageView1);
			v.setImageDrawable(items.get(position).getPic());
			TextView t = (TextView)r.findViewById(R.id.quantityLabel);
			t.setText(items.get(position).getDescription());
			return r;
		}
		
				
	}

}
