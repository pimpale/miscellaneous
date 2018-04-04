// include the X library headers
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xos.h>

// include some silly stuff
#include <stdio.h>
#include <stdlib.h>

#include "opencl_utils.h"
#include "ray_casting.h"
#include "screen_functions.h"


int main() {
	init_x();
	while(1) {
		handle_user_input();
	}
}







