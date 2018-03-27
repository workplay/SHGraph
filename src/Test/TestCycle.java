package Test;

import graph.Graph;

public class TestCycle {

	public static void main(String[] args) {
		System.out.println(new TestCycle().canFinish(2, new int[][]{{1,0}}));
	}

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph<Integer> g = new Graph<Integer>();
        for (int i=0;i<numCourses;i++){
            g.addNode(i);
        }
        for (int i=0;i<prerequisites.length;i++){
            int[] pre = prerequisites[i];
            g.addEdge(pre[1],pre[0]);
        }
        g.display();
        System.out.println("-------------------");
        return g.noCycle();
    }
	
}
