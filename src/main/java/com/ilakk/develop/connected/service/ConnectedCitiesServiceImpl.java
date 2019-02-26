package com.ilakk.develop.connected.cities.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.ilakk.develop.connected.cities.city.*;
import com.ilakk.develop.connected.cities.factory.*;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

@Component
public class ConnectedCitiesServiceImpl implements IConnectedCitiesService{

	Set<CityPair> cityPairs = new HashSet<CityPair>();
	Map<City, Integer> cityIdMap = new HashMap<City, Integer>();
	Set<City> distinctEntries = new HashSet<City>();

	static int counter = 0;
	
	RouteFinderFactory routeFactory;
	IConnectedCitiesFinder finder;

	@Value("${connctedcities.input.file}")
	private String file;
	
	@Value("${connctedcities.graphsearch.algorithm}")
	private String algorithm;

	@PostConstruct
	public void init() throws URISyntaxException, IOException {
		cityPairs = loadCities();
		setCityIds();
		setFinderObject(); 
	}

	public Set<CityPair> loadCities() throws URISyntaxException, IOException {
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource(file).toURI()))
				.filter(s -> !s.isEmpty())) { // ignore new line (empty lines) in the input file
			cityPairs = stream.map(line -> {
				String[] pairItems = line.split(",");
				City city1 = new City(pairItems[0].trim().toLowerCase());
				City city2 = new City(pairItems[1].trim().toLowerCase());
				CityPair newPair = new CityPair(city1, city2);
				distinctEntries.add(city1);
				distinctEntries.add(city2);
				return newPair;
			}).collect(Collectors.toSet());
		} catch (URISyntaxException | IOException e) {
			System.out.println(" Error reading Cities");
			e.printStackTrace();
		}
		return cityPairs;
	}

	private void setCityIds() {
		distinctEntries.forEach((City city) -> {
			cityIdMap.put(city, counter);
			counter++;

		});

		cityPairs.forEach((CityPair citypair) -> {
			City LCity = citypair.getLCity();
			City RCity = citypair.getRCity();
			LCity.setId(cityIdMap.get(LCity));
			RCity.setId(cityIdMap.get(RCity));

		});

	}
	
	public void setFinderObject() {
		int vertices = cityIdMap.size();
    	routeFactory = new RouteFinderFactory(vertices, cityPairs);
    	finder = routeFactory.getFinderAlgorithm(GraphSearchAlgorithm.valueOf(algorithm));
    }
    
    @Override
   	public boolean isConnected(String origin, String destination) {
   		City srcCity = new City(origin.trim());
   		City destCity = new City(destination.trim());
   		Integer srcCityId = cityIdMap.get(srcCity);
   		Integer destCityId = cityIdMap.get(destCity);
   		return finder.isConnected(srcCityId, destCityId);
   	}

}
