package com.tower.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.List;

import paintobject.PaintImage;
import paintobject.PaintObject;

import com.tower.core.Renderer;
import com.tower.core.Updateble;
import com.tower.resource.Images;


public class MiniMap implements Renderer, Updateble {
		private Rectangle top = new Rectangle(80,0,350,30);
		private Rectangle bottom = new Rectangle(80,400,350,30);

		private List<PaintObject> objects;
		private int life;
		
		private int s1;
		private int s2;
		private int player;


		public MiniMap(List<PaintObject> objects,int player) {
			this.objects = objects;
			life = 200;
			this.player = player;
			s1 = 0;
			s2 = 0;

		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
		
		public void setObjects(List<PaintObject> objects){
			this.objects = objects;
		}



		@Override
		public void renderer(Graphics2D g) {
			// TODO Auto-generated method stub
			g.setColor(new Color(0,0,0,100));
			g.fill(top);
			g.fill(bottom);
			g.setColor(Color.MAGENTA);
			g.setFont(new Font("Times New Roman", 0, 18));
		
			if(player != 0){

					g.drawString("Score:",90,420);
					g.drawString(s1+"",170,420);
				
		
					g.drawString("Score:",330,420);
					g.drawString(s2+"",400,420);	
					
					
					g.setColor(Color.RED);
					g.setFont(new Font("Times New Roman", 0, 24));
					
					g.drawString("life:",200,420);
					g.drawString(life+"",280,420);
					
					
					g.setColor(Color.ORANGE);
					g.drawRect(515, 152, 30, 43);
					g.drawRect(555, 152, 30, 43);
					g.drawRect(555, 248, 30, 43);
					
					
					for(int i=0;i<objects.size();i++){
						objects.get(i).draw(g);
					}
					
				
			}
	

			
	
		}
		
		public void setLife(int life) {
			this.life = life;
		}
		
		public void setP1Score(int s1) {
			this.s1 = s1;	
		}

		public void setP2Score(int s2) {
			this.s2 = s2;
			
		}
}
