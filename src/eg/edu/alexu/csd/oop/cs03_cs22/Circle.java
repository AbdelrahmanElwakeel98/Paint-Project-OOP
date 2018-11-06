package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Circle extends IShape{

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		Point point;
		point = super.getPosition();
		int x = (int) point.getX();
		int y = (int) point.getY();
		int x1 = 0;
		
		Map<String, Double> properties = new HashMap< String, Double>();
		properties = getProperties();
		double SecondX = properties.get("SecondX");
		double SecondY = properties.get("SecondY");
		
		if ((int) SecondX < x) {
			x1 = x - (int) Point2D.distance(x, y, SecondX, SecondY);
			canvas.drawOval(x1, y,(int) Point2D.distance(x, y, SecondX, SecondY),(int) Point2D.distance(x, y, SecondX, SecondY));
		} else {
			canvas.drawOval(x, y,(int) Point2D.distance(x, y, SecondX, SecondY),(int) Point2D.distance(x, y, SecondX, SecondY));
		}
		
		
		
		
	}
}
