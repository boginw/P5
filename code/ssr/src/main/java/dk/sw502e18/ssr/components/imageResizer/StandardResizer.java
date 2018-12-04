package dk.sw502e18.ssr.components.imageResizer;

import dk.sw502e18.ssr.components.ImageResizer;
import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class StandardResizer implements dk.sw502e18.ssr.components.ImageResizer, Step<Mat, Mat> {

  private int destWidth;
  private int destHeight;
  private Mat dest;

  /**
   * Image resizer that fills the image into a 20x20 pixel canvas.
   */
  public StandardResizer(){
    this.destWidth = this.destHeight = 20;
    this.dest = new Mat();
  }

  /**
   * Image resizer that fills the image into the specified width and height.
   * @param width
   * @param height
   */
  public StandardResizer(int width, int height){
    this.destWidth = width;
    this.destHeight = height;
    this.dest = new Mat();
  }

  @Override
  public Mat process(Mat input) {
    Imgproc.resize(input, dest, new Size(destWidth, destHeight));

    return dest;
  }
}
