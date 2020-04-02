package com.springboot.microservice.microservices_product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.microservice.microservices_product.model.entity.Product;
import com.springboot.microservice.microservices_product.repositories.IProductRepository;

@Service
public class ProductService {

	@Autowired
	IProductRepository productRepo;

	@Transactional(readOnly = true)
	public List<Product> findAllProducts() {
		return productRepo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Product findProductById(Long productId) {
		return productRepo.findById(productId).orElse(null);
	}
}
