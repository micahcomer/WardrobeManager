package com.mjc.wardrobemanager;

import java.util.ArrayList;

public class WardrobeTag {

	public String tag;
	public ArrayList<WardrobeTag> childTags;
	
	public WardrobeTag(String t){
		tag = t;
		childTags = new ArrayList<WardrobeTag>();
	}
}
