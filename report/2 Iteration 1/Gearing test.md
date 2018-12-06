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
The time elapsed between these two points is calculated by recording the individual test runs with a slow motion camera filming in 480 frames per second (FPS), and then counting the amount of frames between the car passing the second and third marker.

#### Results
By analyzing the slow motion video, the speed of the car was obtained. All test results are shown in @section:appendix_gearing_test.
As an example, the result of the first test run with gear ratio 1:3 (as seen in @tbl:ratio13) was analysed in the following way:
First, the time it took for the car to drive the distance is calculated in @eq:TimeElapsed:

$$ \frac{836 F}{480 FPS} = 1.742 seconds $$ {#eq:TimeElapsed}

Where F is frames, and FPS is frames per second; a constant of 480 frames per second.

The speed is calculated by dividing the time elapsed by the distance driven, which was a constant of 2 meters, as seen in @eq:MetersSecond:

$$ \frac{3 m}{1.742 s} = 1.72 \frac{m}{s} $$ {#eq:MetersSecond}

This is trivially converted to $6.20 \frac{km}{h}$.

@Tbl:avg_speed shows the average speed obtained when using different gearing ratios.

| Gear ratio | Average speed [km/h] |
| ---------- | -------------------------------- |
| 1:1        | 2.2                              |
| 1:2        | 4.3                              |
| 1:3        | 4.3                              |
| 1:4        | 5.0                              |
| 1:5        | 5.9                              |
| 1:6        | 4.8                              |

: Test results for a gear ratio of 1/3 {#tbl:avg_speed}

As seen in table @tbl:avg_speed, the speed declines, when the gear ratio exceeds 1:5.
This is due to the distance given for acceleration.
If the car would have a longer distance to accelerate on, or if the length of the measurement area had been longer, the higher gear ratio would have been more favorables.
The low amount of torque in the car results in a low acceleration, and will thus have a lower speed on the short course.

### Implementation in the car
The gear ratio of 1:5 is chosen, as, according to @tbl:avg_speed that gives the best mix of speed and acceleration.

One of the sources of error introduced was the swerving of the car.
In order to combat this behavior, the wheels were replaced by some smaller and wider wheels.
This gave a decrease in speed of 22% (since the ratio between the wheel and wheel axle is a gearing in it self), but practically eliminated the swerve over a course of 10 meters.
