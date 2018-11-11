package gravityagario;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



@SuppressWarnings("serial")
public class GravityAgario extends JPanel implements MouseListener, MouseMotionListener
{
	ArrayList<String> ParticleId = new ArrayList<String>();
	ArrayList<Double> ParticleX = new ArrayList<Double>();
	ArrayList<Double> ParticleY = new ArrayList<Double>();
	ArrayList<Double> ParticleXvel = new ArrayList<Double>();
	ArrayList<Double> ParticleYvel = new ArrayList<Double>();
	ArrayList<Double> ParticleMa = new ArrayList<Double>();
	
	ArrayList<String> TracerId = new ArrayList<String>();
	ArrayList<Double> TracerX = new ArrayList<Double>();
	ArrayList<Double> TracerY = new ArrayList<Double>();
	
	volatile private boolean mouseDown = false;
	volatile private boolean mouseClicked = false;
	volatile private boolean gamepaused = true;
	
	int mousepressedx = 0;
	int mousepressedy = 0;
	
	int mousereleasedx = 0;
	int mousereleasedy = 0;
	int mousereleased = 0;
	
	
	int mouseclickedx = 0;
	int mouseclickedy = 0;
	
	int speed = 1;
	int counter =0;
	
	public GravityAgario()
	{
		addMouseListener(this);
		addMouseMotionListener(this);	 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		mouseClicked = true;
		mouseclickedx = e.getX();
		mouseclickedy = e.getY();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{	
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (e.getButton() == MouseEvent.BUTTON1) 
		{
			mouseDown = true;
	    	mousepressedx = e.getX();
	    	mousepressedy = e.getY();
	    }
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if (e.getButton() == MouseEvent.BUTTON1) 
	    {
	        mouseDown = false;
	        mousereleasedx = e.getX();
	    	mousereleasedy = e.getY();
	    	mousereleased = 1;
	    }
	}
	

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		mousex = e.getX();
		mousey = e.getY();
	}
	
	int mousex = 0;
	int mousey = 0;
	@Override
	public void mouseMoved(MouseEvent e) 
	{	
		mousex = e.getX();
		mousey = e.getY();
	}
	boolean gamestart = false;
	private void functions()
	{
		if(gamestart == false)
		{
			restart();
			gamestart = true;
		}
		repaint();
		playerinterface();

		counter++;
		if(counter == speed)
		{
			generateObjects();
			player();
			gravity();
			counter = 0;
		}
		
		
	}
	
	private void addObject(String id, double x, double y, double xVel, double yVel, double Mass)
	{
		ParticleId.add(id);
		ParticleX.add(x);
		ParticleY.add(y);
		ParticleXvel.add(xVel);
		ParticleYvel.add(yVel);
		ParticleMa.add(Mass);
	}
	
	private void removeObject(int id)
	{
		ParticleId.remove(id);
		ParticleX.remove(id);
		ParticleY.remove(id);
		ParticleXvel.remove(id);
		ParticleYvel.remove(id);
		ParticleMa.remove(id);
	}
	
