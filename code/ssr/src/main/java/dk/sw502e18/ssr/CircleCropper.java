package dk.sw502e18.ssr;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CircleCropper {

    public CircleCropper() { }

    public Mat crop(Mat src, Mat dst, Point point) {
        int x = (int) point.x;
        int y = (int) point.y;

        MatOfPoint2f points = ellipseCrawler(src, x, y);

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
     * Crops an image to fit the ellipse found.
     *
     * @param input   The original image to be cropped.
     * @param ellipse The ellipse to crop to.
     * @return The cropped, elliptical image.
     */
    private static Mat cropEllipse(Mat input, RotatedRect ellipse) {
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


    /**
     * Finds an edge from an image, point, increments and threshold
     *
     * @param input  The image inwhich to find an edge on
     * @param x      The starting x-coordinate
     * @param y      The starting y-coordinate
     * @param xIncr  Increment for x
     * @param yIncr  Increment for y
     * @return Point of edge or null if nothing was found
     */
    private Point findEdge(Mat input, double x, double y, double xIncr, double yIncr) {
        while (x > 0 && x < input.width() && y > 0 && y < input.height()) {
            if (input.get((int) y, (int) x)[0] > 0) {
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
     * @return A matrix of 2D points on the periphery.
     */
    private MatOfPoint2f ellipseCrawler(Mat input, double x, double y) {
        try {
            return new MatOfPoint2f(
                    // Left, right, top, bottom
                    findEdge(input, x, y, -1, 0),
                    findEdge(input, x, y, 1, 0),
                    findEdge(input, x, y, 0, -1),
                    findEdge(input, x, y, 0, 1),

                    // Top-left, bottom-left
                    findEdge(input, x, y, -1, -1),
                    findEdge(input, x, y, -1, 1),
                    // Top-right, bottom-right
                    findEdge(input, x, y, 1, -1),
                    findEdge(input, x, y, 1, 1)
            );
        } catch (NullPointerException e) {
            return null;
        }
    }
}
