package gamepackage;

import java.awt.Point;

import java.util.ArrayList;

import structures.VillageCenter;
import structures.Villagerhouse;

public class Dungeon 
{
	public ArrayList<Point> EntityContained = new ArrayList<Point>();
	public ArrayList<Entity> Entitys = new ArrayList<Entity>();
	//I know I misspelled this but there was already "Entities"
	
	public boolean hasthisdungeonbeenentered = false;
	public String Type;
	
	private Tile[][] map = new Tile[0][0];
	
	Point entrance = null;
	public Dungeon(int entranceX, int entranceY, String type)
	{
		Point qwertyuiopasdfgh = new Point(entranceX, entranceY);
		Type = type;
		entrance = qwertyuiopasdfgh;
		if(type == "SmallHorizontalWoodVillageHouse")
		{
			map = Villagerhouse.VillageHouseMap(this);
			EntityContained = Villagerhouse.AddEntityPoints();
			Entitys = Villagerhouse.GetEntitys(this);
		}
		else if(type == "villagecenter")
		{
			map = VillageCenter.VillageHouseMap(this);
			EntityContained = VillageCenter.AddEntityPoints();
			Entitys = VillageCenter.GetEntitys(this);
		}
	}
	
	
	public Point Entrance()
	{
		return entrance;
	}
	
	
	
	public Point Moveplayer()
	{
		return Villagerhouse.MovePlayer();
	}
	
	public Tile[][] Map()
	{
		return map;
	}
	
	
}
