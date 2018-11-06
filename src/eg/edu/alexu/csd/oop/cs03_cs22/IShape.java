package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class IShape implements Shape {
	
	Point position;
	Color c;
	Map<String, Double> properties = new HashMap< String, Double>(); 

	@Override
	public void setPosition(Point position) {
		this.position = new Point((int) position.getX(), position.y);
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties.put("SecondX", properties.get("First"));
		this.properties.put("SecondY", properties.get("Second"));
		
	}

	@Override
	public Map<String, Double> getProperties() {
		return properties;
	}

	@Override
	public void setColor(Color color) {
		this.c = color;
		
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		
	}
	
	public Object clone() throws CloneNotSupportedException{
		return null;
		
	}

}
