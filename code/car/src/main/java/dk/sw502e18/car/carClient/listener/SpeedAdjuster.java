package dk.sw502e18.car.carClient.listener;

import dk.sw502e18.car.carClient.CarClientMessageListener;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class SpeedAdjuster implements CarClientMessageListener {
    private RegulatedMotor mC;
    private RegulatedMotor mD;
    private float maxVal;
    private float maxSpeed;

    public SpeedAdjuster(float maxVal) {
        this.maxVal = maxVal;
        mC = new EV3LargeRegulatedMotor(MotorPort.C);
        mD = new EV3LargeRegulatedMotor(MotorPort.D);
        mD.synchronizeWith(new RegulatedMotor[]{mC});

        maxSpeed = (mC.getMaxSpeed() > mD.getMaxSpeed()) ? mD.getMaxSpeed() : mC.getMaxSpeed();
    }

    @Override
    public void onMessage(String message) {
        float p = Integer.valueOf(message) / maxVal;

        mD.startSynchronization();
        mC.setSpeed((int) Math.ceil(maxSpeed * p));
        mD.setSpeed((int) Math.ceil(maxSpeed * p));
        mC.forward();
        mD.forward();
        mD.endSynchronization();

    }
}
