/**
 * The RandomizedQueue class is an implemantion of a randomized queue.
 * @author Brian Drake
 *
 * @param <Item> the data type of items stored in the RandomizedQueue
 */

import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] queue; // array for storing items in queue
	int head; // the index of the first item in the queue
	int tail; // the index after the last item in the queue
	int size; // number of items in the queue
	
	/**
	 * Construct an empty randomized queue.
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		queue = (Item[]) new Object[1];
		head = 0;
		tail = 0;
		size = 0;
	}
	
	/**
	 * Is the randomized queue empty?
	 * 
	 * @return true if the randomized queue is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Return the number of items on the randomized queue.
	 * @return the number of items on the randomized queue.
	 */
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		
		int current = head;
		for(int i = 0; i < size; i++) {
			copy[i] = queue[current];
			current = (current + 1) % queue.length;
		}
		queue = copy;
		head = 0;
		tail = size;
	}
	
	/**
	 * Add an item to the randomized queue.
	 * @param item
	 */
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.IllegalArgumentException("Can not enqueue null item."); 
		}
		 
		// Add to the tail of the queue
		queue[tail] = item;
		
		// Increment the size
		size++;
		
		// Increment the tail
		tail = (tail + 1) % queue.length;
		
		// Double the array length if at capacity
		if(size == queue.length) {
			resize(2 * queue.length);
		}
		

	}
	
	/**
	 * Remove and return a random item
	 * @return
	 */
	public Item dequeue() {
		if(isEmpty()) {
			throw new java.util.NoSuchElementException("The queue is empty, cannot deque.");
		}
		
		// Swap random item with the front
		int rand = (head + StdRandom.uniform(size)) % queue.length;
		Item swap = queue[head];
		queue[head] = queue[rand];
		queue[rand] = swap;
		
		// Remove front of the queue
		Item item = queue[head];
		queue[head] = null;
		head = (head + 1) % queue.length;
		
		// Decrement the size
		size--;
		
		// Half the size of the array when less than 1/4 full
		if (size == queue.length / 4) {
			resize(queue.length/2);
		}
		
		return item;
	}
	
	/**
	 * Return a random item (but do not remove it)
	 * @return
	 */
	public Item sample() {
		if(isEmpty()) {
			throw new java.util.NoSuchElementException("The queue is empty, cannot sample.");
		}
		int rand = (head + StdRandom.uniform(size)) % queue.length;

		return queue[rand];
	}
	
	/**
	 * Return an independent iterator over items in random order
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator(); 
	}
	
	/**
	 * The RandomizedQueueIterator class impelement an iterator for
	 * the RandomizedQueue class.
	 */
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int current;
		private Item[] iteratorQueue;
		
		@SuppressWarnings("unchecked")
		public RandomizedQueueIterator() {
			// Make a copy of the queue, then shuffle  
			iteratorQueue = (Item[]) new Object[size];
			int current = head;
			for(int i = 0; i < size; i++) {
				iteratorQueue[i] = queue[current];
				current = (current + 1) % queue.length;
			}
			StdRandom.shuffle(iteratorQueue);
			current = 0;
		}
		
		public boolean hasNext() {
			return current < iteratorQueue.length;
		}
		
		public Item next() {
			if(!hasNext()) {
				throw new java.util.NoSuchElementException("No more items to return.");
			}
			
			Item item = iteratorQueue[current];
			current++;
			return item;
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException("Remove not supported.");
		}		
	}

	/**
	 * Prints the queue.  Make private before submitting.
	 */
	public void print() {
		for (Item item : queue) {
			System.out.print("| ");
			if(item == null) { 
				System.out.print("-");
			}
			else {
				System.out.print(item.toString());
			}
			System.out.print(" |");
		}
		System.out.println("\nSize = " + size);
		System.out.println("Queue.length = " + queue.length);
		System.out.println("Head = " + head);
		System.out.println("Tail = " + tail);
	}
}
