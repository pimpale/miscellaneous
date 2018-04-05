package dbg;

import engine.Dimension;
import engine.ScreenView;

public class _Utils {


    public static void initManifestation() {

        System.out.println("Executing InitManifestation");
        _Manifestation m = new _Manifestation();

        ScreenView sc = new ScreenView(new Dimension(null), m.location);

        System.out.println("Screenview instantiated");

        while (true) {
            sc.draw();
            try {
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}