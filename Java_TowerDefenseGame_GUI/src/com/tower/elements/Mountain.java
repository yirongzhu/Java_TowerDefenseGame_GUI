package com.tower.elements;

import java.awt.Graphics2D;
import java.awt.Image;

import com.tower.core.Renderer;
import com.tower.resource.Images;

public class Mountain implements Renderer {
	private Image mountain=Images.MOUNTAIN;
	@Override
	public void renderer(Graphics2D g) {
		g.drawImage(mountain, 0, 0, null);
		
	}

}
