package Algorithms;

import Principal.sArray;

public class Bitonic_sort {
	public Bitonic_sort(sArray a) {
		sort(a, a.getLength(), 1);
		a.verificar();
	}
    /* The parameter dir indicates the sorting direction,
    ASCENDING or DESCENDING; if (a[i] > a[j]) agrees
    with the direction, then a[i] and a[j] are
    interchanged. */
 void compAndSwap(sArray a, int i, int j, int dir)
 {
     if ( (a.readElement(i) > a.readElement(j) && dir == 1) ||
          (a.readElement(i) < a.readElement(j) && dir == 0))
     {
         // Swapping elements
    	 a.swap(i, j);
     }
 }

 /* It recursively sorts a bitonic sequence in ascending
    order, if dir = 1, and in descending order otherwise
    (means dir=0). The sequence to be sorted starts at
    index position low, the parameter cnt is the number
    of elements to be sorted.*/
 void bitonicMerge(sArray a, int low, int cnt, int dir)
 {
     if (cnt>1)
     {
         int k = cnt/2;
         for (int i=low; i<low+k; i++)
             compAndSwap(a,i, i+k, dir);
         bitonicMerge(a,low, k, dir);
         bitonicMerge(a,low+k, k, dir);
     }
 }

 /* This function first produces a bitonic sequence by
    recursively sorting its two halves in opposite sorting
    orders, and then  calls bitonicMerge to make them in
    the same order */
 void bitonicSort(sArray a, int low, int cnt, int dir)
 {
     if (cnt>1)
     {
         int k = cnt/2;

         // sort in ascending order since dir here is 1
         bitonicSort(a, low, k, 1);

         // sort in descending order since dir here is 0
         bitonicSort(a,low+k, k, 0);

         // Will merge whole sequence in ascending order
         // since dir=1.
         bitonicMerge(a, low, cnt, dir);
     }
 }

 /*Caller of bitonicSort for sorting the entire array
   of length N in ASCENDING order */
 void sort(sArray a, int N, int up)
 {
     bitonicSort(a, 0, N, up);
 }
}
