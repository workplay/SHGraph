package Test;

import graph.Graph;

public class TestGraph {

	public static void main(String[] args) {
		Graph<Integer> g = new Graph<Integer>();
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addEdge(1, 2);
		g.addEdge(1, 2);
		g.display();	
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
	}

}
