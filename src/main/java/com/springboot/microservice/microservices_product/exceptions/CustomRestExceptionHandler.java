package com.springboot.microservice.microservices_product.exceptions;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.microservice.microservices_product.response.ResponseError;


@EnableWebMvc
@ControllerAdvice
@RestController
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomRestExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ResponseError resp = null;
		resp = new ResponseError(HttpStatus.NOT_FOUND, "the resource not exist.");
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler({ Exception.class,NullPointerException.class,
			NoSuchElementException.class, HttpClientErrorException.class, EmptyResultDataAccessException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	protected ResponseEntity<Object> handleAll(final Exception ex, WebRequest request) {
		LOGGER.info("Ingresa en el metodo handleAll de la clase ResponseEntityExceptionHandler.");
		return EnumExceptionsHandler.THROW_EXCEPTION.exceptionBuilder(ex, request, ex.getClass().getSimpleName());
	}

}
