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
		int expected[] = {3};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 0);
		
		assertArrayEquals(expected, result);
		assertEquals(0,test.getComparisons());
		
	}
	/** 
	 * Test QuickSort properly sorts 8 integer array.
	 */
	
	@Test
	void testQuicksort8() {
		int input[] = {3,8,2,5,1,4,7,6};
		int expected[] = {1,2,3,4,5,6,7,8};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 7);
		
		assertArrayEquals(expected, result);
	}
	
	/** 
	 * The following tests confirm QuickSort properly sorts integer arrays using the first element as 
	 * pivot (this is the default) and counts the number of comparisons correctly.
	 */
	
	@Test
	void testQuicksort5_1() {
		int input[] = {3,2,1,4,5};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(6, test.getComparisons());
	}
	
	@Test
	void testQuicksort5_2() {
		int input[] = {4,3,2,5,1};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(7, test.getComparisons());
	}
	
	@Test
	void testQuicksort5_3() {
		int input[] = {4,5,2,3,1};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(7, test.getComparisons());
	}
	
	@Test
	void testQuicksort3_1() {
		int input[] = {26,21,22};
		int expected[] = {21,22,26};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 2);
		
		assertArrayEquals(expected, result);
		assertEquals(3,test.getComparisons());
	}
	
	@Test
	void testQuicksort15_20() {
		int input[] = {2,1,12,13,16,10,9,5,18,8,17,20,19,3,4,11,14,6,7,15};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(69, test.getComparisons());
	}
	
	@Test
	void testQuicksort14_20() {
		int input[] = {2,20,1,15,3,11,13,6,16,10,19,5,4,9,8,14,18,17,7,12};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(81, test.getComparisons());
	}
	
	@Test
	void testQuicksort13_20() {
		int input[] = {19,17,4,12,18,2,7,10,8,16,9,3,5,6,15,11,20,1,13,14};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(91, test.getComparisons());
	}
	
	@Test
	void testQuicksort12_20() {
		int input[] = {5,11,8,17,18,19,4,13,16,6,10,9,3,1,2,14,15,12,7,20};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(76, test.getComparisons());
	}
	
	@Test
	void testQuicksort10_10() {
		int input[] = {3,2,10,6,7,1,9,5,4,8};
		int expected[] = {1,2,3,4,5,6,7,8,9,10};
		
		QuickSort test = new QuickSort();
		int result[] = test.quicksort(input, 0, 9);
		
		assertArrayEquals(expected, result);
		assertEquals(21, test.getComparisons());
	}
	
	/** 
	 * The following tests confirm QuickSort properly sorts integer arrays using the final element as 
	 * pivot and counts the number of comparisons correctly.
	 */
	@Test
	void testQuicksort5_1_final() {
		int input[] = {3,2,1,4,5};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.LAST);
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(10, test.getComparisons());
	}
	
	@Test
	void testQuicksort5_2_final() {
		int input[] = {4,3,2,5,1};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.LAST);
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(8, test.getComparisons());
	}
	
	@Test
	void testQuicksort5_3_final() {
		int input[] = {4,5,2,3,1};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.LAST);
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(8, test.getComparisons());
	}
	
	@Test
	void testQuicksort15_20_final() {
		int input[] = {2,1,12,13,16,10,9,5,18,8,17,20,19,3,4,11,14,6,7,15};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.LAST);
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(65, test.getComparisons());
	}
	
	@Test
	void testQuicksort14_20_final() {
		int input[] = {2,20,1,15,3,11,13,6,16,10,19,5,4,9,8,14,18,17,7,12};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.LAST);
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(69, test.getComparisons());
	}
	
	@Test
	void testQuicksort13_20_final() {
		int input[] = {19,17,4,12,18,2,7,10,8,16,9,3,5,6,15,11,20,1,13,14};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.LAST);
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(78, test.getComparisons());
	}
	
	@Test
	void testQuicksort12_20_final() {
		int input[] = {5,11,8,17,18,19,4,13,16,6,10,9,3,1,2,14,15,12,7,20};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.LAST);
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(75, test.getComparisons());
	}
	
	@Test
	void testQuicksort10_10_final() {
		int input[] = {3,2,10,6,7,1,9,5,4,8};
		int expected[] = {1,2,3,4,5,6,7,8,9,10};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.LAST);
		int result[] = test.quicksort(input, 0, 9);
		
		assertArrayEquals(expected, result);
		assertEquals(22, test.getComparisons());
	}

	/** 
	 * The following tests confirm QuickSort properly sorts integer arrays using the median of the
	 * first, middle and last element as the pivot and counts the number of comparisons correctly.
	 */
	@Test
	void testQuicksort5_1_median() {
		int input[] = {3,2,1,4,5};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.MEDIANOFTHREE);
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(6, test.getComparisons());
	}
	
	@Test
	void testQuicksort5_2_median() {
		int input[] = {4,3,2,5,1};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.MEDIANOFTHREE);
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(6, test.getComparisons());
	}
	
	@Test
	void testQuicksort5_3_median() {
		int input[] = {4,5,2,3,1};
		int expected[] = {1,2,3,4,5};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.MEDIANOFTHREE);
		int result[] = test.quicksort(input, 0, 4);
		
		assertArrayEquals(expected, result);
		assertEquals(6, test.getComparisons());
	}
	
	@Test
	void testQuicksort15_20_median() {
		int input[] = {2,1,12,13,16,10,9,5,18,8,17,20,19,3,4,11,14,6,7,15};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.MEDIANOFTHREE);
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(56, test.getComparisons());
	}
	
	@Test
	void testQuicksort14_20_median() {
		int input[] = {2,20,1,15,3,11,13,6,16,10,19,5,4,9,8,14,18,17,7,12};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.MEDIANOFTHREE);
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(55, test.getComparisons());
	}
	
	@Test
	void testQuicksort13_20_median() {
		int input[] = {19,17,4,12,18,2,7,10,8,16,9,3,5,6,15,11,20,1,13,14};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.MEDIANOFTHREE);
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(66, test.getComparisons());
	}
	
	@Test
	void testQuicksort12_20_median() {
		int input[] = {5,11,8,17,18,19,4,13,16,6,10,9,3,1,2,14,15,12,7,20};
		int expected[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.MEDIANOFTHREE);
		int result[] = test.quicksort(input, 0, 19);
		
		assertArrayEquals(expected, result);
		assertEquals(60, test.getComparisons());
	}
	
	@Test
	void testQuicksort10_10_median() {
		int input[] = {3,2,10,6,7,1,9,5,4,8};
		int expected[] = {1,2,3,4,5,6,7,8,9,10};
		
		QuickSort test = new QuickSort();
		test.setPivotElement(QuickSort.element.MEDIANOFTHREE);
		int result[] = test.quicksort(input, 0, 9);
		
		assertArrayEquals(expected, result);
		assertEquals(20, test.getComparisons());
	}
}
