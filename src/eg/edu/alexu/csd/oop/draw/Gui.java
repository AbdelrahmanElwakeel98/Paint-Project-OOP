package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;

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
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;

public class Gui {

	private JFrame frame;
	public static int drawFlag = 0;
	public static int sel = 0, remove = 0, undo = 0, redo = 0, move = 0, resize = 0, changeColor = 0, saveXML = 0, loadXML = 0;
	public static String path;
	public static Color c = null;
	public static Color c1 = null;
	public static Color c2 = null;

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
		panel.setBounds(0, 143, 945, 378);
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
				changeColor = 0;
				saveXML = 0;
				loadXML = 0;
			}
		});
		btnLine.setBounds(12, 28, 52, 37);

		frame.getContentPane().add(btnLine);

		JButton button = new JButton(new ImageIcon("resources/empty.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 2;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				changeColor = 0;
				saveXML = 0;
				move = 0;
				loadXML = 0;
			}
		});
		button.setBounds(76, 28, 52, 37);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton(new ImageIcon("resources/ellipse-outline-shape-variant.png"));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFlag = 3;
				saveXML = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;
				changeColor = 0;
				loadXML = 0;
			}
		});
		button_1.setBounds(140, 28, 52, 37);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton(new ImageIcon("resources/ruler.png"));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFlag = 6;
				sel = 0;
				redo = 0;
				saveXML = 0;
				remove = 0;
				undo = 0;
				move = 0;
				changeColor = 0;
				loadXML = 0;
			}
		});
		button_2.setBounds(204, 28, 52, 37);
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
				changeColor = 0;
				saveXML = 0;
				loadXML = 0;
			}
		});
		button_3.setBounds(268, 28, 52, 37);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton(new ImageIcon("resources/rectangle.png"));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFlag = 4;
				saveXML = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 0;
				changeColor = 0;
				loadXML = 0;
			}
		});
		button_4.setBounds(332, 28, 52, 37);
		frame.getContentPane().add(button_4);

		JButton btnNewButton = new JButton("Main color");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser jcc = new JColorChooser ();
				c = jcc.showDialog(null,"ss", Color.RED);
				c1 = null;
				c2 = null;
			}
		});
		btnNewButton.setBounds(396, 66, 97, 25);
		frame.getContentPane().add(btnNewButton);

		JButton btnFillColor = new JButton("Fill color");
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser jcc1 = new JColorChooser ();
				c1 = jcc1.showDialog(null,"ss", Color.RED);
				c = null;
				c2 = null;
			}
		});
		btnFillColor.setBounds(396, 28, 97, 25);
		frame.getContentPane().add(btnFillColor);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove = 1;
				redo = 0;
				undo = 0;
				changeColor = 0;
				drawFlag = 0;
				sel = 0;
				move = 0;
				saveXML = 0;
				loadXML = 0;
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();

			}
		});
		btnRemove.setBounds(836, 99, 97, 25);
		frame.getContentPane().add(btnRemove);

		JButton btnSelecr = new JButton("Select");
		btnSelecr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 0;
				sel = 1;
				redo = 0;
				remove = 0;
				saveXML = 0;
				changeColor = 0;
				undo = 0;
				move = 0;
				loadXML = 0;

			}
		});
		btnSelecr.setBounds(574, 0, 97, 25);
		frame.getContentPane().add(btnSelecr);

		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				undo = 1;
				drawFlag = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				saveXML = 0;
				changeColor = 0;
				move = 0;
				loadXML = 0;
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();

			}
		});
		btnUndo.setBounds(836, 0, 97, 25);
		frame.getContentPane().add(btnUndo);

		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				redo = 1;
				undo = 0;
				drawFlag = 0;
				sel = 0;
				saveXML = 0;
				remove = 0;
				changeColor = 0;
				move = 0;
				loadXML = 0;
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();
			}
		});
		btnRedo.setBounds(727, 0, 97, 25);
		frame.getContentPane().add(btnRedo);
		
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 0;
				sel = 0;
				saveXML = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				move = 1;
				loadXML = 0;
				changeColor = 0;
				resize = 0;
			}
		});
		btnMove.setBounds(12, 93, 63, 37);
		frame.getContentPane().add(btnMove);
		
		JButton btnResize = new JButton("Resize");
		btnResize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				saveXML = 0;
				move = 0;
				resize = 1;
				changeColor = 0;
				loadXML = 0;
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();
			}
		});
		btnResize.setBounds(87, 93, 69, 37);
		frame.getContentPane().add(btnResize);
		
		JButton btnChangecolor = new JButton("ChangeColor");
		btnChangecolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				saveXML = 0;
				move = 0;
				resize = 0;
				changeColor = 1;
				loadXML = 0;
				JColorChooser jcc2 = new JColorChooser ();
				c2 = jcc2.showDialog(null,"ss", Color.RED);
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();
			}
		});
		btnChangecolor.setBounds(168, 93, 105, 37);
		frame.getContentPane().add(btnChangecolor);
		
		JButton btnSavexml = new JButton("SaveXML");
		btnSavexml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawFlag = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				saveXML = 1;
				move = 0;
				resize = 0;
				changeColor = 0;
				loadXML = 0;
				JFileChooser fs = new JFileChooser(new File("E:\\"));
				fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fs.setDialogTitle("Save");
				@SuppressWarnings("unused")
				int result = fs.showSaveDialog(null);
				File fi = fs.getCurrentDirectory();

				if (fi == null) {
					JOptionPane.showMessageDialog(null, " No Destination chosen");

				} else {
					path = fs.getSelectedFile().getPath();

				}
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();
			}
		});
		btnSavexml.setBounds(703, 99, 97, 25);
		frame.getContentPane().add(btnSavexml);
		
		JButton btnLoadxml_1 = new JButton("LoadXML");
		btnLoadxml_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawFlag = 0;
				sel = 0;
				redo = 0;
				remove = 0;
				undo = 0;
				saveXML = 0;
				move = 0;
				resize = 0;
				changeColor = 0;
				loadXML = 1;
				JFileChooser fs = new JFileChooser(new File("E:\\"));
				fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fs.setDialogTitle("Save");
				@SuppressWarnings("unused")
				int result = fs.showSaveDialog(null);
				File fi = fs.getCurrentDirectory();

				if (fi == null) {
					JOptionPane.showMessageDialog(null, " No Destination chosen");

				} else {
					path = fs.getSelectedFile().getPath();

				}
				panel.removeAll();
				//add your elements
				panel.revalidate();
				panel.repaint();
			}
		});
		btnLoadxml_1.setBounds(594, 99, 97, 25);
		frame.getContentPane().add(btnLoadxml_1);
		
		JLabel lblSupportedShapes = new JLabel("Supported shapes: -");
		lblSupportedShapes.setFont(new Font("Bauhaus Std Light", Font.PLAIN, 13));
		lblSupportedShapes.setBounds(12, 6, 135, 16);
		frame.getContentPane().add(lblSupportedShapes);
		
		JLabel lblAvaiableModification = new JLabel("Avaiable modification: -");
		lblAvaiableModification.setFont(new Font("Bauhaus Std Light", Font.PLAIN, 13));
		lblAvaiableModification.setBounds(12, 78, 159, 16);
		frame.getContentPane().add(lblAvaiableModification);
		
		JLabel lblColors = new JLabel("Colors: -");
		lblColors.setFont(new Font("Bauhaus Std Light", Font.PLAIN, 13));
		lblColors.setBounds(401, 6, 135, 16);
		frame.getContentPane().add(lblColors);

	}
}
