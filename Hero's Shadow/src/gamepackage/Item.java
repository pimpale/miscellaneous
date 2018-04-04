 package gamepackage;

import java.awt.Image;
import java.awt.Toolkit;

public class Item 
{
	static Image BasicStaffImage = Toolkit.getDefaultToolkit().getImage("Items/basic staff icon.png");
	static Image GoldCoin = Toolkit.getDefaultToolkit().getImage("Items/goldcoin.png");
	static Image BronzeShortSword = Toolkit.getDefaultToolkit().getImage("Items/bronze short sword.png");
	
	double attack;
	double defense;
	String effects;
	int projectilelife;
	String weapontype;
	String type;
	int cooldown;
	int value;
	String description;
	int count = 0;
	Image sprite;
	double speed = 0;
	
	public Item(String ItemType, int number)
	{
		Object[] things;
		if(ItemType.equals("goldcoin"))
		{
			things = GetStatsGold();
		}
		else if(ItemType.equals("basicstaff"))
		{
			things = GetStatsBasicStaff();
		}
		else if(ItemType.equals("bronzeshortsword"))
		{
			things = GetStatsBronzeShortSword();
		}
		else
		{
			things = GetStatsNull();
		}
		count = number;
		type = ItemType;
		attack = (double) things[0];
		defense = (double) things[1];
		effects = (String) things[2];
		projectilelife = (int) things[3];
		weapontype = (String) things[4];
		cooldown = (int) things[5];
		speed = (double) things[6];
		value = (int) things[7];
		description = (String) things[8];
		sprite = (Image) things[9];
		
	}
	
	
	
	
	private static Object[] GetWeaponStatsNull()
	{
		double attack = 1;
		double defense = 0;
		String effects = "knockback:2";
		int projectilelife  = 10;
		String weapontype = "melee"; 
		int cooldown = 50;
		double speed = 0;
		Object[]  returnthis = new Object [] {attack, defense, effects, projectilelife,weapontype, cooldown, speed};
		return returnthis;
	}
	
	
	
	
	
	private static Object[] GetStatsBasicStaff()
	{
		
		double attack = 1;
		double defense = 0;
		String effects = "knockback:4";
		int projectilelife  = 30;
		String weapontype = "melee"; 
		
		int cooldown = 80;
		int value = 1; 
		double speed = 0;
		String description = "A basic wooden staff. Good for hitting monsters with. Does 1 damage.";
		Image img = BasicStaffImage;
		Object[]  returnthis = new Object [] {attack, defense, effects, projectilelife, weapontype, cooldown, speed, value, description,img};
		return returnthis;
	}
	
	
	private static Object[] GetStatsBronzeShortSword()
	{
		
		double attack = 2;
		double defense = 0;
		String effects = "knockback:3";
		int projectilelife  = 20;
		String weapontype = "melee"; 
		
		int cooldown = 80;
		int value = 5; 
		double speed = 0;
		String description = "A short cheaply mass produced bronze sword. It does 2 damage.";
		Image img = BronzeShortSword;
		Object[]  returnthis = new Object [] {attack, defense, effects, projectilelife, weapontype, cooldown, speed, value, description,img};
		return returnthis;
	}
	
	
	public static Object[] GetStatsNull()
	{
		Object thing[] = GetWeaponStatsNull();
		
		
		int value = 0; 
		String description = "";

		Object[]  returnthis = new Object []{thing[0], thing[1], thing[2], thing[3],thing[4], thing[5], thing[6], value, description,null};
		return returnthis;
	}
	
	private static Object[] GetStatsGold()
	{
		
		Object thing[] = GetWeaponStatsNull();
		
		int value = 1; 
		String description = "A gold coin: Worth exactly one gold coin!";
		Image img = GoldCoin;
		Object[]  returnthis = new Object [] {thing[0], thing[1], thing[2], thing[3],thing[4], thing[5], thing[6], value, description,img};
		return returnthis;
	}
	
}
