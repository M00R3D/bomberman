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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class bomberman {

	public BufferedImage spr_wall0 ,spr_wall1,spr_bomb,spr_expl_centro,spr_expl_arriba,spr_expl_abajo,spr_expl_izquierda,spr_expl_derecha,spr_enemigo;
	private JFrame frame;
	public int aux=0,max=4,anchomapa=25,altomapa=13;
	public BufferedImage spr_bomberman_caminando,spr_bomberman_caminando1,spr_bomberman_caminando2,spr_bomberman_caminando3,spr_bomberman_caminando4,spr_bomberman_caminando5;
	public elemento wall_0 = new elemento(50, 50, 32, 32, spr_wall0, 1);
	public elemento player = new elemento(32, 32, 25,25,spr_bomberman_caminando);
	public int bombaX,bombaY,bombaEstar=0,explEstar=0,explX,explY;
	public elemento explosion_superior = new elemento(-64, -64, 32,32,spr_expl_arriba);
	public elemento explosion_izquierda = new elemento(-64, -64, 32,32,spr_expl_izquierda);
	public elemento explosion_derecha = new elemento(-64, -64, 32,32,spr_expl_derecha);
	public elemento explosion_inferior = new elemento(-64, -64, 32,32,spr_expl_abajo);	
	public elemento enemigo1 = new elemento(11*32, 5*32, 32,32,spr_enemigo);
	public elemento enemigo2 = new elemento(16*32, 7*32, 32,32,spr_enemigo);
	public elemento enemigo3 = new elemento(18*32, 3*32, 32,32,spr_enemigo);

	public elemento[] explosiones={explosion_superior,explosion_izquierda,explosion_derecha,explosion_inferior};
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
			new elemento(0,0,32,32,spr_wall1,2),new elemento(32,0,32,32,spr_wall1,2),new elemento(64,0,32,32,spr_wall1,2),new elemento(96,0,32,32,spr_wall1,2),new elemento(128,0,32,32,spr_wall1,2),new elemento(160,0,32,32,spr_wall1,2),new elemento(192,0,32,32,spr_wall1,2),new elemento(224,0,32,32,spr_wall1,2),new elemento(256,0,32,32,spr_wall1,2),new elemento(288,0,32,32,spr_wall1,2),new elemento(320,0,32,32,spr_wall1,2),new elemento(352,0,32,32,spr_wall1,2),new elemento(384,0,32,32,spr_wall1,2),new elemento(416,0,32,32,spr_wall1,2),new elemento(448,0,32,32,spr_wall1,2),new elemento(480,0,32,32,spr_wall1,2),new elemento(512,0,32,32,spr_wall1,2),new elemento(544,0,32,32,spr_wall1,2),new elemento(576,0,32,32,spr_wall1,2),new elemento(608,0,32,32,spr_wall1,2),new elemento(640,0,32,32,spr_wall1,2),new elemento(672,0,32,32,spr_wall1,2),new elemento(704,0,32,32,spr_wall1,2),new elemento(736,0,32,32,spr_wall1,2),new elemento(768,0,32,32,spr_wall1,2),
			new elemento(0,32,32,32,spr_wall1,2),new elemento(32,32,32,32,spr_wall0,0),new elemento(64,32,32,32,spr_wall0,0),new elemento(96,32,32,32,spr_wall0,1),new elemento(128,32,32,32,spr_wall0,0),new elemento(160,32,32,32,spr_wall0,1),new elemento(192,32,32,32,spr_wall0,0),new elemento(224,32,32,32,spr_wall0,0),new elemento(256,32,32,32,spr_wall0,1),new elemento(288,32,32,32,spr_wall0,1),new elemento(320,32,32,32,spr_wall0,0),new elemento(352,32,32,32,spr_wall0,1),new elemento(384,32,32,32,spr_wall0,0),new elemento(416,32,32,32,spr_wall0,1),new elemento(448,32,32,32,spr_wall0,1),new elemento(480,32,32,32,spr_wall0,1),new elemento(512,32,32,32,spr_wall0,0),new elemento(544,32,32,32,spr_wall0,1),new elemento(576,32,32,32,spr_wall0,1),new elemento(608,32,32,32,spr_wall0,1),new elemento(640,32,32,32,spr_wall0,1),new elemento(672,32,32,32,spr_wall0,1),new elemento(704,32,32,32,spr_wall0,1),new elemento(736,32,32,32,spr_wall0,0),new elemento(768,32,32,32,spr_wall1,2),
			new elemento(0,64,32,32,spr_wall1,2),new elemento(32,64,32,32,spr_wall0,0),new elemento(64,64,32,32,spr_wall1,2),new elemento(96,64,32,32,spr_wall0,0),new elemento(128,64,32,32,spr_wall1,2),new elemento(160,64,32,32,spr_wall0,0),new elemento(192,64,32,32,spr_wall1,2),new elemento(224,64,32,32,spr_wall0,0),new elemento(256,64,32,32,spr_wall1,2),new elemento(288,64,32,32,spr_wall0,0),new elemento(320,64,32,32,spr_wall1,2),new elemento(352,64,32,32,spr_wall0,0),new elemento(384,64,32,32,spr_wall1,2),new elemento(416,64,32,32,spr_wall0,0),new elemento(448,64,32,32,spr_wall1,2),new elemento(480,64,32,32,spr_wall0,1),new elemento(512,64,32,32,spr_wall1,2),new elemento(544,64,32,32,spr_wall0,0),new elemento(576,64,32,32,spr_wall1,2),new elemento(608,64,32,32,spr_wall0,0),new elemento(640,64,32,32,spr_wall1,2),new elemento(672,64,32,32,spr_wall0,0),new elemento(704,64,32,32,spr_wall1,2),new elemento(736,64,32,32,spr_wall0,0),new elemento(768,64,32,32,spr_wall1,2),
			new elemento(0,96,32,32,spr_wall1,2),new elemento(32,96,32,32,spr_wall0,0),new elemento(64,96,32,32,spr_wall0,0),new elemento(96,96,32,32,spr_wall0,0),new elemento(128,96,32,32,spr_wall0,0),new elemento(160,0,32,32,spr_wall0,0),new elemento(192,0,32,32,spr_wall0,0),new elemento(224,0,32,32,spr_wall0,0),new elemento(256,0,32,32,spr_wall0,0),new elemento(288,0,32,32,spr_wall0,0),new elemento(320,0,32,32,spr_wall0,0),new elemento(352,0,32,32,spr_wall0,0),new elemento(384,0,32,32,spr_wall0,0),new elemento(416,0,32,32,spr_wall0,1),new elemento(448,0,32,32,spr_wall0,1),new elemento(480,0,32,32,spr_wall0,1),new elemento(512,0,32,32,spr_wall0,0),new elemento(544,0,32,32,spr_wall0,0),new elemento(576,0,32,32,spr_wall0,1),new elemento(608,0,32,32,spr_wall0,0),new elemento(640,0,32,32,spr_wall0,1),new elemento(672,0,32,32,spr_wall0,0),new elemento(704,0,32,32,spr_wall0,1),new elemento(736,0,32,32,spr_wall0,1),new elemento(768,0,32,32,spr_wall1,2),
			new elemento(0,128,32,32,spr_wall1,2),new elemento(32,128,32,32,spr_wall0,128),new elemento(64,128,32,32,spr_wall1,2),new elemento(96,128,32,32,spr_wall0,0),new elemento(128,128,32,32,spr_wall1,2),new elemento(160,128,32,32,spr_wall0,0),new elemento(192,128,32,32,spr_wall1,2),new elemento(224,128,32,32,spr_wall0,0),new elemento(256,128,32,32,spr_wall1,2),new elemento(288,128,32,32,spr_wall0,0),new elemento(320,128,32,32,spr_wall1,2),new elemento(352,128,32,32,spr_wall0,0),new elemento(384,128,32,32,spr_wall1,2),new elemento(416,128,32,32,spr_wall0,1),new elemento(448,128,32,32,spr_wall1,2),new elemento(480,128,32,32,spr_wall0,0),new elemento(512,128,32,32,spr_wall1,2),new elemento(544,128,32,32,spr_wall0,0),new elemento(576,128,32,32,spr_wall1,2),new elemento(608,128,32,32,spr_wall0,0),new elemento(640,128,32,32,spr_wall1,2),new elemento(672,128,32,32,spr_wall0,0),new elemento(704,128,32,32,spr_wall1,2),new elemento(736,128,32,32,spr_wall0,0),new elemento(768,128,32,32,spr_wall1,2),
			new elemento(0,160,32,32,spr_wall1,2),new elemento(32,160,32,32,spr_wall0,0),new elemento(64,160,32,32,spr_wall0,0),new elemento(96,160,32,32,spr_wall0,0),new elemento(128,160,32,32,spr_wall0,0),new elemento(160,160,32,32,spr_wall0,0),new elemento(192,160,32,32,spr_wall0,0),new elemento(224,160,32,32,spr_wall0,0),new elemento(256,160,32,32,spr_wall0,0),new elemento(288,160,32,32,spr_wall0,0),new elemento(320,160,32,32,spr_wall0,0),new elemento(352,160,32,32,spr_wall0,0),new elemento(384,160,32,32,spr_wall0,0),new elemento(416,160,32,32,spr_wall0,1),new elemento(448,160,32,32,spr_wall0,0),new elemento(480,160,32,32,spr_wall0,0),new elemento(512,160,32,32,spr_wall0,0),new elemento(544,160,32,32,spr_wall0,0),new elemento(576,160,32,32,spr_wall0,0),new elemento(608,160,32,32,spr_wall0,0),new elemento(640,160,32,32,spr_wall0,0),new elemento(672,160,32,32,spr_wall0,0),new elemento(704,160,32,32,spr_wall0,0),new elemento(736,160,32,32,spr_wall0,0),new elemento(768,160,32,32,spr_wall1,2),
			new elemento(0,192,32,32,spr_wall1,2),new elemento(32,192,32,32,spr_wall0,0),new elemento(64,192,32,32,spr_wall1,2),new elemento(96,192,32,32,spr_wall0,0),new elemento(128,192,32,32,spr_wall1,2),new elemento(160,192,32,32,spr_wall0,0),new elemento(192,192,32,32,spr_wall1,2),new elemento(224,192,32,32,spr_wall0,0),new elemento(256,192,32,32,spr_wall1,2),new elemento(288,192,32,32,spr_wall0,0),new elemento(320,192,32,32,spr_wall1,2),new elemento(352,192,32,32,spr_wall0,0),new elemento(384,192,32,32,spr_wall1,2),new elemento(416,192,32,32,spr_wall0,1),new elemento(448,192,32,32,spr_wall1,2),new elemento(480,192,32,32,spr_wall0,0),new elemento(512,192,32,32,spr_wall1,2),new elemento(544,192,32,32,spr_wall0,0),new elemento(576,192,32,32,spr_wall1,2),new elemento(608,192,32,32,spr_wall0,0),new elemento(640,192,32,32,spr_wall1,2),new elemento(672,192,32,32,spr_wall0,0),new elemento(704,192,32,32,spr_wall1,2),new elemento(736,192,32,32,spr_wall0,0),new elemento(768,192,32,32,spr_wall1,2),
			new elemento(0,224,32,32,spr_wall1,2),new elemento(32,224,32,32,spr_wall0,0),new elemento(64,224,32,32,spr_wall0,0),new elemento(96,224,32,32,spr_wall0,0),new elemento(128,224,32,32,spr_wall0,0),new elemento(160,224,32,32,spr_wall0,0),new elemento(192,224,32,32,spr_wall0,0),new elemento(224,224,32,32,spr_wall0,0),new elemento(256,224,32,32,spr_wall0,0),new elemento(288,224,32,32,spr_wall0,0),new elemento(320,224,32,32,spr_wall0,0),new elemento(352,224,32,32,spr_wall0,0),new elemento(384,224,32,32,spr_wall0,0),new elemento(416,224,32,32,spr_wall0,1),new elemento(448,224,32,32,spr_wall0,0),new elemento(480,224,32,32,spr_wall0,0),new elemento(512,224,32,32,spr_wall0,0),new elemento(544,224,32,32,spr_wall0,0),new elemento(576,224,32,32,spr_wall0,0),new elemento(608,224,32,32,spr_wall0,0),new elemento(640,224,32,32,spr_wall0,0),new elemento(672,224,32,32,spr_wall0,0),new elemento(704,224,32,32,spr_wall0,0),new elemento(736,224,32,32,spr_wall0,0),new elemento(768,224,32,32,spr_wall1,2),
			new elemento(0,256,32,32,spr_wall1,2),new elemento(32,256,32,32,spr_wall0,0),new elemento(64,256,32,32,spr_wall1,2),new elemento(96,256,32,32,spr_wall0,0),new elemento(128,256,32,32,spr_wall1,2),new elemento(160,256,32,32,spr_wall0,0),new elemento(192,256,32,32,spr_wall1,2),new elemento(224,256,32,32,spr_wall0,0),new elemento(256,256,32,32,spr_wall1,2),new elemento(288,256,32,32,spr_wall0,0),new elemento(320,256,32,32,spr_wall1,2),new elemento(352,256,32,32,spr_wall0,0),new elemento(384,256,32,32,spr_wall1,2),new elemento(416,256,32,32,spr_wall0,1),new elemento(448,256,32,32,spr_wall1,2),new elemento(480,256,32,32,spr_wall0,0),new elemento(512,256,32,32,spr_wall1,2),new elemento(544,256,32,32,spr_wall0,0),new elemento(576,256,32,32,spr_wall1,2),new elemento(608,256,32,32,spr_wall0,0),new elemento(640,256,32,32,spr_wall1,2),new elemento(672,256,32,32,spr_wall0,0),new elemento(704,256,32,32,spr_wall1,2),new elemento(736,256,32,32,spr_wall0,0),new elemento(768,256,32,32,spr_wall1,2),
			new elemento(0,288,32,32,spr_wall1,2),new elemento(32,288,32,32,spr_wall0,0),new elemento(64,288,32,32,spr_wall0,0),new elemento(96,288,32,32,spr_wall0,0),new elemento(128,288,32,32,spr_wall0,0),new elemento(160,288,32,32,spr_wall0,0),new elemento(192,288,32,32,spr_wall0,0),new elemento(224,288,32,32,spr_wall0,0),new elemento(256,288,32,32,spr_wall0,0),new elemento(288,288,32,32,spr_wall0,0),new elemento(320,288,32,32,spr_wall0,0),new elemento(352,288,32,32,spr_wall0,0),new elemento(384,288,32,32,spr_wall0,0),new elemento(416,288,32,32,spr_wall0,1),new elemento(448,288,32,32,spr_wall0,0),new elemento(480,288,32,32,spr_wall0,0),new elemento(512,288,32,32,spr_wall0,0),new elemento(544,288,32,32,spr_wall0,0),new elemento(576,288,32,32,spr_wall0,0),new elemento(608,288,32,32,spr_wall0,0),new elemento(640,288,32,32,spr_wall0,0),new elemento(672,288,32,32,spr_wall0,0),new elemento(704,288,32,32,spr_wall0,0),new elemento(736,288,32,32,spr_wall0,0),new elemento(768,288,32,32,spr_wall1,2),
			new elemento(0,320,32,32,spr_wall1,2),new elemento(32,320,32,32,spr_wall0,0),new elemento(64,320,32,32,spr_wall1,2),new elemento(96,320,32,32,spr_wall0,0),new elemento(128,320,32,32,spr_wall1,2),new elemento(160,320,32,32,spr_wall0,0),new elemento(192,320,32,32,spr_wall1,2),new elemento(224,320,32,32,spr_wall0,0),new elemento(256,320,32,32,spr_wall1,2),new elemento(288,320,32,32,spr_wall0,0),new elemento(320,320,32,32,spr_wall1,2),new elemento(352,320,32,32,spr_wall0,0),new elemento(384,320,32,32,spr_wall1,2),new elemento(416,320,32,32,spr_wall0,0),new elemento(448,320,32,32,spr_wall1,2),new elemento(480,320,32,32,spr_wall0,0),new elemento(512,320,32,32,spr_wall1,2),new elemento(544,320,32,32,spr_wall0,0),new elemento(576,320,32,32,spr_wall1,2),new elemento(608,320,32,32,spr_wall0,0),new elemento(640,320,32,32,spr_wall1,2),new elemento(672,320,32,32,spr_wall0,0),new elemento(704,320,32,32,spr_wall1,2),new elemento(736,320,32,32,spr_wall0,0),new elemento(768,320,32,32,spr_wall1,2),
			new elemento(0,352,32,32,spr_wall1,2),new elemento(32,352,32,32,spr_wall0,0),new elemento(64,352,32,32,spr_wall0,0),new elemento(96,352,32,32,spr_wall0,0),new elemento(128,352,32,32,spr_wall0,0),new elemento(160,352,32,32,spr_wall0,0),new elemento(192,352,32,32,spr_wall0,0),new elemento(224,352,32,32,spr_wall0,0),new elemento(256,352,32,32,spr_wall0,0),new elemento(288,352,32,32,spr_wall0,0),new elemento(320,352,32,32,spr_wall0,0),new elemento(352,352,32,32,spr_wall0,0),new elemento(384,352,32,32,spr_wall0,0),new elemento(416,352,32,32,spr_wall0,0),new elemento(448,352,32,32,spr_wall0,0),new elemento(480,352,32,32,spr_wall0,0),new elemento(512,352,32,32,spr_wall0,0),new elemento(544,352,32,32,spr_wall0,0),new elemento(576,352,32,32,spr_wall0,0),new elemento(608,352,32,32,spr_wall0,0),new elemento(640,352,32,32,spr_wall0,0),new elemento(672,352,32,32,spr_wall0,0),new elemento(704,352,32,32,spr_wall0,0),new elemento(736,352,32,32,spr_wall0,0),new elemento(768,352,32,32,spr_wall1,2),
			new elemento(0,384,32,32,spr_wall1,2),new elemento(32,384,32,32,spr_wall1,2),new elemento(64,384,32,32,spr_wall1,2),new elemento(96,384,32,32,spr_wall1,2),new elemento(128,384,32,32,spr_wall1,2),new elemento(160,384,32,32,spr_wall1,2),new elemento(192,384,32,32,spr_wall1,2),new elemento(224,384,32,32,spr_wall1,2),new elemento(256,384,32,32,spr_wall1,2),new elemento(288,384,32,32,spr_wall1,2),new elemento(320,384,32,32,spr_wall1,2),new elemento(352,384,32,32,spr_wall1,2),new elemento(384,384,32,32,spr_wall1,2),new elemento(416,384,32,32,spr_wall1,2),new elemento(448,384,32,32,spr_wall1,2),new elemento(480,384,32,32,spr_wall1,2),new elemento(512,384,32,32,spr_wall1,2),new elemento(544,384,32,32,spr_wall1,2),new elemento(576,384,32,32,spr_wall1,2),new elemento(608,384,32,32,spr_wall1,2),new elemento(640,384,32,32,spr_wall1,2),new elemento(672,384,32,32,spr_wall1,2),new elemento(704,384,32,32,spr_wall1,2),new elemento(736,384,32,32,spr_wall1,2),new elemento(768,384,32,32,spr_wall1,2)};
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
		frame.setBounds(0, 0, 812, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(new Color(255, 128, 192));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		frame.setFocusable(true);
		frame.requestFocus();
		panel.add(new MyGraphics());
		try {
			ReproducirSonido("BombermanMusica.wav");
			spr_wall0=ImageIO.read(new File("sprite_wall0.png"));
			spr_wall1=ImageIO.read(new File("sprite_brick0.png"));
			spr_bomb=ImageIO.read(new File("sprite_bomb0.png"));
			spr_enemigo = ImageIO.read(new File("Enemigo.png"));
			spr_expl_centro=ImageIO.read(new File("sprite_expl0.png"));
			spr_expl_arriba=ImageIO.read(new File("sprite_expl1.png"));
			spr_expl_izquierda=ImageIO.read(new File("sprite_expl2.png"));
			spr_expl_abajo=ImageIO.read(new File("sprite_expl3.png"));
			spr_expl_derecha=ImageIO.read(new File("sprite_expl4.png"));
//			wall_0.setBi(spr_wall0);
			spr_bomberman_caminando=ImageIO.read(new File("sprite_bomberman_walking_00.png"));spr_bomberman_caminando1=ImageIO.read(new File("sprite_bomberman_walking_01.png"));spr_bomberman_caminando2=ImageIO.read(new File("sprite_bomberman_walking_02.png"));spr_bomberman_caminando3=ImageIO.read(new File("sprite_bomberman_walking_03.png"));spr_bomberman_caminando4=ImageIO.read(new File("sprite_bomberman_walking_04.png"));player.setBi(spr_bomberman_caminando);
			explosiones[0].setBi(spr_expl_arriba);
			explosiones[1].setBi(spr_expl_izquierda);
			explosiones[2].setBi(spr_expl_derecha);
			explosiones[3].setBi(spr_expl_abajo);
			enemigo1.setBi(spr_enemigo);
			enemigo2.setBi(spr_enemigo);
			enemigo3.setBi(spr_enemigo);

		} catch (IOException e) {e.printStackTrace();}
		frame.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
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
            		
            		if(enemigo1.getX()<player.getX()) 
            		{
            			enemigo1.setX(enemigo1.getX()+2);
            		}
    				if(enemigo1.getX()>player.getX()) 
            		{
            			enemigo1.setX(enemigo1.getX()-2);
            		}if(enemigo1.getY()<player.getY()) 
            		{
            			enemigo1.setY(enemigo1.getY()+2);
            		}
    				if(enemigo1.getY()>player.getY()) 
            		{
            			enemigo1.setY(enemigo1.getY()-2);
            		}
    				if(enemigo2.getX()<player.getX()) 
            		{
            			enemigo2.setX(enemigo2.getX()+1);
            		}
    				if(enemigo2.getX()>player.getX()) 
            		{
            			enemigo2.setX(enemigo2.getX()-1);
            		}if(enemigo2.getY()<player.getY()) 
            		{
            			enemigo2.setY(enemigo2.getY()+1);
            		}
    				if(enemigo2.getY()>player.getY()) 
            		{
            			enemigo2.setY(enemigo2.getY()-1);
            		}
    				if(enemigo3.getX()<player.getX()) 
            		{
            			enemigo3.setX(enemigo3.getX()+1);
            		}
    				if(enemigo3.getX()>player.getX()) 
            		{
            			enemigo3.setX(enemigo3.getX()-1);
            		}if(enemigo3.getY()<player.getY()) 
            		{
            			enemigo3.setY(enemigo3.getY()+1);
            		}
    				if(enemigo3.getY()>player.getY()) 
            		{
            			enemigo3.setY(enemigo3.getY()-1);
            		}
    				if(explosion_derecha.colision(enemigo1)){enemigo1.setW(0);enemigo1.setH(0);}
    				if(explosion_izquierda.colision(enemigo1)){enemigo1.setW(0);enemigo1.setH(0);}
    				if(explosion_inferior.colision(enemigo1)) {enemigo1.setW(0);enemigo1.setH(0);}
    				if(explosion_superior.colision(enemigo1)){enemigo1.setW(0);enemigo1.setH(0);}
    				if(explosion_derecha.colision(enemigo2)) {enemigo2.setW(0);enemigo2.setH(0);}
    				if(explosion_izquierda.colision(enemigo2)){enemigo2.setW(0);enemigo2.setH(0);}
    				if(explosion_inferior.colision(enemigo2)){enemigo2.setW(0);enemigo2.setH(0);}
    				if(explosion_superior.colision(enemigo2)) {enemigo2.setW(0);enemigo2.setH(0);}
    				if(explosion_derecha.colision(enemigo3)){enemigo3.setW(0);enemigo3.setH(0);}
    				if(explosion_izquierda.colision(enemigo3)) {enemigo3.setW(0);enemigo3.setH(0);}
    				if(explosion_inferior.colision(enemigo3)) {enemigo3.setW(0);enemigo3.setH(0);}
    				if(explosion_superior.colision(enemigo3)) {enemigo3.setW(0);enemigo3.setH(0);}
    			
            		if(aux==max) {aux=0;}
            		if(bombaEstar==2){explX=(bombaX/32)*32;
            										explY=(bombaY/32)*32;}
            		 for(int a =0;a<elementos.length;a++) 
                     {
            			 if (elementos[a].colision(explosion_derecha)||elementos[a].colision(explosion_izquierda)||elementos[a].colision(explosion_superior)||elementos[a].colision(explosion_inferior)) 
            			 {
            				 if(elementos[a].getTipo()==1) {elementos[a].setX(-321);
            				 elementos[a].setY(-321);}
            			 }	
                     }
                    panel.repaint();
            	
//            	System.out.println("animacion"+aux);
            }};
        timer.schedule(animacion,3*1000,100);
       
	}

	public class MyGraphics extends JComponent {

        private static final long serialVersionUID = 1L;

        MyGraphics() {
            setPreferredSize(new Dimension(812, 452));
        }

        @Override
        public void paintComponent(Graphics g) {
        	super.paintComponent(g);
            g.setColor(Color.black);
            g.drawImage(wall_0.bi,wall_0.x,wall_0.y,wall_0.w, wall_0.h, this);
            g.drawImage(player.bi,player.x,player.y,player.w, player.h, this);
            g.drawImage(enemigo1.bi,enemigo1.x,enemigo1.y,enemigo1.w, enemigo1.h, this);
            g.drawImage(enemigo2.bi,enemigo2.x,enemigo2.y,enemigo2.w, enemigo2.h, this);
            g.drawImage(enemigo3.bi,enemigo3.x,enemigo3.y,enemigo3.w, enemigo3.h, this);

            for(int a =0;a<elementos.length-1;a++) 
            {
//            	g.drawRect(elementos[a].x,elementos[a].y,elementos[a].w, elementos[a].h);
            	if(elementos[a].getTipo()==1) {g.drawImage(spr_wall1,elementos[a].x,elementos[a].y,elementos[a].w, elementos[a].h, this);}
            	if(elementos[a].getTipo()==2) {g.drawImage(spr_wall0,elementos[a].x,elementos[a].y,elementos[a].w, elementos[a].h, this);}
            	if(elementos[a].getTipo()==3) {g.drawImage(spr_bomb,elementos[a].x,elementos[a].y,elementos[a].w, elementos[a].h, this);}
            	if (new elemento(explX-31,explY,32,32,spr_expl_centro,3).colision(elementos[a])==true && elementos[a].getTipo()==0) {
        			System.out.println("jalo");
        		}else {
			        		
        			}

            }
            for(int a =0;a<explosiones.length;a++) 
            {
            	g.drawImage(explosiones[a].getBi(),explosiones[a].x,explosiones[a].y,explosiones[a].w, explosiones[a].h, this);
            }
            
            if(bombaEstar==1)
            {
            	g.drawImage(spr_bomb,bombaX,bombaY,20, 20, this);
            	System.out.println(bombaX+" "+bombaY+"        "+(bombaX/32)+" "+(bombaY/32));
        		final Timer timer1 = new Timer();
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
            	final Timer timer2 = new Timer();
            	   	
    		            	//crear un new element(); para cada direccion y si es que colisiona dependiendo el bloque
    		           
    		                			explosion_izquierda.setX(explX-96);
    		                			explosion_izquierda.setW(96);
    		                			explosion_izquierda.setY(explY);
    			                	
    		
		            				explosion_derecha.setX(explX+32);
    		                			explosion_derecha.setW(96);
    		                			explosion_derecha.setY(explY);
    		                			
    		                			explosion_superior.setX(explX);
    		                			explosion_superior.setH(96);
    		                			explosion_superior.setY(explY-32-64);
    		                			
    		                			explosion_inferior.setX(explX);
    		                			explosion_inferior.setH(96);
    		                			explosion_inferior.setY(explY+32);
    			               
    		            	
    		            	//se haran mas grandes las dimensiones de los new elementes y dependiendo tambien de su tipo

    		                	//		tambien habra un if que verifique si los elementos explosion tocan a los elementos enemigo
    		            	//crear los elementos enemigo
    		            	//crear las vidas, power ups, tiempo y hud
    		//            	if()asgafsdgadfg
    	            
            		panel.repaint();
                TimerTask explosion1 = new TimerTask() {

                    @Override
                    public void run() {
                    	explEstar=0;
                    	bombaEstar=0;
                    	timer2.cancel();
                    }};
                timer2.schedule(explosion1,5*100,1000);
            }
            if(bombaEstar==0) 
            {
            	reestablecerExpl();
                panel.repaint();    

            }
            	
        }
    }
	
	 public void ReproducirSonido(String nombreSonido){
	        try {
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
	         Clip clip = AudioSystem.getClip();
	         clip.open(audioInputStream);
	         clip.start();
	        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
	          System.out.println("Error al reproducir el sonido.");
	        }
	    }

	public void reestablecerExpl() 
	{
		explosiones[0].setX(-64);explosiones[0].setY(-64);explosiones[1].setX(-64);explosiones[1].setY(-64);
    	explosiones[0].setW(32);    	explosiones[0].setH(32);
    	explosiones[1].setW(32);    	explosiones[1].setH(32);
    	explosiones[2].setW(32);    	explosiones[2].setH(32);
    	explosiones[3].setW(32);    	explosiones[3].setH(32);

    	explosiones[2].setX(-64);explosiones[2].setY(-64);explosiones[3].setX(-64);explosiones[3].setY(-64);
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
