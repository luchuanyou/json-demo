package com.demo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class PTest {

	public static void main(String[] args) {
		String strJson = "{'name':'tom',objectName:{'car0':{'type':'car0'},'car1':{'type':'car1'},'car2':{'type':'car2'},'car3':{'type':'car3'}}}";
		JSONObject jsonObject = JSONObject.parseObject(strJson);
		
		//将json转换为Person对象
		Person person = JSONObject.toJavaObject(jsonObject, Person.class);
		/** 方法一： */
		//遍历car对象
		List<Car> carList = new ArrayList<Car>();
		
		JSONObject car0Json = jsonObject.getJSONObject("objectName").getJSONObject("car0");
		if(null != car0Json){
			Car car0 = JSONObject.toJavaObject(car0Json, Car.class);
			carList.add(car0);
		}
		
		JSONObject car1Json = jsonObject.getJSONObject("objectName").getJSONObject("car1");
		if(null != car1Json){
			carList.add(JSONObject.toJavaObject(car1Json, Car.class));
		}
		
		JSONObject car2Json = jsonObject.getJSONObject("objectName").getJSONObject("car2");
		if(null != car2Json){
			carList.add(JSONObject.toJavaObject(car1Json, Car.class));
		}
		
		//设置Person属性carList
		person.setCarList(carList);
		
		System.out.println("====end===="+person);
		
		/**
		 * 方法二：
		 * json对象有一定规律可以使用此方法
		 */
		JSONObject objectNameJson = jsonObject.getJSONObject("objectName");
		int size = objectNameJson.size();
		List<Car> carList2 = new ArrayList<Car>();
		for (int i = 0; i < size; i++) {
			JSONObject carJson = jsonObject.getJSONObject("objectName").getJSONObject("car"+i);
			if(null != carJson){
				Car car = JSONObject.toJavaObject(carJson, Car.class);
				carList2.add(car);
			}
		}
		person.setCarList(carList2);
		System.out.println("===end==="+person);
	}
}
