## Design Criteria
In this section, the design criteria for the car to be built will be discussed. These criteria are determined from the following:

* Achieving specifications
* Ease of component change, as components might be needed for tests or replacements.
 
These criteria will determine how the car should be designed and will be evaluated on each revision of the vehicle to be built.

### Components
Given the specifications, some of the required components are known in advance to make a functioning speed-sign-recognizing car with adjustable speeds. Including the whole car, the required components are listed below.

* Let $C$ denote the entire car with its components.
* Let $E$ denote the EV3 computer.
* Let $M$ denote the Lego large motor.
* Let $W$ denote a single wheel including tire, of which 4 are required.
* Let $O$ denote the camera.

To be able to test multiple prototypes, and to withhold other criteria, a simple design is preferable to achieve better time efficiency.

### Weight
Let $X_w$ denote the weight of component $X$. With the given minimum required components we can determine the minimum weight of the car as being: 

$$\min(C_w) = E_w + M_w + O_w \times 4$$

The weight will affect many other criteria, as more weight requires more torque, which leads to less speed and reduced rigidness. The weight will, therefore, be important to keep low.

### Speed
There is a need for different speed levels which are distinguishable so that the differences can be clearly observed. This results in a need for sufficient max speed ($C_s$) to accommodate this.

### Rigidness
The car should be able to crash into a wall at max speed, without getting destroyed. Due to the necessary tests to be performed, a crash is very likely. This, in turn, means the car should have a strong frame.

### Modular
As design changes are almost a guarantee, a modular design can be used to eliminate some of the hassles later on. Other benefits include changing of battery on the EV3 computer and testing multiple gearing solutions.

### Steering
A car that can turn is not in the project's scope, as it is deemed irrelevant to achieve speed sign recognition and speed adjustment according to seen signs. Even though turning is deemed irrelevant, the car should still be able to drive in a straight line, as this makes observing different speeds easier.
