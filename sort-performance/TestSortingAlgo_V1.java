package project2;

/**
 * @author adithepnarula
 * @version 1
 * This class contains the main logic of the program. It sorts the three array using
 * selectionsort, mergesort, and quicksort and then prints out their sorting time. 
 */


import java.util.Arrays;
import java.util.Random;

public class TestSortingAlgo_V1 {
	
	/**
	 * The main method generates a random array, makes three copies of it, and calls each sorting methods
	 * @param args
	 * 	args will not be used in this program
	 */
	public static void main(String[] args){


			//generate a random array 
			Integer[] originalArray = generateArray();
			
			//create three empty array with the same lenght as original array
			Integer[] arrayQ = new Integer[originalArray.length];
			Integer[] arrayM = new Integer[originalArray.length];
			Integer[] arrayS = new Integer[originalArray.length];
			
			//copy the original array to the three array created above
			System.arraycopy(originalArray, 0, arrayQ, 0, originalArray.length);
			System.arraycopy(originalArray, 0, arrayM, 0, originalArray.length);
			System.arraycopy(originalArray, 0, arrayS, 0, originalArray.length);
			
			//call the sorting methods 
			sortingMethods(arrayM, arrayQ, arrayS);
		
	
	}
	
	/**
	 * This method sorts the unsorted array using selectionsort, mergesort, and quicksort
	 * It calculates the time each of of these method takes and prints it out.
	 * Furthermore, isSorted method is also called to make sure that each method have sorted the array passed in as argument
	 * @param arrayM
	 * 	unsorted array that will be sort by the mergesort method
	 * @param arrayQ
	 * 	unsorted array that will be sort by the quicksort method
	 * @param arrayS
	 * 	unsorted array that will be sort by the selectionsort method
	 */
	 
	public static void sortingMethods(Integer[] arrayM, Integer[] arrayQ, Integer[] arrayS){

		//call the isSorted method to make sure array passed in are not sorted
		boolean v1 = isSorted(arrayS);
		boolean v2 = isSorted(arrayM);
		boolean v3 = isSorted(arrayQ);
		System.out.println("v1 is sorted: " + v1);
		System.out.println("v2 is sorted: " + v2);
		System.out.println("v3 is sorted: " + v3);
	


		//call selection sort method and calculate the time it takes to sort
		System.out.println("----SelectionSort----");
		long startTimeSelection= System.nanoTime();
		SelectionSortTest_V1.selectionSort(arrayS);
		long endTimeSelection= System.nanoTime();
		long totalTimeSelection = (endTimeSelection - startTimeSelection)/1000000;
		System.out.println("Total time: " + totalTimeSelection);
	
		
		
		
		//call mergesort method and calculate the time it takes to sort
		System.out.println("-----MergeSort----");
		long startTimeMerge = System.nanoTime();
		MergeSortTest_V1.mergeSort(arrayM ,0, arrayM.length-1);
		long endTimeMerge = System.nanoTime();
		long totalTimeMerge = (endTimeMerge - startTimeMerge)/1000000;
		System.out.println("Total time: " + totalTimeMerge);
	
		
		//call quicksort method and calculate the time it takes to sort
		System.out.println("-----QuickSort-----");
		long startTimeQuick = System.nanoTime();
		QuickSortTest_V1.quickSort(arrayQ ,0, arrayQ.length-1);
		long endTimeQuick = System.nanoTime();
		long totalTimeQuick = (endTimeQuick - startTimeQuick)/1000000;
		System.out.println("Total time: " + totalTimeQuick);

		//call isSorted method to make sure all my arrays are sorted 
		v1 = isSorted(arrayS);
		v2 = isSorted(arrayM);
		v3 = isSorted(arrayQ);
		System.out.println("v1 is sorted: " + v1);
		System.out.println("v2 is sorted: " + v2);
		System.out.println("v3 is sorted: " + v3);
			
	}
	
	
	/**
	 * This method generates a random array of certain number of elements and returns it
	 * @return
	 * 	return an integer array with random numbers
	 */
	public static Integer[] generateArray(){
		int numOfElements = 20000;
		Integer[] myList = new Integer[numOfElements];
		Random randNum = new Random();
		for(int i = 0; i < myList.length; i++) {
			myList[i] = randNum.nextInt();
		}
		
		return myList;
	}
	
	/**
	 * This method prints the array for debug purposes 
	 * @param myList
	 * 	any array can be passed in as argument 
	 */
	 public static <T> void printMe(T[] myList){
			
			for (int i = 0; i < myList.length; i ++) {
				System.out.print(myList[i] + " ");	
			}
			
			System.out.println();
	 }
	 
	 /**
	  * This method takes the array passed as argument and checks whether it is sorted
	  * @param myList
	  * 	array of any reference type that implements comparable can be passed as argument
	  * @return
	  * 	return true if the array is sorted, false if it is not sorted
	  */
	 public static <T extends Comparable<T>> boolean isSorted(T[] myList) {
		 boolean isSorted = true;
		 for (int i = 0; i < myList.length - 1; i ++) {
			 if (myList[i].compareTo(myList[i+1]) > 0){
				 isSorted = false;
				 break;
			 }
		 }
		 
		 return isSorted;
	 }
	 
	
}
			


