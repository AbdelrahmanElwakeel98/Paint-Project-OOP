package eg.edu.alexu.csd.oop.cs03_cs22;

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

import eg.edu.alexu.csd.oop.draw.Shape;
import java.awt.Rectangle;
import java.awt.Stroke;


public class MyJPanel extends JPanel {

	static IDrawingEngine d = new IDrawingEngine();
    private Point point;
	private   Map<String, Double> properties = new HashMap< String, Double>();
	FactoryShapes factoryShape = new FactoryShapes();
	Shape shape = null;
	Rectangle rect = null;



	public MyJPanel() {
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

            	if (Gui.drawFlag != 0) {
            		if (point == null) {
            			point = getMousePosition();
            		} else {
            			properties.put("First", getMousePosition().getX());
            			properties.put("Second", getMousePosition().getY());

            			shape = factoryShape.getShape(Gui.drawFlag);
            			shape.setPosition(point);
            			shape.setProperties(properties);

            			try {
							d.addShape((Shape) shape.clone());
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

            			repaint();
            		}
            	} else if (Gui.drawFlag == 0 && Gui.sel == 1) {
            	    point = null;
            		point = getMousePosition();
            		repaint();
        		}
            }
        });


		addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {

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
        			/*g.setColor(Color.BLUE);
        			s.draw(g);
        			g.setColor(Color.BLACK);*/
        			if (Gui.remove == 1) {
        				d.removeShape(s);
        				repaint();
        			}
        		}
        	}
         }
        
        if (Gui.refresh == 1) {
        	g.setColor(Color.GRAY);
        	g.clearRect(0, 0, 945, 393);
       
        	try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        	d.refresh(g);
        }



}



}
