package paintobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * PaintObject
 * 
 * <p>A PaintObject is an abstract class defining a shape that
 * can be drawn with two points <p>
 * 
 * @author Gabriel Kishi
 *
 */
public abstract class PaintObject implements Serializable{
	
	private static final long serialVersionUID = 1451607597800871986L;
	protected Color color;
	protected Point2D from, to;
	
	public PaintObject(Color color, Point2D from, Point2D to){
		this.color = color;
		this.from = from;
		this.to = to;
	}
	
	public abstract void draw(Graphics g);

	public void setTo(Point to) {
		this.to = to;
	}
}
