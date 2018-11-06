package snippet;


import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Line;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.cs03_cs22.Circle;
import eg.edu.alexu.csd.oop.cs03_cs22.Ellipse;
import eg.edu.alexu.csd.oop.cs03_cs22.LineSegment;
import eg.edu.alexu.csd.oop.cs03_cs22.Rectangle;
import eg.edu.alexu.csd.oop.cs03_cs22.Square;
import eg.edu.alexu.csd.oop.cs03_cs22.Triangle;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

public class painting {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					painting window = new painting();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public painting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new Mycanvas();
		panel.setBounds(0, 73, 432, 180);
		frame.getContentPane().add(panel);


	}
}

class Mycanvas extends JPanel{

	private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;

    Ellipse line = new Ellipse();

    int i = 0;
    Point point;
 private   Map<String, Double> properties = new HashMap< String, Double>();


	public Mycanvas() {


       
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

            	if (point == null) {
               point = new Point(e.getX(), e.getY());
                
                line.setPosition(point);
                
                System.out.print(point.getX());
                System.out.print("\n");
                System.out.print(point.getY());
                
            	} else {
            		properties.put("First", (double) e.getX());
                	properties.put("Second", (double) e.getY());
                	
                	 System.out.print("\n");
                	System.out.print(properties.get("First"));
                    System.out.print("\n");
                    System.out.print(properties.get("Second"));
                	
                	line.setProperties(properties);
                	repaint();
            	}
            }
        });
        

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });


    }

    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((squareX!=x) || (squareY!=y)) {
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);

        }
    }


    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("d5l");
        if (point != null && !properties.isEmpty()) {
        	System.out.println("d5l");
        line.draw(g);
        }
        /*g.drawString("This is my custom Panel!",10,20);
        g.setColor(Color.RED);
        g.drawRect(squareX,squareY,squareW,squareH);*/


       // g.fillRect(squareX,squareY,squareW,squareH);
      //  g.setColor(Color.BLACK);
     //   g.drawRect(squareX,squareY,squareW,squareH);
    
    
}




}
