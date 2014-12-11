package paintobject;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public class Line extends PaintObject {
	private static final long serialVersionUID = -7150909862702207156L;
	private float width;
	
	public Line(Color color, Point2D f, Point2D t) {
		super(color, f, t);
		width = 1.0f;
	}
	
	public Line(Color color, Point from, Point to, float width)
	{
		super(color, from, to);
		this.width = width;
	}

	/**
	 * 	Draws a line to the given context
	 * 
	 * 	@param g	the context to render to
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		Stroke oldStroke = g2d.getStroke();
		Color oldColor = g2d.getColor();
		
		g2d.setColor(super.color);
		g2d.setStroke(new BasicStroke(width));
		g2d.drawLine(
				(int)super.from.getX(), (int)super.from.getY(), 
				(int)super.to.getX(), (int)super.to.getY()
		);
		
		g2d.setColor(oldColor);
		g2d.setStroke(oldStroke);
	}
}
