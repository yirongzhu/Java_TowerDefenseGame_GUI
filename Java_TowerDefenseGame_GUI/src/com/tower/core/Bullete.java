package com.tower.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import com.tower.resource.Images;

public class Bullete implements Renderer ,Updateble{
	
	private int bulletex;
	private int bulletey;
	private int bulleteType;
	private BaseData data;
	private BufferedImage img=Images.BULLETE;
	private double a;
	private int v=10;
	private Rectangle bounds=img.getRaster().getBounds();
	private int power;
	private Fighter fighter;

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getBulletex() {
		return bulletex;
	}

	public void setBulletex(int bulletex) {
		this.bulletex = bulletex;
	}

	public int getBulletey() {
		return bulletey;
	}

	public void setBulletey(int bulletey) {
		this.bulletey = bulletey;
	}

	public int getBulleteType() {
		return bulleteType;
	}

	public void setBulleteType(int bulleteType) {
		this.bulleteType = bulleteType;
	}

	public BaseData getData() {
		return data;
	}

	public void setData(BaseData data) {
		this.data = data;
	}

	@Override
	public void update() {
		if(data.getCurrentFighters().size()!=0){
			int fx=data.getCurrentFighter().getFighterx();
			int fy=data.getCurrentFighter().getFightery();
			a=Math.atan2(fy-bulletey, fx-bulletex);
			
			if(Math.abs(fx-bulletex)>v||Math.abs(fy-bulletey)>v){
				double tx = bulletex + Math.cos(a) * v;
				double ty = bulletey + Math.sin(a) * v;
				bulletex = (int) tx;
				bulletey = (int) ty;
			}
			
			if(data.isHit(this)){
				data.moveBullete(this);
			}
		}
		
	}

	@Override
	public void renderer(Graphics2D g) {
		
		switch (bulleteType){
		case 1:
			g.drawImage(img, bulletex,bulletey,null);
			bounds.x=bulletex;
			bounds.y=bulletey;
			break;
		case 2:
			if(data.getCurrentFighter()!=null){
				int fx=data.getCurrentFighter().getFighterx();
				int fy=data.getCurrentFighter().getFightery();
				int tx=data.getCurrentTower().getTowerx();
				int ty=data.getCurrentTower().getTowery();
				g.setColor(Color.YELLOW);
				g.drawLine(tx+16, ty+16, fx+16, fy+16);
			}
			break;
		case 8:
			Rectangle r=new Rectangle(bulletex,bulletey,1,1);
			g.setColor(Color.RED);
			g.draw(r);
			break;
		}
		
	
		
	}
}