package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class Rect extends IShape{

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		Point point;
		point = super.getPosition();
		//System.out.println(point);
		int x = (int) point.getX();
		int y = (int) point.getY();
		int x1 = 0;
		int y1 =0;

		Map<String, Double> properties = new HashMap< String, Double>();
		properties = super.getProperties();
		//System.out.println(properties);
		double SecondX = properties.get("SecondX");
		double SecondY = properties.get("SecondY");


		if ((int) SecondX < x) {
			if ( (int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, y, x, SecondY);
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);

				canvas.setClip(x1, y1, (int) Point2D.distance(x, y, SecondX, y) + 1, (int) Point2D.distance(x, y, x, SecondY)+ 1);
				canvas.drawRect(x1, y1, (int) Point2D.distance(x, y, SecondX, y), (int) Point2D.distance(x, y, x, SecondY));
			} else {
				x1 = x - (int) Point2D.distance(x, y, SecondX, y);

				canvas.setClip(x1, y, (int) Point2D.distance(x, y, SecondX, y)+ 1, (int) Point2D.distance(x, y, x, SecondY)+ 1);
				canvas.drawRect(x1, y, (int) Point2D.distance(x, y, SecondX, y), (int) Point2D.distance(x, y, x, SecondY));
			}

		} else {
			if ((int) SecondY < y ) {
				y1 = y - (int) Point2D.distance(x, y, x, SecondY);

				canvas.setClip(x, y1, (int) Point2D.distance(x, y, SecondX, y)+ 1, (int) Point2D.distance(x, y, x, SecondY)+ 1);
				canvas.drawRect(x, y1, (int) Point2D.distance(x, y, SecondX, y), (int) Point2D.distance(x, y, x, SecondY));
			} else {

				canvas.setClip(x, y, (int) Point2D.distance(x, y, SecondX, y)+ 1, (int) Point2D.distance(x, y, x, SecondY)+ 1);
				canvas.drawRect(x, y, (int) Point2D.distance(x, y, SecondX, y), (int) Point2D.distance(x, y, x, SecondY));
			}

		}



	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		Rect e = new Rect();
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
