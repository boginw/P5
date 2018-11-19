package dk.sw502e18.ssr.components;

import org.opencv.core.Mat;

public interface Grayscaler {

    /**
     * Grayscales an input
     *
     * @param input Image to be grayscaled
     * @return Grayscaled image
     */
    Mat grayscale(Mat input);
}
