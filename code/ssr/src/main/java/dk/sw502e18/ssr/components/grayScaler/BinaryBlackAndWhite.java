package dk.sw502e18.ssr.components.grayScaler;

import dk.sw502e18.ssr.components.Grayscaler;
import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class BinaryBlackAndWhite implements Grayscaler, Step<Mat, Mat> {

    /**
     * Threshold value, every pixel under will be black, over will be white
     */
    private int threshold;
    
    /**
     * The maximum value for things above the threshold
     */
    private int thresholdMax = 255;

    /**
     * Convert a picture to Binary black and white, expects a gray scale image.
     * @param thresh threshold value.
     */
    public BinaryBlackAndWhite(int thresh){
        this.threshold = thresh;
    }

    @Override
    public Mat grayscale(Mat input) {
        Imgproc.threshold(
                input,
                input,
                this.threshold,
                this.thresholdMax,
                Imgproc.THRESH_BINARY
        );
        return input;
    }


    @Override
    public Mat process(Mat input) {
        return grayscale(input);
    }
}
