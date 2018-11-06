package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class LineSegment extends IShape{

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
        Point point;
		point = super.getPosition();
		int x = (int) point.getX();
		int y = (int) point.getY();
		
		Map<String, Double> properties = new HashMap< String, Double>();
		properties = getProperties();
		double SecondX = properties.get("SecondX");
		double SecondY = properties.get("SecondY");
		
		canvas.setColor(Color.BLACK);
		canvas.drawLine(x, y, (int) SecondX, (int) SecondY);
		
		
		
		
		
		
	}
}
