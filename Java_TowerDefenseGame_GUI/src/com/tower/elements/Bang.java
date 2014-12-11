package com.tower.elements;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.tower.core.Renderer;
import com.tower.core.Stage;
import com.tower.resource.Images;

public class Bang implements Renderer {
 
	private BufferedImage image = Images.BANG;
	
	private Rectangle bounds = new Rectangle(0,0,21,16);
	
	private Stage stage;

	private int i;
	
	public Bang(int x, int y) {
		bounds.x = x;
		bounds.y = y;
	}

	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void renderer(Graphics2D g) {
		g.setClip(bounds);
		g.drawImage(image, bounds.x-i, bounds.y, null);
		i += 21;
		if( i>=250 ) {
			stage.moveElement(this);
		}
		
	}
	 
}
 
