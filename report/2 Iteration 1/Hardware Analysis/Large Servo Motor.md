## EV3 Large Servo Motor
The vehicle to be built is required to drive, hence a motor or two are required. The Lego Group provides two types of motors with their Lego Mindstorm set[@mindstorm_set], and to achieve higher speeds with the vehicle, the larger motors, EV3 Large Servo Motor[@large_servo_motor], will be used for drive.

According to Lego the EV3 Large Servo Motor is a powerful motor they which uses tacho feedback (tachometer) which is a instrument that measures the rotation speed of the motor. This allows for precise control which Lego claims is in one degree of accuracy. Lego also claims that motors have some intelligence that allows for aligning motors together, such that they would be able to drive in a straight line at the same speed[@large_servo_motor].

### Specifications
These specifications where fetched from the product page of EV3 Large Servo[@large_servo_motor] and filtered such that only the relevant specifications for the vehicle remain.

- 160-170 RPM (Rounds per minute)
- Running torque of 20 N.cm
- Stall torque of 40 N.cm

### Hypothesis
As the motors provided in the set are brand new, it is assumed that they will perform better than the specification provided by Lego, this assumption is based on the presumption that the specifications provided is created conservatively. It is therefore assumed that the actual measurements for torque and RPM are about between 5-10% higher than what the specification states.

As the power-level of the battery most likely affects the specifications, it is assumed that the specifications given by Lego are based on tests with an external power source and not the included battery. Albeit the power source is expected to equal the output of the included battery at maximum charge. It is therefore presumed that different power levels yield different measurements, including differing torque and RPM.

The included battery is rated at 7.4V, but the EV3 also takes six standard AA batteries, which are rated at 1.5V, which results in 9V in serial. It is therefore assumed, that given a higher voltage, the measurements of both torque and speed, would be higher than the given specifications.

### Methodology for experiment
Each of the following experiments is to be repeated 3 times in an attempt to eliminate any abnormalities. All experiments sensitive to power are started with a fully charged battery.

#### Speed (RPM)
As tachometers are expensive, a rotary encoder can be used, in conjunction with an Arduino Micro Processor to measure revolutions. The EV3 Large servo motor is then attached to an rotary encoder in such a way that the motor spins freely. For each revolution of the motor, the rotary encoder also revolves once. Given the number of steps in the rotary encoder, a simple program can then determine when the motor has revolved.

##### Affects of power
The above-mentioned method, is to be repeated on the EV3 official battery of 7.4V and on 6 $\times$ 1.5V AA batteries. This will determine if an increase in voltage has any effect on the RPM of the motor.

#### Torque
To test the torque of the motors a Prony brake is to be used. Two wooden planks are clamped on the motor, in such a way that the planks extend out to lay on a scale. The motor is then started. Whilst the motor is running, the planks are tightened until the motor cannot rotate anymore. At this point we note the weight on the scale. The length from the center of the motor, to the end of the planks, in conjunction with the weight noted on the scale, can then be used to determine torque.

##### Affects of power
The above-mentioned method, is to be repeated on the EV3 official battery of 7.4V and on 6 $\times$ 1.5V AA batteries. This will determine if an increase in voltage has any effect on the torque of the motor.

#### Battery life
Run the car until the battery is empty

#### Camera
Camera tests here

##### Resolution
Resolution tests here

##### Lighting
Lighting tests here

### Notes from experiment

### Conclusion

