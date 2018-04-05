package engine;

import utils.Utils;

import java.awt.image.BufferedImage;

public class Block {

	public short id;

	public Block(short id)
	{
		this.id = id;
	}

	public BufferedImage getImage()
	{
		return Utils.getBlockImage(id);
	}

	public static short toShort(Block b)
	{
		return b.id;
	}

	public static Block toBlock(short s)
	{
		return new Block(s);
	}

}
