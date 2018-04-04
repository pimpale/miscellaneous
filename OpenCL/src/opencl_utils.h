/*
 * opencl_utils.h
 *
 *  Created on: Oct 24, 2017
 *      Author: govind
 */

#ifndef OPENCL_UTILS_H_
#define OPENCL_UTILS_H_

#include <CL/cl.h>

cl_int error;
cl_platform_id platform;
cl_device_id device;
cl_uint platforms, devices;

//set up the context, error, and other stuff
void init_opencl()
{

}

void get_context()
{
	// Get platform and device information
	cl_int ret = clGetPlatformIDs(1, &platform, &platforms);
	ret = clGetDeviceIDs( platform, CL_DEVICE_TYPE_DEFAULT, 1,
			&device, &devices);
}

#endif /* OPENCL_UTILS_H_ */
