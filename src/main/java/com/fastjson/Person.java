package com.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
/**
 * @JSONField������set�����ϣ��൱�ڸ��߸���newName��json��Ѱ�Ҹ��ֶ�ֵ�������Ƹ������name�ֶ�
 * @JSONField������get�����ϣ����൱�ڰѶ���Ϊname��ֵת��Ϊjson���ΪnewName
 * @author v_luchuanyou
 *
 */
public class Person {

	private String name;
	
	@JSONField(name="age")
	private String age;
	
	@JSONField(name="desc")
	private String desc;
	
	public String getName() {
		return name;
	}
	@JSONField(name="newName,newName2")
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setNAME(String NAME) {
		this.name = NAME;
	}
	
	public void setAGE(String AGE) {
		this.age = AGE;
	}
	
	public void setDESC(String DESC) {
		this.desc = DESC;
	}
	
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}