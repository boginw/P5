## The Design of the Gearing
This section introduces the concept of gearing. When building a car, gearing is an essential part of acceleration and speed. This section includes a quick overview of what gearing is and how it is implemented in this project.

### Gearing in Vehicles
The basic concept is simple: You can use gears to transfer rotational velocity from one axle to another one. By utilizing different sizes of gears, you can convert torque to a higher velocity and vice-versa. An example would be a 1:4 relationship where one full rotation of the larger gear would result in four full rotations on the smaller gear, which means that there is an increase in speed by a factor of four. The other way around would divide your velocity by four, but increase your torque by a factor of four. 

### Horsepower and Torque
#### Torque
Torque is a measure of force that is applied to an object anywhere but its center of mass. Applying a force to an object somewhere that is not the object's center of mass, causes the object to rotate around its center of mass. An essential part of the vehicle to be built is the starting torque, as using a gear ratio that generates more speed might cause the car to stall due to it not having enough torque to start its acceleration. The more the vehicle weighs, the more torque is required. Torque defined by the equation below:

> $$\tau = F \times d$$

where $F$ stands for *force* and $d$ stands for *distance*.

### RPM and Optimal Gearing
Different gearing options were tested with the amount of RPM available. Gear ratios anywhere between $\ \frac{1}{6}$ or 1:6 to $\ \frac{1}{9}$ or 1:9 resulted in a very low amount of torque. By using these gearing ratios, placing the car on the ground would result in too much resistance and the vehicle would have the gears jump teeth creating little to no motion, which is not preferable. Therefore, a gear ratio of $\ \frac{1}{3}$ or 1:3 would be a more reasonable gear ratio, however, possibly not the best as 1:4 and 1:5 were not tested. 

### The LEGO Car Gearing
Based on the findings of optimal gearing, it was decided that a gear ratio of $\ \frac{1}{3}$ or 1:3 would be chosen for this project. This gear ratio means that the speed will be **increased** by a factor of three, and the torque will, as a result, be **decreased** by a factor of three. This gear ratio might not be the best option, but it makes sure that the vehicle can operate at different levels of speed, which is desired as the LEGO car should be able to autonomously adjust its speed based on speed signs. 
