package dk.sw502e18.ssr;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;

public class SSRTrainerTest {

  private TemporaryFolder trainData = new TemporaryFolder();
  private TemporaryFolder testData = new TemporaryFolder();
  private TemporaryFolder paramsData = new TemporaryFolder();

  @Before
  public void setUp() throws IOException {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    trainData.create();
    testData.create();
    paramsData.create();

    trainData.newFolder("00000");
    trainData.newFolder("00001");
    trainData.newFolder("00002");
    trainData.newFolder("00003");
    trainData.newFolder("00004");
    trainData.newFolder("00005");

    testData.newFolder("00000");
    testData.newFolder("00001");
    testData.newFolder("00002");
    testData.newFolder("00003");
    testData.newFolder("00004");
    testData.newFolder("00005");


    trainData.newFile("/00000/train.ppm");
    trainData.newFile("/00001/train.ppm");
    trainData.newFile("/00002/train.ppm");
    trainData.newFile("/00003/train.ppm");
    trainData.newFile("/00004/train.ppm");
    trainData.newFile("/00005/train.ppm");

    testData.newFile("/00000/test.ppm");
    testData.newFile("/00001/test.ppm");
    testData.newFile("/00002/test.ppm");
    testData.newFile("/00003/test.ppm");
    testData.newFile("/00004/test.ppm");
    testData.newFile("/00005/test.ppm");

  }

  @Test
  public void canTrainOnASingleConfiguration() throws IOException {

    File config = paramsData.newFile("test.csv");

    Configuration[] configs = new Configuration[]{
        Configuration.fromString("1;0.1;0.1;1;0.1;0.1;1500;0.005;2;6;20")
    };

    writeConfigsToFile(configs, config);

    mockSSRTrainer ssr = new mockSSRTrainer(
        trainData.getRoot().getPath(),
        testData.getRoot().getPath(),
        config.getPath()
    );

    ssr.train();

    // We call 12 times, 6 times for training, 6 timers for test
    verify(ssr.spyEP, atMost(12)).detect(any(File.class));

    ANN test = ssr.spyNns.get(0);

    // We call 6 times for training.
    verify(test, atMost(6)).addSample(any(), anyInt());

    // We call 6 times for testing.
    verify(test, atMost(6)).addTestSample(any(), anyInt());

    verify(test).train();

    verify(test).calcError();

  }

  @Test
  public void canTrainOnMultipleConfigurations() throws IOException {
    File config = paramsData.newFile("test.csv");

    Configuration[] configs = new Configuration[]{
        Configuration.fromString("1;0.1;0.1;1;0.1;0.1;1500;0.005;2;6;20"),
        Configuration.fromString("2;0.2;0.2;2;0.2;0.2;1;0.5;2;6;20"),
        Configuration.fromString("3;0.3;0.3;3;0.3;0.3;1900;0.8;2;6;20")
    };

    writeConfigsToFile(configs, config);

    mockSSRTrainer ssr = new mockSSRTrainer(
        trainData.getRoot().getPath(),
        testData.getRoot().getPath(),
        config.getPath()
    );

    ssr.train();

    // We call 12*3 times, 6 times for training, 6 timers for test and 3 configurations
    verify(ssr.spyEP, atMost(12*3)).detect(any(File.class));
  }

  private void writeConfigsToFile(Configuration[] configs, File file) throws IOException {

    Assert.assertTrue(file.canWrite());
    Assert.assertTrue(configs.length > 0);

    FileWriter fw = new FileWriter(file);

    for(int i= 0; i < configs.length; i++){
      fw.write(configs[i].serialize()+"\n");
    }

    fw.close();

  }


  private class mockSSRTrainer extends SSRTrainer{

    public EllipseProcessor spyEP;
    public List<ANN> spyNns;

    public mockSSRTrainer(String train, String test, String param) {
      super(train, test, param);
    }

    @Override
    protected EllipseProcessor ellipseProcessorBuilder(int minWH, Size size){
      spyEP = Mockito.spy(new EllipseProcessor(minWH, size));

      CircleCropper cc = Mockito.spy(new CircleCropper());

      Mat theReturn = new Mat(2,2, CvType.CV_8U);
      theReturn.put(0,0, 1,2);
      theReturn.put(1,0, 3,4);

      doReturn(theReturn).when(cc).crop(any(), any(), any());

      try {
        FieldSetter.setField(spyEP, spyEP.getClass().getDeclaredField("cc"), cc);
      }catch (Exception ignore){

      }



      doReturn(theReturn).when(spyEP).detect(any(File.class));

      return spyEP;
    }

    @Override
    protected ANN ANNBuilder(String line){
      if(spyNns == null){
        spyNns = new ArrayList<>();
      }
      ANN ann = Mockito.spy(new ANN(Configuration.fromString(line)));

      spyNns.add(ann);


      doNothing().when(ann).train();
      doReturn(new MatOfFloat()).when(ann).predict(any());
      doReturn(42f).when(ann).calcError();

      return ann;
    }
  }

}
