package org.java.spring.db.pojo;


import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
	@DecimalMin(value = "0.00" , inclusive = false, message = "Il prezzo deve essere maggiore di 0 ")
	@DecimalMax(value = "15.00" , inclusive = true, message = "Il prezzo non può essere maggiore di 15 euro ")
	private float  price;
	
	@OneToMany(mappedBy = "pizza")
	private List<SpecialDiscount>specialDiscount;
	
	@ManyToMany()
	@JoinTable(
			name="pizza_ingredients",
	        joinColumns = @JoinColumn(name = "pizza_id"),
	        inverseJoinColumns = @JoinColumn(name = "ingrdient_id")
			)
			
	
	private List<Ingredient>ingredients;

	
	public Pizza() { }
	public Pizza(String name,String description, String foto, float price, Ingredient...ingredients) {
		
		setName(name);
		setDescription(description);
		setFoto(foto);
		setPrice(price);
		setIngredients(ingredients);
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public List<SpecialDiscount> getSpecialDiscount() {
		return specialDiscount;
	}
	public void setSpecialDiscount(List<SpecialDiscount> specialDiscount) {
		this.specialDiscount = specialDiscount;
	}
	public void deleteIngredient(Ingredient ingredient) {
		getIngredients().remove(ingredient);
	}
	
	
	
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setIngredients(Ingredient... ingredients) {
		
		setIngredients(Arrays.asList(ingredients));
	}
	
	public void ClearIngredients() {
		getIngredients().clear();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  getName() + getDescription() + getFoto() + String.format("%.02f", getPrice()) + "euro";
	}

}
