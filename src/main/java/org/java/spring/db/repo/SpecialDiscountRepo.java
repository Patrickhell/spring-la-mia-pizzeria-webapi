package org.java.spring.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.java.spring.db.pojo.SpecialDiscount;

@Repository
public interface SpecialDiscountRepo  extends JpaRepository<SpecialDiscount, Integer> {
	
	List<SpecialDiscount> findByPizzaId(int pizzaId);
}
