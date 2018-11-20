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
		int y1 = 0;

		Map<String, Double> properties = new HashMap< String, Double>();
		properties = getProperties();
		double SecondX = properties.get("SecondX");
		double SecondY = properties.get("SecondY");

		if ((int) SecondX < x) {
			if ( (int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, y, SecondX, SecondY);
				x1 = x - (int) Point2D.distance(x, y, SecondX, SecondY);
				canvas.setClip(x1 - 1, y1- 1, (int) Point2D.distance(x, y, SecondX, SecondY)+ 1, (int) Point2D.distance(x, y, SecondX, SecondY)+ 1);
				canvas.drawOval(x1, y1, (int) Point2D.distance(x, y, SecondX, SecondY), (int) Point2D.distance(x, y, SecondX, SecondY));

			} else {
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);
				canvas.setClip(x1- 1, y- 1, (int) Point2D.distance(x, y, SecondX, SecondY)+ 1, (int) Point2D.distance(x, y, SecondX, SecondY)+ 1);
				canvas.drawOval(x1, y, (int) Point2D.distance(x, y, SecondX, SecondY), (int) Point2D.distance(x, y, SecondX, SecondY));
			}
		} else {
			if ( (int) SecondY < y) {
				y1 = y - (int) Point2D.distance(x, y, SecondX, SecondY);
				canvas.setClip(x- 1, y1- 1, (int) Point2D.distance(x, y, SecondX, SecondY)+ 1, (int) Point2D.distance(x, y, SecondX, SecondY)+ 1);
				canvas.drawOval(x, y1, (int) Point2D.distance(x, y, SecondX, SecondY), (int) Point2D.distance(x, y, SecondX, SecondY));
			} else {
				canvas.setClip(x- 1, y- 1, (int) Point2D.distance(x, y, SecondX, SecondY)+ 1, (int) Point2D.distance(x, y, SecondX, SecondY)+ 1);
				canvas.drawOval(x, y, (int) Point2D.distance(x, y, SecondX, SecondY), (int) Point2D.distance(x, y, SecondX, SecondY));
			}
		}

	}
	
	public Object clone() throws CloneNotSupportedException{
		Circle e = new Circle();
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
