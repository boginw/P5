## Design Criteria {#sec:DesignCritera}
In this section, the design criteria for the car to be built are discussed.
These criteria are determined from the following:

* Achieving specifications
* Ease of component change, as components might be needed for tests or replacements.

These criteria will determine how the car should be designed and will be evaluated after the car has been built.

### Components
Given the specifications, some of the required components are known in advance to make a functioning speed-sign-recognizing car with adjustable speeds.
Including the whole car, the required components are listed below.

* The EV3 computer
* Two large LEGO motors
* Four wheels
* A chassis
* A standard USB webcamera
* A Raspberry PI

To be able to test multiple prototypes, and to withhold other criteria, a simple design is preferable to achieve better efficiency in terms of speed and ease of design.

### Weight
The weight is a crucial variable when it comes to the gearing.
The heavier the car, the more mass has to be accelerated, and the lower the gearing will be able to do it.
A heavier car needs more power to be applied to get the car to move, which in turn requires a stronger chassis to keep the structural integrity.

The weight will also affect many other criteria, as more weight requires more torque, which leads to less speed.
The weight will, therefore, be important to keep low, and all extra hardware except for the minimum required components should be discouraged.

### Speed {#sec:DesignCritSpeed}
There is a need for different levels of velocity which are distinguishable, so that the difference in velocity between levels can be easily observed.
This results in a need for sufficient max velocity to accommodate the needed span of levels.
Moreover, it is important that the velocity is obtained rather quickly, as the car will be running on a track of limited length.
How quickly the velocity has to be obtained is arbitrarily chosen, and in this project we chose 50 cm of travel distance.

### Modular
As design changes are almost a guarantee, a modular design can be used to eliminate some of the hassles later on. Other benefits include changing of battery on the EV3 computer and testing multiple gearing solutions.
