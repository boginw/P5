# Discussion
The following section will discuss the results presented in the report, along with the findings presented along the way.

## Problem statement
Based on the problem statement defined in the beginning of the report, the following section will discuss the subproblems and their requirements.
The problem statement was:

> "How can a car-system be designed to recognize speed limits, based on visual inputs, such that the car-system never exceeds the speed limit?"

This problem statement had two different subproblems that supports the problem statement.

**A car must be designed and built out of LEGO**
This subproblems specified three criteria to ensure fulfillment of the problem.
The first criteria demands the car upholds criteria specified by the group.
These criteria was discovered and defined in [@sec:DesignCritera], where the relevant criteria to discuss is the speed criteria.
The project demands for the speed to be high enough to recognize different speed levels, one for each sign.
As the car is not capable of reaching speeds that actually surpass the speed of real speed signs, this became a term that was hard to coin.
When is a car capable of achieving a top speed high enough to allow for multiple different speed levels?
Technically, if the car was capable of driving 0.10 km/h the car could represent 10 speed levels, each differing by a step of 0.01 km/h.
This is hardly relevant for the project, as that differentiation on speed probably would not be differentiable by the human eye, or even measuring equipment, such as a RPM counter.
Another question is, whether the motors are actually capable of expressing speed differences in that range.

Further refinement of the criteria could be done, in order to investigate what the intended outcome of the criteria are.
If the intended outcome is to make a system where the change of speed is recognizable by the human eye, the test for verification could have considered a test person that tries to identify when the car changes speed.
If the test person correctly recognizes the time at which the car changes speed, the speed of the car is sufficient.
The criteria was resolved by running tests of the different gearing configurations during the distance on which the car is expected to reach full speed.
During these tests, the second criteria demanding that the car drives straight was asserted, as the car did not deviate from a straight line.

The last criteria demanded the car to be able to receive and react to commands through a wired or wireless connection.
In this project, the wired connection was chosen.
This is described in [@sec:communication] where a master-slave relationship is defined between a Raspberry PI and the EV3.

**A system capable of recognizing speed signs must be designed and implemented**
This subproblem also specified three criteria to ensure fulfillment of the problem.
The first criteria demands for the project to use commonly obtainable video-capturing hardware.
This was achieved by using a standard USB webcam, and abstracting the implementation to allow for any USB webcam to be used.
[@Sec:webcam] describes this application.

The second criteria demands for the project to be able to recognize different speed signs with a success rate that is higher than random.
In the project, the neural network was successfully configured to recognize 6 different speed signs, all with an accuracy of about 68 %.
With 6 different speed signs, a random probability would be an accuracy of 16 %.
The system is more than 4 times as good as a random guess.

The last criteria demands for the machine intelligence to be able to send commands to the car through a wired or wireless connection.
As mentioned under the last criteria, a wired connection was chosen, as is described in [@sec:communication].
The neural network simply tells the car which kind of speed sign was recognized, and the car becomes responsible for the right adjustment according to the result.

## Neural network
For the project, an artificial neural network from the OpenCV Java library was chosen, as described in [@sec:somethingThatComesBeforeANN].
This implementation of the neural network only allowed for one activation function to be used in the entirety of the network.
Some networks are designed with different configurations, where some layers or neurons have one activation function, while other layers have another activation function.

Whether this would actually provide a better result is hard to know without actually testing the different solutions, as it depend on the specific problem.
A general rule of thumb is that using a linear function right before the output layer is best used when the prediction needs to be any real number, which is not the case in this problem domain.
Instead, the sigmoid function is better for binary decisions like the ones present in this project, i.e. is it a 20 km/h speed sign or not?

Overall, this project did not pursue using different activation functions, but future research might find it beneficial to investigate alternative activation functions, as well as a mix of these.

### OpenCV Java
As already mentioned, library functions from OpenCV was used for much of the process.
OpenCV provides a bunch of well-supported and well-documented functionality that was used in the project to create both the preprocessor and the actual neural network.
However, one of the decisions taken early in the project was to implement the project in Java, as that was the language most of the group felt familiar with.
As the OpenCV library is originally implemented in C++, much of the equivalent Java functionality is migrated from C++, or sometimes even referencing some C++ functions.
Much of the OpenCV community also revolves around the C++ version of OpenCV, which is why a big part of the documentation and community-driven guides had to be migrated from C++ to Java to implement it in the project.
In hindsight the added cost of gaining familiarity with C++ would probably not exceed the cost of understanding and migrating C++ documentation to Java documentation.
Another added benefit, discussed later, is the lack of garbage collection in the C++ programming language.

### Input {#sec:input}
In [@sec:separate], it was discussed whether to separate the numbers or not.
A naive approach that simply splits the speed sign in half was applied in order to test whether or reducing the input would yield a better result.
An immediate effect of the training of the network was experienced, as the network increased it's accuracy by approximately 30 %.
The effect relates to the discussion that was shortly introduced in [@sec:skip_prep], where it was discussed whether the necessity of introducing preprocessing was needed.
The argument for introducing preprocessing was that the neural network would have a much easier time generalizing, as it would not have to worry about the different environmental variables influencing the quality of the input images.
Instead, the neural network would only have to process the data that was actually subject to change.
Since all speed signs post speed divisible by 10, the last digit will always be zero.
Giving the entire speed sign as an input to the neural network is a contradiction to the previous argument.
On the lowest of speed signs (range 20 km/h - 90 km/h), roughly half the speed sign will never change, and will never have an influence on the actual output of the speed, because the right half of the speed sign is simply a zero.
When it comes to speed signs at 100 km/h or above, the relevant area is the one containing the first two digits, but the last digit might very well be discarded.

