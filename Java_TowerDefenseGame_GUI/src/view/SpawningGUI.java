package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import model.AddFighterCommand;
import model.AddObjectCommand;
import model.AppendMessageCommand;
import paintobject.DrawString;
import paintobject.PaintObject;

import com.tower.core.MapChoise;
import com.tower.core.Renderer;
import com.tower.core.Stage;
import com.tower.core.Tower;
import com.tower.core.Updateble;
import com.tower.core.Welcome;
import com.tower.elements.MiniMap;
import com.tower.elements.Soldier1;
import com.tower.elements.Soldier2;
import com.tower.elements.Soldier8;
import com.tower.main.Game;
import com.tower.resource.Images;

public class SpawningGUI extends JFrame{


	public class e3ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub
			if (player != 0) {
				 System.out.println("enemy 3");
				 try {
					
				  output.writeObject(new AddFighterCommand(clientName,3, player));
				 } catch (IOException e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();
				 }
			}

		}

	}

	public class e2ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (player != 0) {
				 System.out.println("enemy 2");
				 try {
				  output.writeObject(new AddFighterCommand(clientName,2, player));
				 } catch (IOException e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();
				 }
			}

		}

	}

	public class e1ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (player != 0) {
				 System.out.println("enemy 1");
				 try {
					
				  output.writeObject(new AddFighterCommand(clientName,1, player));
				 } catch (IOException e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();
				 }
			}

		}

	}

	private JPanel spawningPanel;
	private String clientName;
	private int player;
	private List<PaintObject> objects;
	private ObjectOutputStream output;
	private BufferedImage img1 =Images.FIGHTER1;
	private BufferedImage img2 =Images.FIGHTER2;
	private BufferedImage img3 =Images.FIGHTER3;

	
	private List<Renderer> renderers;
	private List<Updateble> updateble;
	
	private BufferedImage map1 = Images.PVPMAP;

	public SpawningGUI(String client, ObjectOutputStream outstream, String response) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(null);
		userLogin();

		if(response.equals("accept 0"))
			player = 1;
		else if (response.equals("accept 1"))
			player = 2;
		else
			player = 0;
		
		renderers = new CopyOnWriteArrayList<Renderer>();
		updateble = new CopyOnWriteArrayList<Updateble>();
		
		output = outstream;
		clientName = client;

		setTitle("Spawning Unit (" + clientName + ")");
		setSize(250, 90);

		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(20, 590);
		if(player == 2){
			setLocation(660, 590);
		}
		
		

	}

	// user login
	private void userLogin() {

		spawningPanel = new JPanel();
		spawningPanel.setLayout(null);
		spawningPanel.setSize(240, 400);
		

		JButton e1 = new JButton(new ImageIcon(img1.getSubimage(24, 20, 45, 45)));
		e1.setSize(40,40);
		e1.setLocation(20, 10);
		e1.setBackground(Color.CYAN);
		
		
		JButton e2 = new JButton(new ImageIcon(img2.getSubimage(24, 20, 45, 45)));
		e2.setSize(40,40);
		e2.setLocation(80, 10);
		e2.setBackground(Color.PINK);
		
		
		JButton e3 = new JButton(new ImageIcon(img3.getSubimage(26, 26, 45, 45)));
		e3.setSize(40,40);
		e3.setLocation(140, 10);
		e3.setBackground(Color.RED);
		
		spawningPanel.add(e1);
		spawningPanel.add(e2);
		spawningPanel.add(e3);
	

//		Image newimg = map1.getScaledInstance(250, 220,  java.awt.Image.SCALE_SMOOTH);  
//		ImageIcon newIcon = new ImageIcon(newimg);  
//
//		JButton m = new JButton(newIcon);
//		m.setSize(250, 220);
//		m.setLocation(0, 60);
//		
//		spawningPanel.add(m);
		
		this.add(spawningPanel);
		
		e1.addActionListener(new e1ButtonListener());
		e2.addActionListener(new e2ButtonListener());
		e3.addActionListener(new e3ButtonListener());
		
	}
	
	
	
	
	public void addElement(Object element) {

		if (element instanceof Renderer) {
			renderers.add((Renderer) element);
		}
		if (element instanceof Updateble) {
			updateble.add((Updateble) element);
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
	
	public void removeTower(int type, double x, double y, int player2) {
	
	
	if (player != player2) {
		if(type == 1){
			Tower t = new Soldier1(500,500);
			moveElement(t);
		}
		if(type == 2){
			Tower t = new Soldier2(500,500);
			moveElement(t);
		}
		if(type == 8){
			Tower t = new Soldier8(500,500);
			moveElement(t);
		}
	}
	

}

public void addTower(int type, double x, double y, int player2) {
	
	if (player != player2) {
		System.out.println("addTower");
		if(type == 1){
			System.out.println("addTower1");
			Tower t = new Soldier1(50,50);
			addElement(t);
		}
		if(type == 2){
			System.out.println("addTower2");
			Tower t = new Soldier2(30,30);
			addElement(t);
		}
		if(type == 8){
			System.out.println("addTower3");
			Tower t = new Soldier8(0,0);
			addElement(t);
		}
	}
}

	






}