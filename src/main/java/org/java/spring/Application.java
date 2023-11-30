package org.java.spring;


import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private PizzaService pizzaService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		pizzaService.save(new Pizza("MARINARA", "pomodoro, aglio, olio, origano", "https://www.lapastadij-momo.it/wp-content/uploads/2019/07/Marinara.jpg", 7.66));
		pizzaService.save(new Pizza("MARGHERITA", "pomodoro, mozzarella", "https://images.ctfassets.net/nw5k25xfqsik/64VwvKFqxMWQORE10Tn8pY/200c0538099dc4d1cf62fd07ce59c2af/20220211142754-margherita-9920.jpg", 6.99));
		pizzaService.save(new Pizza("PROSCIUTTO", "pomodoro, mozzarella, prosciutto cotto", "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/48fcca95-c468-4a3d-bd48-d2240ec65a05/Derivates/3871b6f6-b560-40f9-af63-104d91e10db3.jpg", 10.00));
		pizzaService.save(new Pizza("SALAME PICCANTE", "pomodoro, mozzarella, salame piccante", "https://blog.giallozafferano.it/zeroglutine/wp-content/uploads/2019/05/IMG_1577.jpg", 9.99));
		
	}

}
