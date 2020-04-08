package com.springboot.microservice.microservices_product.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.microservice.microservices_product.repositories.IProductRepository;
import com.springboot.microservice.servicec_commons.model.entities.Product;

@Service
public class ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	private static final String ENTRY_METHOD_MESSAGE = "obtain info from {} interfaz.";

	@Autowired
	IProductRepository productRepo;
	@Value("${server.port}")
	private Integer port;

	@Transactional(readOnly = true)
	public List<Product> findAllProducts() {
		LOGGER.info(ENTRY_METHOD_MESSAGE, "productRepo.findAll()");
		return productRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Product findProductById(Long productId) {
		LOGGER.info(ENTRY_METHOD_MESSAGE, "productRepo.findById(productId)");
		return productRepo.findById(productId).orElse(null);
	}

	@Transactional
	public Product save(Product product) {
		LOGGER.info(ENTRY_METHOD_MESSAGE, "productRepo.save(product)");
		return productRepo.save(product);
	}

	@Transactional
	public void deleteById(Long productId) {
		LOGGER.info(ENTRY_METHOD_MESSAGE, "productRepo.deleteById(productId)");
		productRepo.deleteById(productId);
	}
}
