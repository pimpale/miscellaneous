package cyberpunk;

import dbg._Utils;
import engine.Dimension;
import engine.ScreenView;
import launcher.Launcher;

/**
 * This is the main class and initializer. It has one use, and that use is to initialize the prok
 */
public class Init {

	/**
	 * launches the launcher in a new thread.
	 * Launcher should be able to create a new world, and once finished, return path to it
	 *
	 * Should return a path to World.
	 */
	static String initLauncher() {
		Launcher l = new Launcher();
		l.run();
		return "";//TODO
	}

	public static void main(String[] args) {
		_Utils.initManifestation();
	}
}
