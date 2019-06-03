import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

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
		int numDequeue = 3;
		while(numDequeue > 0) {
			System.out.println("dequeue() returned " + rq.dequeue());
			rq.print();
			System.out.println("");
			numDequeue--;
		}
		
		System.out.println("Enqueue more for tail wrap to front of array");
		while(index < 10) {
			System.out.println("enqueue(" + input[index] + ")");
			rq.enqueue(input[index]);
			rq.print();
			System.out.println("");
			index++;
		}
		
		System.out.println("Dequeue/Enqueue more for head wrap to front");
		numDequeue = 3;
		while(numDequeue > 0) {
			System.out.println("dequeue() returned " + rq.dequeue());
			rq.print();
			System.out.println("");
			numDequeue--;
		}
		while(index < 13) {
			System.out.println("enqueue(" + input[index] + ")");
			rq.enqueue(input[index]);
			rq.print();
			System.out.println("");
			index++;
		}
		numDequeue = 4;
		while(numDequeue > 0) {
			System.out.println("dequeue() returned " + rq.dequeue());
			rq.print();
			System.out.println("");
			numDequeue--;
		}
		
		
	}
	
	@Test
	void testSample() {
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		for(int i : input) {
			rq.enqueue(i);
		}
		
		boolean result = Arrays.binarySearch(input, rq.sample()) > 0;
		assertTrue(result);
	}

	
	@Test
	void testIterator() {
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
		int[] output = new int[14];
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		
		// Enqueue all the items in the input array
		for(int i : input) {
			rq.enqueue(i);
		}
		
		// Dequeue all the items and store in output array
		int index = 0;
		for (int item : rq) {
			output[index] = item;
			index++;
		}
		// Sort the array, should now match input array
		Arrays.sort(output);
		assertArrayEquals(input,output);
	}
	
}
