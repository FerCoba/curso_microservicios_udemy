package com.springboot.microservice.microservices_product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.microservice.microservices_product.model.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
	
	

}
