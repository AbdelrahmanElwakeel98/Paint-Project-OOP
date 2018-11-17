package eg.edu.alexu.csd.oop.cs03_cs22;

import eg.edu.alexu.csd.oop.draw.Shape;

public class FactoryShapes {
	
	public Shape getShape (int i) {
		
		switch (i) {
		case 1 : return new LineSegment();
		
		case 2 : return new Circle();
		
		case 3 : return new Ellipse();
		
		case 4 : return new Rectangle();
		
		case 5 : return new Square();
		
		case 6 : return new Triangle();
		
		default : return null;
		}
	}

}
