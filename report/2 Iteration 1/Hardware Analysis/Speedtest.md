## Gearing test
In order to determine the appropriate gearing ratio, the following section will describe the experiment that was done, in order to determine the gearing that will allow for the highest speed.

### Methodology
In order to measure the speed of the car, the following experiment was conducted.
The car was placed 40 cm's in front of a three meters long measuring tape. A program setting the car's speed to full was started, and was terminated when the car reached the end of the measuring tape.
Everything was filmed with a slow motion camera that films 480 FPS, and afterwards analyzed in order to determine the number of frames spent traveling from one end of the measuring tape to the other.
The timing started when the front of the car reached the start of the measuring tape, and stopped when the front of the car reached the end of the measuring tape.
This experiment was repeated five times for gearings with ratio: 1/1, 1/2, 1/3, 1/4, and 1/5.

### Results
By analyzing the slow motion video, the speed of the car was obtained. All test results is show in appendix X.
To calculate the results, the first entry is calculated, as an example.
First, the time it took for the car to drive the distance is calculated:

$$ frac{836 F}{480 FPS} = 1.742 seconds $$

Where F is the number of frames that it took the car to drive from the start to the end of the measuring tape, and FPS is the frames per second of the slow motion camera; a constant of 480 frames per second.

The speed is calculated by dividing the time elapsed by the distance driven, which was a constant of 3 meters:

$$ frac{3 m}{1.742 s} = 1.72 frac{m}{s} $$

This is trivially converted to $ 6.20 frac{km}{h} $.

@Tbl:avg_speed shows the average speed obtained when using different gearing ratios.

| Gear ratio | Average speed [$ frac{km}{h} $] | 
| ---------- | ------------------------------- |
| 1/1        | 2.2                             |
| 1/2        |                                 |
| 1/3        | 6.2                             |
| 1/4        |                                 |
| 1/5        | N/A                             |

: Test results for a gear ratio of 1/3 {#tbl:avg_speed}

When the obtained speed is bigger, there is more room for differentiating the speed levels, and distinguishing them, as demanded by the design criteria.
As such, the gear ratio of 1/4 is deemed as the most suitable.

### Sources of errors
Different sources of error introduced in the experiments may have an impact on the result.
Even though the car was placed parallel to the measuring tape, it did not finish parallel to the measuring tape.
Over the course of all tests, the car would consistently swerve 12 cm to the right.
This adds extra travel time to the car, and will thus give a lower speed in the results.

A robot that does not have full battery power might not drive at full speed. 
In order to counter this, the robot was fully charged before every change of a gear ratio test.
The amount of discharge between each run might have an impact on the average speed, but in that case, a decrease in speed is expected.
This is not the case, and as thus, this source of error does not seem plausible.