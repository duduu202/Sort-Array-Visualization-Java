package Principal;
/*
 * Problema: Muitos estaticos!
 */
import java.awt.Color;
import java.awt.Graphics;

import Objects.GameObject;

import java.util.concurrent.ThreadLocalRandom;

import Algorithms.Bead_sort;
import Algorithms.Bitonic_sort;
import Algorithms.Bogo_sort;
import Algorithms.Bubble_sort;
import Algorithms.Counting_sort;
import Algorithms.HeapSort;
import Algorithms.My_sort;
import Algorithms.Quick_sort;
import Algorithms.Radix_sort;
import Algorithms.Shell_sort;

public class sArray extends GameObject implements Runnable{
	public static ThControl TC =  new ThControl();;
	
	private static int[] array; //O array que será corrigido
	
	//Corrigir
	private static int[] corrigirArr; //Um array de referência para corerção
	private static int[] certo; // 1 = certo, 0 = errado
	
	//Tamanho da tela
	int h = Principal.HEIGHT;
	int w = Principal.WIDTH;
	
	//Configuração das barras
	private int width = 5; //1
	private int contraste = 3;
	static private int read = -1;
	static private int swap = -1;
	static private int swap2 = -1;
	
	//Contadores de leitura e acesso
	static private int contRead = 0;
	static private int contAcess = 0;
	
	static private int quantidade;
	/*BACKUP
 	private int width = 1;
	private int contraste = 1;
	private int read = 4;
	private int swap = 5;
	 */
	
	private static boolean verificar = false;
	
	sArray(int n){
		super();
		System.out.println("sArray");
		array = new int[n];
		corrigirArr = new int[n];
		certo = new int[n];
		this.quantidade = n;
		buildArray(array);
		buildArray(corrigirArr);
		
		
	}
	public static int getQuantidade() {
		return quantidade;
	}
	public static String getEficiencia(int n, int leitura, int acessos) {
		int r = (leitura+acessos)/2;
		return "";
	}
	
	private void buildArray(int[] array) {
		// 1, 2, 3 ... n
		for(int i = 0; i < array.length; i++) {
			array[i] = i+1;
		}
	}
	public static void piorCenario() {
		System.out.println("Carregando PIOR cenario...");
		Principal.addTempTituloP(" | Carregando PIOR cenario...");
		for(int i = 0; i < array.length; i++) {
			set(i, array.length-i);
			//int temp = array[i];
			//array[i] = array[randomNum];
			//array[randomNum] = temp;
		}
		Principal.addTempTituloP("");
		contRead = 0;
		contAcess = 0;
	}
	//Mistura o array
	public static void shuffle() {
		System.out.println("Embaralhando...");
		Principal.addTempTituloP(" | Embaralhando...");
		for(int i = 0; i < array.length; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(1, array.length);
			swap(i, randomNum);
			//int temp = array[i];
			//array[i] = array[randomNum];
			//array[randomNum] = temp;
		}
		Principal.addTempTituloP("");
		contRead = 0;
		contAcess = 0;
	}
	public static void fastShuffle() {
		System.out.println("Embaralhando...");
		Principal.addTempTituloP(" | Embaralhando...");
		for(int i = 0; i < array.length; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(1, array.length);
			int temp = array[i];
			array[i] = array[randomNum];
			array[randomNum] = temp;
		}
		Principal.addTempTituloP("");
		contRead = 0;
		contAcess = 0;
	}
	
	public int getLength() {
		return array.length;
	}
	
	public static int readElement(int n) {
		waitForTick();
		contRead = contRead + 1;
		int temp = array[n];
		read = n;
		swap = -1;
		swap2 = -1;
		
		return temp;
	}
	
	//Trocar a pelo b
	public static void swap(int a, int b) {
		//Esperar! :D
		waitForTick();
		
		contAcess = contAcess + 2;
		int temp = array[b];
		array[b] = array[a];
		array[a] = temp;
		swap = a;
		swap2 = b;
		read = -1;
	}
	public static void set(int posicao, int numero) {
		waitForTick();
		
		contAcess = contAcess + 1;
		array[posicao] = numero;
		swap = posicao;
		swap2 = -1;
	}
	
	//Deve ser chamado após a ordenação para mostrar quais estão corretos (verde)
	//e quais estão errados (vermelho)
	public void verificar() {
		read = 0;
		swap = 0;
		verificar = true;
		verificando();

	}
	
