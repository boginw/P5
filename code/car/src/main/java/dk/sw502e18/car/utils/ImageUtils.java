package dk.sw502e18.car.utils;

import lejos.hardware.lcd.Image;
import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.IOException;

public class ImageUtils {
    /**
     * Due to LeJOS having a bug in Image.createImage(InputStream) a workaround has been created
     * @param context From which context the resource should be fetched
     * @param resource The resource to be fetched
     * @return LeJOS image object
     * @throws IOException due to use of DataInputStream
     */
    public static Image fromResource(Object context, String resource) throws IOException {
        DataInputStream in = new DataInputStream(context.getClass().getResourceAsStream(resource));
        in.readInt(); // skip header

        Image img = new Image(
                in.readUnsignedShort(), // width
                in.readUnsignedShort(), // height
                IOUtils.toByteArray(in) // bytes
        );
        in.close();

        return img;
    }
}
