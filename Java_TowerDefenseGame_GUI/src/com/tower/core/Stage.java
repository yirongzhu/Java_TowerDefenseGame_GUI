package com.tower.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.SliderUI;

import model.AddObjectCommand;
import paintobject.Line;
import paintobject.PaintImage;
import paintobject.PaintObject;
import javazoom.jl.decoder.JavaLayerException;

import com.tower.elements.Grass;
import com.tower.elements.Menue;
import com.tower.elements.MiniMap;
import com.tower.elements.Mountain;
import com.tower.elements.Remark;
import com.tower.elements.Road;
import com.tower.elements.Soldier1;
import com.tower.main.Game;
import com.tower.main.Start;
import com.tower.resource.Images;
import com.tower.resource.PropertiesUtil;
import com.tower.resource.SoundUtil;
import com.uitl.button.WelcomButton;

public class Stage extends JPanel implements Runnable {
	public Game game;
	private JButton jbshow;
	private JFrame jframe = new JFrame();
	private BufferedImage lsoldier1 = Images.lSOLDIER1;
	private BufferedImage lsoldier2 = Images.lSOLDIER2;
	private boolean run;
	private List<Renderer> renderers = new CopyOnWriteArrayList<Renderer>();
	private List<Updateble> updateble = new CopyOnWriteArrayList<Updateble>();
	private Thread currentThread;
	private Rectangle currentRectangle;
	private SoundUtil soundUtil;
	private Soldier1 soldier;
	private Rectangle attention = new Rectangle(498, 0, 700, 700);
	private Rectangle back = new Rectangle(498, 100, 135, 29);
	private Rectangle soldier1 = new Rectangle(515, 152, 30, 43);
	private Rectangle soldier2 = new Rectangle(555, 152, 30, 43);
	private Rectangle soldier3 = new Rectangle(595, 152, 30, 43);
	private Rectangle soldier4 = new Rectangle(515, 200, 30, 43);
	private Rectangle soldier5 = new Rectangle(555, 200, 30, 43);
	private Rectangle soldier6 = new Rectangle(595, 200, 30, 43);
	private Rectangle soldier7 = new Rectangle(515, 248, 30, 43);
	private Rectangle soldier8 = new Rectangle(555, 248, 30, 43);
	private Rectangle soldier9 = new Rectangle(595, 248, 30, 43);
	private Rectangle soldier10 = new Rectangle(515, 296, 30, 43);
	private Rectangle soldier11 = new Rectangle(555, 296, 30, 43);
	private Rectangle soldier12 = new Rectangle(595, 296, 30, 43);
	private Rectangle go = new Rectangle(515, 369, 100, 16);
	private Rectangle pause = new Rectangle(595, 425, 20, 20);
	private Rectangle goOn = new Rectangle(520, 425, 20, 20);
	private Rectangle fast = new Rectangle(552, 425, 22, 20);
	private List<Tower> buildinglist = new ArrayList<Tower>();
	private int fightertype;
	private BaseData baseData;
	private int squaresSize;
	private int xR;
	private int yR;
	private boolean drawTowerTools = false;
	private int number = 0;
	private Timer timer;
	private Remark remark;
	private int wave = 0;
	private PropertiesUtil proer;
	private String waves;
	public Integer choice;
	private MapChoise mapChoise;
	private Grass grass;
	private Road road;
	private Mountain moutain;
	private Menue menue;
	private Welcome welcome;
	private String fileName;
	private int money1;
	private int waves1;
	private int score1;
	private int life1;
	private Rectangle save = new Rectangle(498, 70, 135, 29);
	private boolean saved;
	private boolean loaded;
	private MiniMap miniMap;
	private List<PaintObject> objects;
	private Point2D from1;
	private Point2D from2;
	private Point2D from3;
	private Point2D to1;
	private Point2D to2;
	private Point2D to3;
	private String clientName;
	private ObjectOutputStream output;
	private int player;
	private boolean pvp;
	private List<Tower> towers;
	private int space;

	public List<Renderer> getRenderers() {
		return renderers;
	}

	public void setChoice(int choice) {
		
		baseData.setChoice(choice);
		
//		grass.setBaseData(baseData);
//		road.setBaseData(baseData);
	}
	

	public void setRenderers(List<Renderer> renderers) {
		this.renderers = renderers;
	}

	public List<Updateble> getUpdateble() {
		return updateble;
	}

	public void setUpdateble(List<Updateble> updateble) {
		this.updateble = updateble;
	}

	public void setObjects(List<PaintObject> objs) {
		this.objects = objs;
	}

	public void setClientName(String n) {
		this.clientName = n;
	}

