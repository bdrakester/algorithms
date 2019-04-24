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
		//System.out.println("Inside testCopyConstructor...");
		
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
		/*
		System.out.println("firstGraph = ");
		firstGraph.printGraph();
		System.out.println("");
		*/
		Graph secondGraph = new Graph(firstGraph);
		/*
		System.out.println("secondGraph = ");
		secondGraph.printGraph();
		System.out.println("");
		*/
		assertTrue(secondGraph.containsEdge(1,2));
		assertTrue(secondGraph.containsVertex(5));
		
		//System.out.println("Adding edge 5 1 to secondGraph");
		secondGraph.addEdge(5, 1);
		assertTrue(secondGraph.containsEdge(5,1));
		assertFalse(firstGraph.containsEdge(5,1));
		
		/*
		System.out.println("firstGraph = ");
		firstGraph.printGraph();
		System.out.println("");
		
		System.out.println("secondGraph = ");
		secondGraph.printGraph();
		System.out.println("");
		*/
		
		//System.out.println("Removing edge 4 1 from firstGraph");
		firstGraph.removeEdge(4, 1);
		assertFalse(firstGraph.containsEdge(4,1));
		assertTrue(secondGraph.containsEdge(4,1));
		/*
		System.out.println("firstGraph = ");
		firstGraph.printGraph();
		System.out.println("");
		
		System.out.println("secondGraph = ");
		secondGraph.printGraph();
		System.out.println("");
		*/
		
	}

	@Test
	void testContainsEdge(){
		Graph theGraph = new Graph();
		
		theGraph.addEdge(1,2);
		assertEquals(theGraph.containsEdge(1,2), true);
		assertEquals(theGraph.containsEdge(2,1), true);
		assertEquals(theGraph.containsEdge(2,3), false);
	}
	
	@Test
	void testRemoveVertex() {
		//System.out.println("Inside testRemoveVertex...");
		
		Graph theGraph = new Graph();
		theGraph.addEdge(1,2);
		theGraph.addEdge(1,3);
		theGraph.addEdge(1,4);
		theGraph.addEdge(2,5);
		theGraph.addEdge(2,6);
		theGraph.addEdge(2,4);
		theGraph.addEdge(3,5);
		theGraph.addEdge(4,5);
		theGraph.addEdge(5,6);
		
		//System.out.println("theGraph = ");
		//theGraph.printGraph();
		//System.out.println("");
		
		//System.out.println("Remove vertex 4...");
		theGraph.removeVertex(4);
		
		//System.out.println("theGraph = ");
		//theGraph.printGraph();
		//System.out.println("");
		
		assertFalse(theGraph.containsVertex(4));
		assertFalse(theGraph.containsEdge(1,4));
		assertFalse(theGraph.containsEdge(4,2));
		assertFalse(theGraph.containsEdge(4,5));
		assertTrue(theGraph.containsEdge(5,6));
		
	}
	
	@Test
	void testRemoveSelfLoops() {
		Graph theGraph = new Graph();
		theGraph.addEdge(1,2);
		theGraph.addEdge(1,3);
		theGraph.addEdge(1,4);
		theGraph.addEdge(2,2);
		theGraph.addEdge(3,3);
		
		theGraph.removeSelfLoops();
		
		assertFalse(theGraph.containsEdge(2,2));
		assertFalse(theGraph.containsEdge(3,3));
		assertEquals(3, theGraph.getNumEdges());
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
		
		assertEquals(6, testGraph.getNumVertices());
		assertEquals(9, testGraph.getNumEdges());
		
		System.out.println("testMinCut1_6 - testGraph:");
		testGraph.printGraph();
		System.out.println("");
		
		
		int result = testGraph.minimumCut();
		
		
		assertEquals(2, result);
	}
	
	/**
	 * Test Case input_random_2_6 
	 */
	
	@Test
	void testMinCut2_6() {
		Graph testGraph = new Graph();
		
		testGraph.addEdge(1,2);
		testGraph.addEdge(1,3);
		testGraph.addEdge(1,4);
		testGraph.addEdge(2,3);
		testGraph.addEdge(2,6);
		testGraph.addEdge(3,4);
		testGraph.addEdge(3,5);
		testGraph.addEdge(4,3);
		testGraph.addEdge(4,5);
		
		
		assertEquals(6, testGraph.getNumVertices());
		assertEquals(9, testGraph.getNumEdges());
		
		System.out.println("testMinCut1_6 - testGraph:");
		testGraph.printGraph();
		System.out.println("");
		
		
		int result = testGraph.minimumCut();
		
		
		assertEquals(1, result);
	}
	
	/**
	 * Test Case input_random_3_6 
	 */
	
	@Test
	void testMinCut3_6() {
		Graph testGraph = new Graph();
		
		testGraph.addEdge(1,3);
		testGraph.addEdge(1,5);
		testGraph.addEdge(1,2);
		testGraph.addEdge(1,6);
		testGraph.addEdge(2,5);
		testGraph.addEdge(2,4);
		testGraph.addEdge(3,6);
		testGraph.addEdge(3,5);
		testGraph.addEdge(4,5);
		testGraph.addEdge(4,6);
		testGraph.addEdge(5,6);
		
		int result = testGraph.minimumCut();
		assertEquals(3, result);
	}
	
	/**
	 * Test Case input_random_4_6 
	 */
	
	@Test
	void testMinCut4_6() {
		Graph testGraph = new Graph();
		
		testGraph.addEdge(1,2);
		testGraph.addEdge(1,5);
		testGraph.addEdge(1,4);
		testGraph.addEdge(1,6);
		testGraph.addEdge(2,3);
		testGraph.addEdge(2,6);
		testGraph.addEdge(2,4);
		testGraph.addEdge(3,4);
		testGraph.addEdge(3,6);
		testGraph.addEdge(3,5);
		testGraph.addEdge(4,5);
		testGraph.addEdge(5,6);
				
		int result = testGraph.minimumCut();		
		assertEquals(4, result);
	}
	
	/**
	 * Test Case input_random_10_25 
	 */
	
	@Test
	void testMinCut_10_25() {
		Graph testGraph = new Graph();
		
		testGraph.addEdge(1,14);
		testGraph.addEdge(1,21);
		testGraph.addEdge(1,11);
		testGraph.addEdge(1,3);
		testGraph.addEdge(1,10);
		testGraph.addEdge(1,17);
		testGraph.addEdge(1,20);
		testGraph.addEdge(1,22);
		testGraph.addEdge(1,24);
		testGraph.addEdge(1,25);
		testGraph.addEdge(2,3);
		testGraph.addEdge(2,6);
		testGraph.addEdge(2,8);
		testGraph.addEdge(2,9);
		testGraph.addEdge(2,10);
		testGraph.addEdge(2,14);
		testGraph.addEdge(2,17);
		testGraph.addEdge(2,22);
		testGraph.addEdge(2,25);
		testGraph.addEdge(3,13);
		testGraph.addEdge(3,5);
		testGraph.addEdge(3,7);
		testGraph.addEdge(3,11);
		testGraph.addEdge(3,6);
		testGraph.addEdge(3,4);
		testGraph.addEdge(3,22);
		testGraph.addEdge(3,9);
		testGraph.addEdge(3,12);
		testGraph.addEdge(3,14);
		testGraph.addEdge(3,15);
		testGraph.addEdge(3,16);
		testGraph.addEdge(3,17);
		testGraph.addEdge(3,18);
		testGraph.addEdge(3,20);
		testGraph.addEdge(3,23);
		testGraph.addEdge(4,23);
		testGraph.addEdge(4,15);
		testGraph.addEdge(4,11);
		testGraph.addEdge(4,17);
		testGraph.addEdge(4,7);
		testGraph.addEdge(4,22);
		testGraph.addEdge(4,12);
		testGraph.addEdge(4,20);
		testGraph.addEdge(4,25);
		testGraph.addEdge(4,18);
		testGraph.addEdge(4,24);
		testGraph.addEdge(5,19);
		testGraph.addEdge(5,18);
		testGraph.addEdge(5,10);
		testGraph.addEdge(5,7);
		testGraph.addEdge(5,9);
		testGraph.addEdge(5,16);
		testGraph.addEdge(5,22);
		testGraph.addEdge(6,14);
		testGraph.addEdge(6,15);
		testGraph.addEdge(6,10);
		testGraph.addEdge(6,18);
		testGraph.addEdge(6,12);
		testGraph.addEdge(6,8);
		testGraph.addEdge(6,22);
		testGraph.addEdge(6,25);
		testGraph.addEdge(6,19);
		testGraph.addEdge(6,21);
		testGraph.addEdge(7,25);
		testGraph.addEdge(7,12);
		testGraph.addEdge(7,16);
		testGraph.addEdge(7,17);
		testGraph.addEdge(7,20);
		testGraph.addEdge(7,22);
		testGraph.addEdge(8,17);
		testGraph.addEdge(8,21);
		testGraph.addEdge(8,15);
		testGraph.addEdge(8,22);
		testGraph.addEdge(8,20);
		testGraph.addEdge(8,10);
		testGraph.addEdge(8,23);
		testGraph.addEdge(9,11);
		testGraph.addEdge(9,12);
		testGraph.addEdge(9,16);
		testGraph.addEdge(9,14);
		testGraph.addEdge(9,10);
		testGraph.addEdge(9,25);
		testGraph.addEdge(9,18);
		testGraph.addEdge(9,20);
		testGraph.addEdge(10,19);
		testGraph.addEdge(10,16);
		testGraph.addEdge(10,17);
		testGraph.addEdge(10,24);
		testGraph.addEdge(10,18);
		testGraph.addEdge(10,22);
		testGraph.addEdge(10,23);
		testGraph.addEdge(11,21);
		testGraph.addEdge(11,12);
		testGraph.addEdge(11,13);
		testGraph.addEdge(11,14);
		testGraph.addEdge(11,22);
		testGraph.addEdge(12,23);
		testGraph.addEdge(12,16);
		testGraph.addEdge(12,20);
		testGraph.addEdge(12,25);
		testGraph.addEdge(12,21);
		testGraph.addEdge(12,18);
		testGraph.addEdge(12,19);
		testGraph.addEdge(13,24);
		testGraph.addEdge(13,16);
		testGraph.addEdge(13,20);
		testGraph.addEdge(13,21);
		testGraph.addEdge(14,16);
		testGraph.addEdge(14,24);
		testGraph.addEdge(14,17);
		testGraph.addEdge(14,20);
		testGraph.addEdge(14,23);
		testGraph.addEdge(15,25);
		testGraph.addEdge(15,19);
		testGraph.addEdge(15,20);
		testGraph.addEdge(15,16);
		testGraph.addEdge(15,21);
		testGraph.addEdge(15,22);
		testGraph.addEdge(15,24);
		testGraph.addEdge(16,21);
		testGraph.addEdge(16,17);
		testGraph.addEdge(16,23);
		testGraph.addEdge(16,18);
		testGraph.addEdge(17,19);
		testGraph.addEdge(17,22);
		testGraph.addEdge(17,23);
		testGraph.addEdge(17,25);
		testGraph.addEdge(18,23);
		testGraph.addEdge(18,22);
		testGraph.addEdge(18,24);
		testGraph.addEdge(18,20);
		testGraph.addEdge(18,21);
		testGraph.addEdge(19,21);
		testGraph.addEdge(19,22);
		testGraph.addEdge(19,23);
		testGraph.addEdge(20,22);
		testGraph.addEdge(20,25);
		testGraph.addEdge(21,25);
		testGraph.addEdge(21,23);
		testGraph.addEdge(21,24);
		testGraph.addEdge(22,24);
		testGraph.addEdge(22,23);
		testGraph.addEdge(23,25);
		testGraph.addEdge(24,25);

				
		int result = testGraph.minimumCut();		
		assertEquals(6, result);
	}
	
	
}
