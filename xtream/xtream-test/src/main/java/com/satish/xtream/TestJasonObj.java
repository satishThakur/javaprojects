package com.satish.xtream;

import java.util.HashMap;
import java.util.Map;

public class TestJasonObj {
	
	String name = "hello";
	
	Map<String,String> map = new HashMap<String,String>();
	
	public TestJasonObj() {
		
		map.put("key1", "vaue1");
	}
	
	
	@Override
	public String toString() {
		return name + " Map: " + map;
	}

}
