package Algorithms;

import Principal.sArray;

public class Counting_sort extends SortAlgorithm{
	static int length;
	sArray arr;
	static int tarr[];
	static int menor = 2147483647;

	@Override
	public void sort(sArray arr) {
		this.length = arr.getLength();
		this.tarr = new int[length];
		//encontra o maior valor em v[]	
		int maior = arr.readElement(0);
		for (int i = 1; i < arr.getLength(); i++) {
			if (arr.readElement(i) > maior) {
				maior = arr.readElement(i);
			}
		}
			
	//conta quantas vezes cada valor de v[] aparece
		int[] c = new int[maior+1];//+1 pois se 10 for o maior valor, ele iria apenas de 0 a 9
		for (int i = 0; i < arr.getLength(); i++) {
			c[arr.readElement(i)] += 1;
		}
			
	//acumula as vezes de cada elemento de v[] com os elementos anteriores a este
		for (int i = 1; i < c.length; i++) {
			c[i] += c[i-1];
		}
			
	//adiciona os elementos em suas posições conforme o acumulo de suas frequencias
		Integer[] b = new Integer[arr.getLength()];
		for (int i = 0; i < b.length; i++) {//percorre do fim para o inicio para manter estavel, mas não haveria problema em i = 0; i < b.lenght; i++
			b[c[arr.readElement(i)] -1] = arr.readElement(i);
			c[arr.readElement(i)]--;
		}
		
		for (int i = 0; i < arr.getLength(); i++) {
			arr.set(i, b[i]);
		}
		
	}


}

/*
package Algorithms;

import Principal.sArray;

public class Counting_sort{
	static int length;
	sArray arr;
	static int tarr[];
	static int menor = 2147483647;
	
	public Counting_sort(sArray a) {
		// TODO Auto-generated constructor stub
		this.arr = a;
		this.length = a.getLength();
		this.tarr = new int[length];
		sort(arr);
		a.verificar();
	}

	private void sort(sArray arr) {
		//encontra o maior valor em v[]	
		int maior = arr.readElement(0);
		for (int i = 1; i < arr.getLength(); i++) {
			if (arr.readElement(i) > maior) {
				maior = arr.readElement(i);
			}
		}
			
	//conta quantas vezes cada valor de v[] aparece
		int[] c = new int[maior+1];//+1 pois se 10 for o maior valor, ele iria apenas de 0 a 9
		for (int i = 0; i < arr.getLength(); i++) {
			c[arr.readElement(i)] += 1;
		}
			
	//acumula as vezes de cada elemento de v[] com os elementos anteriores a este
		for (int i = 1; i < c.length; i++) {
			c[i] += c[i-1];
		}
			
	//adiciona os elementos em suas posições conforme o acumulo de suas frequencias
		Integer[] b = new Integer[arr.getLength()];
		for (int i = b.length-1; i >= 0; i--) {//percorre do fim para o inicio para manter estavel, mas não haveria problema em i = 0; i < b.lenght; i++
			b[c[arr.readElement(i)] -1] = arr.readElement(i);
			c[arr.readElement(i)]--;
		}
		
		for (int i = 0; i < arr.getLength(); i++) {
			arr.set(i, b[i]);
		}
		
	}


}
*/
