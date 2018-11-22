package dk.sw502e18.ssr.components.captureDevice;

import dk.sw502e18.ssr.Main;
import dk.sw502e18.ssr.components.CaptureDevice;
import dk.sw502e18.ssr.pipeline.Input;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;

public class Resource implements CaptureDevice, Input<Mat> {
    private URL url;

    /**
     * Open resource as CaptureDevice
     * @param resourceName The name of the resource
     */
    public Resource(String resourceName) {
        this.url = Main.class.getResource(resourceName);
    }

    @Override
    public void close() {

    }

    @Override
    public Mat capture() {
        return Imgcodecs.imread(url.getPath());
    }

    @Override
    public Mat get() {
        return capture();
    }
}
