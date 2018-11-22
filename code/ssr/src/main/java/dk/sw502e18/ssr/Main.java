package dk.sw502e18.ssr;

import dk.sw502e18.ssr.components.captureDevice.Camera;
import dk.sw502e18.ssr.components.captureDevice.Resource;
import dk.sw502e18.ssr.components.grayScaler.Grayscale;
import dk.sw502e18.ssr.components.imageCropper.CircleCropper;
import dk.sw502e18.ssr.pipeline.Pipe;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Main {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Pipe<Mat> pipe = new Pipe<Mat>()
                .first(new Resource("sample3.jpg"))
                .then(new CircleCropper(160));

        Imgcodecs.imwrite("/home/hamburger/Desktop/image.jpg", pipe.run());
        System.out.println("OK");
    }
}
