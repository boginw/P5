package dk.sw502e18.ssr.components.imageResizer;

import dk.sw502e18.ssr.components.ImageResizer;
import dk.sw502e18.ssr.pipeline.Step;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class StandardResizer implements dk.sw502e18.ssr.components.ImageResizer, Step<Mat, Mat> {

  private int _destWidth;
  private int _destHeight;
  private Mat _dest;

  /**
   * Image resizer that fills the image into a 20x20 pixel canvas.
   */
  public StandardResizer(){
    this._destWidth = this._destHeight = 20;
    this._dest = new Mat();
  }

  /**
   * Image resizer that fills the image into the specified width and height.
   * @param width
   * @param height
   */
  public StandardResizer(int width, int height){
    this._destWidth = width;
    this._destHeight = height;
    this._dest = new Mat();
  }

  @Override
  public Mat process(Mat input) {
    Imgproc.resize(input, _dest, new Size(_destWidth, _destHeight));

    return _dest;
  }
}
