package com.tower.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import paintobject.PaintObject;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import com.tower.elements.MiniMap;
import com.tower.main.Game;
import com.tower.resource.Images;
import com.tower.resource.SoundUtil;
import com.uitl.button.LoadButton;
import com.uitl.button.PVPButton;
import com.uitl.button.WelcomButton;

public class Welcome extends JPanel implements Runnable {
	public class welcomeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	private Image welcomeL = Images.LEFT;
	private Image welcomeR = Images.RIGHT;
	private Image map = Images.MAP;
	private Rectangle boundsR = ((BufferedImage) welcomeR).getRaster()
			.getBounds();
	private Rectangle boundsL = ((BufferedImage) welcomeL).getRaster()
			.getBounds();
	private WelcomButton welcomeButton = new WelcomButton();
	private LoadButton loadButton = new LoadButton();
	private Rectangle rectangel = new Rectangle(165, 320, 155, 30);
	private Rectangle load = new Rectangle(165, 375, 155, 30);
	private boolean run;
	private Thread welcomeThread;
	private MapChoise mapChoise;
	private int xL = 0;
	private int xR = 480;
	private boolean loaded;
	private Stage stage;
	private String fileName;
	private PVPButton PVPButton = new PVPButton();
	private Rectangle pvp = new Rectangle(165, 265, 155, 30);
	
	private String clientName;
	private ObjectOutputStream output;

	private List<PaintObject> objects;

	private MiniMap miniMap;
	private int player;

	private JPanel p;

	private Point first;
	private int i;
	private ObjectOutputStream outstream;
	private Game g;




	public Welcome(String client,MapChoise mapChoise2, Stage stage, int player, Game game)
			throws IOException, ClassNotFoundException {
		this.player = player;

		g=game;
		mapChoise = mapChoise2;
		setVisible(true);
		setOpaque(false);
		initButton();
		soundStart();
		loaded = false;
		this.stage = stage;
		
		clientName = client;
		stage.start();

	}

	private void initButton() {
		setLayout(null);
		welcomeButton.setBounds(148, 296, 190, 70);
		loadButton.setBounds(150, 352, 190, 70);
		PVPButton.setBounds(150, 240, 190, 70);

		this.add(welcomeButton);
		this.add(loadButton);
		this.add(PVPButton);

		welcomeButton.setVisible(false);
		loadButton.setVisible(false);
		PVPButton.setVisible(false);

		addMouseListener(mouseAdapter);
	}

	public void update() {
		xL -= 9;
		xR += 3;
		repaint();
		if (xL < -boundsL.width + 30 && xR > boundsR.width + boundsL.width - 10
				&& loaded == false) {
			run = false;
			xL += 9;
			xR -= 3;
			welcomeThread.interrupt();
			setVisible(false);
			setOpaque(false);
			mapChoise.setVisible(true);
//			playerstyle.setVisible(true);
		}

		if (xL < -boundsL.width + 30 && xR > boundsR.width + boundsL.width - 10
				&& loaded == true) {
			run = false;
			xL += 9;
			xR -= 3;
			welcomeThread.interrupt();
			setVisible(false);
			setOpaque(false);
			mapChoise.setVisible(false);
//			playerstyle.setVisible(true);

			stage.setVisible(true);
			// stage.go();
		}

	}

	public void setChoiseMap(MapChoise mapChoise) {
		this.mapChoise = mapChoise;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(map, 0, 0, null);
		g2.drawImage(welcomeL, xL, 0, null);
		g2.drawImage(welcomeR, xR, 0, null);

	}

	private MouseAdapter mouseAdapter = new MouseAdapter() {

		public void mousePressed(java.awt.event.MouseEvent e) {
			
			if (pvp.contains(e.getPoint().x, e.getPoint().y) && player !=0) {
					PVPButton.setVisible(true);
			}
			if (rectangel.contains(e.getPoint().x, e.getPoint().y) && player !=0) {
				welcomeButton.setVisible(true);
			}
			if (load.contains(e.getPoint().x, e.getPoint().y) && player == 0) {
				loadButton.setVisible(true);
				loaded = true;
				System.out.println("Load Click");
				stage.setLoaded(true);

				fileName = "c.out";
				try {
					stage.readSavedGame();

					stage.go();
					// stage.start();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		};

		public void mouseReleased(java.awt.event.MouseEvent e) {

			if (rectangel.contains(e.getPoint().x, e.getPoint().y)) {	
				if(player == 0){
					//welcomeButton.setVisible(false);
					start();
				}
				else
					JOptionPane.showMessageDialog(null,"Please disconnect the server\n ");
			}
			if (load.contains(e.getPoint().x, e.getPoint().y)) {
				if(player == 0){
					//loadButton.setVisible(false);
					start();
				}
				else
					JOptionPane.showMessageDialog(null,"Please disconnect the server\n ");
			}
			

			if (pvp.contains(e.getPoint().x, e.getPoint().y)) {
				
				if(player !=0){
					//PVPButton.setVisible(false);
					start();
				}
				else{
					JOptionPane.showMessageDialog(null,"Please connect to a server first");
				}
				
			}
			
		};

	};

	public boolean getLoaded() {
		return loaded;
	}

	public synchronized void start() {
		run = true;
		welcomeThread = new Thread(this);
		welcomeThread.start();
	}

	@Override
	public synchronized void run() {
		while (run) {
			update();
			try {
				this.wait(10);
			} catch (InterruptedException e) {
				run = false;

			}
		}

	}

	private void soundStart() {

		try {
			SoundUtil.load("sound3.mp3");
			SoundUtil.play("sound3.mp3");
		} catch (JavaLayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void restart(Stage stage2) throws IOException, ClassNotFoundException {
		
		xL = 0;
		xR = 480;
		
		mapChoise.setVisible(true);
		mapChoise.setStage(stage2);
		
//		stage = stage2;
//		stage.setVisible(true);
//		setVisible(true);
//		setOpaque(false);
		soundStart();
//		stage.start();	
	}


}
