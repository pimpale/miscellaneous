package launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//TODO this class is garbage and it needs to be totally revised to look awesome

@SuppressWarnings("serial")
public class Launcher extends JPanel implements Runnable, MouseListener{
	

			
	
	byte screen;
	static boolean keepGoing = true;
	
	private void paintSplash(Graphics2D g2d)
	{
		
	}
	

	@Override
	public void paint(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D)g;
		paintSplash(g2d);
	}
	
	@Override
	public void run()
	{
		JFrame frame = new JFrame("stuff");
		frame.add(this);
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		
		while(keepGoing)
		{
			repaint();
			try {
				Thread.sleep(100);
				System.out.println("ok");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		frame.setVisible(false);
		frame.removeAll();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		keepGoing = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
}