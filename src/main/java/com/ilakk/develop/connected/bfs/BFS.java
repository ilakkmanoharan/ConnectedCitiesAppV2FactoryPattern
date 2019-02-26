package com.ilakk.develop.connected.cities.bfs;

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

public class BFS implements IConnectedCitiesFinder {

	private Graph graph;

	public BFS(Graph graph) {
		this.graph = graph;
	}

	@Override
	public boolean isConnected(Integer src, Integer dest) {
		List<Integer> visited = new ArrayList<Integer>();
		visited.add(src);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(src);
		return isConnBFS(visited, queue, dest);
	}

	private boolean isConnBFS(List<Integer> visited, Queue<Integer> queue, Integer dest) {
		while (!queue.isEmpty()) {
			Integer curr = queue.remove();
			visited.add(curr);
			if (curr == dest) {
				return true;
			}
			LinkedList<Integer> currAdjacents = this.graph.getAdjacents(curr);
			for (Integer adj : currAdjacents) {
				if (!(visited.contains(adj))) {
					queue.add(adj);
					if (isConnBFS(visited, queue, dest))
						return true;
				}
			}
		}
		return false;
	}

}
