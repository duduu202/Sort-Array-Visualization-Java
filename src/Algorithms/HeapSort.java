package Algorithms;

import Principal.sArray;

public class HeapSort extends SortAlgorithm{
	
	@Override
	public void sort(sArray arr) {
		heapSort(arr);
	}
	
	static void heapify(sArray a, int length, int i) {
	    int leftChild = 2*i+1;
	    int rightChild = 2*i+2;
	    int largest = i;

	    // if the left child is larger than parent
	    if (leftChild < length && a.readElement(leftChild) > a.readElement(largest)) {
	        largest = leftChild;
	    }

	    // if the right child is larger than parent
	    if (rightChild < length && a.readElement(rightChild) > a.readElement(largest)) {
	        largest = rightChild;
	    }

	    // if a swap needs to occur
	    if (largest != i) {
	    	a.swap(i, largest);
	        heapify(a, length, largest);
	    }
	}

	public static void heapSort(sArray a) {
	    if (a.getLength() == 0) return;

	    // Building the heap
	    int length = a.getLength();
	    // we're going from the first non-leaf to the root
	    for (int i = length / 2-1; i >= 0; i--)
	        heapify(a, length, i);

	    for (int i = length-1; i >= 0; i--) {
	    	a.swap(0, i);
//	        int temp = a.readElement(0);
//	        a[0] = a[i];
//	        a[i] = temp;

	        heapify(a, i, 0);
	    }
	}


}
