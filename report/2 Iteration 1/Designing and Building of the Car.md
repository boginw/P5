## Designing and Building of the Car
This section describes how the car was designed. Included is a description of the tools used, which features it brings and then steps through the design criteria to assert that the vehicle fulfills the criteria.

### CAD design
By utilizing LeoCAD[@leocad_website] a car with all the components, except the camera, was designed. By utilizing this tool, disassembly and reassembly become much easier since the steps for assembling are available through the tool. A rendered image of the car can be seen in figure {@fig:cad_car}

![CAD model of the car](report/assets/pictures/cad_car.png){#fig:cad_car}

Through some simple non-formal tests, it was concluded that this design produced a car that drives around 6 km/h, is rigid enough to drive into walls multiple times and can withstand multiple drops from around 50 cm. These results prompted the group to stop the design process as most of the goals had been reached.

### Comparing Design to Design Criteria
This section compares the elements of the design criteria to the design of the vehicle in order to assert that the design complies with the criteria.

#### Components
The design of the car has 114 components (without the camera) which might sound like a lot, but to encompass all the necessary functions, such as rigidness, this was needed. A trade-off to consider is the weight. By shedding some components the weight of the car can be reduced, but as you can read later on, this might not be needed. There might be some optimizations that can be made, but this will not be focused on here.

#### Speed
// Writing initial thoughts. Everything is subject to change. 
Given a car with an appropiate level of speed, we want to know whether or not it is possible to have the car adjust its speed in such a way that it is noticeable by a human. 

Hypothesis
The speed of the car is high enough for a human to notice a 25% and 50% increase or decrease in speed not execeding the top speed. 

Experiment
An empirical test was conducted in two different phases. 
The objective of the first phase was to determine the max. speed of the car in order to have an upper bound as a means for ...
This was done by having the car drive a distance of three meters while measuring the amount of time it took the car.
The objective of the second phase was to have a human observe the car's speed being reduced by 25% and 50%.
Based on the observations it was then decided whether or not a change was noticeable. 

Analysis



Conclusion

// Delete below text. Redefining this whole section. 
With no specific requirement for velocity, a subjective decision is made regarding the fulfillment of the criterion. 
An empirical test was conducted with the purpose of figuring out what the speed of the car actually is.
The test performed was done by having the car drive a distance of three meters while measuring the amount of time it takes the car.
The test was performed with a camera and a Raspberry Pi attached to the car while it was driving because images will be processed as the car is driving. 

To calculate the speed, the formula below was used

> $$speed = \frac{\Delta s}{\Delta t} $$

where s is equal to the distance in meters and t is equal to time in seconds. 

The speed test was conducted ten times in order to get an estimate of the speed of the car on average. 
A video was also captured in slow motion to have another type of measurement to compare with.

The change in space, or distance, was three meters and the change in time was 2.02 seconds on average. Given these measurements, the calculated overall speed is

> $$speed = \frac{3}{2.02} = 1.48 m/s $$

To get this in km/h, 1.48 m/s is multiplied by 3.6 to get 5.33 km/h. 

The video footage that was used as a measure of comparison resulted in 4.67 km/h, which makes the total difference between the ten tests and the video footage .11 km/h. 
This was done to determine whether or not the speed is accurate or not. 

The goal is to reach a reasonable speed level, which has been decided to range from 5 to 10 km/h, as the car is supposed to adjust its speed level based on the speed sign it processes. 

The conclusion, based on the performed tests, is that the car drives approximately 5.33 km/h, which enables adjustment of different speed levels while the car is driving.


#### Weight
As mentioned in section {ref to Components}, some optimizations regarding component count might be possible, but rigidness is also important. As a compromise, only functional components were added to the car, which might sound obvious, but can get hard to design due to Lego's stud system. At the time of writing, the weight of the car and its components have not been measured, but as mentioned earlier, the speed is deemed appropriate, and as such, the weight goal is considered achieved.

#### Rigidness
To fulfill the criterion of rigidness the bottom and front were considered primary areas of stress since these surfaces will be most exposed to crashes and other stresses. 

* The front of the car, as seen in figure {@fig:cad_car_front}, is built to absorb shock coming from the front, which hopefully is dissipated by the time it would reach the driving axle. 
* The bottom of the car is made almost completely solid in order to make it sturdy. The front and back of the bottom are left open, in order to absorb shock from crashes from those sides.

![CAD model of the car, front](report/assets/pictures/cad_car_front.png){#fig:cad_car_front}

![CAD model of the car, back](report/assets/pictures/cad_car_back.png){#fig:cad_car_back}

The rigidness of the car was tested dropping the car approximately 50 cm and by making the car drive into a wall at full speed multiple times. As the car could withstand this criterion was considered fulfilled. 

#### Modular
The car was built with modularity in mind. The camera, programming block, and wheels are easily removed. Unfortunately, the motors are a bit more difficult to remove, since they sit at the base of the car. This was due to the motors being the centerpiece of the build, as this makes a more rigid car.

#### Steering
Steering is not the most important feature, and as such, not a lot of time was spent on it. A simple test showed that the car is turning slightly to the right. The issue has been isolated to the front wheels, but nothing will be done about it since the car drives a great enough distance before turning.

### Results
The purpose of this section was to construct a durable LEGO car based on certain design criteria. The finished design fulfills all criteria to an acceptable extent. Some issues were introduced in the process regarding rigidity, weight, and modularity. The result was a more durable and reliable design. 