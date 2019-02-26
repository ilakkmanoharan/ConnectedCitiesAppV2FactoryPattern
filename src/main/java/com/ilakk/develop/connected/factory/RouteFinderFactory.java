package com.ilakk.develop.connected.cities.factory;

import java.util.Set;

import com.ilakk.develop.connected.cities.graph.Graph;
import com.ilakk.develop.connected.cities.city.*;
import com.ilakk.develop.connected.cities.dfs.DFS;
import com.ilakk.develop.connected.cities.bfs.BFS;
import com.ilakk.develop.connected.cities.disjointsets.DisjointUnionSets;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

public class RouteFinderFactory {

	private int vertices;
	private Set<CityPair> cityPairs;
	private Graph graph;
	private DisjointUnionSets disjointsets;

	public RouteFinderFactory(int vertices, Set<CityPair> cityPairs) {
		this.vertices = vertices;
		this.cityPairs = cityPairs;
		//initGraph();
	}

	private void initGraph() {
		graph = new Graph(vertices);
		cityPairs.forEach((CityPair citypair) -> {
			City LCity = citypair.getLCity();
			City RCity = citypair.getRCity();
			graph.addEdge(graph, LCity.getId(), RCity.getId());

		});
	}
	
    private void initDisjointSets() {
		
    	disjointsets = new DisjointUnionSets(vertices);
		
		cityPairs.forEach((CityPair citypair) -> {
			City LCity = citypair.getLCity();
			City RCity = citypair.getRCity();
			disjointsets.union(LCity.getId(), RCity.getId());

		});
		
	}
	
	public IConnectedCitiesFinder getFinderAlgorithm(GraphSearchAlgorithm algorithm) {
		if (algorithm == null) {
			return null;  // check for null before switch statement
		} else {
		switch (algorithm) {
		    case DFS:
		    {
		    	initGraph();
				return new DFS(graph);
		    }
			case BFS:
			{
				initGraph();
				return new BFS(graph);
			}
			case DisjointSets:
			{
				initDisjointSets();
				return disjointsets;
			}
			default:
				return null;	
		}
		}
	}
	

}
