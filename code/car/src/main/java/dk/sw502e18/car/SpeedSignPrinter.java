package dk.sw502e18.car;

import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;

import java.io.IOException;
import java.io.InputStream;

public class SpeedSignPrinter implements PipeListener {
    private final GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
    private final int SW = g.getWidth();
    private final int SH = g.getHeight();
    private final Image c;

    public SpeedSignPrinter() {
        InputStream in = getClass().getResourceAsStream("sign.image");
        try {
            c = Image.createImage(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
