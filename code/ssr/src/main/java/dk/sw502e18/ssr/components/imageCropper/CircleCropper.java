package dk.sw502e18.ssr.components.imageCropper;

import dk.sw502e18.ssr.components.ImageCropper;
import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CircleCropper implements ImageCropper, Step<Mat, Mat> {

    private final int thresh;

    public CircleCropper(int thresh) {
        this.thresh = thresh;
    }

    @Override
    public Mat process(Mat input) {
        return crop(input);
    }

    @Override
    public Mat crop(Mat input) {
        Mat out = fetchRedChannel(input);

        int x = out.width() / 2;
        int y = out.height() / 2;

        RotatedRect ellipse = Imgproc.fitEllipse(ellipseCrawler(out, x, y, thresh));

        return cropEllipse(input, ellipse);
    }

    /**
     * Finds an edge from an image, point, increments and threshold
     *
     * @param input  The image inwhich to find an edge on
     * @param x      The starting x-coordinate
     * @param y      The starting y-coordinate
     * @param xIncr  Increment for x
     * @param yIncr  Increment for y
     * @param thresh Threshold for edge
     * @return Point of edge or null if nothing was found
     */
    private Point findEdge(Mat input, double x, double y, double xIncr, double yIncr, int thresh) {
        while (x > 0 && x < input.width() && y > 0 && y < input.height()) {
            if (input.get((int) y, (int) x)[0] > thresh) {
                return new Point(x, y);
            }

            x += xIncr;
            y += yIncr;
        }

        // no edge found
        return null;
    }

    private Mat fetchRedChannel(Mat input) {
        Mat out = new Mat();
        Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2YCrCb);
        List<Mat> ycrcb_planes = new ArrayList<>();
        Core.split(out, ycrcb_planes);
        return ycrcb_planes.get(1);
    }

    /**
     * Will crawl an ellipse to find 8 points on the periphery.
     *
     * @param input  Image to crawl.
     * @param x      X-coordinate inside ellipse.
     * @param y      Y-coordinate inside ellipse.
     * @param thresh Threshold that catches edge ring.
     * @return A matrix of 2D points on the periphery.
     */
    private MatOfPoint2f ellipseCrawler(Mat input, double x, double y, int thresh) {
        return new MatOfPoint2f(
                // Left, right, top, bottom
                findEdge(input, x, y, -1, 0, thresh),
                findEdge(input, x, y, 1, 0, thresh),
                findEdge(input, x, y, 0, -1, thresh),
                findEdge(input, x, y, 0, 1, thresh),

                // Top-left, bottom-left
                findEdge(input, x, y, -1, -1, thresh),
                findEdge(input, x, y, -1, 1, thresh),
                // Top-right, bottom-right
                findEdge(input, x, y, 1, -1, thresh),
                findEdge(input, x, y, 1, 1, thresh)
        );
    }

    /**
     * Crops an image to fit the ellipse found.
     *
     * @param input   The original image to be cropped.
     * @param ellipse The ellipse to crop to.
     * @return The cropped, elliptical image.
     */
    private Mat cropEllipse(Mat input, RotatedRect ellipse) {
        int thickness = -1; // set to -1 to indicate to fill

        // Create a mask
        Mat mask = new Mat(input.rows(), input.cols(), CvType.CV_8U, Scalar.all(0));

        // Draw the ellipse on that mask
        Imgproc.ellipse(mask, ellipse, new Scalar(255, 255, 255), thickness);

        // Crop the image, using the mask
        Mat masked = new Mat();
        input.copyTo(masked, mask);

        // Apply threshold
        Mat threshold = new Mat();
        Imgproc.threshold(mask, threshold, 1, 255, Imgproc.THRESH_BINARY);

        // Find contours
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(threshold, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Crop
        Rect rect = Imgproc.boundingRect(contours.get(0));

        return masked.submat(rect);
    }

}
