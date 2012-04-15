package org.jay.CarLog.model;

public class Vehicle {

	private String model;
	private String year;
	private int id;
	
	public Vehicle(){};
	
	
	public Vehicle(int id, String model, String year)
	{
		this.id = id;
		this.model = model;
		this.year = year;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
