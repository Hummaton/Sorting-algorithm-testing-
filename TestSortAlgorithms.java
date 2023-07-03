/*
 * Harjot Gill 
 * 7/12/2022
 * In this program several sorting algorithms will be tested. 
 * They will be tasked with sorting an increasing list of numbers and the time 
 * it takes for the sorting to occur will be tracked and displayed on a table 
 * 
 * All sorting algorithms are implemented into this file 
 */ 

import java.util.Random;
import java.util.*;

public class TestSortAlgorithms {

	public static void main(String[] args) { 
		System.out.println("Array size\t|\tSelection\tMerge\tQuick\tHeap\tRadix");
		System.out.println("-------------------------------------------------------------------------");
		
		int intialSortSize = 500000;
		int maxSortSize = 30000000;
		int currentSortSize = 0; 
		int count = 1;
		
		while (currentSortSize < maxSortSize) { 
			 currentSortSize = intialSortSize * count; 
			 testSort(currentSortSize);
			 count++;
		}	
	}
	
	public static void randomize(int [] points) {
		Random rand = new Random(); 
		for (int i = 0; i < points.length; i++) {
			points[i] = rand.nextInt(points.length);
		//	System.out.println(points[i]);
		}
	}

	public static void testSort(int sortSize) {
		int [] points = new int[sortSize];
		int sortingTypes = 5;
		int sortingCase = 1;
		
		System.out.print(sortSize +"\t\t");
		
		for (int i = 1; i <= sortingTypes; i++) {
			randomize(points);
			long startTime = System.currentTimeMillis( ); 
			
				switch(sortingCase) {
				  case 1:
					//SelectionSort(points);
					break;
				  case 2:
			  		mergeSort(points);
					break;
				  case 3: 
					quickSort(points);
					break;
				  case 4: 
					heapSort(points);
					break;
				  case 5:
					radixSort(points);
  					break;
				}		
			long executionTime = System.currentTimeMillis( ) - startTime;        
			System.out.print("\t" + executionTime);
			if (sortingCase == 1) {
				System.out.print("\t");
			}
			sortingCase++;
		}
		System.out.println();
	}
	
	public static void SelectionSort(int[] array) {
		int min = array[0];
		
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[minIndex])
					minIndex = j; 	
			}
			if (i != minIndex) {
				// swap values between new min index and i 
				int temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}		
		}
	}
	
	 public static void mergeSort(int[] list) {
    if (list.length > 1) {
      // Merge sort the first half
      int[] firstHalf = new int[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      int[] secondHalf = new int[secondHalfLength];
      System.arraycopy(list, list.length / 2,
        secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf);

      // Merge firstHalf with secondHalf into list
      merge(firstHalf, secondHalf, list);
    }
  }

  /** Merge two sorted lists */
  public static void merge(int[] list1, int[] list2, int[] temp) {
    int current1 = 0; // Current index in list1
    int current2 = 0; // Current index in list2
    int current3 = 0; // Current index in temp

    while (current1 < list1.length && current2 < list2.length) {
      if (list1[current1] < list2[current2])
        temp[current3++] = list1[current1++];
      else
        temp[current3++] = list2[current2++];
    }

    while (current1 < list1.length)
      temp[current3++] = list1[current1++];

    while (current2 < list2.length)
      temp[current3++] = list2[current2++];
  }
	
	public static void quickSort(int[] list) {
    quickSort(list, 0, list.length - 1);
  }

  public static void quickSort(int[] list, int first, int last) {
    if (last > first) {
      int pivotIndex = partition(list, first, last);
      quickSort(list, first, pivotIndex - 1);
      quickSort(list, pivotIndex + 1, last);
    }
  }

  /** Partition the array list[first..last] */
  public static int partition(int[] list, int first, int last) {
    int pivot = list[first]; // Choose the first element as the pivot
    int low = first + 1; // Index for forward search
    int high = last; // Index for backward search

    while (high > low) {
      // Search forward from left
      while (low <= high && list[low] <= pivot)
        low++;

      // Search backward from right
      while (low <= high && list[high] > pivot)
        high--;

      // Swap two elements in the list
      if (high > low) {
        int temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    while (high > first && list[high] >= pivot)
      high--;

    // Swap pivot with list[high]
    if (pivot > list[high]) {
      list[first] = list[high];
      list[high] = pivot;
      return high;
    }
    else {
      return first;
    }
  }
  
    public static void heapSort(int arr[])
    {
        int n = arr.length;
  
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
  
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
  
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public static void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
  
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
  
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
  
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
  
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
    
  public static void radixSort(int[] list) { 
		final int RADIXSIZE = 10; 
		
		List<Integer>[] bucket = new ArrayList[RADIXSIZE]; 
		for (int i = 0; i < bucket.length; i++) { 
			bucket[i] = new ArrayList<Integer>(); 
		} 
		
		boolean maxLength = false; 
		int temp = 0;
		int key = 1; 
		while (maxLength != true) { 
			maxLength = true; 
			for (int i = 0; i < list.length; i++) { 
				temp = list[i] / key; 
				bucket[temp % RADIXSIZE].add(list[i]);  //remainder of division determines bucket placement
				
				/*After the last digit is added from the largest value, the loop runs again 
				  and the int division remainder for the entire list should be 0 as temp is now less than 0
				  * thus the entire list should be added into bucket[0] in sorted order*/
				if (temp > 0) { 
					maxLength = false; 
				}
			}  
			// Enter buckets into subsorted list 
			int k = 0; 
			for (int b = 0; b < RADIXSIZE; b++) { 
				for (int j = 0; j < bucket[b].size(); j++) {
					list[k++] = bucket[b].get(j); 
					} 
				bucket[b].clear(); 
			} 
			// move to next digit 
			key = key * RADIXSIZE; 
		}  
	}   
	
	
}

