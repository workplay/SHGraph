package graph;

import java.util.HashSet;
import java.util.Set;

public class Node<T>{
	T label;
	Set<Node<T>> neighbors = new HashSet<Node<T>>();
	
	public Node(T l){
		this.label = l;
	}
	public boolean addNeighbor(Node<T> n){
		return neighbors.add(n);
	}
	public boolean removeNeighbor(Node<T> n){
		return neighbors.remove(n);
	}
	public T getLabel(){
		return label;
	}
	@Override
	public String toString(){
		return label.toString();
	}
}

