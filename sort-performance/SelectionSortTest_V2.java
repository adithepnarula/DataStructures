package project2;

/**
 * @author adithepnarula
 * @version 2
 * This class uses the selectionsort algorithm to sort a given array 
 */


public class SelectionSortTest_V2 {

	/**	
	 * This method uses the selection sort algorithm to sorted the array passed as argument.
	 * This is different than V1 because it implements both descending selection sort algorithm
	 * and ascending selection sort algorithm into one
	 * @param myList
	 * 	array of any reference type that implements comparable can be passed as argument
	 */
	public static <T extends Comparable<T>> void selectionSort(T[] myList){
	
		T currentMin, currentMax;
		int currentMinIndex, currentMaxIndex;
		for (int i = 0; i < myList.length/2; i++ ){
			currentMin = myList[i];
			currentMax = myList[myList.length-i-1];
			
			currentMinIndex = i;
			currentMaxIndex = myList.length-i-1;
			
			for (int j = i+1, k = myList.length - i - 2; j < myList.length-i; j++, k-- ) {
				//if currentMin is greater than current element in list
				//then switch current min
				
				if(currentMin.compareTo(myList[j]) > 0) {
					currentMin = myList[j];
					currentMinIndex = j;
				}
				
				if(currentMax.compareTo(myList[k]) < 0) {
					
					currentMax = myList[k];
					currentMaxIndex = k;
					//System.out.println("current Max Index: " + currentMax);
				}
				
			}//exit inner for loop
			
			//swap if current min position is not what we started with
			
		
	
			if (currentMinIndex != i){
				
	
				T temp = myList[i];
				myList[i] = currentMin;
				myList[currentMinIndex] = temp;	
				
			}
			
			if (currentMaxIndex != myList.length-i-1) {
				if (currentMaxIndex != i){
					T temp = myList[myList.length-i-1];
					myList[myList.length-i-1] = currentMax;
					myList[currentMaxIndex] = temp;
				
					
				}
				
				else {
				
					currentMaxIndex = currentMinIndex;
					T temp = myList[myList.length-i-1];
					myList[myList.length-i-1] = currentMax;
					myList[currentMaxIndex] = temp;
					

				}
								
			}
			
			TestSortingAlgo_V1.printMe(myList);

			
			
		}//exit outer for loop
			

		
	}
	
}
