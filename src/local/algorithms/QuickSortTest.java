package local.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Inversions class
 * @author Brian Drake
 */
class QuickSortTest {

	/** 
	 * Test QuickSort base case.
	 */
	@Test
	void testQuicksortBase() {
		int input[] = {3};
		int length = 1;
		int expected[] = {3};
		int result[] = QuickSort.quicksort(input, length);
		
		assertArrayEquals(expected, result);
		
	}
	/** 
	 * Test QuickSort properly sorts small array.
	 */
	
	@Test
	void testQuicksort() {
		int input[] = {3,8,2,5,1,4,7,6};
		int length = 8;
		int expected[] = {1,2,3,4,5,6,7,8};
		int result[] = QuickSort.quicksort(input, length);
		
		assertArrayEquals(expected, result);
		
	}
	
}
