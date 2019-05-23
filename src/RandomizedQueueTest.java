import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RandomizedQueueTest {

	@Test
	void testIsEmpty() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		assertTrue(rq.isEmpty());
		
		rq.enqueue(10);
		assertFalse(rq.isEmpty());
	}
	
	@Test
	void testSize() {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		assertEquals(0, rq.size());
		
		rq.enqueue(2);
		assertEquals(1, rq.size());
		
		rq.enqueue(3);
		rq.enqueue(5);
		assertEquals(3, rq.size());
		
		rq.dequeue();
		assertEquals(2, rq.size());
	}
	

	
	@Test
	void testEnqueue() {
		System.out.println("testEnqueue...");
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		
		System.out.println("Empty Queue:");
		rq.print();
		System.out.println("");
		for(int i : input) {
			System.out.println("enqueue(" + i + "):");
			rq.enqueue(i);
			rq.print();
			System.out.println("");
		}
	}
	
	
	@Test
	void testDequeue() {
		System.out.println("testDequeue...");
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		
		for(int i : input) {
			rq.enqueue(i);
		}
		rq.print();
		System.out.println("");
		
		while(!rq.isEmpty()) {
			System.out.println("dequeue() returned " + rq.dequeue());
			rq.print();
			System.out.println("");
		}
	}
	
	@Test
	void testEnqueueDequeue() {
		// Test a series of enqueues and dequeue
		System.out.println("testEnqueueDequeue...");
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		
		System.out.println("Enqueue a few items...");
		int index = 0;
		while(index < 6) {
			rq.enqueue(input[index]);
			index++;
		}
		rq.print();
		System.out.println("");
		System.out.println("Dequeue a few items");
		int numDequeue = 2;
		while(numDequeue > 0) {
			System.out.println("dequeue() returned " + rq.dequeue());
			rq.print();
			System.out.println("");
			numDequeue--;
		}
		
		
		
		
	}
	/*
	@Test
	void testSample() {
		fail("Not yet implemented");
	}

	@Test
	void testIterator() {
		fail("Not yet implemented");
	}
	*/
}
