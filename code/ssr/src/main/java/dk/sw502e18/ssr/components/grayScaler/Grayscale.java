package dk.sw502e18.ssr.components.grayScaler;

import dk.sw502e18.ssr.components.Grayscaler;
import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Grayscale implements Grayscaler, Step<Mat, Mat> {
    public Mat grayscale(Mat input) {
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2GRAY);
        return mat;
    }

    @Override
    public Mat process(Mat input) {
        return grayscale(input);
    }
}
