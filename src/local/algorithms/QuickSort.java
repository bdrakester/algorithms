package local.algorithms;

/**
 * The QuickSort Class implements the QuickSort algorithm
 * @author Brian Drake
 */
public class QuickSort
{
	enum element {FIRST, LAST, MEDIANOFTHREE};
	
	// The total number of comparisons used to sort
	int comparisons;
	element pivotElement;
	
	public QuickSort() {
		comparisons = 0;
		pivotElement = element.FIRST;
	}
	
	public int getComparisons() {
		return comparisons;
	}
	
	public void setPivotElement(element pivotElement) {
		this.pivotElement = pivotElement;
	}
	
	/**
	 * Sort an array of integers using the quicksort algorithm
	 * @param input
	 * @param l
	 * @param r
	 * @return
	 */
	public int[] quicksort(int [] input, int l, int r) {
		if (l == r || r < l) {
			return input;
		}
		
		// Increment the comparisons by num of elements being sorted - 1
		comparisons += (r-l);
		
		int pivot;
		
		// Select the pivot element
		if (pivotElement == element.LAST) {
			// Swap the last and first elements so last will be pivot
			swap(input,l,r);
		}
		else if (pivotElement == element.MEDIANOFTHREE) {
			// Swap the median of three element with the first to make it pivot
			int median = medianOfThree(input,l,r);
			swap(input,l,median);
		}
		pivot = input[l];
		
		// Partition around the pivot element
		int i = l+1;
		//int temp;
		for (int j = l+1; j <= r; j++) {
			// If next element smaller than pivot
			if ( input[j] < pivot ) {
				// Swap elements
				swap(input,i,j);
				// Increment partition index
				i++;
			}
		}
		// Swap pivot into place
		swap(input,l,i-1);
		
		// Recursivley sort each partition
		quicksort(input,l,i-2);
		quicksort(input,i,r);
		
		
		return input;
	}
	
	/**
	 * Swap two elements in an array.
	 * @param input
	 * @param i
	 * @param j
	 */
	private void swap(int[] input, int i, int j) {
		int temp = input[j];
		input[j] = input[i];
		input[i] = temp;
	}
	
	/**
	 * Return the index of the median of the first, middle, and last elements in sub array of input
	 * bounded by l and r.
	 * @param input
	 * @return
	 */
	private int medianOfThree(int[] input, int l, int r) {
		int first, middle, last;
		int middleIndex;
		
		first = input[l];
		last = input[r];
		
		/* Select middle index, if even array lenght 2k, the middle is the
		 * k-th index.
		 */
		if ( (r - l) % 2 == 0 ) {
			middleIndex = l + ( (r - l) / 2);
			middle = input[middleIndex];
		}
		else {
			middleIndex = l + ( ( (r - l) - 1 ) / 2 );
			middle = input[middleIndex];
		}
		
		// Find the median element
		if (first < middle) {
			if (first >= last) {
				return l;
			}
			else if(middle < last) {
				return middleIndex;
			}
		}
		else {
			if (first < last) {
				return l;
			}
			if (middle >= last) {
				return middleIndex;
			}
		}
		return r;

	}


}
