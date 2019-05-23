
/**
 * The Stack class is an implementation of a stack using a linked list.
 * 
 * @author Brian Drake
 *
 * @param <Item> the data type of items stored in the stack
 */
public class Stack<Item> {
	private Node top = null;
	
	private class Node{
		Item item;
		Node next;
	}
	
	/**
	 * Pops the item on the top of the stack.
	 * @return the item on the top of the stack.
	 */
	public Item pop() {
		// If the stack is empty
		if (top == null) {
			return null;
		}
		
		Item item = top.item;
		top = top.next;
		return item;
	}
	
	/**
	 * Push a new item onto the stack.
	 * @param item
	 */
	public void push(Item item) {
		Node newNode = new Node();
		
		newNode.item = item;
		newNode.next = top;
		top = newNode;
	}
	
	/**
	 * Returns true if the stack is empty
	 * 
	 * @return true if the stack is emtpy
	 */
	public boolean isEmtpy() {
		return top == null;
		
	}
	/**
	 * Returns the maximum entry in the stack, assuming the items
	 * are integers.
	 * @return
	 */
	public int max() {
		// Brute force method 
		int maxItem = (Integer) top.item;
		Node current = top.next;
		
		while(current != null) {
			if ((Integer)current.item > maxItem) {
				maxItem = (Integer) current.item;
			}
			current = current.next;
		}
		
		return maxItem;
	}
	
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<>();
		
		// Push all command line argruments to the stack
		for(String input : args) {
			s.push(Integer.valueOf(input));
		}
		
		// Print the Max element
		System.out.println("Max = " + s.max());
		
		// Print the entire stack:
		System.out.println("\nStack: ");
		for(int i = 0; i < args.length; i++) {
			System.out.println(s.pop());
		}
	}

}
