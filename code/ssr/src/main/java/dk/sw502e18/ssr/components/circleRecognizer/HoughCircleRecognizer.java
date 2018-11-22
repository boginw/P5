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
  private Mat cr_channel;
  private Mat circles = new Mat();

  private void extractCr() {
    Mat output = new Mat();
    Imgproc.cvtColor(this.input, output, Imgproc.COLOR_BGR2YCrCb);
    List<Mat> ycrcb_planes = new ArrayList<>();
    Core.split(output, ycrcb_planes);
    this.cr_channel = ycrcb_planes.get(1);
  }

  private void threshold() {
    Imgproc.threshold(
        this.cr_channel,
        this.cr_channel,
        160,
        255,
        Imgproc.THRESH_BINARY_INV
    );
  }

  private void blur() {
    Imgproc.GaussianBlur(
        this.cr_channel,
        this.cr_channel,
        new Size(9, 9),
        2,
        2
    );
  }

  private void houghCircle() {
    Imgproc.HoughCircles(
        this.cr_channel,
        this.circles,
        Imgproc.HOUGH_GRADIENT,
        1,
        20,
        50,
        30,
        0,
        0
    );
  }

  private void drawOnInput() {
    if (this.circles.empty()) {
      return;
    }

    for (int i = 0; i < circles.cols(); i++) {
      double[] c = circles.get(0, i);
      Point center = new Point(Math.round(c[0]), Math.round(c[1]));

      Imgproc.circle(input, center, 1, new Scalar(0, 100, 100), 3, 8, 0);
      int radius = (int) Math.round(c[2]);
      Imgproc.circle(input, center, radius, new Scalar(255, 0, 255), 3, 8, 0);
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
