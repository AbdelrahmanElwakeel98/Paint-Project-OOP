package eg.edu.alexu.csd.oop.cs03_cs22;

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

		if ((int) SecondX < x) {
		    if ( (int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, SecondY, x, y);
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);
				c = (int) Point2D.distance(x, y, SecondX, y) / 2;

				canvas.setClip(x1, y1, (int) Point2D.distance(x, y, SecondX, y) +1, (int) Point2D.distance(x, SecondY, x, y) +1);
				canvas.drawPolygon(new int[] {c + x1, (int) SecondX, x}, new int[] {y1, (int) SecondY + (int) Point2D.distance(x, SecondY, x, y), (int) SecondY + (int) Point2D.distance(x, SecondY, x, y)}, 3);
		    } else {
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);
				c = (int) Point2D.distance(x, y, SecondX, y) / 2;

				canvas.setClip(x1, y, (int) Point2D.distance(x, y, SecondX, y) + 1, (int) Point2D.distance(x, SecondY, x, y) + 1);
				canvas.drawPolygon(new int[] {c + x1, (int) SecondX, x}, new int[] {y, (int) SecondY, (int) SecondY}, 3);
		    }

		} else {
			if ( (int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, SecondY, x, y);
				c = (int) Point2D.distance(x, y, SecondX, y) / 2;

				canvas.setClip(x, y1, (int) Point2D.distance(x, y, SecondX, y) + 1, (int) Point2D.distance(x, SecondY, x, y) + 1);
				canvas.drawPolygon(new int[] {c + x, (int) SecondX, x}, new int[] {y1, (int) SecondY+ (int) Point2D.distance(x, SecondY, x, y), (int) SecondY+ (int) Point2D.distance(x, SecondY, x, y)}, 3);
			} else {
			c = (int) Point2D.distance(x, y, SecondX, y) / 2;

			canvas.setClip(x, y, (int) Point2D.distance(x, y, SecondX, y) + 1, (int) Point2D.distance(x, SecondY, x, y) + 1);
			canvas.drawPolygon(new int[] {c + x, (int) SecondX, x}, new int[] {y, (int) SecondY, (int) SecondY}, 3);
			}
		}

	}
}
