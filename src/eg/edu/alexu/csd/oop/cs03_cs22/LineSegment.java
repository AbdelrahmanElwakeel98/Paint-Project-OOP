package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class LineSegment extends IShape{


	public LineSegment() {
		super();
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
        Point point;
		point = super.getPosition();
		int x = (int) point.getX();
		int y = (int) point.getY();
		int x1 = 0;
		int y1 = 0;

		Map<String, Double> properties = new HashMap< String, Double>();
		properties = getProperties();
		double SecondX = properties.get("SecondX");
		double SecondY = properties.get("SecondY");

		canvas.setColor(Color.BLACK);


		if ((int) SecondX < x) {
			if ( (int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, y, x, SecondY);
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);
				canvas.setClip(x1, y1, (int) Point2D.distance(x, y, SecondX, y), (int) Point2D.distance(x, y, x, SecondY));
			} else {
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);
				canvas.setClip(x1, y, (int) Point2D.distance(x, y, SecondX, y), (int) Point2D.distance(x, y, x, SecondY));
			}

		} else {
			if ((int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, y, x, SecondY);
				canvas.setClip(x, y1, (int) Point2D.distance(x, y, SecondX, y), (int) Point2D.distance(x, y, x, SecondY));
			} else {
				canvas.setClip(x, y, (int) Point2D.distance(x, y, SecondX, y), (int) Point2D.distance(x, y, x, SecondY));
			}

		}

		canvas.drawLine(x, y, (int) SecondX, (int) SecondY);






	}
}
