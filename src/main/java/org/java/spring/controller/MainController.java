package org.java.spring.controller;


import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.pojo.SpecialDiscount;
import org.java.spring.db.service.IngredientService;
import org.java.spring.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.java.spring.db.service.SpecialDiscountService;
import org.java.spring.dto.SpecialDiscountDto;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialDiscountService specialDiscountService;
	
	@Autowired
	private IngredientService ingredientService;
	
	
	
	
	
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
		
		List<SpecialDiscount> discount = specialDiscountService.findDiscountsByPizzaId(id);
		model.addAttribute("discount", discount);
		
		Pizza singlePizza = pizzaService.findById(id);
		singlePizza.getIngredients();
		model.addAttribute("singlePizza", singlePizza);
		
		
		return "singlePizza";
		
		
	}
	
	@GetMapping("/pizze/create")
	public String create(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredients", ingredients);
		
		return"/pizza-form";
	}
	
	@PostMapping("/pizze/create")
	public String pizzaStore(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "/pizza-form";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizze/edit/{id}")
	public String editPizza(Model model, @PathVariable int id) {
		
		List<Ingredient> ingredients = ingredientService.findAll();		
		Pizza singlePizza = pizzaService.findById(id);
		
		model.addAttribute("pizza", singlePizza);
		model.addAttribute("ingredients", ingredients);
		
		
		
		return "/pizza-edit";
	}
	
	@PostMapping("pizze/edit/{id}")
	public String updatePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
        if(bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			
			return "/pizza-edit";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
		
	}
	
	@PostMapping("/pizze/delete/{id}")
	public String deletePizza( @PathVariable int id, RedirectAttributes redirectAttributes) {
		
		Pizza singlePizza = pizzaService.findById(id);
		
		singlePizza.ClearIngredients();
		pizzaService.save(singlePizza);
		
		List<SpecialDiscount> specialDiscount  = singlePizza.getSpecialDiscount();
		specialDiscount.forEach(specialDiscountService::delete);
		
		pizzaService.delete(singlePizza);
		
		
		redirectAttributes.addFlashAttribute("deletedPizza", singlePizza);
		
		
		return "redirect:/";
		
	}
	
	@GetMapping("pizze/discount/{pizza_id}")
	public String getDiscountPizzaCreateForm(Model model, @PathVariable("pizza_id") int id) {
		
	    
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		model.addAttribute("specialDiscount", new SpecialDiscount());
	    
		return "discount-form";
		
	}
	
	@PostMapping("pizze/discount/{pizza_id}")
	public String storeSpecialDiscount(Model model, @Valid @ModelAttribute SpecialDiscount specialDiscount, 
			                           BindingResult bindingResult,@PathVariable("pizza_id") int id, RedirectAttributes redirectAttributes) 
	{
		
		
        if (bindingResult.hasErrors()) {
			
			return "discount-form"; 
		}

        Pizza pizza = pizzaService.findById(id);
        
        specialDiscount.setPizza(pizza);
        
        redirectAttributes.addFlashAttribute("createdSpecialDiscount", specialDiscount);
        
		specialDiscountService.save(specialDiscount);
		
		return "redirect:/pizze/" + id;
	}
	
	
	
	
	

}
