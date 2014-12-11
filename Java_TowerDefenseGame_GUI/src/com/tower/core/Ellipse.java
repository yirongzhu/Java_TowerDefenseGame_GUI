package com.tower.core;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ellipse implements Renderer,Updateble {
	private int x;
	private int y;
	private int w;
	private int h;
	private Shape ellipse;
	
	public Ellipse(){
		ellipse=new Ellipse2D.Float(x,y,w,h);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderer(Graphics2D g) {
		g.draw(ellipse);
		
	}

}
