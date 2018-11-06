package eg.edu.alexu.csd.oop.cs03_cs22;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.prism.Image;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;

public class Gui {

	private JFrame frame;

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
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 128, 945, 393);
		frame.getContentPane().add(panel);
		
		Icon warnIcon = new ImageIcon("resources/remove.png");
		
		JButton btnLine = new JButton( new ImageIcon("resources/remove.png"));
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLine.setBounds(12, 28, 97, 37);
		
		frame.getContentPane().add(btnLine);
		
		JButton button = new JButton(new ImageIcon("resources/empty.png"));
		button.setBounds(121, 28, 97, 37);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton(new ImageIcon("resources/ellipse-outline-shape-variant.png"));
		button_1.setBounds(230, 28, 97, 37);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton(new ImageIcon("resources/down-arrow.png"));
		button_2.setBounds(12, 78, 97, 37);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton(new ImageIcon("resources/blank-check-box.png"));
		button_3.setBounds(121, 78, 97, 37);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton(new ImageIcon("resources/rectangle.png"));
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
		btnNewButton.setBounds(519, 78, 97, 25);
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
		
	}
}
