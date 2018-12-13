package dk.sw502e18.ssr;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.util.reflection.FieldSetter;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.TrainData;

public class ANNTest {

  @Spy
  ANN_MLP ann_mlp;

  ANN ann;

  @Before
  public void initialize() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    ann_mlp = Mockito.spy(ANN_MLP.create());
    ann_mlp.setLayerSizes(new MatOfInt(16, 50, 50, 6));
    ann = new ANN(ann_mlp);
  }

  @Test
  public void getSizeShouldCalculateByTheLayerSize() {
    Assert.assertEquals(new Size(4,4), ann.getSize());

    //We assert on two calls, since the constructor uses one call.
    verify(ann_mlp, times(2)).getLayerSizes();
  }

  @Test (expected = RuntimeException.class)
  public void addSampleShouldThrowIfSampleIsSmallerThanInputLayerAllows() {
    // We create a sample of 15 entries, since the input layer has a size of 16.
    Mat sample = new MatOfInt(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

    ann.addSample(sample, 1);
  }

  @Test (expected = RuntimeException.class)
  public void addSampleShouldThrowIfSampleIsBiggerThanInputLayerAllows() {
    // We create a sample of 17 entries, since the input layer has a size of 16.
    Mat sample = new MatOfInt(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17);

    ann.addSample(sample, 1);
  }

  @Test (expected = RuntimeException.class)
  public void addSampleShouldThrowIfLabelIsSmallerThanOutputLayerAllows() {
    Mat sample = new MatOfInt(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

    // We use -1 since output layers cant have a negative amount of nodes
    ann.addSample(sample, -1);
  }

  @Test (expected = RuntimeException.class)
  public void addSampleShouldThrowIfLabelIsBiggerThanOutputLayerAllows() {
    Mat sample = new MatOfInt(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

    // We use 7 since output layer has a max of 6
    ann.addSample(sample, 7);
  }

  @Test
  @SuppressWarnings("SimplifiableJUnitAssertion")
  public void addSampleShouldAddTheParametersCorrectly() throws NoSuchFieldException {
    Mat sample = new Mat(4,4,CvType.CV_8U);
    sample.put(0,0, 1,2,3,4);
    sample.put(1,0, 5,6,7,8);
    sample.put(2,0, 9,10,11,12);
    sample.put(3,0, 13,14,15,16);

    List<float[]> samples = new ArrayList<>();
    List<float[]> labels = new ArrayList<>();

    FieldSetter.setField(ann, ann.getClass().getDeclaredField("samples"), samples);
    FieldSetter.setField(ann, ann.getClass().getDeclaredField("labels"), labels);

    ann.addSample(sample, 1);

    Assert.assertEquals(1, samples.size());
    Assert.assertEquals(1, labels.size());


    Mat actualSample = new MatOfFloat(samples.get(0));

    assert2dMatEquals1dMat(sample, actualSample.t());

    float[] actualLabel = labels.get(0);
    // Assert that the labels are formatted correctly to the output layer
    // -1 = false
    //  1 = true
    Assert.assertTrue(-1f == actualLabel[0]);
    Assert.assertTrue(1f == actualLabel[1]);
    Assert.assertTrue(-1f == actualLabel[2]);
    Assert.assertTrue(-1f == actualLabel[3]);
    Assert.assertTrue(-1f == actualLabel[4]);
    Assert.assertTrue(-1f == actualLabel[5]);

    // Assert that there are as many labels in the label array as there are output nodes
    Assert.assertTrue(6 == actualLabel.length);
  }

  @Test
  public void ShouldBeAbleToAddMultipleItemsTooAddSample() throws NoSuchFieldException {
    int amount = 14;

    Mat sample = new Mat(4,4, CvType.CV_8U);

    List<float[]> samples = new ArrayList<>();
    List<float[]> labels = new ArrayList<>();

    FieldSetter.setField(ann, ann.getClass().getDeclaredField("samples"), samples);
    FieldSetter.setField(ann, ann.getClass().getDeclaredField("labels"), labels);

    for(int i = 0; i < amount; i++) {
      ann.addSample(sample, i%6);
    }


    Assert.assertEquals(amount, samples.size());
    Assert.assertEquals(amount, labels.size());
  }

  @Test
  public void ShouldBeAbleToSaveATrainedModel() {
    when(ann_mlp.isTrained()).thenReturn(true);
    doNothing().when(ann_mlp).save(isA(String.class));

    ann.save("nothing.exe");

    verify(ann_mlp).isTrained();
    verify(ann_mlp).save("nothing.exe");
  }

  @Test
  public void ShouldNotBeAbleToSaveAUntrainedModel() {
    when(ann_mlp.isTrained()).thenReturn(false);
    doNothing().when(ann_mlp).save(isA(String.class));

    ann.save("nothing.exe");

    verify(ann_mlp).isTrained();
    verify(ann_mlp, times(0)).save("nothing.exe");
  }

  @Test
  public void ShouldBeAbleToPredictOnTrainedModel() {
    Mat sample = new Mat(4,4,CvType.CV_8U);
    sample.put(0,0, 1,2,3,4);
    sample.put(1,0, 5,6,7,8);
    sample.put(2,0, 9,10,11,12);
    sample.put(3,0, 13,14,15,16);

    Mat predictSample = new Mat(4,4,CvType.CV_8U);
    predictSample.put(0,0, 11,22,33,44);
    predictSample.put(1,0, 55,66,77,88);
    predictSample.put(2,0, 99,110,111,112);
    predictSample.put(3,0, 113,114,115,116);

    ann.addSample(sample, 1);
    ArgumentCaptor<Mat> argument = ArgumentCaptor.forClass(Mat.class);
    ann.train();

    ann.predict(predictSample);

    verify(ann_mlp).predict(argument.capture(), any(MatOfFloat.class), eq(0));

    Mat parsedSample = argument.getValue();

    assert2dMatEquals1dMat(predictSample, parsedSample);

  }

  @Test
  public void ShouldBeAbleToTrainAModel() {
    ann_mlp.setTermCriteria(new TermCriteria(
        TermCriteria.MAX_ITER,
        1,
        0.1
    ));
    ArgumentCaptor<TrainData> argument = ArgumentCaptor.forClass(TrainData.class);
    Mat sample = new Mat(4,4,CvType.CV_8U);
    sample.put(0,0, 1,2,3,4);
    sample.put(1,0, 5,6,7,8);
    sample.put(2,0, 9,10,11,12);
    sample.put(3,0, 13,14,15,16);

    ann.addSample(sample, 1);

    ann.train();

    verify(ann_mlp).train(argument.capture());

    TrainData traindata = argument.getValue();

    assert2dMatEquals1dMat(sample, traindata.getSamples());

    Mat actualLabel = traindata.getResponses();

    Assert.assertTrue(-1f == actualLabel.get(0,0)[0]);
    Assert.assertTrue(1f == actualLabel.get(0,1)[0]);
    Assert.assertTrue(-1f == actualLabel.get(0,2)[0]);
    Assert.assertTrue(-1f == actualLabel.get(0,3)[0]);
    Assert.assertTrue(-1f == actualLabel.get(0,4)[0]);
    Assert.assertTrue(-1f == actualLabel.get(0,5)[0]);
  }

  private void assert2dMatEquals1dMat(Mat matIn2d, Mat matIn1d) {
    for(int y = 0; y < matIn2d.rows(); y++) {
      for (int x = 0; x < matIn2d.cols(); x++) {
        Assert.assertEquals(
            matIn2d.get(y,x)[0],
            matIn1d.get(0,y * matIn2d.cols() + x)[0],
            0
        );
      }
    }

  }
}
