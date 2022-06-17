package Principal;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window 
{
	JFrame frame = new JFrame("0");
	private String tempTitle = "";
	public Window(int w, int h, String title, Principal game) 
	{
		//Informações básicas para a criação da janela
		System.out.println("window");
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		//label();
		
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		
		frame.setVisible(true);
		

		
		game.start();
		
	}
	public void label() {
		JLabel label = new JLabel("teste", JLabel.CENTER);
		label.setBounds(120, 130, 360, 400);
		label.setBackground(Color.BLACK);
		label.setText("TESTEEEE LABELLL");

		frame.add(label);
	}
	
	public void setTitulo(String title) {
		frame.setTitle(title +" - "+ tempTitle);
	}
	public void addTempTitulo(String tempTitle) {
		this.tempTitle = tempTitle;
	}
}
