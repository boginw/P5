import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class runner {
  public static void main(String[] args) {
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

      }
  }
}
