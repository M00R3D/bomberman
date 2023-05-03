import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class bomberman {

	private JFrame frame;
	public int aux=0;
	public ImageIcon[] spr_bomberman_caminando= {new ImageIcon("sprite_bomberman_walking_00.png"),new ImageIcon("sprite_bomberman_walking_01.png")
			,new ImageIcon("sprite_bomberman_walking_02.png")
			,new ImageIcon("sprite_bomberman_walking_03.png")
			,new ImageIcon("sprite_bomberman_walking_04.png")};
	public int max=spr_bomberman_caminando.length;
	public JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bomberman window = new bomberman();
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
	public bomberman() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel(spr_bomberman_caminando[0]);
		panel.add(lblNewLabel);
		frame.setFocusable(true);
		frame.requestFocus();
		Timer timer = new Timer();
		TimerTask animacion = new TimerTask() {

			@Override
			public void run() {
				lblNewLabel.setIcon(spr_bomberman_caminando[aux]);
				if(aux==max-1) {
					aux=0;
				}
				aux++;
				panel.repaint();
			}};
		timer.schedule(animacion,3*100,100);
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) 
				{
				case 87://  w
					lblNewLabel.setLocation(lblNewLabel.getX(), lblNewLabel.getY()-3);
					break;
				case 65: //  a
					lblNewLabel.setLocation(lblNewLabel.getX()-3, lblNewLabel.getY());
					break;
				case 83: //  s
					lblNewLabel.setLocation(lblNewLabel.getX(), lblNewLabel.getY()+3);
					break;
				case 68: //  d
					lblNewLabel.setLocation(lblNewLabel.getX()+3, lblNewLabel.getY());
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});
		

	}
	

}
