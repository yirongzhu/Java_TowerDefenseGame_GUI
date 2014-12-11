package com.tower.elements;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.tower.core.Renderer;
import com.tower.core.Updateble;

public class Remark implements Renderer, Updateble {
	private Rectangle top = new Rectangle(80, 0, 350, 30);
	private Rectangle bottom = new Rectangle(80, 400, 350, 30);
	private int num = 20;
	private int mylife = 100;
	private int mymoney;
	private int mywave = 20;
	private int score = 0;
	private int clear = 0;
	private int information = 0;

	private int player;

	public Remark(int player) {
		this.player = player;
		
		if(player == 0)
			mymoney = 500;
		else
			mymoney = 700;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderer(Graphics2D g) {

		g.setColor(new Color(0, 0, 0, 100));
		g.fill(top);
		g.fill(bottom);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Times New Roman", 0, 20));

		if (player == 0) {
			g.drawString("wave:", 80, 20);
			g.drawString(mywave + "", 150, 20);
		}
		g.drawString("score:", 220, 20);
		g.drawString(score + "", 290, 20);
		g.drawString("gold:", 380, 20);
		g.drawString(mymoney + "", 450, 20);
		g.setColor(Color.RED);
		g.setFont(new Font("Times New Roman", 0, 20));

		if (player == 0) {
			g.drawString("life:", 200, 420);
			g.drawString(mylife + "", 280, 420);
			
		}
		
		
		g.setColor(Color.ORANGE);
		g.drawRect(515, 152, 30, 43);
		g.drawRect(555, 152, 30, 43);
		g.drawRect(555, 248, 30, 43);
		
		// else{
		// g2d.setColor(Color.RED);
		// g2d.fillRect(80, 400, 350, 30);
		//
		//
		// g2d.drawRect (80, 400, 350, 30);
		// }

		if (getInformation() == 1) {
			g.drawString("35", 570, 32);
			g.drawString("100", 570, 50);
			g.drawString("fast", 570, 68);
		}
		if (getInformation() == 111) {
			g.drawString("45", 570, 32);
			g.drawString("150", 570, 50);
			g.drawString("fast", 570, 68);
		}
		if (getInformation() == 2) {
			g.drawString("50", 570, 32);
			g.drawString("180", 570, 50);
			g.drawString("slow", 570, 68);
		}
		if (getInformation() == 222) {
			g.drawString("60", 570, 32);
			g.drawString("230", 570, 50);
			g.drawString("slow", 570, 68);
		}
		if (getInformation() == 8) {
			g.drawString("0", 570, 32);
			g.drawString("200", 570, 50);
			g.drawString("ice", 570, 68);
		}

	}

	public int getInformation() {
		return information;
	}

	public void setInformation(int information) {
		this.information = information;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getClear() {
		return clear;
	}

	public void setClear(int clear) {
		this.clear = clear;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMylife() {
		return mylife;
	}

	public int getWave() {
		return mywave;
	}

	public void setMylife(int mylife) {
		this.mylife = mylife;
	}

	public void setWave(int mywave) {
		this.mywave = mywave;
	}

	public int getMymoney() {
		return mymoney;
	}

	public void setMymoney(int mymoney) {
		this.mymoney = mymoney;
	}

}
