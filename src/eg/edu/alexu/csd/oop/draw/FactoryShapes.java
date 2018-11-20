package eg.edu.alexu.csd.oop.draw;

public class FactoryShapes {
	
	public Shape getShape (int i) {
		
		switch (i) {
		case 1 : return new LineSegment();
		
		case 2 : return new Circle();
		
		case 3 : return new Ellipse();
		
		case 4 : return new Rect();
		
		case 5 : return new Square();
		
		case 6 : return new Triangle();
		
		default : return null;
		}
	}

}
