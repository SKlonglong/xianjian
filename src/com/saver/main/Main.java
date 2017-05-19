package com.saver.main;

import javax.swing.JFrame;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String GAME_NAME = "仙剑奇侠传";

	public Main() {
		setSize(800, 600);
		setTitle(GAME_NAME);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		MainPanel panel = new MainPanel();
		main.add(panel);
		main.addKeyListener(panel);
		Thread thread = new Thread(panel);
		thread.start();
		main.setVisible(true);
	}

}
