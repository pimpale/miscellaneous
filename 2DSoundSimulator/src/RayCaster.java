
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



@SuppressWarnings("serial")
public class RayCaster extends JPanel implements MouseListener, MouseMotionListener
{
	public RayCaster()
	{
		addMouseListener(this);
		addMouseMotionListener(this);	 
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		mousex = e.getX();
		mousey = e.getY();
		//pause button
		if(IsButtonClicked(710, 20, 80, 20))
		{
			gamePaused = !gamePaused;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{	
		mousex = e.getX();
		mousey = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		mousex = e.getX();
		mousey = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		mousex = e.getX();
		mousey = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{    
		mouseDown = false;  
	}


	@Override
	public void mouseDragged(MouseEvent e) 
	{
		mousex = e.getX();
		mousey = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{	
		mousex = e.getX();
		mousey = e.getY();
	}

	//checks if mouse is within bounds of button
	boolean IsButtonClicked(int x, int y, int width, int height)
	{
		return withinParams(x,y,x+width,y+height, mousex, mousey);
	}


	double getResponse(String prompt, double defaultValue, 
			boolean needstobeint, boolean needstobepositive)
	{
		gamePaused = true;//pause game
		//creates the actual prompt.
		String response = JOptionPane.showInputDialog(prompt);
		double numberInResponse = 0;
		try
		{
			//convert the string response into a double
			numberInResponse = Double.parseDouble(response);
			//if the number is not an integer when it should be
			if(needstobeint && numberInResponse != (int)numberInResponse)
			{
				JOptionPane.showMessageDialog(null, "Invalid input. Integer value required.");
				numberInResponse = defaultValue;
			}
			//if the number is negative when it should be positive
			if(needstobepositive && numberInResponse <= 0)
			{
				JOptionPane.showMessageDialog(null, "Invalid input. Positive values only.");
				numberInResponse = defaultValue;
			}
		}
		catch(java.lang.NumberFormatException NumError)
		{
			//if it cannot parse the string
			JOptionPane.showMessageDialog(null, "Invalid input. Only numeric characters accepted.");
			numberInResponse = defaultValue;
		}
		catch(java.lang.NullPointerException canceled)
		{
			//if canceled
			numberInResponse = defaultValue;
		}
		return numberInResponse;
	}


	static final int xSize = 700;
	static final int ySize = 700;

	boolean mouseDown = false;
	boolean gamePaused = false;

	int mousex = 0;
	int mousey = 0;

	int speed = 1;



	void initialize() throws InterruptedException
	{
		double max = 1000;
		for(int i = 0; i < max; i++)
		{
			Ray r = new Ray();
			r.x = 0;
			r.y = 0;
			double direction = Math.PI*2*(i/max);
			r.xVel = Math.cos(direction);
			r.yVel = Math.sin(direction);
			raylist.add(r);
		}
		for(int i = 0; i < max; i++)
		{
			Ray r = new Ray();
			r.x = 700;
			r.y = 700;
			double direction = Math.PI*2*(i/max);
			r.xVel = Math.cos(direction);
			r.yVel = Math.sin(direction);
			raylist.add(r);
		}
		System.out.println("initialized");
		while(true)
		{
			repaint(50);
			if(gamePaused == false)
			{
				wrkplz();
				Thread.sleep(speed);
			}
			else
			{
				Thread.sleep(100);
			}
		}
	}

	ArrayList<Ray> raylist = new ArrayList<Ray>();

	double[][] sound = new double[701][701];

	void wrkplz()
	{
		for(int i = raylist.size()-1; i >=0; i--)
		{
			Ray r = raylist.get(i);
			r.t += 0.2;
			r.x += r.xVel;
			r.y += r.yVel;

			boolean remove = false;

			if(r.x > 700)
			{
				//remove = true;
				r.x = 700;
				r.xVel = -r.xVel;
			}
			else if(r.x < 0)
			{
				//	remove = true;
				r.x = 0;
				r.xVel = -r.xVel;
			}

			if(r.y > 700)
			{
				r.y = 700;
				r.yVel = -r.yVel;
			}
			else if(r.y < 0)
			{
				r.y = 0;
				r.yVel = -r.yVel;
			}

			sound[(int)r.x][(int)r.y] += Math.cos(r.t);///(r.t/10);

			if(remove)
			{
				raylist.remove(i); 
			}
		}
	}

	boolean withinParams(int xmin, int ymin, int xmax, int ymax, int x, int y)
	{
		boolean b = false;
		if(x > xmin && y > ymin && x < xmax && y < ymax)
		{
			b = true;
		}
		return b;
	}

	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		setBackground(Color.gray);
		g2d.setPaint(Color.black);

		g2d.fillRect(0, 0, 700, 700);
		g2d.setPaint(Color.white);
		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		g2d.fillRect(710, 20, 80, 20);
		g2d.fillRect(800, 20, 80, 20);
		g2d.fillRect(890, 20, 80, 20);
		g2d.fillRect(710, 260, 260, 20);
		g2d.setPaint(Color.black);
		g2d.drawString("TRACING", 893, 35);
		g2d.drawString("ADD_OBJECT", 803, 35);
		g2d.drawString("CHANGE_APPLICATION_SPEED", 713, 275);

		if(gamePaused == true)
		{
			g2d.drawString("PLAY", 713, 35);
		}
		else
		{
			g2d.drawString("PAUSE", 720, 35);
		}

		for(int x = 0; x < 700; x++)
		{
			for(int y = 0; y < 700; y++)
			{ 
				int val = (int)((sound[x][y])*50);

				if(val > 0)
				{
					g2d.setPaint(new Color(0,0,Math.min(255, val)));
				}
				else
				{
					g2d.setPaint(new Color(Math.min(255, -val),0,0));
				}

				g2d.fillRect(x*1, y*1, 1, 1);
			}
		}
	}	

	public static void main(String[] args) throws InterruptedException 
	{
		JFrame frame = new JFrame("Gravity Simulator");
		RayCaster game = new RayCaster();
		frame.add(game);
		frame.setSize(1000, 700);                                        //creating window
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.initialize();
	}
}


class Ray
{
	public double t = 0;
	public double xVel = 0;
	public double yVel = 0;
	public double x = 0;
	public double y = 0;
}