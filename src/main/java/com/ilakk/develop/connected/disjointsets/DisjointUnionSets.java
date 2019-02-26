package com.ilakk.develop.connected.cities.disjointsets;

import com.ilakk.develop.connected.cities.factory.IConnectedCitiesFinder;

public class DisjointUnionSets implements IConnectedCitiesFinder {

	private int[] rank, parent;
	private int n;

	public DisjointUnionSets(int n) {
		rank = new int[n];
		parent = new int[n];
		this.n = n;
		makeSet();
	}

	private void makeSet() {
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	private int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public void union(int x, int y) {
		int xRoot = find(x), yRoot = find(y);
		if (xRoot == yRoot)
			return;
		if (rank[xRoot] < rank[yRoot])
			parent[xRoot] = yRoot;
		else if (rank[yRoot] < rank[xRoot])
			parent[yRoot] = xRoot;
		else {
			parent[yRoot] = xRoot;
			rank[xRoot] = rank[xRoot] + 1;
		}
	}
    
	@Override
	public boolean isConnected(Integer src, Integer dest) {
		if (find(src) == find(dest))
			return true;
		else
			return false;
	}

}
