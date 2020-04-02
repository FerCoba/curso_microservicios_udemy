package com.springboot.microservice.microservices_product.exceptions;

public class ProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer exceptionId;
	private String exceptionMessage;

	public ProductException(Integer exceptionId, String exceptionMessage) {
		super();
		this.exceptionId = exceptionId;
		this.exceptionMessage = exceptionMessage;
	}

	public ProductException() {
		super();
	}

	public Integer getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(Integer exceptionId) {
		this.exceptionId = exceptionId;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
