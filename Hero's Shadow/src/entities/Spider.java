package entities;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import gamepackage.Entity;
import gamepackage.MainLoop;

public class Spider 
{
	double X;
	double Y;
	Entity Instance;
	Image spider_up = Toolkit.getDefaultToolkit().getImage("entities/spider/spider up.png");
	Image spider_down = Toolkit.getDefaultToolkit().getImage("entities/spider/spider down.png");
	Image spider_right = Toolkit.getDefaultToolkit().getImage("entities/spider/spider right.png");
	Image spider_left = Toolkit.getDefaultToolkit().getImage("entities/spider/spider left.png");
	
	public Spider(Entity instance)
	{
		Instance = instance;
		
	}
	
	
	public void update()
	{
		X = Instance.X();
		Y = Instance.Y();
		//Image VillageElderGuy = Toolkit.getDefaultToolkit().getImage("entities/village elder guy.png");
		Image img = null;
		double deg = Math.atan2((Y-MainLoop.playerentity.Y), (X-MainLoop.playerentity.X));
		X-=Math.cos(deg);
		Y-=Math.sin(deg);
		deg = deg * 180/Math.PI;
		if(deg >=-45 && deg <= 45)
		{
			img=spider_left;
		}
		else if(deg >=45 && deg<= 135)
		{
			img=spider_up;
		}
		else if(deg <=-45 && deg>= -135)
		{
			img=spider_down;
		}
		else
		{
			img = spider_right;
		}
		Instance.SetSprite(img);
		Instance.X = X;
		Instance.Y = Y;
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
