package org.java.spring.dto;


public class SpecialDiscountDto {

	private int pizza_id;
	private String title;
	private String startDate;
	private String endDate;
	
	
	
	public int getPizza_id() {
		return pizza_id;
	}
	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getPizza_id() + " - " + getTitle()+ " - " + getStartDate()+ " - " + getEndDate();
	}
}
