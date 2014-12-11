package com.tower.resource;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract  class Images {
	public static final BufferedImage playerStyle = loadImage("playerStyle.png");
	public static final BufferedImage  hignGroud= loadImage("hignGroud.jpg");
	public static final BufferedImage  lowGroud= loadImage("lowGroud.jpg");
	public static final BufferedImage  RIGHT= loadImage("welcomeR.png");
	public static final BufferedImage LEFT= loadImage("welcomeL.png");
	public static final BufferedImage GRASS= loadImage("grass.png");
	public static final BufferedImage ROAD= loadImage("road.png");
	public static final BufferedImage MOUNTAIN= loadImage("mountain.png");
	public static final BufferedImage FIRE= loadImage("bang.png");
	public static final BufferedImage WELCOMEBUTTON=loadImage("welcomeButton.png");
	public static final BufferedImage LOADBUTTON=loadImage("loadButton.png");
	public static final BufferedImage PVPBUTTON=loadImage("PVPButton.png");
	public static final BufferedImage BACKBUTTON=loadImage("Button.png");
	public static final BufferedImage MENUE=loadImage("menue.png");
	public static final BufferedImage SOLDIER1=loadImage("soldier1.png");
	public static final BufferedImage lSOLDIER1=loadImage("Lsoldier1.png");
	public static final BufferedImage lSOLDIER2=loadImage("Lsoldier2.png");
	public static final BufferedImage SOLDIER2=loadImage("soldier2.png");
	public static final BufferedImage SOLDIER3=loadImage("soldier3.png");
	public static final BufferedImage SOLDIER4=loadImage("soldier4.png");
	public static final BufferedImage SOLDIER5=loadImage("soldier5.png");
	public static final BufferedImage SOLDIER6=loadImage("soldier6.png");
	public static final BufferedImage SOLDIER7=loadImage("soldier7.png");
	public static final BufferedImage SOLDIER8=loadImage("soldier8.png");
	public static final BufferedImage SOLDIER9=loadImage("soldier9.png");
	public static final BufferedImage SOLDIER10=loadImage("soldier10.png");
	public static final BufferedImage SOLDIER11=loadImage("soldier11.png");
	public static final BufferedImage SOLDIER12=loadImage("soldier12.png");
	public static final BufferedImage MAP=loadImage("map.png");
	public static final BufferedImage MAP1=loadImage("map1.png");
	public static final BufferedImage MAP2=loadImage("map2.png");
	public static final BufferedImage MAP3=loadImage("map3.png");
	public static final BufferedImage go=loadImage("go.png");

	public static final BufferedImage FIGHTER1=loadImage("fighter1.png");
	public static final BufferedImage FIGHTER2=loadImage("fighter2.png");
	public static final BufferedImage FIGHTER3=loadImage("fighter3.png");
	public static final BufferedImage ROAD2=loadImage("road2.png");
	public static final BufferedImage ROAD3=loadImage("road3.png");
	public static final BufferedImage ROAD4=loadImage("road4.png");
	public static final BufferedImage ROAD5=loadImage("road5.png");
	public static final BufferedImage ROAD6=loadImage("road6.png");
	public static final BufferedImage ROAD7=loadImage("road7.png");
	public static final BufferedImage ROAD8=loadImage("road8.png");
	public static final BufferedImage BULLETE=loadImage("bullete.png");
	public static final BufferedImage BANG=loadImage("bang.png");
	public static final BufferedImage WIN=loadImage("win.png");
	public static final BufferedImage LOSE=loadImage("lose.png");
	public static final BufferedImage PVPMAP=loadImage("pvpmap.tiff");
	public static final BufferedImage MapChoise=loadImage("choise.png");


	private static BufferedImage loadImage(String string) {
		try {
			BufferedImage img=ImageIO.read(Images.class.getResourceAsStream("/image/"+string));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
