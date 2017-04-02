package project2;
/**
 * @author adithepnarula
 * @version 2
 * This class uses the mergesort algorithm to sort a given array 
 */


import java.lang.reflect.Array;
import java.util.ArrayList;

public class MergeSortTest_V2 {

	/**
	 * This method is contains the main logic behind mergesort algorihm. 
	 * It recursively calls itself until last >= first, indicating that 
	 * the next mergesort call or merge method will be called. 
	 * In addition, this mergesort method is different than the one in V1 
	 * because it uses the conditional statement to decide whether to call the merge method or not
	 * @param myList
	 * 	array of any reference type that implements comparable can be passed as argument
	 * 	in this program, the unsorted argument array is passed from TestingAlgo_V1
	 * @param first
	 * 	first element in the selected portion
	 * @param last
	 * 	last element in the selection portion
	 */
	
	 public static <T extends Comparable <T>> void mergeSort(T[] myList, int first, int last){
		 
		 if (first < last){
			 //find mid element
			 int mid = (first+last)/2;
			 mergeSort(myList, first, mid);
			 mergeSort(myList, mid+1, last);
			 if(myList[mid].compareTo(myList[mid+1]) > 0) {
				 merge(myList, first, mid, mid+1, last);
			 }
		 }
		 
	 }
	
	 /**
	  * This method compares a portion of the array and sorts it in the correct order
	  * This method is identical to the one in mergeSort V1
	  * @param myList
	  * 	array that is passed in from merge method
	  * @param firstL
	  * 	the first element of the first portion of the array
	  * @param lastL
	  * 	the last element of the first portion of the array
	  * @param firstR
	  * 	the first element of the second portion of the array
	  * @param lastR
	  * 	the last element of the secon portion of the array
	  */
	 
	 public static <T extends Comparable<T>> void merge(T[] myList, int firstL, int lastL, int firstR, int lastR){
		 //cannot create arrays of parameterized types
		 @SuppressWarnings("unchecked")
		T tempArray[] = (T[])Array.newInstance(myList.getClass().getComponentType(), lastR-firstL+1);

		 int indexL = firstL;
		 int indexR = firstR;
		 int indexM = 0;
	
		 while (indexL <= lastL && indexR <= lastR){
			 if(myList[indexL].compareTo(myList[indexR]) <= 0){
				 tempArray[indexM] = myList[indexL];
				 indexL++;
			 }
			 
			 else{
				 tempArray[indexM] = myList[indexR];
				 indexR++;
			 }
			indexM++;
			 	 
		 }
		 
		 while(indexL <= lastL){
			 tempArray[indexM] = myList[indexL];
			 indexL++;
			 indexM++;
		 }
		 
		 while(indexR <= lastR){
			 tempArray[indexM] = myList[indexR];
			 indexR++;
			 indexM++;
		 }
		
		 //copy array back
		 for (int i = 0; i < tempArray.length; i++, firstL++){
			 myList[firstL] = tempArray[i];
		 }
		 

		 
		 
	 }
	 
	}
