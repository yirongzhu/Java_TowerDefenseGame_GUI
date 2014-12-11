package com.tower.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import com.tower.resource.Images;

public class Tower implements Renderer, Updateble{
	private int towerx;
	private int towery;
	private Shape ellipse;
	private Fighter currentFighter;
	private BaseData data;
	private BufferedImage img;
	private BufferedImage[] towerImg = new BufferedImage[71];
	private int i;
	private boolean isFire;
	private Color color = Color.yellow;
	private Stage stage;
	private int ellipsex;
	private int ellipsey;
	private int size;
	private Rectangle bounds;
	private int towerType;
	private boolean timer;
	private int power=0;
	private int time=0;
	private int level = 0;
	
	public int getTowerType() {
		return towerType;
	}

	public void setTowerType(int towerType) {
		this.towerType = towerType;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}


	public int getEllipsex() {
		return ellipsex;
	}

	public void setEllipsex(int ellipsex) {
		this.ellipsex = ellipsex;
	}

	public int getEllipsey() {
		return ellipsey;
	}

	public void setEllipsey(int ellipsey) {
		this.ellipsey = ellipsey;
	}
	

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Fighter getCurrentFighter() {
		return currentFighter;
	}

	public BaseData getData() {
		return data;
	}

	public Shape getEllipse() {
		return ellipse;
	}
	
	public BufferedImage getImg() {
		return img;
	}

	public int getTowerx() {
		return towerx;
	}

	public int getTowery() {
		return towery;
	}
	public boolean isFire() {
		return isFire;
	}

	public void setFire(boolean isFire) {
		this.isFire = isFire;
	}

	public void setCurrentFighter(Fighter currentFighter) {
		this.currentFighter = currentFighter;
	}

	public void setData(BaseData data) {
		this.data = data;
	}

	public void setEllipse(Shape ellipse) {
		
		this.ellipse = ellipse;
	}

	public void setImg(BufferedImage img) {
		for(int i=0;i<71;i++){
			towerImg[i]=img.getSubimage(32*i, 0, 32, 32);
		}
		
	}

	public void setTowerx(int towerx) {
		this.towerx = towerx;
	}

	public void setTowery(int towery) {
		this.towery = towery;
	}
	

	@Override
	public void renderer(Graphics2D g) {
		
		ellipse=new Ellipse2D.Float(towerx-(size/3),towery-(size/3),size,size);
		bounds=ellipse.getBounds();
		g.drawImage(towerImg[i], towerx, towery, null);
//		g.setColor(color);
		g.setColor(Color.YELLOW);
		g.draw(ellipse);
		
	}
	@Override
	public void update() {

		if(data != null){
			if(data.isFire(this)){
				i++;
				i=i%towerImg.length;
				if(timer()){
					data.addBullete(towerx+16, towery+16,towerType,data.getCurrentFighter());	
				}	
			}
		}
		
	}
	
	public boolean timer(){
		time++;
		if(time%125==5){
			time=0;
			timer=true;
		}
		else {
			timer=false;
		}
		return timer;
	}
	
	
}