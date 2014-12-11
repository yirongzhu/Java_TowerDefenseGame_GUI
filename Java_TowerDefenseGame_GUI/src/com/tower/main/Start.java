package com.tower.main;

import java.io.IOException;
import javax.swing.JFrame;

public class Start extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Game game = new Game ("single", null, "s");
		game.start();
	}
}
