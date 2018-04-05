package engine;

import utils.Point3D;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Dimension {

    private HashMap<Point3D, Chunk> chunkHashMap = new HashMap<Point3D, Chunk>();
    private String path;

    public Dimension(String savePath)
	{
		path = savePath;
	}

	public String getPath()
    {
        return path;
    }

	public void loadChunk(Point3D chunkLocation, Chunk chunk)
    {
        chunkHashMap.put(chunkLocation, chunk);
    }

	public void loadChunkFromSave(Point3D chunkLocation)
    {
        try {
            chunkHashMap.put(chunkLocation, fetchChunkFromSave(chunkLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param chunkLocation The location of the chunk
     * @return If the file representing the chunk exists in the save
     */
    public boolean chunkExistsInSave(Point3D chunkLocation)
    {
        File ch = new File(getChunkPath(chunkLocation));
        return ch.exists();
    }

    public void unloadChunk(Point3D chunkLocation)
    {
        Chunk ch = chunkHashMap.get(chunkLocation);
        if(ch instanceof FileChunk && ch.modified)
        {
            try {
                ((FileChunk)ch).write();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        chunkHashMap.remove(chunkLocation);
    }

    public Block get(long globalX, long globalY, long globalZ)
    {
        return get(new Point3D(globalX,globalY,globalZ));
    }

    public Block get(Point3D globalLocation)
    {
        Chunk ch = chunkHashMap.get(getChunkLocation(globalLocation));
        return ch.get(getLocalLocation(globalLocation));
    }

    /**
     *
     * @param globalX
     * @param globalY
     * @param globalZ
     * @param b
     */
    public void set(long globalX, long globalY, long globalZ, Block b)
    {
        set(new Point3D(globalX, globalY, globalZ),b);
    }

    /**
     * Sets a block at the location to b
     * @param globalLocation the global location of the block
     * @param b the block to set at that location
     */
    public void set(Point3D globalLocation, Block b)
    {
        Chunk ch = chunkHashMap.get(getChunkLocation(globalLocation));
        ch.set(getLocalLocation(globalLocation), b);
    }

    static Point3D getChunkLocation(Point3D globalLocation) {
        long chunkX = globalLocation.x/Chunk.CHUNKSIZE;
        long chunkY = globalLocation.y/Chunk.CHUNKSIZE;
        long chunkZ = globalLocation.z/Chunk.CHUNKSIZE;
        return new Point3D(chunkX,chunkY,chunkZ);
    }

    static Point3D getLocalLocation(Point3D globalLocation) {
        long localX = globalLocation.x%Chunk.CHUNKSIZE;
        long localY = globalLocation.y%Chunk.CHUNKSIZE;
        long localZ = globalLocation.z%Chunk.CHUNKSIZE;
        return new Point3D(localX,localY,localZ);
    }

    /**
     * @param chunkLocation the location of the chunk
     * @return the chunk at the location
     * @throws IOException if the file does not exist
     */
    Chunk fetchChunkFromSave(Point3D chunkLocation) throws IOException {
        return new FileChunk(getChunkPath(chunkLocation));
    }

    /**
     * @param chunkLocation the location of the chunk
     * @return the file path of the chunk save
     */
    String getChunkPath(Point3D chunkLocation)
    {
      return path
              + Long.toHexString(chunkLocation.x)+"-"
              + Long.toHexString(chunkLocation.y)+"-"
              + Long.toHexString(chunkLocation.z);
    }


    //chunk modification methods

    /**
     * Fills the area specified with blocks
     *
     * @param b  the block to fill with
     * @param x1 the global location of the starting x location
     * @param y1 the global location of the starting y location
     * @param z1 the global location of the starting z position
     * @param x2 the global location
     * @param y2
     * @param z2
     */
    public void __fillRect(Block b, int x1, int y1, int z1, int x2, int y2, int z2)
    {

    }
}
