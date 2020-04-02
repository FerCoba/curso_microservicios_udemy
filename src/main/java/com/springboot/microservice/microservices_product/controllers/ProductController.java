package com.springboot.microservice.microservices_product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservice.microservices_product.model.entity.Product;
import com.springboot.microservice.microservices_product.response.Response;
import com.springboot.microservice.microservices_product.services.ProductService;

@RestController
@RequestMapping(produces = "application/json")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(value = "/obtainInformationAllProducts")
	public ResponseEntity<Response> obtainAllProducts() {

		List<Product> product = productService.findAllProducts();

		return product.isEmpty()

				? new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK.value()), "Not Data Found", null),
						HttpStatus.BAD_REQUEST)

				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), null, product), HttpStatus.OK);
	}

	@GetMapping(value = "obtainProductInformation/{productId}")
	public ResponseEntity<Response> obtainProductBy(@PathVariable Long productId) {

		Product product = productService.findProductById(productId);

		return product == null

				? new ResponseEntity<>(
						new Response(String.valueOf(HttpStatus.OK.value()),
								"Not Data Found for this id : ".concat(String.valueOf(productId)), null),
						HttpStatus.BAD_REQUEST)

				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), null, product), HttpStatus.OK);
	}

}