	public void setOutput(ObjectOutputStream out) {
		this.output = out;
	}

	public void setMiniMap(MiniMap m) {
		this.miniMap = m;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public void setGame(Game g) {
		this.game = g;
	}

	public Stage(List<PaintObject> objects, String name,
			ObjectOutputStream outstream, MiniMap miniMap2, int player, Game g)
			throws IOException, ClassNotFoundException {

		towers = new ArrayList<Tower>();
		setGame(g);
		setPlayer(player);
		setObjects(objects);
		objects = Collections.synchronizedList(new ArrayList<PaintObject>());
		setClientName(name);
		setOutput(outstream);
		setMiniMap(miniMap2);
		
		
//		this.player =player;
//		game = g;
//		this.objects = objects;
//		clientName = name;
//		output = outstream;
//		miniMap =miniMap2;

		space = 80;
		//
		// game = game2;
		remark = new Remark(player);
		baseData = new BaseData(this, remark, objects, output, clientName,
				player, miniMap);
		grass = new Grass(baseData);
		road = new Road(baseData);
		moutain = new Mountain();
		menue = new Menue();
		saved = false;

		pvp = false;

		fileName = "c.out";

		this.setVisible(false);
		this.addElement(grass);
		this.addElement(road);
		this.addElement(moutain);
		this.addElement(menue);
		this.addElement(remark);
		this.addElement(baseData);
		
		
//
//		if (player != 0)
			this.addElement(miniMap);

		init();

		fightertype = 0;

	}
	
	
	public void reset(int c){	
		
		
		
		
		moveElement(grass);
		moveElement(road);
		moveElement(moutain);
		moveElement(menue);
		moveElement(remark);
		moveElement(baseData);
		
		
		remark = new Remark(player);	
		baseData = new BaseData(this, remark, objects, output, clientName,
				player, miniMap);
		baseData.setChoice(c);

		
		grass = new Grass(baseData);
		grass.setBaseData(baseData);
		
	
		road = new Road(baseData);
		moutain = new Mountain();
		menue = new Menue();
		
		addElement(grass);
		addElement(road);
		addElement(moutain);
		addElement(menue);
		addElement(remark);
		addElement(baseData);
		if(player !=0)
			addElement(miniMap);
		saved = false;
	
	}

	public void setLoaded(boolean l) {
		loaded = l;
	}

	public void setPVP(boolean l) {
		pvp = l;
	}

	public void pvpModeSetting() {
		baseData.setChoice(1);
		setChoice(1);
	}

	public void readSavedGame() throws IOException, ClassNotFoundException {
		// read file
		// fileName = "c.out";

		ArrayList<Fighter> currentFighters;
		try {

			String s = "d";
			saved = true;
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);

			// Object o11 = ois.readObject();
			// baseData = (BaseData) o11;

			Object o10 = ois.readObject();
			int map = (Integer) o10;
	

			baseData.setChoice(map);
			setChoice(map);

			Object o0 = ois.readObject();
			int size = (Integer) o0;

			// towers(type,level,x,y)
			for (int i = 0; i < size; i++) {
				Object o1 = ois.readObject();
				Object o2 = ois.readObject();
				Object o3 = ois.readObject();
				Object o4 = ois.readObject();

				int type = (Integer) o1;
				int level = (Integer) o2;
				int x = (Integer) o3;
				int y = (Integer) o4;

				baseData.addTower(type, x, y, level);

			}

			// user info(money, score, waves,life)
			Object o5 = ois.readObject();
			Object o6 = ois.readObject();
			Object o7 = ois.readObject();
			Object o8 = ois.readObject();

			money1 = (Integer) o5;
			score1 = (Integer) o6;
			waves1 = (Integer) o7;
			life1 = (Integer) o8;

			remark.setMymoney(money1);
			remark.setScore(score1);
			remark.setWave(waves1);
			remark.setMylife(life1);


			Object o9 = ois.readObject();
			int size1 = (Integer) o9;

			// Enemy(type,x,y,life)

			List<Fighter> fighters = new ArrayList<Fighter>();

			for (int i = 0; i < size1; i++) {
				Object o1 = ois.readObject();
				Object o2 = ois.readObject();
				Object o3 = ois.readObject();
				Object o4 = ois.readObject();

				String type = (String) o1;
				int x = (Integer) o2;
				int y = (Integer) o3;
				int life = (Integer) o4;


				if (remark.getClear() == 0) {
					start();
					drawTowerTools = true;
					proer = new PropertiesUtil();
					waves = proer.getProperty(wave);
					timer = new Timer(800, null);
					timer.addActionListener(actionListener);
					timer.start();
					drawTowerTools = false;
				}
				baseData.addFighter(x, y, type, life);
				
			}

			
			File f = new File(fileName);
			f.createNewFile();

		} catch (FileNotFoundException ex) {
			// create and write
			System.out.println("Unable to open file '" + fileName + "'");
			File f = new File(fileName);
			f.createNewFile();
			System.out.println("create '" + fileName + "'");

		} catch (IOException ex) {

			System.out.println("Error reading file '" + fileName + "'");

		}
	}

