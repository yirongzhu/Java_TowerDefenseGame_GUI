package com.tower.elements;

import java.awt.image.BufferedImage;
import com.tower.core.Fighter;
import com.tower.resource.Images;

public class Fighter2 extends Fighter{
	private BufferedImage img=Images.FIGHTER2;
	private int life=50;
	private int speed=4;
	private int money=50;
	private int defen = 20;
	private int x;
	private int y;
	public Fighter2( int x, int y, int life2) {
		this.x = x;
		this.y = y;
		life = life2;
		setFighterx(x);
		setFightery(y);
		setImg(img);
		setSpeed(speed);
		setLife(life);
		setDefen(defen);
		setFighterMoney(money);
	}
	
	public String getType(){
		return "2";
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}



	
}
