## The Design of the Gearing
This section introduces the concept of gearing. When building a car, gearing is an essential part of acceleration and speed. A quick overview of what gearing is and how it is implemented in this project will be included. 

### Gearing in Vehicles
The basic concept is simple: You can use gears to transfer motion from one rotational axle to another one. This is beneficial because by rotating a big axle and transfering its motion to a smaller axle, you can generate more *speed*. An example would be a 1:4 relationship where one full rotation of the big axle would result in four full rotations on the smaller axle. This means that there is an increase in speed by a factor of four. The other way around would count if you wanted more torque meaning a decrease in speed by a factor of four. 

### Horsepower and Torque
#### Torque
Torque is a measure of force that can cause an object to **rotate** around an axis. Rotation here is important because applying force to an object moving it in a straight line has nothing to do with torque. Torque is basically what causes motion as the applied force over a given distance generates torque and therefore the movement of an object in a rotational direction. The output of torque is the amount of *work* done. The word "work" is somewhat loosely defined, but it is refered to as the cause of movement of a vehicle from point A to point B. This is an essential part of a vehicle because during initial acceleration a high amount of torque is required to move the car. The more the vehicle weighs, the more torque is required. Torque is calculated by multiplying *force* by *distance*

> $$T = F \cdot d$$

where F stands for *force* and d stands for *distance*. 

#### Horsepower
Horsepower is the rate at which work is done. Horsepower is calculated as the product of torque and RPM (revolutions per minute). It basically states how fast you can move a certain object from point A to point B. Torque gets the vehicle to point B, but horsepower is how fast you get there. Horsepower is calculated by multiplying *torque* with *RPM*

> $$Horsepower = T \cdot RPM$$

Note that this formula is for rotating objects, which is what is used here. 
### RPM and Optimal Gearing
Different gearing options were tested with the amount of RPM available. Gear ratios anywhere between $\ \frac{1}{6}$ or 1:6 to $\ \frac{1}{9}$ or 1:9 resulted in a very low amount of torque. By using these gearing ratios, placing the car on the ground would result in too much resistance and the vehicle would have the gears jump teeth creating little to no motion, which is not preferable. Therefore, a gear ratio of $\ \frac{1}{3}$ or 1:3 would be a more reasonable gear ratio, however, possibly not the best as 1:4 and 1:5 were not tested. 

### The LEGO Car Gearing
Based on the findings of optimal gearing, it was decided that a gear ratio of $\ \frac{1}{3}$ or 1:3 would be chosen for this project. This gear ratio means that the speed will be **increased** by a factor of three, and the torque will, as a result, be **decreased** by a factor of three. This gear ratio might not be the best option, but it makes sure that the vehicle can operate at different levels of speed, which is desired as the LEGO car should be able to autonomously adjust its speed based on speed signs. 
