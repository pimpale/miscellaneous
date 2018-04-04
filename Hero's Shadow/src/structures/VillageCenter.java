package structures;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import gamepackage.*;


public class VillageCenter 
{
	
	public static Point MovePlayer()
	{
		Point hi = new Point(7,10);
		return hi;
	}
	
	public static Tile[][] VillageHouseMap(Dungeon instance)
	{
		int[][] hi = 
			{
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
				{2,1,1,1,1,1,1,1,1,1,1,1,1,1,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
				{2,2,2,2,2,2,0,0,2,2,2,2,2,2,2}
			};
			
			Tile[][] processingtilegrid = new Tile[hi.length][hi[0].length];
			Tile woodfloorQWERTY = new Tile("woodfloor");
			Tile woodwallQWERTY = new Tile("woodwall");
			Tile woodtopQWERTY = new Tile("woodtop");
			Tile TUNDRA = new Tile("tundra");
			for(int i = 0; i < hi.length; i++)
			{
				for(int a = 0; a < hi[0].length; a++)
				{
					if(hi[i][a] == 0) processingtilegrid[i][a] = woodfloorQWERTY;
					if(hi[i][a] == 1) processingtilegrid[i][a] = woodwallQWERTY;
					if(hi[i][a] == 2) processingtilegrid[i][a] = woodtopQWERTY;
					if(hi[i][a] == 123) processingtilegrid[i][a] = TUNDRA;
				}
			}
			
			return processingtilegrid;
	}

	public static ArrayList<Point> AddEntityPoints() 
	{
		ArrayList<Point> bob = new ArrayList<Point>();
		bob.add(new Point(64*7,64*5));
		bob.add(new Point(64*2,64*3));
		bob.add(new Point(64*13,64*3));
		bob.add(new Point(64*2,64*8));
		bob.add(new Point(64*13,64*8));
		return bob;
	}
	
	public static ArrayList<Entity> GetEntitys(Dungeon instance)
	{
		ArrayList<Entity> bob = new ArrayList<Entity>();
		Entity eldervillager = MainLoop.CreateEntity(0,0,"villager", "elder", "", instance);
		bob.add(eldervillager);
		Entity qwertybob = MainLoop.CreateEntity(0,0,"villager", "", "", instance);
		Entity qwertybob1 = MainLoop.CreateEntity(0,0,"villager", "", "", instance);
		Entity qwertybob2 = MainLoop.CreateEntity(0,0,"villager", "", "", instance);
		Entity qwertybob3 = MainLoop.CreateEntity(0,0,"villager", "", "", instance);
		bob.add(qwertybob);
		bob.add(qwertybob1);
		bob.add(qwertybob2);
		bob.add(qwertybob3);
		return bob;
	}
	
}
