package com.ilakk.develop.connected.cities.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

	// The graph is represented using adjacency list.
	// An adjacency list is an array of separate lists.
	// Each element of the array is a list,
	// which contains all the vertices that are adjacent to vertex i.
	// Size of array will be V (number of vertices in graph)

	private int V;

	private LinkedList<Integer> adjListArray[];

	public Graph(int V) {
		this.V = V;
		adjListArray = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adjListArray[i] = new LinkedList<>();
		}
	}

	public void addEdge(Graph graph, int src, int dest) {
		graph.adjListArray[src].add(dest);
		graph.adjListArray[dest].add(src);
	}
	
	public LinkedList<Integer> getAdjacents(int node){
		return adjListArray[node];
	}
	
	// A utility function to print the adjacency list  
    // representation of graph 
    public void printGraph(Graph graph) 
    {        
        for(int v = 0; v < graph.V; v++) 
        { 
            System.out.println("Adjacency list of vertex "+ v); 
            System.out.print("head"); 
            for(Integer pCrawl: graph.adjListArray[v]){ 
                System.out.print(" -> "+pCrawl); 
            } 
            System.out.println("\n"); 
        } 
    }
	

}