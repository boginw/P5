import VideoCapture.VideoCapture;
import java.io.IOException;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.video.YUYVImage;
import lejos.robotics.RegulatedMotor;

public class runner {
  public static void main(String[] args) {
    try {
      VideoCapture webcam = new VideoCapture(160, 120);

      YUYVImage img = new YUYVImage(webcam.getFrame(), webcam.getWidth(), webcam.getHeight());

      GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
      final int SW = g.getWidth();
      final int SH = g.getHeight();
      g.setFont(Font.getSmallFont());

      EV3LargeRegulatedMotor motor1 = new EV3LargeRegulatedMotor(MotorPort.A);
      EV3LargeRegulatedMotor motor2 = new EV3LargeRegulatedMotor(MotorPort.B);

      motor1.synchronizeWith(new EV3LargeRegulatedMotor[]{motor2});

      motor1.startSynchronization();
      motor1.setSpeed(motor1.getMaxSpeed());
      motor2.setSpeed(motor2.getMaxSpeed());
      motor1.forward();
      motor2.forward();
      motor1.endSynchronization();

      while (!Button.ESCAPE.isDown()) {
        webcam.nextFrame();
        img.display(g, 0, 0, 150);
      }
      webcam.close();
      g.clear();
    }catch (IOException ioe) {
      ioe.printStackTrace();
      System.out.println("Driver exception: " + ioe.getMessage());
    }
  }


}
