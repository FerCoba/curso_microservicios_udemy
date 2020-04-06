package com.springboot.microservice.microservices_product.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservice.servicec_commons.model.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findAll();
	
	Optional<Product> findById(Long productId);
	
	@SuppressWarnings("unchecked")
	Product save(Product product);
	
	void deleteById(Long productId);

}
