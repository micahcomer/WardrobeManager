package com.mjc.wardrobemanager;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class WardrobeItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7995363333743177309L;
	private transient Drawable pic; 
	private ArrayList<WardrobeTag>tags;
	private String desc;
	
	public static String PIC_PATH = "picpath";
	public static String ITEM_PATH = "itempath";
	static String TAGS = "tags";
	
	public WardrobeItem(){
		tags = new ArrayList<WardrobeTag>();
		desc = "Item";
	}
	
	public void setDesc(String d){
		desc = d;
	}
	
	public void setPic (Drawable p){
		this.pic = p;
	}
	
	public Drawable getPic(){
		return pic;
	}
	
	public String getDescription(){
		if (desc==""){
			return tags.get(0).tag;
		}else{
			return desc;
		}
	}
	public void setTags(ArrayList<WardrobeTag>t){
		tags = t;
		
	}
	
	public void addTag(String tag){
		WardrobeTag t = new WardrobeTag(tag);		
		tags.add(t);
	}
	
	public void removeTag(String tag){
		int j=-1;
		for (int i=0; i<tags.size(); i++){
			if (tags.get(i).tag==tag){
				j=i;
			}
		}
		if (j>-1){
			tags.remove(j);
		}
	}
	
	public ArrayList<WardrobeTag> getTags(){
		return tags;
	}
	
	public static ArrayList<WardrobeTag> getDefaultTags(){
		
		ArrayList<WardrobeTag> tags = new ArrayList<WardrobeTag>();
		
		tags.add(new WardrobeTag("Accessories"));
		tags.add(new WardrobeTag("Belt"));
		tags.add(new WardrobeTag("Bottom"));
		tags.add(new WardrobeTag("Business"));
		tags.add(new WardrobeTag("Casual"));
		tags.add(new WardrobeTag("Coat"));
		tags.add(new WardrobeTag("Dress"));
		tags.add(new WardrobeTag("Formal"));
		
		tags.add(new WardrobeTag("Jacket"));
		tags.add(new WardrobeTag("Jewelry"));
		tags.add(new WardrobeTag("Lingerie"));
		tags.add(new WardrobeTag("Pants"));
		tags.add(new WardrobeTag("Shirt"));
		tags.add(new WardrobeTag("Skirt"));
		tags.add(new WardrobeTag("Socks"));
		tags.add(new WardrobeTag("Top"));
		tags.add(new WardrobeTag("Work"));
				
		return tags;
		
	}
	
		
	public static void packageIntent(Intent i, String picpath, String itempath){
		
		i.putExtra(WardrobeItem.PIC_PATH, picpath);
		i.putExtra(WardrobeItem.ITEM_PATH, itempath);
		
	}
	//TODO... Below...
	//public static WardrobeItem unpackIntent(Intent i){
	
		//WardrobeItem item = new WardrobeItem();
		
		
		
	//}
	
}
