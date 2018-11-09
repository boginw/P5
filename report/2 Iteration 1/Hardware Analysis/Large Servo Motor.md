## EV3 Large Servo Motor

The vehicle to be built is required to drive; hence a motor or two are required. The Lego Group provides two types of motors with its Lego Mindstorms set[@mindstorm_set], and to achieve higher speeds with the vehicle, the larger motors, EV3 Large Servo Motor[@large_servo_motor], will be used for driving since these motors are the largest available for the group.

According to Lego, the EV3 Large Servo Motor is a powerful motor which uses tacho feedback (tachometer) which is an instrument that measures the rotation speed of the motor. This tachometer allows for precise control which Lego claims is in one degree of accuracy. Lego also claims that motors have some intelligence that allows for aligning motors together, such that they would be able to drive in a straight line at the same speed[@large_servo_motor].

### Specifications

These specifications were obtained from the product page of EV3 Large Servo[@large_servo_motor] and filtered such that only the relevant specifications for the vehicle remain.

- 160-170 RPM (Rounds per minute)
- Running torque of 20 N.cm
- Stall torque of 40 N.cm

### Hypothesis

As the motors provided in the set are brand new, it is assumed that they will perform close specification provided by Lego.

### Methodology for the experiments

Each of the following experiments is to be repeated three times in an attempt to eliminate any abnormalities.

#### Speed (RPM)

As tachometers are expensive, a rotary encoder can be used, in conjunction with an Arduino Micro Processor to measure revolutions. The EV3 Large servo motor is then attached to a rotary encoder in such a way that the motor spins freely. For each revolution of the motor, the rotary encoder also revolves once. Given the number of steps in the rotary encoder, a simple program can then determine when the motor has revolved.

#### Torque

To test the torque of the motors a Prony brake is to be used. As can be seen on figure @fig:prony, two wooden planks are clamped on the motor, in such a way that the planks extend out to lay on a scale. The motor is then started. While the motor is running, the planks are tightened until the motor cannot rotate anymore. At this point, we note the weight on the scale. The length from the center of the motor, to the end of the planks, in conjunction with the weight noted on the scale, can then be used to determine torque.

![Illustration of Prony Brake](report/assets/pictures/prony.png){#fig:prony}

#### 2nd Torque

Due to issues that will be described later, a verification of the first torque test was needed. 
First, a LEGO wheel rim with a radius of 1,5cm is acquired, then a wire is attached to the said wheel rim, in such a way that when the rim rotates the wire is wrapped around the rim.

Afterward, the rim is attached to the motor, so that when the motor rotates, the rim rotates. At the end of the wire, a container is attached. The motor is then set to rotate, and by doing so, it is lifting the container. After the container has been lifted close to the rim, the container is then lowered again, and some extra weight is put in the container.

The lifting, lowering, and adding weights is repeated until the motor cannot lift the weight of the container and extra weights, at that point the rim is detached from the motor put on a scale, along with the wire, container, and weights.

Given the radius as $A$, the weight as $W$, and the gravitational acceleration as $g$, the torque can be calculated as seen in +@eq:torqueFormula2.

$$ \tau = A \times W \times g $$ {#eq:torqueFormula2}

### Results

#### Speed (RPM)

The results from the test can be seen in +@tbl:motorRPM. The table shows each motors speed at increments of 20%.

+---------------+--------+--------+--------+--------+--------+
| Motor / Speed |    20% |    40% |    60% |    80% |   100% |
+===============+========+========+========+========+========+
| 1st Motor     |  34,48 |  68,08 | 101,39 | 135,31 | 156,06 |
+---------------+--------+--------+--------+--------+--------+
| 2nd Motor     |  34,50 |  69,01 | 101,71 | 137,83 | 151,14 |
+---------------+--------+--------+--------+--------+--------+

Table: RPM Test results {#tbl:motorRPM}

The percentage difference is shown +@eq:rpmPercent1 and +@eq:rpmPercent2.

$$ \frac{156,06-160}{160} = -2,4625\% $$ {#eq:rpmPercent1}

$$ \frac{151,14-160}{160} = -5,5375\% $$ {#eq:rpmPercent2}

#### Torque

The gravitational acceleration ($g$) of 9,82 is used. The arm used ($A$) was 17 cm in length, the max weight ($W$) was 0,12 Kg. 

$$ \tau = A \times W \times g = 20,03 \text{N}\cdot \text{cm} $$

The percentage difference is shown +@eq:torquePercent1.

$$ \frac{20,03-40}{40} = -49,925\% $$ {#eq:torquePercent1}

#### 2nd Torque

The gravitational acceleration ($g$) of 9,82 is used. The arm used ($A$) was 1,5 cm in length, the max weight ($W$) was 1.268 Kg. 

$$ \tau = A \times W \times g = 18,68 \text{N}\cdot \text{cm} $$

The percentage difference is shown +@eq:torquePercent2.

$$ \frac{18,68-40}{40} = -53,3\% $$ {#eq:torquePercent2}

### Conclusion

Taking the lower bound of the range 160-170 RPM which LEGO provided, the speed test showed that the motors used were close to the specifications LEGO provided. 

LEGO claims the motor has 40 Ncm in stall torque and 20 Ncm in running torque. As far as my understanding goes, the experiments performed should test stall torque, and I'm getting half of what's expected, are there any blatant faults in my tests?