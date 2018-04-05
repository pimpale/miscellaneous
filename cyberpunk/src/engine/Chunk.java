package engine;

import utils.Point3D;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;

public class Chunk {
	public static final byte CHUNKSIZE = 64;

	Block[][][] map = new Block[CHUNKSIZE][CHUNKSIZE][CHUNKSIZE];
	boolean modified = false;

	public Chunk(Chunk ch)
	{
		this(ch.map);
	}

	public Chunk(Block[][][] map) {
		for (int z = 0; z < CHUNKSIZE; z++) {
			for (int y = 0; y < CHUNKSIZE; y++) {
				for (int x = 0; x < CHUNKSIZE; x++) {
					this.map[x][y][z] = new Block(map[x][y][z].id);
				}
			}
		}
	}

	public Chunk(short[][][] map)
	{
		for (int z = 0; z < CHUNKSIZE; z++) {
			for (int y = 0; y < CHUNKSIZE; y++) {
				for (int x = 0; x < CHUNKSIZE; x++) {
					this.map[x][y][z] = new Block(map[x][y][z]);
				}
			}
		}
	}

	public Chunk()
	{

	}

	public Block get(Point3D localLocation)
	{
		return get((int)localLocation.x, (int)localLocation.y, (int)localLocation.z);
	}

	public Block get(int x, int y, int z)
	{
		return map[x][y][z];
	}

	public void set(Point3D localLocation, Block b) {
		set((int)localLocation.x,(int)localLocation.y,(int)localLocation.z,b);
	}

	public void set(int x, int y, int z, Block b) {
		map[x][y][z] = b;
		modified = true;
	}

}
