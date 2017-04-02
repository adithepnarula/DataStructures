package project2;
/**
 * @author adithepnarula
 * @version 2.1
 * This class uses the quicksort algorithm to sort a given array 
 */



public class QuickSortTest_V2_1 {

	/**
	 * This method contains the main logic behind quicksort algorithm
	 * It recursively calls itself until last >= first, indicating that
	 * either the partition method or quicksort method will be called next.
	 * This method is identical to the one in V1.
	 * @param myList
	 * 	array of any reference type that implements comparable can be passed as argument
	 * 	in this program, the unsorted argument array is passed from TestingAlgo_V1
	 * @param first
	 * 	first element in the selected portion
	 * @param last
	 * 	last element in the selected portion
	 */
	public static <T extends Comparable<T>> void quickSort(T[] myList, int first, int last) {
		
		if (first < last) {

		int pivotPosition = partition(myList, first, last);
		quickSort(myList, first, pivotPosition-1);
		quickSort(myList, pivotPosition+1, last);
		
		}
	}
	
	
	/**
	 * This method puts the pivot in the right index, thus partitioning the array. 
	 * It also returns the index of pivot.
	 * This method is different thatn the one in V1 because it selects the pivot as the last element
	 * and does not do any swaps 
	 * 
	 * @param myList
	 * 	array that is passed in from quicksort method
	 * @param first
	 * 	the first element of the selected portion of the array
	 * @param last
	 * 	the last element of the selection portion of the array
	 * @return
	 * 	
	 */
	public static <T extends Comparable<T>> int partition(T[] myList, int first, int last){
		//select pivot as the last element
		T pivot = myList[last];
		
		int left = first;
		int right = last-1;
		
		while (right >= left) {
			
			while(myList[left].compareTo(pivot) < 0){
				left++;
				
			}
			
			while(right >= left && myList[right].compareTo(pivot) >= 0) {
				
				right--;
				
			}
			
			if(right > left) {
				T temp2 = myList[left];
				myList[left] = myList[right];
				myList[right] = temp2;
				
			}
			
			
		}
		
		//swap pivot back to the right place
		T temp = myList[left];
		myList[left] = pivot;
		myList[last] = temp;
		
	
		return left;
		
		
		
		
	}
	
	
	
	
}
