package com.tower.elements;

import java.awt.image.BufferedImage;
import com.tower.core.Fighter;
import com.tower.resource.Images;

public class Fighter1 extends Fighter{
	private BufferedImage img=Images.FIGHTER1;
	private int life=20;
	private int speed=6;
	private int money=20;
	private int defen = 10;
	private int x;
	private int y;
	public Fighter1( int x, int y, int life2) {
		this.x = x;
		this.y = y;
		life = life2;
		setFighterx(x);
		setFightery(y);
		setImg(img);
		setLife(life);
		setSpeed(speed);
		setDefen(defen);
		setFighterMoney(money);
		
		
		
		
	}
	public String getType(){
		return "1";
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	

	



	
}
