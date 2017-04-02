package project2;
/**
 * @author adithepnarula
 * @version 1
 * This class uses the selectionsort algorithm to sort a given array 
 */


public class SelectionSortTest_V1 {
	

	/**	
	 * This method uses the selection sort algorithm to sorted the array passed as argument
	 * @param myList
	 * 	array of any reference type that implements comparable can be passed as argument
	 */
	public static <T extends Comparable<T>> void selectionSort(T[] myList){
		
		T currentMin;
		int currentMinIndex;
		
		for (int i = 0; i < myList.length - 1; i++ ){
			currentMin = myList[i];
			currentMinIndex = i;
		
			for (int j = i+1; j < myList.length; j++ ) {
				//if currentMin is greater than current element in list
				//then switch current min
				if(currentMin.compareTo(myList[j]) > 0) {
					currentMin = myList[j];
					currentMinIndex = j;
				}
				
			}//exit inner for loop
			
			//swap if current min position is not what we started with
			if (currentMinIndex != i){
				T temp = myList[i];
				myList[i] = currentMin;
				myList[currentMinIndex] = temp;	
			}

		}//exit outer for loop
			

		
	}
	


}
