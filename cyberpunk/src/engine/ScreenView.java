package engine;

import utils.Point3D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScreenView extends JPanel implements Runnable, KeyListener, MouseListener{

	private JFrame frame;

	//the screen view of the tiles to display
	public static final byte X_SIZE = 64;
	public static final byte Y_SIZE = 64;
	//Size in pixels of each tile
	public static final byte TILE_SIZE = 64;

	//global location of viewpoint
	private Point3D center;
	private final Dimension dimension;

	//the array of visible blocks
	public Block[][] visible = new Block[X_SIZE][Y_SIZE];

	public ScreenView(Dimension d, long centerX, long centerY, long centerZ)
	{
		center = new Point3D(centerX, centerY, centerZ);
		this.dimension = d;
	}

	public ScreenView(Dimension dimension, Point3D center)
	{
		this.center = center;
		this.dimension = dimension;
	}

	public Point3D getCenter()
	{
		return center;
	}

	public void setCenter(Point3D center)
	{
		this.center = center;
	}

	public void update(Point3D center)
	{
		setCenter(center);
		draw();
	}


	public void draw()
	{
		repaint();
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.clearRect(0,0, super.getWidth(), super.getWidth());
		Color downShade = new Color(0,0,0,100);//black shader for lower levels
		for(byte z = -1; z <= 0; z++)
		{
			for(byte x = 0; x < X_SIZE; x++)
			{
				for(byte y = 0; y < Y_SIZE; y++)
				{
					//the global position of the block to be rendered
					long globalX = center.x + x - X_SIZE/2;
					long globalY = center.y + y - Y_SIZE/2;
					long globalZ = center.z + z;
					//get the block to be rendered
					Block b = dimension.get(globalX, globalY, globalZ );
					
					//the screen pixel position of the image
					int screenX = x*TILE_SIZE;
					int screenY = y*TILE_SIZE;
					//draw the image itself
					if(b != null)
					{
						g2d.drawImage(b.getImage(), screenX, screenY, null);
					}
					//draw the shading, the farther down something is the more shaded it will be. At the bottommost layer it should be black.
					g2d.setPaint(downShade);
					g2d.fillRect(screenX, screenY, TILE_SIZE, TILE_SIZE);
				}
			}
		}

	}




	@Override
	public void run() {
		frame = new JFrame("stuff");
		frame.add(this);
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		this.addKeyListener(this);
	}


	public void destroy()
	{
		frame.setVisible(false);
		frame.removeAll();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


}

