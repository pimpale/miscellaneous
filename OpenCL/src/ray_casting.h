/*
 * RayCasting.h
 *
 *  Created on: Oct 24, 2017
 *      Author: govind
 */

#ifndef RAYCASTING_H_
#define RAYCASTING_H_

//a point with 3 dimensions
struct triplet {
	double x;
	double y;
	double z;
};


//a ray struct with both a current location and a direction vector
struct ray {
	struct triplet loc;
	struct triplet vec;

};
//creates new point
struct triplet new_triplet(int x, int y, int z)
{
	struct triplet p;
	p.x = x;
	p.y = y;
	p.z = z;
	return p;
}


//creates new ray
struct ray new_ray(struct triplet vec, struct triplet loc)
{
	struct ray r;
	r.vec = vec;
	r.loc = loc;
	return r;
}

#endif /* RAYCASTING_H_ */
