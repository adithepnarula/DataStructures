package project2;
/**
 * @author adithepnarula
 * @version 1
 * This class uses the mergesort algorithm to sort a given array 
 */

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MergeSortTest_V1 {

	
	/**
	 * This method is contains the main logic behind mergesort algorihm. 
	 * It recursively calls itself until last >= first, indicating that 
	 * the next mergesort call or merge method will be called.
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
			 merge(myList, first, mid, mid+1, last);
			 
		 }
		 
	 }
	
	 /**
	  * This method compares a portion of the array and sorts it in the correct order
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
	 private static <T extends Comparable<T>> void merge(T[] myList, int firstL, int lastL, int firstR, int lastR){
		 //cannot create arrays of parameterized types
		 @SuppressWarnings("unchecked")
		T tempArray[] = (T[])Array.newInstance(myList.getClass().getComponentType(), lastR-firstL+1);

		 int indexL = firstL;
		 int indexR = firstR;
		 int indexM = 0;
	
		 //as long as we have not finished sorting one portion of the array keep looping
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
		 
		 //copy leftover elements to temporary array
		 while(indexL <= lastL){
			 tempArray[indexM] = myList[indexL];
			 indexL++;
			 indexM++;
		 }
		 
		 //copy leftover elements to temporary array
		 while(indexR <= lastR){
			 tempArray[indexM] = myList[indexR];
			 indexR++;
			 indexM++;
		 }
		
		 //copy array back to original array
		 for (int i = 0; i < tempArray.length; i++, firstL++){
			 myList[firstL] = tempArray[i];
		 }
		 

		 
		 
	 }
	 
	}