The strategy that is currently introduced, as also described in [@sec:whereWeTalkAboutSplittingTheSpeedsign], simply cuts image of the speed sign in half.
This is a naive strategy that only works for speed signs below 100 km/h, as each digit takes up roughly half of the image.
If it was applied to speed signs above 100 km/h it would affect a bit of the second digit, as that is in the middle of the image.
As the project only considers speed signs below 100 km/h, and since the problem was only identified and corrected at the end of the project, the solution is passable for this application, but in future research project, a more sophisticated solution should always be used.
A proposal for such a solution is discussed in [@sec:obtainDigits].

Implementing the solution, whether it is naive or not, will give a better result, as the area that the neural network is working on becomes smaller.
This means the neural network will have an easier time generalizing when it only receives the relevant digits, as the area that is has to consider is upwards of 50 % less.

## Normalization
The project used normalization in the form of a pre-processor that tried to normalize the input images in order to deliver as similar input as possible to the neural network.
The following section will discuss some of the strategies involved in the normalization.

### Obtaining everything but the last digit {#sec:obtainDigits}
As discussed in [@sec:input], there is a need for obtaining only the relevant digits, since that only lets the neural network consider the digits that matter for the result.
The last digit will be insignificant in all cases, as all speed limits are divisible by ten.
To obtain the area of the image where the digits are positioned, we suggest using the solution proposed by [@real_time_detection] on both the x- and y-plane.
The solution first uses projection onto the x-plane, to create a histogram where local minima close to zero will represent the white space between digits.
In this way, the number of digits and their respective position can be determined, and each digit can be isolated.
As each image will only be cropped on the x-axis, whitespace will still appear above and below the input image.
If another vertical projection is performed on every single digit individually, onto the y-axis, the height of the digit can be precisely determined, and the image can be cropped to fit the edge of each digit.

### Flattening the circle
One of the problems discovered in this project, was the angled perspective of speed signs as the car drove by them.
When the pre-processor tries to identify speed signs in the image, it initially only considers perfect circles.
Even at small angles, the speed sign is no longer a perfect circle, but an ellipse.
In all instances experienced, however, recognizing perfect circles would still capture the speed sign - even if it was truly an ellipse.
The algorithm used only needs a part of a circle to construct the entirety of the circle.
Combined with the wide red ring defining the outer perimeter of the speed sign, the algorithm is capable of fitting a perfect circle in a big part of the ellipse.

As described in [@sec:flattening], the algorithm uses four points on the periphery of the speed sign to transform and project the image into a flattened image, as seen in [@fig:webcam_demo].
This transformation allows for speed signs with a quite extreme angle to be represented as a flattened image.
We tried measuring the impact on the performance of this pre-processing step, but the amount of data available for this test was not enough to get any conclusive results.
Based on the arguments in [@sec:input], however, the group decided to include the step in the pre-processor to increase the normalization of the data, as it intuitively provided a clearer input for the neural network.

### Rotating number
Another consideration for the pre-processor was the ability to rotate the number.
Numbers in speed signs viewed at an angle, or speed signs bent by a truck will be rotated, so they are not standing up.
Although it is not something that was tested in the project, it could have an impact on the neural network if the numbers was tilted.
[@rotate] suggests rotating the number until the bounding box is as narrow as possible.

The method might be beneficial if the target architecture doing the recognition contains a graphical processing unit (GPU), as rotation requires a lot of matrix multiplication.
The EV3 architecture that was the target of this project does not include a GPU, which is why we decided not to begin rotating the image.

## Car
The following section will discuss some of the implementation specific details regarding the car.

### Language and third-party software
To use common high-level programming languages on the car, it is necessary to install third-party software.
The third-party software uses some of the available memory in the car, and will thus shrink the amount of memory available for the other programs to execute.
Furthermore, the project had a requirement to analyze the amount of memory used, and guarantee that the program never runs out of memory.

When we choose to install a Java based operating system, allowing us to write Java, we also opted-in on the garbage collection features of Java.
When we have to analyze the amount of memory used, we have no way to know when garbage collection occurs.
Assuming no garbage collection, the program will inevitably run out of memory, as it runs in an infinite loop as long as the car is powered.

All other assumptions of garbage collection will be speculation, and simply reduced to qualified guesses, meaning no guarantees can be given.

### Steering
One feature that would improve the car, would be including some kind of steering, enabling the car to follow a line.
The car initially had some trouble driving in a straight line, but this was resolved by changing the design of the car.
However, if the car were to follow a line, it would be able to make turns, and the car could be expected to follow long tracks of road, experiencing several different speed signs along the way.
We decided not to implement any of this behavior, as the design of the car is capable of driving on a track where it has time to recognize a single speed sign.
For future work, however, it should be considered if a project could benefit from some kind of steering that allows the car to turn, and correct it's course, if the test should be conducted on longer tracks.
