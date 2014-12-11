package com.tower.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.tower.resource.Images;

public class Win implements Renderer {
	private BufferedImage img=Images.WIN;
	@Override
	public void renderer(Graphics2D g) {
		g.drawImage(img, 0, 0, null);
		System.out.println("ddddddd");
	}

}
