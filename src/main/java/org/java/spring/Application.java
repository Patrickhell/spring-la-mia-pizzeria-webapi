package org.java.spring;


import java.time.LocalDate;
import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.pojo.SpecialDiscount;
import org.java.spring.db.service.PizzaService;
import org.java.spring.db.service.SpecialDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialDiscountService specialDiscountService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		pizzaService.save(new Pizza("MARINARA", "pomodoro, aglio, olio, origano", "https://www.lapastadij-momo.it/wp-content/uploads/2019/07/Marinara.jpg", 7.99f));
		pizzaService.save(new Pizza("MARGHERITA", "pomodoro, mozzarella", "https://images.ctfassets.net/nw5k25xfqsik/64VwvKFqxMWQORE10Tn8pY/200c0538099dc4d1cf62fd07ce59c2af/20220211142754-margherita-9920.jpg", 6.99f));
		pizzaService.save(new Pizza("PROSCIUTTO", "pomodoro, mozzarella, prosciutto cotto", "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/48fcca95-c468-4a3d-bd48-d2240ec65a05/Derivates/3871b6f6-b560-40f9-af63-104d91e10db3.jpg", 10f));
		pizzaService.save(new Pizza("SALAME PICCANTE", "pomodoro, mozzarella, salame piccante", "https://blog.giallozafferano.it/zeroglutine/wp-content/uploads/2019/05/IMG_1577.jpg", 9.99f));
		
        List<Pizza>pizze = pizzaService.findAll();
        
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2023-12-15"), "Flash Discount Blitz", pizze.get(0)));
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2023-12-25"), "Discount Dash" , pizze.get(0)));
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2023-12-31"), "Prime Time Offers" , pizze.get(1)));
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2024-01-10"), "DealSphere" , pizze.get(1)));
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2024-01-20"), "Summer Promotion" , pizze.get(2)));
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2024-03-20"), "Student Discount" , pizze.get(3)));
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2024-04-20"), "Happy Hour Deal" , pizze.get(3)));
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2024-06-20"), "Holiday Discount" , pizze.get(2)));
		specialDiscountService.save(new SpecialDiscount(LocalDate.now(),LocalDate.parse("2024-07-15"), "Family Feast Discount" , pizze.get(3)));




	}

}
