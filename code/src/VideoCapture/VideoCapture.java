package VideoCapture;

import java.io.IOException;

public class VideoCapture {
  private Webcam webcam;

  private int w;
  private int h;

  public VideoCapture(int w, int h) throws IOException {
    this.w = w;
    this.h = h;
    webcam = new StandardWebcam(this.w,this.h);
  }

  public byte[] getFrame() {
     return webcam.getFrame();
  }

  public int nextFrame() throws IOException {
    return webcam.nextFrame();
  }

  public void close() throws IOException {
    webcam.close();
  }

  public int getWidth() {
    return this.w;
  }

  public int getHeight() {
    return this.h;
  }
}
