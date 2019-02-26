package com.ilakk.develop.connected.cities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.ilakk.develop.connected.cities.service.IConnectedCitiesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

@RestController
@Api(value = "", description = "Method to find if the given two cities - origin and destination are connected")
public class ConnectedCitiesController {

	@Autowired
	private IConnectedCitiesService iConnectedCitiesService;

	@GetMapping("/connected")
	@ApiOperation(value = "Returns 'yes' if origin and destination are connected", notes = "Returns 'no' if origin and destination are not connected")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Returned route details for the given cities", response = String.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "No result returned") })

	public String isConnected(
			@ApiParam(name = "origin", value = "origin", defaultValue = "") @RequestParam("origin") String origin,
			@ApiParam(name = "destination", value = "destination", defaultValue = "") @RequestParam("destination") String destination) {

		if (iConnectedCitiesService.isConnected(origin.toLowerCase(), destination.toLowerCase())) {
			return "yes";
		} else {
			return "no";
		}

	}

}
