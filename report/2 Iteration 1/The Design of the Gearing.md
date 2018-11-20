## The Design of the Gearing
This section introduces the concept of gearing. When building a car, gearing is an essential part to control acceleration and velocity. This section includes a quick overview of what gearing is and how it is implemented in this project.

### Gearing in Vehicles
By using gears, one can transfer rotational velocity from one axle to another one. By utilizing different sizes of gears, one can convert torque to a higher velocity and vice-versa. An example would be a 1:4 relationship where one full rotation of the larger gear would result in four full rotations on the smaller gear, which means that there is an increase in velocity by a factor of four. The other way around would divide the velocity by four, but increase the torque by a factor of four.

### Torque
Torque is a measure of force that is applied to an object anywhere but its center of mass. Applying a force to an object somewhere that is not the object's center of mass causes the object to rotate around its center of mass. An essential part of the vehicle to be built is the starting torque. A gear ratio that generates more velocity might cause the car to stall due to it not having enough torque to initiate its acceleration. The more the vehicle weighs, the more torque is required. Torque is defined by the equation below:

> $$\tau = F \times d$$

Where $F$ stands for *force* and $d$ stands for *distance* from the center of mass. If a line were drawn from the center of mass and $d$ units out, the force should be applied perpendicular to this line at its end.

### RPM and Optimal Gearing
Different gearing options were tested with the amount of RPM available. Gear ratios anywhere between 1:6 to 1:9 resulted in a meager amount of torque. By using these gearing ratios, placing the car on the ground would result in too much resistance, and the vehicle would have the gears jump teeth creating little to no motion, which is not preferable. Therefore, a gear ratio of 1:3 would be a more reasonable gear ratio, however, possibly not the best as 1:4 and 1:5 were not tested.

### The LEGO Car Gearing
Based on the findings of optimal gearing, it was decided that a gear ratio of 1:3 would be chosen for this project. This gear ratio means that the velocity will be increased by a factor of three, and the torque will, as a result, be divided by three. This gear ratio might not be the best option, but it makes sure that the vehicle can operate at different levels of velocity as required.

### Results
The purpose of this section was to describe the concept of gearing, experiment with several gearing options and finally select an appropriate gear ratio. The gear ratio should both enable liberal movement of the car by providing sufficient torque, but also have different levels of velocity to accommodate the need for adjustability based on the purpose of the overall project. The gear ratio 1:3 was chosen, as this ratio was deemed to provide the best options regarding torque and velocity based on what was desired for the car.
