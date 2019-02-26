package com.ilakk.develop.connected.cities.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.ilakk.develop.connected.cities.factory.IConnectedCitiesFinder;
import com.ilakk.develop.connected.cities.graph.Graph;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

public class DFS implements IConnectedCitiesFinder{
	private Graph graph;
	
	public DFS(Graph graph) {
		this.graph = graph;
	}
	
	@Override
	public boolean isConnected(Integer src, Integer dest) {
		List<Integer> visited = new ArrayList<Integer>();
		visited.add(src);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		return isConnDFS(visited, queue, dest);
	}

	private boolean isConnDFS(List<Integer> visited, Queue<Integer> queue, Integer dest) {
		boolean result = false;
		while (!queue.isEmpty()) {
			Integer curr = queue.remove();
			visited.add(curr);
			if (curr == dest) {
				result = true;
			}
			LinkedList<Integer> currAdjacents = this.graph.getAdjacents(curr);
			for (Integer adj : currAdjacents) {
				if (!(visited.contains(adj))) {
					queue.add(adj);
				}
			}
		}
		return result;
	}

}
