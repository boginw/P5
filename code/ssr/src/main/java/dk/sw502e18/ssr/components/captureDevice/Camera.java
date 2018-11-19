package dk.sw502e18.ssr.components.captureDevice;

import dk.sw502e18.ssr.components.CaptureDevice;
import dk.sw502e18.ssr.pipeline.Input;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class Camera implements Input<Mat>, CaptureDevice {
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
    public void close() {
        cam.release();
    }

    @Override
    public boolean isCameraActive() {
        return cam.isOpened();
    }

    @Override
    public Mat capture() {
        if (!isCameraActive()) {
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
