package gamepackage;

import java.awt.Image;
import java.util.ArrayList;

import entities.Boar;
import entities.DropsBag;
import entities.Player;
import entities.Spider;
import entities.Villager;

public class Entity 
{
	boolean active  = false;
	public Dungeon Setting = null;
	public Object AI;
	int invulnerability =0;
	public double X = 0;
	public double Y = 0;
	double MaxHealth;
	double Health = 0;
	String Type = "";
	String Subtype = "";
	Image Sprite;
	int Xonmap = 0;
	int Yonmap = 0;
	public int KnockBackofThing;
	public Item[] isHolding = {new Item("goldcoin", 1), new Item("bronzeshortsword", 1)};
	boolean selected = false;
	ArrayList<String> Effectlist = new ArrayList<String>();
	ArrayList<Integer> Effectlength = new ArrayList<Integer>();
	public Entity(double x, double y, String type, String subtype, String effects, Dungeon setting)
	{
		Xonmap = (int)(x/MainLoop.TILESIZE);
		Yonmap = (int)(y/MainLoop.TILESIZE);
		
		Setting = setting;
		Type=type;
		if(effects != "")
		{
			String[] Effect = effects.split(":");
			EffectWith(Effect[0],Integer.parseInt(Effect[1]));
		}
		Subtype = subtype;
		KnockBackofThing = 1;
		X = x;
		Y = y;
		if(type == "player")
		{
			Player thing = new Player(this); 
			MaxHealth = Player.playerMaxHealth();
			AI = thing;
		}
		else if(type == "villager")
		{
			Villager thing = new Villager(this,subtype); 
			MaxHealth = 500;
			AI = thing;
		}
		else if(type == "spider")
		{
			Spider thing = new Spider(this); 
			MaxHealth = 10;
			AI = thing;
		}
		else if(type == "boar")
		{
			Boar thing = new Boar(this); 
			MaxHealth = 200;
			AI = thing;
		}
		else if(type == "dropsbag")
		{
			DropsBag thing = new DropsBag(this);
			MaxHealth = 100;
			AI = thing;
		}
		
		Health = MaxHealth;
		
	}
	
	public double X()
	{
		return X;
	}
	
	public double Y()
	{
		return Y;
	}
	

	public Object AIpair() 
	{
		return null;
	}
	
	
	
	
	
	public boolean Active()
	{
		return active;
	}
	
	public int Xdimension()
	{
		try
		{
			int thing = 0;
			if(Sprite != null)
			{
				thing = Sprite.getWidth(null);
				//if(Subtype == "elder")System.out.println(thing);
				
			}
			return thing;
					
		}
		catch( java.lang.NullPointerException e)
		{
			System.out.println("hi");
			return 0;
		}
	}
	
	public int Ydimension()
	{
		try
		{
			int thing = 0;
			if(Sprite != null)
			{
				thing = Sprite.getHeight(null);
				//if(Subtype == "elder")System.out.println(thing);
				
			}
			return thing;
					
		}
		catch( java.lang.NullPointerException e)
		{
			System.out.println("hi");
			return 0;
		}
	}
	
	public String Type()
	{
		return Type;
	}
	
	public String Subtype()
	{
		return Subtype;
	}
	
	public Dungeon Setting()
	{
		return Setting;
	}
	
	public double Health()
	{
		return Health;
	}
	
	public Image Sprite()
	{
		return Sprite;
	}
	
	public void Damage(int damagegiven)
	{
		
		Health -=damagegiven;
	}
	
	public void Heal(int healthgiven)
	{
		Health +=healthgiven;
	}
	
	double sourceX = 0;
	double sourceY = 0;
	public void EffectWith(String effect, int length)
	{
		String[] thing = effect.split("#");
		if(thing.length > 1)
		{
			sourceX = Double.parseDouble(thing[1]);
			sourceY = Double.parseDouble(thing[2]);
		}
		
		Effectlist.add(thing[0]);
		Effectlength.add(length);
	}
	
	public void SetSprite(Image newsprite)
	{
		
		Sprite = newsprite;
	}
	
	
	
	public void Deactivate()
	{
		active = false;
	}
	public void Activate()
	{
		active = true;
	}
	
	
	public void Select()
	{
		if(Type == "player")
		{
			Player hi = (Player) AI; 
			hi.onclick();
		}
		if(Type == "spider")
		{
			Spider hi = (Spider) AI; 
			hi.onclick();
		}
		else if(Type == "villager")
		{
			Villager hi = (Villager) AI; 
			hi.onclick();
		}
		else if(Type == "boar")
		{
			Boar hi = (Boar) AI; 
			hi.onclick();
		}
		else if(Type == "dropsbag")
		{
			DropsBag hi = (DropsBag) AI;
			hi.onclick();
		}
		
	}
	
	
	public int contactDamage(Entity bob) 
	{
		return 0;
	}
	
	public int knockbackval() 
	{
		return KnockBackofThing;
	}
	private void UpdateEffects()
	{
		for(int i = 0; i < Effectlist.size(); i++)
		{
			double magnitude = 5;
			
			
			if(Effectlist.get(i).equals("invulnerable")&&Effectlength.get(i) >= 0)
			{
				Health = MaxHealth;
				Effectlength.set(i, Effectlength.get(i)-1);
				break;
			}
			
			if(Effectlist.get(i).equalsIgnoreCase("knockback"))
			{
				//Entitymove = false;
				double sourcedeg = Math.atan2(sourceY-Y-Ydimension()/2, sourceX-X-Xdimension()/2);
				X +=-magnitude*Math.cos(sourcedeg);
				Y +=-magnitude*Math.sin(sourcedeg);
				
				//X +=-magnitude*Math.cos(Math.atan2(sourceY-Y-Ydimension()/2, sourceX-X-Xdimension()/2));
				//Y +=-magnitude*Math.sin(Math.atan2(sourceY-Y-Ydimension()/2, sourceX-X-Xdimension()/2));
				
				if(Type == "player")
				{
					if(MainLoop.setting == null)
					{
				//		MainLoop.playerxmain +=-magnitude*Math.cos(sourcedeg);
				//		MainLoop.playerymain +=-magnitude*Math.sin(sourcedeg);
					}
					else
					{
				//		MainLoop.playerxdungeon +=-magnitude*Math.cos(sourcedeg);
				//		MainLoop.playerydungeon +=-magnitude*Math.sin(sourcedeg);
					}
				}
			}
			int time = Effectlength.get(i);
			time -= 1;
			if(time < 0)
			{
				Effectlength.remove(i);
				Effectlist.remove(i);
			}
			else
			{
				Effectlength.set(i, time);
			}
		}
		
		
		
	}
	

	public void Update()
	{ 
		
		Xonmap = (int)(X/MainLoop.TILESIZE);
		Yonmap = (int)(Y/MainLoop.TILESIZE);
	
		UpdateEffects();
		
		
		if(Type == "player")
		{
			Player hi = (Player) AI; 
			hi.update();
		}
		if(Type == "spider")
		{
			Spider hi = (Spider) AI; 
			hi.update();
		}
		else if(Type == "villager")
		{
			Villager hi = (Villager) AI; 
			hi.update();
		}
		else if(Type == "boar")
		{
			Boar hi = (Boar) AI; 
			hi.update();
		}
		else if(Type == "dropsbag")
		{
			DropsBag hi = (DropsBag) AI; 
			hi.update();
		}
		
		
	}
}
