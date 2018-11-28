package dk.sw502e18.ssr.components.circleRecognizer;

import dk.sw502e18.ssr.components.CircleRecognizer;
import dk.sw502e18.ssr.pipeline.Step;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class HoughCircleRecognizer implements CircleRecognizer, Step<Mat, Mat> {

  private Mat input;
  private Mat crChannel;
  private Mat circles = new Mat();

  /****************************
  *****************************
  **   Threshold  settings   **
  *****************************
  ****************************/
  private int thresholdMin = 160;
  private int thresholdMax = 255;

  /****************************
  *****************************
  ** Gaussian blur settings  **
  *****************************
  ****************************/
  private Size gaussianKernelSize = new Size(9,9);
  private int gaussianSigmaX = 2;
  private int gaussianSigmaY = 2;

  /****************************
  *****************************
  **  Hough Circle settings  **
  *****************************
  ****************************/
  // The resolution of the accumulator. If set to 1 the accumulator has same size as input.
  // If higher than the 1 the accumulator will be smaller by the factor of that number.
  // The value has to be >= 1
  private int houghResolution = 1;

  // This is the minimum distance that must exists between two circles for the algorithm to
  // consider them as distinct circles
  private int houghMinDist = 20;

  // This is the threshold used by the Canny Edge Detecting with in the algorithm.
  // This value is passed as the upper bound to Canny Edge and the lower bound
  // is set to the half of this value.
  private int houghCannyThreshold = 50;

  // the accumulators threshold, just like every other Hough Transform.
  private int houghAccumulatorThreshold = 30;

  // The min/max radius of foundable circles.
  // This means that these are the radii of circles for which the accumulator has a representation.
  // If set to 0 no limit.
  private int houghMinCircleRadius = 0;
  private int houghMaxCircleRadius = 0;


  /****************************
  *****************************
  **    Drawing  settings    **
  *****************************
  ****************************/
  private int drawCenterRadius = 1;
  private Scalar drawCenterColor = new Scalar(0, 100, 100);

  private Scalar drawCircumferenceColor = new Scalar(255, 0, 255);

  private int drawLineThickness = 3;
  private int drawLineType = 8;
  private int drawShift = 0;



  private void extractCr() {
    Mat output = new Mat();
    Imgproc.cvtColor(this.input, output, Imgproc.COLOR_BGR2YCrCb);
    List<Mat> ycrcb_planes = new ArrayList<>();
    Core.split(output, ycrcb_planes);
    this.crChannel = ycrcb_planes.get(1);
  }

  private void threshold() {
    Imgproc.threshold(
        this.crChannel,
        this.crChannel,
        this.thresholdMin,
        this.thresholdMax,
        Imgproc.THRESH_BINARY_INV
    );
  }

  private void blur() {
    Imgproc.GaussianBlur(
        this.crChannel,
        this.crChannel,
        this.gaussianKernelSize,
        this.gaussianSigmaX,
        this.gaussianSigmaY
    );
  }

  private void houghCircle() {
    Imgproc.HoughCircles(
        this.crChannel,
        this.circles,
        Imgproc.HOUGH_GRADIENT,
        this.houghResolution,
        this.houghMinDist,
        this.houghCannyThreshold,
        this.houghAccumulatorThreshold,
        this.houghMinCircleRadius,
        this.houghMaxCircleRadius
    );
  }

  private void drawOnInput() {
    if (this.circles.empty()) {
      return;
    }

    for (int i = 0; i < circles.cols(); i++) {
      double[] c = circles.get(0, i);
      Point center = new Point(Math.round(c[0]), Math.round(c[1]));

      Imgproc.circle(
          input,
          center,
          this.drawCenterRadius,
          this.drawCenterColor,
          this.drawLineThickness,
          this.drawLineType,
          this.drawShift
      );

      int radius = (int) Math.round(c[2]);

      Imgproc.circle(
          input,
          center,
          radius,
          this.drawCircumferenceColor,
          this.drawLineThickness,
          this.drawLineType,
          this.drawShift
      );
    }
  }

  @Override
  public Mat process(Mat input) {
    this.input = input;

    extractCr();
    threshold();
    blur();
    houghCircle();
    drawOnInput();

    return this.input;
  }
}
