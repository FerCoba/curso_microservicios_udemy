package com.springboot.microservice.microservices_product.exceptions;

import lombok.Data;

@Data
public class ParametersException extends Exception {

	private static final long serialVersionUID = 1L;

	private int code;
	private String message;
}
