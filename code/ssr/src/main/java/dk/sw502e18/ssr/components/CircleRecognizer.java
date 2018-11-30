package dk.sw502e18.ssr.components;

import org.opencv.core.Mat;

public interface CircleRecognizer {
    Mat process(Mat input);
}
