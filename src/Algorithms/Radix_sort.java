package Algorithms;

import Principal.sArray;

public class Radix_sort extends SortAlgorithm{
	@Override 
	public void sort(sArray arr){
		//O(n*d)
		for(int digit = 0; digit < 3; digit ++){
			int power = (int) Math.pow(10, digit+1);
			
			int z[][] = new int[arr.getLength()][10];
			int n[] = new int[10];

			for(int i = 0; i < arr.getLength(); i ++){
				int num = arr.readElement(i);
				z[n[(num%power)/(power/10)]][(num%power)/(power/10)] = num;
				n[(num%power)/(power/10)]++;
			}
			int c = 0;
			for(int i = 0; i < 10; i ++){
				
				for(int j = 0; j < arr.getLength(); j ++){
					if(j < n[i]){
						arr.set(c, z[j][i]);
						c++;
					}else{break;}
				}
			}
			
		}
	}
	@Override
	public String getNome() {
		return "Radix sort";
	}
}
