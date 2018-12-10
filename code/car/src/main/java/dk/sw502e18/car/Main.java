package dk.sw502e18.car;

import dk.sw502e18.car.carClient.SocketCarClient;
import dk.sw502e18.car.carClient.listener.SpeedSignPrinter;
import dk.sw502e18.car.utils.ExitOnEscape;
import lejos.hardware.lcd.LCD;
import org.opencv.core.Core;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // exits whenever ESCAPE is pressed (important!)
        ExitOnEscape.enable();

        LCD.drawString("Ready for input", 0,0);

        // CarClient p = new FifoPipeCarClient("/home/lejos/pipes/speedlimit");
        CarClient p = new SocketCarClient();
        p.addListener(new SpeedSignPrinter());
        p.connect();
    }
}

