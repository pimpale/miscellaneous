package structures;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import gamepackage.*;


public class Villagerhouse 
{
	
	public static Point MovePlayer()
	{
		Point hi = new Point(4,7);
		return hi;
	}
	
	public static Tile[][] VillageHouseMap(Dungeon instance)
	{
		//MainLoop.CreateEntity(2,20, 5, 2,20, "villager", "", "", instance);
		int[][] hi = 
			{
				{2,2,2,2,2,2,2,2,2,2,2,2},
				{2,1,1,1,1,1,1,1,1,1,1,2},
				{2,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,2},
				{2,0,0,0,0,0,0,0,0,0,0,2},
				{2,2,2,2,0,2,2,2,2,2,2,2}
			};
			
		/*	
		int[][] hi = 
			{
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
				{2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2},
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
				{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
			};
			*/
		
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
		Point Thing = new Point(5,3);
		bob.add(Thing);
		return bob;
	}
	
	public static ArrayList<Entity> GetEntitys(Dungeon instance)
	{
		ArrayList<Entity> bob = new ArrayList<Entity>();
		Entity qwertybob = MainLoop.CreateEntity(0,0,"villager", "", "", instance);
		bob.add(qwertybob);
		return bob;
	}
	
}
