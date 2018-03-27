package graph;
import java.util.*;

public class Graph<T> {
	Map<T,Node<T>> LabelsAndNodes = new HashMap<T, Node<T>>();
	
	public boolean addNode(T label){
		if (this.hasNode(label)) return false;
		LabelsAndNodes.put(label, new Node<T>(label));
		return true;
	}
	
	public boolean addNode(Node<T> n){
		if (n==null) return false;
		if (this.hasNode(n)) return false;
		LabelsAndNodes.put(n.label,n);
		return true;
	}
	
	public boolean addEdge(Node<T> start, Node<T> end){
		if (!LabelsAndNodes.values().contains(start) || !LabelsAndNodes.values().contains(end))
			return false;
		return start.addNeighbor(end);
	}
	public boolean addEdge(T start, T end){
		if (!LabelsAndNodes.containsKey(start) || !LabelsAndNodes.containsKey(end))
			return false;
		return addEdge(LabelsAndNodes.get(start), LabelsAndNodes.get(end));
	}
	
	public boolean hasNode(Node<T> n){
		return LabelsAndNodes.values().contains(n);
	}
	public boolean hasNode(T label){
		return LabelsAndNodes.containsKey(label);
	}
	
	public int size(){
		return LabelsAndNodes.size();
	}
	
	public Node<T> getNode(T label){
		return LabelsAndNodes.get(label);
	}
	
	public Set<Node<T>> getNodes(){
		return new HashSet<Node<T>>(LabelsAndNodes.values());
	}
	
	public void display(){
		for (Node<T> n:LabelsAndNodes.values()){
			System.out.print(n.label + " : ");
			for (Node<T> neighbor : n.neighbors){
				System.out.print(neighbor.label +" ");
			}
			System.out.println();
		}
	}
	
	
	/*
	 * Algorithm1 : check cycle and topological sort
	 */
	//DFS is the only good search for cycle finding.
	public boolean noCycle(){
		Set<Node<T>> checked = new HashSet<Node<T>>();
		Stack<Node<T>> result = new Stack<Node<T>>();
		for (Node<T> n: this.getNodes()){
			if (checked.contains(n)) continue;
			if (!dfs_noCycle(n, new HashSet<Node<T>>(),checked, result)){
				return false;
			}
		}
		while (!result.isEmpty()){
			System.out.println("order " + result.pop());
		}
		return true;
	}
	// no cycle?
	public boolean dfs_noCycle(Node<T> node, Set<Node<T>> visited, Set<Node<T>> checked, Stack<Node<T>> sort){
		if (visited.contains(node))
			return false;
		visited.add(node);
		checked.add(node);
		for (Node<T> n : node.neighbors){
			if (!dfs_noCycle(n, visited, checked, sort)){
				return false;
			}
		}
		visited.remove(node); //back trace
		
		//Topological Sort, remove duplicates
		if (!sort.contains(node)){
			sort.push(node);
		}
	
		return true;
	}
	
	/*
	 * Algorithm 2: Find path
	 */
	public List<List<Node<T>>> findPath(T start, T end){
		Node<T> nstart = this.getNode(start);
		Node<T> nend = this.getNode(end);
		List<List<Node<T>>> result = new ArrayList<List<Node<T>>>();
		dfsPath(nstart, nend, new ArrayList<Node<T>>(), result);
		return result;
	}
	
	public void dfsPath(Node<T> node, Node<T> end, List<Node<T>> current, List<List<Node<T>>> result){
		if (node.equals(end)){
			current.add(node);
			result.add(new ArrayList<Node<T>>(current));
			current.remove(node);
			return;
		}
		
		current.add(node);
		for (Node<T> n: node.neighbors){
			dfsPath(n,end, current, result);
		}
		current.remove(current.size()-1);
	}
	
}
