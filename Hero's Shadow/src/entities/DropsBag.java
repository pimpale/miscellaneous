package entities;

import java.awt.Image;
import java.awt.Toolkit;

import gamepackage.*;

public class DropsBag 
{
	Entity Instance;
	Image Sprite = Toolkit.getDefaultToolkit().getImage("entities/dropsbag/drops bag.png");
	public DropsBag(Entity instance)
	{
		Instance = instance;
		Instance.SetSprite(Sprite);
	}
	
	public void update()
	{
		Instance.SetSprite(Sprite);
	}

	public void onclick() 
	{
		MainLoop.OpenBag(this, Instance.isHolding);
	}
	
	public void updatestocks(Item[] itemlist)
	{
		Instance.isHolding = itemlist;
	}
}
