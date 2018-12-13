package dk.sw502e18.ssr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.MatOfFloat;

public class SpeedsignTest {

  @Before
  public void setUp() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }

  @Test
  public void canSaveResultsFromNN() {
    MatOfFloat result = new MatOfFloat();
    result.fromArray(0.25f, 0.25f, 0.5f);

    Speedsign.ANNResultEntry actual = Speedsign.fromNN(result.t());

    Assert.assertEquals(2, actual.label);
    Assert.assertEquals(50, actual.sign);
    Assert.assertEquals(0.5, actual.certainty, 0);
  }

  @Test
  public void canGetSignFromLabel() {
    Assert.assertEquals(20,Speedsign.fromLabel(0));
    Assert.assertEquals(30,Speedsign.fromLabel(1));
    Assert.assertEquals(50,Speedsign.fromLabel(2));
    Assert.assertEquals(60,Speedsign.fromLabel(3));
    Assert.assertEquals(70,Speedsign.fromLabel(4));
    Assert.assertEquals(80,Speedsign.fromLabel(5));
  }

}
