## Design Criteria {#sec:DesignCritera}
In this section, the design criteria for the car to be built are discussed.
These criteria are determined from the following:

* Achieving specifications
* Ease of component change, as components might be needed for tests or replacements.

These criteria will determine how the car should be designed and will be evaluated on after the car has been built.

### Components
Given the specifications, some of the required components are known in advance to make a functioning speed-sign-recognizing car with adjustable speeds.
Including the whole car, the required components are listed below.

* Let $C$ denote the entire car with its components.
* Let $E$ denote the EV3 computer.
* Let $M$ denote the Lego large motor.
* Let $W$ denote a single wheel including a tire, of which four are required.
* Let $O$ denote the camera.
* let $R$ denote the Raspberry Pi.

To be able to test multiple prototypes, and to withhold other criteria, a simple design is preferable to achieve better time efficiency.

### Weight
Let $X_w$ denote the weight of component $X$.
With the given minimum required components we can determine the minimum weight of a car as being:

$$\min(C_w) = E_w + M_w + R_w + (O_w \times 4)$$

The weight will affect many other criteria, as more weight requires more torque, which leads to less speed.
The weight will, therefore, be important to keep low.

### Speed {#sec:DesignCritSpeed}
There is a need for different levels of velocity which are distinguishable, so that the difference in velocity between levels can be easily observed.
This results in a need for sufficient max velocity ($C_s$) to accommodate the needed span of levels.
Moreover, it is important that the velocity is obtained rather quickly, as the car will be running on a track of limited length.
How quickly the velocity has to be obtained is arbitrarily chosen, and in this project we chose 50 cm (or less) of travel distance.

### Modular
As design changes are almost a guarantee, a modular design can be used to eliminate some of the hassles later on. Other benefits include changing of battery on the EV3 computer and testing multiple gearing solutions.
