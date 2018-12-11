package dk.sw502e18.ssr.mode;

import dk.sw502e18.ssr.CarServer;
import dk.sw502e18.ssr.EllipseProcessor;
import dk.sw502e18.ssr.Mode;
import dk.sw502e18.ssr.carServer.SocketCarServer;
import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;

import java.util.function.Function;


public class CarMode implements Mode {
    private CarServer cs;

    public CarMode() {
        cs = new SocketCarServer("10.0.1.1", 9090);
    }

    public void start(VideoCapture vid, EllipseProcessor processor, Function<Mat, Integer> func) {
        Mat mat = new Mat();

        while (!cs.connect()) {
            System.out.println("Connection unsuccessfull, trying again in 5 seconds");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //noinspection InfiniteLoopStatement
        while (true) {
            vid.read(mat);
            Mat p;

            if (!mat.empty() && (p = processor.detect(mat)) != null) {
                cs.send(String.valueOf(func.apply(p)));
            }
        }
    }
}