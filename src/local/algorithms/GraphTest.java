package local.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {

	/**
	 * Test adding vertices
	 */
	@Test
	void testAddVertex() {
		Graph testGraph = new Graph();
		
		boolean result = testGraph.addVertex(1);
		assertEquals(result, true);
		
		result = testGraph.addVertex(1);
		assertEquals(result, false);
		//System.out.println("testAddVertex - Graph = ");
		//testGraph.printGraph();
		//System.out.println("");
	}
	
	@Test
	void testAddEdge() {
		Graph testGraph = new Graph();
		
		boolean result = testGraph.addEdge(1, 2);
		assertEquals(result, true);
		
		testGraph.addEdge(1,3);
		testGraph.addEdge(1,4);
		testGraph.addEdge(2,5);
		testGraph.addEdge(2,6);
		testGraph.addEdge(2,4);
		testGraph.addEdge(3,5);
		testGraph.addEdge(4,5);
		testGraph.addEdge(5,6);
		
		/*
		System.out.println("testAddEdge - Graph = ");
		testGraph.printGraph();
		System.out.println("");
		
		System.out.println("Updating Edge List 1,4 to 1,9 ...");
		testGraph.updateEdgeList(1, 4, 1, 9);
		testGraph.printGraph();
		System.out.println("");
		*/
	}
	
	/**
	 * Test minimum cut using test cases from - 
	 * https://github.com/beaunus/stanford-algs/tree/master/testCases/course1/assignment4MinCut
	 */
	
	/**
	 * Test Case input_random_1_6 
	 */
	@Test
	void testMinCut1_6() {
		Graph testGraph = new Graph();
		
		testGraph.addEdge(1,2);
		testGraph.addEdge(1,3);
		testGraph.addEdge(1,4);
		testGraph.addEdge(2,5);
		testGraph.addEdge(2,6);
		testGraph.addEdge(2,4);
		testGraph.addEdge(3,5);
		testGraph.addEdge(4,5);
		testGraph.addEdge(5,6);
		
		int numVertices = testGraph.getNumVertices();
		int numEdges = testGraph.getNumEdges();
		assertEquals(6, numVertices);
		assertEquals(9, numEdges);
		
		System.out.println("testMinCut1_6 - testGraph:");
		testGraph.printGraph();
		System.out.println("");
		
		/*
		Graph newGraph = new Graph(testGraph);
		newGraph.addEdge(1,5);
		//newGraph.addVertex(7);
		System.out.println("Added edge 1 5 to newGraph\n");
		
		System.out.println("testMinCut1_6 - newGraph (" + newGraph.getNumVertices() + " vertices)");
		newGraph.printGraph();
		System.out.println("");
		
		System.out.println("testMinCut1_6 - testGraph (" + testGraph.getNumVertices() + " vertices)");
		testGraph.printGraph();
		System.out.println("");
		*/
		
		int result = testGraph.minimumCut();
		assertEquals(2, result);
	}
}
