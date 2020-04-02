package com.springboot.microservice.microservices_product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservice.microservices_product.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

}
