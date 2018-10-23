## The Design of the Vehicle's Chassis
This section describes how the car was built and the reasoning behind the design. 

Most vehicles on the road are more or less square shaped. In this subsection, the reasoning of why precisely this design choice is an excellent way to construct the LEGO car's chassis will be described.

### The Shape of the Car
Looking at the body and the frame of a car, today's cars are typically designed in one of two ways: *unibody*, or *body-on-frame*. The table below lists a few trade-offs on why one is preferred over the other [^unibody_vs_body_on _frame].

| Features          |     | Unibody | Body-on-frame |
| ----------------- | --- | ------- | ------------- |
| Safety (crash)    |     | [x]     | []            |
| Fuel economy      |     | [x]     | []            |
| Weight            |     | [x]     | []            |
| Stress handling   |     | [x]     | []            |
| Off-road strength |     | []      | [x]           |
| Resist twisting   |     | []      | [x]           |

From the table above it can be concluded that the features the LEGO car need the most are provided by the unibody. Off-road strength and resisting twisting are features that the LEGO car will never need as it will never experience different terrains other than a standard dry floor. The primary feature of interest is the *weight*, as is also one of our design criteria [ref til design criteria her]. A car as light as possible is desired to make less torque required to initate acceleration and to have an appropiate speed enabling the car to adjust according to speed signs [ref til design criteria her om speed]. Torque and speed will be discussed in another section **[Hardware analysis ref her]**. 

+ Images (refer to appendix)

### The Balance of the Car

The optimal distribution of the weight of the car and its structural integrity are two aspects that were considered when designing the car. The general center of mass is important to consider in order to not make the car lean towards a certain direction or create more preasure on one end of the car. The programming block weighs .288 kg whereas the two motors combined weigh .162 kg. If the programming block was to be positioned at the very rear end of the LEGO car, it would create an unequal weight distribution casuing the LEGO car to lift upon initial acceleration. 
This assumption holds true if the motors were to drive the rear wheels. 
By making the LEGO car front wheel driven, the first issue of lifting upon initial acceleration is discarded. However, if the programming blocks center of mass is further away from the middle than the rear wheel pivot points, the car is at risk of tipping, and then the car will not be able to drive anyway, as the front wheels does not touch the ground. Therefore, by positioning the programming block more towards the center of mass of the car, the issue of direct pressure on the rear wheels are discarded. Designing the car this way results in a car with a more equally distributed weight. However, it will be difficult to create a 100% equal weight distribution as the programming block weighs 43.8% more in total weight compared to the two motors. 

+ Images (refer to appendix)

### The Chassis Design
As is depicted on the images in the appendix, the design chosen was the square shape. The reason is the general balance of the car and not needing off-road strength, but also because it was the most preferred out of the few prototypes that were drawn before building the actual LEGO car. 

### Conclusion
The purpose of this section was to construct a durable LEGO car based on certain design criteria. Different designs were drawn to experiment on a few car designs on a theoretical level. The design that was chosen to work with was initially rear wheel driven, but during the process of building the car, it was deemed difficult to work with due to issues with the weight distribution. Therefore, by reiterating the design, making it rear wheel driven and slightly repositioning some of the components, the result was a more durable and reliable design than the previously designed car. 


[^unibody_vs._body_on_frame]: http://www.autonews.com/article/20170626/OEM01/170629864/body-on-frame-vs.-unibody:-pros-and-cons

