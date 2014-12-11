package com.tower.main;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import paintobject.PaintObject;

import com.tower.core.MapChoise;
import com.tower.core.Renderer;
import com.tower.core.Lose;
import com.tower.core.Stage;
import com.tower.core.Updateble;
import com.tower.core.Welcome;
import com.tower.core.Win;
import com.tower.elements.MiniMap;
import com.uitl.button.LoadButton;
import com.uitl.button.PVPButton;
import com.uitl.button.WelcomButton;

public class Game implements Renderer, Updateble {
	private Stage stage;
	public JFrame frame;
	private Welcome welcome;

	private MapChoise mapChoise;

	private String clientName;
	private ObjectOutputStream output;

	private List<PaintObject> objects;

	private MiniMap miniMap;
	private int player;

	private JPanel p;

	private Point first;
	private int i;
	private ObjectOutputStream outstream;
	private int mapChoice;
	
	
	private Rectangle rectangel = new Rectangle(165, 320, 155, 30);
	private Rectangle load = new Rectangle(165, 375, 155, 30);
	private Rectangle pvp = new Rectangle(165, 265, 155, 30);
	
	private PVPButton PVPButton = new PVPButton();
	private WelcomButton welcomeButton = new WelcomButton();
	private LoadButton loadButton = new LoadButton();
	private boolean loaded;
	private String fileName;
	
	private int time;

	public Game(String client, ObjectOutputStream outstream, String response)
			throws IOException, ClassNotFoundException {

		clientName = client;
	
		time = 0;
		if (response.equals("accept 0"))
			player = 1;
		else if (response.equals("accept 1"))
			player = 2;
		else
			player = 0;

		this.outstream = outstream;

		objects = Collections.synchronizedList(new ArrayList<PaintObject>());
		
		
		miniMap = new MiniMap(objects, player);
		stage = new Stage(objects, clientName, outstream, miniMap, player, this);
		
		
		mapChoise = new MapChoise(stage, player);

		welcome = new Welcome(clientName,mapChoise, stage, player,this);
		output = outstream;

		welcome.setPreferredSize(new Dimension(640, 480));
		stage.setPreferredSize(new Dimension(640, 480));
		mapChoise.setPreferredSize(new Dimension(640, 480));
		

	
		frame = new JFrame();
		frame.setTitle("Brain Protection");
		frame.setSize(640, 480);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(20, 100);
		frame.add(welcome);
		frame.add(stage);
		frame.add(mapChoise);
		
		
		if(player == 2){
			frame.setLocation(500, 100);
		}
	}

	public void stop() {
		Lose lose = new Lose();
		stage.addElement(lose);
		stage.stop();
	}

	public void start() {		
		frame.setVisible(true);
//		stage.start();
	}

	public void restart() throws IOException, ClassNotFoundException {

		frame.remove(stage);
		stage = new Stage(objects, clientName, outstream, miniMap, player, this);
		stage.setPreferredSize(new Dimension(640, 480));
		frame.add(stage);
		welcome.setVisible(true);
		welcome.restart(stage);
		
	
		
	}
		
	

	@Override
	public void renderer(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public void setLife(int life) {
		miniMap.setLife(life);
	}

	public void update(List<PaintObject> asList) {

		objects = asList;
		if (objects.size() > 0) {
			miniMap.setObjects(objects);
		}
		frame.repaint();
	}

	public void win() {
		Win win = new Win();
		stage.addElement(win);
		stage.stop();
	}

	public void updateScore(int s1, int s2) {

		miniMap.setP1Score(s1);
		miniMap.setP2Score(s2);

		if (player == 1) {
			if (s2 > 2) {
				stop();
			}
		}
		if (player == 2) {

			if (s1 > 2) {
				stop();
			}
		}
	}


	public void addFighter(int fighterType, int p) {
		int cost = 0;
		if(player != p)
			stage.addFighter(fighterType);
		
		if(player == p){
			if(fighterType == 1)
				cost = 10;
			if(fighterType == 2)
				cost = 15;
			if(fighterType == 2)
				cost = 20;
			stage.setMoney(cost);
		}
	}
	

	
}
