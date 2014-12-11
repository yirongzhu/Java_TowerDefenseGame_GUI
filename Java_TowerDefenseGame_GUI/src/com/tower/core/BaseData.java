package com.tower.core;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import model.AddObjectCommand;
import model.AddScoreCommand;
import model.ReduceLifeCommand;
import paintobject.DrawString;
import paintobject.Line;
import paintobject.PaintImage;
import paintobject.PaintObject;

import com.tower.elements.Bang;
import com.tower.elements.ElectricBullet;
import com.tower.elements.Fighter1;
import com.tower.elements.Fighter2;
import com.tower.elements.Fighter3;
import com.tower.elements.FireBullete;
import com.tower.elements.MiniMap;
import com.tower.elements.Remark;
import com.tower.elements.Soldier1;
import com.tower.elements.Soldier2;
import com.tower.elements.Soldier8;
import com.tower.elements.SolwBullete;
import com.tower.resource.Images;
import com.uitl.button.BackButton;

public class BaseData {
	private BufferedImage lsoldier1 = Images.lSOLDIER1;
	private BufferedImage lsoldier2 = Images.lSOLDIER2;
	private Tower tower;
	private Fighter fighter;
	private Bullete bullete;	
	private boolean canBuild;
	private List<Fighter> fighters = new ArrayList<Fighter>();
	private List<Fighter> cuFighters = new ArrayList<Fighter>();
	private List<Tower> towers = new ArrayList<Tower>();
	private List<Bullete> bulletes = new ArrayList<Bullete>();
	private Stage stage;
	private Remark remark;
	private Fighter currentFighter;
	private Tower currentTower;
	private boolean isFire;
	private boolean isHit;
	private Bullete currentBullete;
	private Bang bang;
	public int choice;
	private final static String IS_ADD="isAdd";	
	private int num = 0;
	public int [][]beginPlace={
			{64,0},{0,93},{480,0}};
	List<int[][]> squareList=new ArrayList<int [][]>();
	private int[][] squares1={
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 19, 1, 6, 0, 0, 0, 0, 5, 1, 1, 1, 1,1, 6, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 12, 1, 1, 1, 1, 1, 13, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 3, 1, 1, 1, 1, 4, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0,0 } };
	private int[][] squares0 = {
			{ 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 5, 1, 1, 1, 1, 6, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0,0 },
			{ 0, 0, 3, 1, 1, 1, 1, 1, 4, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 8, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	private int [][]squares2={
			{ 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 8, 11, 11, 11, 11, 11, 7, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 6, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 23, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 7, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
	
	private MiniMap miniMap;
	private List<PaintObject> objects;

	
	private Point from1;
	private Point to1;
	private Point from2;
	private Point to2;
	private Point2D from4;
	private Point2D to4;
	
//	private Point rectX;
//	private Point rectY;
//	
	private int player;
	
	private int space;
	
	private Point2D from;
	private Point2D to;
	
	private Point2D rectX;
	private Point2D rectY;
	
	private int n;
	private int score;

	
	private String clientName;
	private ObjectOutputStream output;
	
	
	public BaseData(Stage stage2, Remark remark2, List<PaintObject> objects, 
			ObjectOutputStream output, String clientName, int player, 
			MiniMap miniMap2)
	{
		
		
		fighters = new ArrayList<Fighter>();
		cuFighters = new ArrayList<Fighter>();
		towers = new ArrayList<Tower>();
		bulletes = new ArrayList<Bullete>();
	
		score = 0;
		this.miniMap = miniMap2;
		this.player = player;
		this.objects = objects;
		n = 0;
		if(player == 2)
			space = 120;
		if(player == 1)
			space = 0;
		objects = Collections.synchronizedList(new ArrayList<PaintObject>());
		
		from1 = new Point(505, 195+space);
		to1 = new Point(555, 292+space);
		
		from2 = new Point(90, 410+space);
		to2 = new Point(330, 292+space);
		
		if(player == 1){
			from2 = new Point(90, 405);
			to2 = new Point(330, 292);
		}
		if(player == 2){
			from2 = new Point(330, 405);
			to2 = new Point(330, 292);
		}
		
		from4 = new Point2D.Double(545, 275+space);
		to4 = new Point2D.Double(555, 2850+space);

		this.output = output;
		
		stage = stage2;
		remark =remark2;
		squareList.add(squares0);
		squareList.add(squares1);
		squareList.add(squares2);
		
		from = new Point2D.Double(515, 200+space);
		to = new Point2D.Double(601.4, 275.6+space);

		rectX = new Point2D.Double(0, 0);
		rectY = new Point2D.Double(10, 10);
		
		if(player != 0){
			// write paintobject
			PaintObject object;				
		
				object = new PaintImage(Color.PINK, from, to, "./images/pvpmap.tiff");	
			
			try{
				output.writeObject(new AddObjectCommand(clientName, object));		
			}catch(Exception ex){
				System.err.println("In Client MouseHandler:");
				ex.printStackTrace();
			}
			
				Double x = 80.0;
				Double y = 300.0;
				
				Point2D	f = new Point2D.Double(x,y);
				Point2D	t = new Point2D.Double(x+100, y);
					
					// write paintobject
					PaintObject object1;	
					PaintObject object2;	
						object1 = new DrawString(Color.CYAN, from1, to1,"Player: "+clientName);
						object2 = new DrawString(Color.CYAN, from2, to2,"Player: "+clientName);
					try{
						output.writeObject(new AddObjectCommand(clientName, object1));		
						output.writeObject(new AddObjectCommand(clientName, object2));		
					}catch(Exception ex){
					System.err.println("In Client MouseHandler:");
						ex.printStackTrace();
					}
		}
		
	}
	// get map choice
	public int getChoice(){
		return choice;
	}
	public void setChoice(int choice){
		this.choice=choice;
	}
	public Bullete getCurrentBullete(){
		for (int i = 0; i < bulletes.size(); i++) {
			currentBullete = bulletes.get(i);
		}
		return currentBullete;
	}

	public void setCurrentBullete(Bullete currentBullete) {
		this.currentBullete = currentBullete;
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public List<Fighter> getFighters() {
		return fighters;
	}

	public void setFighters(List<Fighter> fighters) {
		this.fighters = fighters;
	}

	public List<Tower> getTowers() {
		return towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public List<Bullete> getBulletes() {
		return bulletes;
	}

	public void setBulletes(List<Bullete> bulletes) {
		this.bulletes = bulletes;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setRemark(Remark remark) {
		this.remark = remark;
	}

	public Fighter getCurrentFighter() {
		if(cuFighters.size()!=0){
			currentFighter=cuFighters.get(0);
			return currentFighter;
		}
		return null;
	}

	public void setCurrentFighter(Fighter currentFighter) {
		this.currentFighter = currentFighter;
	}

	public Tower getCurrentTower() {
		if(getCurrentFighter() != null){
			
			for (int i = 0; i < towers.size(); i++) {
				Rectangle r = new Rectangle(getCurrentFighter().getFighterx(),
						getCurrentFighter().getFightery(), 32, 32);
				if(r != null && towers.get(i).getBounds()!= null){
					if (towers.get(i).getBounds().intersects(r)) {
						currentTower = towers.get(i);
					}
				}
			}
			return currentTower;
		}
		return null;	
	}

	
	
	
	public void setCurrentTower(Tower currentTower) {
		this.currentTower = currentTower;
	}
	
	public void drawTowers(){
		for(int i=0;i<towers.size();i++){
			
			if(towers.get(i).getTowerx() < 481){
				Double x = 515+towers.get(i).getTowerx()*0.18;
				Double y = 195+towers.get(i).getTowery()*0.18+space;
						
				Point2D	f = new Point2D.Double(x,y);
				Point2D	t = new Point2D.Double(x+5, y+5);
				
				if(towers.get(i).getTowerType() == 1){		
					// write paintobject
					PaintObject object;				
					object = new PaintImage(Color.PINK, f, t, "./images/soldier4.png");	
					try{
						output.writeObject(new AddObjectCommand(clientName, object));	
					}catch(Exception ex){
						System.err.println("In Client MouseHandler:");
						ex.printStackTrace();
					}

				}
				if(towers.get(i).getTowerType() == 2){
					// write paintobject
					PaintObject object;				
					object = new PaintImage(Color.PINK, f, t, "./images/soldier6.png");	
					try{
						output.writeObject(new AddObjectCommand(clientName, object));		
					}catch(Exception ex){
						System.err.println("In Client MouseHandler:");
						ex.printStackTrace();
					}
				}
				if(towers.get(i).getTowerType() == 8){
					// write paintobject
					PaintObject object;				
					object = new PaintImage(Color.PINK, f, t, "./images/soldier7.png");	
					try{
						output.writeObject(new AddObjectCommand(clientName, object));		
					}catch(Exception ex){
						System.err.println("In Client MouseHandler:");
						ex.printStackTrace();
					}
				}	
			}
		}		
	}
	

	public void addBullete(int x, int y, int towerType,Fighter fighter) {
		switch (towerType) {
		case 1:
			bullete = new FireBullete(x, y,fighter);	
			break;
		case 2:  
			bullete = new ElectricBullet(x, y,fighter);
			break;
		case 8:
			bullete =new SolwBullete(x,y,fighter);
			break;
		}
		bullete.setData(this);
		bulletes.add(bullete);
		stage.addElement(bullete);

	}

	public boolean isFire(Tower currentTower) {
		 Fighter fighter2;
		if (fighters.size() != 0 && tower.getEllipse() != null) {
			
			for(int i=0;i<fighters.size();i++){
				
				Fighter fighter=fighters.get(i);
				Rectangle r = new Rectangle(fighter.getFighterx(),fighter.getFightery(), 32, 32);
				if(currentTower.getBounds().intersects(r)){
					
					if(!fighter.getIsAdd().equals(IS_ADD)){
						fighter.setIsAdd(IS_ADD);
						cuFighters.add(fighter);
					}
					
				}
			}
			for(int j=0;j<cuFighters.size();j++){
				fighter2=cuFighters.get(j);
				Rectangle r = new Rectangle(fighter2.getFighterx(),
						fighter2.getFightery(), 32, 32);
				if(currentTower.getBounds().intersects(r)){
					setCurrentFighter(fighter2);
					isFire=true;
				}else{
					
					cuFighters.remove(cuFighters.get(j));
					isFire=false;
					fighter2.setIsAdd("dd");
				}
			}

		}
		
		return isFire;

	}

	public void addTower(int type, int x, int y, int level) {

		if (type == 1 && remark.getMymoney() >= 100) {
			tower = new Soldier1(x, y);
			if(level == 2)
				tower.setImg(lsoldier1);
			remark.setMymoney(remark.getMymoney() - 100);

			}
		 else if (type == 2 && remark.getMymoney() >= 200) {
			tower = new Soldier2(x, y);
			if(level == 2)
				tower.setImg(lsoldier2);
			remark.setMymoney(remark.getMymoney() - 200);

		} else if(type==8&&remark.getMymoney()>=150){
			tower = new Soldier8(x, y);
			remark.setMymoney(remark.getMymoney() - 150);
	
		}
		else{
			JOptionPane.showMessageDialog(null, "You have not enough money");
		}
		towers.add(tower);
		tower.setData(this);
		stage.addElement(tower);	
		
		if(player !=0 ){
			drawTowers();
		}

	}


	public void removeTowerPaint(Tower thisTower){
		
			Double x = 515+thisTower.getTowerx()*0.18;
			Double y = 195+thisTower.getTowery()*0.18+space;
			
			Point2D	f = new Point2D.Double(x,y);
			Point2D	t = new Point2D.Double(x+5, y+5);

						// write paintobject
						PaintObject object;	
						object = new Line(Color.RED, f, t);
						try{
							output.writeObject(new AddObjectCommand(clientName, object));		
						}catch(Exception ex){
						System.err.println("In Client MouseHandler:");
							ex.printStackTrace();
				
	}
	}

	public void removeTower(Tower tower){	
		if(player !=0 ){

			removeTowerPaint(tower);
		}

		stage.moveElement(tower);

	}
	public void addFighter(int x, int y, String type, int life) {
		
		if (type.equals("1")) {
			fighter = new Fighter1(x, y,life);	
			remark.setClear(remark.getClear()+1);
			fighter.setData(this);
			fighters.add(fighter);
			cuFighters.add(fighter);	
			stage.addElement(fighter);
			getCurrentTower();
		} else if (type.equals("2")) {
			fighter = new Fighter2(x, y,life);
			remark.setClear(remark.getClear()+1);
			fighter.setData(this);
			fighters.add(fighter);
			cuFighters.add(fighter);
			stage.addElement(fighter);
			getCurrentTower();
		} else if(type.equals("3")){
			fighter=new Fighter3(x,y,life);
			remark.setClear(remark.getClear()+1);
			fighter.setData(this);
			fighters.add(fighter);
			cuFighters.add(fighter);
			stage.addElement(fighter);
			getCurrentTower();
		}
		else{
			System.out.println("no enemy");
		}

	}
	
//	
//	public void addMapFighter(int x, int y, String type,int life){
//
//		
//		if (type.equals("1")) {
//			fighter = new Fighter1(x, y,life);	
//			stage.addElement(fighter);
//		} else if (type.equals("2")) {
//			fighter = new Fighter2(x, y,life);
//			stage.addElement(fighter);
//		} else if(type.equals("3")){
//			fighter=new Fighter3(x,y,life);
//			stage.addElement(fighter);
//		}
//		else{
//			System.out.println("no enemy map");
//		}
//
//	}
	
//	public void removeMapFighter(int x, int y, String type,int life){
//
//		
//		if (type.equals("1")) {
//			fighter = new Fighter1(x, y,life);	
//			stage.addElement(fighter);
//		} else if (type.equals("2")) {
//			fighter = new Fighter2(x, y,life);
//			stage.addElement(fighter);
//		} else if(type.equals("3")){
//			fighter=new Fighter3(x,y,life);
//			stage.addElement(fighter);
//		}
//		else{
//			System.out.println("no enemy map");
//		}
//
//	}
	

	public int[][] getSquares() {
		return squareList.get(choice);
	}
	

	public boolean canBuild(int x, int y) {	
		x = x / 32;
		y = y / 32;
			int [][]squares=getSquares();
		
			if (squares[y][x] == 0) {
				canBuild = true;
			} 
			
			if (squares[y][x] == 0) {
				canBuild = true;
			} 
			
			
			else {
				canBuild = false;
			}
		return canBuild;	
	}

	public void moveFighter(Fighter fighter) {
		
		if(getCurrentFighter() != null)
			remark.setMymoney(remark.getMymoney() + getCurrentFighter().getFighterMoney());
		
		if (fighters.size() != 0) {
			fighters.remove(fighter);
			cuFighters.remove(fighter);
			stage.moveElement(fighter);
			moveBullete(currentBullete);
		}
		if (fighters == null) {
			moveBullete(currentBullete);
			bulletes.clear();
		}
			
		remark.setClear(remark.getClear()-1);
		remark.setScore(remark.getScore()+1);
		
		if(player != 0){
			try{
				output.writeObject(new AddScoreCommand(clientName,1,player));		
			}catch(Exception ex){
				System.err.println("In Client MouseHandler:");
				ex.printStackTrace();
			}
			
			score++;
			
			if(score == 3){
				win();
			}
		}
		
		if(player == 0){
			if(remark.getScore()==20){
				win();
			}
		}
		
	}

	public void moveBullete(Bullete bullete) {
		bulletes.remove(bullete);
		stage.moveElement(bullete);

	}

	public boolean isHit(Bullete bullete) {
				
			int power = getCurrentBullete().getPower();
			int n = getCurrentTower().getPower();
			int life = getCurrentFighter().getLife();
			int defen = getCurrentFighter().getDefen();
			switch (bullete.getBulleteType()) {
			case 1:
					isHit = true;
					int f1 = power+n - defen;
					getCurrentFighter().setLife(life - f1);
					break;
			
			case 2:
					isHit = true;
					int f2 = power+n - defen;
					getCurrentFighter().setLife(life - f2);
					break;
			case 8:
					isHit=true;
					getCurrentFighter().setSpeed(1);
					break;
			}
			return isHit;
	}
	
	
	public List<Fighter> getCurrentFighters() {
		return cuFighters;
	}
	
	public void lose(Fighter fighter) {
		fighters.clear();
		stage.moveElement(fighter);
		remark.setMylife(remark.getMylife() - fighter.getLife());
		
		if(player != 0){
			try{
				output.writeObject(new ReduceLifeCommand(clientName, fighter.getLife()));		
			}catch(Exception ex){
				System.err.println("In Client MouseHandler:");
				ex.printStackTrace();
			}
			
//			miniMap.reduceLife(fighter.getLife());
		}
		if(player == 0){
			if(remark.getMylife()<0){
				die();
			}
		}
	}
	
	public void die(){
		Lose lose=new Lose();
		BackButton b = new BackButton();

		stage.addElement(lose);
//		b.setBounds(498, 100, 135, 29);
//		stage.addElement(b);
		stage.stop();
		
	}
	
	public void win(){
		Win win=new Win();
		BackButton b = new BackButton();

		stage.addElement(win);
//		b.setBounds(498, 100, 135, 29);
//		stage.addElement(b);
		stage.stop();		
	}
}