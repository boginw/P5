package dk.sw502e18.ssr;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
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

        Pair<Mat, RotatedRect> smthng = flattenCircle(src, ellipse);

        try {
            return cropEllipse(smthng.getLeft(), smthng.getRight());
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

    private Pair<Mat, RotatedRect> flattenCircle(Mat input, RotatedRect ellipse){

        long timer = System.nanoTime();
        Mat tempMat1 = new Mat();
        Mat tempMat2 = new Mat();
        MatOfPoint2f tempMOP2f = new MatOfPoint2f();

        input.copyTo(tempMat1);

        // Input is rotated to align ellipse focal-points with x-axis. Stored in Tobias.
        Mat rotateM = Imgproc.getRotationMatrix2D(ellipse.center, ellipse.angle, 1.0);
        Imgproc.warpAffine(input, tempMat1, rotateM, new Size(tempMat1.width(), tempMat1.width()));

        // Left/right top/buttom points are located on ellipse.
        int x = (int)ellipse.center.x;
        int y = (int)ellipse.center.y;
        Point p3 = findEdge(tempMat1, x, y, 0, -1, threshold);
        Point p4 = findEdge(tempMat1, x, y, 0, 1, threshold);
        Point p1 = findEdge(tempMat1, x, y, -1, 0, threshold);
        Point p2 = findEdge(tempMat1, x, y, 1, 0, threshold);

        // If anything has been null, invoke fuck-it-Im-out-of-here
        if ((p1 == null) || (p2 == null) || (p3 == null) || (p4 == null)){
            return new ImmutablePair<>(input, ellipse);
        }

        // Storing p1-p4 as matrix.
        MatOfPoint2f perspecInputMat = new MatOfPoint2f(p1, p2, p3, p4);

        // Transformation matrix for ellipse-to-circle is calculated.
        Mat perspecTransfMat = Imgproc.getPerspectiveTransform(
                perspecInputMat,  // 1st arg: Points from before.
                new MatOfPoint2f( // 2nd arg: Points to "land" on.
                        new Point(x - (y - p3.y), p1.y),
                        new Point(x + (y - p3.y), p2.y),
                        p3,
                        p4
                )
        );


        // Image warp - ellipse becomes circle, held in tempMat2
        Imgproc.warpPerspective(tempMat1, tempMat2, perspecTransfMat, tempMat1.size());

        tempMOP2f = ellipseCrawler(tempMat2,(float)tempMat2.width() / 2, (float)tempMat2.height()/2, threshold);
        // If anything has been null, invoke fuck-it-Im-out-of-here
        if (tempMOP2f == null){
            return new ImmutablePair<>(input, ellipse);
        }
        // Image is rotated back to normal orientation, now held in tempMat1
        RotatedRect ellipseOut = Imgproc.fitEllipse(tempMOP2f);
        rotateM = Imgproc.getRotationMatrix2D(ellipseOut.center, - ellipse.angle, 1.0);
        Imgproc.warpAffine(tempMat2, tempMat1, rotateM, tempMat2.size());

        tempMOP2f = ellipseCrawler(tempMat1,(float)tempMat1.width() / 2, (float)tempMat1.height()/2, threshold);
        // If anything has been null, invoke fuck-it-Im-out-of-here
        if (tempMOP2f == null){
            return new ImmutablePair<>(input, ellipse);
        }

        // Ellipse for further cropping is calculated.
        ellipseOut = Imgproc.fitEllipse(tempMOP2f);

        long timer2 = System.nanoTime();

        // System.out.print(timer2 - timer);

        return new ImmutablePair<>(tempMat1, ellipseOut);
    }

}