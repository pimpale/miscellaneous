package entities;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.util.ArrayList;

import gamepackage.Entity;
import gamepackage.UsefulMethods;
import gamepackage.MainLoop;

public class Player
{
	static double playerspeed = 3;
	static double playerMaxHealth = 500;
	static double playerHealth = 500;
	
	static double playerMaxSanity = 500;
	static double playerSanity = 500;
	
	static double playerMaxMagic = 500;
	static double playerMagic = 500;
	
	public static double playerspeed()
	{
		return playerspeed;
	}

	public static double playerMaxHealth()
	{
		return playerMaxHealth;
	}
	public static double playerHealth()
	{
		return playerHealth;
	}
	

	public static double playerMaxSanity()
	{
		return playerMaxSanity;
	}
	public static double playerSanity()
	{
		return playerSanity;
	}
	

	public static double playerMaxMagic()
	{
		return playerMaxMagic;
	}
	public static double playerMagic()
	{
		return playerMagic;
	}
	
	
	public static double X()
	{
		return X;
	}
	public static double Y()
	{
		return Y;
	}
	
	public void Shift(double x, double y)
	{
		X+=x;
		Y+=y;
		playersEntity.Update();
	}
	
	
	public static double X = 0;
	public static double Y = 0;
	
	double mousex = (MouseInfo.getPointerInfo().getLocation().getX());
	double mousey = (MouseInfo.getPointerInfo().getLocation().getY());
	
	public static Entity playersEntity;
	int Counter = 0;
	public Player(Entity playersEntityq)
	{
		X= playersEntityq.X();
		Y= playersEntityq.Y();
		playersEntity = playersEntityq;
		playersEntity.SetSprite(GetImage());
		playerHealth = playersEntity.Health();
		playersEntity.Setting = MainLoop.setting;
	}
	Image heroback = Toolkit.getDefaultToolkit().getImage("entities/hero/hero(back).png");
	Image herofront = Toolkit.getDefaultToolkit().getImage("entities/hero/hero(front).png");
	Image heroleft = Toolkit.getDefaultToolkit().getImage("entities/hero/hero(left).png");
	Image heroright = Toolkit.getDefaultToolkit().getImage("entities/hero/hero(right).png");
	
	
	
	public void update() 
	{
		mousex = (MouseInfo.getPointerInfo().getLocation().getX());
		mousey = (MouseInfo.getPointerInfo().getLocation().getY());
		Counter +=1;
		X= playersEntity.X();
		Y= playersEntity.Y();
		playersEntity.SetSprite(GetImage());
		playerHealth = playersEntity.Health();
		playersEntity.Setting = MainLoop.setting;
		playersEntity.isHolding = (UsefulMethods.Items);
		moveplayer();
	}
	
	
	/*
	public void shift(double x, double y)
	{
		if(MainLoop.setting == null)
		{
			MainLoop.playerxmain +=x;
			MainLoop.playerymain +=y;
		}
		else
		{
			MainLoop.playerxdungeon +=x;
			MainLoop.playerydungeon +=y;
		}
		X+=x;
		Y+=y;
		playersEntity.X = X;
		playersEntity.Y = Y;
	}
	*/
	
	
	public void moveplayer()
	{
		
		if(MainLoop.setting == null)
		{
			if(MainLoop.keysPressed.contains('s'))
			{
				Y -= Player.playerspeed();
			}
			if(MainLoop.keysPressed.contains('w'))
			{
				Y += Player.playerspeed();
			}
			if(MainLoop.keysPressed.contains('a'))
			{
				X -= Player.playerspeed();
			}
			if(MainLoop.keysPressed.contains('d'))
			{
				X += Player.playerspeed();
			}
		}
		playersEntity.X = X;
		playersEntity.Y = Y;
	}
	
	
	
	public Image GetImage()
	{
		Image img = null;
		Counter = 0;
		if(MainLoop.keysPressed.contains('a'))
		{
			img = heroleft;
		}
		else if(MainLoop.keysPressed.contains('d'))
		{
			img = heroright;
		}
		else if(MainLoop.keysPressed.contains('w'))
		{
			img = heroback;
		}
		else if(MainLoop.keysPressed.contains('s'))
		{
			img = herofront;
		}
		else
		{
			if(Counter>60)
			{
				double deg = Math.atan2((350 -mousey), (350-mousex));
				deg = deg * 180/Math.PI;
				if(deg >=-45 && deg <= 45)
				{
					img=heroleft;
				}
				else if(deg >=45 && deg<= 135)
				{
					img=heroback;
				}
				else if(deg <=-45 && deg>= -135)
				{
					img=herofront;
				}
				else
				{
					img = heroright;
				}
			}
			else
			{
				Image thing = playersEntity.Sprite();
				if(thing == null)
				{
					img = herofront;
				}
				else
				{
					img = thing;
				}
			}
		}
		return img;
	}

	public void onclick() {
		// TODO Auto-generated method stub
		
	}

	
	
}
