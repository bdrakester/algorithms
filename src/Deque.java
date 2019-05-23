/**
 * The Deque class is an implemantion of a double-ended queue.
 * 
 * @author Brian Drake
 *
 * @param <Item> the data type of items stored in the deque
 */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Node front;
	private Node end;
	private int size;
	
	/**
	 * The Node inner class implements a node in the deque 
	 */
	private class Node{
		Item item;
		Node next;
		Node prev;
	}
	
	/**
	 * Construct an empty deque
	 */
	public Deque() {
		front = null;
		end = null;
		size = 0;
		
	}
	
	/**
	 * Is the deque empty?
	 * @return true if the deque is empty.
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Return the number of items on the deque
	 * @return the number items on the deque
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Add the item to the front.
	 * @param item the item to add.
	 */
	public void addFirst(Item item) {
		if(item == null) {
			throw new java.lang.IllegalArgumentException("Item cannot be null.");
		}
		
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = front;
		newNode.prev = null;
		
		// If the deque was empty, this new node is also the end
		if (size == 0) {
			end = newNode;
		}
		// Else the deque had at least 1 node
		else {
			front.prev = newNode;
		}
		
		front = newNode;
		
		size++;
	}
	
	/**
	 * Add the item to the end.
	 * @param item the item to add.
	 */
	public void addLast(Item item) {
		if(item == null) {
			throw new java.lang.IllegalArgumentException("Item cannot be null.");
		}
		
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = null;
		newNode.prev = end;
		
		// If the deque was empty, the new item is also at the front
		if (size == 0) {
			front = newNode;
		}
		// The deque was not empty
		else {
			end.next = newNode;
		}
		
		end = newNode;
		size++;
	}
	
	/**
	 * Remove and return the item from the front.
	 * @return the item at the front of the deque.
	 */
	public Item removeFirst() {
		if (size == 0) {
			throw new java.util.NoSuchElementException("Item cannont be removed from emtpy deque.");
		}
		
		Node oldFront = front;
		front = front.next;
		size--;
		
		/* If the deque is now empty, set end to null. Front should already
		 * be null from front = oldFront.next */ 
		if (size == 0) {
			end = null;
		}
		else {
			front.prev = null;
		}
		
		return oldFront.item;
	}
	
	/**
	 * Remove and return the item from the end.
	 * @return the item from the end of the deque.
	 */
	public Item removeLast() {
		if (size == 0) {
			throw new java.util.NoSuchElementException("Item cannont be removed from emtpy deque.");
		}
		
		Node oldEnd = end;
		end = end.prev;
		size--;
		
		/* If the deque is now empty, set front to null. End should already
		 * be null from end = end.prev */
		if(size == 0) {
			front = null;
		}
		else {
			end.next = null;
		}
		
		return oldEnd.item;
		
	}
	
	/**
	 * Return an iterator over items in order from front to end.
	 */
	public Iterator<Item> iterator(){
		return new DequeIterator();
	}
	
	/**
	 * The DequeIterator inner class implements an Iterator for the 
	 * Deque class
	 */
	private class DequeIterator implements Iterator<Item>{
		private Node current = front;
		
		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException("Remove not implemented.");
		}
		public Item next() {
			if(current == null) {
				throw new java.util.NoSuchElementException("No more items to return.");
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	
	/**
	 * Unit testing
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Unit Testing the Deque class ...\n");
		int failures = 0;
		
		Deque<Integer> myDeque = new Deque<>();
		
		// Test isEmpty() on empty Deque
		System.out.print("Testing isEmpty() on empty Deque... ");
		if(myDeque.isEmpty()) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test size on empty Deque
		System.out.print("Testing size on empty Deque... ");
		if(myDeque.size() == 0) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test isEmpty() on non-empty Deque
		myDeque.addFirst(1);
		System.out.print("Testing isEmpty() on non-empty Deque... ");
		if(!myDeque.isEmpty()) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test size on non-empty Deque
		System.out.print("Testing size on non-empty Deque... ");
		if(myDeque.size() == 1) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test addFirst on emtpy Deque
		myDeque = new Deque<>();
		System.out.print("Testing addFirst on empty Deque... ");
		myDeque.addFirst(2);
		if(myDeque.removeFirst() == 2) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test addLast on empty Deque
		myDeque = new Deque<>();
		System.out.print("Testing addLast on empty Deque... ");
		myDeque.addLast(2);
		if(myDeque.removeLast() == 2) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test addFirst on non-emtpy Deque
		myDeque = new Deque<>();
		System.out.print("Testing addFirst on non-empty Deque... ");
		myDeque.addFirst(1);
		myDeque.addFirst(2);
		myDeque.addFirst(3);
		if(myDeque.removeFirst() == 3) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test addLast on non-empty Deque
		System.out.print("Testing addLast on non-empty Deque... ");
		myDeque = new Deque<>();
		myDeque.addLast(3);
		myDeque.addLast(2);
		myDeque.addLast(1);
		if(myDeque.removeLast() == 1) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test removeFirst on 1 item Deque
		System.out.print("Testing removeFirst on 1 item Deque... ");
		myDeque = new Deque<>();
		myDeque.addLast(3);
		if(myDeque.removeFirst() == 3 && myDeque.isEmpty()) { 
			System.out.println("Passed!"); 
		}
		else { System.out.println("Failed :("); failures++; }
		
		// Test removeFirst on more than 1 item Deque
		System.out.print("Testing removeFirst on more than 1 item Deque... ");
		myDeque = new Deque<>();
		myDeque.addLast(3);
		myDeque.addFirst(20);
		if(myDeque.removeFirst() == 20) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test removeLast on 1 item Deque
		System.out.print("Testing removeLast on 1 item Deque... ");
		myDeque = new Deque<>();
		myDeque.addLast(3);
		if(myDeque.removeLast() == 3 && myDeque.isEmpty()) { 
			System.out.println("Passed!"); 
		}
		else { System.out.println("Failed :("); failures++; }
		
		// Test removeLast on more than 1 item Deque
		System.out.print("Testing removeLast on more than 1 item Deque... ");
		myDeque = new Deque<>();
		myDeque.addLast(3);
		myDeque.addFirst(20);
		if(myDeque.removeLast() == 3) { System.out.println("Passed!"); }
		else { System.out.println("Failed :("); failures++; }
		
		// Test iterator()
		System.out.print("Testing iterator()... ");
		myDeque = new Deque<>();
		int[] items = {10, 20, 30, 40, 50, 60, 70, 80};
		for (int item : items) {
			myDeque.addLast(item);
		}
		int[] returned = new int[items.length];
		
		int index = 0;
		for(int item: myDeque) {
			returned[index] = item;
			if(returned[index] == items[index]) { 
				System.out.print(" " + returned[index]);
			}
			else {
				failures++;
				break;
			}
			index++;
		}
		
		// Print test summary
		if (failures == 0) {
			System.out.println("\nAll tests passed!");
		}
		else {
			System.out.println(failures + " tests failed :(");
		}
		
	}

}
