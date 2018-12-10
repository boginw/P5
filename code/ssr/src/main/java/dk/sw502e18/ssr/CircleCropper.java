package dk.sw502e18.ssr;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CircleCropper {

    private final int threshold;

    public CircleCropper(int threshold) {
        this.threshold = threshold;
    }

    public Mat crop(Mat src, Mat dst, Point point) {
        int x = (int) point.x;
        int y = (int) point.y;

        MatOfPoint2f points = ellipseCrawler(src, x, y, threshold);

        if (points == null) {
            return null;
        }

        RotatedRect ellipse = Imgproc.fitEllipse(points);

        try {
            return cropEllipse(dst, ellipse);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
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
        try {
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
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Crops an image to fit the ellipse found.
     *
     * @param input   The original image to be cropped.
     * @param ellipse The ellipse to crop to.
     * @return The cropped, elliptical image.
     */
    public static Mat cropEllipse(Mat input, RotatedRect ellipse) {
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
