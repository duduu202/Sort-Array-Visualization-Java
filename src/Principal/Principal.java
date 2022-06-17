/*
 * O SortArray é um projeto feito para visualizar os algoritmos de ordenação,
 * graças aos threads feitos no projeto, é possivel controlar a velocidade de
 * atualização das modificações feitas no array
 * 
 * Os algoritmos de ordenação devem ser instanciados dentro da classe sArray! Em
 * iniciarAlgoritmo();
 */

package Principal;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import GameInput.InputSet;
import GameInput.Keyboard;



public class Principal extends Canvas implements Runnable{
	
	//
	private static float velocidadeDeAtualizacao = 360*100; //Por segundo
	private int tamanhoDoArray = 300;
	//
	
	private double scaleScreen = (213/(double)tamanhoDoArray); //213
	
	
	private float adaptacao = 1;
	private static final long serialVersionUID = 988601718953775631L;
	
	private static boolean running = false;
	private Thread thread;
	private Thread thArray;
	public static int WIDTH, HEIGHT;
	static Window window;
	private static boolean terminou = false;
	private Keyboard keyboard;
	private InputSet inputSet;
	
	private boolean pause = false;
	private boolean switchPause = true;
	
	private boolean switchTick = true;
	private boolean switchAumentarOuDiminuirTick = true;
	
	Handler handler;
	sArray array;
	
	Principal(){
		
	}
	private void init() {
		
		
		inputSet = new InputSet();
		keyboard = new Keyboard(inputSet);
		
		addKeyListener(keyboard);
		
		System.out.println("init");
		WIDTH = getWidth();
		HEIGHT = getHeight();
		//array = new sArray((int)(640/3));
		array = new sArray((int)(tamanhoDoArray));
		handler = new Handler();
		
		
		handler.addObject(array);
		
		//array.testes();
		//TC = new ThControl();
		
		
		
	}
	
	public void restart() {
		thread.interrupt();
		running = false;
		handler.clearAll();
		start();
	}
	public synchronized void start() {
		System.out.println("start");
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		//array = new sArray((int)(640/3));
		array = new sArray((int)(tamanhoDoArray));
		thArray = new Thread(array);
		
		thArray.start();
		thread.start();
		System.out.println("after start");
	}
	

	
	public void run() 
	{
		System.out.println("run");

		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = velocidadeDeAtualizacao;
		double ns = 1000000000 / amountOfTicks;
		float delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		
		
		

		System.out.println("variaveis criadas (run())");
		
		
		//Gameloop
		//tick() e render() são independentes
		//tick sempre atualizará 60 vezes por segunto.
		//Render pode atualizar quantas vezes quiser sem interferir na
		//frequência de atualização do tick
		String info = "";
		while(running) {
			amountOfTicks = velocidadeDeAtualizacao;
			ns = 1000000000 / amountOfTicks;
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			//Se o lag for muito alto, zeramos o lag e diminuimos os ticks pela metade
			if(delta > velocidadeDeAtualizacao*adaptacao) {
				System.out.println("Lag multo alto! " + delta + "\nDiminundo ticks!");
				delta = 1;
				ns *= 2;
				adaptacao /= 2;
			}

			while(delta >= 1) {
				tick();
				updates++;
				delta--;
				
			}
			render();
			frames++;
			
			//Se os numero de frames por segundo forem alto, duplicamos os ticks (E se os ticks forem 60 ou mais)
			if(frames > 200 && ns > (1000000000 / amountOfTicks)) {
				System.out.println("Lag normalizando, aumentando ticks");
				ns /= 2;
				adaptacao *= 2;
			}
			
			//Leituras e Acessos serão mostrados em uma taxa mais rapida
			window.setTitulo(info + " | Leituras: " + sArray.getLeiturasFeitas()
							      + " Acessos: " + sArray.getAcessosRealizados());
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				
				//atualização de tick e render por segundo será informado no título da janela:
				info = "SortArray | Elementos: "+new StringBuilder().append("").append(sArray.getQuantidade()).toString() +" | Frames: " + new StringBuilder().append("").append(frames).toString()
						+ " Ticks: " + new StringBuilder().append("").append(updates).toString();
				

				frames = 0;
				updates = 0;
			}
		}
		
		if(!terminou)
			restart();
		
	}
	static public void setTituloP(String s) {
		window.setTitulo("SortArray | " + s);
	}
	static public void addTempTituloP(String s) {
		window.addTempTitulo(s);
	}
	
	
	private void controlesTick() {
		//Pausar = P
		if((inputSet.isPressed(80)) && switchPause) {
			if(pause)
				pause = false;
			else
				pause = true;
			
			switchPause = false;
		}
		else {
			if(!(inputSet.isPressed(80))) 
				switchPause = true;
		}
		
		//Dar 1 tick = T
		if((inputSet.isPressed(84)) && switchTick){
			handler.tick();
			switchTick = false;
		}
		else if(!(inputSet.isPressed(84))){
			switchTick = true;
		}
		
		//Aumentar ou diminuir ticks = + ou -
		if((inputSet.isPressed(61)) && switchAumentarOuDiminuirTick) {
			velocidadeDeAtualizacao *= 2;
			switchAumentarOuDiminuirTick = false;
		}
		else if((inputSet.isPressed(45)) && switchAumentarOuDiminuirTick) {
			velocidadeDeAtualizacao /= 2;
			switchAumentarOuDiminuirTick = false;
		}
		else if(!(inputSet.isPressed(45)) && !(inputSet.isPressed(61))){
			switchAumentarOuDiminuirTick = true;
		}
	}
	
	private void tick() 
	{
		//System.out.println("tick");
		controlesTick();
			
		if(!pause)
			handler.tick();
		
		//notifyAll();
	}
	
	
	
	private void render() 
	{
		//System.out.println("render");
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			
			this.createBufferStrategy(3);
			return;
		}


		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;
		// Translate used to make sure scale is centered
		g2.translate((WIDTH/2)*0, HEIGHT);
		g2.scale((WIDTH/HEIGHT)*scaleScreen, 1*scaleScreen);
		g2.translate((-WIDTH/2)*0, -HEIGHT);
		///////////////////////////////////
		//Desenhar aqui
		
		
		
		//g.setColor(Color.gy);
		g.fillRect(-100000000, -100000000, 2000000000, 1000000000);
		handler.render(g);
		
		
		if(terminou) {
			g.setColor(Color.GREEN);
		}
		else {
			g.setColor(Color.white);
		}
		
		g.drawString("Leituras: "+sArray.getLeiturasFeitas(), 100, 100);
		g.drawString("Acessos: "+sArray.getAcessosRealizados(), 100, 120);
		
		/////////////////////////////////
		g.dispose();
		bs.show();
		
		
	}
	static public void setRunning(boolean set) {
		running = set;
	}
	
	public static void main(String args[]) {
		System.out.println("main");
		window = new Window(1280, 720, "SortArray", new Principal());
		window.setTitulo("SortArray");
		
	}
	public static void terminou() {
		terminou = true;
	}
	
}
