package dk.sw502e18.ssr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

public class EllipseProcessorTest {

  EllipseProcessor ep;

  @Before
  public void initializer() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    ep = new EllipseProcessor(160, 10, new Size(30, 30));
  }


  @Test
  public void isAbleToDetectARedCircleInOptimalSituation() {
    Mat img = Imgcodecs.imread(getClass().getResource("red_circle.jpg").getPath());

    Assert.assertNotNull(img);

    Mat result = ep.detect(img);

    Assert.assertNotNull(result);
  }

  @Test
  public void wontDetectARedCircleInOptimalSituation() {
    Mat img = Imgcodecs.imread(getClass().getResource("blue_circle.jpg").getPath());

    Assert.assertNotNull(img);

    Mat result = ep.detect(img);

    Assert.assertNull(result);
  }

  @Test
  public void dontRecognizeOtherRedObjectsAsCircle() {
    Mat img = Imgcodecs.imread(getClass().getResource("misc_no_red_circle.jpg").getPath());

    Assert.assertNotNull(img);

    Mat result = ep.detect(img);

    Imgcodecs.imwrite("image.jpg", result);

    Assert.assertNull(result);
  }
}
