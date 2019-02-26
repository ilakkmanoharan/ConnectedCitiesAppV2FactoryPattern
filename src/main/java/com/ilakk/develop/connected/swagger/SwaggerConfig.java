package com.ilakk.develop.connected.cities.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket connectedCitiesApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ilakk.develop.connected.cities.controller"))
                .paths(PathSelectors.any()) 
                .build();
                
    } 
	 
	  private ApiInfo apiInfo() {
		    return new ApiInfoBuilder()
		    .title("Connected Cities Rest APIs")
		    .description("This page gives information about the Connected Cities Rest API.")
		    .version("1.0-SNAPSHOT")
		    .build();
	}
       
}
