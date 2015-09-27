/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L; // Eclipse added this automatically
	public final static int windowWidth = 400;
	public final static int windowHeight = 400;

	private JPanel jContentPane = null;

	private GamePanel panel = null; // This is the panel of the game class

	private GamePanel getPanel() {
		if (panel == null) {
			String Team1Name = JOptionPane.showInputDialog("Please Enter Team 1 Name");
			String Team2Name = JOptionPane.showInputDialog("Please Enter Team 2 Name");
                        

			panel = new GamePanel(Team1Name , Team2Name); // The panel is created
		}
		return panel;
	}

	/**
	 * This is the default constructor
	 */
	public Main() {
		super();
		initialize();
        // Listeners for the keyboard
        this.addKeyListener(new KeyAdapter() {
        	//Method for the key pressed
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
            // Method for the key released
            public void keyReleased(KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
	}

    //	Here i'm stating the method that will send the key pressed to the game class
	private void formKeyPressed(KeyEvent evt)
    {
        panel.keyPressed(evt);
    }

    //	Here i'm stating the method that will send the key released to the game class
    private void formKeyReleased(KeyEvent evt)
    {
        panel.keyReleased(evt);
    }

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setResizable(false);
		this.setBounds(new Rectangle(312, 184, windowWidth, windowHeight)); // Position on the desktop
		this.setMinimumSize(new Dimension(windowWidth, windowHeight));
		this.setMaximumSize(new Dimension(windowWidth, windowHeight));
		JPanel ramiPanel = getJContentPane();

		this.setContentPane(ramiPanel);
		this.setTitle("My Game");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main thisClass = new Main();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
}
