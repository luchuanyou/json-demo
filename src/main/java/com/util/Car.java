package com.util;

public class Car {

	private String type;
	private Bus bus;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public String toString() {
		return "Car [type=" + type + ", bus=" + bus + "]";
	}
	

}
