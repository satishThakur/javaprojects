package com.satish.xtream;

import java.util.HashMap;
import java.util.Map;

public class TestObject {
	
	public static Object getTestObject() {
		
		Map<String,Object> testObj = new HashMap<String,Object>();
		
		testObj.put("key1","value1");
		testObj.put("key2","value2");
		testObj.put("key3","value3");
		testObj.put("key4","value4");
		return testObj;
	}

}
