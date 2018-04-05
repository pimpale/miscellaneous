package engine;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileChunk extends Chunk {

	File save;


	public FileChunk(String path) throws IOException
	{
		this(new File(path));
	}

	public FileChunk(File f) throws IOException
	{
		this.save = f;
		read();
	}


	void read() throws IOException {
        read(save);
    }

	void read(File f) throws IOException {
		RandomAccessFile r = new RandomAccessFile(f, "r");
		for (int z = 0; z < Chunk.CHUNKSIZE; z++) {
			for (int y = 0; y < Chunk.CHUNKSIZE; y++) {
				for (int x = 0; x < Chunk.CHUNKSIZE; x++) {
					this.map[x][y][z] = Block.toBlock(r.readShort());
				}
			}
		}
		r.close();
	}

	void write() throws IOException {
        write(save);
    }

	void write(File f) throws IOException {
		RandomAccessFile r = new RandomAccessFile(f,"w");
		for(int z = 0; z < Chunk.CHUNKSIZE; z++) {
			for(int y = 0; y < Chunk.CHUNKSIZE; y++) {
				for(int x = 0; x < Chunk.CHUNKSIZE; x++) {
					r.writeShort(Block.toShort(map[x][y][z]));
				}
			}
		}
		r.close();
	}
}
