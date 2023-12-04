package org.java.spring.db.pojo;


import org.hibernate.validator.constraints.Length;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@Length (min = 5, max = 60, message = "Il nome aver un numero di caratteri compreso tra 5 e 60")
	@NotEmpty(message = "Il campo nome non può essere vuoto")
	private String name;
	
	@Column(columnDefinition = "TEXT")
	@NotEmpty(message = "Il campo descrizione non può essere vuoto")
	@Length (min = 10 , max = 120, message = "Il nome aver un numero di caratteri compreso tra 10 e 120")
	private String description;
	
	@Column(length = 255)
	@NotEmpty(message = "Il image Url nome non può essere vuoto")
	private String foto;
	
	@Column(length = 60)
	@NotNull(message = "Il campo prezzo non può essere vuoto")
	@DecimalMin(value = "0.0" , inclusive = false, message = "Il prezzo deve essere maggiore di 0 ")
	@DecimalMax(value = "15.0" , inclusive = true, message = "Il prezzo non può essere maggiore di 15 euro ")
	private double price;
	
	public Pizza() { }
	public Pizza(String name,String description, String foto, double price) {
		
		setName(name);
		setDescription(description);
		setFoto(foto);
		setPrice(price);
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  getName() + getDescription() + getFoto() + getPrice();
	}

}
