package com.sdm.manager;

public class Sensor {
	public Integer address;
	public Integer value;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getAdress() {
		return address;
		
	}
	public Sensor(Integer address){
		this.value = 0;
		this.address = address;
	}
}
