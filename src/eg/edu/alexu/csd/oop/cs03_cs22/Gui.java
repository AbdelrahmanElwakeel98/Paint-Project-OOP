package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import java.awt.Rectangle;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class Gui {

	private JFrame frame;
	public static int drawFlag = 0;
	public static int sel = 0, remove = 0, undo = 0, redo = 0, move = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
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
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 963, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new MyJPanel();
		panel.setBounds(0, 128, 945, 393);
		frame.getContentPane().add(panel);



		JButton btnLine = new JButton( new ImageIcon("resources/remove.png"));
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 1;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;
			}
		});
		btnLine.setBounds(12, 28, 97, 37);

		frame.getContentPane().add(btnLine);

		JButton button = new JButton(new ImageIcon("resources/empty.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 2;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;
			}
		});
		button.setBounds(121, 28, 97, 37);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton(new ImageIcon("resources/ellipse-outline-shape-variant.png"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFlag = 3;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;
			}
		});
		button_1.setBounds(230, 28, 97, 37);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton(new ImageIcon("resources/ruler.png"));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFlag = 6;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;
			}
		});
		button_2.setBounds(12, 78, 97, 37);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton(new ImageIcon("resources/blank-check-box.png"));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFlag = 5;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;
			}
		});
		button_3.setBounds(121, 78, 97, 37);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton(new ImageIcon("resources/rectangle.png"));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFlag = 4;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;
			}
		});
		button_4.setBounds(230, 78, 97, 37);
		frame.getContentPane().add(button_4);

		JButton btnNewButton = new JButton("Main color");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser jcc = new JColorChooser ();
				Color c = jcc.showDialog(null,"ss", Color.RED);
				panel.setBackground(c);


			}
		});
		btnNewButton.setBounds(384, 78, 97, 25);
		frame.getContentPane().add(btnNewButton);

		JButton btnFillColor = new JButton("Fill color");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser jcc1 = new JColorChooser ();
				Color c1 = jcc1.showDialog(null,"ss", Color.RED);
				panel.setBackground(c1);
			}
		});
		btnFillColor.setBounds(670, 78, 97, 25);
		frame.getContentPane().add(btnFillColor);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove = 1;
				redo = 0;
				undo = 0;
				drawFlag = 0;
				move = 0;

			}
		});
		btnRemove.setBounds(519, 78, 97, 25);
		frame.getContentPane().add(btnRemove);

		JButton btnSelecr = new JButton("Select");
		btnSelecr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 0;
				sel = 1;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;

			}
		});
		btnSelecr.setBounds(670, 40, 97, 25);
		frame.getContentPane().add(btnSelecr);

		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				undo = 1;
				drawFlag = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				move = 0;
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();

			}
		});
		btnUndo.setBounds(384, 40, 97, 25);
		frame.getContentPane().add(btnUndo);

		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				redo = 1;
				undo = 0;
				drawFlag = 0;
				sel = 0;
				remove = 0;
				move = 0;
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();
			}
		});
		btnRedo.setBounds(519, 40, 97, 25);
		frame.getContentPane().add(btnRedo);
		
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 1;
			}
		});
		btnMove.setBounds(384, 2, 97, 25);
		frame.getContentPane().add(btnMove);

	}
}
