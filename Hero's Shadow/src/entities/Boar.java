package entities;

import java.awt.Image;
import java.awt.Toolkit;

import gamepackage.Entity;

public class Boar {

	public double X = 0;
	public double Y = 0;
	Entity BoarEntity = null;
	int Counter;
	public double thing1 = 0;
	Image Boar_front = Toolkit.getDefaultToolkit().getImage("entities/wild boar/wild pig front.png");
	Image Boar_back = Toolkit.getDefaultToolkit().getImage("entities/wild boar/wild pig back.png");
	Image Boar_left = Toolkit.getDefaultToolkit().getImage("entities/wild boar/wild pig left.png");
	Image Boar_right = Toolkit.getDefaultToolkit().getImage("entities/wild boar/wild pig right.png");
	
	double momentum = 0;
	public boolean BOO = false;
	boolean angry = true;
	Entity Hitter = Player.playersEntity;
	
	
	public Boar(Entity instance)
	{
		BoarEntity = instance;
		BoarEntity.KnockBackofThing = 10;
	}

	
	
	public void ifHit(Entity hitter)
	{
		if(hitter != null)
		{
			angry = true;
			Hitter = hitter;
		}
	}
	
	
	public void update()
	{
		X = BoarEntity.X();
		Y = BoarEntity.Y();
		if(angry)
		{
			
			
			double x1 = X;
			double y1 = Y;
			double x2 = Hitter.X();
			double y2 = Hitter.Y();
		
			double x3 = X + 100 * Math.cos(thing1);
			double y3 = Y + 100 * Math.sin(thing1);
		
			double d;
			double ux;
			double vx;
			double uy;
			double vy;
		
			ux = x2 - x1  ;  uy = y2 - y1;   // u = (ux,uy) = vector from p1 to p2
			vx = x3 - x1  ;  vy = y3 - y1;   // v = (vx,vy) = vector from p1 to p3
			d = ux*vx + uy*vy;               // Dot product of u and v
			//System.out.println(d);
			Counter++;
			if(Counter > 20)
			{	
				if(d < 0)
				{
					momentum -=0.05;
					BOO = false;
				}
				else
				{
					momentum +=0.05;
					BOO = true;
				}
			}
			else
			{
				momentum = 0.01;
			}
			if(momentum > 4)
			{
				momentum = 4;
			}
		
			if(momentum < 0)
			{
				thing1 = Math.atan2((Hitter.Y()-Y), (Hitter.X()-X));
				Counter = 0;
			}
		
			X+=1*momentum*Math.cos(thing1);
			Y+=1*momentum*Math.sin(thing1);
		}
		else
		{
			Counter ++;
			if(Counter == 50)
			{
				thing1 = (Math.random()*2*Math.PI) -Math.PI;
			}
			else if(Counter > 0 && Counter < 150)
			{
				X+=0.5*Math.cos(thing1);
				Y+=0.5*Math.sin(thing1);
			}
			else if(Counter > 300)
			{
				Counter = 0;
			}
		}
		
		Image img = null;
		double thing2 = thing1 * 180/Math.PI;
		if(true == true)
		{
			thing2 = -thing2;
		}
		//System.out.println(thing2);
		if(thing2 >=-45 && thing2 <= 45)
		{
			img=Boar_left;
		}
		else if(thing2 >=45 && thing2<= 135)
		{
			img=Boar_back;
		}
		else if(thing2 <=-45 && thing2>= -135)
		{
			img=Boar_front;
		}
		else
		{
			img = Boar_right;
		}
		//img = Boar_front;
		BoarEntity.SetSprite(img);
		BoarEntity.X = X;
		BoarEntity.Y = Y;
	}
	
	
	public double X()
	{
		return X;
	}

	public double Y()
	{
		return Y;
	}



	public void onclick() {
		// TODO Auto-generated method stub
		
	}
	

}