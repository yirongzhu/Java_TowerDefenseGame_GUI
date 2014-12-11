package com.tower.elements;

import java.awt.image.BufferedImage;

import com.tower.core.Tower;
import com.tower.resource.Images;

public class Soldier2 extends Tower {
	private static BufferedImage soldier2=Images.SOLDIER2;
	private int size=180;
	private int power = 50;
	private int towerType=2;
	public Soldier2(int x,int y){
		setTowerx(x);
		setTowery(y);
		setImg(soldier2);
		setTowerType(towerType);
		setSize(size);
		setEllipsex(x);
		setEllipsey(y);
	}
	
}
