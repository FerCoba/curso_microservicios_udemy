package com.springboot.microservice.microservices_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.springboot.microservice.servicec_commons.model.entities"})
public class MicroserviceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProductApplication.class, args);
	}
	
	@Bean
	DispatcherServlet dispatcherServlet () {
	    DispatcherServlet ds = new DispatcherServlet();
	    ds.setThrowExceptionIfNoHandlerFound(true);
	    return ds;
	}

}
