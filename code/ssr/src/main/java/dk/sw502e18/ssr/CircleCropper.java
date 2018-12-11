package dk.sw502e18.ssr;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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

        // Circle flattening is attempted. Reverts back to normal if flattening fails.
        Pair<Mat, RotatedRect> newAndImproved = flattenCircle(src, dst, ellipse);

        try {
            return cropEllipse(newAndImproved.getLeft(), newAndImproved.getRight());
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

    /**
     * Attempts to warp/transform / "flatten" ellipse into circle.
     *
     * @param inputSrc Orignal red-channel Mat containing ellipse to be flattened.
     * @param inputDst Original picture containing red ellipse (to be flattened).
     * @param ellipse  The original ellipse.
     * @return New flattened picture and corresponding ellipse, or original picture and ellipse if flattening failed.
     */
    private Pair<Mat, RotatedRect> flattenCircle(Mat inputSrc, Mat inputDst, RotatedRect ellipse) {

        // Temporary placeholders used to preserve original inputsSrc and inputDst.
        Mat tempDstMat1 = new Mat();
        Mat tempDstMat2 = new Mat();
        Mat tempSrcMat1 = new Mat();
        Mat tempSrcMat2 = new Mat();
        // Temporary Mat used with various opencv-methods
        MatOfPoint2f tempMatOP2f;

        inputDst.copyTo(tempDstMat1);

        // Inputs is rotated to align ellipse focal-points with x/y-axis. Stored in temp-Mats1.
        Mat rotateM = Imgproc.getRotationMatrix2D(ellipse.center, ellipse.angle, 1.0);
        Imgproc.warpAffine(inputDst, tempDstMat1, rotateM, new Size(tempDstMat1.width(), tempDstMat1.width()));
        Imgproc.warpAffine(inputSrc, tempSrcMat1, rotateM, new Size(tempSrcMat1.width(), tempSrcMat1.width()));

        // Left/right top/bottom points are located on ellipse.
        int x = (int)ellipse.center.x;
        int y = (int)ellipse.center.y;
        Point p3 = findEdge(tempSrcMat1, x, y, 0, -1, threshold);
        Point p4 = findEdge(tempSrcMat1, x, y, 0, 1, threshold);
        Point p1 = findEdge(tempSrcMat1, x, y, -1, 0, threshold);
        Point p2 = findEdge(tempSrcMat1, x, y, 1, 0, threshold);

        // If anything has been null, invoke FIIOOH-protocol
        if ((p1 == null) || (p2 == null) || (p3 == null) || (p4 == null)) {
            return new ImmutablePair<>(inputSrc, ellipse);
        }

        // Storing p1-p4 as matrix.
        MatOfPoint2f perspectInputMat = new MatOfPoint2f(p1, p2, p3, p4);

        // Transformation matrix for ellipse-to-circle is calculated.
        Mat perspectTransfMat = Imgproc.getPerspectiveTransform(
                perspectInputMat,  // 1st arg: Points from before.
                new MatOfPoint2f( // 2nd arg: Points to "land" on.
                        new Point(x - (y - p3.y), p1.y),
                        new Point(x + (y - p3.y), p2.y),
                        p3,
                        p4
                )
        );


        // Image warp - ellipse becomes circle, result stored in temp-Mats2
        Imgproc.warpPerspective(tempDstMat1, tempDstMat2, perspectTransfMat, tempDstMat1.size());
        Imgproc.warpPerspective(tempSrcMat1, tempSrcMat2, perspectTransfMat, tempSrcMat1.size());

        tempMatOP2f = ellipseCrawler(tempSrcMat2,(float)tempSrcMat2.width() / 2, (float)tempSrcMat2.height()/2, threshold);
        // If anything has been null, invoke FIIOOH-protocol
        if (tempMatOP2f == null) {
            return new ImmutablePair<>(inputSrc, ellipse);
        }

        // Image is rotated back to original rotational orientation, and then stored in temp-Mats1
        RotatedRect ellipseOut = Imgproc.fitEllipse(tempMatOP2f);
        rotateM = Imgproc.getRotationMatrix2D(ellipseOut.center, - ellipse.angle, 1.0);
        Imgproc.warpAffine(tempDstMat2, tempDstMat1, rotateM, tempDstMat2.size());
        Imgproc.warpAffine(tempSrcMat2, tempSrcMat1, rotateM, tempSrcMat2.size());

        tempMatOP2f = ellipseCrawler(tempSrcMat1,(float)tempSrcMat1.width() / 2, (float)tempSrcMat1.height()/2, threshold);
        // Once again, if anything has been null: FIIOOH
        if (tempMatOP2f == null) {
            return new ImmutablePair<>(inputSrc, ellipse);
        }

        // Ellipse for further cropping is calculated.
        ellipseOut = Imgproc.fitEllipse(tempMatOP2f);

        return new ImmutablePair<>(tempDstMat1, ellipseOut);
    }

}