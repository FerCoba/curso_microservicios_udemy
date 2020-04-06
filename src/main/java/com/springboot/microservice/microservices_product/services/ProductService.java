package com.springboot.microservice.microservices_product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.microservice.microservices_product.repositories.IProductRepository;
import com.springboot.microservice.servicec_commons.model.entities.Product;

@Service
public class ProductService {

	@Autowired
	IProductRepository productRepo;
	@Value("${server.port}")
	private Integer port;

	@Transactional(readOnly = true)
	public List<Product> findAllProducts() {
		return productRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Product findProductById(Long productId) {
		return productRepo.findById(productId).orElse(null);
	}

	@Transactional
	public Product save(Product product) {
		return productRepo.save(product);
	}

	@Transactional
	public void deleteById(Long productId) {
		productRepo.deleteById(productId);
	}
}
