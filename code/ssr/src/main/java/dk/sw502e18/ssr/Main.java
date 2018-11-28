package dk.sw502e18.ssr;

import dk.sw502e18.ssr.components.grayScaler.BinaryBlackAndWhite;
import dk.sw502e18.ssr.components.outputtingComponents.Outputter;
import dk.sw502e18.ssr.components.imageProvider.FolderScanner;
import dk.sw502e18.ssr.components.grayScaler.Grayscale;
import dk.sw502e18.ssr.pipeline.Pipe;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


public class Main {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Pipe<Mat> pipe = new Pipe<Mat>()
                .first(new FolderScanner("/home/scarress/Pictures"))
                .then(new Grayscale())
                .then(new BinaryBlackAndWhite(127))
                .then(new Outputter("/home/scarress/Pictures/Wubz"));

        pipe.run();
        System.out.println("OK");
    }
}
