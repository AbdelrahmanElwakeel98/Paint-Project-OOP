package eg.edu.alexu.csd.oop.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import java.awt.Rectangle;
import java.awt.Stroke;


public class MyJPanel extends JPanel {

	private IDrawingEngine d = new IDrawingEngine();
    private Point point;
	private   Map<String, Double> properties = new HashMap< String, Double>();
	private   Map<String, Double> oldProperties = new HashMap< String, Double>();
	FactoryShapes factoryShape = new FactoryShapes();
	Shape shape = null;
	Rectangle rect = null;
	Shape shapeDragged = null;
	Shape shapeDragging = null;
	boolean dragging = false;
	Shape shapeUpdated = null;
	Shape shapeSelected = null;
	Point oldPoint = null;
	private Map<String, Double> resizePros = new HashMap< String, Double>();


	public MyJPanel() {
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	if (Gui.drawFlag != 0) {
            		if (point == null) {
            			point = getMousePosition();
            			shapeDragged = factoryShape.getShape(Gui.drawFlag);
            			shapeDragged.setPosition(point);
            			shape = factoryShape.getShape(Gui.drawFlag);
            			shape.setPosition(point);
            		}
            	} else if (Gui.sel == 1) {
            	    point = null;
            		point = getMousePosition();
            		repaint();
        		}  else if (Gui.resize == 1) {
        			resizePros.put("First", (double) e.getX());
        			resizePros.put("Second", (double) e.getY());
        			
        			shapeUpdated.setPosition(shapeSelected.getPosition());
        			shapeUpdated.setProperties(resizePros);
        			
        			try {
						d.updateShape( (Shape) shapeSelected.clone(), (Shape) shapeUpdated.clone());
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			repaint();
        		}
            }

            public void mouseReleased(MouseEvent e) {
            	dragging = false;
            	if (Gui.drawFlag != 0) {
            		if (shape.getPosition() != null) {
            			properties.put("First", (double) e.getX());
            			properties.put("Second", (double) e.getY());
            			shape.setProperties(properties);
            			if (Gui.c != null) {
            				shape.setColor(Gui.c);
            			}
            			
            			if (Gui.c1 != null) {
            				System.out.println("d5l");
            				shape.setFillColor(Gui.c1);
            			}
            			
            			try {
							d.addShape((Shape) shape.clone());
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            			repaint();
            		}
            	}
            	if (Gui.move == 1) {
        			point = null;
        			oldPoint = shapeDragging.getPosition();
        			oldProperties = shapeDragging.getProperties();
        			point = getMousePosition();
        			if ((oldPoint.getX() <= point.getX()) && oldPoint.getY() >= point.getY()) {
        				properties.put("First", oldProperties.get("SecondX") + (point.getX() - oldPoint.getX()));
            			properties.put("Second", oldProperties.get("SecondY") - (oldPoint.getY() - point.getY()));
        			} else if ((oldPoint.getX() >= point.getX()) && oldPoint.getY() >= point.getY()) {
        				properties.put("First", oldProperties.get("SecondX") - (oldPoint.getX() - point.getX()));
            			properties.put("Second", oldProperties.get("SecondY") - (oldPoint.getY() - point.getY()));
        			} else if ((oldPoint.getX() <= point.getX()) && oldPoint.getY() <= point.getY()) {
        				properties.put("First", oldProperties.get("SecondX") + (point.getX() - oldPoint.getX()));
        				properties.put("Second", oldProperties.get("SecondY") + (point.getY() - oldPoint.getY()));
        			} else if ((oldPoint.getX() >= point.getX()) && oldPoint.getY() <= point.getY()) {
        				properties.put("First", oldProperties.get("SecondX") - (oldPoint.getX() - point.getX()));
        				properties.put("Second", oldProperties.get("SecondY") + (point.getY() - oldPoint.getY()));
        			}
        			shapeUpdated.setPosition(point);
        			shapeUpdated.setProperties(properties);
        			
        			if (shapeSelected.getColor() != null) {
        				shapeUpdated.setColor(shapeSelected.getColor());
        			} else if (shapeSelected.getFillColor() != null) {
        				shapeUpdated.setFillColor(shapeSelected.getFillColor() );
        			}

        			shapeDragging.setPosition(shapeSelected.getPosition());
        			Map<String, Double> pros = new HashMap< String, Double>();
        			pros.put("First", shapeSelected.getProperties().get("SecondX"));
        			pros.put("Second", shapeSelected.getProperties().get("SecondY"));
        			shapeDragging.setProperties(pros);
        			
        			shapeSelected = shapeDragging;

        			try {
						d.updateShape(shapeSelected,(Shape)  shapeUpdated.clone());
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			repaint();
        		}
            }
        });

		addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
            	if (Gui.drawFlag != 0) {
            	dragging = true;
            	properties.put("First", (double) e.getX());
    			properties.put("Second", (double) e.getY());
    			shapeDragged.setProperties(properties);
    			repaint();
            	}

            	if (Gui.move == 1) {
                dragging = true;
                point = null;
       			oldPoint = shapeDragging.getPosition();
       			oldProperties = shapeDragging.getProperties();
       			point = getMousePosition();
       			if ((oldPoint.getX() <= point.getX()) && oldPoint.getY() >= point.getY()) {
       				properties.put("First", oldProperties.get("SecondX") + (point.getX() - oldPoint.getX()));
           			properties.put("Second", oldProperties.get("SecondY") - (oldPoint.getY() - point.getY()));
       			} else if ((oldPoint.getX() >= point.getX()) && oldPoint.getY() >= point.getY()) {
       				properties.put("First", oldProperties.get("SecondX") - (oldPoint.getX() - point.getX()));
           			properties.put("Second", oldProperties.get("SecondY") - (oldPoint.getY() - point.getY()));
       			} else if ((oldPoint.getX() <= point.getX()) && oldPoint.getY() <= point.getY()) {
       				properties.put("First", oldProperties.get("SecondX") + (point.getX() - oldPoint.getX()));
       				properties.put("Second", oldProperties.get("SecondY") + (point.getY() - oldPoint.getY()));
       			} else if ((oldPoint.getX() >= point.getX()) && oldPoint.getY() <= point.getY()) {
       				properties.put("First", oldProperties.get("SecondX") - (oldPoint.getX() - point.getX()));
       				properties.put("Second", oldProperties.get("SecondY") + (point.getY() - oldPoint.getY()));
       			}
       			shapeDragging.setPosition(point);
       			shapeDragging.setProperties(properties);

       			repaint();
            	}
            }
        });
	}

	protected void paintComponent(Graphics g) {

        super.paintComponent(g);


        if (point != null && !properties.isEmpty() && Gui.drawFlag != 0) {
        Shape[] shapes = d.getShapes();
        for (Shape shape : shapes) {
        	if (shape == null) {

        		break;
        	}
        		 shape.draw(g);
            	 point = null;
        }
        }

        if (point != null && Gui.sel  == 1) {
        	Shape[] sh = d.getShapes();
        	for (Shape s : sh) {
        		if (s == null) {
        			break;
        		}
        		s.draw(g);
        		rect = g.getClipBounds();
        		if (rect.contains(point)) {
        			 Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        			 Graphics2D g2 = (Graphics2D) g;
        			 g2.setStroke(dashed);
        			 s.draw(g2);
        			 g2.setStroke(new BasicStroke());

        			 Class cls = s.getClass();

        			 if (cls == LineSegment.class) {
        				 shapeUpdated = factoryShape.getShape(1);
        			 } else if (cls == Circle.class) {
        				 shapeUpdated = factoryShape.getShape(2);
        			 } else if (cls == Ellipse.class) {
        				 shapeUpdated = factoryShape.getShape(3);
        			 } else if (cls == Rect.class) {
        				 shapeUpdated = factoryShape.getShape(4);
        			 } else if (cls == Square.class) {
        				 shapeUpdated = factoryShape.getShape(5);
        			 } else if (cls  == Triangle.class) {
        				 shapeUpdated = factoryShape.getShape(6);
        			 }
        			 try {
						shapeSelected = (Shape) s.clone();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

        			 shapeDragging = s;
        		}
        	}
         }

        if (Gui.undo == 1) {
        	d.undo();
        	d.refresh(g);
        	point = null;
        }

        if (Gui.redo == 1) {
        	d.redo();
        	d.refresh(g);
        	point = null;
        }

        if (dragging && Gui.drawFlag != 0) {
        	shapeDragged.draw(g);
        	d.refresh(g);
        	point = null;
        }

        if (Gui.drawFlag != 0) {
        	d.refresh(g);
        	point = null;
        }

        if (Gui.move == 1 && dragging) {
        	shapeDragging.draw(g);
        	d.refresh(g);
        	point = null;
        }

        if (Gui.move == 1) {
        	d.refresh(g);
        	point = null;
        }
        
        if (Gui.remove == 1) {
        	shapeSelected = shapeDragging;
        	d.removeShape(shapeSelected);
        	d.refresh(g);
        }
        
        if (Gui.resize == 1) {
        	d.refresh(g);
        }
        
        if (Gui.changeColor == 1) {
        	shapeUpdated.setPosition(shapeSelected.getPosition());
        	Map<String, Double> pros = new HashMap< String, Double>();
			pros.put("First", shapeSelected.getProperties().get("SecondX"));
			pros.put("Second", shapeSelected.getProperties().get("SecondY"));
        	shapeUpdated.setProperties(pros);
        	if (shapeSelected.getColor() != null) {
        		shapeUpdated.setColor(Gui.c2);
        	} else if (shapeSelected.getFillColor() != null) {
        		shapeUpdated.setFillColor(Gui.c2);
        	} else {
        		shapeUpdated.setColor(Gui.c2);
        	}
        	
        	shapeSelected = shapeDragging;
        	try {
				d.updateShape(shapeSelected,(Shape)  shapeUpdated.clone());
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	d.refresh(g);
        }
        if (Gui.saveXML == 1) {
        	d.save(Gui.path);
        	d.refresh(g);
        }
        
        if (Gui.loadXML == 1) {
        	d.load(Gui.path);
        	d.refresh(g);
        }
}
}
