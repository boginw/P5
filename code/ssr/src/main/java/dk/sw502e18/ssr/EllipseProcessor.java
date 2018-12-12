package dk.sw502e18.ssr;

import dk.sw502e18.ssr.mode.WebCamMode;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EllipseProcessor {
    private CircleCropper cc;
    private int minWH;
    private Size size;

    public EllipseProcessor(int minWH, Size size) {
        cc = new CircleCropper();
        this.minWH = minWH;
        this.size = size;
    }

    public Mat detect(File file) {
        Mat c = Imgcodecs.imread(file.getAbsolutePath());
        return detect(c);
    }

    public Mat detect(Mat input) {
        Mat process = crExtract(input.clone());
        Mat out = input.clone();
        threshold(process, false);
        threshold(out, true);
        Imgproc.GaussianBlur(process, process, new Size(9, 9), 2, 2);

        Mat circles = detectCircles(process);

        for (int i = 0; i < circles.cols(); i++) {
            double[] c = circles.get(0, i);
            Mat cropped = cc.crop(process, out, new Point(c));

            if (cropped != null && cropped.rows() > minWH && cropped.cols() > minWH) {
                Imgproc.resize(cropped, cropped, size);
                Rect rectCrop = new Rect(0, 0, (int) size.width / 2, (int) size.height);
                Imgproc.resize(new Mat(cropped, rectCrop), cropped, size);

                return cropped;
            }
        }

        return null;
    }

    private void threshold(Mat c, boolean inv) {
        if (c.type() != CvType.CV_8UC1) {
            Imgproc.cvtColor(c, c, Imgproc.COLOR_BGR2GRAY);
        }

        //Imgproc.threshold(c, c, 150, 255, Imgproc.THRESH_TOZERO);
        Imgproc.threshold(c, c, 160, 255, Imgproc.THRESH_OTSU | (inv ? Imgproc.THRESH_BINARY_INV : Imgproc.THRESH_BINARY));
    }

    private Mat crExtract(Mat c) {
        Imgproc.cvtColor(c, c, Imgproc.COLOR_BGR2YCrCb);
        List<Mat> ycrcb_planes = new ArrayList<>();
        Core.split(c, ycrcb_planes);
        c.release();
        return ycrcb_planes.get(1);
    }

    private Mat detectCircles(Mat thresholdImage) {
        Mat circles = new Mat();
        double houghResolution = 1;
        double houghMinDist = 10;
        double houghCannyThreshold = 50;
        double houghAccumulatorThreshold = 30;
        int houghMinCircleRadius = 0;
        int houghMaxCircleRadius = 0;

        Imgproc.HoughCircles(
                thresholdImage,
                circles,
                Imgproc.HOUGH_GRADIENT,
                houghResolution,
                houghMinDist,
                houghCannyThreshold,
                houghAccumulatorThreshold,
                houghMinCircleRadius,
                houghMaxCircleRadius
        );

        return circles;
    }
}
