package com.fastjson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class TestDemo {

	public static void main(String[] args) {
//		List list = new ArrayList();
//		list.add(8);
//		list.add(2);
//		list.add(5);
//		System.out.println(list);
//		Collections.sort(list );
//		System.out.println(list);
		
		String key = "objectName,car,car.bus";
		System.out.println(key.split("\\.").length);
		System.out.println(Arrays.toString(key.split(",")));
		System.out.println(key.split("\\.").length-1);
		String strsss = key.split("\\.")[key.split("\\.").length-1];
		System.out.println(strsss);
		System.out.println(key.substring(0,key.length() - strsss.length()));
		
		JSONObject json = new JSONObject();
		json.put("name", "jack");
		
		JSONObject a = new JSONObject();
		a.put("name", "jack");
		json.put("a", a);
		
		JSONObject o = json.getJSONObject("a");
		o.put("age", 123);
		
		System.out.println("json:"+json);
		System.out.println("o:"+o);
	}
}
