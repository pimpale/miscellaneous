package gamepackage;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Projectile 
{
	int lifeleft = 10;
	Image Sprite =  null;
	double X = 0;
	double Y = 0;
	double Xmomentum = 0;
	double Ymomentum = 0;
	String Type;
	double Damage;
	String Effects = "";
	Entity Caster = null;
	public Projectile(double x, double y, String type, String effects, double damage, double speed, double degrees, int lifetime, Entity caster)
	{
		lifeleft = lifetime;
		X = x;
		Y = y;
		Type = type;
		Xmomentum = Math.cos(degrees) * speed;
		Ymomentum = Math.sin(degrees) * speed;
		Damage = damage;
		Effects = effects;
		Caster = caster;
		if(type == "melee")
		{
			MeleeWeapon(effects, damage, degrees, lifeleft);
		}
	}
	
	public Image Sprite()
	{
		return Sprite;
	}
	
	public double X()
	{
		return X;
	}
	
	public double Y()
	{
		return Y;
	}
	
	public String Type()
	{
		return Type;
	}
	
	public String Effects()
	{
		return Effects;
	}
	
	public double Damage()
	{
		return Damage;
	}
	
	private void MeleeWeapon(String effects, double damage, double degrees, int lifeleft)
	{
		Image meleestrikeup = Toolkit.getDefaultToolkit().getImage("projectiles/meleestrike/range1 melee strike up.png");
		Image meleestrikedown = Toolkit.getDefaultToolkit().getImage("projectiles/meleestrike/range1 melee strike down.png");
		Image meleestrikeleft = Toolkit.getDefaultToolkit().getImage("projectiles/meleestrike/range1 melee strike left.png");
		Image meleestrikeright = Toolkit.getDefaultToolkit().getImage("projectiles/meleestrike/range1 melee strike right.png");
		
		double mousex = (MouseInfo.getPointerInfo().getLocation().getX() -8);
		double mousey = (MouseInfo.getPointerInfo().getLocation().getY() -24);
		double deg = Math.atan2((350 -mousey), (350-mousex));
		deg = deg * 180/Math.PI;
		if(deg >=-45 && deg <= 45)
		{
			Sprite=meleestrikeleft;
			X -= 86;
			Y -= 86;
		}
		else if(deg >=45 && deg<= 135)
		{
			X -= 88;
			Y -= 86;
			Sprite=meleestrikeup;
			
		}
		else if(deg <=-45 && deg>= -135)
		{
			X -= 88;
			Y -= 0;
			Sprite=meleestrikedown;
		}
		else
		{
			Sprite = meleestrikeright;
			Y -= 86;
		}
	}
	
	public void update()
	{
		X+=Xmomentum;
		Y+=Ymomentum;
		lifeleft  -=1;
	}
}
