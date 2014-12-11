package com.tower.elements;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.tower.core.BaseData;
import com.tower.core.Renderer;
import com.tower.resource.Images;

public class Grass implements Renderer {
	private Image grass=Images.GRASS;
	private Image higngroud=Images.hignGroud;
	private Image lowgroud=Images.lowGroud;
	private Image  []imageList={grass,higngroud,lowgroud};
	
	private int squaressize;
	private int [][] squares;
	private BaseData baseData;
	public Grass(BaseData baseData2) {
		// TODO Auto-generated constructor stub
		baseData = baseData2;
	}
	@Override
	public void renderer(Graphics2D g) {	
		g.drawImage(imageList[baseData.choice],0, 0, null);

	}
	
	public void setBaseData(BaseData baseData2) {	
		this.baseData=baseData2;
	
	}
}
 
