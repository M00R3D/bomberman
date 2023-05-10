import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class bomberman {

	public BufferedImage spr_wall0 ,spr_wall1,spr_bomb,spr_expl_centro,spr_expl_arriba,spr_expl_abajo,spr_expl_izquierda,spr_expl_derecha;
	private JFrame frame;
	public int aux=0,max=4,anchomapa=25,altomapa=13;
	public int cordExplIzqX0=-64,cordExplIzqY0=-64,cordExplIzqX1=-64,cordExplIzqY1=-64,cordExplIzqX2=-64,cordExplIzqY2=-64;
	public int cordExplDerX0=-64,cordExplDerY0=-64,cordExplDerX1=-64,cordExplDerY1=-64,cordExplDerX2=-64,cordExplDerY2=-64;
	public int cordExplArrX0=-64,cordExplArrY0=-64,cordExplArrX1=-64,cordExplArrY1=-64,cordExplArrX2=-64,cordExplArrY2=-64;
	public int cordExplAbaX0=-64,cordExplAbaY0=-64,cordExplAbaX1=-64,cordExplAbaY1=-64,cordExplAbaX2=-64,cordExplAbaY2=-64;
	
	public BufferedImage spr_bomberman_caminando,spr_bomberman_caminando1,spr_bomberman_caminando2,spr_bomberman_caminando3,spr_bomberman_caminando4,spr_bomberman_caminando5;
	public elemento wall_0 = new elemento(50, 50, 32, 32, spr_wall0, 1);
	public elemento player = new elemento(32, 32, 25,25,spr_bomberman_caminando);
	public int bombaX,bombaY,bombaEstar=0,explEstar=0,explX,explY;
	public elemento[] explosiones= {new elemento(32, 32, 32,32,spr_expl_centro)};
	public int[][]cuadrados= {
									{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
									{2,0,0,1,0,1,0,0,1,1,0,1,0,1,1,1,0,1,1,1,1,1,1,0,2},
									{2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,1,2,0,2,0,2,0,2,0,2},
									{2,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,1,0,1,0,1,1,2},
									{2,0,2,0,2,0,2,0,2,0,2,0,2,1,2,0,2,0,2,0,2,0,2,0,2},
									{2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,2},
									{2,0,2,0,2,0,2,0,2,0,2,0,2,1,2,0,2,0,2,0,2,0,2,0,2},
									{2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,2},
									{2,0,2,0,2,0,2,0,2,0,2,0,2,1,2,0,2,0,2,0,2,0,2,0,2},
									{2,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,2},
									{2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,2},
									{2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
									{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}};
	public elemento[] elementos= {
			new elemento(0,0,32,32,spr_wall1,2),new elemento(32,0,32,32,spr_wall1,2),new elemento(64,0,32,32,spr_wall1,2),new elemento(96,0,32,32,spr_wall1,2),new elemento(128,0,32,32,spr_wall1,2),new elemento(160,0,32,32,spr_wall1,2),new elemento(192,0,32,32,spr_wall1,2),new elemento(224,0,32,32,spr_wall1,2),new elemento(256,0,32,32,spr_wall1,2),new elemento(288,0,32,32,spr_wall1,2),new elemento(320,0,32,32,spr_wall1,2),new elemento(352,0,32,32,spr_wall1,2),new elemento(384,0,32,32,spr_wall1,2),new elemento(406,0,32,32,spr_wall1,2),new elemento(438,0,32,32,spr_wall1,2),new elemento(470,0,32,32,spr_wall1,2),new elemento(502,0,32,32,spr_wall1,2),new elemento(534,0,32,32,spr_wall1,2),new elemento(566,0,32,32,spr_wall1,2),new elemento(598,0,32,32,spr_wall1,2),new elemento(630,0,32,32,spr_wall1,2),new elemento(662,0,32,32,spr_wall1,2),new elemento(694,0,32,32,spr_wall1,2),new elemento(726,0,32,32,spr_wall1,2),new elemento(758,0,32,32,spr_wall1,2),
			new elemento(0,32,32,32,spr_wall1,2),new elemento(32,32,32,32,spr_wall0,0),new elemento(64,32,32,32,spr_wall0,0),new elemento(96,32,32,32,spr_wall0,1),new elemento(128,32,32,32,spr_wall0,0),new elemento(160,32,32,32,spr_wall0,1),new elemento(192,32,32,32,spr_wall0,0),new elemento(224,32,32,32,spr_wall0,0),new elemento(256,32,32,32,spr_wall0,1),new elemento(288,32,32,32,spr_wall0,1),new elemento(320,32,32,32,spr_wall0,0),new elemento(352,32,32,32,spr_wall0,1),new elemento(384,32,32,32,spr_wall0,0),new elemento(406,32,32,32,spr_wall0,1),new elemento(438,32,32,32,spr_wall0,1),new elemento(470,32,32,32,spr_wall0,1),new elemento(502,32,32,32,spr_wall0,0),new elemento(534,32,32,32,spr_wall0,1),new elemento(566,32,32,32,spr_wall0,1),new elemento(598,32,32,32,spr_wall0,1),new elemento(630,32,32,32,spr_wall0,1),new elemento(662,32,32,32,spr_wall0,1),new elemento(694,32,32,32,spr_wall0,1),new elemento(726,32,32,32,spr_wall0,0),new elemento(758,32,32,32,spr_wall1,2),
			new elemento(0,64,32,32,spr_wall1,2),new elemento(32,64,32,32,spr_wall0,0),new elemento(64,64,32,32,spr_wall1,2),new elemento(96,64,32,32,spr_wall0,0),new elemento(128,64,32,32,spr_wall1,2),new elemento(160,64,32,32,spr_wall0,0),new elemento(192,64,32,32,spr_wall1,2),new elemento(224,64,32,32,spr_wall0,0),new elemento(256,64,32,32,spr_wall1,2),new elemento(288,64,32,32,spr_wall0,0),new elemento(320,64,32,32,spr_wall1,2),new elemento(352,64,32,32,spr_wall0,0),new elemento(384,64,32,32,spr_wall1,2),new elemento(406,64,32,32,spr_wall0,0),new elemento(438,64,32,32,spr_wall1,2),new elemento(470,64,32,32,spr_wall0,1),new elemento(502,64,32,32,spr_wall1,2),new elemento(534,64,32,32,spr_wall0,0),new elemento(566,64,32,32,spr_wall1,2),new elemento(598,64,32,32,spr_wall0,0),new elemento(630,64,32,32,spr_wall1,2),new elemento(662,64,32,32,spr_wall0,0),new elemento(694,64,32,32,spr_wall1,2),new elemento(726,64,32,32,spr_wall0,0),new elemento(758,64,32,32,spr_wall1,2),
			new elemento(0,96,32,32,spr_wall1,2),new elemento(32,96,32,32,spr_wall0,0),new elemento(64,96,32,32,spr_wall0,0),new elemento(96,96,32,32,spr_wall0,0),new elemento(128,96,32,32,spr_wall0,0),new elemento(160,0,32,32,spr_wall0,0),new elemento(192,0,32,32,spr_wall0,0),new elemento(224,0,32,32,spr_wall0,0),new elemento(256,0,32,32,spr_wall0,0),new elemento(288,0,32,32,spr_wall0,0),new elemento(320,0,32,32,spr_wall0,0),new elemento(352,0,32,32,spr_wall0,0),new elemento(384,0,32,32,spr_wall0,0),new elemento(406,0,32,32,spr_wall0,1),new elemento(438,0,32,32,spr_wall0,1),new elemento(470,0,32,32,spr_wall0,1),new elemento(502,0,32,32,spr_wall0,0),new elemento(534,0,32,32,spr_wall0,0),new elemento(566,0,32,32,spr_wall0,1),new elemento(598,0,32,32,spr_wall0,0),new elemento(630,0,32,32,spr_wall0,1),new elemento(662,0,32,32,spr_wall0,0),new elemento(694,0,32,32,spr_wall0,1),new elemento(726,0,32,32,spr_wall0,1),new elemento(758,0,32,32,spr_wall1,2),
			new elemento(0,128,32,32,spr_wall1,2),new elemento(32,128,32,32,spr_wall0,128),new elemento(64,128,32,32,spr_wall1,2),new elemento(96,128,32,32,spr_wall0,0),new elemento(128,128,32,32,spr_wall1,2),new elemento(160,128,32,32,spr_wall0,0),new elemento(192,128,32,32,spr_wall1,2),new elemento(224,128,32,32,spr_wall0,0),new elemento(256,128,32,32,spr_wall1,2),new elemento(288,128,32,32,spr_wall0,0),new elemento(320,128,32,32,spr_wall1,2),new elemento(352,128,32,32,spr_wall0,0),new elemento(384,128,32,32,spr_wall1,2),new elemento(406,128,32,32,spr_wall0,1),new elemento(438,128,32,32,spr_wall1,2),new elemento(470,128,32,32,spr_wall0,0),new elemento(502,128,32,32,spr_wall1,2),new elemento(534,128,32,32,spr_wall0,0),new elemento(566,128,32,32,spr_wall1,2),new elemento(598,128,32,32,spr_wall0,0),new elemento(630,128,32,32,spr_wall1,2),new elemento(662,128,32,32,spr_wall0,0),new elemento(694,128,32,32,spr_wall1,2),new elemento(726,128,32,32,spr_wall0,0),new elemento(758,128,32,32,spr_wall1,2),
			new elemento(0,160,32,32,spr_wall1,2),new elemento(32,160,32,32,spr_wall0,0),new elemento(64,160,32,32,spr_wall0,0),new elemento(96,160,32,32,spr_wall0,0),new elemento(128,160,32,32,spr_wall0,0),new elemento(160,160,32,32,spr_wall0,0),new elemento(192,160,32,32,spr_wall0,0),new elemento(224,160,32,32,spr_wall0,0),new elemento(256,160,32,32,spr_wall0,0),new elemento(288,160,32,32,spr_wall0,0),new elemento(320,160,32,32,spr_wall0,0),new elemento(352,160,32,32,spr_wall0,0),new elemento(384,160,32,32,spr_wall0,0),new elemento(406,160,32,32,spr_wall0,1),new elemento(438,160,32,32,spr_wall0,0),new elemento(470,160,32,32,spr_wall0,0),new elemento(502,160,32,32,spr_wall0,0),new elemento(534,160,32,32,spr_wall0,0),new elemento(566,160,32,32,spr_wall0,0),new elemento(598,160,32,32,spr_wall0,0),new elemento(630,160,32,32,spr_wall0,0),new elemento(662,160,32,32,spr_wall0,0),new elemento(694,160,32,32,spr_wall0,0),new elemento(726,160,32,32,spr_wall0,0),new elemento(758,160,32,32,spr_wall1,2),
			new elemento(0,192,32,32,spr_wall1,2),new elemento(32,192,32,32,spr_wall0,0),new elemento(64,192,32,32,spr_wall1,2),new elemento(96,192,32,32,spr_wall0,0),new elemento(128,192,32,32,spr_wall1,2),new elemento(160,192,32,32,spr_wall0,0),new elemento(192,192,32,32,spr_wall1,2),new elemento(224,192,32,32,spr_wall0,0),new elemento(256,192,32,32,spr_wall1,2),new elemento(288,192,32,32,spr_wall0,0),new elemento(320,192,32,32,spr_wall1,2),new elemento(352,192,32,32,spr_wall0,0),new elemento(384,192,32,32,spr_wall1,2),new elemento(406,192,32,32,spr_wall0,1),new elemento(438,192,32,32,spr_wall1,2),new elemento(470,192,32,32,spr_wall0,0),new elemento(502,192,32,32,spr_wall1,2),new elemento(534,192,32,32,spr_wall0,0),new elemento(566,192,32,32,spr_wall1,2),new elemento(598,192,32,32,spr_wall0,0),new elemento(630,192,32,32,spr_wall1,2),new elemento(662,192,32,32,spr_wall0,0),new elemento(694,192,32,32,spr_wall1,2),new elemento(726,192,32,32,spr_wall0,0),new elemento(758,192,32,32,spr_wall1,2),
			new elemento(0,224,32,32,spr_wall1,2),new elemento(32,224,32,32,spr_wall0,0),new elemento(64,224,32,32,spr_wall0,0),new elemento(96,224,32,32,spr_wall0,0),new elemento(128,224,32,32,spr_wall0,0),new elemento(160,224,32,32,spr_wall0,0),new elemento(192,224,32,32,spr_wall0,0),new elemento(224,224,32,32,spr_wall0,0),new elemento(256,224,32,32,spr_wall0,0),new elemento(288,224,32,32,spr_wall0,0),new elemento(320,224,32,32,spr_wall0,0),new elemento(352,224,32,32,spr_wall0,0),new elemento(384,224,32,32,spr_wall0,0),new elemento(406,224,32,32,spr_wall0,1),new elemento(438,224,32,32,spr_wall0,0),new elemento(470,224,32,32,spr_wall0,0),new elemento(502,224,32,32,spr_wall0,0),new elemento(534,224,32,32,spr_wall0,0),new elemento(566,224,32,32,spr_wall0,0),new elemento(598,224,32,32,spr_wall0,0),new elemento(630,224,32,32,spr_wall0,0),new elemento(662,224,32,32,spr_wall0,0),new elemento(694,224,32,32,spr_wall0,0),new elemento(726,224,32,32,spr_wall0,0),new elemento(758,224,32,32,spr_wall1,2),
			new elemento(0,256,32,32,spr_wall1,2),new elemento(32,256,32,32,spr_wall0,0),new elemento(64,256,32,32,spr_wall1,2),new elemento(96,256,32,32,spr_wall0,0),new elemento(128,256,32,32,spr_wall1,2),new elemento(160,256,32,32,spr_wall0,0),new elemento(192,256,32,32,spr_wall1,2),new elemento(224,256,32,32,spr_wall0,0),new elemento(256,256,32,32,spr_wall1,2),new elemento(288,256,32,32,spr_wall0,0),new elemento(320,256,32,32,spr_wall1,2),new elemento(352,256,32,32,spr_wall0,0),new elemento(384,256,32,32,spr_wall1,2),new elemento(406,256,32,32,spr_wall0,1),new elemento(438,256,32,32,spr_wall1,2),new elemento(470,256,32,32,spr_wall0,0),new elemento(502,256,32,32,spr_wall1,2),new elemento(534,256,32,32,spr_wall0,0),new elemento(566,256,32,32,spr_wall1,2),new elemento(598,256,32,32,spr_wall0,0),new elemento(630,256,32,32,spr_wall1,2),new elemento(662,256,32,32,spr_wall0,0),new elemento(694,256,32,32,spr_wall1,2),new elemento(726,256,32,32,spr_wall0,0),new elemento(758,256,32,32,spr_wall1,2),
			new elemento(0,288,32,32,spr_wall1,2),new elemento(32,288,32,32,spr_wall0,0),new elemento(64,288,32,32,spr_wall0,0),new elemento(96,288,32,32,spr_wall0,0),new elemento(128,288,32,32,spr_wall0,0),new elemento(160,288,32,32,spr_wall0,0),new elemento(192,288,32,32,spr_wall0,0),new elemento(224,288,32,32,spr_wall0,0),new elemento(256,288,32,32,spr_wall0,0),new elemento(288,288,32,32,spr_wall0,0),new elemento(320,288,32,32,spr_wall0,0),new elemento(352,288,32,32,spr_wall0,0),new elemento(384,288,32,32,spr_wall0,0),new elemento(406,288,32,32,spr_wall0,1),new elemento(438,288,32,32,spr_wall0,0),new elemento(470,288,32,32,spr_wall0,0),new elemento(502,288,32,32,spr_wall0,0),new elemento(534,288,32,32,spr_wall0,0),new elemento(566,288,32,32,spr_wall0,0),new elemento(598,288,32,32,spr_wall0,0),new elemento(630,288,32,32,spr_wall0,0),new elemento(662,288,32,32,spr_wall0,0),new elemento(694,288,32,32,spr_wall0,0),new elemento(726,288,32,32,spr_wall0,0),new elemento(758,288,32,32,spr_wall1,2),
			new elemento(0,320,32,32,spr_wall1,2),new elemento(32,320,32,32,spr_wall0,0),new elemento(64,320,32,32,spr_wall1,2),new elemento(96,320,32,32,spr_wall0,0),new elemento(128,320,32,32,spr_wall1,2),new elemento(160,320,32,32,spr_wall0,0),new elemento(192,320,32,32,spr_wall1,2),new elemento(224,320,32,32,spr_wall0,0),new elemento(256,320,32,32,spr_wall1,2),new elemento(288,320,32,32,spr_wall0,0),new elemento(320,320,32,32,spr_wall1,2),new elemento(352,320,32,32,spr_wall0,0),new elemento(384,320,32,32,spr_wall1,2),new elemento(406,320,32,32,spr_wall0,0),new elemento(438,320,32,32,spr_wall1,2),new elemento(470,320,32,32,spr_wall0,0),new elemento(502,320,32,32,spr_wall1,2),new elemento(534,320,32,32,spr_wall0,0),new elemento(566,320,32,32,spr_wall1,2),new elemento(598,320,32,32,spr_wall0,0),new elemento(630,320,32,32,spr_wall1,2),new elemento(662,320,32,32,spr_wall0,0),new elemento(694,320,32,32,spr_wall1,2),new elemento(726,320,32,32,spr_wall0,0),new elemento(758,320,32,32,spr_wall1,2),
			new elemento(0,352,32,32,spr_wall1,2),new elemento(32,352,32,32,spr_wall0,0),new elemento(64,352,32,32,spr_wall0,0),new elemento(96,352,32,32,spr_wall0,0),new elemento(128,352,32,32,spr_wall0,0),new elemento(160,352,32,32,spr_wall0,0),new elemento(192,352,32,32,spr_wall0,0),new elemento(224,352,32,32,spr_wall0,0),new elemento(256,352,32,32,spr_wall0,0),new elemento(288,352,32,32,spr_wall0,0),new elemento(320,352,32,32,spr_wall0,0),new elemento(352,352,32,32,spr_wall0,0),new elemento(384,352,32,32,spr_wall0,0),new elemento(406,352,32,32,spr_wall0,0),new elemento(438,352,32,32,spr_wall0,0),new elemento(470,352,32,32,spr_wall0,0),new elemento(502,352,32,32,spr_wall0,0),new elemento(534,352,32,32,spr_wall0,0),new elemento(566,352,32,32,spr_wall0,0),new elemento(598,352,32,32,spr_wall0,0),new elemento(630,352,32,32,spr_wall0,0),new elemento(662,352,32,32,spr_wall0,0),new elemento(694,352,32,32,spr_wall0,0),new elemento(726,352,32,32,spr_wall0,0),new elemento(758,352,32,32,spr_wall1,2),
			new elemento(0,384,32,32,spr_wall1,2),new elemento(32,384,32,32,spr_wall1,2),new elemento(64,384,32,32,spr_wall1,2),new elemento(96,384,32,32,spr_wall1,2),new elemento(128,384,32,32,spr_wall1,2),new elemento(160,384,32,32,spr_wall1,2),new elemento(192,384,32,32,spr_wall1,2),new elemento(224,384,32,32,spr_wall1,2),new elemento(256,384,32,32,spr_wall1,2),new elemento(288,384,32,32,spr_wall1,2),new elemento(320,384,32,32,spr_wall1,2),new elemento(352,384,32,32,spr_wall1,2),new elemento(384,384,32,32,spr_wall1,2),new elemento(406,384,32,32,spr_wall1,2),new elemento(438,384,32,32,spr_wall1,2),new elemento(470,384,32,32,spr_wall1,2),new elemento(502,384,32,32,spr_wall1,2),new elemento(534,384,32,32,spr_wall1,2),new elemento(566,384,32,32,spr_wall1,2),new elemento(598,384,32,32,spr_wall1,2),new elemento(630,384,32,32,spr_wall1,2),new elemento(662,384,32,32,spr_wall1,2),new elemento(694,384,32,32,spr_wall1,2),new elemento(726,384,32,32,spr_wall1,2),new elemento(758,384,32,32,spr_wall1,2)};
	//25ancho	800px*13alto	416px
	public elemento[] bombas;
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
		frame.setBounds(0, 0, 800, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(new Color(255, 128, 192));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		frame.setFocusable(true);
		frame.requestFocus();
		panel.add(new MyGraphics());
		try {
			spr_wall0=ImageIO.read(new File("sprite_wall0.png"));
			spr_wall1=ImageIO.read(new File("sprite_brick0.png"));
			spr_bomb=ImageIO.read(new File("sprite_bomb0.png"));
			spr_expl_centro=ImageIO.read(new File("sprite_expl0.png"));
			spr_expl_arriba=ImageIO.read(new File("sprite_expl1.png"));
			spr_expl_izquierda=ImageIO.read(new File("sprite_expl2.png"));
			spr_expl_abajo=ImageIO.read(new File("sprite_expl3.png"));
			spr_expl_derecha=ImageIO.read(new File("sprite_expl4.png"));
			wall_0.setBi(spr_wall0);
			spr_bomberman_caminando=ImageIO.read(new File("sprite_bomberman_walking_00.png"));spr_bomberman_caminando1=ImageIO.read(new File("sprite_bomberman_walking_01.png"));spr_bomberman_caminando2=ImageIO.read(new File("sprite_bomberman_walking_02.png"));spr_bomberman_caminando3=ImageIO.read(new File("sprite_bomberman_walking_03.png"));spr_bomberman_caminando4=ImageIO.read(new File("sprite_bomberman_walking_04.png"));player.setBi(spr_bomberman_caminando);
				
		} catch (IOException e) {e.printStackTrace();}
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.print(e.getKeyCode());
				switch(e.getKeyCode()) 
				{
				case 87://  w
					player.setY(player.getY()-3);
					break;
				case 65: //  a
					player.setX(player.getX()-3);
					break;
				case 83: //  s
					player.setY(player.getY()+3);
					break;
				case 68: //  d
					player.setX(player.getX()+3);
					break;
				case 32: //  espacio
					bombaX=player.getX();
					bombaY=player.getY();
					bombaEstar=1;
					panel.repaint();
					break;
				}
				for(int a =0;a<elementos.length-1;a++) 
	            {
						if(player.colision(elementos[a]) && elementos[a].getTipo()!=0 &&elementos[a].getTipo()!=3) 
			            {	
		//	            	System.out.println("choca");
			            	switch(e.getKeyCode()) 
							{
							case 87://  w
								player.setY(player.getY()+3);
								break;
							case 65: //  a
								player.setX(player.getX()+3);
								break;
							case 83: //  s
								player.setY(player.getY()-3);
								break;
							case 68: //  d
								player.setX(player.getX()-3);
								break;
							}
			            }
	            }
				panel.repaint();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}});
		Timer timer = new Timer();
        TimerTask animacion = new TimerTask() {

            @Override
            public void run() {
            		aux++;
            		switch(aux) {
            		case 0:
            			player.setBi(spr_bomberman_caminando);
            			break;
            		case 1:
            			player.setBi(spr_bomberman_caminando1);
            			break;
            		case 2:
            			player.setBi(spr_bomberman_caminando2);
            			break;
            		case 3:
            			player.setBi(spr_bomberman_caminando3);
            			break;
            		case 4:
            			player.setBi(spr_bomberman_caminando4);
            			break;
            		}
            		if(aux==max) {aux=0;}
            		if(bombaEstar==2){explX=(bombaX/32)*32;
            										explY=(bombaY/32)*32;}
                    panel.repaint();
            	
//            	System.out.println("animacion"+aux);
            }};
        timer.schedule(animacion,3*1000,100);
       
	}

	public class MyGraphics extends JComponent {

        private static final long serialVersionUID = 1L;

        MyGraphics() {
            setPreferredSize(new Dimension(800, 416));
        }

        @Override
        public void paintComponent(Graphics g) {
        	super.paintComponent(g);
            g.setColor(Color.black);
            g.drawImage(wall_0.bi,wall_0.x,wall_0.y,wall_0.w, wall_0.h, this);
            g.drawImage(player.bi,player.x,player.y,player.w, player.h, this);
            for(int a =0;a<elementos.length-1;a++) 
            {
//            	g.drawRect(elementos[a].x,elementos[a].y,elementos[a].w, elementos[a].h);
            	if(elementos[a].getTipo()==1) {g.drawImage(spr_wall1,elementos[a].x,elementos[a].y,elementos[a].w, elementos[a].h, this);}
            	if(elementos[a].getTipo()==2) {g.drawImage(spr_wall0,elementos[a].x,elementos[a].y,elementos[a].w, elementos[a].h, this);}
            	if(elementos[a].getTipo()==3) {g.drawImage(spr_bomb,elementos[a].x,elementos[a].y,elementos[a].w, elementos[a].h, this);}
            	if (new elemento(explX-31,explY,32,32,spr_expl_centro,3).colision(elementos[a])==true && elementos[a].getTipo()==0) {
        			System.out.println("jalo");
        		}else {
			        			cordExplIzqX0=explX-32;
			        			cordExplIzqY0=explY;
				        			if (new elemento(explX-63,explY,32,32,spr_expl_centro,3).colision(elementos[a])==true && elementos[a].getTipo()!=0) {
				            			System.out.println("jalo");
					            		}else {
					            			cordExplIzqX1=explX-64;
					            			cordExplIzqY1=explY;
					            			if (new elemento(explX-95,explY,32,32,spr_expl_centro,3).colision(elementos[a])==true && elementos[a].getTipo()!=0) {
						            			System.out.println("jalo");
							            		}else {
							            			cordExplIzqX2=explX-96;
							            			cordExplIzqY2=explY;
							            		}
					            		}
        			}
//            	if (new elemento(explX+32,explY,32,32,spr_expl_centro,3).colision(elementos[a])==true && elementos[a].getTipo()!=0) {
//        			System.out.println("jalo");
//        		}else {
//        			explEstar=1;
//        		}
//            	if (new elemento(explX,explY-32,32,32,spr_expl_centro,3).colision(elementos[a])==true && elementos[a].getTipo()!=0) {
//        			System.out.println("jalo");
//        		}else {
//        			explEstar=1;
//        		}
//            	if (new elemento(explX,explY+32,32,32,spr_expl_centro,3).colision(elementos[a])==true && elementos[a].getTipo()!=0) {
//        			System.out.println("jalo");
//        		}else {
//        			explEstar=1;
//        		}
            }
//            for(int a =0;a<explosiones.length-1;a++) 
//            {
//            	g.drawImage(explosiones[a].getBi(),explosiones[a].x,explosiones[a].y,explosiones[a].w, explosiones[a].h, this);
//            }
            
            if(bombaEstar==1)
            {
            	g.drawImage(spr_bomb,bombaX,bombaY,20, 20, this);
            	System.out.println(bombaX+" "+bombaY+"        "+(bombaX/32)+" "+(bombaY/32));
        		Timer timer1 = new Timer();
                TimerTask explosion = new TimerTask() {

                    @Override
                    public void run() {
                    		bombaEstar=2;
                            panel.repaint();
                            timer1.cancel();
                    }};
                timer1.schedule(explosion,6*1000,100);
               
            }else if(bombaEstar==2)
            {
            	g.drawImage(spr_expl_centro,explX,explY,32, 32, this);
            	Timer timer2 = new Timer();
                TimerTask explosion1 = new TimerTask() {

                    @Override
                    public void run() {
                    	bombaEstar=0;
                    	explEstar=0;
                    	cordExplIzqX0=0;cordExplIzqX1=0;cordExplIzqX2=0;cordExplIzqY0=0;cordExplIzqY1=0;cordExplIzqY2=0;
                        panel.repaint();    
                    	timer2.cancel();
                    }};
                timer2.schedule(explosion1,1*1000,1000);
            }
            if(explEstar==1) 
            {
            	g.drawImage(spr_expl_izquierda,cordExplIzqX0,cordExplIzqY0,32, 32, this);
            	g.drawImage(spr_expl_izquierda,cordExplIzqX1,cordExplIzqY1,32, 32, this);
            	g.drawImage(spr_expl_izquierda,cordExplIzqX2,cordExplIzqY2,32, 32, this);
            }
            	
        }
    }


	public class elemento 
	{
		//tipo 0:vacio		tipo 1:bloque indestructible		 tipo 2 :bloque destructible
		int x,y,h,w,tipo;
		BufferedImage bi;
		elemento(int x,int y,int h,int w, BufferedImage bi,int t)
		{this.x=x;this.y=y;this.h=h;this.w=w;this.bi=bi;this.tipo=t;}
		public elemento(int x, int y, int h, int w, BufferedImage bi) 
		{this.x=x;	this.y=y;	this.h=h;	this.w=w; this.bi=bi;}

		public elemento() {
			// TODO Auto-generated constructor stub
		}
		public boolean colision(elemento e) 
		{if(this.x<e.x +e.w && this.x+this.w>e.x && this.y<e.y +e.h && this.y+this.h>e.y ) {return true;}else {return false;}}
		public BufferedImage getBi() {return bi;}
		public void setBi(BufferedImage bi) {this.bi = bi;}
		public int getX() {return x;}
		public void setX(int x) {this.x = x;}
		public int getY() {return y;}
		public void setY(int y) {this.y = y;}
		public int getH() {return h;}
		public void setH(int h) {this.h = h;}
		public int getW() {return w;}
		public void setW(int w) {this.w = w;}
		public int getTipo() {return tipo;}
		public void setTipo(int t) {this.tipo = t;}
	}
	
	
}
