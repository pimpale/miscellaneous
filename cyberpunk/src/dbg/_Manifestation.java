package dbg;

import utils.Point3D;

/**
 * A spectator with no attributes that flies around the world
 */
public class _Manifestation {
    Point3D location;

    /**
     * spawns a manifestation at the spawnpoint
     */
    public _Manifestation()
    {
        location = new Point3D(0,0,0);
    }

    public _Manifestation(int x, int y, int z)
    {
        location = new Point3D(x,y,z);
    }
}
