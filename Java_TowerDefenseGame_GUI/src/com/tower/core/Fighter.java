package com.tower.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.tower.elements.Remark;
import com.tower.elements.Soldier1;
import com.tower.resource.PropertiesUtil;
import com.tower.resource.SoundUtil;

public class Fighter implements Renderer ,Updateble{
	
	private BufferedImage img;
	private int fighterx;
	private int fightery;
	private int life;
	private int defen;
	private int money;
	private BufferedImage[][] imgs=new BufferedImage[4][5]; 
	private int i;
	private int j=0;
	private BaseData data;
	private int direction;
	private int speed;
	private int[][] squares;
	private Rectangle bounds;
	private String isAdd="dd";
	private int fighterMoney;
	private int squaresSize;
	private int a= 0;
	public int getFighterMoney() {
		return fighterMoney;
	}

	public void setFighterMoney(int fighterMoney) {
		this.fighterMoney = fighterMoney;
	}

	public String getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		for(int i=0;i<imgs.length;i++){
			for(int j=0;j<imgs[i].length;j++){
				imgs[i][j]=img.getSubimage(j*32,i*32,32,32);
			}
		}
	}

	public int getSpeed() {
		return speed;
	}

	public int getFighterx() {
		return fighterx;
	}

	public void setFighterx(int fighterx) {
		this.fighterx = fighterx;
	}

	public int getFightery() {
		return fightery;
	}

	public void setFightery(int fightery) {
		this.fightery = fightery;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public int getDefen() {
		return defen;
	}

	public void setDefen(int defen) {
		this.defen = defen;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	@Override
	public void update() {

		j++;
		j=j%imgs[i].length;
		int x = (fighterx+16)/ 32;
		int y = (fightery+16)/ 32;
		
		
		squares = data.getSquares();
		Random rand = new Random();
		int r = rand.nextInt(2);
		if (direction == 3) {
			fightery += speed;
			i=0;
		} else if (direction == 1) {
			fighterx += speed;
			i=1;
		} else if (direction == 2) {
			fightery -= speed;
			i=2;
		} else if (direction == 4) {
			fighterx -= speed;
			i=3;
		}
		else if (direction == 5) {
			fightery += speed;
			i=0;
		}
		
		if(squares[y][x] == 3)
		{
			direction=1;
		}
		else if(squares[y][x] ==1)
		{
			direction=1;
		}
		else if(squares[y][x] ==2)
		{
			direction=5;
		}
		else if(squares[y][x] ==23)
		{
			direction=2;
		}
		else if(squares[y][x] ==22)
		{
			direction=2;
		}
		else if(squares[y][x] == 11){
			direction = 4;
		}
		
		else if ( squares[y][x] == 5||squares[y][x] == 19) {
			direction = 1;
			
		} else if (squares[y][x] == 4) {
			direction = 2;
			
		} else if (squares[y][x] == 6 || squares[y][x] == 8 || squares[y][x] == 13) {
			
			direction = 3;
			
		} else if (squares[y][x] == 7) {
			direction = 4;
			
		} else if (squares[y][x] == 9) {
			data.lose(this);
			}
		else if (squares[y][x] == 12) {
			if(getType().equals("1")){
				direction = 1;
			}
			else
				direction = 2;
				
			
//			if(r %2 == 1 && a == 2){
//				System.out.println("move right");
//				direction = 1;
//				a = 0;
//			}
//			else{
//				System.out.println("move up");
//				a ++;
//				direction = 2;
//			}
		} 
		else{
			System.out.println("Some thing wrong");
		}
		if (life <= 0) {
			data.moveFighter(this);

		}
		
	}
	
	public void setData(BaseData data) {
		this.data = data;
	}
	

	

	@Override
	public void renderer(Graphics2D g) {
		g.drawImage(imgs[i][j], fighterx, fightery, null);
		bounds=new Rectangle(fighterx,fightery,32,32);
		Rectangle r1 = new Rectangle(fighterx+5, fightery-3, getFighterMoney(), 5);
		Rectangle r2 = new Rectangle(fighterx+5, fightery-3, life, 5);
		g.setColor(Color.YELLOW);
		g.draw(r1);
		g.fill(r2);
	}

	public String getType() {
		return this.getType();
	}

}

