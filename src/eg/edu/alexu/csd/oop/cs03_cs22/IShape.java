package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class IShape implements Shape, Cloneable {

	private Point position;
	private Color c;
	private Color c1;
	private Map<String, Double> propertie = new HashMap< String, Double>();



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
		this.propertie.put("SecondX", properties.get("First"));
		this.propertie.put("SecondY", properties.get("Second"));
	}

	@Override
	public Map<String, Double> getProperties() {
		return propertie;
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
		this.c1 = color;
	}

	@Override
	public Color getFillColor() {
		return c1;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object clone() throws CloneNotSupportedException{
		return null;
	}
}
