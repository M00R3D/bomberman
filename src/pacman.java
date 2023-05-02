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
import javax.swing.JLabel;

public class pacman {

	private JFrame frame;
	public int pac_x=220,pac_y=220,puntosint=0;
	public String puntostring=(String.valueOf(puntosint)+" puntos");
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
	public elemento wall_dn=new elemento(-20,445,10,500,Color.blue);

	
	public elemento walls[]= {wall_1,wall_2,wall_2_2,wall_2_3,wall_3,wall_4,wall_5,wall_6,wall_7,wall_8,
			wall_9,wall_10,wall_7_2,wall_8_2,wall_9_2,wall_10_2,wall_7_3,wall_8_3,wall_9_3,wall_10_3,
			wall_11,wall_12,wall_13,wall_14,wall_dn,wall_up};
	public elemento pastilla_1=new elemento(4,223,5,5,Color.white);
	public elemento pastilla_2=new elemento(4,243,5,5,Color.white);
	public elemento pastilla_3=new elemento(4,263,5,5,Color.white);
	public elemento pastilla_4=new elemento(4,283,5,5,Color.white);
	public elemento pastilla_5=new elemento(4,303,5,5,Color.white);
	public elemento pastilla_6=new elemento(4,323,5,5,Color.white);
	public elemento pastilla_7=new elemento(4,343,5,5,Color.white);
	public elemento pastilla_8=new elemento(4,363,5,5,Color.white);
	public elemento pastilla_9=new elemento(4,383,5,5,Color.white);
	public elemento pastilla_10=new elemento(4,403,5,5,Color.white);
	public elemento pastilla_11=new elemento(4,423,5,5,Color.white);
	public elemento pastilla_12=new elemento(4,443,5,5,Color.white);
	public elemento pastilla_c_1=new elemento(451,223,5,5,Color.white);
	public elemento pastilla_c_2=new elemento(451,243,5,5,Color.white);
	public elemento pastilla_c_3=new elemento(451,263,5,5,Color.white);
	public elemento pastilla_c_4=new elemento(451,283,5,5,Color.white);
	public elemento pastilla_c_5=new elemento(451,303,5,5,Color.white);
	public elemento pastilla_c_6=new elemento(451,323,5,5,Color.white);
	public elemento pastilla_c_7=new elemento(451,343,5,5,Color.white);
	public elemento pastilla_c_8=new elemento(451,363,5,5,Color.white);
	public elemento pastilla_c_9=new elemento(451,383,5,5,Color.white);
	public elemento pastilla_c_10=new elemento(451,403,5,5,Color.white);
	public elemento pastilla_c_11=new elemento(451,423,5,5,Color.white);
	public elemento pastilla_c_12=new elemento(451,443,5,5,Color.white);

	
	public elemento pastilla_1_2=new elemento(24,223,5,5,Color.white);
	public elemento pastilla_2_2=new elemento(44,223,5,5,Color.white);
	public elemento pastilla_3_2=new elemento(64,223,5,5,Color.white);
	public elemento pastilla_4_2=new elemento(84,223,5,5,Color.white);
	public elemento pastilla_5_2=new elemento(104,223,5,5,Color.white);
	public elemento pastilla_6_2=new elemento(124,223,5,5,Color.white);
	public elemento pastilla_7_2=new elemento(144,223,5,5,Color.white);
	
	public elemento pastilla_1_3=new elemento(250+24,223,5,5,Color.white);
	public elemento pastilla_2_3=new elemento(250+44,223,5,5,Color.white);
	public elemento pastilla_3_3=new elemento(250+64,223,5,5,Color.white);
	public elemento pastilla_4_3=new elemento(250+84,223,5,5,Color.white);
	public elemento pastilla_5_3=new elemento(250+104,223,5,5,Color.white);
	public elemento pastilla_6_3=new elemento(250+124,223,5,5,Color.white);
	public elemento pastilla_7_3=new elemento(250+144,223,5,5,Color.white);
	public elemento pastilla_8_3=new elemento(250+164,223,5,5,Color.white);
	public elemento pastilla_9_3=new elemento(250+184,223,5,5,Color.white);
	public elemento pastilla_10_3=new elemento(250+204,223,5,5,Color.white);
	public elemento pastilla_11_3=new elemento(250+224,223,5,5,Color.white);
	public elemento pastilla_12_3=new elemento(250+244,223,5,5,Color.white);

	public elemento pastilla_a_1=new elemento(79,4,5,5,Color.white);
	public elemento pastilla_a_2=new elemento(79,24,5,5,Color.white);
	public elemento pastilla_a_3=new elemento(79,44,5,5,Color.white);
	public elemento pastilla_a_4=new elemento(79,64,5,5,Color.white);
	public elemento pastilla_a_5=new elemento(79,84,5,5,Color.white);
	public elemento pastilla_a_6=new elemento(79,104,5,5,Color.white);
	public elemento pastilla_a_7=new elemento(79,124,5,5,Color.white);
	public elemento pastilla_a_8=new elemento(79,144,5,5,Color.white);
	public elemento pastilla_a_9=new elemento(79,164,5,5,Color.white);
	public elemento pastilla_a_10=new elemento(79,184,5,5,Color.white);
	public elemento pastilla_a_11=new elemento(79,204,5,5,Color.white);
	public elemento pastilla_b_1=new elemento(339,4,5,5,Color.white);
	public elemento pastilla_b_2=new elemento(339,24,5,5,Color.white);
	public elemento pastilla_b_3=new elemento(339,44,5,5,Color.white);
	public elemento pastilla_b_4=new elemento(339,64,5,5,Color.white);
	public elemento pastilla_b_5=new elemento(339,84,5,5,Color.white);
	public elemento pastilla_b_6=new elemento(339,104,5,5,Color.white);
	public elemento pastilla_b_7=new elemento(339,124,5,5,Color.white);
	public elemento pastilla_b_8=new elemento(339,144,5,5,Color.white);
	public elemento pastilla_b_9=new elemento(339,164,5,5,Color.white);
	public elemento pastilla_b_10=new elemento(339,184,5,5,Color.white);
	public elemento pastilla_b_11=new elemento(339,204,5,5,Color.white);

