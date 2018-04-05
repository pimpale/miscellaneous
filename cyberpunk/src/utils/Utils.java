package utils;

import engine.Chunk;
import engine.Block;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Utils {

	public static BufferedImage getImage(String path)
	{
		return getImage(new File(path));
	}
	
	public static BufferedImage getImage(File file)
	{
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static final BufferedImage airpng       = getImage("Templates/Default/Blocks/Images/air.png");
	public static final BufferedImage firmamentpng = getImage("Templates/Default/Blocks/Images/firmame.png");
	public static final BufferedImage groundpng    = getImage("Templates/Default/Blocks/Images/ground.png");
	public static final BufferedImage wallpng      = getImage("Templates/Default/Blocks/Images/wall.png");


	public static BufferedImage getBlockImage(Block b)
	{
		return getBlockImage(b.id);
	}

	//TODO place this into the filesystem and make it extensible
	public static BufferedImage getBlockImage(short id)
	{
		BufferedImage bimg = null;

		if(id == 0)
		{
			bimg =  airpng;
		}
		else if(id == 1)
		{
			bimg = firmamentpng;
		}

		return bimg;
	}


	public static Chunk parseAsciiChunk(File f) throws IOException
	{
		Scanner scan = new Scanner(f);
		//The first line is the dimensions of the thing/
		int xSize = scan.nextInt();//width
		scan.next();//skip space
		int ySize = scan.nextInt();
		scan.next();//skip space
		int zSize = scan.nextInt();

		//move to next line
		scan.nextLine();

		Chunk ch = new Chunk();
		for(int z = 0; z < zSize; z++) {
			//Move to nextline to distinguish between z levels
			scan.nextLine();
			for(int y = 0; y < ySize; y++) {
				String str = scan.nextLine();
				char[] charray = str.toCharArray();
				for (int x = 0; x < xSize; x++) {
					char c = charray[x];
					short sh = 0;
					if(c == '#') {
						sh = 1;
					}
					ch.set(x,y,z,new Block(sh));
				}
			}
		}
		return ch;
	}
}
