package com.tower.elements;

import java.awt.Graphics2D;
import java.awt.Image;

import com.tower.core.BaseData;
import com.tower.core.Data;
import com.tower.core.Renderer;
import com.tower.resource.Images;

public class Road implements Renderer{
	private Image road=Images.ROAD;
	private Image road2=Images.ROAD2;
	private Image road3=Images.ROAD3;
	private Image road4=Images.ROAD4;
	private Image road5=Images.ROAD5;
	private Image road6=Images.ROAD6;
	private Image road7=Images.ROAD7;
	private Image road8=Images.ROAD8;
	private int[][] squares;
	private BaseData baseData;
	private int squaressize;
	
	public Road(BaseData baseData2){
		baseData = baseData2;
	}
	@Override
	public void renderer(Graphics2D g) {
		squaressize=Data.squaresSize;
		squares=baseData.getSquares();
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[i].length;j++){
				if(squares[i][j]==1||squares[i][j]==11||squares[i][j]==19){
					g.drawImage(road,j*squaressize, i*squaressize, null);
				}
			}
			
		}
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[i].length;j++){
				if(squares[i][j]==2||squares[i][j]==9||squares[i][j]==22){
					g.drawImage(road2,j*squaressize,i*squaressize, null);
				}
			}
			
		}
		
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[i].length;j++){
				if(squares[i][j]==3||squares[i][j]==23){
					g.drawImage(road3,j*squaressize, i*squaressize, null);
				}
				
			}
			
		}
		
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[i].length;j++){
				if(squares[i][j]==4||squares[i][j]==7){
					g.drawImage(road4,j*squaressize, i*squaressize, null);
				}
				
			}
			
		}
		
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[i].length;j++){
				if(squares[i][j]==5||squares[i][j]==8){
					g.drawImage(road5,j*squaressize, i*squaressize, null);
				}
				
			}
			
		}
		
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[i].length;j++){
				if(squares[i][j]==6){
					g.drawImage(road6,j*squaressize, i*squaressize, null);
				}
				
			}
			
		}
		
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[i].length;j++){
				if(squares[i][j]==12){
					g.drawImage(road7,j*squaressize, i*squaressize, null);
				}
				
			}
			
		}
		
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares[i].length;j++){
				if(squares[i][j]==13){
					g.drawImage(road8,j*squaressize, i*squaressize, null);
				}
				
			}
			
		}
		
	}
	
	
	
	
	
	
	public void setBaseData(BaseData baseData2) {
		this.baseData=baseData2;
		
	}
	
}
