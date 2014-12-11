package com.tower.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.tower.elements.Remark;
import com.tower.main.Game;
import com.tower.resource.Images;

public class MapChoise extends JPanel {
	private Image choiseMap = Images.MapChoise;
	private JRadioButton jrbMap1, jrbMap2, jrbMap3;
	private JButton okButton;
	private Stage stage;
	private Image Map1Button = Images.MAP1;
	private Image Map2Button = Images.MAP2;
	private Image Map3Button = Images.MAP3;
	private Image Go = Images.go;

	private int choice;

	public MapChoise(Stage stage2, int player) {

		stage = stage2;
		ImageIcon icon1 = new ImageIcon(Map1Button);
		ImageIcon icon2 = new ImageIcon(Map2Button);
		ImageIcon icon3 = new ImageIcon(Map3Button);
		ImageIcon go = new ImageIcon(Go);
		okButton = new JButton("");
		okButton.setIcon(go);
		okButton.setFocusPainted(false);
		okButton.setContentAreaFilled(false);
		okButton.setBorderPainted(false);
		okButton.setBorder(null);

		// okButton.setBounds(140,20,33,26);
		okButton.setSize(200, 50);
		okButton.setLocation(200, 300);
		okButton.setVisible(true);
		setLayout(null);
		okButton.addMouseListener(mouseAdapter);

		jrbMap1 = new JRadioButton("");
		jrbMap2 = new JRadioButton("");
		jrbMap3 = new JRadioButton("");

		jrbMap1.setSize(200, 50);
		jrbMap1.setLocation(40, 50);
		jrbMap2.setSize(200, 50);
		jrbMap2.setLocation(240, 50);
		jrbMap3.setSize(200, 50);
		jrbMap3.setLocation(440, 50);

		jrbMap1.setIcon(icon1);
		jrbMap2.setIcon(icon2);
		jrbMap3.setIcon(icon3);

		jrbMap1.setBorderPainted(false);
		jrbMap1.setBorder(null);
		jrbMap1.setFocusPainted(false);
		jrbMap1.setContentAreaFilled(false);

		jrbMap2.setBorderPainted(false);
		jrbMap2.setBorder(null);
		jrbMap2.setFocusPainted(false);
		jrbMap2.setContentAreaFilled(false);

		jrbMap3.setBorderPainted(false);
		jrbMap3.setBorder(null);
		jrbMap3.setFocusPainted(false);
		jrbMap3.setContentAreaFilled(false);
		this.add(jrbMap1);
		this.add(jrbMap2);
		this.add(jrbMap3);
		this.add(okButton);
		ButtonGroup group = new ButtonGroup();

		group.add(jrbMap1);
		group.add(jrbMap2);
		group.add(jrbMap3);

		// jrbMap1.setSelected(true);
		JTextArea text = new JTextArea(
				"Tower Defense Rules\n"
						+ "The goal of the game is to kill all the enemies by strategically placing towers on the map.\n"
						+ " A player wins when they have defeated all twenty waves of enemies;\n"
						+ "they lose if too many enemies make it to the end of the path.\n"
						+ "\n"
						+ "The player can choose one of three maps to play on.\n"
						+ "Each map has a predefined path that the enemy will follow.\n"
						+ "Before the game starts, the player can purchase towers from the \n"
						+ " menu on the side and place them anywhere on the map, \n"
						+ "as long as they do not block the path of the enemy.\n"
						+ "Towers can be upgraded or sold before pressing the GO button,\n"
						+ "and after the wave of enemies has been defeated.\n"
						+ "The game can be paused and continued at any point in the game.\n"
						+ "The speed of the game can also be increased and decreased as the player chooses.\n"
						+ "A player can pause the game and save it at any time from the gameplay area,\n"
						+ "and load the game upon start up.\n"
						+ "\n"
						+ "A player can see an enemy’s speed, life, and defense count by clicking on the enemy.\n"
						+ "Clicking on a tower will show the tower’s stats in the upper right hand corner of the screen,\n"
						+ "and also show the option of upgrading or selling the tower.\n"
						+ ""
						+ "A player can choose to play a multiplayer game from the main menu.\n"
						+ "The objective of the game remains the same.\n"
						+ "The two players share the same health counter.\n"
						+ "When an enemy reaches the end of the path on one map,\n"
						+ "it diminishes the health counter for both players.\n"
						+ "Players can send any positive amount of currency to each other.\n"
						+ "Players can communicate with each other over a chat room displayed in the game play area.\n"
						+ "\n"
						+ "Single Mode: \n" + "Get Score to 20 to win \n"
						+ "PVP Mode: \n" + "Get Score to 3 to win \n" +

						"" + "" + "");
		text.setEditable(false);
		JPanel J = new JPanel();
		J.add(text);
		setBounds(200, 200, 600, 400);
		setLayout(null);
		JScrollPane jsp = new JScrollPane(text);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(40, 110, 560, 190);
		add(jsp);
		text.setBackground(Color.WHITE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jrbMap1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (jrbMap1.isSelected()) {

					choice = 1;
					stage.setChoice(choice - 1);
				}
			}
		});
		jrbMap2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (jrbMap2.isSelected()) {
					choice = 2;
					stage.setChoice(choice - 1);
				}
			}
		});
		jrbMap3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (jrbMap3.isSelected()) {
					choice = 3;
					stage.setChoice(choice - 1);

				}
			}
		});

		if (player != 0) {
			jrbMap1.setVisible(false);
			jrbMap3.setVisible(false);

		}

		this.setVisible(false);

	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setChoice(int i) {
		choice = i;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(choiseMap, 0, 0, null);
	}

	private MouseAdapter mouseAdapter = new MouseAdapter() {

		public void mousePressed(java.awt.event.MouseEvent e) {
			if (choice == 0) {
				JOptionPane.showMessageDialog(null,
						"Please choose one map to begin the game");
			} else {
				if (e.getSource() == okButton) {

					stage.setVisible(true);
					setVisible(true);
					setOpaque(false);

					stage.start();

					setVisible(false);
					stage.setChoice(choice - 1);
					stage.reset(choice - 1);
					stage.setVisible(true);
					setChoice(choice - 1);
				}
			}
		};
	};

	public int getChoice() {

		return choice;
	}

}
