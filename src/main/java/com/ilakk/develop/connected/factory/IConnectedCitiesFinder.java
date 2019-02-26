package com.ilakk.develop.connected.cities.factory;

/*
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

@FunctionalInterface
public interface IConnectedCitiesFinder {
	
	public boolean isConnected(Integer origin, Integer destination);

}
