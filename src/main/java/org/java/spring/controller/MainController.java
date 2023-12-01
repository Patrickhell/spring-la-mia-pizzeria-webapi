package org.java.spring.controller;


import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getPizze(Model model, @RequestParam(required = false) String query) {
		
		List<Pizza> pizze = query == null 
				                          ? pizzaService.findAll()
				                          : pizzaService.findByName(query);		  
		
		model.addAttribute("pizze" , pizze);
		model.addAttribute("query", query == null ? "" : query);
		
		return "pizze";
	}
	
	
	
	@GetMapping("/pizze/{id}")
	public String getPizzaById(Model model, @PathVariable int id) {
		
		Pizza singlePizza = pizzaService.findById(id);
		model.addAttribute("singlePizza", singlePizza);
		
		return "singlePizza";
		
		
	}
	
	
	

}
