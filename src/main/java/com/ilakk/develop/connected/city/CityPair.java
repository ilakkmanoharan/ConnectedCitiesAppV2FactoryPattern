package com.ilakk.develop.connected.cities.city;

import java.util.ArrayList;
import java.util.List;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

public class CityPair {

	private City lCity;
	private City rCity;

	public CityPair(City lCity, City rCity) {
		this.lCity = lCity;
		this.rCity = rCity;
	}

	public City getLCity() {
		return lCity;
	}

	public City getRCity() {
		return rCity;
	}

}
