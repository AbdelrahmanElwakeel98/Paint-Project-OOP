package eg.edu.alexu.csd.oop.cs03_cs22;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class IDrawingEngine implements DrawingEngine {
	
	private Shape[] shapes = new Shape[100];
	private int counter = 0;
	private int i = 0;

	@Override
	public void refresh(Graphics canvas) {
		for (Shape shape : shapes) {
        	if (shape == null) {
        		break;
        	}
        		 shape.draw(canvas);
        }

	}

	@Override
	public void addShape(Shape shape) {
		shapes[counter] = shape;
		counter++;
	}

	@Override
	public void removeShape(Shape shape) {
		for(int i = 0; i < shapes.length; i++){
            if(shapes[i] == shape){
            	System.out.print(i);
            	shapes = removeElementUsingCollection(shapes, i);
                break;
            }
        }


	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape[] getShapes() {
		return shapes;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

	}
	
	 public static Shape[] removeElementUsingCollection( Shape[] shapes2, int index ){
	        List<Shape> tempList = new ArrayList<Shape>(Arrays.asList(shapes2));
	        tempList.remove(index);
	        return tempList.toArray(new Shape[0]);
	    }



}
