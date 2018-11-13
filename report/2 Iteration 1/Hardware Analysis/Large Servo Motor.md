## EV3 Large Servo Motor

The vehicle to be built is required to drive; hence a motor or two are required. The LEGO Group provides two types of motors with its LEGO Mindstorms set[@mindstorm_set], and to achieve higher speeds with the vehicle, the larger motors, EV3 Large Servo Motor[@large_servo_motor], will be used for driving since these motors are the largest available for the group.

According to LEGO, the EV3 Large Servo Motor is a powerful motor which uses tacho feedback (tachometer) which is an instrument that measures the rotation speed of the motor. This tachometer allows for precise control which LEGO claims is within one degree of accuracy. LEGO also claims that motors have some intelligence that allows for aligning motors together, such that they would be able to drive in a straight line at the same speed[@large_servo_motor].

### Specifications

These specifications were obtained from the product page of EV3 Large Servo[@large_servo_motor] and filtered such that only the relevant specifications for the vehicle remain.

- 160-170 RPM (Rounds per minute)
- Running torque of 20 N.cm
- Stall torque of 40 N.cm

### Hypothesis

As the motors provided in the set are brand new, it is assumed that they will perform close to the specification provided by LEGO. It is also assumed that the RPM of the motor has a linear growth with the power level provided to the motors by the EV3 brick. 

### Methodology for the experiments

The EV3 brick will be programmed using the LEGO programming software for LEGO Mindstorms in these tests.

#### Speed (RPM)

The speed test is to be conducted using an Arduino and a rotary encoder. It is essential that the rotary encoder be an incremental encoder. An incremental rotary encoder has a set of steps in a full rotation, and every step produces a digital output. To calculate how many rotations is as simple as counting the steps up until the number of steps per rotation and then increment the number of rotations. Then the Arduino script takes the number of rotations and divides it with the amount of time passed in minutes.

The LEGO Medium Motor is to be attached to the rotary encoder using a custom designed LEGO block, which attaches the encoder in the center and the motor at two points on each side.

The Motor is then set to run at speed intervals of 20% power level, i.e., 20%, 40%, 60%, 80%, 100%. Between every run, the battery of the EV3 should stay fully recharged. A run has a duration of 5 minutes per speed interval and the average RPM after the 5 minutes is the result. As to why the motor is tested at different intervals, this is needed to show whether by increasing the motor power level shows a linear or exponetial growth.

#### Torque

To test the torque of the motors a Prony brake is to be used. As can be seen on figure @fig:prony, two wooden planks are clamped on the motor, in such a way that the planks extend out to lay on a scale. The motor is then started. While the motor is running, the planks are tightened until the motor cannot rotate anymore. At this point, we note the weight on the scale. The length from the center of the motor, to the end of the planks, in conjunction with the weight noted on the scale, can then be used to determine torque.

![Illustration of Prony Brake](report/assets/pictures/prony.png){#fig:prony}

#### 2nd Torque

Due to issues that will be described later, a verification of the first torque test was needed. 
First, a LEGO wheel rim with a radius of 1,5cm is acquired, then a wire is attached to the said wheel rim, in such a way that when the rim rotates the wire is wrapped around the rim.

Afterward, the rim is attached to the motor, so that when the motor rotates, the rim rotates. At the end of the wire, a container is attached. The motor is then set to rotate, and by doing so, it is lifting the container. After the container has been lifted close to the rim, the container is then lowered again, and some extra weight is put in the container.

The lifting, lowering, and adding weights is repeated until the motor cannot lift the weight of the container and extra weights, at which point the rim is detached from the motor put on a scale, along with the wire, container, and weights.

Given the radius as $A$, the weight as $W$, and the gravitational acceleration as $g$, the torque can be calculated as seen in +@eq:torqueFormula2.

$$ \tau = A \times W \times g $$ {#eq:torqueFormula2}

### Results

In this section the results from the tests are described.

#### Speed (RPM)

The results from the test can be seen in +@tbl:motorRPM. The table shows each motor's speed at increments of 20%.

Table: RPM Test results {#tbl:motorRPM} \label{test}

+---------------+--------+--------+--------+--------+--------+
| Motor / Speed |    20% |    40% |    60% |    80% |   100% |
+===============+========+========+========+========+========+
| 1st Motor     |  34,48 |  68,08 | 101,39 | 135,31 | 156,06 |
+---------------+--------+--------+--------+--------+--------+
| 2nd Motor     |  34,50 |  69,01 | 101,71 | 137,83 | 151,14 |
+---------------+--------+--------+--------+--------+--------+

Taking the lower bound of the 160 - 170 RPM from the specificaton, these motors look to be close to it. The percentage difference is shown in +@eq:rpmPercent1 and +@eq:rpmPercent2.

$$ \frac{156,06-160}{160} = -2,4625\% $$ {#eq:rpmPercent1}

$$ \frac{151,14-160}{160} = -5,5375\% $$ {#eq:rpmPercent2}

As shown in @fig:rpmFig, which plots a graph of +@tbl:motorRPM, a clear linear growth is observed.

![The Motors RPM](report/assets/pictures/motor_rpm.png){#fig:rpmFig}

#### Torque

The gravitational acceleration ($g$) of 9,82 is used. The arm used ($A$) was 17 cm in length, the max weight ($W$) was 0,12 Kg.

$$ \tau = A \times W \times g = 20,03 \text{N}\cdot \text{cm} $$

The percentage difference is shown in +@eq:torquePercent1.

$$ \frac{20,03-40}{40} = -49,925\% $$ {#eq:torquePercent1}

#### 2nd Torque

The gravitational acceleration ($g$) of 9,82 is used. The arm used ($A$) was 1,5 cm in length, the max weight ($W$) was 1.268 Kg.

$$ \tau = A \times W \times g = 18,68 \text{N}\cdot \text{cm} $$

The percentage difference is shown in +@eq:torquePercent2.

$$ \frac{18,68-40}{40} = -53,3\% $$ {#eq:torquePercent2}

### Conclusion

Taking the lower bound of the range 160-170 RPM which LEGO provided, the speed test showed that the motors used were close to the specifications LEGO provided. With the average deviation, our motors are around 4% off what LEGO claims, but this is an acceptable margin of error. The motors also show a linear growth, which enables easy predictions for the actual RPM given the power level provided.

The torque tests on the other hand show completely different results. LEGO claims the motors have 40 N.cm in stall torque and 20 N.cm in running torque, and to the best of the group's knowledge, our tests tested the stall torque, which only shows the motors getting to half of what LEGO claims. It should be noted, that our tests only tested the motors at their maximum speeds, and it is unknown what their stall torque is at lower levels. Acknowledging the oversight in our test, the group choose not to proceed with further tests. If a problem should arise due to the oversight, new torque tests with the different speed levels will be performed.

These tests show that the motors have a torque of 20 N.cm, a linear growth in RPM per power level, and match the RPM specifications of roughly 160 RPM. The tests also show a deficit in torque in the motors, compared to the specifications. These findings will be used in later decisions regarding the design of the car.
