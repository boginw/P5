package dk.sw502e18.ssr.modules;

import dk.sw502e18.ssr.components.CaptureDevice;
import dk.sw502e18.ssr.components.CircleRecognizer;
import dk.sw502e18.ssr.components.ImageCropper;
import org.opencv.core.Mat;

public class CircleLocator implements Locator {
    private CaptureDevice camera;
    private CircleRecognizer recognizer;
    private ImageCropper cropper;

    @Override
    public CaptureDevice getCaptureDevice() {
        return camera;
    }

    @Override
    public CircleRecognizer getCircleRecognition() {
        return recognizer;
    }

    @Override
    public ImageCropper getImageCropper() {
        return cropper;
    }

    @Override
    public void setCaptureDevice(CaptureDevice device) {
        this.camera = device;
    }

    @Override
    public void setCircleRecognition(CircleRecognizer circleRec) {
        this.recognizer = circleRec;
    }

    @Override
    public void setImageCropper(ImageCropper cropper) {
        this.cropper = cropper;
    }

    @Override
    public Mat[] findCircle() {
        Mat image = camera.capture();
        return recognizer.process(image);
    }
}
