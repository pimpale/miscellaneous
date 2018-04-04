	package gamepackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.w3c.dom.css.Rect;

import entities.DropsBag;
import entities.Player;
import entities.Villager;

@SuppressWarnings("serial")
public class MainLoop extends JPanel implements MouseListener, KeyListener
{	

	public MainLoop()
	{
		addMouseListener(this);
		this.addKeyListener(this);
	}


	int mousepressedx = 0;
	int mousepressedy = 0;
	static int mousereleasedx = 0;
	int mousereleasedy = 0;
	static double mousex = 0;
	static double mousey = 0;
	static int mouseclickedx = 0;
	static int mouseclickedy= 0;
	static boolean mousedown = false;
	boolean rightkeydown = false;
	public static ArrayList<Character> keysPressed = new ArrayList<Character>();
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		mouseclickedx = arg0.getX();
		mouseclickedy = arg0.getY();		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{

	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{

	}
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		mousedown = true;
		mousepressedx = arg0.getX();
		mousepressedy = arg0.getY();
		mousereleasedx = 0;
		mousereleasedy = 0;
		int modifiers = arg0.getModifiers();
		if((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) 
		{
			rightkeydown = true;
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		mousedown = false;
		mousepressedx = 0;
		mousepressedy = 0;
		mousereleasedx = arg0.getX();
		mousereleasedy = arg0.getY();		
		int modifiers = arg0.getModifiers();
		if((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) 
		{
			rightkeydown = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		MainLoop.keysPressed.add(arg0.getKeyChar());

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		MainLoop.keysPressed.remove(arg0.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	static Tile[][] tilegrid = new Tile[0][0];
	Tile[][] worldgrid = new Tile[0][0];



	void CreateWorld()
	{
		worldgrid = UsefulMethods.ProcessMap();
		tilegrid = worldgrid;
		CreateEntity(0, 0, "player", "", "", null);
		CreateEntity(7, 7, "spider", "", "", null);
		//MainLoop.CreateEntity(5, 5, 10, "boar", "", "", null);
		UsefulMethods.addItem("goldcoin", 100, 0,0);
		UsefulMethods.addItem("basicstaff", 1, 0,10);


	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	static ArrayList<Entity> Entities = new ArrayList<Entity>();
	public static ArrayList<Entity> getEntities()
	{
		return Entities;
	}

	static ArrayList<Object> EntityAIs = new ArrayList<Object>();
	public static ArrayList<Object> getEntityAIs()
	{
		return EntityAIs;
	}

	static ArrayList<Projectile> Projectiles = new ArrayList<Projectile>();
	public static ArrayList<Projectile> getProjectiles()
	{
		return Projectiles;
	}

	static HashMap<Point, Dungeon> Dungeons = new HashMap<Point, Dungeon>();
	public static HashMap<Point, Dungeon> getDungeons()
	{
		return Dungeons;
	}



	static int TILESIZE = 64;
	static boolean gamepaused = false;
	static boolean InventoryOn = false;
	String displaystring = "";
	static int draggedXpos = -1;
	static int draggedYpos = -1;
	static int cooldown = 0;
	public static Dungeon setting = null;
	static boolean villagertrading = false;
	static boolean openingdrops = false;
	static DropsBag Bagthatisopened = null;
	static Villager trader;
	public static Entity playerentity= null;
	static ArrayList<Item> Items = new ArrayList<Item>();

	public static Entity CreateEntity(double mapX, double mapY, String Type, String subtype, String Effects, Dungeon EntitySetting)
	{
		double X = mapX*TILESIZE;
		double Y = mapX*TILESIZE;

		Entity newentity = new Entity(X, Y,Type, subtype,Effects, EntitySetting);
		Entities.add(newentity);
		EntityAIs.add(newentity.AIpair());
		//System.out.println(newentity);
		return newentity;

	}

	public static void DestroyEntity(Entity destroyable)
	{
		for(int i = 0; i < Entities.size(); i++)
		{
			if(Entities.get(i).equals(destroyable))
			{
				if(destroyable.isHolding != null && !destroyable.Type.equals("dropsbag"))
				{
					Entity bob = CreateEntity(0, 0,"dropsbag", "", "invulnerable:9999999", setting);
					bob.isHolding = destroyable.isHolding;
					bob.X = destroyable.X;
					bob.Y = destroyable.Y;
				}
				Entities.remove(i);
				EntityAIs.remove(i);
			}
		}
	}

	public static Dungeon InitializeDungeon(Point entrancePoint, String type)
	{
		Dungeon newDungeon = new Dungeon(entrancePoint.x, entrancePoint.y, type);
		Point entranceXYonmap = entrancePoint; 
		Dungeons.put(entranceXYonmap, newDungeon);		
		return newDungeon;
	}

	public void functions()
	{
		mousex = (MouseInfo.getPointerInfo().getLocation().getX()-8);
		mousey = (MouseInfo.getPointerInfo().getLocation().getY() -30);
		playerinterface();
		if(gamepaused == false)
		{
			projectile(); //TODO add projectiles later
			entity();
			EntityCollision();
			//EnterDungeon(); TODO add dungeons later
			rendermap();
		}
		if(playerentity == null)
		{
			CreateEntity(0, 0, "player", "", "", null);
		}
		repaint();
	}


	private static void playerinterface() 
	{
		cooldown -=1;
		if(mousex < 700 && mousedown == true && gamepaused == false)
		{

			boolean villagerclicked = false;
			for(int i = 0; i< Entities.size(); i++)
			{
				Entity bob = Entities.get(i);
				if((mousex+playerentity.X() -350  >= bob.X()-playerentity.X() && mousex -playerentity.X() -350 <= bob.X() + bob.Xdimension()) && (mousey -playerentity.Y() -350> bob.Y() && mousey -playerentity.X() -350 < bob.Y() + bob.Ydimension()))
				{
					mousex = 0;
					mousey = 0;//TODO
					mousedown = false;
					bob.Select();
					villagerclicked = true;
				}
			}


			if(cooldown < 1 && villagerclicked == false)
			{
				Item [] hotbar = UsefulMethods.DisplayHotbar();
				Item select= null;//hotbar[boxselected];
				if(select != null)
				{
					double degrees = Math.atan2((playerentity.Y() -mousey), (playerentity.X()-mousex));
					Projectile projectile = new Projectile(playerentity.X(), playerentity.Y(), select.weapontype, select.effects, select.attack, select.speed, degrees, select.projectilelife, playerentity);
					Projectiles.add(projectile);
					cooldown = select.cooldown;
				}
				else
				{
					double degrees = Math.atan2((playerentity.Y() -mousey), (playerentity.X()-mousex));
					Projectile projectile = new Projectile(playerentity.X()+90, playerentity.Y(), "melee", "knockback:2", 1, 0, degrees, 10,playerentity);
					Projectiles.add(projectile);
					cooldown = 50;
				}

			}
			mouseclickedx = 0;
			mouseclickedy = 0;
		}

		//Pause or Play button
		if(mouseclickedx > 710 && mouseclickedx < 790 && mouseclickedy > 20 && mouseclickedy < 40 && InventoryOn == false)
		{
			gamepaused = !gamepaused;
			mouseclickedx = 0;
			mouseclickedy = 0;
		}
		//Inventory
		if((mouseclickedx > 800 && mouseclickedx < 880 && mouseclickedy > 20 && mouseclickedy < 40))
		{
			gamepaused = true;
			villagertrading = false;
			openingdrops = false;
			InventoryOn =!InventoryOn;
			mouseclickedx = 0;
			mouseclickedy = 0;

		}
		//savegame
		if(mouseclickedx > 890 && mouseclickedx < 970 && mouseclickedy > 12 && mouseclickedy < 12+18)
		{
			UsefulMethods.SaveGame();
			mouseclickedx = 0;
			mouseclickedy = 0;
		}

		if(mouseclickedx > 890 && mouseclickedx < 970 && mouseclickedy > 34 && mouseclickedy < 34+18)
		{
			UsefulMethods.LoadGame();
			mouseclickedx = 0;
			mouseclickedy = 0;
		}

		if(InventoryOn == true && mousedown == true && draggedXpos == -1 && !villagertrading)
		{
			for(int i = 0; i < 10; i++)
			{
				for(int a = 0; a < 10; a++)
				{
					int xpos = 100+((i)*50)+9;
					int ypos = 70+((a)*50)+9;
					if((mousex > xpos && mousex < xpos+32 && mousey > ypos && mousey < 32+ypos ))
					{
						draggedXpos=i;
						draggedYpos=a;
						break;
					}
				}
			}
			for(int i = 0; i < 10; i++)
			{
				int ypos = 40 +((i)*50);
				if((mousex > 1000 && mousex < 1050 && mousey > ypos && mousey < 50+ypos ))
				{
					draggedXpos=10;
					draggedYpos=i;
					break;
				}
			}
			if(openingdrops)
			{
				for(int i = 0; i < 10; i++)
				{
					int xpos = 100 +((i)*50);
					if((mousex > xpos && mousex < xpos+50 && mousey > 600 && mousey < 650))
					{
						draggedXpos=11;
						draggedYpos=i+1;
						break;
					}
				}
			}
			if((mousex > 1000 && mousex < 1050 && mousey > 570 && mousey < 640))
			{
				draggedXpos= 11;
				draggedYpos= 0;
			}
		}
		else if(villagertrading == true && mouseclickedx != 0 && mouseclickedy != 0)
		{
			mouseclickedx = 0;
			mouseclickedy = 0;
			for(int i = 0; i < 10; i++)
			{
				for(int a = 0; a < 10; a++)
				{
					int xpos = 100+((i)*50)+9;
					int ypos = 70+((a)*50)+9;
					if((mousex > xpos && mousex < xpos+49 && mousey > ypos && mousey < 49+ypos ))
					{
						int tradeXpos=i;
						int tradeYpos=a;
						UsefulMethods.Trade(tradeXpos,tradeYpos);
						break;
					}
				}
			}
			for(int i = 0; i < 10; i++)
			{
				int ypos = 40 +((i)*50);
				if((mousex > 1000 && mousex < 1050 && mousey > ypos && mousey < 50+ypos ))
				{
					int tradeXpos=10;
					int tradeYpos=i;
					UsefulMethods.Trade(tradeXpos,tradeYpos);
					break;
				}
			}
			for(int i = 0; i < 10; i++)
			{
				int xpos = 100 +((i)*50);
				if((mousex > xpos && mousex < xpos+50 && mousey > 600 && mousey < 650))
				{
					int tradeXpos=11;
					int tradeYpos=i+1;
					UsefulMethods.Trade(tradeXpos,tradeYpos);
					break;
				}
			}

		}


		if(InventoryOn == true && draggedXpos!=-1 && mousereleasedx != 0)
		{
			for(int i = 0; i < 10; i++)
			{
				for(int a = 0; a < 10; a++)
				{
					int xpos = 100+((i)*50);
					int ypos = 70+((a)*50);
					if((mousex > xpos && mousex < xpos+50 && mousey > ypos && mousey < 50+ypos ))
					{
						UsefulMethods.switchItems(draggedXpos, draggedYpos, i, a);
						if(openingdrops)
						{
							Item[] itemlist = new Item[10];
							for(int q = 0; q < 10; q++)
							{
								itemlist[i] = UsefulMethods.Items[i+111];
							}
							Bagthatisopened.updatestocks(itemlist); 
						}
						draggedXpos=-1;
						draggedYpos=-1;
						break;
					}
				}
			}
			for(int i = 0; i < 10; i++)
			{
				int ypos = 40 +((i)*50);
				if((mousex > 1000 && mousex < 1050 && mousey > ypos && mousey < 50+ypos ))
				{
					UsefulMethods.switchItems(draggedXpos, draggedYpos, 10, i);
					if(openingdrops)
					{
						Item[] itemlist = new Item[10];
						for(int q = 0; q < 10; q++)
						{
							itemlist[i] = UsefulMethods.Items[i+111];
						}
						Bagthatisopened.updatestocks(itemlist); 
					}
					draggedXpos=-1;
					draggedYpos=-1;
					break;
				}
			}

			if(openingdrops)
			{
				for(int i = 0; i < 10; i++)
				{
					int xpos = 100 +((i)*50);
					if((mousex > xpos && mousex < xpos+50 && mousey > 600 && mousey < 650))
					{
						UsefulMethods.switchItems(draggedXpos, draggedYpos, 11, i+1);
						Item[] itemlist = new Item[10];
						for(int q = 0; q < 10; q++)
						{
							itemlist[i] = UsefulMethods.Items[i+111];
						}
						Bagthatisopened.updatestocks(itemlist); 
						draggedXpos=-1;
						draggedYpos=-1;
						break;
					}
				}
			}
			if((mousex > 1000 && mousex < 1050 && mousey > 570 && mousey < 640))
			{
				UsefulMethods.switchItems(draggedXpos, draggedYpos, 11, 0);
				draggedXpos=-1;
				draggedYpos=-1;
			}
		}
	}

	// We'll reimplement projectiles later. I just want the game to work!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private void projectile()
	{
		for(int i = 0; i< Projectiles.size(); i++)
		{
			Projectile projectile = Projectiles.get(i);
			if(projectile.Type()== "melee")
			{
				Image meleestrikeup = Toolkit.getDefaultToolkit().getImage("projectiles/meleestrike/range1 melee strike up.png");
				Image meleestrikedown = Toolkit.getDefaultToolkit().getImage("projectiles/meleestrike/range1 melee strike down.png");
				Image meleestrikeleft = Toolkit.getDefaultToolkit().getImage("projectiles/meleestrike/range1 melee strike left.png");
				//Image meleestrikeright = Toolkit.getDefaultToolkit().getImage("projectiles/meleestrike/range1 melee strike right.png");
				double xmin;
				double ymin;
				double xmax;
				double ymax;
				if(projectile.Sprite == meleestrikeup)
				{
					ymin = projectile.Y() -90;
					ymax = projectile.Y();
					xmin = projectile.X()-90;
					xmax = projectile.X()+90;
				}
				else if(projectile.Sprite == meleestrikedown)
				{
					ymin = projectile.Y();
					ymax = projectile.Y()+90;
					xmin = projectile.X()-90;
					xmax = projectile.X()+90;
				}
				else if(projectile.Sprite == meleestrikeleft)
				{
					ymin = projectile.Y()-90;
					ymax = projectile.Y()+90;
					xmin = projectile.X()-90;
					xmax = projectile.X();
				}
				else
				{
					ymin = projectile.Y()-90;
					ymax = projectile.Y()+90;
					xmin = projectile.X();
					xmax = projectile.X()+90;
				}
				for(int a = 0; a< Entities.size(); a++)
				{
					Entity bob = Entities.get(a);
					if(!bob.Type().equals(projectile.Caster.Type()))
					{
						if(bob.X()> xmin && bob.X() < xmax && bob.Y()> ymin && bob.Y() < ymax )
						{
							bob.Damage((int) projectile.Damage());
							String string1 = projectile.Effects();
							String[] Effects = string1.split(",");
							for(int q = 0; q < Effects.length; q++)
							{
								String[] parts = Effects[q].split(":");
								bob.EffectWith(parts[0]+"#"+projectile.Caster.X()+"#"+projectile.Caster.Y(), Integer.parseInt(parts[1]));
							}
						}
					}
				}

			}
		}

		for(int i = 0; i< Projectiles.size(); i++)
		{
			Projectile bob = Projectiles.get(i);
			bob.update();
			if(bob.lifeleft == 0)
			{
				Projectiles.remove(i);
			}

		}

	}


	private void entity()
	{
		for(int i = 0; i < Entities.size(); i++)
		{
			if(Entities.get(i).Type().equalsIgnoreCase("player"))
			{
				playerentity = Entities.get(i);
			}
		}

		for(int i = 0; i< Entities.size(); i++)
		{
			Entity bob = Entities.get(i);
			double distance = Math.sqrt(Math.pow(playerentity.Y() - bob.Y(), 2) + Math.pow(playerentity.Y()-bob.X(), 2));
			if(bob.Active() == true)
			{	
				bob.Update();
				if(setting != bob.Setting())
				{
					bob.Deactivate(); // if it is in another setting, it deactivates
				}
				if(TILESIZE*100 <distance)
				{
					bob.Deactivate(); // check if distance is too great.
				}
			}
			else
			{
				if(setting == bob.Setting() && TILESIZE*80 > distance)
				{
					bob.Activate(); // if it is in the same setting and the distance is less than 100 tiles, it will reactivate
				}
			}

			if(bob.Health()<0)
			{
				DestroyEntity(bob); //if health is less than 0 , it diess die.
			}
		}	
	}

	private void EntityCollision()
	{				
		boolean playerExists = false;
		for(int i = 0; i < Entities.size(); i++)
		{
			if(Entities.get(i).Type().equalsIgnoreCase("player"))
			{
				playerentity = Entities.get(i);
				playerExists = true;
				break;
			}
		}
		if(playerExists == true)
		{
			for(int i = 0; i < Entities.size(); i++)
			{
				Entity bob = Entities.get(i);
				if(bob.Active())
				{
					double iXmin = bob.X();
					double iXmax = bob.X()+ bob.Xdimension();
					double iYmin = bob.Y();
					double iYmax = bob.Y() + bob.Ydimension();

					for(int a = 0; a< Entities.size(); a++)
					{
						Entity hi = Entities.get(a);
						if(a!=i && hi.Active())
						{
							double aXmin = hi.X();
							double aXmax = hi.X()+hi.Xdimension();
							double aYmin = hi.Y();
							double aYmax = hi.Y()+hi.Ydimension();

							if((iXmin<aXmax)&&(iXmax>aXmin)&&(iYmin<aYmax)&&(iYmax>aYmin))
							{
								String effect = "knockback"+"#"+(int)hi.X()+"#"+(int)hi.Y();
								bob.EffectWith(effect, hi.knockbackval());
								bob.Damage(hi.contactDamage(bob));
							}	
						}
					}

					ArrayList<Double> xStart = new ArrayList<Double>();
					ArrayList<Double> yStart = new ArrayList<Double>();


					for(int a = 0; a < 8; a++)
					{
						for(int b = 0; b < 8; b++)
						{
							try
							{
								int xonmap = -4+b + bob.Xonmap;
								int yonmap = -4+a  +bob.Yonmap;
								Tile tile = (tilegrid[yonmap][xonmap]);
								if(tile.Collidable() == true)
								{
									xStart.add(xonmap*TILESIZE+0.0);
									yStart.add(yonmap*TILESIZE+0.0);
								}
							}
							catch(ArrayIndexOutOfBoundsException e)
							{

							}
							catch(NullPointerException e)
							{

							}
						}
					}


					for(int a = 0; a < xStart.size(); a++)
					{

						double aXmin = xStart.get(a);
						double aXmax = xStart.get(a)+MainLoop.TILESIZE;
						double aYmin = yStart.get(a);
						double aYmax = yStart.get(a)+MainLoop.TILESIZE;

						if ((iXmin<aXmax)&&(iXmax>aXmin)&&(iYmin<aYmax)&&(iYmax>aYmin))
						{
							String effect = "knockback"+"#"+(xStart.get(a).intValue()+TILESIZE/2)+"#"+(yStart.get(a).intValue()+TILESIZE/2);
							bob.EffectWith(effect, 1);
						}
					}
				}
			}
		}
	}

	/*
	private void EnterDungeon()
	{

		for(int i = 0; i < Entities.size(); i++)
		{
			Entity bob = Entities.get(i);
			if(bob.Type() == "player")
			{
				double iXmin = bob.X();
				double iXmax = bob.X() + bob.Xdimension();
				double iYmin = bob.Y();
				double iYmax = bob.Y() + bob.Ydimension();
				ArrayList<Double> xStart = new ArrayList<Double>();
				ArrayList<Double> yStart = new ArrayList<Double>();
				ArrayList<Point> PointThing = new ArrayList<Point>();
				ArrayList<String> dungeonType = new ArrayList<String>();
				for(int a = 0; a < 14; a++)
				{
					for(int b = 0; b < 14; b++)
					{
						if(activetilegrid[a][b] != null)
						{
							Tile tile = activetilegrid[a][b];
							if(tile.DungeonTo() != null)
							{

								Point asdfThing = new Point(b,a);
								if(tile.DungeonTo() != "Main")
								{
									xStart.add((b-2)*TILESIZE - playerx);
									yStart.add((a-2)*TILESIZE - playery);
									PointThing.add(asdfThing);
									dungeonType.add(tile.dungeonto);
								}
							}
						}
						else
						{ 
							if(setting != null)
							{
								Point asdfThing = new Point(b,a);
								PointThing.add(asdfThing);
								xStart.add((asdfThing.x-2)*TILESIZE - playerxdungeon);
								yStart.add((asdfThing.y-2)*TILESIZE - playerydungeon);
								dungeonType.add("Main");
							}
						}
					}
				}
				for(int a = 0; a < xStart.size(); a++)
				{
					double aXmin = xStart.get(a);
					double aXmax = xStart.get(a)+MainLoop.TILESIZE;
					double aYmin = yStart.get(a);
					double aYmax = yStart.get(a)+MainLoop.TILESIZE;

					if((iXmin<aXmax)&&(iXmax>aXmin)&&(iYmin<aYmax)&&(iYmax>aYmin))
					{
						int qwertyx  = PointThing.get(a).x-7+playerxonmap;
						int qwertyy  = PointThing.get(a).y-7+playeryonmap;
						//I didn't comment this, and I am sorry now
						if(dungeonType.get(a).equalsIgnoreCase("Main"))
						{
							if(setting  != null)
							{
								for(int q = 0; q < setting.EntityContained.size(); q++)
									{
										setting.EntityContained.get(q).setLocation(setting.Entitys.get(q).X(), setting.Entitys.get(q).Y()); 
										System.out.println(setting.EntityContained.get(q).x);
									}
									setting = null;
									tilegrid = worldgrid;
									Player playerAI = (Player)bob.AI;
									playerAI.shift(32-playerxonmain, 32-playeryonmain);
									rendermap();
								}
							}
							else
							{
								Point asdfThing = new Point(qwertyx,qwertyy);
								Dungeon dungeontobentered = Dungeons.get(asdfThing);

								if(dungeontobentered == null)
								{
									InitializeDungeon(asdfThing, dungeonType.get(a));
									dungeontobentered = Dungeons.get(asdfThing);
								}

								setting = dungeontobentered;
								Point playerlocate = dungeontobentered.Moveplayer();
								Player playerAI = (Player)bob.AI;
								playerxondungeon = playerlocate.x;
								playeryondungeon = playerlocate.y;
								tilegrid = dungeontobentered.Map();
								playerAI.shift(10-playerxdungeon, 62-playerydungeon);
								rendermap();
								repaint();
								if(dungeontobentered.hasthisdungeonbeenentered == false)
								{
									//creates dungeon
									for(int qwertyENTITY = 0; qwertyENTITY < dungeontobentered.EntityContained.size(); qwertyENTITY++)
									{
										Entity dungeonEntity = dungeontobentered.Entitys.get(qwertyENTITY);
										dungeonEntity.X = dungeontobentered.EntityContained.get(qwertyENTITY).getX();
										dungeonEntity.Y = dungeontobentered.EntityContained.get(qwertyENTITY).getX();
									}
									dungeontobentered.hasthisdungeonbeenentered = true;
								}
							}
						}
					}
				}
			}
		}
	 */



	//Says something
	public static void Say(String statement)
	{
		JOptionPane.showMessageDialog(null, statement);
	}
	//when given 
	public static String ChooseAnswer(String[] possibleanswers,String questionasked)
	{
		Object[] possibleValues = possibleanswers;
		String selectedValue = (String) JOptionPane.showInputDialog(null,"\""+questionasked+"\"", "Input",JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
		return selectedValue;
	}

	public static void OpenBag(DropsBag Bag, Item[] stuff)
	{
		Bagthatisopened = Bag;
		gamepaused = true;
		InventoryOn = true;
		openingdrops = true;
		for(int i = 0; i < stuff.length; i++)
		{
			int thing = i +111;
			int XposofTrade = (int)(thing/10);
			int YposofTrade = thing % 10;
			Item item = stuff[i];
			if(item != null)
			{
				UsefulMethods.addItem(item.type, item.count, YposofTrade, XposofTrade);
			}
		}
	}

	public static void Trade(Item[] itemsforsale, Villager tradeperson)
	{
		villagertrading = true;
		gamepaused = true;
		InventoryOn = true;
		trader = tradeperson;
		for(int i = 0; i < itemsforsale.length; i++)
		{
			int thing = i +111;
			int XposofTrade = (int)(thing/10);
			int YposofTrade = thing % 10;
			Item item = itemsforsale[i];
			if(item != null)
			{
				UsefulMethods.addItem(item.type, item.count, YposofTrade, XposofTrade);
			}
		}		
	}


	static ArrayList<String> NotificationList = new ArrayList<>();
	static ArrayList<Integer> NotificationListTime = new ArrayList<>();
	public static void Notify(String display)
	{
		NotificationList.add(0,display);
		NotificationListTime.add(0,1000);
		if(NotificationList.size() > 2)
		{
			for(int i = 0; i < NotificationList.size(); i++)
			{
				if(i>2)
				{
					NotificationList.remove(i);
					NotificationListTime.remove(i);
				}
			}
		}
	}



	static private void rendermap()
	{

	}

	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		setBackground(Color.black);
		g2d.setPaint(Color.red);	

		boolean playerExists = false;
		for(int i = 0; i < Entities.size(); i++)
		{
			if(Entities.get(i).Type().equalsIgnoreCase("player"))
			{
				playerentity = Entities.get(i);
				playerExists = true;
				break;
			}
		}

		if(playerExists == true)
		{

			for(int i = 0; i < 14; i++)
			{
				for(int a = 0; a < 14; a++)
				{		    
					try
					{
						int yoftile = playerentity.Yonmap-7+a;
						int xoftile = playerentity.Xonmap-7+i;
						Tile tile = tilegrid[yoftile][xoftile];
						Image img1 = tile.Sprite();
						if(img1!= null)
						{
							g2d.drawImage(img1, (xoftile)*TILESIZE-(int)playerentity.X()+350, (yoftile)*TILESIZE-(int)playerentity.Y()+350, this);
						}
					}
					catch(NullPointerException e)
					{

					}
					catch(ArrayIndexOutOfBoundsException e)
					{

					}
				}
			}


			for(int i = 0; i < Entities.size(); i++)
			{
				Entity bob = Entities.get(i);
				if(bob.Active())
				{
					g2d.drawImage(bob.Sprite, (int)(bob.X()-playerentity.X+350), (int)(bob.Y()-playerentity.Y+350), null); 
					//g2d.drawRect((int)(bob.X()-playerentity.X+350), (int)(bob.Y()-playerentity.Y+350), bob.Xdimension(), bob.Ydimension());
				}
			}
			g2d.setColor(Color.BLUE);
			g2d.fillOval(mouseclickedx, mouseclickedy, 3, 3);
			g2d.fillOval((int)mousex, (int)mousey, 3, 3);

			for(int i = 0; i < Projectiles.size(); i++)
			{
				Projectile bob = Projectiles.get(i);
				g2d.drawImage(bob.Sprite, (int)(bob.X()-playerentity.X()+350), (int)(bob.Y()-playerentity.Y()+350), null);
			}

			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(700, 0, 2000, 2000);

			g2d.setPaint(Color.white);
			g2d.fillRect(710, 20, 80, 20);
			g2d.fillRect(800, 20, 80, 20);
			g2d.fillRect(890, 12, 80, 18);
			g2d.fillRect(890, 34, 80, 18);
			g2d.setPaint(Color.red);
			g2d.fillRect(710, 65, (int)(260 * Player.playerHealth()/Player.playerMaxHealth()), 20);
			g2d.setPaint(Color.MAGENTA);
			g2d.fillRect(710, 85, (int)(260 * Player.playerSanity()/Player.playerMaxSanity()), 20);
			g2d.setPaint(Color.blue);
			g2d.fillRect(710, 105, (int)(260 * Player.playerMagic()/Player.playerMaxMagic()), 20);
			g2d.setPaint(Color.black);
			g2d.drawString("Health: " + Player.playerHealth() + "/" + Player.playerMaxHealth(), 710, 80);
			g2d.drawString("Sanity: " + Player.playerSanity() + "/" + Player.playerMaxSanity(), 710, 100);
			g2d.drawString("Magic: " + Player.playerMagic() + "/" + Player.playerMaxMagic(), 710, 120);
			g2d.drawString("SAVE", 893, 26);
			g2d.drawString("LOAD", 893, 48);
			g2d.drawString("HOTBAR:", 1000, 35);
			g2d.drawString("ARMOR:", 1000, 565);
			for(int a = 0; a < 10; a++)
			{
				g2d.drawRect(1000, 40+(a)*50, 50,50);
			}
			Color greenselect = new Color(0, 100, 0, 50);
			g2d.setPaint(greenselect);
			//g2d.fillRect(1000, 40+(boxselected*50), 50, 50);
			Color hi = new Color(100, 100, 0, 100);
			g2d.setPaint(hi);
			//g2d.fillRect(1050, 0, 500,1000);
			g2d.setPaint(Color.black);

			g2d.drawRect(1000, 570, 50,50);
			if(InventoryOn == false)
			{
				g2d.drawString("INVENTORY", 803, 35);
				if(gamepaused == true)
				{
					g2d.drawString("PLAY", 713, 35);
					Color InventoryBackgroundColor = new Color(50, 100, 50, 10);
					g2d.setPaint(InventoryBackgroundColor);
					g2d.fillRect(0, 00, 700, 700);

				}
				else
				{
					g2d.drawString("PAUSE", 720, 35);
				}
			}
			else
			{
				g2d.drawString("CLOSE", 803, 35);
				Color InventoryBackgroundColor = new Color(50, 50, 50, 150);
				g2d.setPaint(InventoryBackgroundColor);
				g2d.fillRect(0, 00, 700, 700);
				g2d.setPaint(Color.RED);
				for(int i = 0; i < 10; i++)
				{
					for(int a = 0; a < 10; a++)
					{	
						g2d.drawRect(100+(i)*50, 70+(a)*50, 50,50);
					}
				}
				g2d.setPaint(Color.BLUE);
				if(villagertrading == true || openingdrops == true)
				{
					if(villagertrading == true )
					{
						g2d.setPaint(Color.white);
						g2d.setFont(new Font("Courier New", Font.BOLD, 15));
						g2d.drawString("Trading... ", 100, 20);
						g2d.drawString("Item switching disabled. Click to sell or buy.", 100, 40);
						g2d.setPaint(Color.BLUE);
						g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
					}

					for(int i = 0; i < 10; i++)
					{	
						g2d.drawRect(100+(i)*50, 600, 50,50);
					}
					for(int i = 0; i < 10; i++)
					{
						if(draggedXpos != 11 || draggedYpos != i+1)
						{
							Item[] Item4 = UsefulMethods.DisplayDrops();
							if(Item4[i] != null)
							{
								g2d.drawImage(Item4[i].sprite, 100+50*i, 600, null);
								if(Item4[i].count > 1)
								{
									g2d.setPaint(Color.WHITE);
									g2d.drawString(Integer.toString(Item4[i].count), 100+(i)*50 + 40, 648);
								}
							}
						}
					}

					for(int i = 0; i < 10; i++)
					{
						int xpos = 100 +((i)*50);
						if((mousex > xpos && mousex < xpos+50 && mousey > 600 && mousey < 650))
						{
							int descriptionXpos=11;
							int descriptionYpos=i+1;
							Item thing = UsefulMethods.Items[descriptionYpos + 10*descriptionXpos];
							g2d.setPaint(Color.WHITE);
							if(thing != null)
							{
								g2d.drawString(thing.description, 15+(int)mousex, (int)mousey);
								g2d.setFont(new Font("Courier New", Font.BOLD, 15));
								g2d.drawString("Cost: "+thing.value, 15+(int)mousex, (int)mousey+13);
								g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
							}
							break;
						}
					}

				}
				Item[][] Item1 = UsefulMethods.Display();
				for(int i = 0; i < 10; i++)
				{

					for(int a = 0; a < 10; a++)
					{
						if(draggedXpos != i || draggedYpos != a)
						{

							Item thing = Item1[a][i];
							if(thing!=null)
							{
								g2d.drawImage(Item1[a][i].sprite, 100+(i)*50+0, 70+(a)*50+0, null);
								if(thing.count > 1)
								{
									g2d.setPaint(Color.WHITE);
									g2d.drawString(Integer.toString(thing.count), 100+(i)*50 + 40, 100+(a)*50 + 18);
								}
							}
						}
					}
				}

				if(draggedXpos == -1)
				{
					for(int i = 0; i < 10; i++)
					{
						for(int a = 0; a < 10; a++)
						{
							int xpos = 100+((i)*50)+0;
							int ypos = 70+((a)*50)+0;
							if((mousex > xpos && mousex < xpos+50 && mousey > ypos && mousey < 50+ypos ))
							{
								int descriptionXpos=i;
								int descriptionYpos=a;

								Item thing = UsefulMethods.Items[descriptionYpos + 10*descriptionXpos];
								g2d.setPaint(Color.WHITE);
								if(thing != null)
								{
									g2d.drawString(thing.description, 15+(int)mousex, (int)mousey);
									if(villagertrading == true)
									{
										g2d.setFont(new Font("Courier New", Font.BOLD, 15));
										g2d.drawString("Cost: "+thing.value, 15+(int)mousex, (int)mousey+13);
										g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
									}
								}
								break;
							}
						}
					}
				}

				if(draggedXpos != -1 && draggedXpos != -1)
				{
					if(draggedXpos  < 10)
					{
						if(Item1[draggedYpos][draggedXpos] !=null) g2d.drawImage(Item1[draggedYpos][draggedXpos].sprite, (int)mousex-25, (int)mousey -25, null);
					}
					else if(draggedXpos  == 10)
					{
						Item[] Item2 = UsefulMethods.DisplayHotbar();
						if(Item2[draggedYpos] !=null) g2d.drawImage(Item2[draggedYpos].sprite, (int)mousex-25, (int)mousey -25, null);
					}
					else if(draggedXpos == 11 && draggedYpos == 0)
					{
						Item[] Item3 = UsefulMethods.DisplayArmor();
						if(Item3[draggedYpos] !=null)g2d.drawImage(Item3[draggedYpos].sprite, (int)mousex-25, (int)mousey -25, null);
					}
					else
					{
						Item[] Item4 = UsefulMethods.DisplayDrops();
						if(Item4[draggedYpos-1] !=null) g2d.drawImage(Item4[draggedYpos-1].sprite, (int)mousex-25, (int)mousey -25, null);

					}
				}

			}


			for(int i = 0; i < 10; i++)
			{
				if(draggedXpos != 10 || draggedYpos != i)
				{
					Item[] Item2 = UsefulMethods.DisplayHotbar();
					if(Item2[i] != null)
					{
						g2d.drawImage(Item2[i].sprite, 1000, 40+(i)*50, null);
					}
				}
			}
			if(draggedXpos != 11 || draggedYpos != 0)
			{
				Item[] Item3 = UsefulMethods.DisplayArmor();
				if(Item3[0] != null)
				{
					g2d.drawImage(Item3[0].sprite, 1000, 570, null);
				}
			}


			for(int i = 0; i < NotificationList.size(); i++)
			{
				NotificationListTime.set(i,NotificationListTime.get(i)-1);
				g2d.setFont(new Font("Courier New", Font.BOLD, 15));
				Color InventoryBackgroundColor = new Color(50, 50, 50, 180);
				g2d.setPaint(InventoryBackgroundColor);
				g2d.fillRect(20, 610+i*15, NotificationList.get(i).length()*10, 15);
				g2d.setPaint(Color.white);
				g2d.drawString(NotificationList.get(i), 20, 620+i*15);
				if(NotificationListTime.get(i) <= 0)
				{
					NotificationList.remove(i);
					NotificationListTime.remove(i);
				}
			}
		}

	}



	public static void main(String[] args) throws InterruptedException 
	{	
		JFrame frame = new JFrame("game");
		MainLoop game = new MainLoop();
		frame.add(game);
		frame.setSize(1070, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.CreateWorld();
		while (true) 
		{
			game.functions();
			Thread.sleep(15);
		}
	}
}