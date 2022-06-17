package Algorithms;

import Principal.sArray;

public class Shell_sort extends SortAlgorithm{
	
	@Override
	public void sort(sArray arr) {
	    int h = 1;
	    int n = arr.getLength();
	    
	    while(h < n) {
	            h = h * 3 + 1;
	    }
	    
	    h = h / 3;
	    int c, j;
	    
	    while (h > 0) {
	        for (int i = h; i < n; i++) {
	            c = arr.readElement(i);
	            j = i;
	            while (j >= h && arr.readElement(j - h) > c) {
	            	
	            	arr.set(j, arr.readElement(j - h));
	                //nums[j] = nums[j - h];
	                j = j - h;
	            }
	            arr.set(j, c);
	            //nums[j] = c;
	        }
	        h = h / 2;
	    }
	}

}
