## Designing and Building of the Car
This section describes how the car was designed. Included is a description of the tools used, which features it brings and then steps through the design criteria to assert that the vehicle fulfills the criteria.

### CAD design
By utilizing LeoCAD[@leocad_website] a car with all the components, except the camera, was designed. By utilizing this tool, disassembly and reassembly become much easier since the steps for assembling are available through the tool. A rendered image of the car can be seen in figure {@fig:cad_car}

![CAD model of the car](report/assets/pictures/cad_car.png){#fig:cad_car}

Through some simple non-formal tests, it was concluded that this design produced a car that drives around 6 km/h. This result prompted the group to stop the design process as most of the goals had been reached.

### Comparing Design to Design Criteria
This section compares the elements of the design criteria to the design of the vehicle in order to assert that the design complies with the criteria.

#### Components
The design of the car has 114 components (without the camera) which might sound like a lot, but to encompass all the necessary functions. A trade-off to consider is the weight. By shedding some components the weight of the car can be reduced, but as you can read later on, this might not be needed. There might be some optimizations that can be made, but this will not be focused on here.

#### Speed
With no specific requirement for velocity, a subjective determination is made regarding the fulfillment of the criterion. A simple test showed that the car drives approximately 6 km/h, which leaves plenty of room for different speed levels. An obvious segmentation would be to have 1 km/h to correspond to 10 km/h, 2 km/h to 20 km/h, and so on, which leads to six speed levels. With multiple speed levels and reasonable top speed, this fulfills the criterion.

#### Weight
As mentioned in section {ref to Components}, some optimizations regarding component count might be possible. As a compromise, only functional components were added to the car, which might sound obvious, but can get hard to design due to Lego's stud system. At the time of writing, the weight of the car and its components have not been measured, but as mentioned earlier, the speed is deemed appropriate, and as such, the weight goal is considered achieved.

#### Modular
The car was built with modularity in mind. The camera, programming block, and wheels are easily removed. Unfortunately, the motors are a bit more difficult to remove, since they sit at the base of the car. This was due to the motors being the centerpiece of the build, as this makes a more rigid car.

#### Steering
Steering is not the most important feature, and as such, not a lot of time was spent on it. A simple test showed that the car is turning slightly to the right. The issue has been isolated to the front wheels, but nothing will be done about it since the car drives a great enough distance before turning.

### Results
The purpose of this section was to construct a durable LEGO car based on certain design criteria. The finished design fulfills all criteria to an acceptable extent. Some issues were introduced in the process regarding weight, and modularity. The result was a more durable and reliable design. 

