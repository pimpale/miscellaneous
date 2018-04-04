package gamepackage;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import entities.DropsBag;
import entities.Villager;

public class UsefulMethods 
{
	//maps&dungeons
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public static Image deepwater = Toolkit.getDefaultToolkit().getImage("maptiles/deep water.png");
	public static Image water = Toolkit.getDefaultToolkit().getImage("maptiles/water.png");
	public static Image sand = Toolkit.getDefaultToolkit().getImage("maptiles/sand.png");
	public static Image gravel = Toolkit.getDefaultToolkit().getImage("maptiles/gravel.png");
	public static Image snow = Toolkit.getDefaultToolkit().getImage("maptiles/snow.png");
	public static Image ice = Toolkit.getDefaultToolkit().getImage("maptiles/ice.png");
	public static Image tundra = Toolkit.getDefaultToolkit().getImage("maptiles/tundra.png");
	public static Image plainsgrass = Toolkit.getDefaultToolkit().getImage("maptiles/plains grass.png");
	public static Image farmland = Toolkit.getDefaultToolkit().getImage("maptiles/farmland.png");
	
	public static Image highlands = Toolkit.getDefaultToolkit().getImage("maptiles/highlands.png");
	public static Image ordinaryforest = Toolkit.getDefaultToolkit().getImage("maptiles/forest grass.png");
	public static Image junglegrass = Toolkit.getDefaultToolkit().getImage("maptiles/jungle grass.png");
	public static Image northcliff = Toolkit.getDefaultToolkit().getImage("maptiles/northcliff.png");
	public static Image northeastcliff = Toolkit.getDefaultToolkit().getImage("maptiles/northeastcliff.png");
	public static Image eastcliff = Toolkit.getDefaultToolkit().getImage("maptiles/eastcliff.png");
	public static Image southeastcliff = Toolkit.getDefaultToolkit().getImage("maptiles/southeastcliff.png");
	public static Image southcliff = Toolkit.getDefaultToolkit().getImage("maptiles/southcliff.png");
	public static Image southwestcliff = Toolkit.getDefaultToolkit().getImage("maptiles/southwestcliff.png");
	public static Image westcliff = Toolkit.getDefaultToolkit().getImage("maptiles/westcliff.png");
	public static Image northwestcliff = Toolkit.getDefaultToolkit().getImage("maptiles/northwestcliff.png");
	public static Image corruption = Toolkit.getDefaultToolkit().getImage("maptiles/corruption.png");
	public static Image SmallHorizontalWoodVillageHouseChunk1 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk1.png");
	public static Image SmallHorizontalWoodVillageHouseChunk2 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk2.png");
	public static Image SmallHorizontalWoodVillageHouseChunk3 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk3.png");
	public static Image SmallHorizontalWoodVillageHouseChunk4 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk4.png");
	public static Image SmallHorizontalWoodVillageHouseChunk5 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk5.png");
	public static Image SmallHorizontalWoodVillageHouseChunk6 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk6.png");
	public static Image SmallHorizontalWoodVillageHouseChunk7 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk7.png");
	public static Image SmallHorizontalWoodVillageHouseChunk8 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk8.png");
	public static Image SmallHorizontalWoodVillageHouseChunk9 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk9.png");
	public static Image SmallHorizontalWoodVillageHouseChunk10 = Toolkit.getDefaultToolkit().getImage("maptiles/SmallHorizontalWoodVillageHouseChunk10.png");
	public static Image woodwall = Toolkit.getDefaultToolkit().getImage("maptiles/housetiles/wood wall.png");
	public static Image woodfloor = Toolkit.getDefaultToolkit().getImage("maptiles/housetiles/wood planks.png");
	public static Image woodtop = Toolkit.getDefaultToolkit().getImage("maptiles/housetiles/top of wall.png");
	public static Image stonewall = Toolkit.getDefaultToolkit().getImage("maptiles/villagecentertiles/stonebrick.png");
	public static Image stonewallshadow = Toolkit.getDefaultToolkit().getImage("maptiles/villagecentertiles/stoneroofthing.png");
	public static Image villagecenterdoor = Toolkit.getDefaultToolkit().getImage("maptiles/villagecentertiles/stonedoor.png");
	public static Image redroof = Toolkit.getDefaultToolkit().getImage("maptiles/villagecentertiles/redshingleroofthing.png");
	
	
	
	
	
	
	


	
	public static int[][] ForbiddenTiles(Image[][] activetilegrid)
	{
		int[][] forbiddentiles = new int[14][14];	
		for(int i = 0; i < 14; i++)
		 {
			 for(int a = 0; a < 14; a++)
			 {
		   		try
		   		{
		   			int switchthis = 0;
		   			Image thing = activetilegrid[i][a];
		   			if(thing == deepwater) switchthis = 1;
		   			else if(thing == northcliff || thing == northeastcliff || thing == eastcliff || thing == southeastcliff || thing == southcliff || thing == southwestcliff || thing == westcliff || thing == northwestcliff)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == SmallHorizontalWoodVillageHouseChunk1 || thing == SmallHorizontalWoodVillageHouseChunk2 || thing == SmallHorizontalWoodVillageHouseChunk3 || thing == SmallHorizontalWoodVillageHouseChunk4)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == SmallHorizontalWoodVillageHouseChunk5 || thing == SmallHorizontalWoodVillageHouseChunk6 || thing == SmallHorizontalWoodVillageHouseChunk7)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == SmallHorizontalWoodVillageHouseChunk9 || thing == SmallHorizontalWoodVillageHouseChunk10)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == SmallHorizontalWoodVillageHouseChunk8)
		   			{
		   				switchthis = 2;
		   			}
		   			else if(thing == redroof)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == stonewall)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == stonewallshadow)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == villagecenterdoor)
		   			{
		   				switchthis = 3;
		   			}
		   			else if(thing == woodwall)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == woodtop)
		   			{
		   				switchthis = 1;
		   			}
		   			else if(thing == null)
		   			{
		   				switchthis = -1;
		   			}
		   			else
		   			{
		   				switchthis = 0;
		   			}
		   			forbiddentiles[i][a] = switchthis;
		   		}
		   		catch(IndexOutOfBoundsException e)
		   		{
		   			forbiddentiles[i][a] = 0;
		   		}
			 }
		 }
		return forbiddentiles;			
	}
	
	
	
	public static Tile[][] ProcessMap()
	{
		Tile[][] maptilegrid = new Tile[0][0];
		try
		{
			BufferedImage bimg = ImageIO.read(new File("map/villagemap.png"));
			int mapwidth          = bimg.getWidth();
			int mapheight         = bimg.getHeight();
			String[][] processingtilegrid = new String[mapheight][mapwidth];
			for(int i = 0; i < mapheight; i++)
			{
				for(int a = 0; a < mapwidth; a++)
				{
					int b = 0;
					int g = 0;
					int r = 0;
					try
					{
						int clr=  bimg.getRGB(a,i);
						Color color = new Color(clr, true);
						b = color.getBlue();
						g = color.getGreen();
						r = color.getRed();
	    			
					}
					catch(ArrayIndexOutOfBoundsException e )
					{
	    			
					}
					if(r==0 && g==0 && b==255)
					{
						processingtilegrid[i][a] = "deepwater";
					}
					else if(r==0 && g == 255 && b==255)
					{
						processingtilegrid[i][a] = "water";
					}
					else if(r == 255 && g == 255 && b == 0)
					{
						processingtilegrid[i][a] = "sand";
					}
					else if(r == 200 && g == 200 && b == 200)
					{
						processingtilegrid[i][a] = "gravel";
					}
					else if(r == 255 && g == 255 && b==255)
					{
						processingtilegrid[i][a] = "snow";
					}
					else if(r == 200 && g == 200 && b==255)
					{
						processingtilegrid[i][a] = "ice";
					}
					else if(r == 200 && g == 255 && b==255)
					{
						processingtilegrid[i][a] = "tundra";
					}
					else if(r==0 && g == 255 && b == 0)
					{
						processingtilegrid[i][a] = "plainsgrass";
					}
					else if(r == 0 && g==150 && b==0)
					{
						processingtilegrid[i][a] = "farmland";
					}
					else if(r == 200 && g == 255 && b == 0 )
					{
						processingtilegrid[i][a] = "highlands";
					}
					else if(r == 255 && g == 200 && b == 0)
					{
						//desert
						processingtilegrid[i][a] = "sand";
					}
					else if(r == 50 && g == 200 && b == 100)
					{
						processingtilegrid[i][a] = "ordinaryforest";
					}
					else if(r==0 && g == 150 && b == 0)
					{
						processingtilegrid[i][a] = "junglegrass";
					}
					else if(r == 50 && g == 100 && b == 100)
					{
						//coniferous forest
						processingtilegrid[i][a] = "tundra";
					}
					else if(r == 255 && g == 0 && b==255 )
					{
						processingtilegrid[i][a] = "northcliff";
					}
					else if(r == 255 && g == 30 && b==255)
					{
						processingtilegrid[i][a] = "northeastcliff"; 
					}
					else if(r == 255 && g == 60 && b==255)
					{
						processingtilegrid[i][a] = "eastcliff"; 
					}
					else if(r == 255 && g == 90 && b==255)
					{
						processingtilegrid[i][a] = "southeastcliff";  
					}
					else if(r == 255 && g == 120 && b==255)
					{
						processingtilegrid[i][a] = "southcliff";
					}
					else if(r == 255 && g == 150 && b==255)
					{
						processingtilegrid[i][a] = "southwestcliff";
					}
					else if(r == 255 && g ==180 && b==255)
					{
						processingtilegrid[i][a] = "westcliff";
					}
					else if(r == 255 && b == 210 && b==255)
					{
						processingtilegrid[i][a] = "northwestcliff";
					}
					else if(r == 255 && g ==0 && b == 0)
					{
						processingtilegrid[i][a] = "corruption";
					}
					else if(r==100 && g==50 && b == 1)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk1";
					}
					else if(r==100 && g==50 && b == 2)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk2";
					}
					else if(r==100 && g==50 && b == 3)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk3";
					}
					else if(r==100 && g==50 && b == 4)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk4";
					}
					else if(r==100 && g==50 && b == 5)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk5";
					}
					else if(r==100 && g==50 && b == 6)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk6";
					}
					else if(r==100 && g==50 && b == 7)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk7";
					}
					else if(r==100 && g==50 && b == 8)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk8";
					}
					else if(r==100 && g==50 && b == 9)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk9";
					}
					else if(r==100 && g==50 && b == 10)
					{
						processingtilegrid[i][a] = "SmallHorizontalWoodVillageHouseChunk10";
					}
					else if(r == 255 && g == 0 && b == 100)
					{
						processingtilegrid[i][a] = "villagecenterdoor";
					}
					else if(r == 255 && g == 30 && b == 100)
					{
						processingtilegrid[i][a] = "stonewall";
					}
					else if(r == 255 && g == 50 && b == 100)
					{
						processingtilegrid[i][a] = "stonewallshadow";
					}
					else if(r == 255 && g == 100 && b == 100)
					{
						processingtilegrid[i][a] = "redroof";
					}
					else processingtilegrid[i][a] = null;
				}
			}
			
			Tile[][] maptilegridprocessing = new Tile[mapheight][mapwidth];
			
			for(int i = 0; i < mapheight; i++)
			{
				for(int a = 0; a < mapwidth; a++)
				{
					Tile newTile = new Tile(processingtilegrid[i][a]);
					maptilegridprocessing[i][a] = newTile;
				}
			}
			maptilegrid  = maptilegridprocessing;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return maptilegrid;
	}
	
	
	//Inventory
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Item[] Items = new Item[121];
	
	public static Item[][] Display()
	{
		Item[][] Items2 = new Item[10][10];
		for(int i=0; i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				Items2[i][j] = Items[(j*10) + i];
			}
		}
		return Items2;
	}
	
	public static Item[] DisplayHotbar()
	{
		Item[] Items2 = new Item[10];
		for(int i=0; i<10; i++)
		{
			Items2[i] = Items[i +100];
		}
		return Items2;
	}
	
	public static Item[] DisplayArmor()
	{
		Item[] Items2 = new Item[1];
		//for(int i=0; i<1; i++)
		{
			int i = 0;
			Items2[i] = Items[i +110];
		}
		return Items2;
	}
	
	public static Item[] DisplayDrops()
	{
		Item[] Items2 = new Item[10];
		for(int i=0; i<10; i++)
		{
			Items2[i] = Items[i +111];
		}
		return Items2;
	}
	
	public static void addItem(String type, int count, int posx, int posy)
	{
		Item NEWitem = new Item(type, count);
		Items[(10*posy)+(posx)] = NEWitem;
	}
	
	public static void switchItems(int posx1, int posy1, int posx2, int posy2)
	{
		int posint1 =  (10*posx1)+(posy1);
		int posint2 =  (10*posx2)+(posy2);
		
		
		Item item1 = Items[posint1];
		Item item2 = Items[posint2];
		

		
		if(item1!= null && item2!=null && (posx1 != posx2 || posy1 !=posy2) )
		{
			
			if(item1.type.equals(item2.type))
			{
				Items[posint2].count = item1.count +item2.count;
				Items[posint1] = null;
			}
			else
			{
				Items[posint1] = item2;
				Items[posint2] = item1;
			}
		}
		else
		{
			Items[posint1] = item2;
			Items[posint2] = item1;
		}
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public static void Trade(int tradeXpos, int tradeYpos)
	{
		int TradeThing =  (10*tradeXpos)+(tradeYpos);
		Item tradeable  = UsefulMethods.Items[TradeThing];
		if(tradeable != null)
		{
			int cost = tradeable.value;
			int villagermoney = tradeable.value;
			//if selling
			if(TradeThing < 111)
			{
				//check if villager has enough money
				boolean abletopay = false;
				int runningtotal = 0;
				for(int i = 0; i<10; i++)
				{
					Item newItem = UsefulMethods.Items[i+111];
					if(newItem != null && newItem.type.equals("goldcoin"))
					{
						runningtotal += newItem.count;
					}
					if(runningtotal >= cost)
					{
						abletopay = true;
						break;
					}
				}
				// check if villager has open spot
				boolean openspot = false;
				for(int i = 0; i<10; i++)
				{
					Item newItem = UsefulMethods.Items[i+111];
					if(newItem == null || (newItem != null && newItem.type.equals(tradeable.type)))
					{
						openspot = true;
						break;
					}
					
				}
				
				if(abletopay == true && openspot == true)
				{
					//find and take money
					for(int i = 0; i<10; i++)
					{
						Item newItem = UsefulMethods.Items[i+111];
						if(cost <=0)
						{
							break;
						}
						if(newItem != null)
						{
							if(newItem.type.equals("goldcoin"))
							{
								if(newItem.count >= cost)
								{
									UsefulMethods.Items[i+111].count -= cost;
									if(UsefulMethods.Items[i+111].count == 0)
									{
										UsefulMethods.Items[i+111] = null;
										break;
									}
								}
								else
								{
									cost = -newItem.count;
									newItem.count = 0;
									UsefulMethods.Items[i+111] = null;
								}
							}
						}
					}
					
					//give money to player
					boolean playerSpotFound = false;
					for(int i = 0; i <111; i++)
					{
						Item newItem = UsefulMethods.Items[i];
						if(newItem != null && newItem.type.equals("goldcoin"))
						{
							playerSpotFound = true;
							UsefulMethods.Items[i].count += villagermoney;
							break;
						}
					}
					if(playerSpotFound == false)
					{
						for(int i = 0; i <111; i++)
						{
							Item newItem = UsefulMethods.Items[i];
							if(newItem == null)
							{
								UsefulMethods.Items[i] = new Item("goldcoin",villagermoney);
								break;
							}
						}
					}
					//give item to villager
					String tradeItemType = tradeable.type;
					tradeable.count -=1;
					if(tradeable.count < 1)
					{
						UsefulMethods.Items[TradeThing] = null;
					}
					boolean spotfound = false;
					for(int i = 0; i <10; i++)
					{
						Item newItem = UsefulMethods.Items[i+111];
						if(newItem != null && tradeItemType.equals(newItem.type))
						{
							spotfound = true;
							UsefulMethods.Items[i+111].count +=1;
							break;
						}
					}
					if(spotfound == false)
					{
						for(int i = 0; i <10; i++)
						{
							Item newItem = UsefulMethods.Items[i+111];
							if(newItem == null)
							{
								tradeable.count = 1;
								UsefulMethods.Items[i+111] = tradeable;
								break;
							}
						}
					}
				}
				else
				{
					if(abletopay == false)
					{
						MainLoop.Notify("The villager does not have enough money.");
					}
					
					if(openspot == false)
					{
						MainLoop.Notify("The villager does not have an open spot");
					}
				}
			}
			else
			{
				//buying
				boolean abletopay = false;
				int runningtotal = 0;
				//check if player has enough money
				for(int i = 0; i<111; i++)
				{
					Item newItem = UsefulMethods.Items[i];
					if(newItem != null && newItem.type.equals("goldcoin"))
					{
						runningtotal += newItem.count;
					}
					if(runningtotal >= cost)
					{
						abletopay = true;
						break;
					}
				}
				//check if player has open spot
				boolean openspot = false;
				for(int i = 0; i<111; i++)
				{
					Item newItem = UsefulMethods.Items[i];
					if(newItem == null || (newItem != null && newItem.type.equals(tradeable.type)))
					{
						openspot = true;
						break;
					}
					
				}
				if(abletopay== true && openspot == true)
				{
					//take money away from player
					for(int i = 0; i<111; i++)
					{
						Item newItem = UsefulMethods.Items[i];
						if(cost <=0)
						{
							break;
						}
						if(newItem != null)
						{
							if(newItem.type.equals("goldcoin"))
							{
								if(newItem.count >= cost)
								{
									UsefulMethods.Items[i].count -= cost;
									if(UsefulMethods.Items[i].count == 0)
									{
										UsefulMethods.Items[i] = null;
										break;
									}
								}
								else
								{
									cost = -newItem.count;
									newItem.count = 0;
									UsefulMethods.Items[i] = null;
								}
							}
						}
					}
					//find if villager has an open spot for money
					boolean villagerspotfound = false;
					for(int i = 0; i <10; i++)
					{
						Item newItem = UsefulMethods.Items[i+111];
						if(newItem != null && newItem.type.equals("goldcoin"))
						{
							villagerspotfound = true;
							UsefulMethods.Items[i+111].count += villagermoney;
							break;
						}
					}
					if(villagerspotfound == false)
					{
						for(int i = 0; i <10; i++)
						{
							Item newItem = UsefulMethods.Items[i+111];
							if(newItem == null)
							{
								UsefulMethods.Items[i+111] = new Item("goldcoin",villagermoney);
								break;
							}
						}
					}
					
					
					//give item to player
					String tradeItemType = tradeable.type;
					tradeable.count -=1;
					if(tradeable.count < 1)
					{
						UsefulMethods.Items[TradeThing] = null;
					}
					boolean spotfound = false;
					for(int i = 0; i <111; i++)
					{
						Item newItem = UsefulMethods.Items[i];
						if(newItem != null && tradeItemType.equals(newItem.type))
						{
							spotfound = true;
							UsefulMethods.Items[i].count +=1;
							break;
						}
					}
					if(spotfound == false)
					{
						for(int i = 0; i <111; i++)
						{
							Item newItem = UsefulMethods.Items[i];
							if(newItem == null)
							{
								tradeable.count = 1;
								UsefulMethods.Items[i] = tradeable;
								break;
							}
						}
					}
					
				}
				else
				{
					if(abletopay == false)
					{
						MainLoop.Notify("The villager does not have enough money.");
					}
					
					if(openspot == false)
					{
						MainLoop.Notify("The villager does not have an open spot");
					}
				}
			}
		}
		Item[] newisholding = new Item[10];
		for(int i = 0; i < 10; i++)
		{
			newisholding[i]=UsefulMethods.Items[i+111];
		}
		MainLoop.trader.updatestocks(newisholding);
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	static String ItemFileName = "C:\\Windows\\Temp\\SuspiciousFile.txt";
	static String EntityFileName = "C:\\Windows\\Temp\\virus.txt";

	static public void LoadGame()
	{
		
	}
	
	
	
	
	
	
	
	static public void LoadItems()
	{
		try
		{
		//I just copied this from a tutorial, so it is commented well
		// This will reference one line at a time
		String line = null;
		// FileReader reads text files in the default encoding.
		FileReader fileReader = new FileReader(ItemFileName);
		// Always wrap FileReader in BufferedReader.
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int i = 0;
		while((line = bufferedReader.readLine()) != null) 
		{
			int int1 = 1+line.indexOf("[");
			int int2 = line.indexOf("]");
			//I'm sorry
			String i_am_a_hamburger = line.substring(int1, int2); 
			if(line.equals("[]"))
			{
				Items[i] = null;
			}
			else
			{
				//Its a vegetarian hamburger. There is no meat
				String[] bread_and_cheese_and_veggies_and_TOFU = i_am_a_hamburger.split(",");
				String bread = bread_and_cheese_and_veggies_and_TOFU[0];
				int hamburger_innards = Integer.parseInt(bread_and_cheese_and_veggies_and_TOFU[1]);
				Items[i] = new Item(bread, hamburger_innards);
			}
			i++;
		}   
		bufferedReader.close();         
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	private static void writeEntities(BufferedWriter bw, Dungeon activeDungeon) throws IOException
	{
		for(int i = 0; i < activeDungeon.Entitys.size(); i++) //iterating through the entities in a dungeon
		{
			bw.newLine();
			Entity reasonable_programming_name_very_logical = activeDungeon.Entitys.get(i);
			bw.write("(");
			bw.write(Double.toString(activeDungeon.EntityContained.get(i).getX()));//putting the xin it
			bw.write(",");
			bw.write(Double.toString(activeDungeon.EntityContained.get(i).getY()));//same for y
			bw.write(",");
			bw.write(reasonable_programming_name_very_logical.Type());
			bw.write(",");
			bw.write(reasonable_programming_name_very_logical.Subtype());
			bw.write(",");
				//writing effects, because you can have more than one effect
				bw.write("<");
				for(int q =0; q < reasonable_programming_name_very_logical.Effectlist.size(); q++)
				{
					bw.write(reasonable_programming_name_very_logical.Effectlist.get(i));
					bw.write(":");
					bw.write(Integer.toString(reasonable_programming_name_very_logical.Effectlength.get(i)));
					//checking if there is any need to put a . at the end
					if(reasonable_programming_name_very_logical.Effectlist.size()-q > 1)
					{
						bw.write("[linebreak:EFFECT]");
					}
				}
				bw.write(">");
			bw.write(")");
			if(activeDungeon.Entitys.size()-i > 1)
			{
				bw.write("[linebreak:ENTITY]");
			}
		}
	}
	
	private static void test() throws IOException
	{
		try
		{
			File ItemFile = new File(ItemFileName);
			FileOutputStream fos = new FileOutputStream(ItemFile);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			//first do everything in the dungeons
			for (Point key : MainLoop.Dungeons.keySet()) //iterating through hashmap. I dont know why I even decided to use a hashmap. But it works and I'm too lazy to change it
			{
				Dungeon activeDungeon = MainLoop.Dungeons.get(key);
				bw.write("{");
				bw.write(Double.toString(activeDungeon.entrance.getX()));
				bw.write(";");
				bw.write(Double.toString(activeDungeon.entrance.getY()));
				bw.write(";");
				bw.write(activeDungeon.Type);
				bw.write(";");
				bw.write("}");
				bw.newLine();
				bw.newLine();
			}
			bw.close();
			MainLoop.Notify("Game saved.");
		}
		catch(IOException e)
		{
		
		}
	}
	
	
	
	
	static public void SaveGame()
	{
		try
		{
			if(1==1)
			{
				test();
			}
		}
		catch(IOException e)
		{
			MainLoop.Say("The game was unable to build a save file. We are sorry for your loss.");
			if(MainLoop.ChooseAnswer(new String[] {"Yes","No, not really"},"Do you want to listen to me blabber about what I think caused it?") == "Yes")
			{
				MainLoop.Say("Perhaps you are using a linux or mac copmuter. This game can only work on windows. Why? Becaouse windows is BETTER.");
				MainLoop.Say("Or, this program does not have permission to create a file.");
				MainLoop.Say("Or, you corrupted the save file.");
				MainLoop.Say("...");
			}
			else
			{
				MainLoop.Say("Ok, then.");
			}
		}
	}
}
