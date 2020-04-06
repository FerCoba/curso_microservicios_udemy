package com.springboot.microservice.microservices_product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservice.microservices_product.exceptions.Exception500Status;
import com.springboot.microservice.microservices_product.exceptions.ParametersException;
import com.springboot.microservice.microservices_product.response.Response;
import com.springboot.microservice.microservices_product.services.ProductService;
import com.springboot.microservice.servicec_commons.model.entities.Product;

@RestController
@RequestMapping(produces = "application/json")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(value = "/obtainInformationAllProducts")
	public ResponseEntity<Response> obtainAllProducts() {

		List<Product> products = productService.findAllProducts();

		return products.isEmpty()

				? new ResponseEntity<>(
						new Response(String.valueOf(HttpStatus.OK.value()), "Not Data Found", null, null),
						HttpStatus.BAD_REQUEST)

				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), null, null, products),
						HttpStatus.OK);
	}

	@GetMapping(value = "obtainProductInformation/{productId}")
	public ResponseEntity<Response> obtainProductBy(@PathVariable Long productId) {

		Product product = productService.findProductById(productId);

		return product == null

				? new ResponseEntity<>(
						new Response(String.valueOf(HttpStatus.OK.value()),
								"Not Data Found for this id : ".concat(String.valueOf(productId)), null, null),
						HttpStatus.BAD_REQUEST)

				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK.value()), null, product, null),
						HttpStatus.OK);
	}

	@PostMapping(value = "/createNewProduct")
	public ResponseEntity<Response> createNewProduct(@RequestBody Product productParams)
			throws Exception500Status, ParametersException {
		Product product = new Product();
		try {
			product = productService.save(productParams);
		} catch (Exception exception) {
			throw exception;
		}

		return product.getId() == 0l
				? new ResponseEntity<>(new Response(String.valueOf(HttpStatus.BAD_REQUEST.value()),
						"does't insert the product, review the parameters.", null, null), HttpStatus.BAD_REQUEST)

				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.CREATED.value()), null, product, null),
						HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/deleteProduct/{productId}")
	public ResponseEntity<Response> deleteProduct(@PathVariable("productId") Long productId) {

		try {
			productService.deleteById(productId);
		} catch (Exception e) {
			throw new EmptyResultDataAccessException(0);
		}

		return new ResponseEntity<>(
				new Response(String.valueOf(HttpStatus.NO_CONTENT.value()), "product deleted successfully.", null, null),
				HttpStatus.NO_CONTENT);
	}

}