	public void init() {
		jbshow = new JButton("show");

		jbshow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jbshow) {
					jframe.setVisible(false);
				} else {
					jframe.setVisible(false);
				}
			}
		});
		squaresSize = Data.squaresSize;
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);

	}

	public void addElement(Object element) {
		
	

		if (element instanceof Renderer) {
			renderers.add((Renderer) element);
		}
		if (element instanceof Updateble) {
			updateble.add((Updateble) element);
		}

	}

	public void addElement(Object el, int index) {
		if (el instanceof Renderer) {
			renderers.add(index, (Renderer) el);
		}
		if (el instanceof Updateble) {
			updateble.add(index, (Updateble) el);
		}

	}

	public void moveElement(Object element) {
		if (element instanceof Renderer) {
			renderers.remove((Renderer) element);
		}
		if (element instanceof Updateble) {
			updateble.remove((Updateble) element);
		}

	}

	public void setBaseData(BaseData baseData) {
		this.baseData = baseData;

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		for (Renderer r : renderers) {
			r.renderer(g2);

		}
		g2.setColor(Color.white);
	}

	public boolean isRun() {
		return run;
	}
	
	public void setRun(boolean t){
		run = t;
	}

	public synchronized void start() {
		run = true;
		currentThread = new Thread(this);
		currentThread.start();
	}

	@Override
	public synchronized void run() {
		while (run) {
			update();
			try {
				this.wait(100);
			} catch (InterruptedException e) {
				run = false;
			}
		}

	}

	public void fighterStop() {
		if (baseData.getFighters() == null) {
			stop();
		}

	}

	public synchronized void stop() {
		if (!run) {
			throw new RuntimeException("wrong");
		}

		run = false;
		currentThread.interrupt();

		// for(int i=0;i<baseData.getFighters().size();i++){
		// moveElement(baseData.getFighters().get(i));
		// }
		// for(int j=0;j<baseData.getCurrentFighters().size();j++){
		// moveElement(baseData.getCurrentFighters().get(j));
		// }
		// for(int k=0;k<baseData.getTowers().size();k++){
		// moveElement(baseData.getTowers().get(k));
		// }
		//
		// //baseData.setCurrentFighter();
		// baseData.setFighters(new ArrayList<Fighter>());
		// baseData.setTowers(new ArrayList<Tower>());
		// wave = 0;
		// remark.setMymoney(500);
		// remark.setScore(0);
		// remark.setWave(20);
		// remark.setMylife(100);

	}

	private void update() {
		
		for (Updateble u : updateble) {
			u.update();
		}
		//
		// if(towers.size()>0)
		// baseData.drawTowers();
		repaint();
	}

	public void setChoiseMap(MapChoise mapChoise) {
		this.mapChoise = mapChoise;
	}

	public void setWelcome(Welcome welcome) {
		this.welcome = welcome;
	}

	private MouseAdapter mouseAdapter = new MouseAdapter() {
		private int type;
		private int x;
		private int y;
		private int focusX;
		private int focusY;

		public void mousePressed(MouseEvent e) {
			Point p = e.getPoint();
			List<Fighter> fighters = new ArrayList<Fighter>();
			// List<Tower> towers = new ArrayList<Tower>();
			Rectangle r;
			Rectangle t;
			int x = 0;
			int y = 0;
			int speed = 0;
			int life = 0;
			int defen = 0;
			fighters = baseData.getFighters();
			towers = baseData.getTowers();

			for (int i = 0; i < fighters.size(); i++) {
				t = fighters.get(i).getBounds();
		
				

				speed = fighters.get(i).getSpeed();
				life = fighters.get(i).getLife();
				defen = fighters.get(i).getDefen();

				if (t != null) {
					if (t.contains(p)) {
						JOptionPane.showMessageDialog(null, "Speed: " + speed
								+ "\n Life: " + life + "\n defence:" + defen);
					}
				}
			}
			Object[] options = { "upgrade", "sale" };
			for (int i = 0; i < towers.size(); i++) {
				r = towers.get(i).getBounds();

				if (r.contains(p)) {
					int n = JOptionPane.showOptionDialog(null,
							"upgrade or sale tower? upgrade 100, sale 100:\n",
							"tower information",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, options,
							options[1]);

					if (towers.get(i).getTowerType() == 1
							&& towers.get(i).getLevel() == 0) {
						remark.setInformation(1);
					} else if (towers.get(i).getTowerType() == 1
							&& towers.get(i).getLevel() == 1) {
						remark.setInformation(111);
					} else if (towers.get(i).getTowerType() == 2
							&& towers.get(i).getLevel() == 0) {
						remark.setInformation(2);
					} else if (towers.get(i).getTowerType() == 2
							&& towers.get(i).getLevel() == 1) {
						remark.setInformation(222);
					} else {
						remark.setInformation(8);
					}
					if (n == 0) {
						if (towers.get(i).getLevel() == 0) {
							if (towers.get(i).getTowerType() == 1) {
								if (remark.getMymoney() >= 100) {
									remark.setMymoney(remark.getMymoney() - 100);
									towers.get(i).setLevel(
											towers.get(i).getLevel() + 1);
									towers.get(i).setSize(
											towers.get(i).getSize() + 50);
									towers.get(i).setPower(
											towers.get(i).getPower() + 10);
									towers.get(i).setImg(lsoldier1);
									remark.setInformation(111);
								} else {
									JOptionPane.showMessageDialog(null,
											"You don't have enough money");
									remark.setInformation(1);
								}

							} else if (towers.get(i).getTowerType() == 2) {
								if (remark.getMymoney() >= 100) {
									remark.setMymoney(remark.getMymoney() - 100);
									towers.get(i).setLevel(
											towers.get(i).getLevel() + 1);
									towers.get(i).setSize(
											towers.get(i).getSize() + 50);
									towers.get(i).setPower(
											towers.get(i).getPower() + 10);
									towers.get(i).setImg(lsoldier2);
									remark.setInformation(222);
								} else {
									JOptionPane.showMessageDialog(null,
											"You don't have enough money");
									remark.setInformation(2);
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"This tower cannot upgrade.");
								remark.setInformation(8);
							}
						} else {
							JOptionPane
									.showMessageDialog(null,
											"You only can upgrade one time for each tower.");
						}
					} else if (n == 1) {
						remark.setMymoney(remark.getMymoney() + 100);
						baseData.removeTower(towers.get(i));
						List<Tower> towers1 = baseData.getTowers();
						towers.remove(i);
						towers.clear();
						baseData.setTowers(towers1);
					} else {

					}
				}

				}
			

			if (soldier1.contains(p)) {
				drawTowerTools = true;
				type = 1;
				// baseData.addTower(type, p.x, p.y, 1);
				// remark.setInformation(1);

			} else if (soldier2.contains(p)) {
				drawTowerTools = true;
				type = 2;
				// baseData.addTower(type, p.x, p.y, 1);
				// remark.setInformation(2);

			} else if (soldier8.contains(p)) {
				drawTowerTools = true;
				type = 8;
				// baseData.addTower(type, p.x, p.y, 1);
				// remark.setInformation(8);

			} else if (back.contains(p)){
				if(player == 0)
					restart();

				// try {
				// game.restart();
				// } catch (IOException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// } catch (ClassNotFoundException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				//

			} else if (save.contains(p)) {

				if (player == 0) {
					List<Fighter> f = new ArrayList<Fighter>();
					f = baseData.getFighters();
					String s = "s";

					stop();
					// write
					try {
						Object[] opt = { "Yes", "No" };
						int n = JOptionPane.showOptionDialog(null,
								"Save game?", "Save Game",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, opt,
								opt[0]);

						if (n == 0) {
							FileOutputStream fos = new FileOutputStream(
									fileName);
							ObjectOutputStream oos = new ObjectOutputStream(fos);

							// oos.writeObject(baseData);

							oos.writeObject(baseData.getChoice());

							buildinglist = baseData.getTowers();
							int size = buildinglist.size();
							
							oos.writeObject(size);

							// towers(type,level,x,y)
							for (int i = 0; i < size; i++) {
								oos.writeObject(buildinglist.get(i)
										.getTowerType());
								oos.writeObject(buildinglist.get(i).getLevel());
								oos.writeObject(buildinglist.get(i).getTowerx());
								oos.writeObject(buildinglist.get(i).getTowery());
							}

							// user info(money, score, waves,life)
							oos.writeObject(remark.getMymoney());
							oos.writeObject(remark.getScore());
							oos.writeObject(remark.getWave());
							oos.writeObject(remark.getMylife());

							fighters = baseData.getFighters();
							int size1 = fighters.size();
					
							oos.writeObject(size1);

							// Enemy(type,x,y,life)
							for (int i = 0; i < size1; i++) {

								String type = ""
										+ baseData.getFighters().get(i)
												.getType();
								oos.writeObject(type);
								oos.writeObject(baseData.getFighters().get(i)
										.getFighterx());
								oos.writeObject(baseData.getFighters().get(i)
										.getFightery());
								oos.writeObject(baseData.getFighters().get(i)
										.getLife());
							}

							oos.close();
							System.exit(0);

						} else {
							start();
						}

					} catch (FileNotFoundException ex) {
						System.out.println("Unable to open file '" + fileName
								+ "'");
					} catch (IOException ex) {
						System.out.println("Error writing file '" + fileName
								+ "'");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Can save only in single mode");
				}
			} else if (go.contains(p)) {

				go();
			} else if (pause.contains(p)) {
				stop();

			} else if (goOn.contains(p)) {
				start();

			} else if (fast.contains(p)) {
				start();
				start();
			}
		}

		public void mouseReleased(java.awt.event.MouseEvent e) {
			Point p = e.getPoint();
			
			focusX = (p.x) / squaresSize * squaresSize;
			focusY = (p.y) / squaresSize * squaresSize;

			if (drawTowerTools == true && p.x < 480) {
				if (baseData.canBuild(focusX, focusY)) {
					baseData.addTower(type, p.x-15, p.y-15, 1);
					remark.setInformation(type);
					drawTowerTools = false;
				}

				// baseData.getTower().setColor(new Color(0, 0, 0, 0));
			}
		};

		public void mouseDragged(java.awt.event.MouseEvent e) {
			Point p = e.getPoint();

			// focusX = p.x / squaresSize * squaresSize;
			// focusY = p.y / squaresSize * squaresSize;
			// if (drawTowerTools == true) {
			// if (baseData.canBuild(focusX, focusY)) {
			//
			// baseData.getTower().setTowerx(focusX);
			// baseData.getTower().setTowery(focusY);
			// }
			// }
		};

		public void mouseMoved(java.awt.event.MouseEvent e) {
		};

	};

	private void restart() {

		moveElement(grass);
		moveElement(road);
		moveElement(moutain);
		moveElement(menue);
		moveElement(remark);
		moveElement(baseData);
		
		
		currentThread = new Thread();
		currentThread.start();
		run = true;
		stop();
		// stop();

		try {
			game.restart();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}// TODO Auto-generated method stub

	}

	public void go() {

		fightertype = 0;
		if (player == 0) {
			if (remark.getClear() == 0) {
				wave++;
				remark.setWave(remark.getWave() - 1);
				remark.setNum(remark.getNum() - 1);
				drawTowerTools = true;
				fightertype = 0;
				proer = new PropertiesUtil();
				waves = proer.getProperty(wave);
				timer = new Timer(800, null);
				timer.addActionListener(actionListener);
				timer.start();
				drawTowerTools = false;
				

				if (loaded) {

					for (int i = 0; i < baseData.getFighters().size(); i++) {
						baseData.getFighters().get(i).update();

					}
				}
			}
		}

	}

	public void setSoundUtil(SoundUtil soundUtil) {
		this.soundUtil = soundUtil;

	}

	public void setSoldier(Soldier1 soldier) {
		this.soldier = soldier;

	}

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (waves != null && waves.length() != 0) {

				if (!saved) {
					String type = waves.substring(0, 1);
					int life = 0;
					if (type.equals("1")) {
						life = 20;
					}
					if (type.equals("2")) {
						life = 50;
					}
					if (type.equals("3")) {
						life = 100;
					}
					if(baseData.choice != 4)
					baseData.addFighter(
							baseData.beginPlace[baseData.choice][0],
							baseData.beginPlace[baseData.choice][1], type, life);
				}
				if (number++ == waves.length()) {
					number = 0;
					timer.stop();
				}
				saved = false;
				waves = waves.substring(1);
			}
		}
	};

	public void setRemark(Remark remark2) {
		this.remark = remark2;
	}

	public void addFighter(int fighterType) {

		String type = "" + fighterType;
		int life = 0;
		if (type.equals("1")) {
			life = 20;
		}
		if (type.equals("2")) {
			life = 50;
		}
		if (type.equals("3")) {
			life = 100;
		}
		
		baseData.addFighter(baseData.beginPlace[baseData.choice][0],
				baseData.beginPlace[baseData.choice][1], type, life);
	}
	
	public int getChoice(){
		return choice;
	}

	public void setMoney(int cost) {
		remark.setMymoney(remark.getMymoney()-cost);
	}

}
