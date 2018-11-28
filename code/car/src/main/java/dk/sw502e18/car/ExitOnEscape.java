package dk.sw502e18.car;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class ExitOnEscape {

    public static void enable() {
        Button.ESCAPE.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(Key key) {

            }

            @Override
            public void keyReleased(Key key) {
                System.exit(0);
            }
        });
    }
}
