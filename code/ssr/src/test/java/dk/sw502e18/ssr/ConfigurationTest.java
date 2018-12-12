package dk.sw502e18.ssr;

import dk.sw502e18.ssr.Configuration;
import org.junit.Assert;
import org.junit.Test;
import org.opencv.ml.ANN_MLP;

public class ConfigurationTest {

  @Test
  public void canConstructWithDefaultData() {
    Configuration config = new Configuration();

    Assert.assertEquals(ANN_MLP.SIGMOID_SYM, config.activationFunction);
    Assert.assertEquals(0.1, config.activationFunctionParam1, 0);
    Assert.assertEquals(0.1, config.activationFunctionParam2, 0);
    Assert.assertEquals(ANN_MLP.RPROP, config.trainingMethod);
    Assert.assertEquals(0.1, config.trainingMethodParam1, 0);
    Assert.assertEquals(0.1, config.trainingMethodParam2, 0);
    Assert.assertEquals(1500, config.trainingCriteriaLimit);
    Assert.assertEquals(0.005, config.trainingCriteriaPrecision, 0);
    Assert.assertArrayEquals(new int[]{40 * 40, 20 * 20, 10 * 10, 6}, config.layers);
  }

  @Test
  public void canConstructWithDataGivenAsArrayOfDoubles() {
    double[] params = new double[]{
        2,    // Activation function
        0.5,  // Activation param1
        0.5,  // Activation param2
        1,    // Training method
        0.1,  // Training param1
        0.1,  // Training param2
        3500, // Max iteration term condition
        0.8,  // Minimum delta epsilon term condition
        20,   // Width of rectangle input image
        5,    // Size of output layer
        50,   // Size of first hidden layer
        100   // Size of second hidden layer
    };

    Configuration config = new Configuration(params);

    Assert.assertEquals(2, config.activationFunction);
    Assert.assertEquals(0.5, config.activationFunctionParam1, 0);
    Assert.assertEquals(0.5, config.activationFunctionParam2, 0);
    Assert.assertEquals(1, config.trainingMethod);
    Assert.assertEquals(0.1, config.trainingMethodParam1, 0);
    Assert.assertEquals(0.1, config.trainingMethodParam2, 0);
    Assert.assertEquals(3500, config.trainingCriteriaLimit);
    Assert.assertEquals(0.8, config.trainingCriteriaPrecision, 0);
    Assert.assertArrayEquals(new int[]{400, 50, 100, 5}, config.layers);
  }

  @Test
  public void canConstructWithDataGivenAsAString() {
    Configuration config = Configuration.fromString(
        "1;0.1;0.1;2;0.4;0.2;700;0.06;15;7;100;200;300;500;"
    );

    Assert.assertEquals(1, config.activationFunction);
    Assert.assertEquals(0.1, config.activationFunctionParam1, 0);
    Assert.assertEquals(0.1, config.activationFunctionParam2, 0);
    Assert.assertEquals(2, config.trainingMethod);
    Assert.assertEquals(0.4, config.trainingMethodParam1, 0);
    Assert.assertEquals(0.2, config.trainingMethodParam2, 0);
    Assert.assertEquals(700, config.trainingCriteriaLimit);
    Assert.assertEquals(0.06, config.trainingCriteriaPrecision, 0);
    Assert.assertArrayEquals(new int[]{225, 100, 200, 300, 500, 7}, config.layers);
  }

  @Test
  public void canSerializeAConfiguration() {
    String expected = "1;0.100000;0.100000;2;0.400000;0.200000;700;0.060000;15;7;100;200;300;500;";
    Configuration config = Configuration.fromString(
        "1;0.1;0.1;2;0.4;0.2;700;0.06;15;7;100;200;300;500;"
    );

    Assert.assertEquals(
        "Error asserting that:\n" + config.serialize() + "\nis equal to:\n" + expected,
        config.serialize(),
        expected
    );
  }

  @Test(expected = RuntimeException.class)
  public void FromStringThrowsErrorIfToFewArguments() {
    Configuration config = Configuration.fromString(
        "1;0.1;0.1;2;0.4;0.2;700;0.06;"
    );
  }

  @Test(expected = RuntimeException.class)
  public void FromDoubleArrayThrowsErrorIfToFewArguments() {
    Configuration config = new Configuration(new double[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
  }

}
