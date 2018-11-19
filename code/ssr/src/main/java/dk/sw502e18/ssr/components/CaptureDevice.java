package dk.sw502e18.ssr.components;

import org.opencv.core.Mat;

public interface CaptureDevice {
    void close();
    boolean isCameraActive();
    Mat capture();
}
