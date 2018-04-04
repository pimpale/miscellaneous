package gamepackage;

import java.awt.Image;

public class Tile 
{
	Image Sprite = null;
	boolean collidable = false;
	String dungeonto = null;
	String SpecialEffects = "";
	
	public Tile(String type)
	{

		if(type ==  "deepwater")
		{
			Sprite = UsefulMethods.deepwater;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "water")
		{
			Sprite = UsefulMethods.water;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "sand")
		{
			Sprite = UsefulMethods.sand;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "gravel")
		{
			Sprite = UsefulMethods.gravel;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "snow")
		{
			Sprite = UsefulMethods.snow;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "ice")
		{
			Sprite = UsefulMethods.ice;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "tundra")
		{
			Sprite = UsefulMethods.tundra;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "plainsgrass")
		{
			Sprite = UsefulMethods.plainsgrass;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "farmland")
		{
			Sprite = UsefulMethods.farmland;
			collidable = false;
			dungeonto = null;
		}
		else if(type ==  "sand")//desert
		{
			Sprite = UsefulMethods.sand;
			collidable = false;
			dungeonto = null;
		}
		else if(type ==  "tundra")//coniferous forest
		{
			Sprite = UsefulMethods.tundra;
			collidable = false;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk1")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk1;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk2")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk2;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk3")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk3;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk4")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk4;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk5")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk5;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk6")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk6;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk7")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk7;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk8")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk8;
			collidable = false;
			dungeonto = "SmallHorizontalWoodVillageHouse";
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk9")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk9;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "SmallHorizontalWoodVillageHouseChunk10")
		{
			Sprite = UsefulMethods.SmallHorizontalWoodVillageHouseChunk10;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "villagecenterdoor")
		{
			Sprite = UsefulMethods.villagecenterdoor;
			collidable = true;
			dungeonto = "villagecenter";
		}
		
		else if(type ==  "stonewall")
		{
			Sprite = UsefulMethods.stonewall;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "stonewallshadow")
		{
			Sprite = UsefulMethods.stonewallshadow;
			collidable = true;
			dungeonto = null;
		}
		
		else if(type ==  "redroof")
		{
			Sprite = UsefulMethods.redroof;
			collidable = true;
			dungeonto = null;
		}
		else if(type == "woodfloor")
		{
			Sprite = UsefulMethods.woodfloor;
			collidable = false;
			dungeonto = null;
		}
		else if(type == "woodwall")
		{
			Sprite = UsefulMethods.woodwall;
			collidable = true;
			dungeonto = null;
		}
		else if(type == "woodtop")
		{
			Sprite = UsefulMethods.woodtop;
			collidable = true;
			dungeonto = null;
		}
		else
		{
			Sprite = null;
			collidable = false;
			dungeonto = "main";
		}
	}
		
		
	public String DungeonTo()
	{
		return dungeonto;
	}
	
	
	public boolean Collidable()
	{
		return collidable;
	}
	
	public Image Sprite()
	{
		return Sprite;
	}
	
	public Dungeon DungeonsContained()
	{
		return null;
	}
}
