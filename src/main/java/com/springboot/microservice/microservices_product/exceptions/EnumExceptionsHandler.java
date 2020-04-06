package com.springboot.microservice.microservices_product.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.microservice.microservices_product.response.ResponseError;

public enum EnumExceptionsHandler {

	THROW_EXCEPTION("Exception500Status", "BadRequest", "ZuulException", "NotFound", "HystrixRuntimeException",
			"EmptyResultDataAccessException", "NoHandlerFoundException") {

		private Object conversion(Object obj) {

			ObjectMapper jsonMapper = new ObjectMapper();
			String resultado = null;
			try {
				resultado = jsonMapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.getMessage();
			}

			return resultado;
		}

		public ResponseEntity<Object> exceptionBuilder(Exception ex, WebRequest wr, String tipoException) {
			ResponseError resp = null;
			if (THROW_EXCEPTION.getValues().contains((tipoException))) {

				switch (tipoException) {
				case "NoHandlerFoundException":
					resp = new ResponseError(HttpStatus.NOT_FOUND, "the resource not exist.");
					return new ResponseEntity<>(conversion(resp), HttpStatus.NOT_FOUND);
				case "EmptyResultDataAccessException":
					resp = new ResponseError(HttpStatus.OK, "Not data found.");
					return new ResponseEntity<>(conversion(resp), HttpStatus.OK);
				case "BadRequest":
					resp = new ResponseError(HttpStatus.OK, "Not data found.");
					return new ResponseEntity<>(conversion(resp), HttpStatus.OK);
				case "Exception500Status":
					resp = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR,
							"in this moment we can't respond a your request, please try again.");
					return new ResponseEntity<>(conversion(resp), HttpStatus.INTERNAL_SERVER_ERROR);
				default:
					resp = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR,
							"in this moment we can't respond a your request, please try again.");
					return new ResponseEntity<>(conversion(resp), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			return new ResponseEntity<>(conversion(resp), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	};

	private final List<String> values;

	EnumExceptionsHandler(String... values) {
		this.values = Arrays.asList(values);
	}

	public List<String> getValues() {
		return values;
	}

	public abstract ResponseEntity<Object> exceptionBuilder(Exception ex, WebRequest wr, String tipoException);

}
