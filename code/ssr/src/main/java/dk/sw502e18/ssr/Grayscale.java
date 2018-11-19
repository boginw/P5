package dk.sw502e18.ssr;

import dk.sw502e18.ssr.components.Grayscaler;
import dk.sw502e18.ssr.pipe.Step;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Grayscale implements Grayscaler, Step<Mat, Mat> {
    @Override
    public Mat process(Mat input) {
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2GRAY);
        return mat;
    }
}