	private void generateObjects()
	{
		if(ParticleId.size() < 50000)
		{
			int q = 0;
			for(int i = 0; i < ParticleId.size(); i++)
			{
				if(ParticleId.get(i)== "player") q=i;
			}
			
			if(Math.random() > 0.95)
			{
				addObject("dust",
						(double)((Math.random()*1400))-350,
						(double)((Math.random()*1400))-350,
						(double)((Math.random()-0.5)*0.01),
						(double)((Math.random()-0.5)*0.01),
						1);
			}
			
			if(Math.random() > 0.98)
			{
				addObject("dust",
						(double)((Math.random()*1400))-350,
						(double)((Math.random()*1400))-350,
						(double)((Math.random()-0.5)*0.01),
						(double)((Math.random()-0.5)*0.01),
						Math.random()*10);
			}
			if(Math.random() > 0.995)
			{
				addObject("asteroid",
						(double)((Math.random()*1400))-350,
						(double)((Math.random()*1400))-350,
						(double)((Math.random()-0.5)*0.01),
						(double)((Math.random()-0.5)*0.01),
						Math.random()*10);
			}
			if(Math.random() > 0.99987)
			{
				addObject("asteroid",
						(double)((Math.random()*1400))-350,
						(double)((Math.random()*1400))-350,
						(double)((Math.random()-0.5)*0.5),
						(double)((Math.random()-0.5)*0.5),
						Math.random()*100);
			}
			
			if(Math.random() > 0.99995)
			{
				addObject("asteroid",
						(double)((Math.random()*1400))-350,
						(double)((Math.random()*1400))-350,
						(double)((Math.random()-0.5)*0.01),
						(double)((Math.random()-0.5)*0.01),
						Math.random()*2000);
			}
		}
	}
	int playercount = 0;
	
	private void LaunchParticle(int launcherID, double angle, double velocity, double mass)
	{
		ParticleXvel.set(launcherID, (ParticleXvel.get(launcherID)+velocity*mass*Math.cos(angle)/ParticleMa.get(launcherID)));
		ParticleYvel.set(launcherID, (ParticleYvel.get(launcherID)+velocity*mass*Math.sin(angle)/ParticleMa.get(launcherID)));	
		SpawnDustParticle(launcherID, angle, velocity, mass, 0);
	}	

	
	private void SpawnDustParticle(int launcherID, double angle, double velocity, double mass, double radialvelocity)
	{
		double ParticleRadius = 2+ 0.5*Math.pow(ParticleMa.get(launcherID), 0.5);
		
		
		if(Math.random() > 0.05)
		{
			ParticleId.add("dust");
		}
		else
		{
			mass += 1;
			ParticleId.add("asteroid");
		}
		
		ParticleX.add((ParticleX.get(launcherID) + ParticleRadius * Math.cos(angle+ Math.PI)));
		ParticleY.add((ParticleY.get(launcherID) + ParticleRadius * Math.sin(angle+ Math.PI)));
		ParticleXvel.add(ParticleXvel.get(launcherID)+(velocity*-Math.cos(angle))+(velocity*-Math.cos(90+angle)));
		ParticleYvel.add(ParticleYvel.get(launcherID)+(velocity*-Math.sin(angle))+(velocity*-Math.sin(90+angle)));
		ParticleMa.add(mass);
		ParticleMa.set(launcherID, (ParticleMa.get(launcherID)-mass));
	}
	
	private void player()
	{	
		playercount++;
		for(int i = 0; i < ParticleId.size(); i++)
		{
			if(ParticleId.get(i).equals("player"))
			{
				if(ParticleMa.get(i) <= 100)
				{
					ParticleMa.set(i, 100.0);
				}
				
				if(mouseDown == true && mousex < 700 && mousey < 700)
				{
					if(Math.sqrt(Math.pow(ParticleXvel.get(i)/ParticleMa.get(i),2) + Math.pow(ParticleXvel.get(i)/ParticleMa.get(i),2)) > 1)
					{
						ParticleXvel.set(i,(double) (ParticleXvel.get(i)/1.1));
						ParticleYvel.set(i,(double) (ParticleYvel.get(i)/1.1));
					}
					
					if(ParticleMa.get(i) > 1 && playercount >  0)
					{
						double angle = Math.atan2((mousey -ParticleY.get(i)), (mousex-ParticleX.get(i)));
						angle += (Math.random()-0.5)/5;
						//angle = Math.atan2((ParticleY.get(i) -mousey), (ParticleX.get(i)-mousex));
						playercount = 0;
						LaunchParticle(i, angle, 10,0.5);
						ParticleX.set(i, (double) 350);
						ParticleX.set(i, (double) 350);
					}
				}
				
			}
		}
		
		
		
		
		
	}
	
