package com.tower.elements;

import java.awt.image.BufferedImage;

import com.tower.core.Tower;
import com.tower.resource.Images;

public class Soldier8 extends Tower{
	private int type=8;
	private BufferedImage soldier8=Images.SOLDIER8;
	private int size=200;

	public Soldier8(int x, int y) {
		setEllipsex(x);
		setEllipsey(y);
		setTowerx(x);
		setTowery(y);
		setImg(soldier8);
		setSize(size);
		setTowerType(type);
	}

}
