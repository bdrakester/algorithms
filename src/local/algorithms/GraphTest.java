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
		System.out.println("testAddVertex - Graph = ");
		testGraph.printGraph();
		System.out.println("");
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
		
		assertEquals(testGraph.containsEdge(1,3), true);
		assertEquals(testGraph.containsEdge(4, 5), true);
		
		//System.out.println("testAddEdge - Graph = ");
		//testGraph.printGraph();
		//System.out.println("");

		
	}
	
	@Test
	void testCopyConstructor() {
		System.out.println("Inside testCopyConstructor...");
		
		Graph firstGraph = new Graph();
		firstGraph.addEdge(1,2);
		firstGraph.addEdge(1,3);
		firstGraph.addEdge(1,4);
		firstGraph.addEdge(2,5);
		firstGraph.addEdge(2,6);
		firstGraph.addEdge(2,4);
		firstGraph.addEdge(3,5);
		firstGraph.addEdge(4,5);
		firstGraph.addEdge(5,6);
		
		System.out.println("firstGraph = ");
		firstGraph.printGraph();
		System.out.println("");
		
		Graph secondGraph = new Graph(firstGraph);
		System.out.println("secondGraph = ");
		secondGraph.printGraph();
		System.out.println("");
		
		System.out.println("Adding edge 5 1 to secondGraph");
		secondGraph.addEdge(5, 1);
		
		System.out.println("firstGraph = ");
		firstGraph.printGraph();
		System.out.println("");
		
		System.out.println("secondGraph = ");
		secondGraph.printGraph();
		System.out.println("");
		
		System.out.println("Removing edge 4 1 from firstGraph");
		firstGraph.removeEdge(4, 1);
		
		System.out.println("firstGraph = ");
		firstGraph.printGraph();
		System.out.println("");
		
		System.out.println("secondGraph = ");
		secondGraph.printGraph();
		System.out.println("");
		
	}

	@Test
	void testContainsEdge(){
		Graph theGraph = new Graph();
		
		theGraph.addEdge(1,2);
		assertEquals(theGraph.containsEdge(1,2), true);
		assertEquals(theGraph.containsEdge(2,1), true);
		assertEquals(theGraph.containsEdge(2,3), false);
	}
	
	
	/**
	 * Test minimum cut using test cases from - 
	 * https://github.com/beaunus/stanford-algs/tree/master/testCases/course1/assignment4MinCut
	 */
	
	/**
	 * Test Case input_random_1_6 
	 */
	/*
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
		/*
		int result = testGraph.minimumCut();
		assertEquals(2, result);
	}
	*/
	
}
