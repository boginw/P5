package dk.sw502e18.ssr.components.imageProvider;

import dk.sw502e18.ssr.components.ImageProvider;
import dk.sw502e18.ssr.pipeline.Input;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class Camera implements Input<Mat>, ImageProvider {
    private VideoCapture cam;

    /**
     * Default constructor
     *
     * @param index The index of the camera to be used
     */
    public Camera(int index) {
        cam = new VideoCapture(index);
    }

    @Override
    public Mat capture() {
        if (!cam.isOpened()) {
            throw new RuntimeException("Camera not opened!");
        }

        Mat mat = new Mat();
        cam.read(mat);
        return mat;
    }

    @Override
    public Mat get() {
        return capture();
    }
}
