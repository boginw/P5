package dk.sw502e18.ssr;

import dk.sw502e18.ssr.carServer.PipeCarServer;
import dk.sw502e18.ssr.carServer.SocketCarServer;
import dk.sw502e18.ssr.components.imageCropper.CircleCropper;
import dk.sw502e18.ssr.components.imageProvider.Resource;
import dk.sw502e18.ssr.pipeline.Pipe;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Pipe<Mat> pipe = new Pipe<Mat>()
                .first(new Resource("sample3.jpg"))
                .then(new CircleCropper(160));

        Imgcodecs.imwrite("./image.jpg", pipe.run());
        System.out.println("OK");

        CarServer com = new SocketCarServer("10.0.1.1", 9090);
        // CarServer com = new PipeCarServer("10.0.1.1", "root");

        if (com.connect()) {
            for (int i = 0; i < 100; i++) {
                com.send(String.valueOf(i));
                System.out.println(i);
                Thread.sleep(1000);
            }
        }

        com.disconnect();
    }
}
