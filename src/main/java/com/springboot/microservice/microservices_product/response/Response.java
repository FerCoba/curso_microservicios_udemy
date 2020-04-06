package com.springboot.microservice.microservices_product.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.microservice.servicec_commons.model.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	private String status;
	private String message;
	private Product product;
	private List<Product> products;
}
