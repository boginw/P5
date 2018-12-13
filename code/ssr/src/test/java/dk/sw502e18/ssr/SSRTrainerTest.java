package dk.sw502e18.ssr;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;
import org.opencv.core.Core;
import org.opencv.core.Mat;
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
        Configuration.fromString("1;0.1;0.1;1;0.1;0.1;1500;0.005;20;6;20")
    };

    writeConfigsToFile(configs, config);

    mockSSRTrainer ssr = new mockSSRTrainer(
        trainData.getRoot().getPath(),
        testData.getRoot().getPath(),
        config.getPath(),
        new int[]{10, 20, 30, 40}
    );

    ssr.train();

    //verify(ssr.spyEP).detect(any(File.class));


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

    public mockSSRTrainer(String train, String test, String param, int[] signs) {
      super(train, test, param, signs);
    }

    @Override
    protected EllipseProcessor ellipseProcessorBuilder(int thresh, int minWH, Size size){
      spyEP = Mockito.spy(new EllipseProcessor(thresh, minWH, size));


      return spyEP;
    }
  }

}
