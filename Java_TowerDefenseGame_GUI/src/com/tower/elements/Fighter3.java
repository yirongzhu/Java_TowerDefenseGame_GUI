package com.tower.elements;

import java.awt.image.BufferedImage;
import com.tower.core.Fighter;
import com.tower.resource.Images;

public class Fighter3 extends Fighter{
	private BufferedImage img=Images.FIGHTER3;
	private int life=100;
	private int speed=2;
	private int defen = 30;
	private int money;
	private int x;
	private int y;
	public Fighter3( int x, int y, int life2) {
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
		return "3";
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}