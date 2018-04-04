package entities;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import gamepackage.Entity;
import gamepackage.Item;
import gamepackage.MainLoop;

public class Villager 
{
	double X;
	double Y;
	Entity instance = null;
	public double X()
	{
		return X;
	}
	
	public double Y()
	{
		return Y;
	}
	
	String Subtype = "";
	Image average_back = Toolkit.getDefaultToolkit().getImage("entities/villager average/average village guy back.png");
	Image average_front = Toolkit.getDefaultToolkit().getImage("entities/villager average/average village guy front.png");
	Image average_right = Toolkit.getDefaultToolkit().getImage("entities/villager average/average village guy right.png");
	Image average_left = Toolkit.getDefaultToolkit().getImage("entities/villager average/average village guy left.png");

	Image elder_back = Toolkit.getDefaultToolkit().getImage("entities/villager elder/elder village guy back.png");
	Image elder_front = Toolkit.getDefaultToolkit().getImage("entities/villager elder/elder village guy front.png");
	Image elder_right = Toolkit.getDefaultToolkit().getImage("entities/villager elder/elder village guy right.png");
	Image elder_left = Toolkit.getDefaultToolkit().getImage("entities/villager elder/elder village guy left.png");

	
	public Villager(Entity Instance, String subtype)
	{
		Subtype = subtype;
		instance = Instance;
	}
	
	
	
	public void update()
	{
		X = instance.Y;
		Y = instance.X;
		Image img = null;
		double deg = Math.atan2((Y-350), (X-350));
		deg = deg * 180/Math.PI;
		
		if(Subtype == "house" || Subtype == "")
		{
			if(deg >=-45 && deg <= 45)
			{
				img=average_left;
			}
			else if(deg >=45 && deg<= 135)
			{
				img=average_back;
			}
			else if(deg <=-45 && deg>= -135)
			{
				img=average_front;
			}
			else
			{
				img = average_right;
			}
			
		}
		else if(Subtype == "elder")
		{
			if(deg >=-45 && deg <= 45)
			{
				img=elder_left;
			}
			else if(deg >=45 && deg<= 135)
			{
				img=elder_back;
			}
			else if(deg <=-45 && deg>= -135)
			{
				img=elder_front;
			}
			else
			{
				img = elder_right;
			}
		}
		instance.SetSprite(img);
	}
	
	
	public void updatestocks(Item[] itemlist)
	{
		instance.isHolding = itemlist;
	}
	
	
	public void onclick()
	{
		if(Subtype == "elder")
		{
			eldervillagerdiscuss();
		}
		else
		{
			housevillagerdiscuss();
		}
	}
	
	
	
	
	
	
	
	public void housevillagerdiscuss()
	{
		String[] possibleanswers = new String[] {"I would like to purchase/sell something"};
		String thing = MainLoop.ChooseAnswer(possibleanswers, "What can I help you with?");
		if(thing!=null)
		{
			if(thing == "I would like to purchase/sell something")
			{
				
				Item[] tradeable = instance.isHolding; 
				MainLoop.Trade(tradeable,this);
			}
		}
	}
	
	public void eldervillagerdiscuss()
	{
		String[] possibleanswers = new String[] {
		"<html><p style=\"font-family:courier;\">I am a Storyteller. I am a guardian of the spoken word.<br>"
		+ "Within my memory are stories from the beginning of time<br>"
		+ "to the present day. It is my duty to remember what peop<br>"
		+ "le have forgotten and to speak what I have learned so t<br>"
		+ "hat others may know it.",
		"<html><p style=\"font-family:georgia bold;\">A Hero.",
		 "A simple man trying to seek his fortune."};
		String thing = MainLoop.ChooseAnswer(possibleanswers, "village elder: Who are you?");
		if(thing!=null)
		{
			if(thing == "<html><p style=\"font-family:courier;\">I am a Storyteller. I am a guardian of the spoken word.<br>"
		    + "Within my memory are stories from the beginning of time<br>"
		    + "to the present day. It is my duty to remember what peop<br>"
		    + "le have forgotten and to speak what I have learned so t<br>"
		    + "hat others may know it.")
			{
				String[] thingsyoucouldsay = {/*"A hero with a capital \"H\" or a lowercase \"h\"?",*/ "Maybe I am one."};
				String wizardanswer = MainLoop.ChooseAnswer(thingsyoucouldsay, "village elder: Nice speech. I like it. But we don't need a storyteller. We need a hero");	
				if(wizardanswer!=null)
				{
					/*if(wizardanswer == "A hero with a capital \"H\" or a lowercase \"h\"?")
					{
		
						String[] thingsyoucouldsay2 = {"<html>Yes. A hero with a lowercase h is a person who does the right<br>thing. A Hero with an uppercase H is a legend, an avatar of what <br>they stand for. They are transformed through belief into <br>something more than just human: a Hero."};
						MainLoop.ChooseAnswer(thingsyoucouldsay2, "village elder: Does it really matter?");
						String[] thingsyoucouldsay3 = {"Why do you need one anyway?"};
						MainLoop.ChooseAnswer(thingsyoucouldsay3, "village elder: That's good for you.");
					}
					else*/
					if(wizardanswer == "Maybe I am one."){
						String[] thingsyoucouldsay2 = {"Why not? Why do you need a hero anyway?"};
						MainLoop.ChooseAnswer(thingsyoucouldsay2, "village elder: Somehow, I don't think so.");
					}
					String[] thingsyoucouldsay2 = {"No.", "A few.", "A whole bunch."};
					String answers = MainLoop.ChooseAnswer(thingsyoucouldsay2, "village elder: So you saw those spiders on the way here, right?");
					if(answers == "No.")
					{
						MainLoop.Say("village elder: You've been luckier than our village then.");
						MainLoop.Say("There are giant spiders infesting our village.");
						MainLoop.Say("They attack our crops, and eat the children");
						MainLoop.Say("My people are not warriors by nature. Our villagers at arms were the first to be eaten.");
					}
					else if(answers == "A few." || answers ==  "A whole bunch")
					{
						
					}
				}
			
			}
				
		}
	}
}
