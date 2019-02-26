package com.ilakk.develop.connected.cities.city;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

public class City {

	private Integer id;
	private String name;

	public City(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.getName().length());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (this.getName().equals(((City) obj).getName())) {
			return true;
		}
		return false;
	}

}
