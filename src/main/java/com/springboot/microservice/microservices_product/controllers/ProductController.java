package com.springboot.microservice.microservices_product.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	private static final String ENTRY_METHOD_MESSAGE = "entry in method {}.";
	private static final String EXCEPTION_MESSAGE = "in the method {} occurred the next exception : ";
	private static final String EXIT_METHOD = "leaving from method {}";

	@Autowired
	ProductService productService;

	@GetMapping(value = "/obtainInformationAllProducts")
	public ResponseEntity<Response> obtainAllProducts() throws Exception500Status {

		LOGGER.info(ENTRY_METHOD_MESSAGE, "obtainAllProducts");
		List<Product> products = new ArrayList<>();

		try {
			products = productService.findAllProducts();
		} catch (Exception e) {
			LOGGER.error(EXCEPTION_MESSAGE, e.getMessage());
			throw new Exception500Status();
		}

		LOGGER.info(EXIT_METHOD, "obtainAllProducts");
		return products.isEmpty()

				? new ResponseEntity<>(
						new Response(String.valueOf(HttpStatus.OK.value()), "Not Data Found", null, null),
						HttpStatus.BAD_REQUEST)

				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK), null, null, products),
						HttpStatus.OK);
	}

	@GetMapping(value = "obtainProductInformation/{productId}")
	public ResponseEntity<Response> obtainProductBy(@PathVariable Long productId) throws ParametersException {

		LOGGER.info(ENTRY_METHOD_MESSAGE, "obtainProductBy");
		Product product = new Product();
		try {
			product = productService.findProductById(productId);
		} catch (Exception e) {
			LOGGER.error(EXCEPTION_MESSAGE, e.getMessage());
			throw new ParametersException();
		}

		LOGGER.info(EXIT_METHOD, "obtainProductBy");

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
			throws ParametersException, Exception500Status {

		LOGGER.info(ENTRY_METHOD_MESSAGE, "createNewProduct");

		Product product = productService.save(productParams);
		
		LOGGER.info(ENTRY_METHOD_MESSAGE, "createNewProduct");
		return product.getId() == 0l
				? new ResponseEntity<>(new Response(String.valueOf(HttpStatus.BAD_REQUEST.value()),
						"does't insert the product, review the parameters.", null, null), HttpStatus.BAD_REQUEST)

				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.CREATED.value()), null, product, null),
						HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/deleteProduct/{productId}")
	public ResponseEntity<Response> deleteProduct(@PathVariable("productId") Long productId) {
		LOGGER.info(ENTRY_METHOD_MESSAGE, "deleteProduct");
		try {
			productService.deleteById(productId);
		} catch (Exception e) {
			LOGGER.error(EXCEPTION_MESSAGE, e.getMessage());
			throw new EmptyResultDataAccessException(0);
		}
		LOGGER.info(ENTRY_METHOD_MESSAGE, "deleteProduct");
		return new ResponseEntity<>(new Response(String.valueOf(HttpStatus.NO_CONTENT.value()),
				"product deleted successfully.", null, null), HttpStatus.NO_CONTENT);
	}

}
