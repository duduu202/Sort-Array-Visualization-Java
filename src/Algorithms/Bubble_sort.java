package Algorithms;

import Principal.sArray;

public class Bubble_sort extends SortAlgorithm{
	sArray arr;
	int length = 0;
	public Bubble_sort(sArray a){
		System.out.println("Bubble_sort");
		this.arr = a;
		this.length = arr.getLength();
	}
	
	@Override
	public void sort(sArray arr) {
		//int contif = 0;
		int n = length;   
		for(int i=0; i < n; i++){  
			for(int j=1; j < (n-i); j++){
				//contif++;
				if(arr.readElement(j-1) > arr.readElement(j)){  
					//swap elements  
					arr.swap(j-1, j);
				}  
			} 
		}
		//System.out.println("Quantidade de if's utilizados: "+ contif);
	}
	@Override
	public String getNome() {
		return "Bubble sort";
	}
}





/*BACKUP
 * 
 * package Algorithms;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import Objects.GameObject;
import Objects.ObjectId;
import Principal.sArray;

public class Bubble_sort extends GameObject{
	sArray arr;
	int length = 0;
	public Bubble_sort(sArray a){
		super(ObjectId.Algorithm);
		System.out.println("Bubble_sort");
		this.arr = a;
		this.length = arr.getLength();


	}
	
	int i = 0;
	int j = 0;
	int contador = 0;
	int ordenado = 0;
	public void sort() {
		

		
		if(length == ordenado) {
			arr.verificar();
			arr.shuffle();
			arr.verificar();
			ordenado = 0;
		}
		else {
			//ordenado = 0;
			
			//System.out.println(contador);
//			for(int i = 0; i<lenght; i++){
//				for(int j = 0; j<lenght-1; j++){
					if(arr.readElement(j) > arr.readElement(j + 1)){
						arr.swap(j+1, j);
						//if(j != 0)
						//	j = j+1;
						
						contador = 0;
					}
					else {
						if(j>2)
						//arr.swap(j-2, j);
						
						contador++;
					}
					
					if(contador > length) {
						
						//arr.shuffle();
						arr.verificar();
					}
//				}
//			}
			

			
			if(j<length-2-ordenado) {
				j++;
				
			}
			else {
				ordenado++;
				//ordenado++;
				j = 0;
				if(i<length-1) {
					i++;
				}
				else {
					i = 0;
				}
			}
			
			
		}
		
		//ordenado = 0;

	}
	int n = 0;
	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		if(n == 1) {
			sort();
			n = 0;
		}
		n++;

	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
*/
