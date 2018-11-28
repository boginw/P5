package dk.sw502e18.ssr;

import dk.sw502e18.ssr.components.outputtingComponents.Outputter;
import dk.sw502e18.ssr.components.imageProvider.FolderScanner;
import dk.sw502e18.ssr.components.grayScaler.Grayscale;
import dk.sw502e18.ssr.pipeline.Pipe;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class Main {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Pipe<Mat> pipe = new Pipe<Mat>()
                .first(new FolderScanner("ABSOLUTE PATH TO INPUT-FILES"))
                .then(new Grayscale())
                .then(new Outputter("ABSOLUTE PATH TO OUTPUT-FILES"));

        pipe.run();
        System.out.println("OK");
    }
}
