package dk.sw502e18.car;

import org.opencv.core.Core;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // exits whenever ESCAPE is pressed (important!)
        ExitOnEscape.enable();

        FifoPipe p = new FifoPipe("/home/lejos/pipes/speedlimit");
        p.addListener(new SpeedSignPrinter());
        p.read();
    }
}

