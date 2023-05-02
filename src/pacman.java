import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class pacman {

	private JFrame frame;
	public int pac_x=220,pac_y=220;
	public elemento pacman=new elemento(pac_x,pac_y,20,20,Color.yellow);
	public elemento wall_1=new elemento(60,0,210,10,Color.blue);
	public elemento wall_2=new elemento(100,0,210,10,Color.blue);
	public elemento wall_2_2=new elemento(0,210,10,71,Color.blue);
	public elemento wall_2_3=new elemento(360,210,10,111,Color.blue);


	
	public elemento wall_3=new elemento(320,0,210,10,Color.blue);
	public elemento wall_4=new elemento(360,0,210,10,Color.blue);
	
	public elemento wall_5=new elemento(100,210,10,230,Color.blue);
	public elemento wall_6=new elemento(100,0,10,230,Color.blue);
	
	public elemento wall_7=new elemento(20,250,10,100,Color.blue);
	public elemento wall_8=new elemento(20,250,60,10,Color.blue);
	public elemento wall_9=new elemento(20,310,10,100,Color.blue);
	public elemento wall_10=new elemento(120,250,70,10,Color.blue);
	public elemento wall_7_2=new elemento(20,250+100,10,100,Color.blue);
	public elemento wall_8_2=new elemento(20,250+100,60,10,Color.blue);
	public elemento wall_9_2=new elemento(20,310+100,10,100,Color.blue);
	public elemento wall_10_2=new elemento(120,250+100,70,10,Color.blue);
	public elemento wall_7_3=new elemento(20+130,250+100,10,100,Color.blue);
	public elemento wall_8_3=new elemento(20+130,250+100,60,10,Color.blue);
	public elemento wall_9_3=new elemento(20+130,310+100,10,100,Color.blue);
	public elemento wall_10_3=new elemento(120+130,250+100,70,10,Color.blue);
	public elemento wall_11=new elemento(20+272,250,10,150,Color.blue);
	public elemento wall_12=new elemento(10+272,250,160,10,Color.blue);
	public elemento wall_13=new elemento(20+272,310+90,10,150,Color.blue);
	public elemento wall_14=new elemento(110+262+60,250,160,10,Color.blue);

	public elemento wall_up=new elemento(0,-10,10,470,Color.blue);
	public elemento wall_dn=new elemento(0,440,10,470,Color.blue);

	
	public elemento walls[]= {wall_1,wall_2,wall_2_2,wall_2_3,wall_3,wall_4,wall_5,wall_6,wall_7,wall_8,
			wall_9,wall_10,wall_7_2,wall_8_2,wall_9_2,wall_10_2,wall_7_3,wall_8_3,wall_9_3,wall_10_3,
			wall_11,wall_12,wall_13,wall_14,wall_dn,wall_up};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pacman window = new pacman();
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
	public pacman() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 520, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_superior = new JPanel();
		panel_superior.setBackground(new Color(189, 224, 234));
		frame.getContentPane().add(panel_superior, BorderLayout.NORTH);
		
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setBackground(new Color(194, 228, 196));
		btnReiniciar.setOpaque(true);
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_superior.add(btnReiniciar);
		
		JPanel panel_central = new JPanel();
		panel_central.setBackground(new Color(191, 196, 232));
		frame.getContentPane().add(panel_central, BorderLayout.CENTER);
		panel_central.setSize(500, 500);
		panel_central.add(new MyGraphics());

		frame.setFocusable(true);
		frame.requestFocus();
		
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println(e.getKeyCode()+"  "+e.getKeyChar());
				System.out.println("  x:"+pacman.x+"  y:"+pacman.y);
				panel_central.repaint();
				int spd=3;
				switch(e.getKeyCode()) 
				{
				case 87://  w
					pacman.y-=spd;
					break;
				case 65: //  a
					pacman.x-=spd;
					break;
				case 83: //  s
					pacman.y+=spd;
					break;
				case 68: //  d
					pacman.x+=spd;
					break;
				}
				
				for(int l = 0;l<walls.length;l++)
				{
					if(pacman.colision(walls[l])) 
		            {	
		            	System.out.println("choca");
		            	switch(e.getKeyCode()) 
						{
						case 87://  w
							pacman.y+=spd;
							break;
						case 65: //  a
							pacman.x+=spd;
							break;
						case 83: //  s
							pacman.y-=spd;
							break;
						case 68: //  d
							pacman.x-=spd;
							break;
						}
		            }
					if(l==walls.length) {l=0;}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});
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
            g.fillRect(0,0, 470, 440);
            
            g.setColor(pacman.c);
            g.fillOval(pacman.x,pacman.y,pacman.w,pacman.h);
            
            for(int m = 0;m<walls.length;m++)
			{
            
	            g.setColor(walls[m].c);
	            g.fillRect(walls[m].x,walls[m].y, walls[m].w, walls[m].h);
	            
	        }
            
        }
    }
	
	public class elemento 
	{
		int x,y,h,w;
		Color c;
		elemento(int x,int y,int h,int w, Color c)
		{
			this.x=x;
			this.y=y;
			this.h=h;
			this.w=w;
			this.c=c;
		}
		public boolean colision(elemento e) 
		{
		
			if(this.x<e.x +e.w && this.x+this.w>e.x && 
					this.y<e.y +e.h && this.y+this.h>e.y ) 
			{return true;}else 
								{return false;}
		
		}
	}
}
