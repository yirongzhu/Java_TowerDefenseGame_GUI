package com.tower.elements;

import java.awt.Graphics2D;
import java.awt.Image;

import com.tower.core.Renderer;
import com.tower.resource.Images;

public class Menue implements Renderer{
	private Image menue=Images.MENUE;
	@Override
	public void renderer(Graphics2D g) {
		g.drawImage(menue, 480, 0, null);		
	}

}
