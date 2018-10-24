package VideoCapture;

import java.io.IOException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class OpenCVWebcam implements Webcam {

  private int w;
  private int h;
  private Mat mat;
  private VideoCapture webcam;

  public OpenCVWebcam(int w, int h) {
    this.w = w;
    this.h = h;
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    this.mat = new Mat();
    this.webcam = new VideoCapture(0);
  }

  @Override
  public byte[] getFrame() {
    MatOfByte buf = new MatOfByte(this.mat);
    return buf.toArray();
  }

  @Override
  public int nextFrame() throws IOException {
    return (this.webcam.read(mat)) ? 1 : 0;
  }

  @Override
  public void close() throws IOException {
  this.webcam.release();
  }

  private void _init() {
    this.webcam.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, this.w);
    this.webcam.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, this.h);
    this.webcam.open(0);
  }
}
