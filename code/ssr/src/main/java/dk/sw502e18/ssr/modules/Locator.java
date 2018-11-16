package dk.sw502e18.ssr.modules;

import dk.sw502e18.ssr.components.CaptureDevice;
import dk.sw502e18.ssr.components.CircleRecognizer;
import dk.sw502e18.ssr.components.ImageCropper;
import dk.sw502e18.ssr.units.Image;

/**
 * Responsible for grabbing images, and detecting and cropping circles
 */
public interface Locator {

    /**
     * Gets the Locator's capture device
     *
     * @return The Locator's capture device
     */
    CaptureDevice getCaptureDevice();

    /**
     * Gets the Locator's circle recognition
     *
     * @return The Locator's circle recognition
     */
    CircleRecognizer getCircleRecognition();

    /**
     * Gets the Locator's image cropper
     *
     * @return The Locator's image cropper
     */
    ImageCropper getImageCropper();

    /**
     * Sets the Locator's capture device
     *
     * @param device A CaptureDevice
     */
    void setCaptureDevice(CaptureDevice device);

    /**
     * Sets the Locator's circle recognition
     *
     * @param circleRec A CircleRecognizer
     */
    void setCircleRecognition(CircleRecognizer circleRec);

    /**
     * Sets the Locator's image cropper
     *
     * @param cropper An ImageCropper
     */
    void setImageCropper(ImageCropper cropper);

    /**
     * Grabs captures an image, determines if circles are present in image, if so, crops
     * the image to only contain circles and puts each image of circle in return array.
     *
     * @return Array of circles in image
     */
    Image[] findCircle();
}
