package dk.sw502e18.ssr.components;

import org.opencv.core.Mat;

public interface ImageCropper {

    /**
     * Crops the input image
     *
     * @param input Image to crop
     * @return Cropped image
     */
    Mat crop(Mat input);
}
