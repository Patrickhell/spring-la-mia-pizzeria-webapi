package org.java.spring.db.pojo;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;

@Entity
public class SpecialDiscount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@FutureOrPresent(message="La data deve essere presente o futura")
	private LocalDate startDate;
	
	@Column(nullable = false)
	private LocalDate endDate;
	
	@Column(nullable = false)
	@Length(min = 5 , max = 30, message = "Il nome deve avere un numero di caratteri compreso tra 5 e 30")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "pizza_id")
	@JsonIgnore
	private Pizza pizza;
	
	public SpecialDiscount() { }
	public SpecialDiscount( LocalDate startDate, LocalDate endDate,String title, Pizza pizza) {
		
		setStartDate(startDate);
		setEndDate(endDate);
		setTitle(title);
		setPizza(pizza);
		
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate  getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
    public String getFormatStartDate() {
		
		return getStartDate() == null
				? null
				: getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
	}
    
    
	public void setFormatStartDate(String startDate) {
		
		setStartDate(LocalDate.parse(startDate));
	}
	
	
	public LocalDate  getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
    public String getFormatEndDate() {
		
		return getEndDate() == null
				? null
				: getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
	}
    
	public void setFormatEndDate(String endDate) {
		
		setEndDate(LocalDate.parse(endDate));
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Pizza getPizza() {
		return pizza;
	}
	
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + "] " + getStartDate() + getEndDate() + getTitle();
	}
	

}