	/*
	 * O waitoForTick é o metodo mais importante dessa classe.
	 * Ele acessa o objeto ThControl (TC) para "pedir permissao"
	 * para o código continuar, ele espera até que a classe
	 * Principal chame o notify() do TC
	 * 
	 * Essa é a magica que faz com que possamos visualizar os
	 * algoritmos de ordenação em ação!
	 */
	private static void waitForTick() {
		synchronized(TC) {
			try {
				TC.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void verificando() {
		for(int i = 0; i < array.length; i++) {
			//
			waitForTick();
			//
			if(array[i] == corrigirArr[i]) {
				certo[i] = 1;
				
			}
			else {
				certo[i] = 0;
			}
			
		}
		
		printResultado();
	}
	
	public static void printResultado() {
		System.out.println("\nLeituras feitas: " + contRead);
		System.out.println("Modificações realizadas: " + contAcess);
	}
	
	
	//O tick atualiza em uma taxa definida na classe Principal
	@Override
	public void tick() {
		//O TC.notify(); "dá permissão"
		//às funções do sArray para que a taxa de atualização
		//seja igual ao tick
//		synchronized (TC) {
//			TC.notifyAll();
//		}
		synchronized (sArray.TC) {
			sArray.TC.notify();
		}
	}
	//Desenha as barras
	@Override
	public void render(Graphics g) {
		for(int i = 0; i < array.length; i++) {
			if(verificar) {
				if(certo[i] == 1) {
					g.setColor(Color.green);
				}
				else if(certo[i] == 0){
					g.setColor(Color.red);
				}
			}
			else {
				if(i == read)
					g.setColor(Color.green);
				else if(i == swap)
					g.setColor(Color.red);
				else if(i == swap2)
					g.setColor(Color.red);
				else
					g.setColor(Color.white);
			}

			
			g.fillRect(i*(width+1), // +1 para ter um espaço entre barras
					((array.length - array[i])*contraste)+(h-array.length*contraste), //Ajustar y de acordo com o tamanho do retangulo
					width, 
					(int)array[i]*contraste);
			
			
			
			//System.out.println(array[i]);
		}

		
	}
	/*
	 * O runnable não tem acesso ao swap que esta sendo utilizado, pois é como se o run
	 * estivesse alterando variaveis e metodos de um NOVO sArray.
	 * Por isso, os metodos e variaveis devem ser estaticos...
	 * Ou eu acho uma outra maneira de programar isso...
	 */
	@Override
	public void run() {
		//fastShuffle();
		shuffle();
		//piorCenario();
		iniciarAlgoritmo();
		verificar();
		
		Principal.terminou();
	}
	
	/*
	* Declare seu algoritmo e chame o metodo sort(this)
	* Use o sArray
	* 
	* Veja os exemplos
	*/
	//Com a classe abastrata SortAlgorithm, não é necessário isso. Só enivar o algoritmo e chamar o metodo .sort() da abstrata ja funcionaria
	public void iniciarAlgoritmo() {
		
//		Bubble_sort s1 = new Bubble_sort(this); s1.sort(this);
		
		shuffle();
		Principal.addTempTituloP("Bubble sort");
		Bubble_sort s1 = new Bubble_sort(this); s1.sort(this);
		
		shuffle();
		Principal.addTempTituloP("Shell sort");
		Shell_sort s2 = new Shell_sort(); s2.sort(this); 
		
		shuffle();
		Principal.addTempTituloP("Counting sort");
		Counting_sort s3 = new Counting_sort(); s3.sort(this);
		
		shuffle();
		Principal.addTempTituloP("Radix_sort");
		Radix_sort s4 = new Radix_sort(); s4.sort(this);
		
		shuffle();
		Principal.addTempTituloP("Quick sort");
		Quick_sort s5 = new Quick_sort(); s5.sort(this);
		
		shuffle();
		Principal.addTempTituloP("Heap Sort");
		HeapSort s6 = new HeapSort(); s6.sort(this);
		
//		Bogo_sort s7 = new Bogo_sort(); s7.sort(this);
		
//		while(true) {
//			s2 = new Shell_sort(); s2.sort(this);
//			fastShuffle(); verificar = false;
//		}
		
		
		//Bead_sort a = new Bead_sort(this); a.sort(this); //Não funciona
		//Bitonic_sort s7 = new Bitonic_sort(this); s7.sort(this); //Não funciona
	}
	public static int getLeiturasFeitas() {
		return contRead;
	}
	public static int getAcessosRealizados() {
		return contAcess;
	}
	

}
