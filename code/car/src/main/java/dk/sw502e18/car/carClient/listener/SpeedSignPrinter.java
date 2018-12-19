package dk.sw502e18.car.carClient.listener;

import dk.sw502e18.car.carClient.CarClientMessageListener;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;

public class SpeedSignPrinter implements CarClientMessageListener {
    private final GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
    private final int SW = g.getWidth();
    private final int SH = g.getHeight();

    @Override
    public void onMessage(String message) {
        g.setFont(Font.getFont(0, 0, Font.SIZE_LARGE));
        g.clear();

        g.drawString(
                message,
                SW / 2,
                SH / 2 + g.getFont().height / 3,
                GraphicsLCD.HCENTER | GraphicsLCD.BASELINE
        );
    }
}
