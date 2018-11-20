package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Triangle extends IShape{

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		Point point;
		point = super.getPosition();
		int x = (int) point.getX();
		int y = (int) point.getY();
		int x1 = 0;
		int c = 0;
		int y1 = 0;

		Map<String, Double> properties = new HashMap< String, Double>();
		properties = getProperties();
		double SecondX = properties.get("SecondX");
		double SecondY = properties.get("SecondY");

		Color c1 = getColor();
		Color c2 = getFillColor();

		if (c1 != null) {
			canvas.setColor(c1);
		} else if (c2 != null) {
			canvas.setColor(c2);
		} else {
			canvas.setColor(Color.BLACK);
		}

		if ((int) SecondX < x) {
		    if ( (int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, SecondY, x, y);
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);
				c = (int) Point2D.distance(x, y, SecondX, y) / 2;

				canvas.setClip(x1, y1, (int) Point2D.distance(x, y, SecondX, y) +1, (int) Point2D.distance(x, SecondY, x, y) +1);
				if (c1 != null) {
					canvas.drawPolygon(new int[] {c + x1, (int) SecondX, x}, new int[] {y1, (int) SecondY + (int) Point2D.distance(x, SecondY, x, y), (int) SecondY + (int) Point2D.distance(x, SecondY, x, y)}, 3);
				} else if (c2 != null) {
					canvas.fillPolygon(new int[] {c + x1, (int) SecondX, x}, new int[] {y1, (int) SecondY + (int) Point2D.distance(x, SecondY, x, y), (int) SecondY + (int) Point2D.distance(x, SecondY, x, y)}, 3);
				} else {
					canvas.drawPolygon(new int[] {c + x1, (int) SecondX, x}, new int[] {y1, (int) SecondY + (int) Point2D.distance(x, SecondY, x, y), (int) SecondY + (int) Point2D.distance(x, SecondY, x, y)}, 3);
				}
				
		    } else {
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);
				c = (int) Point2D.distance(x, y, SecondX, y) / 2;

				canvas.setClip(x1, y, (int) Point2D.distance(x, y, SecondX, y) + 1, (int) Point2D.distance(x, SecondY, x, y) + 1);
				if (c1 != null) {
					canvas.drawPolygon(new int[] {c + x1, (int) SecondX, x}, new int[] {y, (int) SecondY, (int) SecondY}, 3);
				} else if (c2 != null) {
					canvas.fillPolygon(new int[] {c + x1, (int) SecondX, x}, new int[] {y, (int) SecondY, (int) SecondY}, 3);
				} else {
					canvas.drawPolygon(new int[] {c + x1, (int) SecondX, x}, new int[] {y, (int) SecondY, (int) SecondY}, 3);
				}
				
		    }

		} else {
			if ( (int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, SecondY, x, y);
				c = (int) Point2D.distance(x, y, SecondX, y) / 2;

				canvas.setClip(x, y1, (int) Point2D.distance(x, y, SecondX, y) + 1, (int) Point2D.distance(x, SecondY, x, y) + 1);
				if (c1 != null) {
					canvas.drawPolygon(new int[] {c + x, (int) SecondX, x}, new int[] {y1, (int) SecondY+ (int) Point2D.distance(x, SecondY, x, y), (int) SecondY+ (int) Point2D.distance(x, SecondY, x, y)}, 3);
				} else if (c2 != null) {
					canvas.fillPolygon(new int[] {c + x, (int) SecondX, x}, new int[] {y1, (int) SecondY+ (int) Point2D.distance(x, SecondY, x, y), (int) SecondY+ (int) Point2D.distance(x, SecondY, x, y)}, 3);
				} else {
					canvas.drawPolygon(new int[] {c + x, (int) SecondX, x}, new int[] {y1, (int) SecondY+ (int) Point2D.distance(x, SecondY, x, y), (int) SecondY+ (int) Point2D.distance(x, SecondY, x, y)}, 3);
				}
				
			} else {
			c = (int) Point2D.distance(x, y, SecondX, y) / 2;

			canvas.setClip(x, y, (int) Point2D.distance(x, y, SecondX, y) + 1, (int) Point2D.distance(x, SecondY, x, y) + 1);
			if (c1 != null) {
				canvas.drawPolygon(new int[] {c + x, (int) SecondX, x}, new int[] {y, (int) SecondY, (int) SecondY}, 3);
			} else if (c2 != null) {
				canvas.fillPolygon(new int[] {c + x, (int) SecondX, x}, new int[] {y, (int) SecondY, (int) SecondY}, 3);
			} else {
				canvas.drawPolygon(new int[] {c + x, (int) SecondX, x}, new int[] {y, (int) SecondY, (int) SecondY}, 3);
			}
			
			}
		}
	}

	public Object clone() throws CloneNotSupportedException{
		Triangle e = new Triangle();
		Map<String, Double> pros = new HashMap< String, Double>();
		pros.put("First", this.getProperties().get("SecondX"));
		pros.put("Second", this.getProperties().get("SecondY"));
		e.setPosition(this.getPosition());
		e.setProperties(pros);
		e.setColor(this.getColor());
		e.setFillColor(this.getFillColor());

		return e;
	}
}
