/**
 * The QueueOfStacks class in an implementation of a Queue with two stacks
 * so that each queue operations takes a constant amortized number of 
 * stack operations.
 * 
 * @author Brian Drake
 *
 */
public class QueueOfStacks<Item> {
	
	private Stack<Item> in, out;
	
	
	public QueueOfStacks(){
		in = new Stack<>();
		out = new Stack<>();
	}
	
	/** 
	 * Add an item to the end of the queue
	 * @param item
	 */
	public void enqueue(Item item) {
		// Push the element onto the in stack
		in.push(item);
	}
	
	/**
	 * Removes and returns the item at the front of the queue
	 * @return the item at the front of the queue
	 */
	public Item dequeue() {
		if( out.isEmtpy() ) {
			while ( !in.isEmtpy() ) {
				out.push(in.pop());
			}
		}
		return out.pop();
	}

	public static void main(String[] args) {
		QueueOfStacks<Integer> myQueue = new QueueOfStacks<>();
		
		System.out.println("Enqueue 1, 2, 3 ...");
		myQueue.enqueue(1);
		myQueue.enqueue(2);
		myQueue.enqueue(3);
		System.out.print("Dequeue x2 = ");
		System.out.println(myQueue.dequeue() + " " + myQueue.dequeue());
		System.out.println("Enqueue 4, 5, 6 ...");
		myQueue.enqueue(4);
		myQueue.enqueue(5);
		myQueue.enqueue(6);
		System.out.print("Dequeue x4 ... ");
		for(int i = 0; i < 4; i++) {
			System.out.print(myQueue.dequeue() + " ");
		}
		System.out.println("");

	}

}