	public elemento pastilla_d_1=new elemento(1,426,5,5,Color.white);
	public elemento pastilla_d_2=new elemento(21,426,5,5,Color.white);
	public elemento pastilla_d_3=new elemento(41,426,5,5,Color.white);
	public elemento pastilla_d_4=new elemento(61,426,5,5,Color.white);
	public elemento pastilla_d_5=new elemento(81,426,5,5,Color.white);
	public elemento pastilla_d_6=new elemento(101,426,5,5,Color.white);
	public elemento pastilla_d_7=new elemento(121,426,5,5,Color.white);
	public elemento pastilla_d_8=new elemento(141,426,5,5,Color.white);
	public elemento pastilla_d_9=new elemento(161,426,5,5,Color.white);
	public elemento pastilla_d_10=new elemento(181,426,5,5,Color.white);
	public elemento pastilla_d_11=new elemento(201,426,5,5,Color.white);
	public elemento pastilla_d_12=new elemento(221,426,5,5,Color.white);
	public elemento pastilla_d_13=new elemento(241,426,5,5,Color.white);
	public elemento pastilla_d_14=new elemento(261,426,5,5,Color.white);
	public elemento pastilla_d_15=new elemento(281,426,5,5,Color.white);
	public elemento pastilla_d_16=new elemento(301,426,5,5,Color.white);
	public elemento pastilla_d_17=new elemento(321,426,5,5,Color.white);
	public elemento pastilla_d_18=new elemento(341,426,5,5,Color.white);
	public elemento pastilla_d_19=new elemento(361,426,5,5,Color.white);
	public elemento pastilla_d_20=new elemento(381,426,5,5,Color.white);
	public elemento pastilla_d_21=new elemento(401,426,5,5,Color.white);
	public elemento pastilla_d_22=new elemento(421,426,5,5,Color.white);
	public elemento pastilla_d_23=new elemento(441,426,5,5,Color.white);
	public elemento pastilla_d_24=new elemento(461,426,5,5,Color.white);
	
	public elemento pastillas[]= {pastilla_1,pastilla_2,pastilla_3,pastilla_4,pastilla_5,pastilla_6,pastilla_7,pastilla_8,pastilla_9,pastilla_10,pastilla_11,pastilla_12,
			pastilla_c_1,pastilla_c_2,pastilla_c_3,pastilla_c_4,pastilla_c_5,pastilla_c_6,pastilla_c_7,pastilla_c_8,pastilla_c_9,pastilla_c_10,pastilla_c_11,pastilla_c_12,
			pastilla_1_2,pastilla_2_2,pastilla_3_2,pastilla_4_2,pastilla_5_2,pastilla_6_2,pastilla_7_2,
			pastilla_1_3,pastilla_2_3,pastilla_3_3,pastilla_4_3,pastilla_5_3,pastilla_6_3,pastilla_7_3,
			pastilla_8_3,pastilla_9_3,pastilla_10_3,pastilla_11_3,pastilla_12_3,
			pastilla_a_1,pastilla_a_2,pastilla_a_3,pastilla_a_4,pastilla_a_5,pastilla_a_6,pastilla_a_7,pastilla_a_8
			,pastilla_a_9,pastilla_a_10,pastilla_a_11,
			pastilla_b_1,pastilla_b_2,pastilla_b_3,pastilla_b_4,pastilla_b_5,pastilla_b_6,pastilla_b_7,pastilla_b_8
			,pastilla_b_9,pastilla_b_10,pastilla_b_11,
			pastilla_d_1,pastilla_d_2,pastilla_d_3,pastilla_d_4,pastilla_d_5,pastilla_d_6,pastilla_d_7,pastilla_d_8
			,pastilla_d_9,pastilla_d_10,pastilla_d_11,pastilla_d_12,pastilla_d_13,pastilla_d_14,pastilla_d_15
			,pastilla_d_16,pastilla_d_17,pastilla_d_18,pastilla_d_19,pastilla_d_20,pastilla_d_21,pastilla_d_22,pastilla_d_23,pastilla_d_24};
	public elemento pastillas2[]=pastillas;
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

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				

			}});
		
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
				if(pacman.x<-20) {pacman.x=470;}
				if(pacman.x>470) {pacman.x=-20;}
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
				for(int l = 0;l<pastillas.length;l++)
				{
					if(pacman.colision(pastillas[l])) 
		            {	
		            	System.out.println("come");
//		            	pastillas2[l]=pastillas[l];
		            	pastillas[l]=new elemento(0,0,0,0,Color.black);
		            	puntosint++;
		            	puntostring=(String.valueOf(puntosint)+" puntos");
		            }
					if(l==pastillas.length) {l=0;}
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
            g.setColor(Color.white);
            g.drawString(puntostring, 200,60);
            
            g.setColor(pacman.c);
            g.fillOval(pacman.x,pacman.y,pacman.w,pacman.h);
            
            for(int m = 0;m<walls.length;m++)
			{
            
	            g.setColor(walls[m].c);
	            g.fillRect(walls[m].x,walls[m].y, walls[m].w, walls[m].h);
	            
	        }
            for(int m = 0;m<pastillas.length;m++)
			{
            
	            g.setColor(pastillas[m].c);
	            g.fillRect(pastillas[m].x,pastillas[m].y, pastillas[m].w, pastillas[m].h);
	            
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