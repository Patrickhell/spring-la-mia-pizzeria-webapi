package org.java.spring.db.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@Length(min = 4, max = 10)
	private String name;
	
	@ManyToMany(mappedBy = "ingredients")
	@JsonIgnore
	private List<Pizza> pizza;
	
	
	public Ingredient() { }
	public Ingredient(String name) {
		
		setName(name);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Pizza> getPizza() {
		return pizza;
	}
	public void setPizza(List<Pizza> pizza) {
		this.pizza = pizza;
	}
	
	public void ClearPizza() {
		getPizza().clear();
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + "]" + getName();
	}

}
