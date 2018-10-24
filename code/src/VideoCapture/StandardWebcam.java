package VideoCapture;

import java.io.IOException;
import lejos.hardware.BrickFinder;
import lejos.hardware.video.Video;

public class StandardWebcam implements Webcam {

  private int format;
  private int field;
  private int fps;
  private int w;
  private int h;
  private Video webcam;
  private byte[] frame;

  public StandardWebcam(int w, int h) throws IOException {
    this.w = w;
    this.h = h;
    this.webcam = BrickFinder.getDefault().getVideo();
    this._init();
  }

  public StandardWebcam(int w, int h, int format, int field, int fps) throws IOException {
    this.w = w;
    this.h = h;
    this.format = format;
    this.field = field;
    this.fps = fps;
    this._init();

  }

  public byte[] getFrame() {
    return this.frame;
  }

  public int nextFrame() throws IOException {
    return this.webcam.grabFrame(frame);
  }

  public void close() throws IOException {
    webcam.close();
  }

  private void _init() throws IOException {
    this.webcam = BrickFinder.getDefault().getVideo();
    this.webcam.open(this.w,this.h);
    this.frame = webcam.createFrame();
  }




}
