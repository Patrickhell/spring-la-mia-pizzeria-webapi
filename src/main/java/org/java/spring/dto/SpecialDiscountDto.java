package org.java.spring.dto;

import java.time.LocalDate;

public class SpecialDiscountDto {

	private int pizza_id;
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	
	

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
	
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getPizza_id() + " - " + getTitle()+ " - " + getStartDate()+ " - " + getEndDate();
	}
}
