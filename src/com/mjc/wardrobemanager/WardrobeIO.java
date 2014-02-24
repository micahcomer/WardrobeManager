package com.mjc.wardrobemanager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public final class WardrobeIO {

	Context mContext;
	private final static String TAG = "WardrobeIO";
	
	public WardrobeIO (Context context){
	
		mContext = context;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<WardrobeTagType> getTags(){
		
		ArrayList<WardrobeTagType> tags= new ArrayList<WardrobeTagType>();
		File dir = mContext.getFilesDir();
		File f = new File(dir, "tags.dat");
		try {
			
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			tags = (ArrayList<WardrobeTagType>) ois.readObject();
			ois.close();
			fis.close();			
			
		} catch (FileNotFoundException e) {			
			Log.i(TAG, "tags.dat - File not found.");
			e.printStackTrace();
			
		} catch (StreamCorruptedException e) {
			Log.i(TAG, "Stream corrupted.");
			e.printStackTrace();
		} catch (IOException e) {
			Log.i(TAG, "IOException.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Log.i(TAG, "Class \"ArrayList<WardrobeTags>\" not found.");
			e.printStackTrace();
		}
		
		return tags;
		
	}
	
	public String saveBitmap(Bitmap b, int number){
		
		File dir = mContext.getFilesDir();
		File f = new File(dir, "pic_"+String.valueOf(number)+".dat");
			
		try {
			FileOutputStream fos = new FileOutputStream(f);
			b.compress(Bitmap.CompressFormat.PNG, 85, fos);
		    fos.flush();
		    fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f.getAbsolutePath();
	}
	
	public Bitmap loadBitmap(String path){
		
		Bitmap bitmap = BitmapFactory.decodeFile(path);
		return bitmap;
	}
	
	public String saveWardrobeItem(WardrobeItem item, int number){
		File dir = mContext.getFilesDir();
		File f = new File(dir, "item_" + String.valueOf(number)+".dat");
		
		try {
			
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(item);
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f.getAbsolutePath();
	}
	
	
	public WardrobeItem loadWardrobeItem(String picPath, String itemPath){
		WardrobeItem wi = null;
		Drawable d = null;
		
		File itemFile = new File(itemPath);		
		try{
			d = new BitmapDrawable(mContext.getResources(), loadBitmap(picPath));
			FileInputStream fis = new FileInputStream(itemFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			wi = (WardrobeItem)ois.readObject();
			ois.close();
			fis.close();
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wi.setPic(d);
		return wi;
	}
	
	
	public void saveTags(ArrayList<WardrobeTagType> tags){
		
		File dir = mContext.getFilesDir();
		File f = new File(dir, "tags.dat");
		
		try {
			
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tags);
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
		public void createDefaultTags(boolean overwrite){
		File dir = mContext.getFilesDir();
		File f = new File(dir, "tags.dat");
		
		//If the file doesn't exist, create default tags.  Or, if we want to overwrite the existing tag file, create default tags.
		if (!f.exists() || overwrite){
						
			ArrayList<WardrobeTag> tags = WardrobeItem.getDefaultTags();
		
		try {
			
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tags);
			oos.close();
			fos.close();
			
			for (long  i =0; i<10; i++){
			
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}	
	}
	
}
