package eg.edu.alexu.csd.oop.draw;


import java.awt.Graphics;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IDrawingEngine implements DrawingEngine {

	private Shape[] shapes = new Shape[100];
	private int counter = 0;
	private ArrayList<Shape> arrayList = new ArrayList<Shape>();
	private CommandManager commandManager = new CommandManager();
	private SaveAndLoadXML saveAndloadXMl;


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
	            if (engine.shapes[i] == this.s) {
	            	engine.shapes = removeElementUsingCollection(engine.shapes, i);
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
				if (engine.shapes[i] == this.oldShape) {
					engine.shapes[i] = this.newShape;
				}
	        }
		}

		@Override
		public void undo() {
			for(int i = 0; i < engine.shapes.length; i++){
				if (engine.shapes[i] == this.newShape) {
					engine.shapes[i] = this.oldShape;
				}
	        }

		}

		@Override
		public void redo() {
			for(int i = 0; i < engine.shapes.length; i++){
				if (engine.shapes[i] == this.oldShape) {
					engine.shapes[i] = this.newShape;
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
		arrayList.clear();
		for (int i = 0; i < shapes.length; i ++) {
			if (shapes[i] == null && i == 0) {
				arrayList.clear();
			}

			if (shapes[i] == null) {
				break;
			}

			arrayList.add(shapes[i]);
		}
		Shape[] ss = new Shape[arrayList.size()];

		for (int i = 0; i < arrayList.size(); i ++) {
			ss[i] = arrayList.get(i);
		}

		return ss;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		List<Class<? extends Shape>> supportedClasses = new LinkedList<>();
		String pack = "eg/edu/alexu/csd/oop/draw";
		ClassLoader cls = ClassLoader.getSystemClassLoader();
		URL url = cls.getResource(pack);
		String path = url.getPath();
		File [] files = new File(path).listFiles();

		for (File file : files) {
			String clsName = pack + "/" + file.getName().substring(0,  file.getName().length() - 6);
			Class<?> checker = null;
			boolean flag = false;

			try {
				checker = Class.forName(clsName.replace('/', '.'));
				flag = IShape.class.isAssignableFrom(checker);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag && !file.getName().contains("IShape")) {
				supportedClasses.add((Class<? extends Shape>) checker);
			}
		}


		try {
			URL[] urls = {new URL("jar:file:RoundedRectangle.jar!/")};
			URLClassLoader clsLoader = URLClassLoader.newInstance(urls);
			Class<?> cl;
			cl = clsLoader.loadClass(pack.replace('/', '.') + ".RoundRectangle");
			supportedClasses.add((Class<? extends Shape>) cl);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}


		return supportedClasses;
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
		saveAndloadXMl = new SaveAndLoadXML(shapes, counter, path);
	}

	@Override
	public void load(String path) {
		saveAndloadXMl = new SaveAndLoadXML(shapes, counter, path);
		shapes = saveAndloadXMl.getShapesSaved();
		counter = saveAndloadXMl.getCounter();

	}

	 private static Shape[] removeElementUsingCollection( Shape[] shapes2, int index ){
	        List<Shape> tempList = new ArrayList<Shape>(Arrays.asList(shapes2));
	        tempList.remove(index);
	        return tempList.toArray(new Shape[0]);
	    }



}
