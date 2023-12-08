package org.java.spring.db.service;

import java.util.List;

import org.java.spring.db.pojo.SpecialDiscount;
import org.java.spring.db.repo.SpecialDiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialDiscountService {
	

	@Autowired
	private SpecialDiscountRepo specialDiscountRepo;
	
	
	public List<SpecialDiscount> findAll() {
		
		return specialDiscountRepo.findAll();
	}
	
	
    public SpecialDiscount findById(int id) {
		
		return specialDiscountRepo.findById(id).get();
	}
    
    
	public void save(SpecialDiscount specialDiscount) {
		
		specialDiscountRepo.save(specialDiscount);
	}
	
	public List<SpecialDiscount> findDiscountsByPizzaId(int pizzaId) {
	    return specialDiscountRepo.findByPizzaId(pizzaId);
	}
	    
	
	public void delete(SpecialDiscount specialDiscount) {
		
		specialDiscountRepo.delete(specialDiscount);
	}
}
