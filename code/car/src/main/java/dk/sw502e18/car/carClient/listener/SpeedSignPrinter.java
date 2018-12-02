package dk.sw502e18.car.carClient.listener;

import dk.sw502e18.car.carClient.CarClientMessageListener;
import dk.sw502e18.car.utils.ImageUtils;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;

import java.io.IOException;

public class SpeedSignPrinter implements CarClientMessageListener {
    private final GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
    private final int SW = g.getWidth();
    private final int SH = g.getHeight();
    private final Image c;

    public SpeedSignPrinter() throws IOException {
        // TODO: this only works sometimes, please fix
        // c = ImageUtils.fromResource(this, "sign.lni");
        c = new Image(10, 10, null);
    }

    @Override
    public void onMessage(String message) {
        g.setFont(Font.getFont(0, 0, Font.SIZE_LARGE));

        g.clear();
        g.drawImage(c, 0, 0, 0);

        g.drawString(
                message,
                SW / 2,
                SH / 2 + g.getFont().height / 3,
                GraphicsLCD.HCENTER | GraphicsLCD.BASELINE
        );
    }
}
