## Design Criterion

In this section the design criteria for the car to be built will be discussed. These criteria are determined from the following:

* Achieving specifications
* Ease of prototyping
* Ease of component change
 
These criteria will determine how the car should be designed, and will be evaluated on each revision of the car to be built.

### Components

Given the specifications some components are already known to be required to build a functioning speed-sign-recognizing car with adjustable speeds. Including the whole car, the required components are listed below.

  * Let $C$ denote the entire car with its components.
* Let $E$ denote the EV3 computer.
* Let $M$ denote the Lego large motor.
* Let $W$ denote a single wheel including tire, of which 4 are required.
* Let $O$ denote the camera.

To be able to test multiple prototypes, and to withhold other criterion, a simple design is preferable to better optimize other criterion and for better time efficiency.

### Weight

Let $X_w$ denote the weight of component $X$. With the given minimum required components we can determine the minimum weight of the car as being: 

$$\min(C_w) = E_w + M_w + O_w \times 4$$

As the weight will affect many other criterion, as more weight requires more torque, hence less speed, and reduced rigidness, this will be important to keep low.

### Speed

To illustrate the car changing speed, it will be important to have speed settings, which are easily observed visually. This, in turn, sets a requirement for a large speed range. As such, higher max speed ($C_s$) is preferable.

### Rigidness

The car should be able to crash into a wall as max speed, without getting destroyed. Due to the necessary tests to be performed, a crash is inevitable. This in turn means the car should have a strong frame.

### Modular

As design changes is almost a guarantee, a modular design can be used to eliminate some of the hassle later on. Other benefits include changing of battery on the EV3 computer, and testing multiple gearing solutions.

### Steering

A car that can turn is not in the projects scope, as it is deemed irrelevant to achieve speed sign recognition and speed adjustment according to seen signs. Even though turning is deemed irrelevant, the car should still be able to drive in a straight line, as this makes observing different speeds easier.
