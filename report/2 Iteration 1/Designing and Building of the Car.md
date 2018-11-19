## Designing and Building of the Car
This section describes how the car was designed. Included is a description of the tools used, which features it brings and then steps through the design criteria to assert that the vehicle fulfills the criteria.

### CAD design
By utilizing LeoCAD[@leocad_website] a car with all the components, except the camera, was designed. By utilizing this tool, disassembly and reassembly become much easier since the steps for assembling are available through the tool. A rendered image of the car can be seen in figure {@fig:cad_car}

![CAD model of the car](report/assets/pictures/cad_car.png){#fig:cad_car}

Through some simple non-formal tests, it was concluded that this design produced a car that drives around 6 km/h. This result prompted the group to stop the design process as most of the goals had been reached.

### Comparing Design to Design Criteria
This section compares the elements of the design criteria to the design of the vehicle in order to assert that the design complies with the criteria.

#### Components
The design of the car has 114 components (without the camera) which might sound like a lot, but to encompass all the necessary functions, this was needed. A trade-off to consider is the weight. By shedding some components the weight of the car can be reduced, but as you can read later on, this might not be needed. There might be some optimizations that can be made, but this will not be focused on here.

#### Speed
Given a car with an appropiate level of speed, we want to know whether or not it is possible to have the car adjust its speed in such a way that it is noticeable by a human. 

Hypothesis
The speed of the car is high enough for a human to notice a different levels of decrease in speed. 

Experiment
Two separate empirical tests were conducted in order to determine whether or not different levels of speeds are noticeable by a human. This is done to fulfill the criterion that multiple speed levels are required. 

The tests were done by having the car drive forward in a straight line while having one human as a control and five others used to make a remark of the moment of decrease. 
The person conducting the tests would know exactly when the decrease in speed was going to happen. 
This number is then compared to the time of remark of the participants. The five participants were participating in the experiment individually. 
Based on the observations, it was then decided whether or not a change was noticeable.

During testing it was observed that the car drove slightly awry. It was then decided that this behavior will be disregarded because nothing could be done about this. 

Analysis
The recorded data are presented in a table below. 

| # Speed Test | Predicted point of decrease | Actual point of decrease | Error |
|--------------|-----------------------------|--------------------------|-------|
|       1      |            x.xx             |           2.00           |  x.xx |
|       2      |            x.xx             |           2.00           |  x.xx |
|       3      |            x.xx             |           2.00           |  x.xx |
|       4      |            x.xx             |           2.00           |  x.xx |
|       5      |            x.xx             |           2.00           |  x.xx |
: 


Based on the observed data presented in table [table_above], we wanted to know the margin of error. In order to get an accurate margin of error, the data was plotted in a graph as x and y values where x is the time and y is the distance. To get a good-fit regression line, the data plots were used to calculate the slope, a, and the y-intercept, b, in the equation

> $$y = a \cdot x + b$$

After drawing the regression line, a predicted value can be extracted. This value resides on the regression line exactly. The predicted value is useful to calculate the error of our model. This was done using the equation for the mean squared error (MSE)

> 	$$ \frac{1}{2} \cdot \sum_{i=1}^{n} (predictedValue - actualValue)^2$$

By using this equation, the mean squared error results in (insert value from MSE here). 
This means that each time a test is performed, the expected error on average (insert value from MSE here) is to be expected. 

So, the whole purpose of this section is to figure out whether or not a decrease of speed of 25% and 50% is noticeable. This is presented in the two table below

| # Speed Test | Noticeable   |
|--------------|:------------:|
| 1            |      Yes     |
| 2            |              |
| 3            |              |
| 4            |              |
| 5            |              |
| 6            |              |
| 7            |              |
| 8            |              |
| 9            |              |
| 10           |              |
: Representation of data for 25% decrease in speed

| # Speed Test | Noticeable |
|--------------|------------|
| 1            |            |
| 2            |            |
| 3            |            |
| 4            |            |
| 5            |            |
| 6            |            |
| 7            |            |
| 8            |            |
| 9            |            |
| 10           |            |
: Representation of data for 50% decrease in speed


Conclusion


#### Weight
As mentioned in section {ref to Components}, some optimizations regarding component count might be possible. As a compromise, only functional components were added to the car, which might sound obvious, but can get hard to design due to Lego's stud system. At the time of writing, the weight of the car and its components have not been measured, but as mentioned earlier, the speed is deemed appropriate, and as such, the weight goal is considered achieved.

#### Modular
The car was built with modularity in mind. The camera, programming block, and wheels are easily removed. Unfortunately, the motors are a bit more difficult to remove, since they sit at the base of the car. This was due to the motors being the centerpiece of the build, as this makes a more rigid car.

#### Steering
Steering is not the most important feature, and as such, not a lot of time was spent on it. A simple test showed that the car is turning slightly to the right. The issue has been isolated to the front wheels, but nothing will be done about it since the car drives a great enough distance before turning.

### Results
The purpose of this section was to construct a durable LEGO car based on certain design criteria. The finished design fulfills all criteria to an acceptable extent. Some issues were introduced in the process regarding weight, and modularity. The result was a more durable and reliable design. 
