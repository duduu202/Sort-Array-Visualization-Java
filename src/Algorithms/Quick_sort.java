package Algorithms;

import Principal.sArray;

public class Quick_sort extends SortAlgorithm{
	int begin;
	int end;
	
	@Override
	public void sort(sArray arr) {
		this.end = arr.getLength()-1;
		this.begin = 0;
		sort(arr, this.begin, this.end);
	}
	
	void sort(sArray arr, int begin, int end){
	    if (begin < end) {
	        int partitionIndex = partition(arr, begin, end);

	        sort(arr, begin, partitionIndex-1);
	        sort(arr, partitionIndex+1, end);
	    }
	}
	private int partition(sArray arr, int begin, int end) {
	    int pivot = arr.readElement(end);
	    int i = (begin-1);

	    for (int j = begin; j < end; j++) {
	        if (arr.readElement(j) <= pivot) {
	            i++;
	            
	            arr.swap(i, j);
//	            int swapTemp = arr[i];
//	            arr[i] = arr[j];
//	            arr[j] = swapTemp;
	        }
	    }
	    
	    arr.swap(i+1, end);
//	    int swapTemp = arr[i+1];
//	    arr[i+1] = arr[end];
//	    arr[end] = swapTemp;

	    return i+1;
	}
}
