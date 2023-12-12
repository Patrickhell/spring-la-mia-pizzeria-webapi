package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.pojo.Pizza;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;



@Controller
public class IngredienController {
	
	@Autowired
	IngredientService ingredientService;
	
	@Autowired
	PizzaService pizzaService;
	
	
	@GetMapping("/IngredientList")
	public String getAllIngredients(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		return "ingredientListPage";
		
	}
	
	@GetMapping("/ingredient/create")
	public String getAddIngredient(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		
		return"/form-ingredient";
	}
	
	@PostMapping("/ingredient/create")
	public String storeAddedIngredient(Model model, @Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult) {
		
        if(bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			model.addAttribute("ingredient", ingredient);
			return "/form-ingredient";
		}
		
        ingredientService.save(ingredient);
		
		return "redirect:/IngredientList";
	}
	
	@PostMapping("/ingredient/delete/{id}")
	public String deleteIngredient( @PathVariable int id, RedirectAttributes redirectAttributes) {
		
		Ingredient singleIngredient = ingredientService.findById(id);
		for(Pizza pizza : singleIngredient.getPizza()) {
			
			pizza.deleteIngredient(singleIngredient);
			pizzaService.save(pizza);
		}
		
		
		ingredientService.delete(singleIngredient);
		
		redirectAttributes.addFlashAttribute("deletedIngredient", singleIngredient);
		
		
		return "redirect:/IngredientList";
		
	}

}
