### Gearing test {#sec:gearingTest}
As described in [@sec:DesignCritera] optimally the car will drive as fast as possible within a limited range, and as such, it is worth exploring different gearing ratios as these can increase the velocity. This section will, therefore, describe an experiment for determining the best gearing ratio for the car given the previous limitations.

#### Methodology

The design of the body of the car allows for an implementation of gearing ratios 1:1 through 1:6.
By creating a gearing for each gearing ratio compatible with the car, the best gearing ratio can be determined by implementing each gearing on the car and measuring the time the car takes to drive a certain distance. The gearing ratio that required the least amount of time to drive the given distance, is the optimal solution.  

Placing a marker at the start of the track to be driven on, and one at the end determines the track to be driven. The distance between the markers defines the length of the track. The car is then to drive the full length of the track, starting 50 cm before the first marker, to allow the car to reach its maximum velocity.
The distance between the markers is chosen as 200 cm, as this distance is deemed reasonable for the car to recognize a speed sign within.

#### Results {#sec:gearingTestResults}

The speed is calculated by dividing the time elapsed by the distance driven, which was a constant of 2 meters.


@Tbl:avg_speed shows the average speed obtained when using different gearing ratios.

| Gear ratio | Average speed [km/h] |
| ---------- | -------------------------------- |
| 1:1        | 2.2                              |
| 1:2        | 4.3                              |
| 1:3        | 4.3                              |
| 1:4        | 5.0                              |
| 1:5        | 5.9                              |
| 1:6        | 4.8                              |

: Average speed for different gear ratio {#tbl:avg_speed}

As can be seen in table @tbl:avg_speed the peak of the curve is 1:5, after which the speed begins to decline due to the first 50 cm not being enough for the car to reach its maximum velocity, and as such the car uses the first parts of the 200 cm to accelerate.

### Implementation in the car
The gear ratio of 1:5 is chosen, as, according to @tbl:avg_speed, that gives the highest speed in the given distance.

One of the sources of error introduced was the swerving of the car.
In order to combat this behavior, the wheels were replaced by some smaller and wider wheels.
This gave a decrease in speed of 22% (since the ratio between the wheel and wheel axle is a gearing in it self), but eliminated the swerve.
