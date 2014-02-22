package com.mjc.wardrobemanager;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class TagAdapter extends BaseAdapter {

	ArrayList<String>tags;
	Context mContext;
	WardrobeItem item;
	
	public TagAdapter(Context context, WardrobeItem item){
		mContext = context;
		tags = new ArrayList<String>();
		this.item =item; 
	}
	
	public void add(String s){
		tags.add(s);
		notifyDataSetChanged();		
	}
	
	@Override
	public int getCount() {
		
		return tags.size();
	}

	@Override
	public Object getItem(int position) {
		
		return tags.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
				
		RelativeLayout tagView = (RelativeLayout)LayoutInflater.from(mContext).inflate(R.layout.taglistelement, null);
		
		Button b = (Button)tagView.findViewById(R.id.button1);
		final CheckBox c = (CheckBox)tagView.findViewById(R.id.checkBox1);
		ImageButton i = (ImageButton)tagView.findViewById(R.id.imageButton1);
		
		c.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Code for adding this tag to the item.
				if (isChecked){
					item.addTag((String) c.getText());
				}else{
					item.removeTag((String)c.getText());
				}
			}
			
		});
		
		i.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Code for deleting a tag.
				
			}
		});
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Code for creating going to subtags
				
				
			}
		});
		
		c.setText(tags.get(position));
		
		return tagView;
	}

}
