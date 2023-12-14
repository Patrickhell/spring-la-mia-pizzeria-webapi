package org.java.spring.db.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	
	public List<Pizza> findAll(){
		List<Pizza> pizze = pizzaRepository.findAll(); 
		
		if (pizze.isEmpty()) {
	        System.out.println("Nessuna pizza presente.");
	        
	        return new ArrayList<>(); 
	    }
		
		return pizze;
	}
	
	public List<Pizza> findByName(String query){
		
		return pizzaRepository.findByNameContainingIgnoreCase(query);
		
	}
	
	public List<Pizza> findByNameLike(String query) {
		
		return pizzaRepository.findByNameLikeIgnoreCase(query);
	}
	
    public Pizza findById(int id) {
		
		return pizzaRepository.findById(id).get();
	}
    
    public Pizza save(Pizza pizza) {
		
    	return pizzaRepository.save(pizza);
	}
    
    
    public void delete(Pizza pizza) {
		
		pizzaRepository.delete(pizza);
	}
    
	public void deleteById(int id) {
		
		pizzaRepository.deleteById(id);
	}
	
	
}
