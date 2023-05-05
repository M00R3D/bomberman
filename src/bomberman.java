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

	public BufferedImage spr_wall0 ;
	private JFrame frame;
	public int aux=0,max=4;
	public BufferedImage spr_bomberman_caminando,spr_bomberman_caminando1,spr_bomberman_caminando2,spr_bomberman_caminando3,spr_bomberman_caminando4,spr_bomberman_caminando5;
	public elemento wall_0 = new elemento(50, 50, 32, 32, spr_wall0);
	public elemento player = new elemento(150, 150, 32, 32, spr_bomberman_caminando);

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
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		
		frame.setFocusable(true);
		frame.requestFocus();
		panel.add(new MyGraphics());


		try {
			spr_wall0=ImageIO.read(new File("sprite_wall0.png"));
			wall_0.setBi(spr_wall0);
			spr_bomberman_caminando=ImageIO.read(new File("sprite_bomberman_walking_00.png"));
			spr_bomberman_caminando1=ImageIO.read(new File("sprite_bomberman_walking_01.png"));
			spr_bomberman_caminando2=ImageIO.read(new File("sprite_bomberman_walking_02.png"));
			spr_bomberman_caminando3=ImageIO.read(new File("sprite_bomberman_walking_03.png"));
			spr_bomberman_caminando4=ImageIO.read(new File("sprite_bomberman_walking_04.png"));
			player.setBi(spr_bomberman_caminando);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
				}
				if(player.colision(wall_0)) 
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
				panel.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});
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
                    panel.repaint();
            	
//            	System.out.println("animacion"+aux);
            }};
        timer.schedule(animacion,3*1000,100);

	}

	public class MyGraphics extends JComponent {

        private static final long serialVersionUID = 1L;

        MyGraphics() {
            setPreferredSize(new Dimension(470, 440));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.black);
            g.drawImage(wall_0.bi,wall_0.x,wall_0.y,wall_0.w, wall_0.h, this);
            g.drawImage(player.bi,player.x,player.y,player.w, player.h, this);

        }
    }


	public class elemento 
	{
		int x,y,h,w;
		BufferedImage bi;
		elemento(int x,int y,int h,int w, BufferedImage bi)
		{
			this.x=x;
			this.y=y;
			this.h=h;
			this.w=w;
			this.bi=bi;
		}
		public boolean colision(elemento e) 
		{
		
			if(this.x<e.x +e.w && this.x+this.w>e.x && 
					this.y<e.y +e.h && this.y+this.h>e.y ) 
			{return true;}else 
								{return false;}
		
		}
		public BufferedImage getBi() {
			return bi;
		}
		public void setBi(BufferedImage bi) {
			this.bi = bi;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getH() {
			return h;
		}
		public void setH(int h) {
			this.h = h;
		}
		public int getW() {
			return w;
		}
		public void setW(int w) {
			this.w = w;
		}
	}
	
}
