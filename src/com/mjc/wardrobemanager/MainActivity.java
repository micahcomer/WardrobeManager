package com.mjc.wardrobemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {

	GridView grid;
	WardrobeGridAdapter gridAdapter;
	Button addButton;
	private static final int REQUEST_NEW_WARDROBE_ITEM = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		grid = (GridView) findViewById(R.id.gridView1);
		gridAdapter = new WardrobeGridAdapter(getApplicationContext());		
		grid.setAdapter(gridAdapter);
		addButton = (Button)findViewById(R.id.button1);
		getWardrobe();
		
		
		addButton.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, AddItemActivity.class);
				i.putExtra("ItemNumber", gridAdapter.getCount()+1);
				startActivityForResult(i, REQUEST_NEW_WARDROBE_ITEM);
			}
		});
		
		WardrobeIO wio = new WardrobeIO(getApplicationContext());
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode==RESULT_OK && requestCode == REQUEST_NEW_WARDROBE_ITEM){
			
			String picPath = data.getStringExtra(WardrobeItem.PIC_PATH);
			String itemPath = data.getStringExtra(WardrobeItem.ITEM_PATH);
			
			WardrobeIO wio = new WardrobeIO(getApplicationContext());			
			WardrobeItem item =  wio.loadWardrobeItem(picPath, itemPath);
			gridAdapter.add(item);
			
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void getWardrobe(){
		//TODO: Load all wardrobe items.
	}
	
	
	
	
}
