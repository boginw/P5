### Gearing test
In order to determine the appropriate gearing ratio, the following section will describe the experiment that was done, in order to determine the gearing that will allow for the highest speed in a certain distance, based on the critera in \ref{ssec:DesignCritera}.

#### Methodology
The optimal gearing ratio gives a mixture of high speed and acceleration, in order to reach the highest speed in the shortest amount of time.
When the gearing ratio is increased, the maximum speed will increase, but on the other hand the maximum acceleration will decrease.

The design of the car's body allows for an implementation of gearing ratios 1:1 through 1:6.
By measuring the speed a car has achieved over a given distance, the car with the optimal mix of acceleration and speed will be found.

The car is placed in front of a marker on the ground. 50 cm from the marker is a new marker that defines the area at which the time will be started.
Another 200 cm from the marker at which the time is started, a third marker is placed.
This marks the point at which the time will be stopped.
The time elapsed between these two points is calculated with a slow motion camera that fils in 480 FPS.

#### Results
By analyzing the slow motion video, the speed of the car was obtained. All test results are shown in \ref{ssec:appendix_gearing_test}.
As an example, the first speed of the result with gear ratio 1:3 in \ref{tbl:ratio13}.
First, the time it took for the car to drive the distance is calculated:

$$ \frac{836 F}{480 FPS} = 1.742 seconds $$

Where F is the number of frames that it took the car to drive from the start to the end of the measuring tape, and FPS is the frames per second of the slow motion camera; a constant of 480 frames per second.

The speed is calculated by dividing the time elapsed by the distance driven, which was a constant of 3 meters:

$$ \frac{3 m}{1.742 s} = 1.72 \frac{m}{s} $$

This is trivially converted to $6.20 \frac{km}{h}$.

@Tbl:avg_speed shows the average speed obtained when using different gearing ratios.

| Gear ratio | Average speed [km/h] |
| ---------- | -------------------------------- |
| 1:1        | 2.2                              |
| 1:2        | 4.3                              |
| 1:3        | 6.2                              |
| 1:4        | 6.5                              |
| 1:5        | 6.3                              |
| 1:6        | N/A                              |

: Test results for a gear ratio of 1/3 {#tbl:avg_speed}

As seen in the table, the speed declines, when the gear ratio exceeds 1:5.
This is due to the distance given for acceleration.
If the car would have a longer distance to accelerate on, or if the length of the measurement area had been longer, the higher gear ratio would have won.
The low amount of torque in the car results in a low acceleration, and will thus have a lower speed on the short course.

#### Sources of errors
Different sources of error introduced in the experiments may have an impact on the result.
Even though the car was placed parallel to the measuring tape, it did not finish parallel to the measuring tape.
A sample taken on the 1:3 gear ratio test shows that the car would consistently swerve 12 cm to the right.
This adds extra travel time to the car, and will thus give a lower speed in the results.

A robot that does not have full battery power might not drive at full speed.
In order to counter this, the robot was fully charged before every change of a gear ratio test.
The amount of discharge between each run might have an impact on the average speed, but in that case, a decrease in speed is expected.
This is not the case, and as thus, this source of error does not seem plausible.

### Implementation in the car
The gear ratio of 1:5 is chosen, as that gives the best mix of speed and acceleration.

One of the sources of error introduced was the swerving of the car.
In order to combat this behavior, the wheels were replaced by some smaller and wider wheels.
This gave a decrease in speed of 22%, but practically eliminated the swerve over a course of 10 meters.
