package dk.sw502e18;

import dk.sw502e18.ssr.CircleCropper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;

public class CircleCropperTest {

  CircleCropper cc;

  @Before
  public void initialize() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    cc = new CircleCropper(160);
  }

  @Test
  public void canConstructUsingAThreshold() {
    Assert.assertNotNull(cc);
  }

  @Test
  public void canCropAImage() {
    Mat image = generateImage();

    Mat newImage = cc.crop(image, image, new Point(5,5));

    Assert.assertEquals(8, newImage.rows());
    Assert.assertEquals(7, newImage.cols());
  }

  /**
   * Generates a simple image with an ellipse inside.
   * @return
   */
  private Mat generateImage(){
    Mat image = new Mat(new Size(10, 10), CvType.CV_8U);

    image.put(0, 0, 0, 0,   0,   0,   0,   0,   0,   0,   0, 0);
    image.put(1, 0, 0, 0,   0, 255, 255, 255, 255, 255,   0, 0);
    image.put(2, 0, 0, 0, 255, 255,   0,   0,   0, 255, 255, 0);
    image.put(3, 0, 0, 0, 255,   0,   0,   0,   0,   0, 255, 0);
    image.put(4, 0, 0, 0, 255,   0,   0,   0,   0,   0, 255, 0);
    image.put(5, 0, 0, 0, 255,   0,   0,   0,   0,   0, 255, 0);
    image.put(6, 0, 0, 0, 255,   0,   0,   0,   0,   0, 255, 0);
    image.put(7, 0, 0, 0, 255, 255,   0,   0,   0, 255, 255, 0);
    image.put(8, 0, 0, 0,   0, 255, 255, 255, 255, 255,   0, 0);
    image.put(9, 0, 0, 0,   0,   0,   0,   0,   0,   0,   0, 0);

    return image;
  }


}
