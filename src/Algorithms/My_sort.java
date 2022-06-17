package Algorithms;

import Principal.sArray;

public class My_sort {
	public My_sort(sArray a) {
		sort(a);
		a.verificar();
	}
	
	void sort(sArray a) {
		int n = a.getLength();
		int media = n/2;
		int temp = 0;  
		for(int i=0; i < n; i++){  
			for(int j=1; j < (n-i); j++){
				int j1 = a.readElement(j-1);
				int j2 = a.readElement(j);
				if(j1 > j2){
					//if(j1 > media)
						a.swap(j-1, j);

				} 
			} 
		}
	}
}