	boolean tracer = true;
	private void playerinterface() 
	{
		if(mouseclickedx > 710 && mouseclickedx < 790 && mouseclickedy > 20 && mouseclickedy < 40)
		{
			gamepaused = !gamepaused;
			mouseclickedx = 0;
			mouseclickedy = 0;
		}
		
		
		if(mouseclickedx > 800 && mouseclickedx < 970 && mouseclickedy > 20 && mouseclickedy < 40)
		{
			 String thingstring = JOptionPane.showInputDialog ("Input application speed. Only positive integers from 0 to 100");
			 int alsothingstring = 0;
			 try
			 {
					alsothingstring = Integer.parseInt(thingstring);
			 }
			 catch(java.lang.NumberFormatException NumError)
			 {
				 JOptionPane.showMessageDialog(null, "Invalid input.");
				 alsothingstring = speed;
			 }
			 catch(java.lang.NullPointerException NumError)
			{
				alsothingstring = speed;
			}
			counter=0;
			speed = alsothingstring;
			mouseclickedx = 0;
			mouseclickedy = 0;
		}
	}	
		
	
	private void restart()
	{
			ParticleId.clear();
			ParticleX.clear();
			ParticleY.clear();
			ParticleXvel.clear();
			ParticleYvel.clear();
			ParticleMa.clear();
			
			ParticleId.add("player");
			ParticleX.add((double)(350));
			ParticleY.add((double)(350));
			ParticleXvel.add((double)0);
			ParticleYvel.add((double)0);
			ParticleMa.add((double)2000);
	}
	
	
	private void collision(int a, int i)
	{
		int removethis = 0;
		int addthis = 0;
		if(ParticleMa.get(a) >= ParticleMa.get(a))
		{
			removethis = i;
			addthis = a;
		}
		if(ParticleMa.get(i) > ParticleMa.get(a))
		{
			removethis = a;
			addthis = i;
		}
		
		
		if(addthis < ParticleId.size() && removethis < ParticleId.size() && !(addthis==removethis))
		{
			ParticleMa.set(addthis, (ParticleMa.get(removethis)+ParticleMa.get(addthis)));
			if(ParticleMa.get(addthis) < 5000 && ParticleId.get(removethis) != "dust")
			{
				ParticleXvel.set(addthis,	ParticleXvel.get(addthis)+	((ParticleXvel.get(removethis)/ParticleMa.get(addthis))	));
				ParticleYvel.set(addthis,	ParticleYvel.get(addthis)+	((ParticleYvel.get(removethis)/ParticleMa.get(addthis))	));
			}
			boolean timetorestart = false;
			if(ParticleId.get(removethis) == "player" )
			{
				timetorestart = true;
			}
			
			ParticleId.remove(removethis);
			ParticleX.remove(removethis);
			ParticleY.remove(removethis);
			ParticleXvel.remove(removethis);
			ParticleYvel.remove(removethis);
			ParticleMa.remove(removethis);
			
			if(timetorestart ==true)
			{
				restart();
				gamepaused = true;
			}
		}	
	}
	
	
	private void  gravity()
	{
		
		for(int id = 0; id < ParticleId.size(); id++)
		{
			if(ParticleId.size() > 10000 && ParticleId.get(id)== "dust" && ParticleMa.get(id)<2)
			{
				ParticleId.remove(id);
				ParticleX.remove(id);
				ParticleY.remove(id);
				ParticleXvel.remove(id);
				ParticleYvel.remove(id);
				ParticleMa.remove(id);
			}
		}
		
		
		if(ParticleId.size() > 0 && gamepaused == false)
		{
			for(int i = 0; i < ParticleId.size(); i++)
			{
				if(Math.abs(ParticleX.get(i)-350) > 2000 || Math.abs(ParticleY.get(i)-350) > 2000)
				{
					ParticleId.remove(i);
					ParticleX.remove(i);
					ParticleY.remove(i);
					ParticleXvel.remove(i);
					ParticleYvel.remove(i);
					ParticleMa.remove(i);
				}
			}
			
			for(int i = 0; i < ParticleId.size(); i++)
			{
				if(Math.abs(ParticleXvel.get(i)) > 0)
				{
					ParticleXvel.set(i, ParticleXvel.get(i)*0.999999);
				}
				if(Math.abs(ParticleYvel.get(i)) > 0)
				{
					ParticleYvel.set(i, ParticleYvel.get(i)*0.999999);
				}
			}
			
			for(int i = 0; i < ParticleId.size(); i++)
			{
				if(ParticleMa.get(i) > 20 )
				{
					for(int q = 0; q < (int)(1); q++)
					{
						double angle = Math.random()*2*Math.PI;
					//	LaunchParticle(i,angle, ParticleMa.get(i)/2000, Math.random()*1);
						SpawnDustParticle(i,angle, (ParticleMa.get(i))/4000, 0.3,1);
					}
				}
			}
			for(int i = 0; i < ParticleId.size(); i++)
			{
				if(Math.random() > 1-(ParticleMa.get(i)/9999999))
				{
					int random = (int)(Math.random()*ParticleMa.get(i)/1);
					for(int q = 0; q < random; q++)
					{
						double angle = Math.random()*2*Math.PI;
						LaunchParticle(i,angle,ParticleMa.get(i)/1000, Math.random()*1);
					}
				
				}
			}
			
			for(int i = 0; i < ParticleId.size(); i++)
			{
				if(ParticleId.get(i) == "player")
				{
					for(int a = 0; a < ParticleId.size(); a++)
					{
						double prevX = ParticleX.get(a);
						double prevY = ParticleY.get(a);
						ParticleX.set(a, prevX - ParticleXvel.get(i));
						ParticleY.set(a, prevY - ParticleYvel.get(i));
					}
				}
				double ParticleXqwerty = (ParticleX.get(i));
				double ParticleYqwerty = (ParticleY.get(i));
				double ParticleXvelqwerty = (ParticleXvel.get(i));
				double ParticleYvelqwerty = (ParticleYvel.get(i));
				double ParticleMass = (ParticleMa.get(i));
				double newParticlex = ParticleXqwerty + ParticleXvelqwerty; 
				double newParticley = ParticleYqwerty + ParticleYvelqwerty; 

				ParticleX.set(i, newParticlex);
				ParticleY.set(i, newParticley);
				if(ParticleId.get(i)!="dust")
				{
					double IParticleXqwerty = (double)(ParticleX.get(i));
					double IParticleYqwerty = (double)(ParticleY.get(i));
					double IParticleradius  = (double)(1/Math.PI * Math.sqrt(ParticleMa.get(i))); 
					
					for(int a = 0; a < ParticleId.size(); a++)
					{
						if(i < ParticleId.size() && a < ParticleId.size())
						{
							if(i!=a)
							{
								double AParticleXqwerty = (ParticleX.get(a));
								double AParticleYqwerty = (ParticleY.get(a));
								double AParticleradius  = (1/Math.PI * Math.sqrt(ParticleMa.get(a))); 
								double ItoAParticledistance = Math.sqrt((Math.pow((IParticleXqwerty-AParticleXqwerty),2)+ Math.pow((IParticleYqwerty-AParticleYqwerty),2)));
								if(Math.pow(ItoAParticledistance, -2) * ParticleMa.get(a) * ParticleMa.get(i) > 0.0005)
								{
									if(ItoAParticledistance > 1+ 0.7*(AParticleradius +IParticleradius))
									{
										if(a < ParticleId.size() && i < ParticleId.size())
										{	
											double ParticledegreesfromAtoI = Math.atan2((IParticleYqwerty -AParticleYqwerty), (IParticleXqwerty-AParticleXqwerty));
											double ParticleXvelqwerty1 = ParticleXvel.get(a) + ((0.01*(ParticleMa.get(i)*ParticleMa.get(a)) * (Math.pow(ItoAParticledistance, -2)) *(Math.cos(ParticledegreesfromAtoI))))/ParticleMa.get(a);
											double ParticleYvelqwerty1 = ParticleYvel.get(a) + ((0.01*(ParticleMa.get(i)*ParticleMa.get(a)) * (Math.pow(ItoAParticledistance, -2)) *(Math.sin(ParticledegreesfromAtoI))))/ParticleMa.get(a);
											ParticleXvel.set(a, (double)ParticleXvelqwerty1);
											ParticleYvel.set(a, (double)ParticleYvelqwerty1);
										}
									}
									else
									{
										collision(a, i);	
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
			
		setBackground(Color.gray);
		g2d.setPaint(Color.black);
		int q=0;
		for(int i= 0; i < ParticleId.size(); i++)
		{
			if(ParticleId.get(i)== "player") i=q;
		}
		g2d.fillRect(0, 0, 700, 700);
		g2d.setPaint(Color.white);
		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		g2d.fillRect(710, 20, 80, 20);
		g2d.fillRect(800, 20, 170, 20);
		g2d.drawString("MASS: " +ParticleMa.get(q), 700, 100);
		g2d.drawString("X_VELOCITY: " +ParticleXvel.get(q)/ParticleMa.get(q), 700, 120);
		g2d.drawString("Y_VELOCITY: " +ParticleYvel.get(q)/ParticleMa.get(q), 700, 140);
		g2d.setPaint(Color.black);
		g2d.drawString("CHANGE_APPLICATION_SPEED", 803, 35);	
		if(gamepaused == true)
		{
			g2d.drawString("PLAY", 713, 35);
		}
		else
		{
			g2d.drawString("PAUSE", 713, 35);
		}
		
		g2d.setPaint(Color.white);
		
		
		if(ParticleId.size() > 0)
		{
			
			g2d.setPaint(Color.white);
			for(int i = 0; i < ParticleId.size(); i++)
			{
				if(ParticleId.get(i) != "dust" && i < ParticleId.size()+1)
				{
					Color player = new Color(160, 210, 190);
					if(ParticleId.get(i)== "player") 
					{
						g2d.setPaint(player); 
						g2d.drawLine(350, 350, (int)(350+50*ParticleXvel.get(i)/ParticleMa.get(i)),(int)(350+50*ParticleYvel.get(i)/ParticleMa.get(i)));
					}
					else g2d.setPaint(Color.white);
					int Particleradius = 1+(int)((1/3.14) *Math.sqrt(ParticleMa.get(i)));
					g2d.fillOval((int)(ParticleX.get(i)-Particleradius), (int)(ParticleY.get(i)-Particleradius), Particleradius*2, Particleradius*2);
					g2d.setPaint(Color.white);
				}
			}
		}
		
		if(ParticleId.size() > 0)
		{
			g2d.setPaint(Color.gray);
			for(int i = 0; i < ParticleId.size(); i++)
			{
				if(ParticleId.get(i) == "dust"&& i < ParticleId.size()+3)
				{	
					if(ParticleX.get(i) != null && ParticleY.get(i) != null)
					{
						int Particleradius = (int)(1 *Math.sqrt(ParticleMa.get(i)));
						if(Particleradius == 0) Particleradius = 1;
						g2d.fillOval((int)(ParticleX.get(i)+0.0), (int)(ParticleY.get(i)+0.0), Particleradius, Particleradius);
					}	
				}
			}
		}
			
	}
		
		
	
		public static void main(String[] args) throws InterruptedException 
		{
			JFrame frame = new JFrame("stuff");
			GravityAgario game = new GravityAgario();
			frame.add(game);
			frame.setSize(1000, 700);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			new GravityAgario();
			while (true) 
			{
				game.functions();
				Thread.sleep(1);
				
			}
		}
}