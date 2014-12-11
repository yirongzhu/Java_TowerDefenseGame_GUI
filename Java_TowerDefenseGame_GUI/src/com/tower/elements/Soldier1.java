package com.tower.elements;


import java.awt.image.BufferedImage;
import com.tower.core.Tower;
import com.tower.resource.Images;

public class Soldier1 extends Tower{
	
	private BufferedImage soldier1=Images.SOLDIER1;
	private int size=100;
	private int type=1;
	private int power=35;
	public Soldier1(int x,int y){
		setEllipsex(x);
		setEllipsey(y);
		setTowerx(x);
		setTowery(y);
		setImg(soldier1);
		setSize(size);
		setTowerType(type);
		
		
	}



	

	

	
	
	
	

	
	
	
 
}
 
