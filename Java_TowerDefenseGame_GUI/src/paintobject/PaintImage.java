package paintobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PaintImage extends PaintObject{
	private static final long serialVersionUID = 3001218711240257004L;
	
	private String filename;
	
	public PaintImage(Color color, Point2D from, Point2D to, String filename) {
		super(color, from, to);
		this.filename = filename;
	}

	public void draw(Graphics g) {
		// images have to be read on the client side
		BufferedImage image = null;
		try{
			// Luckily ImageIO.read uses flyweight, or else this would be horribly inefficiently
			image = ImageIO.read(new File(filename)); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if (image == null) // mistakes were made
			return;
		
		// draw the image
		g.drawImage(image, (int)from.getX(), (int)from.getY(), (int)(to.getX() - from.getX()), (int)(to.getY() - from.getY()), null); 
	}
}
