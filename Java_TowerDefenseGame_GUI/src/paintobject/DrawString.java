package paintobject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public class DrawString extends PaintObject {
	public DrawString(Color color, Point from, Point to, String s) {
		super(color, from, to);
		this.s = s;
		
	}

	private static final long serialVersionUID = -7150909862702207156L;
	private String s;

	/**
	 * Draws a line to the given context
	 * 
	 * @param g
	 *            the context to render to
	 */
	public void draw(Graphics g) {
		

		g.setFont(new Font("Times New Roman", 0, 12));
		g.setColor(super.color);
		g.drawString(s,(int)super.from.getX(), (int)super.from.getY());
	}
}
