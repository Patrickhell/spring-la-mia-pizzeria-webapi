package org.java.spring.restController;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizze")
public class PizzaRestController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex(@RequestParam(required = false) String query) {
		
		List<Pizza> pizze = pizzaService.findAll();
		
		if(query == null) {
			pizze = pizzaService.findAll();
		}else {
			pizze = pizzaService.findByName(query);
		}
		
		return new ResponseEntity<>(pizze, HttpStatus.OK);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizza(@PathVariable int id){
		
		Pizza pizza = pizzaService.findById(id);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
		
		pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pizza> UpdatePizza(@PathVariable int id, @RequestBody Pizza newPizza) {
		
		Pizza pizza = pizzaService.findById(id);
		
		pizza.setName(newPizza.getName());
		pizza.setDescription(newPizza.getDescription());
		pizza.setFoto(newPizza.getFoto());
		pizza.setPrice(newPizza.getPrice());
		
		pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")	
	public ResponseEntity<Pizza> deletePizza(@PathVariable int id){
		
		Pizza pizza = pizzaService.findById(id);
		
		pizzaService.delete(pizza);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
