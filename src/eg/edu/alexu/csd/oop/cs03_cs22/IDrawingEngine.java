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
	private CommandManager commandManager = new CommandManager();


	private class AddShapeCommand implements Command{

		private IDrawingEngine engine;
		private Shape s;
		private int counter;

		private AddShapeCommand(IDrawingEngine engine, Shape s, int counter) {
			this.engine = engine;
			this.s = s;
			this.counter = counter;
		}

		@Override
		public void execute() {
			engine.shapes[this.counter] = this.s;
			this.counter = this.counter + 1;
			engine.counter = this.counter;
		}

		@Override
		public void undo() {
			engine.shapes[this.counter - 1] = null;
			this.counter = this.counter - 1;
			engine.counter = this.counter;

		}

		@Override
		public void redo() {
			this.counter = this.counter + 1;
			engine.shapes[this.counter] = this.s;
			engine.counter = this.counter;

		}

	}

	private class RemoveShapeCommand implements Command{

		private IDrawingEngine engine;
		private Shape s;
		private int counter;
		private Class cls1, cls2;


		private RemoveShapeCommand(IDrawingEngine engine, Shape s, int counter) {
			this.engine = engine;
			this.s = s;
			this.counter = counter;
		}

		@Override
		public void execute() {
			
			for(int i = 0; i < engine.shapes.length; i++){
	            
				if (engine.shapes[i] == null) {
					break;
				}
				cls1 = engine.shapes[i].getClass();
            	cls2 = this.s.getClass();
            	if (cls1.getName().equals(cls2.getName())) {
            		if (engine.shapes[i].getPosition().getX() == this.s.getPosition().getX() &&
            				engine.shapes[i].getPosition().getY() == this.s.getPosition().getY()) {
            			if (engine.shapes[i].getProperties().get("SecondX").equals(this.s.getProperties().get("SecondX")) &&
            					engine.shapes[i].getProperties().get("SecondY").equals(this.s.getProperties().get("SecondY") )) {
            				engine.shapes = removeElementUsingCollection(engine.shapes, i);
        	                break;}
            			}
            		}
            	}
			this.counter = this.counter - 1;
			engine.counter = this.counter;
		}

		@Override
		public void undo() {
			engine.shapes[this.counter] = this.s;
			this.counter = this.counter + 1;
			engine.counter = this.counter;
		}

		@Override
		public void redo() {
			engine.shapes[this.counter - 1] = this.s;
			this.counter = this.counter - 1;
			engine.counter = this.counter;
		}

	}

	private class UpdateShapeCommand implements Command{

		private IDrawingEngine engine;
		private Shape newShape;
		private Shape oldShape;
		private Class cls1, cls2;

		private UpdateShapeCommand(IDrawingEngine engine, Shape newShape, Shape oldShape) {
			this.engine = engine;
			this.newShape = newShape;
			this.oldShape = oldShape;
		}

		@Override
		public void execute() {
			for(int i = 0; i < engine.shapes.length; i++){
				if (engine.shapes[i] == null) {
					break;
				}
				cls1 = engine.shapes[i].getClass();
            	cls2 = this.oldShape.getClass();
            	if (cls1.getName().equals(cls2.getName())) {
            		if (engine.shapes[i].getPosition().getX() == this.oldShape.getPosition().getX() &&
            				engine.shapes[i].getPosition().getY() == this.oldShape.getPosition().getY()) {
            			if (engine.shapes[i].getProperties().get("SecondX").equals(this.oldShape.getProperties().get("SecondX")) &&
            					engine.shapes[i].getProperties().get("SecondY").equals(this.oldShape.getProperties().get("SecondY") )) {
            				try {
								engine.shapes[i] = (Shape) this.newShape.clone();
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
            			}
            		}
            	}
	        }
		}

		@Override
		public void undo() {
			for(int i = 0; i < engine.shapes.length; i++){
				if (engine.shapes[i] == null) {
					break;
				}
				cls1 = engine.shapes[i].getClass();
            	cls2 = this.newShape.getClass();
            	if (cls1.getName().equals(cls2.getName())) {
            		System.out.println("Yes1");
            		if (engine.shapes[i].getPosition().getX() == this.newShape.getPosition().getX() &&
            				engine.shapes[i].getPosition().getY() == this.newShape.getPosition().getY()) {
            			System.out.println("Yes2");
            			if (engine.shapes[i].getProperties().get("SecondX").equals(this.newShape.getProperties().get("SecondX")) &&
            					engine.shapes[i].getProperties().get("SecondY").equals(this.newShape.getProperties().get("SecondY") )) {
            				System.out.println("Yes3");
            				System.out.println(this.oldShape.getFillColor());
            				System.out.println(engine);
            				try {
								engine.shapes[i] = (Shape) this.oldShape.clone();
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
            			}
            		}
            	}
	        }

		}

		@Override
		public void redo() {
			for(int i = 0; i < engine.shapes.length; i++){
				if (engine.shapes[i] == null) {
					break;
				}
				cls1 = engine.shapes[i].getClass();
            	cls2 = this.oldShape.getClass();
            	if (cls1.getName().equals(cls2.getName())) {
            		if (engine.shapes[i].getPosition().getX() == this.oldShape.getPosition().getX() &&
            				engine.shapes[i].getPosition().getY() == this.oldShape.getPosition().getY()) {
            			if (engine.shapes[i].getProperties().get("SecondX").equals(this.oldShape.getProperties().get("SecondX")) &&
            					engine.shapes[i].getProperties().get("SecondY").equals(this.oldShape.getProperties().get("SecondY") )) {
            				try {
								engine.shapes[i] = (Shape) this.newShape.clone();
							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
            			}
            		}
            	}
	        }
		}
	}


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
		commandManager.executeCommand(new AddShapeCommand(this, shape, this.counter));
	}

	@Override
	public void removeShape(Shape shape) {
		commandManager.executeCommand(new RemoveShapeCommand(this, shape, this.counter));
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		commandManager.executeCommand(new UpdateShapeCommand(this, newShape, oldShape));
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
		commandManager.undo();

	}

	@Override
	public void redo() {
		commandManager.redo();
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

	}

	 private static Shape[] removeElementUsingCollection( Shape[] shapes2, int index ){
	        List<Shape> tempList = new ArrayList<Shape>(Arrays.asList(shapes2));
	        tempList.remove(index);
	        return tempList.toArray(new Shape[0]);
	    }



}
