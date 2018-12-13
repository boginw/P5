package dk.sw502e18.ssr;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.videoio.VideoCapture;

import java.util.function.Function;

public interface Mode {
    void start(VideoCapture vid, EllipseProcessor processor, Function<Mat, MatOfFloat> func);
}
