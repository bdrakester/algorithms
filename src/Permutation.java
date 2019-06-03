/**
 * The Permutation class takes an integer k as a command-line argument, reads in a sequence of strings from 
 * standard input and prints exactly k of them, uniformly at random. Each item from the sequence is 
 * printed at most once.
 *  
 * @author Brian Drake
 *
 */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
		int k;
		
		if (args.length != 1) {
			k = 1;
		}
		else {
			k = Integer.parseInt(args[0]);
		}
		
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		while (!StdIn.isEmpty()) {
			rq.enqueue(StdIn.readString());
		}
		
		for (int i = 0; i < k; i++) {
			System.out.println(rq.dequeue());
		}
		
	}

}
