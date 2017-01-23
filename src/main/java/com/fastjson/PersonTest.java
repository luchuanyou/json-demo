
package com.fastjson;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PersonTest {

	private Person person;
	
	/**
	 * ��ʼ������
	 */
	@Before
	public void setUp() {
		person = new Person();
		person.setName("xianglj");
		person.setAge("20");
		person.setDesc("ֻ��һ������");
	}
	
	@Test
	public void test() {
		String jsonStr = JSONObject.toJSONString(person);
		System.out.println("bean to json:" + jsonStr);
		
		//�ı�json��keyΪ��д
		jsonStr = jsonStr.toUpperCase();
		
		System.out.println("��Ҫת����json:" + jsonStr);
		person = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Person.class);
		System.out.println("json to bean:" + person.getName());
		System.out.println(person);
		
		
		JSONObject json = new JSONObject();
		//json.put("newName", "tom");
		json.put("age", 11);
		json.put("newName", "jack");
		person = JSONObject.toJavaObject(json, Person.class);
		System.out.println("json to bean:" + person.getName());
		System.out.println("==========="+person);
		
		
		JSONArray ary = new JSONArray();
		ary.add(json);
		
		JSONObject json2 = new JSONObject();
		json2.put("newName2", "jack");
		json2.put("age", 10);
		ary.add(json2);
		System.out.println(ary);
		List<Person> list = JSONArray.toJavaObject(ary, List.class);
		System.out.println(list);
	}
}