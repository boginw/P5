package dk.sw502e18.car;

import dk.sw502e18.car.carClient.SocketCarClient;
import dk.sw502e18.car.carClient.listener.SpeedAdjuster;
import dk.sw502e18.car.carClient.listener.SpeedSignPrinter;
import dk.sw502e18.car.utils.ExitOnEscape;
import lejos.hardware.lcd.LCD;

public class Main {
    public static void main(String[] args) {
        // exits whenever ESCAPE is pressed (important!)
        ExitOnEscape.enable();

        LCD.drawString("Ready for input", 0, 0);

        CarClient p = new SocketCarClient()
                .addListener(new SpeedAdjuster(80))
                .addListener(new SpeedSignPrinter());
        p.connect();
    }
}

