package dk.sw502e18.ssr;

import dk.sw502e18.ssr.components.captureDevice.Camera;
import dk.sw502e18.ssr.components.grayScaler.Grayscale;
import dk.sw502e18.ssr.pipeline.Pipe;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs .Imgcodecs;


public class Main {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Pipe<Mat> pipe = new Pipe<Mat>()
                .first(new Camera(0))
                .then(new Grayscale());

        Imgcodecs.imwrite("/home/hamburger/Desktop/image.jpg", pipe.run());
        System.out.println("OK");
    }
}
