package Algorithms;

import Principal.sArray;

public class Bogo_sort extends SortAlgorithm{
	sArray arr;
	
    void bogoSort(sArray arr)
    {
        // if array is not sorted then shuffle the
        // array again
        while (isSorted(arr) == false)
            shuffle(arr);
    }
 
    // To generate permutation of the array
    void shuffle(sArray arr)
    {
         // Math.random() returns a double positive
         // value, greater than or equal to 0.0 and
         // less than 1.0.
         for (int i=1; i <= arr.getLength()-1; i++)
             arr.swap(i, (int)(Math.random()*i));
    }
 

 
    // To check if array is sorted or not
    boolean isSorted(sArray arr)
    {
        for (int i=1; i<arr.getLength(); i++)
            if (arr.readElement(i) < arr.readElement(i-1))
                return false;
        return true;
    }
    
	@Override
	public void sort(sArray arr) {
		// TODO Auto-generated method stub
		bogoSort(arr);
		
	}

}
