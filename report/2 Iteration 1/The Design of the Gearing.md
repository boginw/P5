## The Design of the Gearing
This section introduces the concept of gearing. When building a car, gearing is essential in controlling acceleration and velocity. This section includes a quick overview of what gearing is and how it is implemented in this project.

### Gearing in Vehicles
By using gears, one can transfer rotational velocity from one axle to another one. By utilizing different sizes of gears, one can convert torque to a higher velocity and vice-versa. An example would be a 1:4 relationship where one full rotation of the larger gear would result in four full rotations on the smaller gear, which means that there is an increase in velocity by a factor of four. The other way around would divide the velocity by four, but increase the torque by a factor of four.

### Torque
Torque is a measure of force that is applied to an object anywhere but its center of mass. Applying a force to an object somewhere that is not the object's center of mass causes the object to rotate around its center of mass. An essential part of the vehicle to be built is the starting torque. A gear ratio that generates more velocity might cause the car to stall due to it not having enough torque to initiate its acceleration. The more the vehicle weighs, the more torque is required. Torque is defined by the equation below:

> $$\tau = F \times d$$

Where $F$ stands for *force* and $d$ stands for *distance* from the center of mass. If a line were drawn from the center of mass and $d$ units out, the force should be applied perpendicular to this line at its end.
