package com.mjc.wardrobemanager;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddItemActivity extends Activity{

	WardrobeItem item;
	Drawable image;
	ArrayList<WardrobeTag> tags;
	ImageView imageView;
	TextView prePicText;
	AutoCompleteTextView itemDescription;
	ListView tagList;
	TagAdapter tagAdapter;
	String imagePath;
	String itemPath;
	int itemNumber;
	
	
	private static final int REQUEST_WARDROBE_ITEM_PIC = 0;	
	public static final int MEDIA_TYPE_IMAGE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additemscreen);
		
		item = new WardrobeItem();
		itemNumber = getIntent().getIntExtra("ItemNumber", 1);
		imageView = (ImageView)findViewById(R.id.imageView);
		prePicText = (TextView)findViewById(R.id.textView);		
		
		imageView.setVisibility(View.INVISIBLE);		
		
		prePicText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);				
				startActivityForResult(i, REQUEST_WARDROBE_ITEM_PIC);
			}
		});
		
		itemDescription = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
		itemDescription.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AutoCompleteTextView text = (AutoCompleteTextView)v;
				if (text.getText().toString().compareTo(getResources().getString(R.string.clicktoadddesc))==0){
					text.setText("");
				}
				
			}
		});
		
		tagList = (ListView)findViewById(R.id.listView);
		tagAdapter = new TagAdapter(getApplicationContext(), item);
		RelativeLayout footer = (RelativeLayout)LayoutInflater.from(getApplicationContext()).inflate(R.layout.footer, null);		
		tagList.addHeaderView(footer);
		tagList.setAdapter(tagAdapter);
		addTags();
		
		
		Button doneButton = (Button)findViewById(R.id.button);
		doneButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				saveItem();
				Intent i = new Intent();
				WardrobeItem.packageIntent(i, imagePath, itemPath);
				setResult(RESULT_OK, i);
				finish();
				
			}
		});
	}
	
	private void saveItem(){
		WardrobeIO wio = new WardrobeIO(getApplicationContext());
		itemPath = wio.saveWardrobeItem(item, itemNumber);
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		
	};
	
	private void addTags(){
		
		
		tags = WardrobeItem.getDefaultTags();
		
		for (int i=0; i<tags.size(); i++){		
			tagAdapter.add(tags.get(i).tag);		
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		
		
		if (requestCode == REQUEST_WARDROBE_ITEM_PIC && resultCode == RESULT_OK) {
	     
				Bundle extras = data.getExtras();
		        Bitmap imageBitmap = (Bitmap) extras.get("data");
		        imageView.setImageBitmap(imageBitmap);		        
		        prePicText.setVisibility(View.INVISIBLE);
		        imageView.setVisibility(View.VISIBLE);
		        
		        WardrobeIO wio = new WardrobeIO(getApplicationContext());		        
		        imagePath = wio.saveBitmap(imageBitmap, itemNumber);
		        
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}