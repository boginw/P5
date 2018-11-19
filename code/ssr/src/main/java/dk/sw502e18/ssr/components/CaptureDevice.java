package dk.sw502e18.ssr.components;

import org.opencv.core.Mat;

public interface CaptureDevice {
    /**
     * Releases the resource used
     */
    void close();

    /**
     * Graps an image from the image source
     *
     * @return the image
     */
    Mat capture();
}
