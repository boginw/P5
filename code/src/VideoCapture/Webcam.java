package VideoCapture;

import java.io.IOException;

public interface Webcam {
  byte[] getFrame();
  int nextFrame() throws IOException;
  void close() throws IOException;
}
