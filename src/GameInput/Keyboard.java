package GameInput;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import handler;


/*
 * A classe Keyboard � focada em coletar os inputs dado pelo usu�rio e 
 * armazen�los no InputSet atrav�s da classe Principal.
 * 
 * Assim, qualquer outra classe que queira verificar se uma tecla foi pressionada,
 * basta perguntar para a classe Principal, desde que, esse input configurado no
 * enum Input e um else if nessa classe para verificar e adicionar esse input. Ou
 * usar o "KeyCode" da tecla se voc� souber o c�digo :v
 * 
 * Atualiza��o: Com o uso do c�digo, o enum "Input" se torna inutil, e eu n�o precisarei das dezenas de if e else...
 * ent�o me livrei do enum Input
 */
public class Keyboard extends KeyAdapter {
	private static InputSet IS;
	
	
    public Keyboard(InputSet IS) {
    	System.out.println("Keyboard");
        this.IS = IS;
    }

	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
//        if (key == KeyEvent.VK_LEFT) {
//        	inputHandler.add(Input.LEFT);
//        } else if (key == KeyEvent.VK_RIGHT) {
//        	inputHandler.add(Input.RIGHT);
//        } else if (key == KeyEvent.VK_UP) {
//        	inputHandler.add(Input.UP);
//        } else if (key == KeyEvent.VK_DOWN) {
//        	inputHandler.add(Input.DOWN);
//        } else if (key == KeyEvent.VK_SHIFT) {
//        	inputHandler.add(Input.SHIFT);
//        }
        
        IS.add(key);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
//        if (key == KeyEvent.VK_LEFT) {
//        	inputHandler.remove(Input.LEFT);
//        } else if (key == KeyEvent.VK_RIGHT) {
//        	inputHandler.remove(Input.RIGHT);
//        } else if (key == KeyEvent.VK_UP) {
//        	inputHandler.remove(Input.UP);
//        } else if (key == KeyEvent.VK_DOWN) {
//        	inputHandler.remove(Input.DOWN);
//        } else if (key == KeyEvent.VK_SHIFT) {
//        	inputHandler.remove(Input.SHIFT);
//        }
        
		IS.remove(key);
	}
}
